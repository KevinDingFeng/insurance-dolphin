package com.shenghesun.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import lombok.Data;
import lombok.EqualsAndHashCode;

//支付相关信息
@Entity
@Table
@Data
@EqualsAndHashCode(exclude = {"mark"},callSuper = true)
@XStreamAlias("DATA")
public class PayMessage extends BaseEntity implements Serializable{

	private static final long serialVersionUID = -4353307437903537087L;
	
	@XStreamOmitField
	@Column(length=50)
	private String openid;
	
	@XStreamOmitField
	@Column(length=100)
	private String orderNo;
	
	@XStreamOmitField
	@Column(length=1)
	private String payState="0";
	
	@XStreamAlias("APPLYNAME")
	@Column(length=200)
	private String applyname;
	
	@XStreamAlias("APPLYCARTTYPE")
	@XStreamOmitField
	@Column(length=50)
	private String applycarttype="1170010001";
	
	@XStreamAlias("APPLYCARDCODE")
	@Column(length=50)
	private String applycardcode;
	
	@XStreamAlias("INSURANTNAME")
	@Column(length=200)
	private String insurantname;
	
	@XStreamAlias("INSURANCECARDTYPE")
	@XStreamOmitField
	@Column(length=50)
	private String insurancecardtype="1170010001";
	
	@XStreamAlias("INSURANCECARDCODE")
	@Column(length=50)
	private String insurancecardcode;
	
	@XStreamAlias("INSURANTTEL")
	@Column
	private String insuranttel;
	
	@XStreamAlias("INSURANTEMAIL")
	@Column
	private String insurantemail;
	
	@XStreamAlias("CLASSTYPE")
	@Column(length=8)
	private String classtype;
	
	/**
	 * 行李号不入库，接口用
	 */
	@XStreamAlias("MARK")
	@Transient
	private String markNo;
	
	@XStreamAlias("QUANTITY")
	@Column(length=500)
	private String quantity="1";
	
	@XStreamAlias("ITEM")
	@Column(length=500)
	private String item="行李";
	
	@XStreamAlias("PACKCODE")
	@Column(length=2)
	private String packcode="01";
	
	@XStreamAlias("ITEMCODE")
	@Column(length=4)
	private String itemcode="0309";
	
	@XStreamAlias("FLIGHTAREACODE")
	@Column(length=10)
	private String flightareacode;
	
	@XStreamAlias("KIND")
	@Column(length=2)
	private String kind="5";
	
	@XStreamAlias("KINDNAME")
	@Column(length=30)
	private String kindname="航空";
	
	@XStreamAlias("VOYNO")
	@Column(length=30)
	private String voyno;
	
	@XStreamAlias("STARTPORT")
	@Column(length=60)
	private String startport;
	
	@XStreamAlias("TRANSPORT1")
	@Column(length=60)
	private String transport1;
	
	@XStreamAlias("ENDPORT")
	@Column(length=60)
	private String endport;
	
	@XStreamAlias("CLAIMAGENT")
	@Column(length=200)
	private String claimagent;
	
	@XStreamAlias("MAINITEMCODE")
	@Column(length=12)
	private String mainitemcode="HK";
	
	@XStreamAlias("ITEMCONTENT")
	@Column(columnDefinition = "BLOB")
	private String itemcontent="国内航空货物运输保险条款";
	
	@XStreamAlias("ITEMADDCODE")
	@Column(length=12)
	private String itemaddcode;
	
	@XStreamAlias("ITEMADDCONTENT")
	@Column(columnDefinition = "BLOB")
	private String itemaddcontent;
	
	@XStreamAlias("CURRENCYCODE")
	@Column(length=2)
	private String currencycode="01";
	
	@XStreamAlias("PRICECOND")
	@Column(length=1)
	private String pricecond="1";
	
	@XStreamAlias("INVAMOUNT")
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer invamount;
	
	@XStreamAlias("INCRATE")
	@Column
	private String incrate="0";
	
	@XStreamAlias("AMOUNT")
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer amount;
	
	@XStreamAlias("RATE")
	@Column
	private float rate=0.01f;
	
	@XStreamAlias("PREMIUM")
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer premium;
	
	/**
	 * 保单总金额
	 */
	@Column(columnDefinition="DECIMAL(16,2)")
	private Integer orderAmount;
	
	@XStreamAlias("FCURRENCYCODE")
	@Column
	private String fcurrencycode="01";
	
	@XStreamAlias("CLAIMCURRENCYCODE")
	@Column
	private String claimcurrencycode="01";
	
	@XStreamAlias("CLAIMPAYPLACE")
	@Column
	private String claimpayplace;
	
	@XStreamAlias("EFFECTDATE")
	@Column(length=10)
	private String effectdate;
	
	@XStreamAlias("SAILDATE")
	@Column(length=10)
	private String saildate;
	
	@XStreamAlias("FRANCHISE")
	@Column(length=500)
	private String franchise="无";
	
	@XStreamAlias("SPECIALIZE")
	@Column(length=500)
	private String specialize;
	
	@XStreamAlias("COMMENTS")
	@Column(length=500)
	private String comments;
	
	@XStreamAlias("USERNO")
	@Column(length=50)
	private String userno;
	
	@XStreamAlias("INVHEAD")
	@Column(length=200)
	private String invhead;
	
	@XStreamAlias("IFBILL")
	@Column(length=1)
	private String ifbill;
	
	@XStreamAlias("VBILLADDRESS")
	@Column(length=500)
	private String vbilladdress;
	
	@XStreamAlias("PRINTTYPE")
	@Column(length=1)
	private String printtype;
	
	@XStreamAlias("HANDLERUNITCODE")
	@Column(length=7)
	private String handlerunitcode;
	
	@XStreamAlias("HANDLERCODE")
	@Column
	private String handlercode;
	
	@XStreamOmitField
	@Column(length=1)
	private String classestype;
	
	/**
	 * 保单状态 0：下单成功；1：支付成功
	 * 默认为 0
	 */
	@XStreamOmitField
	@Column
	private Integer payStatus = 0; 

	//@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@XStreamOmitField
	@OneToMany(mappedBy = "payMessage",cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Mark> mark;
	
	@XStreamOmitField
	private String markString;
}
