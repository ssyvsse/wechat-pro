package com.ggy.SalesManageSystem.test;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

   

public class XlsMain {  
   
    public static void main(String[] args) throws IOException {  
        XlsMain xlsMain = new XlsMain();  
        XlsDto xls = null;  
        List<XlsDto> list = xlsMain.readXls();  
           
        try {  
            XlsDto2Excel.xlsDto2Excel(list);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        for (int i = 0; i < list.size(); i++) {  
            xls = (XlsDto) list.get(i);  
            System.out.println(xls.getXh() + "    " + xls.getXm() + "    " 
                    + xls.getYxsmc() + "    " + xls.getKcm() + "    " 
                    + xls.getCj());  
        }  
   
    }  
   
  
    private List<XlsDto> readXls() throws IOException {  
        InputStream is = new FileInputStream("E:\\Users\\Workspaces\\MyEclipse Professional 2014\\sS\\src\\com\\ggy\\SalesManageSystem\\test/123.xls");  
        Workbook workbook =null;
		try {
			workbook = WorkbookFactory.create(is);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} 
        XlsDto xlsDto = null;  
        List<XlsDto> list = new ArrayList<XlsDto>();  
     
        for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {  
            Sheet hssfSheet = workbook.getSheetAt(0);  
            if (hssfSheet == null) {  
                continue;  
            }  
         
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {  
                Row hssfRow = hssfSheet.getRow(rowNum);  
                if (hssfRow == null) {  
                    continue;  
                }  
                xlsDto = new XlsDto();  
            
                Cell xh = hssfRow.getCell(0);  
                if (xh == null) {  
                    continue;  
                }  
                xlsDto.setXh(getValue(xh));  
                Cell xm = hssfRow.getCell(1);  
                if (xm == null) {  
                    continue;  
                }  
                xlsDto.setXm(getValue(xm));  
                Cell yxsmc = hssfRow.getCell(2);  
                if (yxsmc == null) {  
                    continue;  
                }  
                xlsDto.setYxsmc(getValue(yxsmc));  
                Cell kcm = hssfRow.getCell(3);  
                if (kcm == null) {  
                    continue;  
                }  
                xlsDto.setKcm(getValue(kcm));  
                Cell cj = hssfRow.getCell(4);  
                if (cj == null) {  
                    continue;  
                }  
                xlsDto.setCj(Float.parseFloat(getValue(cj)));  
                list.add(xlsDto);  
            }  
        }  
        return list;  
    }  
   

    @SuppressWarnings("static-access")  
    private String getValue(Cell xh) {  
        if (xh.getCellType() == xh.CELL_TYPE_BOOLEAN) {  
            
            return String.valueOf(xh.getBooleanCellValue());  
        } else if (xh.getCellType() == xh.CELL_TYPE_NUMERIC) {  
            
            return String.valueOf(xh.getNumericCellValue());  
        } else {  
             
            return String.valueOf(xh.getStringCellValue());  
        }  
    }  
   
} 


class XlsDto2Excel {  
	   

    public static void xlsDto2Excel(List<XlsDto> xls) throws Exception {  
     
        int CountColumnNum = xls.size();  
      
        HSSFWorkbook hwb = new HSSFWorkbook();  
        XlsDto xlsDto = null;  
     
        HSSFSheet sheet = hwb.createSheet("pldrxkxxmb");  
        HSSFRow firstrow = sheet.createRow(0);  
        HSSFCell[] firstcell = new HSSFCell[CountColumnNum];  
        String[] names = new String[CountColumnNum];  
        names[0] = "123";  
        names[1] = "123";  
        names[2] = "123";  
        names[3] = "23";  
        names[4] = "23";  
        for (int j = 0; j < CountColumnNum; j++) {  
            firstcell[j] = firstrow.createCell(j);  
            firstcell[j].setCellValue(new HSSFRichTextString(names[j]));  
        }  
        for (int i = 0; i < xls.size(); i++) {  
       
            HSSFRow row = sheet.createRow(i + 1);  
   
            xlsDto = xls.get(i);  
            for (int colu = 0; colu <= 4; colu++) {  
              
                HSSFCell xh = row.createCell(0);  
                xh.setCellValue(xlsDto.getXh());  
                HSSFCell xm = row.createCell(1);  
                xm.setCellValue(xlsDto.getXm());  
                HSSFCell yxsmc = row.createCell(2);  
                yxsmc.setCellValue(xlsDto.getYxsmc());  
                HSSFCell kcm = row.createCell(3);  
                kcm.setCellValue(xlsDto.getKcm());  
                HSSFCell cj = row.createCell(4);  
                cj.setCellValue(xlsDto.getCj());  
                 
            }  
        }  
     
        OutputStream out = new FileOutputStream("POI2Excel/pldrxkxxmb.xls");  
        hwb.write(out);  
        out.close();  
        System.out.println("��ݿ⵼���ɹ�");  
    }  
   
} 



class XlsDto {  
 
    private Integer xkh;  

    private String xh;  
  
    private String xm;  
 
    private String yxsmc;  
  
    private Integer kch;  

    private String kcm;  

    private float cj;  
    public Integer getXkh() {  
        return xkh;  
    }  
    public void setXkh(Integer xkh) {  
        this.xkh = xkh;  
    }  
    public String getXh() {  
        return xh;  
    }  
    public void setXh(String xh) {  
        this.xh = xh;  
    }  
    public String getXm() {  
        return xm;  
    }  
    public void setXm(String xm) {  
        this.xm = xm;  
    }  
    public String getYxsmc() {  
        return yxsmc;  
    }  
    public void setYxsmc(String yxsmc) {  
        this.yxsmc = yxsmc;  
    }  
    public Integer getKch() {  
        return kch;  
    }  
    public void setKch(Integer kch) {  
        this.kch = kch;  
    }  
    public String getKcm() {  
        return kcm;  
    }  
    public void setKcm(String kcm) {  
        this.kcm = kcm;  
    }  
    public float getCj() {  
        return cj;  
    }  
    public void setCj(float cj) {  
        this.cj = cj;  
    }  
       
} 