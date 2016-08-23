/**
 * 
 */
package com.css.utils.cache.sql;

import java.io.File;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeanUtils;

import com.css.utils.LodXmlUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class SqlContextCache implements Serializable {
	protected final Logger log = Logger.getLogger(getClass());
	private static SqlContextCache sqlContext = null;
	//Query SqlFile缓存
	public static Map<String,Map<String,QuerySqlSupport>> querySqlFileMap;
	private SqlContextCache() {
		init();
	}

	public synchronized static SqlContextCache getInsContext() {
		if (sqlContext == null) {
			sqlContext = new SqlContextCache();
		}
		return sqlContext;
	}

	private void init() {
		querySqlFileMap = new HashMap<String,Map<String,QuerySqlSupport>>();
		Iterator<File> files = LodXmlUtil.getFolderFiles("config/apps/");
		String sqlId;
		while (files.hasNext()) {
			File f = files.next();
			Map<String,QuerySqlSupport> querySqlMap = readXmlFile(f);
			if(f!=null){
				sqlId = f.getName().replace(".xml", "");
				querySqlFileMap.put(sqlId, querySqlMap);
			}
		}
	}
	public Map<String,QuerySqlSupport> readXmlFile(File file){
		Map<String,QuerySqlSupport> querySqlMap = new HashMap<String,QuerySqlSupport>();
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(file);
			List sqls = document.selectNodes("//query-infor//query");
			for(Iterator queryIt = sqls.iterator();queryIt.hasNext();){
				QuerySqlSupport querySqlObj = new QuerySqlSupport();
				Element queryEle = (Element)queryIt.next();
				String sqlType = queryEle.attributeValue("type");//saveOrUpdate、search
				String id = queryEle.attributeValue("id");
				String sql = queryEle.selectSingleNode("sql").getText();
				querySqlObj.setSql(sql);
				querySqlObj.setType(sqlType);
				List<SqlParm> sqlParmList = new ArrayList<SqlParm>();
				List sqlParms = queryEle.elements("sqlParm");
				for(Iterator sqlParmIt = sqlParms.iterator();sqlParmIt.hasNext();){
					Element sqlParmEle = (Element)sqlParmIt.next();
					String binding = sqlParmEle.attributeValue("binding");//字段
					String type = sqlParmEle.attributeValue("type");//数据类型
					String defaultValue = sqlParmEle.attributeValue("defaultValue");//默认值
					String formatter = sqlParmEle.attributeValue("formatter");//格式化
					SqlParm sqlParm = new SqlParm();
					sqlParm.setBinding(binding);
					sqlParm.setType(type);
					sqlParm.setDefaultValue(defaultValue);
					sqlParm.setFormatter(formatter);
					sqlParmList.add(sqlParm);
				}
				querySqlObj.setSqlParms(sqlParmList);
				querySqlMap.put(id, querySqlObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return querySqlMap;
	}
	
	public QuerySqlSupport getQuerySql(String sqlFileName,String queryId,Map<String,Object> conditions){
		Map<String,QuerySqlSupport> querySqlMap = querySqlFileMap.get(sqlFileName);
		QuerySqlSupport querySqlSupport = querySqlMap.get(queryId);
		QuerySqlSupport returnQuqerSS = new QuerySqlSupport();
		BeanUtils.copyProperties(querySqlSupport,returnQuqerSS);
		if("search".equalsIgnoreCase(querySqlSupport.getType())){
			String sql = returnQuqerSS.getSql();
			//--freemarker sql模板解析---start
			String processedQuerySql = "";
			try {
				Configuration cfg = new Configuration();
				cfg.setTemplateLoader(new SqlTemplateLoader(sql));
				cfg.setDefaultEncoding("UTF-8");
				Template template;
				template = cfg.getTemplate("");
				StringWriter writer = new StringWriter();
				template.process(conditions, writer);
				processedQuerySql = writer.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//--freemarker sql模板解析---end
			returnQuqerSS.setSql(processedQuerySql);
		}
		return returnQuqerSS;
	}
	
	public void refresh() {
		synchronized (sqlContext) {
			this.init();
		}
	}
}
