package com.shenghesun.model.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("FREIGHTCPIC")
public class Freightcpic {
	@XStreamAlias("HEADER")
	private Header header;
	@XStreamAlias("DATAS")
	private Datas datas;
	@XStreamAlias("SALERINFOS")
	private Salerinfos salerinfos;
}
