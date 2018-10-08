package com.shenghesun.model.webservice;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;

 /**
  * 
  * @ClassName: Header 
  * @Description: TODO
  * @author: yangzp
  * @date: 2018年9月28日 下午5:41:00  
  */
@Data
@XStreamAlias("HEADER")
public class Header {
	@XStreamAlias("ApplyId")
	private String applyid;
	@XStreamAlias("CLASSESTYPE")
	private String classestype;
}
