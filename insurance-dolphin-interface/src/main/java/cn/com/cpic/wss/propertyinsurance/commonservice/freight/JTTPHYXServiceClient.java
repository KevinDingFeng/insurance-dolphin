package cn.com.cpic.wss.propertyinsurance.commonservice.freight;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.com.cpic.wss.propertyinsurance.commonservice.freight.IZrxCommonService;
public class JTTPHYXServiceClient {
	private static Logger log = Logger.getLogger(JTTPHYXServiceClient.class);
	
	public static JTTPHYXServiceClient client = new JTTPHYXServiceClient();
	
	public static JTTPHYXServiceClient getInstance(){
		return client;
	}
	
	private JTTPHYXServiceClient(){}
	
	//获取服务端方法
	public IZrxCommonService getServicePort(String servicePortAddress) throws Exception{
		FreightCommonServiceLocator serviceLLocaltor = new FreightCommonServiceLocator();
		java.net.URL endpoint = new URL(servicePortAddress);
		return serviceLLocaltor.getFreightCommonServicePort(endpoint);
	}
	
	/******
	 * 转换投保响应报文数据到MAP中
	 * @param responseXml
	 * @return
	 */
	public Map<String, String> getResponseValues(String responseXml){
		Map<String,String> map = new HashMap<String, String>();
		if (responseXml == null || "".equals(responseXml)) {
			return map;
		}
		Document doc = null;
		try {
			String policyXML = new String(responseXml.getBytes(),"GBK");
			doc = DocumentHelper.parseText(policyXML);
		} catch (Exception e) {
			log.error("", e);
		}
		Element rootEL = doc.getRootElement();
		Element result = rootEL.element("RESULT");
		Element unitCodeElement = result.element("UNITCODE");
		//分公司代码
		map.put("unitCode", unitCodeElement.getText());
		//投保单号
		Element applyNoElement = result.element("APPLYNO");
		map.put("applyNo", applyNoElement.getText());
		//保单号
		Element policyNoElement = result.element("POLICYNO");
		map.put("policyNo", policyNoElement.getText());
		//保单状态
		Element statusElement = result.element("STATUS");
		map.put("status", statusElement.getText());
		return map;
	}
	
	/***
	 * 转换查询响应报文数据到MAP中
	 * @param responseXML
	 * @return
	 */
	public Map<String,String> getQueryResponseValues(String responseXML){
		Map<String,String> map = new HashMap<String, String>();
		if (responseXML == null || "".equals(responseXML)) {
			return map;
		}
		Document doc = null;
		try {
			String policyXML = new String(responseXML.getBytes(),"GBK");
			doc = DocumentHelper.parseText(policyXML);
		} catch (Exception e) {
			log.error("", e);
		}
		Element rootEL = doc.getRootElement();
		Element pAuditWork = rootEL.element("P_AUDIT_WORK");
		Element pAuditWorkUnitCode = pAuditWork.element("P_AUDIT_WORK__UNITCODE");
		Element pAuditWorkApplyNo = pAuditWork.element("P_AUDIT_WORK__APPLYNO");
		Element pAuditWorkPolicyNo = pAuditWork.element("P_AUDIT_WORK__POLICYNO");
		map.put("pAuditWorkUnitCode", pAuditWorkUnitCode.getText());
		map.put("pAuditWorkApplyNo", pAuditWorkApplyNo.getText());
		map.put("pAuditWorkPolicyNo", pAuditWorkPolicyNo.getText());
		return map;
	}
}
