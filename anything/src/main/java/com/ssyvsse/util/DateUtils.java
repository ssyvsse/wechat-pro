package com.ssyvsse.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author llb
 *
 * @Date 2018年1月14日 上午9:25:43
 */
public class DateUtils {

	/**
	 * 格式化日期
	 * 
	 * @param format
	 *            输入日期格式
	 * @return 格式化后的当前时间
	 */
	public static String formateDateStr(String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date());
	}

	/**
	 * 输入转换格式以及要转换的日期
	 * 
	 * @param format
	 *            格式 如:yyyy-MM-dd
	 * @param date
	 *            2018-01-14 09:46:20
	 * @return 2018-01-14
	 * @throws ParseException
	 */
	public static String formatDateByDateStr(String format, String date) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df2 = new SimpleDateFormat(format);
		return df2.format(df.parse(date));
	}

	/**
	 * 获取当前时间
	 * 
	 * @param format
	 *            格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNow() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int seconds = calendar.get(Calendar.SECOND);
		return year + "-" + ((month + 1) < 10 ? ("0" + (month + 1)) : (month + 1)) + "-"
				+ (date < 10 ? ("0" + date) : date) + " " + (hour < 10 ? ("0" + hour) : hour) + ":"
				+ (minutes < 10 ? ("0" + minutes) : minutes) + ":" + (seconds < 10 ? ("0" + seconds) : seconds);
	}

	/**
	 * 获取当天日期
	 * 
	 * @return
	 */
	public static String getToday() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		return year + "" + ((month + 1) < 10 ? ("0" + (month + 1)) : (month + 1)) + (date < 10 ? ("0" + date) : date);
	}

	/**
	 * 获取当前时间后一天 <br/>
	 * type=1:return yyyy-MM-dd<br/>
	 * type=2:return yyyyMMdd<br/>
	 * 
	 * @return
	 */
	public static String getTomorrowByType(int type) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day + 1);
		if (type == 1) {
			return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		} else {
			return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		}
	}

	/**
	 * 获取当前时间前一天 <br/>
	 * type=1:return yyyy-MM-dd<br/>
	 * type=2:return yyyyMMdd<br/>
	 * 
	 * @return
	 */
	public static String getYesterdayByType(int type) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE, day - 1);
		if (type == 1) {
			return new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		} else {
			return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
		}
	}

	/**
	 * 格式化日期<br/>
	 * 格式: yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	/**
	 * 将时间戳转换为时间
	 * 
	 * @param stamp
	 * @return
	 */
	public static Date stampToDate(String stamp) {
		Long l = new Long(stamp);
		return new Date(l);
	}

	/**
	 * 将时间转换为时间戳
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static String dateToStamp(String str) throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = df.parse(str);
		Long timestamp = date.getTime();
		return String.valueOf(timestamp);
	}
}
