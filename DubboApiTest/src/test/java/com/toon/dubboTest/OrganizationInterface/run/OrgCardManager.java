package com.toon.dubboTest.OrganizationInterface.run;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.JsonUtil;
import util.StringUtil;

import com.systoon.organization.inter.IOrgCardManager;
import com.systoon.organization.inter.IOrgMemberCardManager;
import com.systoon.organization.parameter.CardListResult;
import com.systoon.organization.parameter.IdListInfo;
import com.systoon.organization.parameter.OrgCardInfo;
import com.systoon.organization.parameter.OrgCardListResult;
import com.systoon.organization.parameter.OrgCardResult;
import com.systoon.organization.parameter.OrgCustomerListResult;
import com.systoon.organization.parameter.OrgExternalContactsInfo;
import com.systoon.organization.parameter.OrgIndustryAndCategoryResult;
import com.systoon.organization.parameter.OrgMemberCardInfo;
import com.systoon.organization.parameter.OrgMemberCardListResult;
import com.systoon.organization.parameter.OrgMemberCardResult;
import com.systoon.organization.parameter.ParamInfo;
import com.systoon.organization.parameter.UserCardRelationListResult;
import com.systoon.organization.pojo.OrgCard;
import com.systoon.organization.pojo.OrgMemberCard;
import com.systoon.organization.pojo.UserCardRelation;
import com.systoon.parameter.ServiceResult;
import com.toon.dataprovider.OrgCardDataProvider;


@ContextConfiguration(locations = {"classpath:applicationContextOrganization.xml"} )
public class OrgCardManager extends AbstractTestNGSpringContextTests {

	@Resource (name="organizationService" )
	private IOrgCardManager orgCardManager;
	
	
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
	
