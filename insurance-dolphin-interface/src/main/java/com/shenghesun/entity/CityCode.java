package com.shenghesun.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

//城市编码
@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
public class CityCode extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 2443277338932417258L;
	@Column
	private String cityName;
	@Column
	private String cityCode;
	@Column
	private String cityType;
}
