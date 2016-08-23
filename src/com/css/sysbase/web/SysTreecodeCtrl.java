package com.css.sysbase.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.SysTreecodeService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class SysTreecodeCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "sysTreecodeService")
	private SysTreecodeService sysTreecodeService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveSysTreecode")
	public void saveSysTreecode(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			map = sysTreecodeService.save(request);
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
	@RequestMapping("/editSysTreecode")
	public ModelAndView editSysTreecode(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("sys_treecode-sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "editSysTreecode", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editSysTreecodeSave")
	public void editSysTreecodeSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			map = sysTreecodeService.update(request);
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
	@RequestMapping("/delSysTreecodes")
	public void delSysTreecodes(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			sysTreecodeService.delMulti(request);
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
	 * 
	 * 列表-数据源 author:syw
	 */
	@RequestMapping("/treecodeList_datagird")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void treecodeList_datagird(HttpServletResponse response,
			HttpServletRequest request)
			throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		List<Map> dataList = commonService.loadData("sys_treecode-sql", "searchList",
				paramMap);
		CtrlUtils.putJSON(dataList,null, response);
	}

}
