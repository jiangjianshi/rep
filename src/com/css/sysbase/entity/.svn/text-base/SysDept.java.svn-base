package com.css.sysbase.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.css.utils.hibernate.BaseModel;

@Entity
@Table(name = "sys_dept")
public class SysDept{

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "dep_id")
	private String depId;
	
	@Column(name = "dep_name")
	private String depName;//部门名称
	
	@Column(name = "dep_order")
	private String depOrder;//排序
	
	@Column(name = "parent_dep")
	private String parentDep;//父级部门
	
	@Column(name = "dep_manager")
	private String depManager;//负责人

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepOrder() {
		return depOrder;
	}

	public void setDepOrder(String depOrder) {
		this.depOrder = depOrder;
	}

	public String getParentDep() {
		return parentDep;
	}

	public void setParentDep(String parentDep) {
		this.parentDep = parentDep;
	}

	public String getDepManager() {
		return depManager;
	}

	public void setDepManager(String depManager) {
		this.depManager = depManager;
	}
	
}
