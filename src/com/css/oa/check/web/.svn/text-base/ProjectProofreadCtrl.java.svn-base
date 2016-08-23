package com.css.oa.check.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.check.service.CheckService;
import com.css.oa.check.service.FlowService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/check")
public class ProjectProofreadCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/check/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "flowService")
	private FlowService flowService;
	
	@Resource(name = "checkService")
	private CheckService checkService;
	
	/**
	 * 
	 * 项目复审-数据列表 datagrid数据源
	 * author:syw
	 */
	@RequestMapping("/proofreadListGridData")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void proofreadListGridData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		String curLoginUserId = CtrlUtils.getSessionUserId(request);
		paramMap.put("curLoginUserId", curLoginUserId);
		List<Map> list = commonService.loadData("project_proofread-sql", "proofreadList", paramMap);
		CtrlUtils.putJSON(list, null, response);
	}
	/**
	 * 项目复审-审查页面
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/proofreadDetail")
	public ModelAndView checkDetail(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = request.getParameter("proId");
		paramMap.put("curLoginUserId", CtrlUtils.getSessionUserId(request));
		List<Map> majorInforList = commonService.loadData("project_check-sql", "getCheckMajorNameForTabs",paramMap);
		paramMap.put("id", proId);
		Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		paramMap.put("majorInforList",majorInforList);
		paramMap.put("proInfor",proInfor);
		
		Map dataMap = commonService.loadSingleData("fn_govopinion_sql", "getGovopinionByProId",paramMap);
		paramMap.put("govopinionInfor",dataMap);
		
		
		return getModelAndView(subPath, "proofreadDetail", paramMap);
	}
	
	
	/**
	 * proofreadDetailSub
	 * @param request
	 * @return
	 */
	@RequestMapping("/proofreadDetailSub")
	public ModelAndView proofreadDetailSub(HttpServletResponse res,HttpServletRequest request) throws Exception{
		Map paramMap = new HashMap();
		String majorId = request.getParameter("majorId");
		String proId = request.getParameter("proId");
		paramMap.put("majorId", majorId);
		paramMap.put("proId", proId);
		
		List<Map> opinionList = commonService.loadData("apply_opinion-sql", "searchList",paramMap);
		paramMap.put("opinionList", opinionList);
		
		List<Map> proofreadOpinionList = commonService.loadData("apply_opinion-sql", "searchProofreadOpinionList",paramMap);
		paramMap.put("proofreadOpinionList", proofreadOpinionList);
		
		Map m  = commonService.loadSingleData("apply_opinion-sql", "getMaxRound", paramMap);
		BigDecimal maxRound = (BigDecimal) m.get("maxRound");
		if(maxRound!=null){
			paramMap.put("maxRound", maxRound.intValue());
		}else{
			paramMap.put("maxRound", 0);
		}
		
		List<Map> minoList = commonService.loadData("project_check-sql", "minoList",paramMap);
		paramMap.put("minoList", minoList);
		
		Map curMajorInfor = commonService.loadSingleData("major-sql", "getMajorInfor", paramMap);
		paramMap.put("curMajorInfor", curMajorInfor);
		
		paramMap.put("basePath", request.getContextPath());
		paramMap.put("curLoginUserId", CtrlUtils.getSessionUserId(request));
		return getModelAndView(subPath, "proofreadDetailSub", paramMap);
	}
	
	/**
	 * checkDetailSubSave
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/proofreadDetailSubSave")
	public void proofreadDetailSubSave(HttpServletResponse res,HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			checkService.proofreadDetailSubSave(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
}
