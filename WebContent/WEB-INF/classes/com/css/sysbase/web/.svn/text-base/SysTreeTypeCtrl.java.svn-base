package com.css.sysbase.web;

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

import com.css.sysbase.service.SysTreeTypeService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;

@Controller
@RequestMapping("/sysbase")
public class SysTreeTypeCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "systreetypeService")
	private SysTreeTypeService systreetypeService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveSysTreeType")
	public void saveSysTreeType(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			map = systreetypeService.save(request);
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
	@RequestMapping("/editSysTreeType")
	public ModelAndView editSysTreeType(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("sys_tree_type-sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "editSysTreeType", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editSysTreeTypeSave")
	public void editSysTreeTypeSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = systreetypeService.update(request);
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
	@RequestMapping("/delSysTreeTypes")
	public void delSysTreeTypes(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			systreetypeService.delMulti(request);
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
