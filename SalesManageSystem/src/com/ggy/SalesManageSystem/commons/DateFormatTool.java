package com.ggy.SalesManageSystem.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTool {
	public static Date String2Date(String d){
		if(d!=null){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null ;
			try {
				date = df.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
		}else{
			return new Date();
		}
				
	}
}
