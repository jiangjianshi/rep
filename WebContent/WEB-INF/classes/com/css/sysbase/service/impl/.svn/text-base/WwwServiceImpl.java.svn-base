package com.css.sysbase.service.impl;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.service.RoleService;
import com.css.sysbase.service.WwwService;
import com.css.utils.WwwUtils;

@Service("wwwService")
public class WwwServiceImpl implements WwwService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "roleService")
	private RoleService roleService;

	/**
	 * 根据sql获得数据列表
	 * @param sqlFileName
	 * @param queryId
	 * @param sqlParam
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> getDataList(String sqlFileName,String queryId,String sqlParam) {
		return commonManager.executeSql(sqlFileName,queryId, WwwUtils.resolveParams(sqlParam));
	}

	@Override
	public Map getDataObject(String sqlFileName, String queryId,String sqlParam) {
		List<Map> dataList = commonManager.executeSql(sqlFileName, queryId, WwwUtils.resolveParams(sqlParam));
    	if(dataList.size()>0){
    		return dataList.get(0);
    	}else{
    		return null;
    	}
	}

	@Override
	public boolean isHaveRightByUserId(String userId, String rightCode) {
		// TODO Auto-generated method stub
		return roleService.isHaveRightByUserId(userId, rightCode);
	}
}
