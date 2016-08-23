package com.css.sysbase.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.sysbase.service.SysTreeTypeService;
import com.css.sysbase.dao.CommonManager;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;

@Service("systreetypeService")
public class SysTreeTypeServiceImpl implements SysTreeTypeService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;

	@Override
	public Map save(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("tId", String.valueOf(UUID.randomUUID()));
		int n = commonManager.saveOrUpdate("sys_tree_type-sql", "addObj",paramMap);
		map.put("n", n);
		return map;
	}
	
	
	@Override
	public Map update(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		int n = commonManager.saveOrUpdate("sys_tree_type-sql", "updateObj",paramMap);
		map.put("n", n);
		return map;
	}
	
	/**
	 * 多项删除
	 * @param request
	 * @throws Exception
	 */
	public void delMulti(HttpServletRequest request) throws Exception{
		String delIds = request.getParameter("delIds");
		String[] delIds_arr = delIds.split(",");
		for (String id : delIds_arr) {
			del(id);
		}
	}
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String delId) throws Exception{
		Map conditions = new HashMap();
		conditions.put("delId", delId);
		commonManager.saveOrUpdate("sys_tree_type-sql", "delObj", conditions);
	}
	
}
