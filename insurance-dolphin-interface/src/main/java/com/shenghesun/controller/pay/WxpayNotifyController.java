package com.shenghesun.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

import com.shenghesun.entity.PayMessage;
import com.shenghesun.service.PayService;
import com.shenghesun.service.cpic.AsyncService;
import com.shenghesun.util.SmsCodeService;

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
	
	/**
	 * 支付成功通知模板code
	 */
	@Value("${sms.success.template.code}")
	private String templateCode;
	
	
	public Element getRootElement(HttpServletRequest request)
			throws IOException, DocumentException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		String xmlStr = sb.toString();
		Document document = DocumentHelper.parseText(xmlStr);
		Element root = document.getRootElement();
		return root;
	}
	
	@RequestMapping(value = "/notify", method = {RequestMethod.GET, RequestMethod.POST})
	public String wxnotify(HttpServletRequest request,
			HttpServletResponse response) throws IOException, DocumentException {
		
		Element root = this.getRootElement(request);
		String returnCode = root.element("return_code").getText();
		Element e = root.element("return_msg");
		String returnMsg = "success";//TODO
		if(e != null) {
			returnMsg = e.getText();
		}
		String smsStatus = null;
		if ("SUCCESS".equals(returnCode)) {//支付成功
			//商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，
			//且在同一个商户号下唯一。
			String orderNo = root.element("out_trade_no").getText();
			PayMessage payMessage = payService.findByOrderNo(orderNo);
			//执行异步接口
			asyncService.executeAsync(payMessage);
			
			//发送成功短信
		    smsStatus = smsCodeService.sendSms(payMessage.getInsuranttel(), "伟林易航",templateCode,"");
		    logger.info("订单号为:"+payMessage.getOrderNo()+";手机号为："+payMessage.getInsuranttel()+"的订单成功短信通知" + smsStatus);
		} else {
			//发送支付失败短信
			logger.info("订单支付失败");
		}
		Document document = DocumentHelper.createDocument();
		Element xmlE = document.addElement("xml");
		xmlE.addElement("return_code").setText(returnCode);
		xmlE.addElement("return_msg").setText(returnMsg);
		return document.asXML();
	}
	
}
