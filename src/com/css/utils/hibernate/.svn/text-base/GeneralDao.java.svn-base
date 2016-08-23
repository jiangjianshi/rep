package com.css.utils.hibernate;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;

import com.css.utils.cache.sql.QuerySqlSupport;
import com.css.utils.cache.sql.SqlContextCache;
import com.css.utils.cache.sql.SqlParm;
import com.css.utils.cache.sql.SqlParmUtils;
import com.css.utils.web.PageInfor;
 
public class GeneralDao implements
        Serializable {
    private static final long serialVersionUID = 3986291569067541566L;
    private static final Log log = LogFactory.getLog(GeneralDao.class);
    @Autowired  
    private SessionFactory sessionFactory;  
    private List<Field> tableFields;
 
    @SuppressWarnings("unchecked")
    public GeneralDao() {
        super();
    }
 
    /**
     * 插入
     * @param obj
     * @return
     */
    public String save(Object obj) throws Exception{
        return sessionFactory.getCurrentSession().save(obj).toString();
    }
    
    /**
     * 更新 或 插入
     * @param obj
     * @return
     */
    public void saveOrUpdate(Object obj) throws Exception{
    	sessionFactory.getCurrentSession().saveOrUpdate(obj);
    }
 
    /**
     * 更新
     * @param obj
     */
    public void update(Object obj) {
    	sessionFactory.getCurrentSession().update(obj);
    }
 
    /**
     * 根据参数params获得数据库中某张表的数据
     * @param params
     * @param claz
     * @return
     */
    public Object getEntityByProperties(Map params,Class claz){
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(claz);
    	Set<Entry> entries = params.entrySet();
		for(Entry e : entries){
			String parmName = String.valueOf(e.getKey());
			Object parmValue = e.getValue();
			criteria.add(Restrictions.eq(parmName, parmValue));
		}
    	return criteria.uniqueResult();
    }
 
    /**
     * 根据主键查询实体
     * @param pkId
     * @return
     */
    public Object get(String pkId,Class claz) {
        return (Object) sessionFactory.getCurrentSession().get(claz, pkId);
    }
 
    /**
     * 根据主键删除实体
     * @param pkId
     */
    public void deleteById(String pkId,Class claz) {
        Object obj = get(pkId,claz);
        sessionFactory.getCurrentSession().delete(obj);
    }
 
    /**
     * 删除实体
     * @param obj
     */
    public void delete(Object obj) {
    	 sessionFactory.getCurrentSession().delete(obj);
    }
 
    /**
     * 查询所有记录
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Object> getAll(Class claz) {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(claz);
    	return criteria.list();
    }
 
 
    /**
     * 查询与obj中非空项相等的记录条数
     * 
     * @param obj
     * @return
     */
    public Long getCountEqNotNullField(Object obj,Class claz) {
        return getCount(getDetachedCriteriaEqNotNullField(obj,claz));
    }
 
 
    /**
     * 获得查询条件对象，并对其填充obj的非null项等值查询条件
     * 
     * @param obj
     * @return
     */
    protected DetachedCriteria getDetachedCriteriaEqNotNullField(Object obj,Class claz) {
 
        DetachedCriteria dc = DetachedCriteria.forClass(claz);
 
        if (obj != null) {
            for (Field field : tableFields) {
                try {
                    Object fieldValue = field.get(obj);
                    if (fieldValue != null) {
                        HQLUtils.addEq(dc, field.getName(), fieldValue);
                    }
                } catch (Exception e) {
                    log.error(
                            claz.getName() + "." + field.getName() + "属性取值错误",
                            e);
                }
            }
        }
 
        return dc;
    }
 
 
    /**
     * 根据条件获得记录总数
     * 
     * @param dc
     * @return
     */
    protected Long getCount(DetachedCriteria dc) {
        Criteria criteria = dc.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.setProjection(Projections.rowCount());
        Object results = criteria.uniqueResult();
        return Long.valueOf(results.toString());
    }
    
    /**
     * 根据原生SQL查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @param pageInfor : 分页对象
     * @return
     */
    public PageInfor findListBySqlQuery(String sqlFileName,String queryId,Map conditions,PageInfor pageInfor) {
    	//从服务器内存中读取缓存好的Sql
    	QuerySqlSupport querySqlSupport = SqlContextCache.getInsContext().getQuerySql(sqlFileName, queryId, conditions);
    	String sql = querySqlSupport.getSql();
    	Session session = sessionFactory.getCurrentSession();
    	//避免在同一事务中进行原生SQL查询时session的缓存，需要清空缓存
    	session.flush();
    	session.clear();
    	String querySql;
    	//排序判断
    	if(pageInfor.getSort()!=null){
    		//对SQL进行排序语法包装
    		querySql = setQuerySortSql(sql,pageInfor);
    	}else{
    		querySql = sql;
    	}
    	//获得查询对象
    	SQLQuery queryObject = session.createSQLQuery(querySql);
    	if("search".equalsIgnoreCase(querySqlSupport.getType())){
    		//SQL参数化
        	queryObject = this.setParameterForSearchQuery(queryObject, querySqlSupport, conditions);
		}
		//将结果转换为Map对象类型
    	queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    	//设置分页信息
		pageInfor.setTotalRows(getRowCountBySqlQuery(querySqlSupport,conditions));
		queryObject.setFirstResult(pageInfor.getCurrentRow());
		queryObject.setMaxResults(pageInfor.getPageSize());
		
    	List dataList = queryObject.list();
    	pageInfor.setDatas(dataList);
        return pageInfor;
    }
    
    /**
     * 为Sql语句包装排序条件
     * @param sql
     * @param pageInfor 中包含排序条件 sort、order
     * @return
     */
    protected String setQuerySortSql(String sql,PageInfor pageInfor){
    	String syh = "";
    	//如果sql中，为as "columnName" 模式，order by 后的列也要加上",如 order by "columnName" 
    	if(sql.contains("\"")){
    		//syh = "\"";
    	}
    	StringBuilder executeSql = new StringBuilder("select * from (").append(sql).append(") t_order ");
    	String sortStr = "";
    	if(pageInfor.getSort()!=null){
    		sortStr = " order by ";
    	}
    	if(pageInfor.getSort()!=null){
    		sortStr += " t_order."+syh+pageInfor.getSort()+syh;
    		if(pageInfor.getOrder()!=null){
    			sortStr += " "+pageInfor.getOrder();
        	}
    	}else{
    		if(sortStr.length()>0){
    			sortStr = sortStr.substring(0, sortStr.length()-1);
    		}
    	}
    	return executeSql.toString()+sortStr.toString();
    }
    /**
     * 根据原生SQL查询结果集totalRows
     * @param querySql
     * @return
     */
    public int getRowCountBySqlQuery(QuerySqlSupport querySqlSupport,Map<String,String> conditions) {
    	Session session = sessionFactory.getCurrentSession();
    	session.flush();
    	session.clear();
    	String sql = querySqlSupport.getSql();
    	String rowCountSql = new StringBuilder("select count(1) as COUNT_ from (")
    					.append(sql).append(") _sqlCount").toString();
    	SQLQuery queryObject = session.createSQLQuery(rowCountSql);
    	if("search".equalsIgnoreCase(querySqlSupport.getType())){
    		//SQL参数化
        	queryObject = this.setParameterForSearchQuery(queryObject, querySqlSupport, conditions);
		}
    	queryObject.addScalar("COUNT_", IntegerType.INSTANCE);
        return new Integer(queryObject.uniqueResult().toString()).intValue();
    }
   
    /**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public List<Map> executeSql(String sqlFileName,String queryId,Map<String,Object> conditions) {
    	//从服务器内存中读取缓存好的Sql
    	QuerySqlSupport querySqlSupport = SqlContextCache.getInsContext().getQuerySql(sqlFileName, queryId, conditions);
    	String sql = querySqlSupport.getSql();
    	Session session = sessionFactory.getCurrentSession();
    	//避免在同一事务中进行原生SQL查询时session的缓存，需要清空缓存
    	session.flush();
    	session.clear();
    	SQLQuery queryObject = session.createSQLQuery(sql);
    	if("search".equalsIgnoreCase(querySqlSupport.getType())){
    		//SQL参数化
        	queryObject = this.setParameterForSearchQuery(queryObject, querySqlSupport, conditions);
		}
    	queryObject.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return queryObject.list();
    }
    
    /**
     * 执行纯SQL更新语句，返回更新的数据条数
     * @param updateId
     * @param conditions
     * @return
     */
    public int saveOrUpdate(String sqlFileName,String queryId,Map<String,Object> conditions){
    	//从服务器内存中读取缓存好的Sql
    	QuerySqlSupport querySqlSupport = SqlContextCache.getInsContext().getQuerySql(sqlFileName, queryId, conditions);
    	String sql = querySqlSupport.getSql();
    	Session session = sessionFactory.getCurrentSession();
    	//避免在同一事务中进行原生SQL查询时session的缓存，需要清空缓存
    	session.flush();
    	session.clear();
    	SQLQuery queryObject = session.createSQLQuery(sql);
    	//SQL参数化
    	queryObject = this.setParameterForSaveOrUpdate(queryObject, querySqlSupport, conditions);
        return queryObject.executeUpdate();
    }
    
    /**
     * SQL参数化 for SaveOrUpdate
     * @param queryObject
     * @param querySql
     * @param conditions
     * @return
     */
    public SQLQuery setParameterForSaveOrUpdate(SQLQuery queryObject,
    		QuerySqlSupport querySqlSupport, Map conditions) {
    	List<SqlParm> sqlParms = querySqlSupport.getSqlParms();
    	if(sqlParms.size()>0){
    		int paramIndex = 0;
    		for (SqlParm sqlParm : sqlParms) {
    			paramIndex = SqlParmUtils.setSqlParmValue(sqlParm,queryObject, paramIndex,conditions);
			}
    	}
    	return setParameterForSearchQuery(queryObject,querySqlSupport,conditions);
	}
    
    /**
     * SQL参数化 for 查询
     * @param queryObject
     * @param querySql
     * @param conditions
     * @return
     */
    public SQLQuery setParameterForSearchQuery(SQLQuery queryObject,
    		QuerySqlSupport querySqlSupport, Map conditions) {
		Pattern p = Pattern.compile(":(\\w+)");
		Matcher m = p.matcher(querySqlSupport.getSql());
		String paraName;
		while (m.find()) {
			paraName = m.group(1);
			if (conditions.get(paraName) != null) {
				if (conditions.get(paraName) instanceof String[]) {
					queryObject.setParameterList(paraName,
							(String[]) conditions.get(paraName));
				}
				// where in list
				else if (conditions.get(paraName) instanceof List) {
					queryObject.setParameterList(paraName,
							(List) conditions.get(paraName));
				}else{
					queryObject.setParameter(paraName,
							conditions.get(paraName));
				}
			}
		}
		return queryObject;
	}
    
    /**
     * 执行原生SQL，并返回查询结果集
     * @param queryId : 在xml文件中唯一标识的一段SQL语句 id
     * @param conditions : 查询条件
     * @return
     */
    public List<Map> executeRawSql(String sql) {
    	//从服务器内存中读取缓存好的Sql
    	Session session = sessionFactory.getCurrentSession();
    	//避免在同一事务中进行原生SQL查询时session的缓存，需要清空缓存
    	session.flush();
    	session.clear();
    	SQLQuery queryObject = session.createSQLQuery(sql);
        return queryObject.list();
    }
    
}