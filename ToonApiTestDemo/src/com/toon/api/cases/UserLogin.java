package com.toon.api.cases;

import java.util.Date;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.toon.api.base.HttpTestStep;
import com.toon.api.base.TestCase;
import com.toon.api.data.RunningData;
import com.toon.api.factory.CaseFactoryDemo;
import com.toon.api.utils.Common;
import com.toon.api.utils.Log;
import com.toon.api.utils.XJsonPathOprator;

public class UserLogin {

	private TestCase testCase;
	private boolean isSuccess;
	
//	@BeforeSuite
//	public void beforeSuite(){
//		
//		this.startDate=new Date();
//		Log.logInfo("start to run suite");
//		Log.logInfo("================================================");
//	}
	
	@BeforeMethod
	public void beforeMethod(){
		Common.createDevicesId();
	}
	
	@Test(priority=1)
	public void userLogin() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		this.testCase=CaseFactoryDemo.createLoginTestCase();
		Log.logInfo("测试用例:"+testCase.testCaseID+"开始执行");
		testCase.run();
		this.isSuccess=true;		
	}
	
	@AfterMethod
	public void afterMethod(){
		if(this.isSuccess){
			doExportProperties();
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
	
//	@AfterSuite
//	public void afterSuite(){
//		endDate=new Date();
//		Log.logInfo("================================================");
//		long runTime=this.endDate.getTime()-this.startDate.getTime();
//		String time=runTime>1000?runTime/1000+"s":runTime+"ms";
//		Log.logInfo("execution time:"+time);
//	}

	private void doExportProperties() {
		String jsonString=((HttpTestStep)this.testCase.getTestSteps().get(0)).responseInfos.get(1).toString();
		XJsonPathOprator xpath=new XJsonPathOprator(jsonString);
		RunningData.setProperty("ticket", xpath.getJsonValue("$.data.ticket"), true);
		RunningData.setProperty("token", xpath.getJsonValue("$.data.token"), true);
	}
}
