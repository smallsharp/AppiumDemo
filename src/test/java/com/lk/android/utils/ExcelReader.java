package com.lk.android.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelReader {

	private static String filePath;
	private String value = null;
	Workbook book = null;
	Sheet sheet = null;

	public ExcelReader(String filePath) {
		this.filePath = filePath;
	}

	public Sheet getSheet(String sheetName) {

		try {
			book = Workbook.getWorkbook(new File(filePath));

			boolean find = false;
			for (String s : book.getSheetNames()) {

				if (sheetName.equals(s)) {
					sheet = book.getSheet(sheetName);
					find = true;
					break;
				}
			}
			if (!find) {
				System.out.println("指定的sheet不存在！");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sheet;

	}

	/**
	 * 
	 * @param columnName：excel的列名
	 * @param lineName：excel的一列的行名
	 * @return
	 */
	public String getValue(String columnName, String lineName) {

		if (sheet != null) {
			int rowNum = sheet.getRows(); // 获得该sheet的行数
			int colNum = sheet.getColumns();
			
			List<String> list = new ArrayList<String>();
			Object[][] data = new Object[rowNum][colNum];
			int k = -1;

			for (int i = 0; i < rowNum; i++) {
				for (int j = 0; j < colNum; j++) {

					Cell c = sheet.getCell(j, i);
					String cz = c.getContents();
					list.add(cz);
//					 System.out.println(cz);

					if (k < list.size()) {
						k++;
						data[i][j] = list.get(k);
					}

					if (data[0][j].equals(columnName) && data[i][0].equals(lineName)) {
						value = data[i][j].toString();
						break;
					}

				}

			}
//			book.close();

		} else {
			System.out.println("检查是否存在这个sheet，如需使用，请先getsheet('xxx')");
		}

		return value;
	}

	// 测试方法
	public static void main(String[] args) {

		filePath = System.getProperty("user.dir") + "\\assets\\data.xls";
		ExcelReader excel = new ExcelReader(filePath);
		excel.getSheet("address");
		System.out.println("邮编：" + excel.getValue("邮编", "tc1"));
		System.out.println("详细地址1：" + excel.getValue("详细地址", "tc1"));

	}
}
