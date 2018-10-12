package com.shenghesun.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table
@Data
@EqualsAndHashCode(callSuper = true)
@XStreamAlias("DATA")
public class Mark extends BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7765040275551790517L;

	@Column
	private String mark;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name = "order_No")
	@NotFound(action=NotFoundAction.IGNORE)
	private PayMessage payMessage;
	
}
