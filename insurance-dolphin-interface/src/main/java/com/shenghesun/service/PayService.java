package com.shenghesun.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.shenghesun.util.wxpay.WXPay;
import com.shenghesun.util.wxpay.WXPayConfig;
import com.shenghesun.util.wxpay.WXPayConfigImpl;

@Service
public class PayService {
	
	//微信发送请求
	public Map<String,String> sendWx(Map<String,String> map){
		 WXPayConfig conf = new WXPayConfigImpl();
	     WXPay wxPay;
	     Map<String,String> resultMap=null;
		try {
			wxPay = new WXPay(conf, "https://wxpay.dazonghetong.com/wxpay/notify");
			resultMap = wxPay.unifiedOrder(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	     return resultMap;
	}
	//获取预支付订单编号
	public void getPrePayId() {
		
	}
	//获取签名
	public void getSign() {
		
	}
	//进行支付
	public void pay() {
		
	}
	
}
