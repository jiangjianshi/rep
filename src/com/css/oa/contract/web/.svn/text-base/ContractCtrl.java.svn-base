package com.css.oa.contract.web;

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
import com.css.sysbase.service.CommonService;
import com.css.utils.TimeUtils;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/contract")
public class ContractCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/contract/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "checkService")
	private CheckService checkService;
	
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
	 * 合同详情-页面
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/viewContract")
	public ModelAndView viewContract(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = (String) paramMap.get("proId");
		paramMap.put("id", proId);
		Map dataMap = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		
		//加载“项目情况”
		paramMap.put("buildProId", proId);
		Map buildStatMap = commonService.loadSingleData("build_stat-sql", "getObjInfor",paramMap);
		paramMap.put("buildStat",buildStatMap);
		
		//加载“送审资料”
		Map param2 = new HashMap();
		param2.put("proId", proId);
		List<Map> approvalInforList = commonService.loadData("approval_infor-sql", "searchList", param2);
		paramMap.put("approvalInforList",approvalInforList);
		
		//加载“合同信息”
		paramMap.put("proId", proId);
		Map contractInfor = commonService.loadSingleData("fn_con_sql", "getContractForId",paramMap);
		paramMap.put("contractInfor",contractInfor);

		
		return getModelAndView(subPath, "viewContract", paramMap);
	}
	
}
