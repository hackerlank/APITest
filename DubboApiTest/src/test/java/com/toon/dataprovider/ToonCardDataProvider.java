package com.toon.dataprovider;

import org.testng.annotations.DataProvider;

public class ToonCardDataProvider {

	@DataProvider(name="card_addUserCard", parallel = true)
	public static Object[][] dpGetUserId() throws Exception {
		return util.CsvUtil.getCsvData("/data/card_addUserCard.csv");
	}
	
	@DataProvider(name="card_updateUserCard", parallel = false)
	public static Object[][] dpUpdateUserCard() throws Exception {
		return util.CsvUtil.getCsvData("/data/card_updateUserCard.csv");
	}
	
	
	
	
}
