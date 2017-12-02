//package com.ggy.SalesManageSystem.test.poi;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class XlsMain {
//	
//	 public static void main(String[] args) throws IOException {  
//	        XlsMain xlsMain = new XlsMain();  
//	        XlsDto xls = null;  
//	        List<XlsDto> list = xlsMain.readXls();  
//	           
//	        try {  
//	            XlsDto2Excel.xlsDto2Excel(list);  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	        for (int i = 0; i < list.size(); i++) {  
//	            xls = (XlsDto) list.get(i);  
//	            System.out.println(xls.getXh() + "    " + xls.getXm() + "    " 
//	                    + xls.getYxsmc() + "    " + xls.getKcm() + "    " 
//	                    + xls.getCj());  
//	        }  
//	   
//	    }  
//	   
//	    /**  
//	     * 读取xls文件内容  
//	     *  
//	     * @return List<XlsDto>对象  
//	     * @throws IOException  
//	     *             输入/输出(i/o)异常  
//	     */ 
//	    private List<XlsDto> readXls() throws IOException {  
//	    	File file = new File("../sS/src/com/ggy/SalesManageSystem/test/poi/123.xlsx");
//	        InputStream is = new FileInputStream(file);	        
//	        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(is);  
//	        XlsDto xlsDto = null;  
//	        List<XlsDto> list = new ArrayList<XlsDto>();  
//	        // 循环工作表Sheet  
//	        
//	        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {  
//	            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);  
//	            if (hssfSheet == null) {  
//	                continue;  
//	            }  
//	            // 循环行Row  
//	            System.out.println(hssfSheet.getLastRowNum());
//	            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
//	                XSSFRow hssfRow = hssfSheet.getRow(rowNum);  
//	                if (hssfRow == null) {  
//	                    continue;  
//	                }  
//	                xlsDto = new XlsDto();  
//	                // 循环列Cell  
//	                // 0学号 1姓名 2学院 3课程名 4 成绩  
//	                // for (int cellNum = 0; cellNum <=4; cellNum++) {  
//	                XSSFCell xh = hssfRow.getCell(0);  
//	                if (xh == null) {  
//	                    continue;  
//	                }  
//	                xlsDto.setXh(getValue(xh));  
//	                XSSFCell xm = hssfRow.getCell(1);  
//	                if (xm == null) {  
//	                    continue;  
//	                }  
//	                xlsDto.setXm(getValue(xm));  
//	                XSSFCell yxsmc = hssfRow.getCell(2);  
//	                if (yxsmc == null) {  
//	                    continue;  
//	                }  
//	                xlsDto.setYxsmc(getValue(yxsmc));  
//	                XSSFCell kcm = hssfRow.getCell(3);  
//	                if (kcm == null) {  
//	                    continue;  
//	                }  
//	                xlsDto.setKcm(getValue(kcm));  
//	                XSSFCell cj = hssfRow.getCell(4);  
//	                if (cj == null) {  
//	                    continue;  
//	                }  
//	                xlsDto.setCj(Float.parseFloat(getValue(cj)));  
//	                list.add(xlsDto);  
//	            }  
//	        }  
//	        return list;  
//	    }  
//	   
//	    /**  
//	     * 得到Excel表中的值  
//	     *  
//	     * @param xm  
//	     *            Excel中的每一个格子  
//	     * @return Excel中每一个格子中的值  
//	     */ 
//	    @SuppressWarnings("static-access")  
//	    private String getValue(XSSFCell xm) {  
//	        if (xm.getCellType() == xm.CELL_TYPE_BOOLEAN) {  
//	            // 返回布尔类型的值  
//	            return String.valueOf(xm.getBooleanCellValue());  
//	        } else if (xm.getCellType() == xm.CELL_TYPE_NUMERIC) {  
//	            // 返回数值类型的值  
//	            return String.valueOf(xm.getNumericCellValue());  
//	        } else {  
//	            // 返回字符串类型的值  
//	            return String.valueOf(xm.getStringCellValue());  
//	        }  
//	    }  
//	   
//	} 
