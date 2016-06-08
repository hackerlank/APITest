package com.toon.dubboTest.PassportInterface.run;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.JsonUtil;
import util.StringUtil;

import javax.annotation.Resource;
import com.toon.dataprovider.ToonCardTemplateProvider;
import com.systoon.passport.inter.ICardTemplateManager;
import com.systoon.passport.parameter.CardFieldConfigInfo;
import com.systoon.passport.parameter.CardFieldConfigListResult;
import com.systoon.passport.pojo.CardFieldConfig;

@ContextConfiguration(locations= "classpath:applicationContext.xml")
public class CardFieldTemplateTest extends AbstractTestNGSpringContextTests {
  private static final Log logger=LogFactory.getLog(CardFieldTemplateTest.class);
  
  @Resource (name="AllCardFieldConfigService")
  private ICardTemplateManager cardTemplateManager;
  
  private CardFieldConfigInfo cardFieldInfo;
  private CardFieldConfig cardFieldConfig;
  
 
	
	
	
	@Test(dataProvider="card_fieldTemplate", dataProviderClass=ToonCardTemplateProvider.class, enabled=true, description="获取名片字段末班测试")
	public void getCardFieldTemplateTest(String caseId, String title, String values, String cardType, String expContent, String comments)
	{
		CardFieldConfigListResult result=null;
		
		if(values.equalsIgnoreCase("null"))
		{
			cardFieldInfo=null;
			cardFieldConfig=null;
			result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
		}
		else if(values.equalsIgnoreCase("-1"))
		{
			cardFieldInfo=new CardFieldConfigInfo();
			cardFieldConfig=null;
			cardFieldInfo.setCardFieldConfig(cardFieldConfig);
			result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
		}
		else if(values.equalsIgnoreCase("-2"))
		{
			cardFieldInfo=new CardFieldConfigInfo();
			cardFieldConfig=new CardFieldConfig();
			cardFieldConfig.setUpdateTime(null);
			cardFieldInfo.setCardFieldConfig(cardFieldConfig);
			result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
		}
		else if(values.equalsIgnoreCase("-100000"))
		{
			long updateTime=Long.parseLong(values);
			cardFieldInfo=new CardFieldConfigInfo();
			cardFieldConfig=new CardFieldConfig();
			cardFieldConfig.setUpdateTime(updateTime);
			cardFieldInfo.setCardFieldConfig(cardFieldConfig);
			result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
		}
		else if(values.equalsIgnoreCase("-3"))
		{
			long updateTime=-1l;
			cardFieldInfo=new CardFieldConfigInfo();
			cardFieldConfig=new CardFieldConfig();
			cardFieldConfig.setUpdateTime(updateTime);
			int cType=Integer.parseInt(cardType);
			cardFieldConfig.setCardType(cType);
			cardFieldInfo.setCardFieldConfig(cardFieldConfig);
			switch(cType)
			{
				case 1:
					result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
					break;
				case 2:
					result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
					break;
				case 3:
					result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
					break;
				case 4:
					result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
					break;
				default:
					break;
			}
			
		}else
		{
			long updateTime=Long.parseLong(values);
			cardFieldInfo=new CardFieldConfigInfo();
			cardFieldConfig=new CardFieldConfig();
			cardFieldConfig.setUpdateTime(updateTime);
			cardFieldInfo.setCardFieldConfig(cardFieldConfig);
			result=cardTemplateManager.getAllCardFieldConfig(cardFieldInfo);
		}
		
		
		String actualResult=JsonUtil.getJsonString(result);
		System.out.println("caseId: #"+caseId+"\ttitle");
		System.out.println(comments);
		System.out.println(expContent);
		System.out.println(actualResult);
		
		StringUtil.AssertByRegEx(actualResult, expContent, "获取名片字段配置信息");
		
	}
  
}
