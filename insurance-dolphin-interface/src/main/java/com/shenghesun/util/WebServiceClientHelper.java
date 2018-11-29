package com.shenghesun.util;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Stub;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.log4j.Logger;

import com.shenghesun.common.exception.WebServiceException;
import com.shenghesun.entity.PayMessage;
import com.shenghesun.model.webservice.Datas;
import com.shenghesun.model.webservice.Freightcpic;
import com.shenghesun.model.webservice.Header;
import com.shenghesun.model.webservice.Salerinfos;
import com.shenghesun.service.cpic.WebServiceClient;

import cn.com.cpic.wss.propertyinsurance.commonservice.freight.FreightCommonServiceLocator;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.IZrxCommonService;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalProduct;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalRequest;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.ApprovalResponse;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.LoginUser;
import cn.com.cpic.wss.propertyinsurance.commonservice.freight.types.SysMessage;


/**
 * WebService客户端通用类
 * 
 * @ClassName: WebServiceClientHelper
 * @Description: TODO
 * @author: yangzp
 * @date: 2018年9月28日 下午6:26:16
 */
public class WebServiceClientHelper {
	private static final Logger log = Logger.getLogger(WebServiceClient.class);
	/**
	 * 调用远程的webservice并返回数据
	 * 
	 * @Title: callonToxml
	 * @Description: TODO
	 * @param wsUrl
	 *            ws地址
	 * @param method
	 *            调用的ws方法名
	 * @param xml
	 *            参数
	 * @return
	 * @throws WebServiceException
	 *             String
	 * @author yangzp
	 * @date 2018年9月28日下午6:26:32
	 **/
	public String callonToxml(String wsUrl, String method, String xml) throws WebServiceException {
		return callService(wsUrl, method, xml);
	}

	public String callService(String wsUrl, String method, Object... arg) throws WebServiceException {
		Object[] res = null;
		Client client = this.createDynamicClient(wsUrl, null);
		try {
			res = client.invoke(method, arg);

		} catch (Exception e) {
			e.printStackTrace();
			throw new WebServiceException("027:服务方调用异常");
		}

		return (String) res[0];
	}

	private synchronized Client createDynamicClient(String wsUrl, Client client) throws WebServiceException {
		try {
			JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
			client = dcf.createClient(wsUrl);
			// 需要密码的情况需要加上用户名和密码
			//client.getInInterceptors().add(new LoggingInInterceptor());
	        client.getOutInterceptors().add(new ClientLoginInterceptor("EBTEST","Ecargo1234"));
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new WebServiceException("025:服务方无法连接");
		}
		return client;
	}
	
	public IZrxCommonService getBinding() throws ServiceException {
		FreightCommonServiceLocator locator = new FreightCommonServiceLocator();
		String url = "http://182.150.60.64/freight/zrxservices/FreightCommonService?wsdl";
		locator.setFreightCommonServicePortEndpointAddress(url);
		return locator.getFreightCommonServicePort();
	}
	
