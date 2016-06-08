package com.toon.dataprovider;
import org.testng.annotations.DataProvider;


public class ToonSecurityQuestionDataProvider {

	@DataProvider(name="getSecurityQuestionUpdateTime",parallel=true)
	public static Object[][] getSecurityQuestionUpdateTime() throws Exception
	{
		return util.CsvUtil.getCsvData("/data/Security_UpdateTime.csv");
	}
	
}
