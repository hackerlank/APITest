package com.toon.dataprovider;
import org.testng.annotations.DataProvider;


public class ToonDeleteUserCollectProvider {

	@DataProvider(name="deleteUserCollectProvider", parallel=false)
	public static Object[][] deleteUserCollectProvider() throws Exception
	{
		return util.CsvUtil.getCsvData("/data/collect_deleteUserCollect.csv");
	}
	
	
}
