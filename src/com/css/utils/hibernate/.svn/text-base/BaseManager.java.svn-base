package com.css.utils.hibernate;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseManager<T> {
	private static final long serialVersionUID = -5007246485129055012L;
	 @Autowired  
    private SessionFactory sessionFactory;  
	 
	private Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	/**
     * 插入
     * @param obj
     * @return
     */
    public String save(T entity) throws Exception{
    	return sessionFactory.getCurrentSession().save(entity).toString();
    }
    
    /**
     * 更新 或 插入
     * @param obj
     * @return
     */
    public void saveOrUpdate(T entity) throws Exception{
    	sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }
 
    /**
     * 更新
     * @param obj
     */
    public void update(T entity) {
    	sessionFactory.getCurrentSession().update(entity);
    }
    
    /**
     * 根据主键查询实体
     * @param pkId
     * @return
     */
    public T get(String pkId) {
        return (T) sessionFactory.getCurrentSession().get(getPersistentClass(), pkId);
    }
 
    /**
     * 根据主键删除实体
     * @param pkId
     */
    public void deleteById(String pkId) {
        Object obj = get(pkId);
        sessionFactory.getCurrentSession().delete(obj);
    }
 
    /**
     * 删除实体
     * @param obj
     */
    public void delete(T obj) {
    	 sessionFactory.getCurrentSession().delete(obj);
    }
    
    /**
     * 根据参数params获得数据库中某张表的数据
     * @param params
     * @param claz
     * @return
     */
    public List<T> getEntityByProperties(Map params){
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
    	Set<Entry> entries = params.entrySet();
		for(Entry e : entries){
			String parmName = String.valueOf(e.getKey());
			Object parmValue = e.getValue();
			criteria.add(Restrictions.eq(parmName, parmValue));
		}
    	return (List<T>)criteria.list();
    }
    
    /**
     * 查询所有记录
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(getPersistentClass());
    	return criteria.list();
    }
	
}
