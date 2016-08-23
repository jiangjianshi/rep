package com.css.sysbase.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RoleService {

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
	public void del(String id) throws Exception;
	
	public void saveRoleRight(String roleCode,String rightCodes) throws Exception;
	
	
	public boolean isHaveRightByUserId(String userId,String rightCode);

}
