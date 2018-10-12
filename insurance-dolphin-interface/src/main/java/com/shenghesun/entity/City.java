package com.shenghesun.entity;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;
//城市起止信息
@Entity
@Data
public class City extends BaseEntity implements Serializable{
	
	private String depCity;
	
	private String arrCity;
	
	private String depCityCode;
	
	private String arrCityCode;
	
}
