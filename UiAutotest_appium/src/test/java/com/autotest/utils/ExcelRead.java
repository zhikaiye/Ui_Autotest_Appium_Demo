package com.autotest.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;

import java.io.FileInputStream;

public class ExcelRead {

    public Object[][] getDataFromExcel(String excelPath) {
        Workbook workbook;
        try {
            FileInputStream excelInputStream = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(excelInputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int rowInExcel = sheet.getPhysicalNumberOfRows();
            int columnInExcel = sheet.getRow(0).getPhysicalNumberOfCells();
            String[][] obj = new String[rowInExcel-1][columnInExcel];
            for(int row = 1; row < rowInExcel; row++){
                for(int col = 0; col < columnInExcel; col++){
                    sheet.getRow(row).getCell(col).setCellType(Cell.CELL_TYPE_STRING);
                    obj[row - 1][col] = sheet.getRow(row).getCell(col).getStringCellValue();
                    System.out.println(obj[row - 1][col]);
                }
            }
            return obj;
        }catch(Exception e){
            throw new SkipException(e.getMessage());
        }

    }


}
