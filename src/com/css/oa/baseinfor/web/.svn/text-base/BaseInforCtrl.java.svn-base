package com.css.oa.baseinfor.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.baseinfor.service.BuildProjectService;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.WwwService;
import com.css.utils.EmailUtils;
import com.css.utils.PropertyConfigurerSon;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/baseinfor")
public class BaseInforCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/baseinfor/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "buildprojectService")
	private BuildProjectService buildprojectService;
	
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
	 * gcslSubmit
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/gcslSubmit")
	public void gcslSubmit(String proId, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			buildprojectService.saveGcslSubmit(proId);
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
	 * 查看设计院临时帐户
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/viewTempUser")
	public ModelAndView viewTempUser(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		Map dataMap = commonService.loadSingleData("business_user-sql", "getTempUserInfor",paramMap);
		paramMap.put("tempUser",dataMap);
		
		return getModelAndView(subPath, "viewTempUser", paramMap);
	}
	
	
	/**
	 * viewTempUserSave
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/viewTempUserSave")
	public void viewTempUserSave(HttpServletResponse res,HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
			commonService.saveOrUpdate("business_user-sql", "updateTempUser",paramMap);
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
	 * sendTempUserInforToEmail
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/sendTempUserInforToEmail")
	public void sendTempUserInforToEmail(HttpServletResponse res,HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
			
			//加载项目信息
			String proId = (String) paramMap.get("proId");
			paramMap.put("id", proId);
			Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
			
			//加载临时帐户信息
			paramMap.put("proId", proId);
			Map tempUserInfor = commonService.loadSingleData("business_user-sql", "getTempUserInfor",paramMap);
			
			String proName = (String) proInfor.get("proName");
			String username = (String) tempUserInfor.get("username");
			String password = (String) tempUserInfor.get("password");
			String toEmail = (String) paramMap.get("receiveEmail");
			
			StringBuffer emailContent = new StringBuffer();
			emailContent.append("用户名："+username+"<br>");
			emailContent.append("密码："+password+"<br>");
			
			emailContent.append("访问连接："+PropertyConfigurerSon.getContextProperty("web.address")+"/sysbase/checkLogin.c?username="+username+"&password="+password+"<br>");
			
			
			
			//PropertyConfigurerSon
			EmailUtils.getInstance().sendEmail("项目["+proName+"]审查进度查看，帐户和密码", emailContent.toString(), toEmail);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
	
	@Resource(name = "wwwService")
	private WwwService wwwService;
	
	/**
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/main")
	public ModelAndView main(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("ws", wwwService);
		
		
		paramMap.put("curLoginUserId", CtrlUtils.getSessionUserId(request));
		paramMap.put("sessionUser", CtrlUtils.getSessionUserInforMap(request));
		return getModelAndView("/oa/", "main", paramMap);
	}
}
