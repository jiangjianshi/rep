package com.css.utils;

import java.util.HashMap;
import java.util.Map;

public class WwwUtils {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map resolveParams(String sqlParam){  
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String[] sqlParam_arr = sqlParam.split("&");
		for (String param : sqlParam_arr) {
			String[] param_arr = param.split("=");
			if(param_arr[0].indexOf("[")==0){
				param_arr[0] = param_arr[0].substring(1, param_arr[0].length()-1);
				paramMap.put(param_arr[0], Integer.parseInt(param_arr[1]));
			}else{
				paramMap.put(param_arr[0], param_arr[1]);
			}
		}
		return paramMap;
    }  
}
