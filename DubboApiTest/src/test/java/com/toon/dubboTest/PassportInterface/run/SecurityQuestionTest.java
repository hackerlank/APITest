package com.toon.dubboTest.PassportInterface.run;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import util.JsonUtil;

import com.systoon.passport.inter.ISecurityQuestionManager;
import com.systoon.passport.parameter.SecurityQuestionInfo;
import com.systoon.passport.parameter.SecurityQuestionResult;
import com.systoon.passport.parameter.SecurityQuestionListResult;
import com.systoon.passport.pojo.SecurityQuestion;
import com.toon.dataprovider.ToonSecurityQuestionDataProvider;

import javax.annotation.Resource;

@ContextConfiguration(locations = {"classpath:applicationContext.xml"} )
//public class SecurityQuestionTest{
public class SecurityQuestionTest extends AbstractTestNGSpringContextTests{
	private static final Log logger=LogFactory.getLog(SecurityQuestionTest.class);
	

	@Resource(name="securityQuestionService")
	private ISecurityQuestionManager securityQuestionManager;
	//static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private SecurityQuestion question;
	private SecurityQuestionInfo questionInfo;


	
	@BeforeMethod
	public void init()
	{
		System.out.println("init...");
	}
	
	
	@AfterMethod
	public void destroy()
	{
		System.out.println("destroy...");
	}
	
	@Test(dataProvider="getSecurityQuestionUpdateTime", dataProviderClass=ToonSecurityQuestionDataProvider.class, enabled=true, description="获取安全问题测试")
	public void getSecurityQuestions(String caseId, String updateTime, String expContent)
	{
		SecurityQuestionListResult result=null;
		if(updateTime.equalsIgnoreCase("null"))
		{
			result=securityQuestionManager.querySecurityQuestionList(null);
			
		}
		else
		{
			question=new SecurityQuestion();
			question.setUpdateTime(Long.parseLong(updateTime));
			questionInfo=new SecurityQuestionInfo();
			questionInfo.setSecurityQuestion(question);
			result=securityQuestionManager.querySecurityQuestionList(questionInfo);
			
		}
		String actualResult=JsonUtil.getJsonString(result);
		
		System.out.println("CaseId: "+caseId);
		System.out.println(actualResult);
		logger.info("CaseId: "+caseId);
		logger.info(actualResult);
		
		util.StringUtil.AssertByRegEx(actualResult, expContent, "获取安全问题");
	
	}
	
}
