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
import com.css.sysbase.service.SysBaseService;
import com.css.utils.cache.sql.SqlContextCache;
import com.css.utils.cache.web.LoginSessionCache;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;

@Controller
@RequestMapping("/sysbase")
public class ParamSetCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "sysbase/";

	@Resource(name = "commonService")
	private CommonService commonService;

	@Resource(name = "sysBaseService")
	private SysBaseService sysBaseService;

	/**
	 * 重新加载sql文件
	 * 
	 * @param res
	 * @param request
	 */
	@RequestMapping("/reloadSqlFile")
	public void reloadSqlFile(HttpServletResponse res, HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			SqlContextCache.getInsContext().refresh();
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
