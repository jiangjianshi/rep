package com.css.sysbase.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.SysRightService;
import com.css.utils.cache.web.LoginSessionCache;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class SysBaseCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";

	@Resource(name = "commonService")
	private CommonService commonService;
	
	/**
	 * 跳转 页面
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/{fileName}")
	public ModelAndView about(@PathVariable String fileName,HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		return getModelAndView(subPath, fileName, paramMap);
	}
	/**
	 * 
	 * 描述 :主界面左侧树形菜单 author:syw 
	 */
	@RequestMapping("/leftZtree")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void leftZtree(HttpSession session,HttpServletResponse response) throws IOException {
		LoginSessionCache loginSessionCache = (LoginSessionCache) session.getAttribute("UserSession");
		String treeJosn = "";
		List<Map> menuList = loginSessionCache.getMenuList();
		if(menuList.size()>0){
			StringBuffer optionObj = new StringBuffer("");
			for (Map m : menuList) {
				optionObj.append("{\"id\":\"" + m.get("rightCode") + "\","
						+ "\"pId\":\"" + m.get("pCode") + "\"," + "\"name\":\""
						+ m.get("rightName") + "\"," + "\"rightType\":\""
						+ m.get("rightType") + "\"," + "\"open\":false,"
						+ "\"menuUrl\":\"" + m.get("rightUrl") + "\"},");
			}
			if (optionObj.length() > 0) {
				treeJosn = "[" + optionObj.substring(0, optionObj.length() - 1)
						+ "]";
			} else {
				treeJosn = "[{\"id\":\"1\"," + "\"pId\":\"0\","
						+ "\"name\":\"此用户无任何权限\"," + "\"linkurl\":\"\"}]";
			}
		}else{
			treeJosn = "[{\"id\":\"1\"," + "\"pId\":\"0\","
					+ "\"name\":\"此用户无任何权限\"," + "\"linkurl\":\"\"}]";
		}
		CtrlUtils.writeStr2Res(treeJosn, response);
	}

	/**
	 * 系统主界面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		return getModelAndView(subPath, "index", map);
	}
	
}
