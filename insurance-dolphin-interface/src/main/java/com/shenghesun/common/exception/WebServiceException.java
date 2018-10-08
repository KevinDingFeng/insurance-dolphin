package com.shenghesun.common.exception;

public class WebServiceException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7313026390344919893L;
	
	public WebServiceException(Exception ex) { 
		super(ex);
	}
	
	public WebServiceException(String msg){
		super(msg);
	}
}
