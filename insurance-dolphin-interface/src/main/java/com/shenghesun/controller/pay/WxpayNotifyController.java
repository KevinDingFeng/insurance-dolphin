package com.shenghesun.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shenghesun.common.BaseResponse;
import com.shenghesun.entity.PayMessage;
import com.shenghesun.entity.WxPayResult;
import com.shenghesun.service.PayService;
import com.shenghesun.service.WxPayResultService;
import com.shenghesun.service.cpic.AsyncService;
import com.shenghesun.util.SmsCodeService;
import com.shenghesun.util.XStreamUtil;
import com.shenghesun.util.wxpay.WXPay;
import com.shenghesun.util.wxpay.WXPayConfig;
import com.shenghesun.util.wxpay.WXPayUtil;

@RestController
@RequestMapping(value = "/wxpay")
public class WxpayNotifyController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PayService payService;

	@Autowired
	private AsyncService asyncService;

	@Autowired
	private SmsCodeService smsCodeService;

	@Autowired
	private WxPayResultService WxPayService;

	@Value("${weixin.notify.url}")
	private String notifyUrl;

	@Autowired
	private WXPayConfig conf;
	/**
	 * 支付成功通知模板code
	 */
	@Value("${sms.success.template.code}")
	private String templateCode;

	public String getXml(HttpServletRequest request) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String xmlStr = sb.toString();
		return xmlStr;
	}
	public Element getRootElement(String xmlStr)
			throws IOException, DocumentException {
		Document document = DocumentHelper.parseText(xmlStr);
		Element root = document.getRootElement();
		return root;
	}

	@RequestMapping(value = "/notify", method = {RequestMethod.GET, RequestMethod.POST})
	public String wxnotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		WXPay wxPay = new WXPay(conf, notifyUrl);
		//获取请求的xml
		String returnXml = this.getXml(request);
		//将xml转换成Element
		Element root = this.getRootElement(returnXml);
		String returnCode = root.element("return_code").getText();
		Element e = root.element("return_msg");
		String returnMsg = "success";//TODO
		if(e != null) {
			returnMsg = e.getText();
		}
		//创建返回xml
		Document document = getReturnElement(returnCode,returnMsg);
		String smsStatus = null;
		//logger.info("returnXml:"+returnXml);
		if ("SUCCESS".equals(returnCode)) {
			//支付成功
			//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，
			//且在同一个商户号下唯一。
			String orderNo = root.element("out_trade_no").getText();
			PayMessage payMessage = payService.findByOrderNo(orderNo);
			try {
				//将支付通知结果转换成map,进行签名验证
				Map<String, String> reqData = WXPayUtil.xmlToMap(returnXml);
				boolean resultSignValid = wxPay.isPayResultNotifySignatureValid(reqData);
				if(!resultSignValid) {
					return null;
				}
				String total_fee = root.element("total_fee").getText();
				//如果验证通过并且订单支付金额与支付结果通知返回金额相同则进行投保
				if(Integer.toString((payMessage.getOrderAmount()*100)).equals(total_fee)) {
					//如果订单状态为已经支付，则直接返回成功，不继续进行投保
					if(payMessage.getPayStatus().equals(BaseResponse.pay_staus)) {
						return document.asXML();
					}else {
						//微信支付平台返回信息xml转支付对象并进行保存
						save(returnXml);
						//执行异步投保接口
						asyncService.executeAsync(payMessage);
						//发送成功短信
						smsStatus = smsCodeService.sendSms(payMessage.getInsuranttel(), "伟林易航",templateCode,"");
						logger.info("订单号为:"+payMessage.getOrderNo()+";手机号为："+payMessage.getInsuranttel()+"的订单成功短信通知" + smsStatus);
					}
				}else {
					return null;
				}
			} catch (Exception e1) {
				logger.error("Exception {} in {}", e1.getStackTrace(), Thread.currentThread().getName());
			}
			
		} else {
			//发送支付失败短信
			logger.info("订单支付失败");
		}
		return document.asXML();
	}
	/**
	 * 创建返回成功xml的document
	 * @param returnCode
	 * @param returnMsg
	 * @return
	 */
	public Document getReturnElement(String returnCode,String returnMsg) {
		Document document = DocumentHelper.createDocument();
		Element xmlE = document.addElement("xml");
		xmlE.addElement("return_code").setText(returnCode);
		xmlE.addElement("return_msg").setText(returnMsg);
		return document;
	}
	/**
	 * 格式化微信支付返回的时间和总金额数据，并进行数据库保存
	 * @throws ParseException 
	 */
	public void save(String returnXml) throws ParseException {
		WxPayResult wxPayResult = XStreamUtil.xmlToBean(returnXml, WxPayResult.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat simple = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		wxPayResult.setTime_end(simple.format(sdf.parse(wxPayResult.getTime_end())));
		wxPayResult.setTotal_fee(Double.toString((Double.parseDouble(wxPayResult.getTotal_fee())/100)));
		WxPayService.save(wxPayResult);
	}
}
