package com.shenghesun.service.cpic;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.com.cpic.wss.propertyinsurance.commonservice.freight.FreightCommonServiceLocator;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.IZrxCommonService;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage;

 /**
  * WebService客户端通用类
  * @ClassName: WebServiceClient 
  * @Description: TODO
  * @author: yangzp
  * @date: 2018年9月30日 下午1:30:19  
  */
@Service
public class WebServiceClient {
	
	private static final Logger log = Logger.getLogger(WebServiceClient.class);
	
	/**
	 * wsdl url
	 */
	@Value("${cpic.web.wsdl.url}")
	private String _WsUrl;
	
	@Value("${cpic.userName}")
	private String userName;
	@Value("${cpic.password}")
	private String password;
	/**
	 * 险种代码
	 */
	@Value("${cpic.classescode}")
	private String classesCode;
	
	/**
	 * 分公司代码
	 */
	@Value("${cpic.unitcode}")
	private String unitCode;
	
	public IZrxCommonService getBinding() throws ServiceException {
		FreightCommonServiceLocator locator = new FreightCommonServiceLocator();
		String url = _WsUrl+"FreightCommonService?wsdl";
		locator.setFreightCommonServicePortEndpointAddress(url);
		return locator.getFreightCommonServicePort();
	}
	
	/**
	 * 货运险承保接口
	 * @Title: approvl 
	 * @Description: TODO 
	 * @param xml 请求报文xml
	 * @return  String 
	 * @author yangzp
	 * @date 2018年9月30日下午1:48:27
	 **/ 
	public String approvl(String xml) {
		ApprovalRequest request = new ApprovalRequest();
		
		//用户信息
		LoginUser userInfo = new LoginUser();
		userInfo.setUserName(userName);
		userInfo.setPassword(password);
        //产品信息
		ApprovalProduct productInfo = new ApprovalProduct();	
		//航空险种
		 productInfo.setClassesCode(classesCode);
		ApprovalResponse reposne=null;
		try {
			//用户信息
			request.setUserInfo(userInfo);
			request.setProductInfo(productInfo);
			//报文信息
//			String policyInfo = new String(FileUtil.getBytesFromFile(new File("E:\\Users\\c_cailiang\\Desktop\\10月份开发\\海豚经纪国内旅客行李保险方案\\freight-11.xml")));
//			policyInfo=	new String(policyInfo.getBytes());
//			String[] strArray=policyInfo.split("\r\n");
//			StringBuffer buff=new StringBuffer();
//			for(int i=0;i<strArray.length;i++){
//				buff.append(strArray[i]);
//			}
//			policyInfo=buff.toString();
			request.setPolicyInfo(xml);
			//默认
			request.setCheckCode("hyxnew");
			request.setFormCommit(true);
	        ((Stub) this.getBinding()).setTimeout(60000);
			//投保
	        reposne = this.getBinding().approval(request);
			SysMessage sysMessage = reposne.getSysMessage();
			if (sysMessage != null) {
				log.info("错误类型:" + sysMessage.getErrorType() + "\n");
				log.info("错误代码:" + sysMessage.getErrorCode() + "\n");
				log.info("错误信息:" + sysMessage.getErrorMsg() + "\n");
			}				
			//log.info("返回报文: \r" + reposne.getPolicyInfo()+ "\n");
			return reposne.getPolicyInfo();
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		} 
	}
	
}
