package com.css.sysbase.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.dao.SysRoleManager;
import com.css.sysbase.entity.SysRole;
import com.css.sysbase.service.RoleService;
import com.css.utils.web.CtrlUtils;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "sysRoleManager")
	private SysRoleManager sysRoleManager;

	public SysRole getById(String depId){
		return sysRoleManager.get(depId);
	}

	@Override
	public void save(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String roleName = (String) paramMap.get("roleName");
		SysRole entity = new SysRole();
		entity.setRoleName(roleName);
		entity.setRoleType("");
		sysRoleManager.save(entity);
		entity.setRoleCode(entity.getPkId());
		sysRoleManager.update(entity);
	}
	
	
	@Override
	public void update(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String roleId = (String) paramMap.get("pkId");
		String roleName = (String) paramMap.get("roleName");
		SysRole entity = sysRoleManager.get(roleId);
		entity.setRoleName(roleName);
		sysRoleManager.update(entity);
	}
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String roleCode) throws Exception{
		Map conditions = new HashMap();
		conditions.put("roleCode", roleCode);
		commonManager.saveOrUpdate("sys_role-sql", "delObj", conditions);
		commonManager.saveOrUpdate("sys_role-sql","del_user_role",conditions);
		commonManager.saveOrUpdate("sys_role-sql","del_role_right",conditions);
	}
	
	/**
	 * saveRoleRight
	 * @param request
	 * @throws Exception
	 */
	public void saveRoleRight(String roleCode,String rightCodes) throws Exception{
		Map delRoleRight_param = new HashMap();
		delRoleRight_param.put("roleCode", roleCode);
		commonManager.saveOrUpdate("right-sql","delRoleRight_by_role",delRoleRight_param);
		if(rightCodes!=null && !"".equals(rightCodes)){
			String[] rightCodes_arr = rightCodes.split(",");
			for (String rightCode : rightCodes_arr) {
				Map addRoleRight_param = new HashMap();
				addRoleRight_param.put("pkId", String.valueOf(UUID.randomUUID()));
				addRoleRight_param.put("roleCode", roleCode);
				addRoleRight_param.put("rightCode", rightCode);
				commonManager.saveOrUpdate("right-sql","addRoleRight",addRoleRight_param);
			}
		}
	}
	
	public boolean isHaveRightByUserId(String userId,String rightCode){
		Map sqlParam = new HashMap();
		sqlParam.put("userId", userId);
		sqlParam.put("rightCode", rightCode);
		List<Map> dataList = commonManager.executeSql("sys_role-sql", "isHaveRightByUserId", sqlParam);
		if(dataList.size()>0){
			return true;
		}
		return false;
	}
	
}
