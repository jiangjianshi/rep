package com.css.oa.baseinfor.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.baseinfor.service.BuildProStatService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/baseinfor")
public class BuildProStatCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/baseinfor/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "buildprostatService")
	private BuildProStatService buildprostatService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveBuildProStat")
	public void saveBuildProStat(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			map = buildprostatService.save(request);
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
	@RequestMapping("/editBuildProStat")
	public ModelAndView editBuildProStat(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("build_stat-sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "editBuildProStat", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editBuildProStatSave")
	public void editBuildProStatSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = buildprostatService.update(request);
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
	@RequestMapping("/delBuildProStats")
	public void delBuildProStats(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			buildprostatService.delMulti(request);
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