	public void basic() {
		ApprovalRequest request = new ApprovalRequest();

		//用户信息
		LoginUser userInfo = new LoginUser();
		userInfo.setUserName("EBTEST");
		userInfo.setPassword("Ecargo1234");
		//产品信息
		ApprovalProduct productInfo = new ApprovalProduct();	
		//航空险种
		productInfo.setClassesCode("12040200");
		ApprovalResponse reposne=null;
		try {
			//用户信息
			request.setUserInfo(userInfo);
			request.setProductInfo(productInfo);
			//报文信息
			request.setPolicyInfo("CLIAMAGENTINFOV2013");
			//默认
			request.setCheckCode("CLIAMAGENTINFOV2013");
			request.setFormCommit(true);
			((Stub) this.getBinding()).setTimeout(60000);
			//投保
			reposne = this.getBinding().doQueryBasicData(request);
			SysMessage sysMessage = reposne.getSysMessage();
			if (sysMessage != null) {
				log.error("错误类型:" + sysMessage.getErrorType() + "\n");
				log.error("错误代码:" + sysMessage.getErrorCode() + "\n");
				log.error("错误信息:" + sysMessage.getErrorMsg() + "\n");
			}				
			//log.info("返回报文: \r" + reposne.getPolicyInfo()+ "\n");

			String result = reposne.getPolicyInfo();
			System.out.println(result);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	private  void testApproval(String xml) {

		ApprovalRequest request = new ApprovalRequest();

		//用户信息
		LoginUser userInfo = new LoginUser();
		userInfo.setUserName("EBTEST");
		userInfo.setPassword("Ecargo1234");
		//产品信息
		ApprovalProduct productInfo = new ApprovalProduct();	
		//航空险种
		productInfo.setClassesCode("12040200");
		ApprovalResponse reposne=null;
		try {
			//用户信息
			request.setUserInfo(userInfo);
			request.setProductInfo(productInfo);
			//报文信息
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
			System.out.println(result);
		} catch (Exception e) {
			log.error(e.getMessage());
		} 
	}
	
	public static void main(String[] args) {
		Freightcpic frdightcpidc = new Freightcpic();
		Header header = new Header();
		header.setApplyid(StringGenerateUtils.generateId());
		header.setClassestype("1");
		Salerinfos salerinfos = new Salerinfos();
		salerinfos.setSalerinfocert("salerinfocert");
		salerinfos.setSalerinfoname("salerinfoname");
		Datas datas = new Datas();

		PayMessage order = new PayMessage();
		order.setApplyname("test1");
		order.setClasstype("11040200");
		order.setMarkNo("123456");
		//待定
		order.setQuantity("1");
		order.setItem("行李");
		order.setPackcode("01");
		order.setItemcode("0309");
		//待定(不问)
		order.setFlightareacode("11040400");
		order.setKind("5");
		order.setKindname("飞机");
		order.setStartport("北京");
		order.setEndport("上海");
		//待定
		order.setMainitemcode("C090019M");
		//待定
		order.setItemcontent("中国太平洋财产保险股份有限公司国内航空旅客行李保险条款");
		order.setCurrencycode("01");
		order.setPricecond("1");
		order.setAmount(1000);
		//费率
		order.setRate(0.01F);
		//保费 国内10国际20
		order.setPremium(10);
		//航班日期起保日期yyyy-MM-dd 如：2013-12-06
		order.setEffectdate("2018-09-30");
		//航班日期起运日期yyyy-MM-dd 如：2013-12-06
		order.setSaildate("2018-09-30");
		//免赔条件国内货运险默认：本保单其他承保条件同协议
		//进出口货运险默认：other terms & conditions are equalent to the updated Open Policy.
		//order.setFranchise("");
		
		datas.setPayMessage(order);

		frdightcpidc.setDatas(datas);
		frdightcpidc.setHeader(header);
		frdightcpidc.setSalerinfos(salerinfos);
		
		String xml = XStreamUtil.beanToXmlWithTag(frdightcpidc);
		System.out.println("xml="+xml);
		xml = "<?xml version='1.0' encoding='UTF-8'?><FREIGHTCPIC> <HEADER> <ApplyId>OCK09950860812</ApplyId> <CLASSESTYPE>2</CLASSESTYPE> </HEADER> <DATAS> <DATA> <version>0</version> <APPLYNAME>123</APPLYNAME> <INSURANTNAME>1423</INSURANTNAME> <RATE>0.03</RATE> <STARTPORT>武汉</STARTPORT> <TRANSPORT1></TRANSPORT1> <ENDPORT>苏州</ENDPORT> <KIND>1</KIND> <SAILDATE>2018-11-26</SAILDATE> <ITEM>矿产类-矿砂</ITEM> <MARK>123456</MARK> <QUANTITY>1</QUANTITY> <PACKCODE>01</PACKCODE> <ITEMCODE>0505</ITEMCODE> <KINDNAME>船舶</KINDNAME> <MAINITEMCODE>01ALL</MAINITEMCODE> <ITEMCONTENT>Covering All Risks as per Ocean Marine Cargo Clauses of China Pacific Property Insurance Co.,Ltd.</ITEMCONTENT> <CURRENCYCODE>02</CURRENCYCODE> <FLIGHTAREACODE>EOIT</FLIGHTAREACODE> <CLAIMAGENT>498498906</CLAIMAGENT> <PRICECOND>1</PRICECOND> <CLAIMCURRENCYCODE>01</CLAIMCURRENCYCODE> <CLAIMPAYPLACE>意大利</CLAIMPAYPLACE> <PREMIUM>9.899999618530273</PREMIUM> <INCRATE>0.1</INCRATE> <FCURRENCYCODE>01</FCURRENCYCODE> <EFFECTDATE>2018-11-26</EFFECTDATE> <FRANCHISE>other terms conditions are equalent to the updated Open Policy.</FRANCHISE> <AMOUNT>33000</AMOUNT> <INVAMOUNT>33000</INVAMOUNT> <CLASSESTYPE>2</CLASSESTYPE> <CLASSTYPE>12040200</CLASSTYPE> </DATA> </DATAS> </FREIGHTCPIC>";
		WebServiceClientHelper wsc = new WebServiceClientHelper();
		//wsc.testApproval(xml);
		wsc.basic();
	}

//	public static void main(String[] args) throws WebServiceException {
//		Freightcpic frdightcpidc = new Freightcpic();
//		Header header = new Header();
//		header.setApplyid("WSI11201300133");
//		header.setClassestype("1");
//		Salerinfos salerinfos = new Salerinfos();
//		salerinfos.setSalerinfocert("salerinfocert");
//		salerinfos.setSalerinfoname("salerinfoname");
//		Datas datas = new Datas();
//
//		Order order = new Order();
//		order.setApplyname("test1");
//		order.setClasstype("11040200");
//		order.setMark("123456");
//		//待定
//		order.setQuantity("1");
//		order.setItem("行李");
//		order.setPackcode("01");
//		order.setItemcode("0309");
//		//待定(不问)
//		order.setFlightareacode("11040400");
//		order.setKind("5");
//		order.setKindname("飞机");
//		order.setStartport("北京");
//		order.setEndport("上海");
//		//待定
//		order.setMainitemcode("C090019M");
//		//待定
//		order.setItemcontent("中国太平洋财产保险股份有限公司国内航空旅客行李保险条款");
//		order.setCurrencycode("01");
//		order.setPricecond("1");
//		order.setAmount("1000");
//		//费率
//		order.setRate("0.01");
//		//保费 国内10国际20
//		order.setPremium("10");
//		//航班日期起保日期yyyy-MM-dd 如：2013-12-06
//		order.setEffectdate("2018-09-30");
//		//航班日期起运日期yyyy-MM-dd 如：2013-12-06
//		order.setSaildate("2018-09-30");
//		//免赔条件国内货运险默认：本保单其他承保条件同协议
//		//进出口货运险默认：other terms & conditions are equalent to the updated Open Policy.
//		//order.setFranchise("");
//		
//		//datas.setOrder(order);
//
//		frdightcpidc.setDatas(datas);
//		frdightcpidc.setHeader(header);
//		frdightcpidc.setSalerinfos(salerinfos);
//
//		String str = XmlUtils.toXml(frdightcpidc);
//		System.out.println(str);
//
//		String str1 = XStreamUtil.beanToXmlWithTag(frdightcpidc);
//		System.out.println(str1);
//		WebServiceClientHelper wc = new WebServiceClientHelper();
//		String xml = str1;
//		String result = wc.callonToxml("http://182.150.60.64/freight/zrxservices/FreightCommonService?wsdl", "approval", xml);
//		System.out.println(result);
//	}
}
