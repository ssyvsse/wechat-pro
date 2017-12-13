package com.ssyvsse.iotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
public class TestMain3 {
	public static void main(String[] args) {
		int i = 0;
		int row = 0;
		File file = new File("C:/Users/2349/Desktop/invalid link.txt");
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				row ++ ;
				try {
					Jsoup.connect(line).get();
				} catch (Exception e) {
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(i+"个无效链接,一共:"+row);
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void joibExcel(Set<String> list, Set<String> list2) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("2349URL---");

		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);

		sheet.setDefaultColumnWidth(60);
		sheet.setDefaultRowHeightInPoints(20);

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		style.setFont(font);

		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("2349URL");

		HSSFRow row2 = sheet.createRow(1);
		String[] titles = { "origin url", "invalid url" };

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell12 = row2.createCell(i);
			style.setFont(font);
			cell12.setCellStyle(style);
			cell12.setCellValue(titles[i]);
		}
		int rows = 3;

		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			HSSFRow row3 = sheet.createRow(rows++);

			HSSFCell cell1 = row3.createCell(0);
			cell1.setCellStyle(style);
			cell1.setCellValue(it.next());

		}
		int rows2 = 3;
		for (Iterator<String> it = list2.iterator(); it.hasNext();) {
			HSSFRow row3 = sheet.createRow(rows2++);

			HSSFCell cell1 = row3.createCell(1);
			cell1.setCellStyle(style);
			cell1.setCellValue(it.next());

		}

		/*
		 * int rows2 = 3; if (list2 != null && list2.size() > 0) { for (String string :
		 * list2) { HSSFRow row3 = sheet.createRow(rows2++);
		 * 
		 * HSSFCell cell1 = row3.createCell(3); cell1.setCellStyle(style);
		 * cell1.setCellValue(string); } }
		 */

		try {
			OutputStream outputStream = new FileOutputStream(new File("C:/Users/2349/Desktop/2349url 2017-12-11.xls"));
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			System.out.println("输出excel");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
