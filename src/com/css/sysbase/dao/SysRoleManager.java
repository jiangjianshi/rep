package com.css.sysbase.dao;

import org.springframework.stereotype.Repository;

import com.css.sysbase.entity.SysRole;
import com.css.utils.hibernate.BaseManager;

@Repository(value="sysRoleManager")
public class SysRoleManager extends BaseManager<SysRole> {
	public SysRoleManager(){
		super.setPersistentClass(SysRole.class);
	}
}
