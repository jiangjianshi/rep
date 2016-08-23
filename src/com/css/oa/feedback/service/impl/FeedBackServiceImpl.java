package com.css.oa.feedback.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.oa.check.service.FlowService;
import com.css.oa.feedback.service.FeedBackService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.dao.CommonManager;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;

@Service("FeedbackService")
public class FeedBackServiceImpl implements FeedBackService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "flowService")
	private FlowService flowService;
	
	@Override
	public void feedBackDetailSubSave(HttpServletRequest request) throws Exception {
		Map paramMap = new HashMap();
		String[] opinFeedbacks = request.getParameterValues("opinFeedback");
		String[] opinIds = request.getParameterValues("opinId");
		if(opinIds!=null){
			for(int i=0;i<opinIds.length;i++){
				paramMap.put("opinFeedback", new String(opinFeedbacks[i].getBytes("ISO-8859-1"), "UTF-8"));
				paramMap.put("opinId", opinIds[i]);
				commonManager.saveOrUpdate("apply_opinion-sql", "feedBackDetailSubSave", paramMap);
			}
		}
		String applyStatus = request.getParameter("applyStatus");
		if("yfk".equals(applyStatus)){
			String proId = request.getParameter("proId");
			String majorId = request.getParameter("majorId");
			saveFeedBackMajorSubmit(proId, majorId, applyStatus);
		}
	}
	
	
	
	
	/**
	 * 设计院回复页面-专业tab页-提交
	 */
	public Map saveFeedBackMajorSubmit(String proId,String majorId,String applyStatus){
		Map conditions = new HashMap();
		conditions.put("proId", proId);
		conditions.put("majorId", majorId);
		conditions.put("applyStatus", applyStatus);
		commonManager.saveOrUpdate("project_check-sql", "updateMajorApplyStatus", conditions);
		Map resultMap = new HashMap();
		
		List<Map> list1 = commonManager.executeSql("project_check-sql", "feedBack_IsAllMajors_isOk", conditions);
		String applyStatus_str = "";
		for (Map map : list1) {
			applyStatus_str += map.get("checkResult")+",";
		}
		
		if(applyStatus_str.equals(OaConstants.applyMajorFlowStatus_yfk+",")//“已反馈”
				){
			//将项目状态置为下一个流程状态“项目状态-已反馈”
			flowService.updateProFlowStatus(proId,OaConstants.proFlowStatus_8);
		}
		return resultMap;
	}
	
}
