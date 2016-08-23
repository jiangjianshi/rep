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

import com.css.sysbase.entity.SysUser;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.SysDepService;
import com.css.sysbase.service.UserService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class SysDepCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "sysdepService")
	private SysDepService sysdepService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveSysDep")
	public void saveSysDep(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			sysdepService.save(request);
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
	@RequestMapping("/editSysDep")
	public ModelAndView editSysDep(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("sys_dept-sql", "getObjInfor",paramMap);
		paramMap.putAll(dataMap);
		return getModelAndView(subPath, "editSysDep", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editSysDepSave")
	public void editSysDepSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			sysdepService.update(request);
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
	@RequestMapping("/delSysDeps")
	public void delSysDeps(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			String depId = request.getParameter("depId");
			Map params = new HashMap(2);
			params.put("depId", depId);
			List<SysUser> userList = userService.getEntityByProperties(params);
			if(userList.size()>0){
				map.put("isSuccess", false);
				map.put("msg", "此部门下还有人员信息，不能删除！");
			}else{
				sysdepService.del(depId);
				map.put("isSuccess", true);
				map.put("msg", "删除成功！");
			}
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	/**
	 * 
	 * 部门列表-数据源 author:syw
	 */
	@RequestMapping("/depList_datagird")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void depList_datagird(HttpServletResponse response)
			throws Exception {
		List<Map> dataList = commonService.loadData("sys_dept-sql", "searchList",
				null);
		CtrlUtils.putJSON(dataList,null, response);
	}

}
