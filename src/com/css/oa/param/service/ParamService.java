package com.css.oa.param.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface ParamService {

	/**
	 * getParamValueByCode
	 * @param request
	 * @throws Exception
	 */
	public String getParamValueByCode(String paramCode);

}
