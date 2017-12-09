package com.ssyvsse.crawl.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import com.ssyvsse.crawl.utils.NetUtils;

/**
 * @author llb
 *
 * @Date 2017年12月8日 下午7:46:10
 */
// @Component
// @EnableScheduling
public class CrawlJob {

	@Value("${crawl.2349.url}")
	private String url;

	private static Set<String> validUrlSet;

	private static Set<String> invalidUrlSet;


	static {
		validUrlSet = new HashSet<String>();
		invalidUrlSet = new HashSet<String>();
	}

	public static void main(String[] args) {
		jsoup("https://www.2349m.com/zoushi/");
		joinExcel();
	}

	private static void jsoup(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
		}
		Elements elements = doc.select(".trend_main").select("ul");
		for (Element element : elements) {
			Elements element2 = element.select("a");
			for (Element element3 : element2) {
				String link = element3.attr("href");
				link = "https://www.2349m.com"+link;
				System.out.println(link);
				validUrlSet.add(link);
				jsoupInside(link);
			}
		}
	}
	
	public static void jsoupInside(String url){
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			invalidUrlSet.add(url);
			return;
		}
		Elements elements = doc.select(".ggaoBox");
		for (Element element : elements) {
			String link = element.select("a").attr("href");
			link = "https://www.2349m.com"+link;
			System.out.println(link);
			validUrlSet.add(link);
		}
	}
	

	private static void joinExcel() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("2349URL");

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
		String[] titles = { "valid url", "invalid url" };

		for (int i = 0; i < titles.length; i++) {
			HSSFCell cell12 = row2.createCell(i);
			style.setFont(font);
			cell12.setCellStyle(style);
			cell12.setCellValue(titles[i]);
		}
		int rows = 3;
		if (validUrlSet != null) {
			for (Iterator<String> it = validUrlSet.iterator(); it.hasNext();) {
				HSSFRow row3 = sheet.createRow(rows++);

				HSSFCell cell1 = row3.createCell(0);
				cell1.setCellStyle(style);
				cell1.setCellValue(it.next());
			}
		}
		int rows2 = 3;
		if (invalidUrlSet != null) {
			for (Iterator<String> it = invalidUrlSet.iterator(); it.hasNext();) {
				HSSFRow row3 = sheet.createRow(rows2++);

				HSSFCell cell1 = row3.createCell(1);
				cell1.setCellStyle(style);
				cell1.setCellValue(it.next());
			}
		}
		
		
		try {
			OutputStream outputStream = new FileOutputStream(new File("c:/Users/Administrator/Desktop/sss.xlsx"));
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			System.out.println("输出excel");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
