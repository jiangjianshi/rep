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

import com.css.sysbase.entity.SysRight;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.SysRightService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class SysRightCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "sysRightService")
	private SysRightService sysRightService;
	
	/**
	 * 权限管理
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/rightList")
	public ModelAndView rightList(String id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		return getModelAndView(subPath, "rightList", map);
	}

	/**
	 * 
	 * 权限管理-数据源 author:syw
	 */
	@RequestMapping("/rightList_datagird")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void rightList_datagird(HttpServletResponse response)
			throws Exception {
		List<Map> dataList = commonService.loadData("right-sql", "rightList",
				null);
		CtrlUtils.putJSON(dataList,null, response);
	}

	/**
	 * 权限管理-保存、更新 author:syw
	 * 
	 * @param res
	 * @param request
	 */
	@RequestMapping("/saveRight")
	public void saveRight(String s, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			String saveType = request.getParameter("saveType");
			if ("update".equals(saveType)) {
				sysRightService.update(request);
			} else if ("add".equals(saveType)) {
				sysRightService.save(request);
			}
			
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
	 * 权限管理-删除 author:syw
	 * 
	 * @param res
	 * @param request
	 */
	@RequestMapping("/delRight")
	public void delRight(String s, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			String rightId = request.getParameter("rightId");
			int r = sysRightService.del(rightId);
			if(r==0){
				map.put("isSuccess", false);
				map.put("msg", "请先删除此权限下的子权限！");
			}else{
				map.put("isSuccess", true);
				map.put("msg", "操作成功！");
			}
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}

	/**
	 * author:syw
	 */
	@RequestMapping("/roleRightTree")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void roleRightTree(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		String curRoleCode = req.getParameter("roleCode");
		Map searchParam = new HashMap();
		searchParam.put("roleCode", curRoleCode);
		List<Map> dataList = commonService.loadData("right-sql","roleRightTree", searchParam);
		StringBuffer optionObj = new StringBuffer("");
		for (Map m : dataList) {
			optionObj.append("{\"id\":\"" + m.get("rightCode") + "\",");
			optionObj.append("\"pId\":\"" + m.get("pCode") + "\",");
			optionObj.append("\"name\":\"" + m.get("rightName") + "\",");
			optionObj.append("\"rightType\":\"" + m.get("rightType") + "\",");
			optionObj.append("\"open\":true,");
			if(curRoleCode!=null && curRoleCode.equals(m.get("roleCode"))){
				optionObj.append("\"checked\":true,");
			}else{
				optionObj.append("\"checked\":false,");
			}
			optionObj.append("\"menuUrl\":\"" + m.get("rightUrl") + "\"},");
		}
		String treeJosn = "";
		if (optionObj.length() > 0) {
			treeJosn = "[" + optionObj.substring(0, optionObj.length() - 1)
					+ "]";
		} else {
			treeJosn = "[{\"id\":\"1\"," + "\"pId\":\"0\","
					+ "\"name\":\"此用户无任何权限\"," + "\"linkurl\":\"\"}]";
		}
		CtrlUtils.writeStr2Res(treeJosn, res);
	}
	
	/**
	 * 权限点添加、更新页面
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/rightPointAddEdit")
	public ModelAndView rightPointAddEdit(HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String pCode = (String) paramMap.get("pCode");
		if(pCode==null){
			paramMap.put("addOrEdit", "edit");
			String rightId = (String) paramMap.get("rightId");
			SysRight sysRight = sysRightService.getById(rightId);
			paramMap.put("editObj", sysRight);
		}else{
			paramMap.put("addOrEdit", "add");
			paramMap.put("editObj", new HashMap());
		}
		return getModelAndView(subPath, "rightPointAddEdit", paramMap);
	}
	
	/**
	 * 权限点添加、更新页面 - 保存操作
	 * 
	 * @param res
	 * @param request
	 */
	@RequestMapping("/rightPointAddEditSave")
	public void rightPointAddEditSave(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			String rightId = request.getParameter("rightId");
			sysRightService.saveRightPointAddEdit(request);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}

	
}
