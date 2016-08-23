package com.css.sysbase.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.dao.SysRightManager;
import com.css.sysbase.entity.SysRight;
import com.css.sysbase.service.SysRightService;
import com.css.utils.web.CtrlUtils;

@Service("sysRightService")
public class SysRightServiceImpl implements SysRightService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "sysRightManager")
	private SysRightManager sysRightManager;

	@Override
	public void save(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		
		String rightName = (String) paramMap.get("rightName");
		String pCode = (String) paramMap.get("pCode");
		String rightUrl = (String) paramMap.get("rightUrl");
		String orderCode = (String) paramMap.get("orderCode");
		
		SysRight sysRight = new SysRight();
		sysRight.setRightName(rightName);//
		sysRight.setpCode(pCode);//
		sysRight.setRightUrl(rightUrl);//
		sysRight.setOrderCode(orderCode);//
		sysRight.setRightType("0");
		
		sysRightManager.save(sysRight);
		sysRight.setRightCode(sysRight.getPkId());
		sysRightManager.update(sysRight);
	}
	
	
	@Override
	public void update(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String pkId = (String) paramMap.get("pkId");
		String rightName = (String) paramMap.get("rightName");
		String rightUrl = (String) paramMap.get("rightUrl");
		String orderCode = (String) paramMap.get("orderCode");
		
		SysRight sysRight = sysRightManager.get(pkId);
		sysRight.setRightName(rightName);//
		sysRight.setRightUrl(rightUrl);//
		sysRight.setOrderCode(orderCode);//
		sysRightManager.update(sysRight);
	}
	
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public int del(String pkId) throws Exception{
		SysRight sysRight = sysRightManager.get(pkId);
		String rightCode = sysRight.getRightCode();
		
		Map conditions = new HashMap();
		conditions.put("rightCode", rightCode);
		
		List<Map> list = commonManager.executeSql("right-sql", "isHaveSubRight_by_right", conditions);
		if(list.size()>0){
			return 0;
		}
		
		commonManager.saveOrUpdate("right-sql", "delRoleRight_by_right", conditions);
		sysRightManager.deleteById(pkId);
		return 1;
	}
	
	public SysRight getById(String id){
		return sysRightManager.get(id);
	}
	
	
	@Override
	public void saveRightPointAddEdit(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String addOrEdit = (String) paramMap.get("addOrEdit");
		if("add".equals(addOrEdit)){
			String rightName = (String) paramMap.get("rightName");
			String pCode = (String) paramMap.get("pCode");
			String orderCode = (String) paramMap.get("orderCode");
			String rightCode = (String) paramMap.get("rightCode");
			
			SysRight sysRight = new SysRight();
			sysRight.setRightName(rightName);//
			sysRight.setpCode(pCode);//
			sysRight.setRightCode(rightCode);
			sysRight.setRightUrl(null);//
			sysRight.setOrderCode(orderCode);//
			sysRight.setRightType("1");
			
			sysRightManager.save(sysRight);
		}else if("edit".equals(addOrEdit)){
			String rightId = (String) paramMap.get("rightId");
			String rightName = (String) paramMap.get("rightName");
			String orderCode = (String) paramMap.get("orderCode");
			String rightCode = (String) paramMap.get("rightCode");
			
			SysRight sysRight = sysRightManager.get(rightId);
			sysRight.setRightName(rightName);//
			sysRight.setOrderCode(orderCode);//
			sysRight.setRightCode(rightCode);
			sysRightManager.update(sysRight);
		}
	}
	
}
