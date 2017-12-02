package com.ssyvsse.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * 
 * @author llb
 *
 * @Date 2017年11月30日 下午9:12:17
 */
public class DateUtils {
	public static String formatDateString(String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}

	public static String formatDateByDateString(String date, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		simpleDateFormat.parse(date);
		return simpleDateFormat.format(simpleDateFormat.parse(date));
	}

	/***
	 * 获取当前时间的前一天
	 * 
	 * @return
	 */
	public static String getSpecifiedDayBefore() {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayBefore;
	}

	public static String getSpecifiedDayBeforeShort() {
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyMMdd").format(c.getTime());
		return dayBefore;
	}

	/****
	 * 获取当前时间的后一天
	 * 
	 * @return
	 */
	public static String getSpecifiedDayAfterShort() {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);
		String dayAfter = new SimpleDateFormat("yyMMdd").format(c.getTime());
		return dayAfter;
	}

	/****
	 * 获取当前时间的后一天
	 * 
	 * @return
	 */
	public static String getSpecifiedDayAfter() {
		Calendar c = Calendar.getInstance();
		Date date = new Date();

		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
		return dayAfter;
	}

	/**
	 * 将时间戳转换为时间
	 * 
	 * @param s
	 * @return
	 */
	public static Date stampToDate(String s) {
		long lt = new Long(s);
		Date date = new Date(lt * 1000);
		return date;
	}

	/**
	 * 将时间转换为时间戳
	 **/
	public static String dateToStamp(String s) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(s);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}
}
