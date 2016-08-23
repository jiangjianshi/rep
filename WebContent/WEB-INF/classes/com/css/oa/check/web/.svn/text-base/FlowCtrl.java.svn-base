package com.css.oa.check.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.oa.check.service.FlowService;
import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/oa/check")
public class FlowCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/check/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "flowService")
	private FlowService flowService;
	
	/**
	 * 改变项目流程状态
	 * @param res
	 * @param request
	 */
	@RequestMapping("/updateProFlowStatus")
	public void updateProFlowStatus(HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap();// 页面输出信息
		try {
			String proId = request.getParameter("proId");
			String flowStatus = request.getParameter("flowStatus");
			flowService.updateProFlowStatus(proId,flowStatus);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "系统异常，操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
	
}
