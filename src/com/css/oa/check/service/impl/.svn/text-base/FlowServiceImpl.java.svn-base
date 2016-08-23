package com.css.oa.check.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.oa.baseinfor.service.BusinessService;
import com.css.oa.check.service.FlowService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.dao.CommonManager;

@Service("flowService")
public class FlowServiceImpl implements FlowService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "businessService")
	private BusinessService businessService;
	
	/**
	 * 更新项目流程状态
	 */
	public void updateProFlowStatus(String proId,String flowStatus){
		Map conditions = new HashMap();
		conditions.put("pkId", proId);
		conditions.put("flowStatus", flowStatus);
		commonManager.saveOrUpdate("build_pro-sql", "updateProFlowStatus", conditions);
	}
	
	/**
	 * 审查页面-专业审核-提交
	 */
	public Map saveCheckMajorSubmit(String proId,String majorId,String applyStatus){
		Map conditions = new HashMap();
		conditions.put("proId", proId);
		conditions.put("majorId", majorId);
		conditions.put("applyStatus", applyStatus);
		commonManager.saveOrUpdate("project_check-sql", "updateMajorApplyStatus", conditions);
		Map resultMap = new HashMap();
		
		List<Map> list1 = commonManager.executeSql("project_check-sql", "IsAllMajors_isOk", conditions);
		String applyStatus_str = "";
		for (Map map : list1) {
			applyStatus_str += map.get("checkResult")+",";
		}
		
		if(applyStatus_str.equals(OaConstants.applyMajorFlowStatus_hg+",")//均“合格”
			){
			updateProFlowStatus(proId,OaConstants.proFlowStatus_5);//审查通过
		}
		if(applyStatus_str.equals(OaConstants.applyMajorFlowStatus_dfk+","+OaConstants.applyMajorFlowStatus_hg+",")//“待反馈”、“合格”
		||applyStatus_str.equals(OaConstants.applyMajorFlowStatus_dfk+",")//均“待反馈”
			){
			updateProFlowStatus(proId,OaConstants.proFlowStatus_6);//待反馈
			businessService.sendTempUserInforToEmail(proId, "待反馈，请登录系统处理反馈意见！");
		}
		return resultMap;
	}
	
}
