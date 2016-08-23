package com.css.utils.cache.sql;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;

import com.css.utils.hibernate.GeneralDao;

public class SqlParmUtils {
	 private static final Log log = LogFactory.getLog(SqlParmUtils.class);
	public static int setSqlParmValue(SqlParm sqlParm, SQLQuery queryObject, int paramIndex, Map conditions) {
		String paramType = sqlParm.getType();
		String formate = sqlParm.getFormatter();
		String binding = sqlParm.getBinding();
		
		Object paramObjValue = null;
		if(conditions.get(sqlParm.getBinding())!=null){
			//取map中的值
			paramObjValue = conditions.get(sqlParm.getBinding());
		}else{
			//取默认值
			paramObjValue = sqlParm.getDefaultValue();
		}
		
		if("[PrimaryKey]".equalsIgnoreCase(sqlParm.getDefaultValue())){
			//生成主键
			paramObjValue=String.valueOf(UUID.randomUUID());
			queryObject.setParameter(paramIndex++, paramObjValue);
		}else{
			//如果Map中的参数值为null,则取默认值
			if(conditions.get(sqlParm.getBinding()) == null){
				if("[systemDate]".equalsIgnoreCase(sqlParm.getDefaultValue())){
					//系统时间
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh24:mm:ss");
					queryObject.setString(paramIndex++, sdf.format(new Date()));
				}else{
					queryObject.setParameter(paramIndex++, paramObjValue);
				}
			}else{
				if(paramType.equals("java.lang.String")){
					queryObject.setString(paramIndex++, paramObjValue.toString());
				}else if(paramType.equals("java.lang.String[]")){
					String paramStrValue = String.valueOf(paramObjValue);
					String[] tempStr_arr = paramStrValue.split(",");
					queryObject.setParameterList(binding, tempStr_arr);
				}else if(paramType.equals("java.util.Date")){
					if(paramObjValue instanceof java.util.Date){
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						queryObject.setString(paramIndex++, sdf.format((Date)paramObjValue));
					}else if(paramObjValue instanceof String){
						queryObject.setString(paramIndex++, (String)paramObjValue);
					}
				}else if(paramType.equals("java.math.BigDecimal")){
					BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(paramObjValue));
					queryObject.setBigDecimal(paramIndex++, bigDecimalValue);
				}else if(paramType.equals("java.lang.Boolean")){
					queryObject.setBoolean(paramIndex++, Boolean.valueOf(String.valueOf(paramObjValue)));
				}else if(paramType.equals("java.lang.Integer")){
					queryObject.setInteger(paramIndex++, Integer.valueOf(String.valueOf(paramObjValue)));
				}else if(paramType.equals("java.lang.Double")){
					queryObject.setDouble(paramIndex++, Double.valueOf(String.valueOf(paramObjValue)));
				}
			}
		}
		
		if(log.isDebugEnabled()) {
			log.debug("paramIndex=["+paramIndex+"],"+sqlParm.getBinding()+"=["+paramObjValue+"]\n");
		}
		return paramIndex;
	}
}
