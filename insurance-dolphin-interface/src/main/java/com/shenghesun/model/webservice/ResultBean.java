package com.shenghesun.model.webservice;

import com.shenghesun.util.XStreamUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("RESULT")
public class ResultBean {
	@XStreamAlias("P_AUDIT_WORK__WORKUNITCODE")
	private String unitCode;
	@XStreamAlias("POLICYNO")
	private String policyNo;

	
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + 
				"  <RESULT>\r\n" + 
				"	<!--分公司-->\r\n" + 
				"    <P_AUDIT_WORK__WORKUNITCODE>123</P_AUDIT_WORK__WORKUNITCODE>\r\n" + 
				"	<!--投保单号-->\r\n" + 
				"    <APPLYNO></APPLYNO>\r\n" + 
				"	<!--保单号-->\r\n" + 
				"    <POLICYNO>fgfd</POLICYNO>\r\n" + 
				"	<!--保单状态 7待审核 10已生效  36待签发 19提交失败 -->\r\n" + 
				"    <STATUS>7</STATUS>\r\n" + 
				"    <COMMENTS>提交投保单到CIBS成功!投保单号为</COMMENTS>\r\n" + 
				"	<!--电子保单状态  0创建失败 1创建成功 2未创建 -->\r\n" + 
				"	<STATUS_EPOLICY></STATUS_EPOLICY>\r\n" + 
				"	<!--电子保单内容-->\r\n" + 
				"	<FILE_EPOLICY></FILE_EPOLICY>\r\n" + 
				"  </RESULT>\r\n";
		ResultBean result = XStreamUtil.xmlToBean(xml, ResultBean.class);
		System.out.println(result);
		System.out.println();
	}
}
