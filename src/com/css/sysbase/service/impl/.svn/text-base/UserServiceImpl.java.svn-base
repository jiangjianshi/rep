package com.css.sysbase.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.dao.SysUserManager;
import com.css.sysbase.entity.SysUser;
import com.css.sysbase.service.UserService;
import com.css.utils.web.CtrlUtils;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	@Resource(name = "sysUserManager")
	private SysUserManager sysUserManager;

	public SysUser getById(String userId){
		return sysUserManager.get(userId);
	}
	@Override
	public void save(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String username = (String) paramMap.get("username");
		String password = (String) paramMap.get("password");
		String realname = (String) paramMap.get("realname");
		String depId = (String) paramMap.get("depId");
		
		SysUser u = new SysUser();
		u.setUserName(username);
		u.setPassword(password);
		u.setRealName(realname);
		u.setDepId(depId);
		sysUserManager.save(u);
//		String pwd=paramMap.get("password").toString();
//		MD5 m = new MD5();
//		pwd=m.getMD5ofStr(pwd);
//		paramMap.put("password", pwd);
	}
	
	
	@Override
	public void update(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String userId = (String) paramMap.get("userId");
		String username = (String) paramMap.get("username");
		String password = (String) paramMap.get("password");
		String realname = (String) paramMap.get("realname");
		String depId = (String) paramMap.get("depId");
		SysUser u = sysUserManager.get(userId);
		u.setUserName(username);
		u.setPassword(password);
		u.setRealName(realname);
		u.setDepId(depId);
		sysUserManager.save(u);
	}
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String userId) throws Exception{
		sysUserManager.deleteById(userId);
		Map conditions = new HashMap();
		conditions.put("userId", userId);
		commonManager.saveOrUpdate("sys_role-sql", "delUserRole_byUser", conditions);
	}


	@Override
	public void saveUserRole(String userId, String roleCode_str)
			throws Exception {
		if(!"".equals(roleCode_str)){
			Map conditions = new HashMap();
			conditions.put("userId", userId);
			commonManager.saveOrUpdate("sys_role-sql", "delUserRole_byUser", conditions);
			String[] roleCode_arr = roleCode_str.split(",");
			for (String roleCode : roleCode_arr) {
				conditions.put("roleCode", roleCode);
				commonManager.saveOrUpdate("sys_role-sql","addUserRole",conditions);
			}
		}
	}
	
	/**
     * 根据参数params获得数据库中某张表的数据
     * @param params
     * @param claz
     * @return
     */
    public List<SysUser> getEntityByProperties(Map params){
    	return sysUserManager.getEntityByProperties(params);
    }
	
}
