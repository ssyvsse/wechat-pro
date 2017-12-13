package com.ssyvsse.iotest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssyvsse.dao.CpHistoryMapper;
import com.ssyvsse.dao.CptypeMapper;
import com.ssyvsse.dao.OpenTimeMapper;
import com.ssyvsse.pojo.CpHistory;
import com.ssyvsse.pojo.Cptype;
import com.ssyvsse.pojo.OpenTime;

/**
 * @author llb
 *
 * @Date 2017年12月9日
 */
@Component
@EnableScheduling
public class TestMain2 {

	@Autowired
	private OpenTimeMapper openTimeMapper;

	@Autowired
	private CptypeMapper cptypeMapper;

	@Autowired
	private CpHistoryMapper cpHistoryMapper;

	@Scheduled(fixedDelay = 1000 * 60)
	public void method1() {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setAlignment(HSSFCellStyle.VERTICAL_CENTER);

		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);

		style.setFont(font);
		int[] typeids = { 15, 19, 24, 25, 26, 27, 28, 31, 33, 36, 37, 38, 39, 40, 43, 56, 57, 82, 116 };
		List<OpenTime> openTime = null;
		for (int i = 0; i < typeids.length; i++) {
			Cptype cptype = cptypeMapper.selectById(typeids[i]);

			HSSFSheet sheet = workbook.createSheet(cptype.getTypename() + " " + typeids[i]);
			sheet.setDefaultColumnWidth(15);
			sheet.setDefaultRowHeightInPoints(20);

			String[] dates = { "开奖时间", "2017-12-03", "2017-12-02", "2017-12-01", "2017-11-30", "2017-11-29",
					"2017-11-28", "2017-11-27" };
			HSSFRow row = sheet.createRow(0);
			List<CpHistory> histories = null;
			HSSFCell cell = null;
			openTime = openTimeMapper.findByTypeid(typeids[i]);
			for (int j = 0; j < dates.length; j++) {
				cell = row.createCell(j+1);
				cell.setCellValue(dates[j]);
			}
			
			for (int j = 0; j < dates.length; j++) {
				int rows = 1;
				if(j==0) {
					for (int j2 = 0,len = openTime.size(); j2 < len; j2++) {
						row = sheet.createRow(rows++);
						HSSFCell cell1 = row.createCell(0);
						cell1.setCellStyle(style);
						cell1.setCellValue(openTime.get(j2).getOpentime());
					}
				}else {
					histories = cpHistoryMapper.selectByDate(dates[j], typeids[i]);
					for (int j2 = 0,len=histories.size(); j2 < len; j2++) {
						String time = histories.get(j2).getCreatetime().substring(11);
						row = sheet.getRow(rows++);
						row.createCell(j).setCellValue(time);
					}
				}
			}
		}
		try {
			OutputStream outputStream = new FileOutputStream(new File("C:/Users/2349/Desktop/cptime.xls"));
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			System.out.println("输出excel");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
