package com.css.sysbase.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.css.sysbase.entity.SysUser;

public interface UserService {

	
	public SysUser getById(String userId);
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
	
	public void saveUserRole(String userId,String roleCode_str) throws Exception;
	
	/**
     * 根据参数params获得数据库中某张表的数据
     * @param params
     * @param claz
     * @return
     */
    public List<SysUser> getEntityByProperties(Map params);

}
