package com.css.sysbase.dao;

import org.springframework.stereotype.Repository;

import com.css.sysbase.entity.SysRight;
import com.css.utils.hibernate.BaseManager;

@Repository(value="sysRightManager")
public class SysRightManager extends BaseManager<SysRight> {
	public SysRightManager(){
		super.setPersistentClass(SysRight.class);
	}
}
