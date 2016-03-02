package com.lk.android.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelDataReader {

	private static String filePath;
	private String value;

	public ExcelDataReader(String filePath) {
		this.filePath = filePath;
	}

	public String getDataFromExcel(String sheetName, String columnName, String lineName) {

		Workbook book = null;
		Sheet sheet = null;

		try {
			book = Workbook.getWorkbook(new File(filePath));
			sheet = book.getSheet(sheetName); // 根据sheet名，或者sheet序号，读取
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sheet != null) {
			
			int rowNum = sheet.getRows(); // 获得该sheet的行数
			int colNum = sheet.getColumns();

			List<String> list = new ArrayList<String>();
			Object[][] data = new Object[rowNum][colNum];
			int k = -1;

			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j <= rowNum; j++) {
					
					Cell c = sheet.getCell(j, i);
					String cz = c.getContents();
					list.add(cz);
					
					if (k < list.size()){
						k++;
						data[i][j] = list.get(k);
					}
					
					if (data[0][j].equals(columnName) && data[i][0].equals(lineName)) {
						value = data[i][j].toString();
					}
				
				}

			}
			book.close();
		}else {
			System.out.println("sheet 不存在");
		}

		return value;
	}

	// 测试方法
	public static void main(String[] args) {
		filePath = System.getProperty("user.dir") + "\\assets\\data.xls";
		ExcelDataReader jre = new ExcelDataReader(filePath);
		System.out.println("testdata:" + jre.getDataFromExcel("login", "password", "tc2"));
		System.out.println("testdata:" + jre.getDataFromExcel("232", "account", "tc2"));
	}
}
