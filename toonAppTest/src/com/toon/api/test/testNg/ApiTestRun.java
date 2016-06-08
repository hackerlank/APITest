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
		
	@Test(dataProvider="user",  dataProviderClass=DProvider.class, enabled = true, description="�û���ؽӿ�")
	public void testUser(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	// �ýӿ���ʱ������
	@Test(dataProvider="org", dataProviderClass=DProvider.class, enabled = false, description="��֯��ؽӿ�")
	public void testOrg(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	//  �ýӿ�ʧЧ
	@Test(dataProvider="communicate", dataProviderClass=DProvider.class, enabled = false, description="��ͨ��ؽӿ�")
	public void testCommunicate(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="plugin", dataProviderClass=DProvider.class, enabled = true, description="��������ؽӿ�")
	public void testPlugin(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token,String expContent) throws Exception {		
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="card", dataProviderClass=DProvider.class,enabled=true,description="��Ƭ��ؽӿ�")
	public void testCard(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception {
		testRun(caseId, caseName, interfaceType, testURL, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	// �ýӿ�ʧЧ
	@Test(dataProvider="activity", dataProviderClass=DProvider.class, enabled=false,description="���ؽӿ�")
	public void testActivity(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
			
	@Test(dataProvider="group",dataProviderClass=DProvider.class,enabled=true,description="Ⱥ����ؽӿ�")
	public void testGroup(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="found",dataProviderClass=DProvider.class,enabled=true,description="�ܱ���ؽӿ�")
	public void testFound(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="version",dataProviderClass=DProvider.class,enabled=true,description="�汾��ؽӿ�")
	public void testVersion(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	@Test(dataProvider="contact",dataProviderClass=DProvider.class,enabled=true,description="ͨѶ¼��ؽӿ�")
	public void testContact(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="groupchat",dataProviderClass=DProvider.class,enabled=true,description="Ⱥ����ؽӿ�")
	public void testGroupchat(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="feed",dataProviderClass=DProvider.class,enabled=true,description="feed��ؽӿ�")
	public void testFeed(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="router",dataProviderClass=DProvider.class,enabled=true,description="·������ؽӿ�")
	public void testRouter(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	@Test(dataProvider="Auth",dataProviderClass=DProvider.class,enabled=true,description="��֤��ؽӿ�")
	public void testAuth(String caseId, String caseName, String interfaceType, String testUrl, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws Exception{
		testRun(caseId, caseName, interfaceType, testUrl, deviceId, platform, platformVersion, appVersion, mobile, bizKey, if_token, expContent);
	}
	
	
	@Test(description="���ܲ���", enabled=false )
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
