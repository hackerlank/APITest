package com.toon.api.test.testNgDataProvider;

import org.testng.annotations.DataProvider;
import com.toon.api.test.testcase.ApiTestCase;

public class DProvider {
		
		private static String testCasePath = null;

		static{	
			if(ApiTestCase.getTestServer().equals("test")){
				testCasePath = "/data/";
			}else if(ApiTestCase.getTestServer().equals("online")){
				testCasePath = "/data_online/";
			}		
		}
	
		@DataProvider(name="user", parallel = false)
		public static Object[][] dpUser() throws Exception {
			return util.CsvUtil.getCsvData(testCasePath + "user.csv");	
		}
		
		@DataProvider(name="org", parallel = false)
		public static Object[][] dpOrg() throws Exception {
			return util.CsvUtil.getCsvData( testCasePath + "org.csv");	
		}
		// ************************************  数据失效start
		@DataProvider(name="communicate", parallel = false)
		public static Object[][] dpCommunicate() throws Exception {
			return util.CsvUtil.getCsvData("/data/communicate.csv");	
		}
		
		@DataProvider(name="activity", parallel=false)
		public static Object[][] dpActivity() throws Exception
		{
			return util.CsvUtil.getCsvData("/data/activity.csv");
		}
		//***************************** 数据失效end
		@DataProvider(name="plugin", parallel = false)
		public static Object[][] dpPlugin() throws Exception {
			return util.CsvUtil.getCsvData(testCasePath + "plugin.csv");	
		}
		
		@DataProvider(name="card", parallel = false)
		public static Object[][] dpCard() throws Exception
		{
			return util.CsvUtil.getCsvData(testCasePath + "card.csv");
		}
				
		@DataProvider(name="group", parallel=false)
		public static Object[][] dpGroup() throws Exception
		{
			return util.CsvUtil.getCsvData(testCasePath + "group.csv");
		}
		
		@DataProvider(name="found", parallel=false)
		public static Object[][] getGound() throws Exception
		{
			return util.CsvUtil.getCsvData(testCasePath + "found.csv");
		}
		
		@DataProvider(name="version", parallel=false)
		public static Object[][] getVersion() throws Exception
		{
			return util.CsvUtil.getCsvData(testCasePath + "version.csv");
		}
		
		@DataProvider(name="contact", parallel=false)
		public static Object[][] getContact() throws Exception{
			return util.CsvUtil.getCsvData(testCasePath + "contact.csv");
		}
		
		@DataProvider(name="groupchat", parallel=false)
		public static Object[][] getGroupchat() throws Exception{
			return util.CsvUtil.getCsvData(testCasePath + "groupchat.csv");
		}
		
		@DataProvider(name="feed", parallel=false)
		public static Object[][] getFeed() throws Exception{
			return util.CsvUtil.getCsvData(testCasePath + "feed.csv");
		}
		
		@DataProvider(name="router", parallel=false)
		public static Object[][] getRouter() throws Exception{
			return util.CsvUtil.getCsvData(testCasePath + "router.csv");
		}
		
		@DataProvider(name="Auth", parallel=false)
		public static Object[][] getAuthDatial() throws Exception{
			return util.CsvUtil.getCsvData(testCasePath + "Auth.csv");
		}
		
}


