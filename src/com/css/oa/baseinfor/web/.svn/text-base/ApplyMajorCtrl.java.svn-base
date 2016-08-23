package com.css.oa.baseinfor.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.baseinfor.service.ApplyMajorService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/baseinfor")
public class ApplyMajorCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/baseinfor/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "applyMajorService")
	private ApplyMajorService applyMajorService;
	/**
	 * 项目分工
	 * @param res
	 * @param request
	 * @throws Exception  bpDivideSave
	 */
	@RequestMapping("/bpDivide")
	public ModelAndView bpDivide(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		
		String proId = (String) paramMap.get("proId");
		paramMap.put("id", proId);
		Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		paramMap.put("proInfor", proInfor);
		
		List<Map> dataList = commonService.loadData("major_devide-sql", "searchList",paramMap);
		paramMap.put("dataList", dataList);
		return getModelAndView(subPath, "bpDivide", paramMap);
	}
	
	/**
	 * 项目分工-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/bpDivideSave")
	public void bpDivideSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			applyMajorService.save(request);
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
	 * 审核专项     新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveApplyMajor")
	public void saveApplyMajor(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			applyMajorService.saveApplyMajor(request);
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
	@RequestMapping("/applyMajorEdit")
	public ModelAndView applyMajorEdit(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("apply_major_sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "applyMajorEdit", paramMap);
	}
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/applyMajorEditSave")
	public void applyMajorEditSave(HttpServletResponse res, HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = applyMajorService.updateApplyMajor(request);
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
	@RequestMapping("/applyMajorDel")
	public void applyMajorDel(HttpServletResponse res, HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			applyMajorService.delApplyMajor(request);
			map.put("isSuccess", true);
			map.put("msg", "删除成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}

}
