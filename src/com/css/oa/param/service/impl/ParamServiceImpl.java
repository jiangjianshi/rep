package com.css.oa.param.service.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.oa.check.service.CheckService;
import com.css.oa.check.service.FlowService;
import com.css.oa.param.service.ParamService;
import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.service.CommonService;
import com.css.utils.TimeUtils;

@Service("paramService")
public class ParamServiceImpl implements ParamService {
	
	@Resource(name = "commonService")
	private CommonService commonService;

	public String getParamValueByCode(String paramCode){
		Map paramMap = new HashMap();
		paramMap.put("paramCode",paramCode);
		Map dataMap = commonService.loadSingleData("fn_param_sql", "getParamValueByCode", paramMap);
		String value = null;
		if(dataMap!=null){
			value = (String) dataMap.get("param_value");
		}
		return value;
	}
	
}
