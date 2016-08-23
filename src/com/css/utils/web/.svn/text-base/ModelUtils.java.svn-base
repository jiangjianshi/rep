package com.css.utils.web;

import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * @author syw 2013-12-29
 */
public class ModelUtils {
    
    private static final Logger log = Logger.getLogger(ModelUtils.class);
    
    /**
     * 判断两个java对象是否相等
     * @param x
     * @param y
     * @return
     */
    public static boolean equals(Object x, Object y) {
        return x == y || (x != null && y != null && x.equals(y));
    }
    
    /**
     * get没有参数，get方法是public的
     * @param method
     * @return
     */
    public static boolean isGetMethod(Method method) {
        if (method == null) {
            return false;
        }
        if (!method.getName().startsWith("get")) {
            return false;
        }
        if (method.getParameterTypes().length > 0) {
            return false;
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            return false;
        }
        return true;
    }
    
    /**
     * 调用get方法，转换非RuntimeException
     * @param object
     * @param method
     * @return
     */
    public static Object invokeGetMethod(Object object, Method method) {
        try {
            return method.invoke(object, new Object[0]);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    /**
     * 调用set方法，转换非RuntimeException
     * @param object
     * @param method
     * @return
     */
    public static void invokeSetMethod(Object object, Method method, Object value) {
        try {
            method.invoke(object, new Object[] {value});
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    /**
     * 获得某个对象中一个指定方法名的方法对象，转换非RuntimeException
     * @param Class 指定对象类型
     * @param methodName 方法名
     * @param parameterTypes 方法参数类型数组
     * @return 方法对象
     */
    public static Method getMethod(Class clazz, String methodName, Class[] parameterTypes) {
        try {
            return clazz.getMethod(methodName, parameterTypes);
        } catch (SecurityException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    /**
     * 根据一个方法名，得到它的属性名，方法只有两种get方法和set方法，属性名第一个强制变小写
     * @param methodName
     * @return
     */
    public static String getPropertyName(String methodName) {
        return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
    }
    
    /**
     * 根据一个属性名，得到它的get方法名
     * @param propertyName
     * @return
     */
    public static String getGetMehodName(String propertyName) {
        return "get" + StringUtils.capitalize(propertyName);
    }
    
    
    /**
     * 调用newInstance方法，转换非RuntimeException
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        Assert.notNull(clazz);
        try {
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
    
    /**
     * 将form表单中的值，封装到java对象中
     * @param params
     * @param clazz
     * @param prefix
     * @return
     * @throws Exception 
     */
    public static <T> T convertParams2Obj(Map params, Class<T> clazz, String prefix) throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat();
    	Set<Entry> entries = params.entrySet();
    	PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
		T ret = newInstance(clazz);
		for(Entry e : entries){
			String keyName = String.valueOf(e.getKey());
			if(keyName.startsWith(prefix)){
				String propertyName = keyName.replace(prefix, "");
				for(PropertyDescriptor pd : pds){
					if(pd.getName().equals(propertyName)){
						Object value = e.getValue();
						if(value == null || "".equals(value))
							continue;
						if(value instanceof String[])
							value = ((String[])value)[0];
						if("".equals(value))
							continue;
						if(pd.getPropertyType() == String.class)
						value = String.valueOf(value);  
						else if(pd.getPropertyType() == java.util.Date.class){
							try {
								String[] values = String.valueOf(value).split(" ");
								String pattern = null;
								if(String.valueOf(values[0]).matches("\\d{4}\\-\\d{2}\\-\\d{2}"))
									pattern = "yyyy-MM-dd";
								else if(String.valueOf(values[0]).matches("\\d{4}\\-\\d{2}\\-\\d"))
									pattern = "yyyy-MM-d";
								else if(String.valueOf(values[0]).matches("\\d{4}\\-\\d\\-\\d"))
									pattern = "yyyy-M-d";
								else if(String.valueOf(values[0]).matches("\\d{4}\\-\\d\\-\\d{2}"))
									pattern = "yyyy-M-dd";
								else if(String.valueOf(values[0]).matches("\\d\\/\\d{2}\\/\\d{4}"))
									pattern = "M/dd/yyyy";
								else if(String.valueOf(values[0]).matches("\\d{2}\\/\\d{2}\\/\\d{4}"))
									pattern = "MM/d/yyyy";
								else if(String.valueOf(values[0]).matches("\\d\\/\\d\\/\\d{4}"))
									pattern = "M/d/yyyy";
								else if(String.valueOf(values[0]).matches("\\d{2}\\/\\d{2}\\/\\d{4}"))
									pattern = "MM/dd/yyyy";
								else if(String.valueOf(values[0]).matches("\\d{4}\\年\\d{2}\\月\\d{2}\\日"))
									pattern = "yyyy年MM月dd日";
								else if(String.valueOf(values[0]).matches("\\d{4}\\年\\d{2}\\月\\d\\日"))
									pattern = "yyyy年MM月d日";
								else if(String.valueOf(values[0]).matches("\\d{4}\\年\\d\\月\\d\\日"))
									pattern = "yyyy年M月d日";
								else if(String.valueOf(values[0]).matches("\\d{4}\\年\\d\\月\\d{2}\\日"))
									pattern = "yyyy年M月dd日";
								if(values.length == 1);
								else if(values[1].matches("\\d{2}:\\d{2}:\\d{2}"))
									pattern += " HH:mm:ss";
								else if(values[1].matches("\\d:\\d:\\d"))
									pattern += " H:m:s";
								else if(values[1].matches("\\d{2}:\\d:\\d{2}"))
									pattern += " HH:m:ss";
								else if(values[1].matches("\\d:\\d{2}:\\d"))
									pattern += " H:mm:s";
								else if(values[1].matches("\\d{2}:\\d{2}:\\d"))
									pattern += " HH:mm:s";
								else if(values[1].matches("\\d{2}:\\d:\\d{2}"))
									pattern += " HH:m:ss";
								sdf.applyPattern(pattern);
								value = sdf.parse(String.valueOf(value));
							} catch (ParseException e1) {
								e1.printStackTrace();
							}
						}else if(pd.getPropertyType() == Double.class){
							String _v = String.valueOf(value).replaceAll(",", "").trim();
							value = Double.valueOf((_v.equals("")?"0":_v));
						}else if(pd.getPropertyType() == BigDecimal.class){
							String _v = String.valueOf(value).replaceAll(",", "").trim();
							value = new BigDecimal((_v.equals("")?"0":_v));
						}else if(pd.getPropertyType() == Integer.class){
							String _v = String.valueOf(value).replaceAll(",", "").trim();
							value = Integer.valueOf((_v.equals("")?"0":_v));
						}
						else if(pd.getPropertyType() == Long.class){
							String _v = String.valueOf(value).replaceAll(",", "").trim();
							value = Long.valueOf((_v.equals("")?"0":_v));
						}
						else if(pd.getPropertyType() == Boolean.class)
							value = value.equals("true");
						else if(pd.getPropertyType() == Short.class)
							value = Short.valueOf(String.valueOf(value));
						invokeSetMethod(ret, pd.getWriteMethod(),value);
						break;
					}
				}
			}
		}
    	return ret;
    }
    /**
     * 将Map中的值，封装到java对象中
     * @param params
     * @param clazz
     * @param prefix
     * @return
     * @throws Exception 
     */
    public static <T> T convertMap2Obj(Map params, Class<T> clazz) throws Exception{
    	SimpleDateFormat sdf = new SimpleDateFormat();
    	Set<Entry> entries = params.entrySet();
    	PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clazz);
    	T ret = newInstance(clazz);
    	for(Entry e : entries){
    		String keyName = String.valueOf(e.getKey());
    			String propertyName = keyName;
    			for(PropertyDescriptor pd : pds){
    				if(pd.getName().equals(propertyName)){
    					Object value = e.getValue();
    					if(value == null || "".equals(value))
    						continue;
    					if(value instanceof String[])
    						value = ((String[])value)[0];
    					if("".equals(value))
    						continue;
    					if(pd.getPropertyType() == String.class)
    						value = String.valueOf(value);  
    					else if(pd.getPropertyType() == Double.class){
    						String _v = String.valueOf(value).replaceAll(",", "").trim();
    						value = Double.valueOf((_v.equals("")?"0":_v));
    					}else if(pd.getPropertyType() == BigDecimal.class){
    						String _v = String.valueOf(value).replaceAll(",", "").trim();
    						value = new BigDecimal((_v.equals("")?"0":_v));
    					}else if(pd.getPropertyType() == Integer.class){
    						String _v = String.valueOf(value).replaceAll(",", "").trim();
    						value = Integer.valueOf((_v.equals("")?"0":_v));
    					}
    					else if(pd.getPropertyType() == Long.class){
    						String _v = String.valueOf(value).replaceAll(",", "").trim();
    						value = Long.valueOf((_v.equals("")?"0":_v));
    					}
    					else if(pd.getPropertyType() == Boolean.class)
    						value = value.equals("true");
    					else if(pd.getPropertyType() == Short.class)
    						value = Short.valueOf(String.valueOf(value));
    					invokeSetMethod(ret, pd.getWriteMethod(),value);
    					break;
    				}
    			}
    	}
    	return ret;
    }
    
    public static <T> void override(T target, T source, String... ignores){
    	PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(target.getClass());
    	for(PropertyDescriptor pd : pds){
        	boolean breakFlag = false;
    		if(ignores != null && ignores.length > 0){
    			for(String ignore : ignores)
    				if(ignore.equals(pd.getName())){
    					breakFlag = true;
    					break;
    				}
    		}
    		if(breakFlag)
    			continue;
    		Object srcValue = invokeGetMethod(source, pd.getReadMethod());
    		if(srcValue != null && !"".equals(String.valueOf(srcValue)) && pd.getWriteMethod() != null)
    			invokeSetMethod(target, pd.getWriteMethod(), srcValue);
    	}
    }
}
