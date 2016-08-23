package com.css.oa.feedback.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FeedBackService {

	/**
	 * 设计院回复页面-专业审核-提交
	 */
	public Map saveFeedBackMajorSubmit(String proId,String majorId,String applyStatus);
	
	public void feedBackDetailSubSave(HttpServletRequest request) throws Exception;
	
	
}
