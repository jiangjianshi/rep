package com.css.utils.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.utility.StringUtil;


/**
 * Title: 时间和日期的工具类
 * Description: DateUtil类包含了标准的时间和日期格式，以及这些格式在字符串及日期之间转换的方法
 */
public class DateUtils {

	/**
	 * 获取当前日期的字符串
	 * 
	 * @param format_string
	 *            时间格式，譬如："yyyy-MM-dd HH:mm:ss"
	 * @return 字符串
	 */
	public static final String getDateNow(String format_string) {
		if ((format_string == null) || (format_string.equals("")))
			format_string = "yyyy-MM-dd HH:mm:ss";
		Calendar cld = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format_string);
		return df.format(cld.getTime());
	}
	
	/**
	 * 日期+天数 后的日期
	 */
	public static final Date plusDate(Date beginDate,int plusNum) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(beginDate);
		ca.add(ca.DATE, plusNum);
		return ca.getTime();
	}
	
	/**
	 * 日期格式化字符串
	 */
	public static final String formateDateToStr(Date formateDate,String formateStr) {
		DateFormat df = new SimpleDateFormat(formateStr);
		return df.format(formateDate);
	}
	
	/**
	 * 格式化字符串 转为 日期对象
	 * @throws ParseException 
	 */
	public static final Date formateStrToDate(String dateStr,String formateStr) throws ParseException {
		DateFormat df = new SimpleDateFormat(formateStr);
		return df.parse(dateStr);
	}
	
}