package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;



public class Utilities {

	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;

	public static String generateEmailTimeStamp() {

		Date date=new Date();

		String timeStap=date.toString().replace(" ", "_").replace(":", "_");
		return "shivaraj"+timeStap+"@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetName) {
		File excelFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\Tutorialsninja Testdata.xlsx");
		XSSFWorkbook workbook=null;
		try {
			FileInputStream file=new FileInputStream(excelFile);
			workbook=new XSSFWorkbook(file);
		}catch (Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet= workbook.getSheet(sheetName);

		int rows=sheet.getLastRowNum();
		int cols=sheet.getRow(0).getLastCellNum();

		Object[][] data=new Object[rows][cols];

		for(int i=0;i<rows;i++) {
			XSSFRow row= sheet.getRow(i+1);
			for(int j=0;j<cols;j++){
				XSSFCell cell= row.getCell(j);
			CellType cellType=cell.getCellType();

				switch(cellType) {
				case STRING:
					data[i][j]= cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}
}
