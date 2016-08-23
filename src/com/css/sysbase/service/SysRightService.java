package com.css.sysbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.css.sysbase.entity.SysRight;

public interface SysRightService {

	/**
	 * 保存
	 * @param request
	 * @throws Exception
	 */
	public void save(HttpServletRequest request) throws Exception;
	
	/**
	 * 更新
	 * @param request
	 * @throws Exception
	 */
	public void update(HttpServletRequest request) throws Exception;
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public int del(String id) throws Exception;
	
	public SysRight getById(String id);
	
	public void saveRightPointAddEdit(HttpServletRequest request) throws Exception;

}
