package com.css.utils.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class BaseController{
    /**日志对象*/
    protected final Logger log = Logger.getLogger(getClass());
    
    public ModelAndView getModelAndView(String subPath, String viewFileName, Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<String, Object>(1);
        }
        map.put("subPath", subPath);
        map.put("fileName", viewFileName);
        return new ModelAndView(subPath + viewFileName, map);
    }
}