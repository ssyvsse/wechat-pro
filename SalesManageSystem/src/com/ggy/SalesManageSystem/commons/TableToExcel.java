package com.ggy.SalesManageSystem.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
   

public class TableToExcel {  
   
    /**  
     * @param xls  
     *            XlsDto实体类的一个对象  
     * @throws Exception  
     *             在导入Excel的过程中抛出异常  
     */ 
    @SuppressWarnings("rawtypes")
	public static XSSFWorkbook xlsDto2Excel(List cellList,Vector v) throws Exception {  
        // 获取总行数  
        @SuppressWarnings("unused")
		int rowCount = v.size();
        // 创建Excel文档  
        XSSFWorkbook hwb = new XSSFWorkbook();  
        int size = cellList.size();
        // sheet 对应一个工作页  
        XSSFSheet sheet = hwb.createSheet("pldrxkxxmb");  
        XSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始  
        XSSFCell[] firstcell = new XSSFCell[size]; 
        
        for (int j = 0; j < size; j++) { //创建表头  
            firstcell[j] = firstrow.createCell(j); 
            firstcell[j].setCellValue(new XSSFRichTextString((String) cellList.get(j)));        	
        }  
        
        
        for (int i = 0; i < v.size(); i++) {  
            // 创建一行  
            XSSFRow row = sheet.createRow(i + 1);  
            // 得到要插入的每一条记录  
            @SuppressWarnings("unchecked")
			List list = new ArrayList((Vector)v.get(i));
            for (int j = 0; j < list.size(); j++) {  
                // 在一行内循环  
                XSSFCell xh = row.createCell(j);  
                xh.setCellValue((String.valueOf((list.get(j)))));                

            }  
        }  
        return hwb;
        // 创建文件输出流，准备输出电子表格  
//        File file = new File("E:\\Users\\Workspaces\\MyEclipse Professional "
//        		+ "2014\\sS\\src\\com\\ggy\\SalesManageSystem\\test\\123.xlsx");
//        if(!file.exists())
//        	file.createNewFile();
//        OutputStream out = new FileOutputStream(file);  
//        hwb.write(out);  
        
    }  
   
} 