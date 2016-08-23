package com.css.sysbase.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.service.CommonService;
import com.css.utils.SysConstants;
import com.css.utils.web.PageInfor;

@Service("commonService")
public class CommonServiceImpl implements CommonService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;

	/**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public List<Map> loadData(String sqlFileName,String queryId,Map<String,Object> conditions) {
    	return commonManager.executeSql(sqlFileName, queryId, conditions);
    }
    
    /**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public Map loadSingleData(String sqlFileName,String queryId,Map<String,Object> conditions){
    	List<Map> dataList = commonManager.executeSql(sqlFileName, queryId, conditions);
    	if(dataList.size()>0){
    		return dataList.get(0);
    	}else{
    		return null;
    	}
    }
    /**
     * 根据原生SQL查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @param pageInfor : 分页对象
     * @return
     */
    public PageInfor loadDataByPager(String sqlFileName,String queryId,Map conditions,PageInfor pageInfor) {
    	return commonManager.findListBySqlQuery(sqlFileName, queryId, conditions, pageInfor);
    }
    /**
     * 执行原生SQL，保存、更新
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
	@Override
	public int saveOrUpdate(String sqlFileName, String queryId,
			Map<String, Object> conditions) {
		return commonManager.saveOrUpdate(sqlFileName, queryId, conditions);
	}
	
	/**
     * 执行原生SQL语句，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public List<Map> executeRawSql(String sql) {
    	return commonManager.executeRawSql(sql);
    }
	
}
