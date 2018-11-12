package com.shenghesun.util.wxpay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WXPayConfigImpl extends WXPayConfig {

	/**
	 *  App ID
	 */
	@Value("${wechat.miniapp.appid}")
	private String appid;
	
	/**
	 * Mch ID
	 */
	@Value("${wechat.pay.mchid}")
	private String mchid;
	
	/**
	 * API 密钥
	 */
	@Value("${wechat.pay.key}")
	private String key;
	
	public String getAppID() {
		return appid;
	}

	public String getMchID() {
		return mchid;
	}

	public String getKey() {
		return key;
	}
	
	public InputStream getCertStream() {
		try {
			return new FileInputStream(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		return null;
	}
	
	public IWXPayDomain getWXPayDomain() {
		return new IWXPayDomainImpl();
	}
}
