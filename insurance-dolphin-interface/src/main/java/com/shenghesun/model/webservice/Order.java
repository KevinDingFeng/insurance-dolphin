package com.shenghesun.model.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

/**
  * 保险订单 货运险承保接口请求报文
  * @ClassName: Order 
  * @Description: TODO
  * @author: yangzp
  * @date: 2018年9月28日 下午4:46:44  
  */
@Data
@XStreamAlias("DATA")
public class Order {
	//header
	@XStreamAlias("APPLYNAME")
	private String applyname;
	@XStreamAlias("APPLYCARTTYPE")
	private String applycarttype;
	@XStreamAlias("APPLYCARDCODE")
	private String applycardcode;
	@XStreamAlias("INSURANTNAME")
	private String insurantname;
	@XStreamAlias("INSURANCECARDTYPE")
	private String insurancecardtype;
	@XStreamAlias("INSURANCECARDCODE")
	private String insurancecardcode;
	@XStreamAlias("INSURANTTEL")
	private String insuranttel;
	@XStreamAlias("INSURANTEMAIL")
	private String insurantemail;
	@XStreamAlias("CLASSTYPE")
	private String classtype;
	@XStreamAlias("MARK")
	private String mark;
	@XStreamAlias("QUANTITY")
	private String quantity;
	@XStreamAlias("ITEM")
	private String item;
	@XStreamAlias("PACKCODE")
	private String packcode;
	@XStreamAlias("ITEMCODE")
	private String itemcode;
	@XStreamAlias("FLIGHTAREACODE")
	private String flightareacode;
	@XStreamAlias("KIND")
	private String kind;
	@XStreamAlias("KINDNAME")
	private String kindname;
	@XStreamAlias("VOYNO")
	private String voyno;
	@XStreamAlias("STARTPORT")
	private String startport;
	@XStreamAlias("TRANSPORT1")
	private String transport1;
	@XStreamAlias("ENDPORT")
	private String endport;
	@XStreamAlias("CLAIMAGENT")
	private String claimagent;
	@XStreamAlias("MAINITEMCODE")
	private String mainitemcode;
	@XStreamAlias("ITEMCONTENT")
	private String itemcontent;
	@XStreamAlias("ITEMADDCODE")
	private String itemaddcode;
	@XStreamAlias("ITEMADDCONTENT")
	private String itemaddcontent;
	@XStreamAlias("CURRENCYCODE")
	private String currencycode;
	@XStreamAlias("PRICECOND")
	private String pricecond;
	@XStreamAlias("INVAMOUNT")
	private String invamount;
	@XStreamAlias("INCRATE")
	private String incrate;
	@XStreamAlias("AMOUNT")
	private String amount;
	@XStreamAlias("RATE")
	private String rate;
	@XStreamAlias("PREMIUM")
	private String premium;
	@XStreamAlias("FCURRENCYCODE")
	private String fcurrencycode;
	@XStreamAlias("CLAIMCURRENCYCODE")
	private String claimcurrencycode;
	@XStreamAlias("CLAIMPAYPLACE")
	private String claimpayplace;
	@XStreamAlias("EFFECTDATE")
	private String effectdate;
	@XStreamAlias("SAILDATE")
	private String saildate;
	@XStreamAlias("FRANCHISE")
	private String franchise;
	@XStreamAlias("SPECIALIZE")
	private String specialize;
	@XStreamAlias("COMMENTS")
	private String comments;
	@XStreamAlias("USERNO")
	private String userno;
	@XStreamAlias("INVHEAD")
	private String invhead;
	@XStreamAlias("IFBILL")
	private String ifbill;
	@XStreamAlias("VBILLADDRESS")
	private String vbilladdress;
	@XStreamAlias("PRINTTYPE")
	private String printtype;
	@XStreamAlias("HANDLERUNITCODE")
	private String handlerunitcode;
	@XStreamAlias("HANDLERCODE")
	private String handlercode;
	

}