	@Test(dataProvider="getParamInfo", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取行业分类信息")
	public void getOrgIndustryAndCategory(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		ParamInfo paramInfo;
		if(tag.equalsIgnoreCase("null"))
		{
			paramInfo=null;
		}
		else if(tag.equalsIgnoreCase("null1"))
		{
			paramInfo=new ParamInfo();
		}
		else 
		{
			paramInfo=new ParamInfo();
			Long updateTime=1423222428162l;
			paramInfo.setUpdateTime(updateTime);
			
		}
		OrgIndustryAndCategoryResult result=orgCardManager.getOrgIndustryAndCategoryList(paramInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, "验证行业及分类信息");
	}
	
	//
	@Test(dataProvider="getOrgCardInfo", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="创建组织名片测试")
	public void AddOrgCard(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo;
		OrgCard orgCard;
		OrgCardResult result = null;
		if(tag.equalsIgnoreCase("fail"))
		{
			orgCardInfo=null;
			result=orgCardManager.addOrgCard(orgCardInfo);
		}else if(tag.equalsIgnoreCase("success1"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCardInfo.setOrgCard(orgCard);
			result=orgCardManager.addOrgCard(orgCardInfo);
		}else if(tag.equalsIgnoreCase("success2"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setBigImage("test");
			orgCard.setCategory("test");
			orgCard.setDisplayName("test");
			orgCard.setIndustry("test");
			orgCardInfo.setOrgCard(orgCard);
			result=orgCardManager.addOrgCard(orgCardInfo);
		}
		
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	/*
	 * Update Org card test
	 */
	@Test(dataProvider="updateOrgCardInfo", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="更新组织名片测试")
	public void updateOrgCard(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo;
		OrgCard orgCard;
		OrgCardResult result = null;
		if(tag.equalsIgnoreCase("fail"))
		{
			orgCardInfo=null;
			result=orgCardManager.updateOrgCard(orgCardInfo);
		}else if(tag.equalsIgnoreCase("fail1"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
	 		orgCard.setDisplayName("JasonZhu");
	 		orgCardInfo.setOrgCard(orgCard);
			result=orgCardManager.updateOrgCard(orgCardInfo);
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setCardId(1445309693352545l);
			expCon=param+Long.toString(System.currentTimeMillis()/1000);
			orgCard.setIntroduction(expCon);
			orgCardInfo.setOrgCard(orgCard);
			result=orgCardManager.updateOrgCard(orgCardInfo);
		}
		
		
		System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	/*
	 * Get Org Card by Org Card id and userId
	 */
	@Test(dataProvider="getOrgCardById", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织名片测试-通过组织名片id")
	public void getOrgCardViaId(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo = null;
		OrgCard orgCard;
		OrgCardResult result = null;
		if(tag.equalsIgnoreCase("fail"))
		{}else if(tag.equalsIgnoreCase("fail1"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
	 		orgCardInfo.setOrgCard(orgCard);
		
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setCardId(Long.parseLong(param));
			orgCard.setIntroduction(expCon);
			orgCardInfo.setOrgCard(orgCard);
		}
		
		result=orgCardManager.getOrgCardById(orgCardInfo);
		System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	/*
	 * Get Simple Org Card by Org Card id and userId
	 */
	@Test(dataProvider="getOrgCardById", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织名片测试-通过组织名片id")
	public void getSimpleOrgCardViaId(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo = null;
		OrgCard orgCard;
		OrgCardResult result = null;
		if(tag.equalsIgnoreCase("fail"))
		{}else if(tag.equalsIgnoreCase("fail1"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
	 		orgCardInfo.setOrgCard(orgCard);
		
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setCardId(Long.parseLong(param));
			orgCard.setIntroduction(expCon);
			orgCardInfo.setOrgCard(orgCard);
		}
		
		result=orgCardManager.getSimpleOrgCardById(orgCardInfo);
		System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	/*
	 * get Org Card list by org Card Id list
	 */
	@Test(dataProvider="getOrgCardListByIdList", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织名片id列表获取组织名片")
	public void getOrgCardListByIdListTest(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		IdListInfo idListInfo=null;
		List<Long> idList=new ArrayList<Long>();
		if(tag.equalsIgnoreCase("fail"))
		{
		}
		else if(tag.equalsIgnoreCase("success"))
		{
			idListInfo=new IdListInfo();
			String[] ids=param.split("-");
			for(String s : ids)
			{
				idList.add(Long.parseLong(s));
			}
			idListInfo.setIdList(idList);
		}
		
		OrgCardListResult result=orgCardManager.getOrgCardListByIdList(idListInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, "获取组织名片id列表测试");
		
	}

	@Test(dataProvider="getOrgCardListByIdList", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织名片id列表获取简单组织名片")
	public void getSimpleOrgCardListByIdListTest(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		IdListInfo idListInfo=null;
		List<Long> idList=new ArrayList<Long>();
		if(tag.equalsIgnoreCase("fail"))
		{
		}
		else if(tag.equalsIgnoreCase("success"))
		{
			idListInfo=new IdListInfo();
			String[] ids=param.split("-");
			for(String s : ids)
			{
				idList.add(Long.parseLong(s));
			}
			idListInfo.setIdList(idList);
		}
		
		OrgCardListResult result=orgCardManager.getSimpleOrgCardListByIdList(idListInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, "获取简单组织名片id列表测试");
		
	}

	@Test(dataProvider="getOrgCardListByUserId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过用户id获取组织名片id")
	public void getOrgCardListByUserIdTest(String caseId, String title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo=null;
		OrgCard orgCard;
		OrgCardListResult result;
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(Long.parseLong(param));
			orgCardInfo.setOrgCard(orgCard);
		}
		
		result = orgCardManager.getOrgCardListByUserId(orgCardInfo);
		System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getOrgCardAndMemberCardListByUserId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织及成员名片")
	public void getOrgCardAndMemberCardListByUserIdTest(String caseId, String title, String tag, String param, String updateTime, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo=null;
		OrgCard orgCard=null;
		CardListResult result;
		
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			if(updateTime.equalsIgnoreCase("null"))
			{
			}else 
			{
				orgCard.setUpdateTime(Long.parseLong(updateTime));
			}
			orgCard.setUserId(Long.parseLong(param));
			orgCardInfo.setOrgCard(orgCard);
		}
		
		result=orgCardManager.getOrgCardAndMemberCardListByUserId(orgCardInfo);
		//System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	
	@Test(dataProvider="getUserCardRelationListByUserId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织关系成员名片")
	public void getUserCardRelationListByUserIdTest(String caseId, String title, String tag, String param, String updateTime, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo=null;
		OrgCard orgCard=null;
		UserCardRelationListResult result;
		
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			if(updateTime.equalsIgnoreCase("null"))
			{
			}else 
			{
				orgCard.setUpdateTime(Long.parseLong(updateTime));
			}
			orgCard.setUserId(Long.parseLong(param));
			orgCardInfo.setOrgCard(orgCard);
		}
		
		result=orgCardManager.getUserCardRelationListByUserId(orgCardInfo);
		//System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	@Test(dataProvider="getUserCardRelationListByUserCardRelation", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织相关名片")
	public void getUserCardRelationListByUserCardRelationTest(String caseId, String title, String tag, String param, String updateTime, String expCon, String comments)
	{
		UserCardRelation userCardRelation=null;
		UserCardRelationListResult result=null;
		if(tag.equalsIgnoreCase("fail"))
		{}else if(tag.equalsIgnoreCase("success"))
		{
			userCardRelation=new UserCardRelation();
			userCardRelation.setUserId(Long.parseLong(param));
			if(!updateTime.equalsIgnoreCase("null"))
			{
				userCardRelation.setUpdateTime(Long.parseLong(updateTime));
			}
			
		}
		
		result=orgCardManager.getUserCardRelationListByUserCardRelation(userCardRelation);
		
		//System.out.println("Comments: "+comments);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getOrgCustomerListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织名片定制列表")
	public void getOrgCustomerListByOrgIdTest(String caseId, String Title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo=null;
		OrgCard orgCard=null;
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setCardId(Long.parseLong(param));
			orgCardInfo.setOrgCard(orgCard);
		}
		
		OrgCustomerListResult result=orgCardManager.getOrgCustomerListByOrgId(orgCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	@Test(dataProvider="getOrgExternalContactsByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取外部联系测试")
	public void getOrgExternalContactsByOrgIdTest(String caseId, String Title, String tag, String param, String expCon, String comments)
	{
		OrgCardInfo orgCardInfo=null;
		OrgCard orgCard=null;
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("success"))
		{
			orgCardInfo=new OrgCardInfo();
			orgCard=new OrgCard();
			orgCard.setUserId(324527l);
			orgCard.setCardId(Long.parseLong(param));
			orgCardInfo.setOrgCard(orgCard);
		}
		
		OrgMemberCardListResult result=orgCardManager.getOrgExternalContactsByOrgId(orgCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	@Test(dataProvider="saveOrgExternalContacts", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="存储组织外部联系")
	public void saveOrgExternalContactsTest(String caseId, String title, String tag, String orgCardId, String CardList, String epxCon, String comments)
	{
		OrgExternalContactsInfo orgExternalContactsInfo=null;
		
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("fail1"))
		{
			orgExternalContactsInfo=new OrgExternalContactsInfo();
			orgExternalContactsInfo.setOrgCardId(Long.parseLong(orgCardId));
		}else
		{
			orgExternalContactsInfo=new OrgExternalContactsInfo();
			orgExternalContactsInfo.setOrgCardId(Long.parseLong(orgCardId));
			List<Long> list=new ArrayList<Long>();
			list.add(Long.parseLong(CardList));
			orgExternalContactsInfo.setCardList(list);
		}
		ServiceResult result=orgCardManager.saveOrgExternalContacts(orgExternalContactsInfo);
		
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), epxCon, comments);
	}
	
	@Test(dataProvider="deleteOrgExternalContacts", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="删除组织外部联系")
	public void deleteOrgExternalContactsTest(String caseId, String title, String tag, String orgCardId, String CardList, String epxCon, String comments)
	{
		OrgExternalContactsInfo orgExternalContactsInfo=null;
		
		if(tag.equalsIgnoreCase("fail"))
		{
		}else if(tag.equalsIgnoreCase("fail1"))
		{
			orgExternalContactsInfo=new OrgExternalContactsInfo();
			orgExternalContactsInfo.setOrgCardId(Long.parseLong(orgCardId));
		}else
		{
			orgExternalContactsInfo=new OrgExternalContactsInfo();
			orgExternalContactsInfo.setOrgCardId(Long.parseLong(orgCardId));
			List<Long> list=new ArrayList<Long>();
			list.add(Long.parseLong(CardList));
			orgExternalContactsInfo.setCardList(list);
		}
		ServiceResult result=orgCardManager.deleteOrgExternalContacts(orgExternalContactsInfo);
		
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), epxCon, comments);
	}

	
	
}
