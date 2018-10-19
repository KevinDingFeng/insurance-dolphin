package com.shenghesun.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 4020096204163058662L;
	public static final int invalid_login_code = 101;// token 过期
	public static final int user_check_failed_code = 102;
	public static final long ex = 60 * 60 * 24 * 30;//token 过期时间 单位：秒
	public static final long redis_ex = 60 * 30;//redis 过期时间 单位：秒
	public static final String inner_price = "15";//国内行李保险价格
	public static final String inner_type = "1";//国内类型
	public static final String foreign_price = "28";//国外行李保险价格
	public static final String foreign_type = "2";//国外类型
	public static final String error_price = "00";//错误代码，用于前端区分
	public static final String error_price1 = "01";//错误代码，用于前端区分
	
	protected long serverDateTime = System.currentTimeMillis();
	//接口是否调用成功
	protected boolean success = true;
	protected int errorCode = 0;
	protected String message = "操作成功";
	protected String extraMessage;
	protected Object data;
	
}
