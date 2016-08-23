package com.css.utils.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)// 注解的存活时间,整个运行时都存活
@Target(ElementType.METHOD)// 此注解表示只能在方法上面有效
public @interface Permission {
	/** 模块名 **/
	String model();

	/** 权限值 **/
	String privilegeValue();

	/* 用于区分当前页面是dialog还是navTab */
	String targetType();
}