package com.shenghesun.model.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("SALERINFOS")
public class Salerinfos {
	@XStreamAlias("SALERINFONAME")
	private String salerinfoname;
	@XStreamAlias("SALERINFOCERT")
	private String salerinfocert;
}
