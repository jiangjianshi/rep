package com.css.oa.feedback.web;

import java.math.BigDecimal;
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

import com.css.oa.feedback.service.FeedBackService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;
import com.css.utils.web.PageInfor;

@Controller
@RequestMapping("/oa/feedback")
public class FeedBackCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/feedback/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "FeedbackService")
	private FeedBackService feedbackService;
	
	
	
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
	 * 意见反馈-数据列表 datagrid数据源
	 * author:syw
	 */
	@RequestMapping("/feedbackListGridData")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void feedbackListGridData(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		//判断是否是：设计院临时帐户登录，如果是，则只加载和这个用户关联的项目信息
		if(CtrlUtils.getSessionUserInforMap(request).get("pro_id")!=null){
			String curLoginUserId = CtrlUtils.getSessionUserId(request);
			paramMap.put("curLoginUserId", curLoginUserId);
		}
		
		List<Map> list = commonService.loadData("pro_feed_sql", "searchList", paramMap);
		CtrlUtils.putJSON(list, null, response);
	}
	
	
	
	/**
	 * @Title: projectFeedBackDeatil
	 * @Description: 意见回复详细页面
	 * @author doumk
	 * @throws Exception 
	 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/feedBackDeatil")
	public ModelAndView projectFeedBackDeatil(HttpServletResponse res,
			HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = request.getParameter("proId");
		paramMap.put("curLoginUserId", CtrlUtils.getSessionUserId(request));
		List<Map> majorInforList = commonService.loadData("project_check-sql", "getCheckMajorNameForTabs",paramMap);
		paramMap.put("id", proId);
		Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		paramMap.put("majorInforList",majorInforList);
		paramMap.put("proInfor",proInfor);
		return getModelAndView(subPath, "feedBackDetail", paramMap);
	}
	
	/**
	 * checkDetailSub
	 * @param request
	 * @return
	 */
	@RequestMapping("/feedBackDetailSub")
	public ModelAndView projectFeedBackDetailSub(HttpServletResponse res,HttpServletRequest request) throws Exception{
		Map paramMap = new HashMap();
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
		
		
		Map m  = commonService.loadSingleData("apply_opinion-sql", "getMaxRound", paramMap);
		BigDecimal maxRound = (BigDecimal) m.get("maxRound");
		if(maxRound!=null && maxRound.intValue()>1){
			paramMap.put("maxRound", maxRound.intValue());
			List<Map> proofreadOpinionList = commonService.loadData("apply_opinion-sql", "searchProofreadOpinionList",paramMap);
			paramMap.put("proofreadOpinionList", proofreadOpinionList);
			return getModelAndView(subPath, "feedBackProofreadDetailSub", paramMap);
		}else{
			return getModelAndView(subPath, "feedBackDetailSub", paramMap);
		}
		
	}
	
	/**
	 * feedBackDetailSubSave
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/feedBackDetailSubSave")
	public void feedBackDetailSubSave(HttpServletResponse res,HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			feedbackService.feedBackDetailSubSave(request);
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
	 * 
	 * 设计院回复页面-专业审核-提交
	 * author:syw
	 */
	@RequestMapping("/saveFeedBackMajorSubmit")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveFeedBackMajorSubmit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map map = new HashMap();// 页面输出信息
		try {
			String proId = request.getParameter("proId");
			String majorId = request.getParameter("majorId");
			String applyStatus = request.getParameter("applyStatus");
			Map resultMap = feedbackService.saveFeedBackMajorSubmit(proId, majorId, applyStatus);
			map.putAll(map);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, response);
	}
}
