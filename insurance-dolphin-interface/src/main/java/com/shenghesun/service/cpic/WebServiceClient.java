package com.shenghesun.service.cpic;

import java.util.Iterator;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shenghesun.entity.PayMessage;
import com.shenghesun.entity.cpic.Approvl;
import com.shenghesun.util.cpic.XmlUtils;

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

	@Autowired
	private ApprovlService approvlService;
	

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
	 * 
	 * 注解@Async(“asyncServiceExecutor”)，
	 * asyncServiceExecutor是前面ExecutorConfig.java中的方法名，
	 * 表明executeAsync方法进入的线程池是asyncServiceExecutor方法创建的
	 **/ 
	//@Async("asyncServiceExecutor")
	public boolean approvl(String xml, PayMessage payMessage) {
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
				log.error("错误类型:" + sysMessage.getErrorType() + "\n");
				log.error("错误代码:" + sysMessage.getErrorCode() + "\n");
				log.error("错误信息:" + sysMessage.getErrorMsg() + "\n");
			}				
			//log.info("返回报文: \r" + reposne.getPolicyInfo()+ "\n");

			String result = reposne.getPolicyInfo();
			Approvl approvl =  xml2Approvl(result,payMessage);
			if(approvl != null) {

				Approvl approvlDB = approvlService.findByApplyId(approvl.getApplyId());
				if(approvlDB != null) {//已有则修改
					BeanUtils.copyProperties(approvl, approvlDB);
					approvlService.save(approvlDB);
				}else {//新增
					approvlService.save(approvl);
				}

				String status = approvl.getStatus();
				/**
				 * 保单状态
				 * 7  待审核-----投保单录入成功，提交人工审核
				   36 待签发-----投保单录入成功，自动核保通过 （非在线支付情况，状态 10 --保单生效）
				   19 提交失败----投保单录入成功，系统提交核保失败，需联系技术人员处理
				 * 
				 */
				//发送短信状态
//				String smsStatus = null;
				if(StringUtils.isNotEmpty(status)) {
					if("10".equals(status)) {
						return true;
					}else if("19".equals(status)) {
						return false;
					}
				}
			}
			return false;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		} 
	}

	/**
	 * 货运险承保接口应答报文转对象
	 * @Title: xml2Approvl 
	 * @Description: TODO 
	 * @param xml  void 
	 * @author yangzp
	 * @date 2018年10月10日下午5:18:00
	 **/ 
	private Approvl xml2Approvl(String xml, PayMessage payMessage) {
		if(StringUtils.isNotEmpty(xml)) {
			Approvl approvl = new Approvl();
			approvl.setOrderNo(payMessage.getOrderNo());
			Document resultDo;
			try {
				resultDo = XmlUtils.parseWithSAX(xml,"GBK");
				Element rootElt = resultDo.getRootElement(); // 获取根节点

				Iterator<?> iter = rootElt.elementIterator("CONFIG"); // 获取根节点下的子节点CONFIG
				while (iter.hasNext()) {
					Element recordEle = (Element) iter.next();
					String ApplyId = recordEle.elementTextTrim("ApplyId"); // 拿到CONFIG节点下的子节点ApplyId值
					approvl.setApplyId(ApplyId);
					String type = recordEle.elementTextTrim("TYPE"); // 拿到CONFIG节点下的子节点TYPE值
					approvl.setType(type);

					String worktype = recordEle.elementTextTrim("WORKTYPE"); // 拿到CONFIG节点下的子节点WORKTYPE值
					approvl.setWorkType(worktype);
				}

				Iterator<?> iterResult = rootElt.elementIterator("RESULT"); // 获取根节点下的子节点RESULT
				while (iterResult.hasNext()) {
					Element recordEle = (Element) iterResult.next();
					String unitcode = recordEle.elementTextTrim("UNITCODE"); // 拿到RESULT节点下的子节点UNITCODE值
					approvl.setUnitCode(unitcode);

					String applyno = recordEle.elementTextTrim("APPLYNO"); // 拿到RESULT节点下的子节点APPLYNO值
					approvl.setApplyNo(applyno);

					String policyno = recordEle.elementTextTrim("POLICYNO"); // 拿到RESULT节点下的子节点POLICYNO值
					approvl.setPolicyNo(policyno);

					String status = recordEle.elementTextTrim("STATUS"); // 拿到RESULT节点下的子节点STATUS值
					approvl.setStatus(status);

					String comments = recordEle.elementTextTrim("COMMENTS"); // 拿到RESULT节点下的子节点COMMENTS值
					approvl.setComments(comments);

					String statusEpolicy = recordEle.elementTextTrim("STATUS_EPOLICY"); // 拿到RESULT节点下的子节点STATUS_EPOLICY值
					approvl.setStatusEpolicy(statusEpolicy);

					return approvl;
				}
			} catch (DocumentException e) {
				log.error("错误信息:" + e.getStackTrace());
				return null;
			}

		}
		return null;
	}

}
