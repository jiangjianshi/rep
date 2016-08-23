package com.css.oa.baseinfor.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.oa.baseinfor.service.ApplyMajorService;
import com.css.oa.check.service.FlowService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.dao.CommonManager;
import com.css.utils.web.CtrlUtils;

@Service("applyMajorService")
public class ApplyMajorServiceImpl implements ApplyMajorService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	@Resource(name = "flowService")
	private FlowService flowService;
	@Override
	public Map save(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = (String) paramMap.get("proId");
		
		int n = commonManager.saveOrUpdate("major_devide-sql", "clearByProId",paramMap);
		
		String checkedMajorId_str = (String) paramMap.get("checkedMajorId_str");
		String checkUserId_str = (String) paramMap.get("checkUserId_str");
		String proofreadUserId_str = (String) paramMap.get("proofreadUserId_str");
		if(!"".equals(checkedMajorId_str)){
			String[] checkedMajorId_arr = checkedMajorId_str.split(",");
			String[] checkUserId_arr = checkUserId_str.split(",");
			String[] proofreadUserId_arr = proofreadUserId_str.split(",");
			for (int i=0;i<checkedMajorId_arr.length;i++) {
				Map addMap = new HashMap();
				addMap.put("pro_major_id", String.valueOf(UUID.randomUUID()));
				addMap.put("pro_id", proId);
				addMap.put("major_id", checkedMajorId_arr[i]);
				addMap.put("check_user", checkUserId_arr[i]);
				addMap.put("proofread_user", proofreadUserId_arr[i]);
				addMap.put("check_result", OaConstants.applyMajorFlowStatus_csz);//专业审查状态-初审中
				commonManager.saveOrUpdate("major_devide-sql", "add",addMap);
			}
		}

		String isSubmit = (String) paramMap.get("isSubmit");
		if("true".equals(isSubmit)){
			flowService.updateProFlowStatus(proId, OaConstants.proFlowStatus_4);
		}else{
			flowService.updateProFlowStatus(proId, OaConstants.proFlowStatus_3);
		}
		
		return map;
	}
	
	@Override
	public Map saveApplyMajor(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("pkId", String.valueOf(UUID.randomUUID()));
		int n = commonManager.saveOrUpdate("apply_major_sql", "addObj",paramMap);
		map.put("n", n);
		return map;
	}
	@Override
	public Map updateApplyMajor(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		int n = commonManager.saveOrUpdate("apply_major_sql", "updateObj",paramMap);
		map.put("n", n);
		return map;
	}
	@Override
	public void delApplyMajor(HttpServletRequest request) throws Exception {
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
		commonManager.saveOrUpdate("apply_major_sql", "delObj", conditions);
	}
}
