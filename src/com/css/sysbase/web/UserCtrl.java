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

import com.css.sysbase.entity.SysDept;
import com.css.sysbase.entity.SysUser;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.SysDepService;
import com.css.sysbase.service.UserService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class UserCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "sysdepService")
	private SysDepService sysDepService;
	
	/**
	 * 新增
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveUser")
	public void saveUser(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			userService.save(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功");
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
	@RequestMapping("/editUser")
	public ModelAndView editUser(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String userId = request.getParameter("id");
		SysUser u = userService.getById(userId);
		paramMap.put("objInfor",u);
		
		if(u.getDepId()!=null){
			SysDept dep = sysDepService.getById(u.getDepId());
			if(dep!=null){
				paramMap.put("depName",dep.getDepName());
				paramMap.put("depId",dep.getDepId());
			}
		}
		
		return getModelAndView(subPath, "editUser", paramMap);
	}
	
	/**
	 * 更新-保存
	 * @param res
	 * @param request
	 */
	@RequestMapping("/editUserSave")
	public void editUserSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			userService.update(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功");
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
	@RequestMapping("/delUser")
	public void delUsers(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			String userId = request.getParameter("userId");
			userService.del(userId);
			map.put("isSuccess", true);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	/**
	 * setUserRole
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/setUserRole")
	public ModelAndView setUserRole(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		List<Map> dataList = commonService.loadData("sys_user-sql", "getRoleInfor",paramMap);
		paramMap.put("roleList",dataList);
		return getModelAndView(subPath, "setUserRole", paramMap);
	}
	
	/**
	 * setUserRole-保存
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/setUserRoleSave")
	public void setUserRoleSave(HttpServletResponse res,
			HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String userId = (String) paramMap.get("userId");
		String roleCode_str =  (String) paramMap.get("roleCode_str");
		Map map = new HashMap(2);// 页面输出信息
		try {
			userService.saveUserRole(userId,roleCode_str);
			map.put("isSuccess", true);
			map.put("msg", "操作成功");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	/**
	 * author:syw
	 */
	@RequestMapping("/leftDepTree")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void leftDepTree(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String curRoleCode = req.getParameter("roleCode");
		Map searchParam = new HashMap();
		searchParam.put("roleCode", curRoleCode);
		List<Map> dataList = commonService.loadData("sys_dept-sql","leftDepTree", searchParam);
		StringBuffer optionObj = new StringBuffer("");
		for (Map m : dataList) {
			optionObj.append("{\"id\":\"" + m.get("depId") + "\",");
			optionObj.append("\"pId\":\"" + m.get("pId") + "\",");
			optionObj.append("\"name\":\"" + m.get("depName") + "\",");
			optionObj.append("\"open\":true},");
		}
		String treeJosn = "";
		if (optionObj.length() > 0) {
			treeJosn = "[" + optionObj.substring(0, optionObj.length() - 1)
					+ "]";
		} else {
			treeJosn = "[{\"id\":\"1\"," + "\"pId\":\"0\","
					+ "\"name\":\"无部门信息\"}]";
		}
		CtrlUtils.writeStr2Res(treeJosn, res);
	}
	

}
