package com.toon.dubboTest.PassportInterface.run;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.annotations.Test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.ContextConfiguration;

import util.JsonUtil;
import util.StringUtil;

import javax.annotation.Resource;

import com.toon.dataprovider.ToonDeleteUserCollectProvider;
import com.toon.dataprovider.ToonGetAllUserCollectsDataProvider;
import com.toon.dataprovider.ToonUserCollectProvider;
import com.systoon.parameter.ServiceResult;
import com.systoon.passport.inter.IUserCollectManager;
import com.systoon.passport.parameter.UserCollectInfo;
import com.systoon.passport.parameter.UserCollectListResult;
import com.systoon.passport.parameter.UserCollectResult;
import com.systoon.passport.pojo.UserCollect;

@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserCollectManagerTest extends AbstractTestNGSpringContextTests
{
	private static final Log log=LogFactory.getLog(UserCollectManagerTest.class);
	
	@Resource(name="UserCollectManagerService")
	private IUserCollectManager userCollectManager;
	
	@Test(dataProvider="getUserCollectInfo", dataProviderClass=ToonUserCollectProvider.class, enabled=true, description="用户收藏-添加测试")
	public void test_addUserCollect(String caseId, String title, String tag, String input, String expContent, String comments)
	{
		UserCollectInfo userCollectInfo;
		UserCollect userCollect;
		UserCollectResult result = null;
		
		if(tag.equalsIgnoreCase("fail"))
		{
			if(input.equalsIgnoreCase("null1"))
			{
				userCollectInfo=null;
				result=userCollectManager.addUserCollect(userCollectInfo);
			}else if(input.equalsIgnoreCase("null2"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=null;
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.addUserCollect(userCollectInfo);
			}else if(input.equalsIgnoreCase("null3"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUserId(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.addUserCollect(userCollectInfo);
			}else if(input.equalsIgnoreCase("null4"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUserId(324527l);
				userCollect.setType(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.addUserCollect(userCollectInfo);
			}else if(input.equalsIgnoreCase("null5"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUserId(324527l);
				userCollect.setObjectId(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.addUserCollect(userCollectInfo);
			}
		}else if(tag.equalsIgnoreCase("success"))
		{
			userCollectInfo=new UserCollectInfo();
			userCollect=new UserCollect();
			userCollect.setType(1);
			userCollect.setUserId(324527l);
			userCollect.setObjectId(input+""+System.currentTimeMillis()/1000);
			userCollectInfo.setUserCollect(userCollect);
			result=userCollectManager.addUserCollect(userCollectInfo);
		}else if(tag.equalsIgnoreCase("dup"))
		{
			userCollectInfo=new UserCollectInfo();
			userCollect=new UserCollect();
			userCollect.setUserId(324527l);
			userCollect.setType(1);
			userCollect.setObjectId(input);
			userCollectInfo.setUserCollect(userCollect);
			result=userCollectManager.addUserCollect(userCollectInfo);
		}else if(tag.equalsIgnoreCase("all"))
		{
			userCollectInfo=getCompleteUserCollectInfo();
			result=userCollectManager.addUserCollect(userCollectInfo);
		}
		
		System.out.println("CaseId: "+caseId+"\t Title: "+title);
		String actualResult=JsonUtil.getJsonString(result);
		System.out.println("Actual Result: \n"+actualResult);
		StringUtil.AssertByRegEx(actualResult, expContent, "用户收场- 添加测试");
	}

	@Test(dataProvider="deleteUserCollectProvider", dataProviderClass=ToonDeleteUserCollectProvider.class, enabled=true, description="用户收藏测试-删除收藏")
	public void test_deleteUserCollect(String caseId, String title, String tag, String info, String epxContent, String comments) throws InterruptedException
	{
		UserCollectInfo userCollectInfo;
		UserCollect userCollect;
		ServiceResult result=null;
		if(tag.equalsIgnoreCase("fail"))
		{
			if(info.equalsIgnoreCase("null1"))
			{
				userCollectInfo=null;
				userCollect=null;
				result=userCollectManager.deleteUserCollect(userCollectInfo);
			}else if(info.equalsIgnoreCase("null2"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=null;
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.deleteUserCollect(userCollectInfo);
			}else if(info.equalsIgnoreCase("null3"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setCollectId(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.deleteUserCollect(userCollectInfo);
			}else if(info.equalsIgnoreCase("null4"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setCollectId(9999999999999l);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.deleteUserCollect(userCollectInfo);
			}
		}else if(tag.equalsIgnoreCase("success"))
		{
			UserCollectInfo userCollectInfo1=getCompleteUserCollectInfo();
			UserCollectResult ucresult=userCollectManager.addUserCollect(userCollectInfo1);
			Thread.sleep(1000);
			String collectId=getCreatedCollectId(JsonUtil.getJsonString(ucresult));
			System.out.println("=========================================\n"+collectId);
			userCollectInfo=new UserCollectInfo();
			userCollect=new UserCollect();
			userCollect.setCollectId(Long.parseLong(collectId));
			userCollect.setUserId(userCollectInfo1.getUserCollect().getUserId());
			userCollectInfo.setUserCollect(userCollect);
			result=userCollectManager.deleteUserCollect(userCollectInfo);
		}
		
		
		String actualResult=JsonUtil.getJsonString(result);
		System.out.println("CaseId: "+caseId+"\t title: "+title);
		System.out.println("The actual result is: \n"+actualResult);
		StringUtil.AssertByRegEx(actualResult, epxContent, "删除用户收藏测试");

	}
	
	@Test(dataProvider="getAllUserCollectsData", dataProviderClass=ToonGetAllUserCollectsDataProvider.class, enabled=true, description="")
	public void test_getAllUserCollects(String caseId,String title, String tag, String info, String expContent, String comments)
	{
		UserCollectInfo userCollectInfo;
		UserCollect userCollect;
		UserCollectListResult result=null;
		
		if(tag.equalsIgnoreCase("fail"))
		{
			if(info.equalsIgnoreCase("null1"))
			{
				userCollectInfo=null;
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}else if(info.equalsIgnoreCase("null2"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=null;
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}else if(info.equalsIgnoreCase("null3"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUserId(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}
		}else if(tag.equalsIgnoreCase("success"))
		{
			if(info.equalsIgnoreCase("no"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUserId(123456l);
				userCollect.setUpdateTime(null);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}else if(info.equalsIgnoreCase("-1"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUpdateTime(Long.parseLong(info));
				userCollect.setUserId(123456l);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}else if(info.equalsIgnoreCase("0"))
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUpdateTime(Long.parseLong(info));
				userCollect.setUserId(123456l);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}else
			{
				userCollectInfo=new UserCollectInfo();
				userCollect=new UserCollect();
				userCollect.setUpdateTime(Long.parseLong(info));
				userCollect.setUserId(123456l);
				userCollectInfo.setUserCollect(userCollect);
				result=userCollectManager.getAllUserCollects(userCollectInfo);
			}
		}
		
		String actualResult=JsonUtil.getJsonString(result);
		System.out.println("CaseId: "+caseId+"\t Title: "+title);
		System.out.println("Actual Result: \n"+actualResult);
		StringUtil.AssertByRegEx(actualResult, expContent, "用户收场- 添加测试");
	}
	
	private static String getCreatedCollectId(String result)
	{
		String patt="collectId\":(.*?),\"userId";
		Pattern p=Pattern.compile(patt);
		Matcher match=p.matcher(result);
		if(match.find())
		{
			return match.group(1);
		}
		else 
		{
			return "Sorry, could not find the CollectId, please check the response";
		}
	}
	
	private static UserCollectInfo getCompleteUserCollectInfo()
	{
		UserCollectInfo userCollectInfo=new UserCollectInfo();
		UserCollect userCollect=new UserCollect();
		userCollect.setUserId(324527l);
		userCollect.setType(1);
		String objectId="Dubbo API test+rnd";
		objectId=objectId.replace("+rnd",""+(System.currentTimeMillis()/1000));
		userCollect.setObjectId(objectId);
		userCollect.setContentText("TestData ContentText");
		//userCollect.setCreateTime(System.currentTimeMillis()/1000);
		userCollect.setFromAvatar("http://www.baidu.com");
		userCollect.setFromCardId(123456l);
		userCollect.setFromCardType(2);
		userCollect.setFromFeedId("c_123456");
		userCollect.setFromNickname("Beetle");
		userCollect.setPictureUrl("http://this is pro tester.com");
		//userCollect.setStatus(0);
		userCollect.setTargetUrl("www.targetURL.com");
		userCollect.setTitle("This is the Title");
		userCollect.setType(1);
		//userCollect.setUpdateTime(System.currentTimeMillis());
		userCollect.setUserId(123456l);
		
		userCollectInfo.setUserCollect(userCollect);
		return userCollectInfo;
		
	}
}
