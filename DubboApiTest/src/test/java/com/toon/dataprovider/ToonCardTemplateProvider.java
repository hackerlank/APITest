package com.toon.dataprovider;

import org.testng.annotations.DataProvider;


public class ToonCardTemplateProvider {

	@DataProvider(name="card_fieldTemplate", parallel=true)
	public static Object[][] card_fieldTemplate() throws Exception
	{
		return util.CsvUtil.getCsvData("/data/card_getTemplate.csv");
	}
	
}
