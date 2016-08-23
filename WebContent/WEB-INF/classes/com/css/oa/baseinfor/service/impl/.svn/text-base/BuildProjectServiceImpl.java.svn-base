package com.css.oa.baseinfor.service.impl;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.oa.baseinfor.service.BuildProjectService;
import com.css.oa.check.service.FlowService;
import com.css.oa.utils.OaConstants;
import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.dao.SysUserManager;
import com.css.sysbase.entity.SysUser;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.UserService;
import com.css.utils.web.CtrlUtils;

@Service("buildprojectService")
public class BuildProjectServiceImpl implements BuildProjectService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "flowService")
	private FlowService flowService;
	
	@Resource(name = "userService")
	private UserService userService;
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "sysUserManager")
	private SysUserManager sysUserManager;
	
	@Override
	public Map save(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String buildPro_pkId = String.valueOf(UUID.randomUUID());
		paramMap.put("pkId", buildPro_pkId);
		paramMap.put("acceptUser", null);
		paramMap.put("receiveTime",null);
		paramMap.put("acceptTime", null);
		paramMap.put("flowStatus", OaConstants.proFlowStatus_1);//项目状态-填报中
		paramMap.put("createUser", CtrlUtils.getSessionUserId(request));//填报人
		paramMap.put("createTime", new Date());//创建时间
		int n = commonManager.saveOrUpdate("build_pro-sql", "addObj",paramMap);
		
		//增加一条“项目情况”记录
		String buildProStat_pkId = String.valueOf(UUID.randomUUID());
		paramMap.put("buildProStat_pkId", buildProStat_pkId);
		paramMap.put("repCode", buildPro_pkId);
		commonManager.saveOrUpdate("build_stat-sql", "addObj",paramMap);
		
		//为该工程追加N条“送审资料”
		Map conditions = new HashMap();
		conditions.put("proType", "buildPro");
		List<Map> projectFileList = commonManager.executeSql("project_file-sql", "searchList", conditions);
		for (Map file : projectFileList) {
			String proFileId = (String) file.get("proFileId");
			conditions.put("approvalInforId", String.valueOf(UUID.randomUUID()));
			conditions.put("proId",buildPro_pkId);
			conditions.put("proFileId",proFileId);
			conditions.put("approvalState","");
			commonManager.saveOrUpdate("approval_infor-sql", "addObj",conditions);
		}
		map.put("n", n);
		return map;
	}
	
	
	@Override
	public Map update(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = request.getParameter("pkId");
		flowService.updateProFlowStatus(proId, OaConstants.proFlowStatus_2);
		int n = commonManager.saveOrUpdate("build_pro-sql", "updateObj",paramMap);
		map.put("n", n);
		return map;
	}
	
	@Override
	public Map updateBuildProjectSave(HttpServletRequest request) throws Exception {
		Map map = new HashMap(2);// 页面输出信息
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("acceptUser", null);
		paramMap.put("receiveTime",null);
		paramMap.put("acceptTime", null);
		int n = commonManager.saveOrUpdate("build_pro-sql", "updateObj",paramMap);
		map.put("n", n);
		return map;
	}
	
	/**
	 * 多项删除
	 * @param request
	 * @throws Exception
	 */
	public void delMulti(HttpServletRequest request) throws Exception{
		String delIds = request.getParameter("delIds");
		String[] delIds_arr = delIds.split(",");
		for (String id : delIds_arr) {
			del(id);
		}
	}
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String delId) throws Exception{
		Map conditions = new HashMap();
		conditions.put("delId", delId);
		commonManager.saveOrUpdate("build_pro-sql", "delObj", conditions);
	}
	
	/**
	 * 保存“送审资料”
	 * @param request
	 * @throws Exception
	 */
	public void saveApprovalInfor(List<String> approvalList) throws Exception{
		Map conditions = new HashMap();
		for (String approval : approvalList) {
			String[] appr_arr = approval.split("_=-=_");
			conditions.put("approvalState", appr_arr[1]);
			conditions.put("approvalInforId", appr_arr[0]);
			commonManager.saveOrUpdate("approval_infor-sql", "updateObj", conditions);
		}
	}
	
	
	/**
	 * 工程受理-提交
	 * @param request
	 * @throws Exception
	 */
	public void saveGcslSubmit(String proId) throws Exception{
		//更新为“待分工”"
		flowService.updateProFlowStatus(proId,OaConstants.proFlowStatus_3);
		
		//---工程受理时添加一个临时帐户---start
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x+100000;
		
		SysUser u = new SysUser();
		String userName = "Account"+c.getTimeInMillis();
		u.setUserName(userName);
		u.setPassword(x+"");
		u.setRealName(userName);
		u.setDepId("sjy_dep");
		u.setProId(proId);
		u.setProType(null);
		sysUserManager.save(u);
		userService.saveUserRole(u.getUserId(),"sjylszh");//赋予角色“设计院临时帐户角色”
		//---工程受理时添加一个临时帐户---end
		
		//---工程受理时，自动向合同表写入初始数据  start
		Map proQuery = new HashMap<>();
		proQuery.put("proId", proId);
		Map proMap = commonService.loadSingleData("fn_con_sql", "getPorInfoForId", proQuery);
		Map proStatMap = commonService.loadSingleData("fn_con_sql", "getPorStatForId", proQuery);
		
		Map conMap = new HashMap<>();
		conMap.put("pro_number", proMap.get("pro_number"));
		conMap.put("fn_id",String.valueOf(UUID.randomUUID()));
		conMap.put("con_amt", proStatMap.get("stat_exam_amt"));
		conMap.put("con_sta", "00");
		commonManager.saveOrUpdate("fn_con_sql", "addContract", conMap);
		//---工程受理时，自动向合同表写入初始数据  start
		
		
	}
	
	
}
