package com.shenghesun.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("DATA")
public class Mark extends BaseEntity implements Serializable{

	@Column
	private String mark;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name = "order_No")
	private PayMessage payMessage;
	
}
