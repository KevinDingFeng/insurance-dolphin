package com.shenghesun.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("RESULT")
public class WxPayResult extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 6208536091356660512L;

	@Column(length=50)
	private String appid;
	
	@Column(length=50)
	private String attach;
	
	@Column(length=50)
	private String bank_type;
	
	@Column(length=50)
	private String cash_fee;
	
	@Column(length=50)
	private String fee_type;
	
	@Column(length=50)
	private String is_subscribe;
	
	@Column(length=50)
	private String mch_id;
	
	@Column(length=50)
	private String nonce_str;
	
	@Column(length=50)
	private String openid;
	
	@Column(length=50)
	private String out_trade_no;
	
	@Column(length=50)
	private String result_code;
	
	@Column(length=50)
	private String return_code;
	
	@Column(length=50)
	private String sign;
	
	@Column(length=50)
	private String sub_mch_id;
	
	@Column(length=50)
	private String time_end;
	
	@Column(length=50)
	private String total_fee;
	
	@Column(length=50)
	private String coupon_count;
	
	@Column(length=50)
	private String trade_type;
	
	@Column(length=50)
	private String transaction_id;
	
	
}
