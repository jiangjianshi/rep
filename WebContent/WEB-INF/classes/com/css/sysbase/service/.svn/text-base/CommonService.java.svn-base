package com.css.sysbase.service;

import java.util.List;
import java.util.Map;

import com.css.utils.web.PageInfor;

public interface CommonService {

	/**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public List<Map> loadData(String sqlFileName,String queryId,Map<String,Object> conditions);
    /**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public Map loadSingleData(String sqlFileName,String queryId,Map<String,Object> conditions);
   
    /**
     * 执行原生SQL，保存、更新
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public int saveOrUpdate(String sqlFileName,String queryId,Map<String,Object> conditions);
    /**
     * 根据原生SQL查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @param pageInfor : 分页对象
     * @return
     */
    public PageInfor loadDataByPager(String sqlFileName,String queryId,Map conditions,PageInfor pageInfor);
    
    public List<Map> executeRawSql(String sql);
}
