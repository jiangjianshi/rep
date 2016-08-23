package com.css.sysbase.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.sysbase.service.CommonService;
import com.css.utils.cache.web.LoginSessionCache;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class LoginCtrl extends BaseController {
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	/**
	 * 检测登录
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/checkLogin")
	public ModelAndView checkLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = new HashMap();// 页面输出信息
		try {
			Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
			List<Map> userList = commonService.loadData("user-sql", "userLogin", paramMap);
			if(userList!=null && userList.size()>0){
				//session中用户信息缓存
				Map<String, Object> userMap = new HashMap<String, Object>();
				session.setAttribute(session.getId(), userList.get(0));
				LoginSessionCache loginSessionCache = new LoginSessionCache();
				loginSessionCache.initSessoinCache(session,commonService);
				session.setAttribute("UserSession",loginSessionCache);
				map.put("isSuccess", true);
				map.put("msg", "登录成功！");
				return getModelAndView("sysbase/", "index", map);
			}else{
				map.put("isSuccess", false);
				map.put("msg", "用户名或密码错误！");
			}
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统登录异常！");
			e.printStackTrace();
		}
		return getModelAndView("sysbase/", "login", map);
	}
	
	@RequestMapping("/logout")
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
	}
}
