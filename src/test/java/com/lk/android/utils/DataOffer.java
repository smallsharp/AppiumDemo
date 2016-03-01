package com.lk.android.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;

public class DataOffer {

	private String filePath;
	private String nextLine[];
	List<String[]> approvalList = new ArrayList<String[]>();
	CSVReader reader = null;

	private String value;

	public DataOffer(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 
	 * @param columnName
	 * @param lineName
	 * @return
	 */
	public String getData(String columnName, String lineName) {

		try {
			reader = new CSVReader(new FileReader(filePath));
			try {
				while ((nextLine = reader.readNext()) != null) {
					// System.out.println("aa");
					approvalList.add(nextLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int count = 0;
			int index = 0;

			for (String j : approvalList.get(0)) {
				count++;
				if (j.equals(columnName)) {
					index = count - 1;
				}
			}

			for (String[] i : approvalList) {
				if (i[0].equals(lineName))
					value = i[index].toString();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public static void main(String[] args) {
		DataOffer dp = new DataOffer(
				System.getProperty("user.dir") + "\\assets\\data.csv");

		System.out.println(dp.getData("account", "tc1") + ":" + dp.getData("password", "tc1"));
		System.out.println(dp.getData("account", "tc2") + ":" + dp.getData("password", "tc2"));

	}

}
