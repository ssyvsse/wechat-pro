package com.ssyvsse.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/***
 * 时间的工具类
 * 
 * @author llb
 *
 */
public class DateUtils {
	/***
	 * 时间增加 向前为正，向后为负
	 * 
	 * @param num
	 * @return
	 */
	public static String dateAdd(Integer num) {
		Date date = new Date();// 取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}

	/***
	 * 和当前时间相减求时间差
	 * 
	 * @param fixedTime
	 * @return
	 */
	public static Long TimingTime(String fixedTime) {
		// 获取当前时间，将其转化成long格式的数字
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String str = sdf.format(date);
		String fs = str.replace("-", "");
		String z = fs.replace(":", "");
		String x = z.replace(" ", "");
		long m = Long.parseLong(x);

		if (fixedTime == null || fixedTime == "") {
			fixedTime = str;
			// 获得定时的时间，并将其转化成long格式的数字
			String fm = fixedTime.replace("-", "");
			String s = fm.replace(":", "");
			String a = s.replace(" ", "");
			long y = Long.parseLong(a);
			return y - m;
		} else {
			// 获得定时的时间，并将其转化成long格式的数字
			String fm = fixedTime.replace("-", "");
			String s = fm.replace(":", "");
			String a = s.replace(" ", "");
			long y = Long.parseLong(a);
			return y - m;
		}
	}
}
