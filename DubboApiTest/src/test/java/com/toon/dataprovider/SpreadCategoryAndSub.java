package com.toon.dataprovider;
import org.testng.annotations.DataProvider;

import util.CsvUtil;

public class SpreadCategoryAndSub {
	
	@DataProvider(name="getSpreadCategoryAndSubList", parallel=false)
	public static Object[][] getSpreadCategoryAndSubList() throws Exception
	{
		return CsvUtil.getCsvData("/data/spreadCategory_GetList.csv");
	}
	
	
}
