package com.toon.api.dbproviders;

import org.testng.annotations.DataProvider;

public class DBProvider {
	//测试用例
	public static Object[][] cases=null;
	
	/**数据驱动**/
	@DataProvider(name="cases")
	public static Object[][] getTestCases(){
		return 	cases;
	}
}
