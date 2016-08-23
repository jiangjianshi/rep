package com.css.oa.check.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.check.service.CheckService;
import com.css.oa.check.service.FlowService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.service.CommonService;
import com.css.utils.TimeUtils;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/check")
public class CheckCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/check/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "checkService")
	private CheckService checkService;
	
	@Resource(name = "flowService")
	private FlowService flowService;
	
	/**
	 * 跳转 页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{fileName}")
	public ModelAndView vistPage(@PathVariable String fileName,HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("basePath", request.getContextPath());
		return getModelAndView(subPath, fileName, paramMap);
	}
	
	/**
	 * 
	 * 项目一审-数据列表 datagrid数据源
	 * author:syw
	 */
	@RequestMapping("/checkListGridData")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void checkListGridData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		String curLoginUserId = CtrlUtils.getSessionUserId(request);
		paramMap.put("curLoginUserId", curLoginUserId);
		List<Map> list = commonService.loadData("project_check-sql", "checkList", paramMap);
		CtrlUtils.putJSON(list, null, response);
	}
	
	/**
	 * 项目一审-审查页面
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/checkDetail")
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
		return getModelAndView(subPath, "checkDetail", paramMap);
	}
	
	/**
	 * checkDetailSub
	 * @param request
	 * @return
	 */
	@RequestMapping("/checkDetailSub")
	public ModelAndView checkDetailSub(HttpServletResponse res,HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String majorId = request.getParameter("majorId");
		String proId = request.getParameter("proId");
		paramMap.put("majorId", majorId);
		paramMap.put("proId", proId);
		paramMap.put("basePath", request.getContextPath());
		List<Map> opinionList = commonService.loadData("apply_opinion-sql", "searchList",paramMap);
		paramMap.put("opinionList", opinionList);
		
		List<Map> minoList = commonService.loadData("project_check-sql", "minoList",paramMap);
		paramMap.put("minoList", minoList);
		
		Map curMajorInfor = commonService.loadSingleData("major-sql", "getMajorInfor", paramMap);
		paramMap.put("curMajorInfor", curMajorInfor);
		
		paramMap.put("curLoginUserId", CtrlUtils.getSessionUserId(request));
		return getModelAndView(subPath, "checkDetailSub", paramMap);
	}
	
	/**
	 * addOpinion
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/addOpinion")
	public void addOpinion(String proId, String majorId,String round, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = new HashMap();
			paramMap.put("opinId", String.valueOf(UUID.randomUUID()));
			paramMap.put("proId", proId);
			paramMap.put("majorId", majorId);
			paramMap.put("round", round);
			paramMap.put("createTime", TimeUtils.getInstance().getTimeByFormat("yyyy-MM-dd HH:mm:ss"));
			int n = commonService.saveOrUpdate("apply_opinion-sql", "addObj", paramMap);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！" + "影响了" + n + "条数据");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	/**
	 * checkDetailSubSave
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/checkDetailSubSave")
	public void checkDetailSubSave(HttpServletResponse res,HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			checkService.checkDetailSubSave(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	/**
	 * archive
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/archive")
	public void archive(String proId, String majorId,String round, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = new HashMap();
			flowService.updateProFlowStatus(proId,OaConstants.proFlowStatus_10);//归档
			map.put("isSuccess", true);
			map.put("msg", "归档成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "归档失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
}
