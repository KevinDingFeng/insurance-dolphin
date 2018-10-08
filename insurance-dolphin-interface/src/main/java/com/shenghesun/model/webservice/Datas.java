package com.shenghesun.model.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

@Data
@XStreamAlias("DATAS")
public class Datas {
	@XStreamAlias("DATA")
	private Order order;
}
