package com.css.sysbase.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.css.utils.hibernate.BaseModel;

@Entity
@Table(name = "sys_right")
public class SysRight implements BaseModel{
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "pk_id", length = 50)
	private String pkId;
	
	@Column(name = "right_name", length = 50)
	private String rightName;
	
	@Column(name = "right_code", length = 50)
	private String rightCode;
	
	@Column(name = "p_code", length = 50)
	private String pCode;
	
	@Column(name = "right_url", length = 50)
	private String rightUrl;
	
	@Column(name = "right_type", length = 50)
	private String rightType;
	
	@Column(name = "order_code", length = 50)
	private String orderCode;
	
	@Override
	public String getPkId() {
		return pkId;
	}
	
	@Override
	public void setPkId(String pkId) {
		this.pkId=pkId;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public String getRightCode() {
		return rightCode;
	}

	public void setRightCode(String rightCode) {
		this.rightCode = rightCode;
	}

	public String getpCode() {
		return pCode;
	}

	public void setpCode(String pCode) {
		this.pCode = pCode;
	}

	public String getRightUrl() {
		return rightUrl;
	}

	public void setRightUrl(String rightUrl) {
		this.rightUrl = rightUrl;
	}

	public String getRightType() {
		return rightType;
	}

	public void setRightType(String rightType) {
		this.rightType = rightType;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

}
