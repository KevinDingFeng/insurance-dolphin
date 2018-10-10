package com.shenghesun.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

//支付相关信息
@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class PayMessage extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -4353307437903537087L;
	
	@Column(length=50)
	private String openid;
	@Column(length=100)
	private String orderNo;
	@Column(length=1)
	private String payState="0";
	@Column(length=200)
	private String applyname;
	@Column(length=50)
	private String applycarttype="1170010001";
	@Column(length=50)
	private String applycardcode;
	@Column(length=200)
	private String insurantname;
	@Column(length=50)
	private String insurancecardtype="1170010001";
	@Column(length=50)
	private String insurancecardcode;
	@Column
	private String insuranttel;
	@Column
	private String insurantemail;
	@Column(length=8)
	private String classtype;
	@Column(length=500)
	private String mark;
	@Column(length=500)
	private String quantity="1";
	@Column(length=500)
	private String item="行李";
	@Column(length=2)
	private String packcode="01";
	@Column(length=4)
	private String itemcode="0309";
	@Column(length=10)
	private String flightareacode;
	@Column(length=2)
	private String kind="5";
	@Column(length=30)
	private String kindname="航空";
	@Column(length=30)
	private String voyno;
	@Column(length=60)
	private String startport;
	@Column(length=60)
	private String transport1;
	@Column(length=60)
	private String endport;
	@Column(length=200)
	private String claimagent;
	@Column(length=12)
	private String mainitemcode="HK";
	@Column(columnDefinition = "BLOB")
	private String itemcontent="国内航空货物运输保险条款";
	@Column(length=12)
	private String itemaddcode;
	@Column(columnDefinition = "BLOB")
	private String itemaddcontent;
	@Column(length=2)
	private String currencycode="01";
	@Column(length=1)
	private String pricecond="1";
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer invamount;
	@Column
	private String incrate="0";
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer amount;
	@Column
	private float rate=0.01f;
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer premium;
	@Column
	private String fcurrencycode="01";
	@Column
	private String claimcurrencycode="01";
	@Column
	private String claimpayplace;
	@Column(length=10)
	private String effectdate;
	@Column(length=10)
	private String saildate;
	@Column(length=500)
	private String franchise="无";
	@Column(length=500)
	private String specialize;
	@Column(length=500)
	private String comments;
	@Column(length=50)
	private String userno;
	@Column(length=200)
	private String invhead;
	@Column(length=1)
	private String ifbill;
	@Column(length=500)
	private String vbilladdress;
	@Column(length=1)
	private String printtype;
	@Column(length=7)
	private String handlerunitcode;
	@Column
	private String handlercode;
	@Column(length=1)
	private String classestype;

}
