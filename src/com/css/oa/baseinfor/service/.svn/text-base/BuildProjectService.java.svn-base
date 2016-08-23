package com.css.oa.baseinfor.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface BuildProjectService {

	/**
	 * 保存
	 * @param request
	 * @throws Exception
	 */
	public Map save(HttpServletRequest request) throws Exception;
	
	/**
	 * 受理-更新
	 * @param request
	 * @throws Exception
	 */
	public Map update(HttpServletRequest request) throws Exception;
	/**
	 * 工程填报-更新
	 * @param request
	 * @throws Exception
	 */
	public Map updateBuildProjectSave(HttpServletRequest request) throws Exception;
	
	/**
	 * 多项删除
	 * @param request
	 * @throws Exception
	 */
	public void delMulti(HttpServletRequest request) throws Exception;
	
	/**
	 * 单项删除
	 * @param request
	 * @throws Exception
	 */
	public void del(String id) throws Exception;
	
	/**
	 * 保存“送审资料”
	 * @param request
	 * @throws Exception
	 */
	public void saveApprovalInfor(List<String> approvalList) throws Exception;
	
	/**
	 * 工程受理-提交
	 * @param request
	 * @throws Exception
	 */
	public void saveGcslSubmit(String proId) throws Exception;
	
}
