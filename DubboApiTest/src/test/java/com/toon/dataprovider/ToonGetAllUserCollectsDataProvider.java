package com.toon.dataprovider;
import org.testng.annotations.DataProvider;

import util.CsvUtil;


public class ToonGetAllUserCollectsDataProvider {
	@DataProvider(name="getAllUserCollectsData", parallel=false)
	public static Object[][] getAllUserCollectsData() throws Exception
	{
		return CsvUtil.getCsvData("/data/collect_queryUserCollect.csv");
	}
	
}
