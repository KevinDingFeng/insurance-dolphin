package com.shenghesun.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.shenghesun.common.exception.WebServiceException;
import com.shenghesun.model.webservice.Datas;
import com.shenghesun.model.webservice.Freightcpic;
import com.shenghesun.model.webservice.Header;
import com.shenghesun.model.webservice.Order;
import com.shenghesun.model.webservice.Salerinfos;


/**
 * WebService客户端通用类
 * 
 * @ClassName: WebServiceClientHelper
 * @Description: TODO
 * @author: yangzp
 * @date: 2018年9月28日 下午6:26:16
 */
public class WebServiceClientHelper {

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

	public static void main(String[] args) throws WebServiceException {
		Freightcpic frdightcpidc = new Freightcpic();
		Header header = new Header();
		header.setApplyid("WSI11201300133");
		header.setClassestype("1");
		Salerinfos salerinfos = new Salerinfos();
		salerinfos.setSalerinfocert("salerinfocert");
		salerinfos.setSalerinfoname("salerinfoname");
		Datas datas = new Datas();

		Order order = new Order();
		order.setApplyname("test1");
		order.setClasstype("11040200");
		order.setMark("123456");
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
		order.setAmount("1000");
		//费率
		order.setRate("0.01");
		//保费 国内10国际20
		order.setPremium("10");
		//航班日期起保日期yyyy-MM-dd 如：2013-12-06
		order.setEffectdate("2018-09-30");
		//航班日期起运日期yyyy-MM-dd 如：2013-12-06
		order.setSaildate("2018-09-30");
		//免赔条件国内货运险默认：本保单其他承保条件同协议
		//进出口货运险默认：other terms & conditions are equalent to the updated Open Policy.
		//order.setFranchise("");
		
		datas.setOrder(order);

		frdightcpidc.setDatas(datas);
		frdightcpidc.setHeader(header);
		frdightcpidc.setSalerinfos(salerinfos);

		String str = XmlUtils.toXml(frdightcpidc);
		System.out.println(str);

		String str1 = XStreamUtil.beanToXmlWithTag(frdightcpidc);
		System.out.println(str1);
		WebServiceClientHelper wc = new WebServiceClientHelper();
		String xml = str1;
		String result = wc.callonToxml("http://182.150.60.64/freight/zrxservices/FreightCommonService?wsdl", "approval", xml);
		System.out.println(result);
	}
}
