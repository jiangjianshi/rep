package com.css.oa.govopinion.service.impl;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.oa.govopinion.service.GovopinionService;
import com.css.sysbase.service.CommonService;

@Service("govopinionService")
public class GovopinionServiceImpl implements GovopinionService {
	
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
