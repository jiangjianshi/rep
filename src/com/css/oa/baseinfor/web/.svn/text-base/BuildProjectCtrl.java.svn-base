package com.css.oa.baseinfor.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.baseinfor.service.BuildProjectService;
import com.css.oa.param.service.ParamService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/baseinfor")
public class BuildProjectCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/baseinfor/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "buildprojectService")
	private BuildProjectService buildprojectService;
	
	@Resource(name = "paramService")
	private ParamService paramService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveBuildProject")
	public void saveBuildProject(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			map = buildprojectService.save(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	/**
	 * 更新
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/editBuildProject")
	public ModelAndView editBuildProject(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		String proId = (String) dataMap.get("pkId");
		BigDecimal totalArea  = (BigDecimal) dataMap.get("totalArea");
		int totalArea_intValue = totalArea.intValue();
		String unitPrice = paramService.getParamValueByCode("fangWuJZ_UnitPrice");
		int unitPrice_intValue = Integer.parseInt(unitPrice);
		String statExamAmtFormula = totalArea_intValue+"(平方米) * " + unitPrice + "(元) = " + (totalArea_intValue*unitPrice_intValue)+ "(元)"; 
		paramMap.put("statExamAmtFormula",statExamAmtFormula);
		paramMap.put("statExamAmt",(totalArea_intValue*unitPrice_intValue)+"");
		
		BigDecimal totalAmount  = (BigDecimal) dataMap.get("totalAmount");
		int totalAmount_intValue = totalAmount.intValue();
		dataMap.put("totalAmount", totalAmount_intValue+"");
		paramMap.putAll(dataMap);
		
		//加载“项目情况”
		paramMap.put("buildProId", proId);
		Map buildStatMap = commonService.loadSingleData("build_stat-sql", "getObjInfor",paramMap);
		BigDecimal statExamAmt  = (BigDecimal) buildStatMap.get("statExamAmt");
		if(statExamAmt!=null){
			buildStatMap.put("statExamAmt", statExamAmt.intValue()+"");
		}
		paramMap.put("buildStat",buildStatMap);
		
		//加载“送审资料”
		Map param2 = new HashMap();
		param2.put("proId", proId);
		List<Map> approvalInforList = commonService.loadData("approval_infor-sql", "searchList", param2);
		paramMap.put("approvalInforList",approvalInforList);
		
		return getModelAndView(subPath, "editBuildProject", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editBuildProjectSave")
	public void editBuildProjectSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = buildprojectService.update(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	/**
	 * 工程填报-编辑
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/updateBuildProject")
	public ModelAndView updateBuildProject(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		BigDecimal totalAmount  = new BigDecimal((String) dataMap.get("totalAmount"));
		int totalAmount_intValue = totalAmount.intValue();
		dataMap.put("totalAmount", totalAmount_intValue+"");
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "updateBuildProject", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/updateBuildProjectSave")
	public void updateBuildProjectSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = buildprojectService.updateBuildProjectSave(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	/**
	 * 删除
	 * @param res
	 * @param request
	 */
	@RequestMapping("/delBuildProjects")
	public void delBuildProjects(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			buildprojectService.delMulti(request);
			map.put("isSuccess", true);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/approvalInforSave")
	public void approvalInforSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
			Set keys = paramMap.keySet();
			List<String> approvalList = new ArrayList<String>();
			for (Object key : keys.toArray()) {
				String value = (String) paramMap.get(key);
				//${approvalInfor.approvalInforId}__yy
				if(value.indexOf("_=-=_")>0){
					approvalList.add(value);
				}
			}
			buildprojectService.saveApprovalInfor(approvalList);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
}
