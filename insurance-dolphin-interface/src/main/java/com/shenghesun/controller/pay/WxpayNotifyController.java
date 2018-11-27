package com.shenghesun.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	public Element getRootElement(HttpServletRequest request)
			throws IOException, DocumentException {
		/*BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String xmlStr = sb.toString();*/
		String xmlStr = getXml(request);
		Document document = DocumentHelper.parseText(xmlStr);
		Element root = document.getRootElement();
		return root;
	}

	@RequestMapping(value = "/notify", method = {RequestMethod.GET, RequestMethod.POST})
	public String wxnotify(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		WXPay wxPay = new WXPay(conf, notifyUrl);

		Element root = this.getRootElement(request);
		String returnCode = root.element("return_code").getText();
		Element e = root.element("return_msg");
		String returnMsg = "success";//TODO
		if(e != null) {
			returnMsg = e.getText();
		}
		//创建返回xml
		Document document = DocumentHelper.createDocument();
		Element xmlE = document.addElement("xml");
		xmlE.addElement("return_code").setText(returnCode);
		xmlE.addElement("return_msg").setText(returnMsg);
		String smsStatus = null;
		String returnXml = this.getXml(request);
		if ("SUCCESS".equals(returnCode)) {//支付成功
			//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，
			//且在同一个商户号下唯一。
			String orderNo = root.element("out_trade_no").getText();
			PayMessage payMessage = payService.findByOrderNo(orderNo);
			try {
				logger.info(returnXml);
				//将支付通知结果转换成map,进行签名验证
				Map<String, String> reqData = WXPayUtil.xmlToMap(returnXml);
				logger.info(reqData.toString());
				boolean resultSignValid = wxPay.isPayResultNotifySignatureValid(reqData);
				if(!resultSignValid) {
					logger.info("resultSignValid failed");
					return null;
				}
				//微信支付平台返回信息xml转支付对象并进行保存
				WxPayResult wxPayResult = XStreamUtil.xmlToBean(returnXml, WxPayResult.class);
				WxPayService.save(wxPayResult);
				//如果验证通过并且订单支付金额与支付结果通知返回金额相同则进行投保
				logger.info("orderAmount:"+payMessage.getOrderAmount()*100+"total_fee:"+wxPayResult.getTotal_fee());
				if(Integer.toString((payMessage.getOrderAmount()*100)).equals(wxPayResult.getTotal_fee())) {
					logger.info("sign success");
					//如果订单状态为已经支付，则直接返回成功，不继续进行投保
					if(payMessage.getPayStatus().equals(BaseResponse.pay_staus)) {
						logger.info("pay_status already equals 1");
						return document.asXML();
					}else {
						logger.info("approvl");
						//执行异步接口
						asyncService.executeAsync(payMessage);
					}
				}else {
					logger.info("sign access but orderAccount not equals total_fee");;
					return null;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//发送成功短信
			smsStatus = smsCodeService.sendSms(payMessage.getInsuranttel(), "伟林易航",templateCode,"");
			logger.info("订单号为:"+payMessage.getOrderNo()+";手机号为："+payMessage.getInsuranttel()+"的订单成功短信通知" + smsStatus);
		} else {
			//发送支付失败短信
			logger.info("订单支付失败");
		}
		return document.asXML();
	}

}
