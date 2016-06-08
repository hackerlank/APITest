package com.toon.api.cases;

import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.toon.api.base.HttpTestStep;
import com.toon.api.base.TestCase;
import com.toon.api.data.RunningData;
import com.toon.api.dbproviders.DBProvider;
import com.toon.api.factory.CaseFactoryDemo;
import com.toon.api.utils.Common;
import com.toon.api.utils.Log;
import com.toon.api.utils.XJsonPathOprator;

public class democase {
	
	private Date startDate;
	private Date endDate;
	private TestCase testCase;
	private boolean isSuccess;
	
	@BeforeSuite
	public void beforeSuite(){
		Common.createDevicesId();
		this.startDate=new Date();
		Log.logInfo("start to run suite");
		Log.logInfo("================================================");
	}
	
	@BeforeTest
	@Parameters({"filePath","header"})
	public void setUp(String filePath,String header) throws Exception{
		Log.logInfo("start to create TestCases");
		CaseFactoryDemo.createTestCases(filePath,header);
		int number=DBProvider.cases==null?0:DBProvider.cases.length;
		Log.logInfo("this test has "+number+" testcases need to run");
		Log.logInfo("================================================");
		
	}
	
	@Test(dataProvider="cases",dataProviderClass=DBProvider.class,dependsOnMethods={"userLogin"})
	public void runCases(TestCase testCase) throws InterruptedException{
		this.testCase=testCase;
		//调试bug时使用
//		if(testCase.testCaseID.contains("queryDefaultRecommendGroupListNew")){
//			System.out.println("");
//		}
		this.testCase.refreshParams();
		Log.logInfo("测试用例:"+testCase.testCaseID+"开始执行");
		testCase.run();
		this.isSuccess=true;
	}
	
	@Test
	public void userLogin() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{	
		this.testCase=CaseFactoryDemo.createLoginTestCase();
		this.testCase.refreshParams();
		Log.logInfo("测试用例:"+testCase.testCaseID+"开始执行");
		testCase.run();
		this.isSuccess=true;
		doExportProperties();
	}
	
	@AfterMethod
	public void afterMethod(){
		if(this.isSuccess){
			this.testCase.flag=true;
			Log.logInfo("PASS");
		}else{
			this.testCase.flag=false;
			Log.logInfo("Fail");
		}
		//写入结果集
		RunningData.caseList.add(this.testCase);
		Log.logInfo("测试用例:"+this.testCase.testCaseID+"执行完毕");
		Log.logInfo("================================================");
	}
	
	@AfterTest
	public void afterTest(){	
		//test执行完毕后清除上一个test的case数据
		DBProvider.cases=null;
		Log.logInfo("================================================");
	}
	
	@AfterSuite
	public void afterSuite(){
		endDate=new Date();
		Log.logInfo("================================================");
		long runTime=this.endDate.getTime()-this.startDate.getTime();
		String time=runTime>1000?runTime/1000+"s":runTime+"ms";
		Log.logInfo("execution time:"+time);
	}
	
	private void doExportProperties() {
		String jsonString=((HttpTestStep)this.testCase.getTestSteps().get(0)).responseInfos.get(1).toString();
		XJsonPathOprator xpath=new XJsonPathOprator(jsonString);
		RunningData.setProperty("ticket", xpath.getJsonValue("$.data.ticket"), true);
		RunningData.setProperty("token", xpath.getJsonValue("$.data.token"), true);
	}
}
