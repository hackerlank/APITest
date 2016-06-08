package com.toon.api.test.testNg;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.toon.api.test.testNgDataProvider.*;
import com.toon.api.test.testcase.ApiTestCase;
import com.toon.api.test.humun.*;


public class ApiTestRun {

	@BeforeTest
	public void setUp() throws IOException {
		ApiTestCase.updateTokenValue();
	}
		
	@Test(dataProvider="user",  dataProviderClass=DProvider.class, enabled = true, description="用户相关接口")
	public void testUser(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	// 该接口暂时不测试
	@Test(dataProvider="org", dataProviderClass=DProvider.class, enabled = false, description="组织相关接口")
	public void testOrg(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	//  该接口失效
	@Test(dataProvider="communicate", dataProviderClass=DProvider.class, enabled = false, description="沟通相关接口")
	public void testCommunicate(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="plugin", dataProviderClass=DProvider.class, enabled = true, description="插件面板相关接口")
	public void testPlugin(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="card", dataProviderClass=DProvider.class,enabled=true,description="名片相关接口")
	public void testCard(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception {
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	// 该接口失效
	@Test(dataProvider="activity", dataProviderClass=DProvider.class, enabled=false,description="活动相关接口")
	public void testActivity(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
			
	@Test(dataProvider="group",dataProviderClass=DProvider.class,enabled=true,description="群组相关接口")
	public void testGroup(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="found",dataProviderClass=DProvider.class,enabled=true,description="周边相关接口")
	public void testFound(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="version",dataProviderClass=DProvider.class,enabled=true,description="版本相关接口")
	public void testVersion(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	@Test(dataProvider="contact",dataProviderClass=DProvider.class,enabled=true,description="通讯录相关接口")
	public void testContact(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="groupchat",dataProviderClass=DProvider.class,enabled=true,description="群聊相关接口")
	public void testGroupchat(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="feed",dataProviderClass=DProvider.class,enabled=true,description="feed相关接口")
	public void testFeed(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="router",dataProviderClass=DProvider.class,enabled=true,description="路由器相关接口")
	public void testRouter(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="Auth",dataProviderClass=DProvider.class,enabled=true,description="认证相关接口")
	public void testAuth(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	
	@Test(description="性能测试", enabled=false )
	public void testJrunner(){
		ApiTestRun_JRunner.main(null);
	}
		
	public void testRun(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		String[] httpMsg = ApiTestHttp.testApi(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token,expContent);
		String ruturnMsg = httpMsg[0];
		String requestMsg = httpMsg[1];
//		String patternString = ".*" + expContent + ".*";
		util.StringUtil.AssertByRegEx(ruturnMsg, expContent, requestMsg);
	}
	
	
}
