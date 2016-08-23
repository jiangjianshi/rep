package com.css.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyConfigurerSon extends PropertyPlaceholderConfigurer {
	private final static Logger log = LogManager
			.getLogger(PropertyConfigurerSon.class);
	private static Map<String, String> ctxPropertiesMap;
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		super.processProperties(beanFactory, props);
		//---------将properties文件中的值放到缓存中-------start
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
		log.error("初始化加载PropertyConfigurerSon文件成功");
		//---------将properties文件中的值放到缓存中-------end
	}

	// static method for accessing context propertiespublic
	public static String getContextProperty(String name) {
		return ctxPropertiesMap.get(name);
	}
	
	public static String fileSeparator() {
		return System.getProperty("file.separator");
	}
	
	public static String replaceFileSeparator(String path) {
		return path.replace("/", fileSeparator());
	}
}