package com.toon.dataprovider;
import org.testng.annotations.DataProvider;



public class ToonUserCollectProvider {

	@DataProvider(name="getUserCollectInfo", parallel=false)
	public static Object[][] getUserCollectInfo() throws Exception
	{
		return util.CsvUtil.getCsvData("/data/collect_userCollect.csv");
	}
}
