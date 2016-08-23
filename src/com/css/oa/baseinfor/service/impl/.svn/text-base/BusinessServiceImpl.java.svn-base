package com.css.oa.baseinfor.service.impl;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.oa.baseinfor.service.BusinessService;
import com.css.oa.check.service.FlowService;
import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.service.CommonService;
import com.css.sysbase.service.UserService;
import com.css.utils.EmailUtils;
import com.css.utils.PropertyConfigurerSon;

@Service("businessService")
public class BusinessServiceImpl implements BusinessService {
	
	@Resource(name = "commonService")
	private CommonService commonService;

	@Override
	public void sendTempUserInforToEmail(String proId,String tipMsg){
		Map paramMap = new HashMap();
		
		//加载项目信息
		paramMap.put("id", proId);
		Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		
		//加载临时帐户信息
		paramMap.put("proId", proId);
		Map tempUserInfor = commonService.loadSingleData("business_user-sql", "getTempUserInfor",paramMap);
		
		String proName = (String) proInfor.get("proName");
		String username = (String) tempUserInfor.get("username");
		String password = (String) tempUserInfor.get("password");
		String toEmail = (String) tempUserInfor.get("receiveEmail");
		
		StringBuffer emailContent = new StringBuffer();
		emailContent.append("用户名："+username+"<br>");
		emailContent.append("密码："+password+"<br>");
		emailContent.append("<font color=red>"+tipMsg+"</font>");
		emailContent.append("访问连接："+PropertyConfigurerSon.getContextProperty("web.address")+"/sysbase/checkLogin.c?username="+username+"&password="+password+"<br>");
		
		EmailUtils.getInstance().sendEmail("项目["+proName+"]审查进度:"+tipMsg, emailContent.toString(), toEmail);
	}
	
	
}
