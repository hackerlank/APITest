package com.toon.api.dbproviders;

import org.testng.annotations.DataProvider;

public class DBProvider {
	//��������
	public static Object[][] cases=null;
	
	/**��������**/
	@DataProvider(name="cases")
	public static Object[][] getTestCases(){
		return 	cases;
	}
}
