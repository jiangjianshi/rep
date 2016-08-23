package com.css.sysbase.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.dao.SysDeptManager;
import com.css.sysbase.dao.SysUserManager;
import com.css.sysbase.entity.SysDept;
import com.css.sysbase.entity.SysUser;
import com.css.sysbase.service.SysDepService;
import com.css.utils.web.CtrlUtils;

@Service("sysdepService")
public class SysDepServiceImpl implements SysDepService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "sysDeptManager")
	private SysDeptManager sysDeptManager;
	
	@Resource(name = "sysUserManager")
	private SysUserManager sysUserManager;

	public SysDept getById(String depId){
		return sysDeptManager.get(depId);
	}
	
	
	@Override
	public void save(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String depName = (String) paramMap.get("depName");
		String depOrder = (String) paramMap.get("depOrder");
		String parentDep = (String) paramMap.get("parentDep");
		String depManager = (String) paramMap.get("depManager");
		SysDept dep = new SysDept();
		dep.setDepName(depName);
		dep.setDepOrder(depOrder);
		dep.setDepManager(depManager);
		dep.setParentDep(parentDep);
		sysDeptManager.save(dep);
	}
	
	
	@Override
	public void update(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String depName = (String) paramMap.get("depName");
		String depOrder = (String) paramMap.get("depOrder");
		String depManager = (String) paramMap.get("depManager");
		String depId = (String) paramMap.get("depId");
		SysDept dep = sysDeptManager.get(depId);
		dep.setDepName(depName);
		dep.setDepOrder(depOrder);
		dep.setDepManager(depManager);
		sysDeptManager.update(dep);
	}
	
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String delId) throws Exception{
		sysDeptManager.deleteById(delId);
	}
	
	/**
     * 根据参数params获得数据库中某张表的数据
     * @param params
     * @param claz
     * @return
     */
    public List<SysDept> getEntityByProperties(Map params){
    	return sysDeptManager.getEntityByProperties(params);
    }
	
}
