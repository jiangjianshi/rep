package com.css.sysbase.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.sysbase.service.CommonService;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.PageInfor;

@Controller
@RequestMapping("/commonCtrl")
public class CommonCtrl extends BaseController {

	@Resource(name = "commonService")
	private CommonService commonService;

	/**
	 * 
	 * 描述 :加载数据
	 * author:syw
	 */
	@RequestMapping("/loadData")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadData(Integer pageSize, String t, String s,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		List<Map> list = commonService.loadData(t, s, paramMap);
		CtrlUtils.putJSON(list, null, response);
	}
	
	/**
	 * 
	 * 描述 :加载数据
	 * author:syw
	 */
	@RequestMapping("/loadSingleData")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadSingleData(String t, String s,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		Map singleData = commonService.loadSingleData(t, s, paramMap);
		CtrlUtils.putJSON(singleData, null, response);
	}
	
	/**
	 * 
	 * 描述 :easyUI datagrid-数据源 
	 * author:syw
	 */
	@RequestMapping("/loadByPage")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void loadByPage(Integer pageSize, String t, String s,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		// 设置分页信息
		PageInfor pageInfo = new PageInfor();
//		pageInfo.setPageSize(pageSize);
		pageInfo.setPageSizeAndPageNumber(request);
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		pageInfo = commonService.loadDataByPager(t, s, paramMap,pageInfo);
		// 页面输出信息
		Map map = new HashMap(2);
		map.put("total", "" + pageInfo.getTotalRows());
		map.put("rows", pageInfo.getDatas());
		CtrlUtils.putMapJSONToClient(map, response);
	}
	
	/**
	 * 基于Sql的更新、插入操作
	 * author:syw
	 * @param res
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/saveOrUpate")
	public void saveOrUpate(String t, String s, HttpServletResponse res,
			HttpServletRequest request) {
		Map map = new HashMap(2);// 页面输出信息
		try {
			Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
			int n = commonService.saveOrUpdate(t, s, paramMap);
			map.put("isSuccess", true);
			map.put("msg", "操作成功！" + "影响了" + n + "条数据");
		} catch (Exception e) {
			map.put("isSuccess", false);
			map.put("msg", "操作失败！");
			e.printStackTrace();
		}
		CtrlUtils.putMapJSONToClient(map, res);
	}
}
