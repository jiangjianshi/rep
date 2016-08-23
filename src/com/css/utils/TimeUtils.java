package com.css.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	private static final long serialVersionUID = 0L;

	private static TimeUtils time = null;

	private final String format = "yyyy-MM-dd HH:mm:ss";

	public static synchronized TimeUtils getInstance() {
		if (time == null) {
			time = new TimeUtils();
		}
		return time;
	}

	/*
	 * 返回指定格式的时间 参数说明： Format: yyyy-MM-dd HH:mm:ss:SSS -> 2006-07-18
	 * 12:30:55:978 Symbol Meaning Presentation Example y year (Number) 2006 M
	 * month in year (Text & Number) July & 07 d day in month (Number) 18 H hour
	 * in day (0~23) (Number) 12 m minute in hour (Number) 30 s second in minute
	 * (Number) 55 S millisecond (Number) 978
	 */
	public String getTimeByFormat(String format) {
		if (format == null || format.trim().length() == 0)
			format = this.format;
		return (new SimpleDateFormat(format)).format(new Date());
	}

	public String getTimeByFormat(String time, String format) {
		String t = null;
		if (time == null || time.trim().length() == 0) {
			return t;
		}
		if (format == null || format.trim().length() == 0) {
			format = this.format;
		}
		DateFormat df = new SimpleDateFormat(format);
		try {
			Date d = df.parse(time);
			t = d.toString();
			d = null;
		} catch (Exception e) {
		}
		df = null;
		return t;
	}

	/**
	 * 返回当前时间精确到秒
	 * 
	 * @return 当前时间(去掉毫秒)
	 */
	public String getTimeByTime(String time) {
		if (time == null || time.trim().length() == 0)
			return "";
		// 由于.是转移字符，所以需要在前面增加\\。
		return time.split("\\.")[0];
	}

	/**
	 * 返回当前时间的毫秒
	 * 
	 * @return 当前时间毫秒
	 */
	public Long getMiliTime() {
		return System.currentTimeMillis();
	}

	/**
	 * 返回毫秒
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 日期相加
	 * 
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	public String addNowDate(int day) {
		return getTimeByTime((new SimpleDateFormat(this.format))
				.format(addDate(new Date(), day)));
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public int diffDate(Date date1, Date date2) {
		return (int) ((getMillis(date1) - getMillis(date2)) / (24 * 3600 * 1000));
	}

	public int diffNowDate(Date date) {
		return diffDate(new Date(), date);
	}

	public Date convertStringToDate(String time) {
		if (time == null || time.trim().length() == 0)
			return new Date();
		return java.sql.Date.valueOf(time);
	}

	public long diffDate(String time1, String time2) {
		long days = 0;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			long diff = d1.getTime() - d2.getTime();
			days = diff / (1000 * 60 * 60 * 24);
		} catch (Exception e) {
		}
		return (int) days;
	}
}
