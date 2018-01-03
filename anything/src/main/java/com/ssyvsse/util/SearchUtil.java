package com.ssyvsse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
public class SearchUtil {

	public static void main(String[] args) {
		String filePath = "C:/Users/2349/Desktop/www.2349m.com_20171201.log";
		String regex = "^/[a-zA-Z0-9]$";
		File file = new File(filePath);
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line=br.readLine())!=null) {
				if(line.indexOf("404")>=0&&line.indexOf("2349m")>=0) {
					line = line.substring(line.indexOf("GET")+4, line.indexOf("HTTP"));
					sb.append( line +"\n");
				}
			}
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
