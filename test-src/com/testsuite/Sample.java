package com.testsuite;


import org.testng.annotations.Test;

import com.dataprovider.ExcelManager;

public class Sample {
	@Test (groups = "regression")
	public static void main(String args[]) throws Exception
	{
	  ExcelManager excel = new ExcelManager("Testing");
	  System.out.println("row count is "+excel.getRowCount("Test1"));
	  System.out.println("column count is "+excel.getColumnCount("Test1"));
	  System.out.println("value of cell A5 is - "+excel.getCellData("Test1", 0, 5));
	}
}
