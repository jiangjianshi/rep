package com.css.sysbase.service;

import java.util.List;
import java.util.Map;

public interface WwwService {

	/**
	 * 根据sql获得数据列表
	 * @param sqlFileName
	 * @param queryId
	 * @param sqlParam
	 * @return
	 */
	public List<Map> getDataList(String sqlFileName,String queryId,String sqlParam);
	/**
	 * 根据sql获得数据对象
	 * @param sqlFileName
	 * @param queryId
	 * @param sqlParam
	 * @return
	 */
	public Map getDataObject(String sqlFileName,String queryId,String sqlParam);
	
	public boolean isHaveRightByUserId(String userId,String rightCode);

}
