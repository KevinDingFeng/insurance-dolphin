package com.shenghesun.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.shenghesun.util.cpic.StringUtils;


/**
 * 
 * @author hao.tan
 *
 */
@Service
@Transactional(readOnly = true)
public class SmsCodeService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	//@Value("${sms.url}")
	//private String smsUrl;

	@Value("${sms.app.key}")
	private String smsAppKey;

	@Value("${sms.app.secret}")
	private String smsAppSecret;
	
	/**
	 * 发送短信
	 * @Title: sendSms 
	 * @Description: TODO 
	 * @param phoneNumbers 手机号
	 * @param signName 短信签名
	 * @param templateCode 短信模板code
	 * @param templateParam 模板中的变量替换JSON串
	 * @return  String 
	 * @author yangzp
	 * @date 2018年11月2日上午10:27:41
	 **/ 
	public String sendSms(String phoneNumbers, String signName, String templateCode, String templateParam) {
		// 设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		final String product = "Dysmsapi";// 短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";// 短信API产品域名（接口地址固定，无需修改）
//		final String templateCode = "SMS_147196678";
		// 替换成你的AK
		//final String AccessKeyID = smsAppKey;// 你的accessKeyId,参考本文档步骤2
		//final String AccessKeySecret = smsAppSecret;// 你的accessKeySecret，参考本文档步骤2
//		final String AccessKeyID = smsAppKey;
//		final String AccessKeySecret = smsAppSecret;
		// 初始化ascClient,暂时不支持多region
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsAppKey, smsAppSecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			
			IAcsClient acsClient = new DefaultAcsClient(profile);
			// 组装请求对象
			SendSmsRequest request = new SendSmsRequest();
			// 使用post提交
			request.setMethod(MethodType.POST);
			// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
			request.setPhoneNumbers(phoneNumbers);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName(signName);
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
			if(StringUtils.isNotEmpty(templateParam)) {
				request.setTemplateParam(templateParam);
			}
			
			// 可选-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");
			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			// request.setOutId("yourOutId");
			// 请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			
			if(sendSmsResponse != null && "OK".equals(sendSmsResponse.getCode())){
				return "success";
			}
			log.info(sendSmsResponse.getCode()+":"+sendSmsResponse.getMessage());
			return "failed";
		} catch (ClientException e) {
			log.error("Exception {} in {} ", e.getMessage(), "sendSmsCode:"+phoneNumbers);
			return "failed";
		}
	}

	/**
	 * 检查手机号是否为空或者格式是否正确（已弃用）
	 * 
	 * @author hao.tan
	 * @param num
	 */
	public String checkNumber(String num) {
		if (num == null || num.length() == 0) {
			return "手机号不能为空";
		} else if (this.checkNumByRegex(num)) {
			return "手机号格式不正确";
		}
		return null;
	}

	/**
	 * 校验手机号码格式（已弃用）
	 * 
	 * @author hao.tan
	 */
	public boolean checkNumByRegex(String num) {
		Matcher m = Pattern.compile("/^(((13[0-9]{1})|(14[7]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+/d{8})$/")
				.matcher(num);
		return m.find();// boolean
	}

}
