package com.toon.dubboTest.OrganizationInterface.run;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.JsonUtil;
import util.StringUtil;

import javax.annotation.Resource;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.systoon.organization.inter.IOrgMemberCardManager;
import com.systoon.organization.parameter.IdListInfo;
import com.systoon.organization.parameter.IdListRelationInfo;
import com.systoon.organization.parameter.IdListResult;
import com.systoon.organization.parameter.MemberNodeRelationInfo;
import com.systoon.organization.parameter.MemberNodeRelationListResult;
import com.systoon.organization.parameter.OrgCustomerListResult;
import com.systoon.organization.parameter.OrgMemberCardInfo;
import com.systoon.organization.parameter.OrgMemberCardListResult;
import com.systoon.organization.parameter.OrgMemberCardResult;
import com.systoon.organization.pojo.MemberNodeRelation;
import com.systoon.organization.pojo.OrgMemberCard;
import com.systoon.parameter.ServiceResult;
import com.toon.dataprovider.OrgCardDataProvider;


@ContextConfiguration(locations = {"classpath:applicationContextOrganization.xml"} )
public class OrgMemberCardManagerTest extends AbstractTestNGSpringContextTests{
	
	@Resource(name="orgMemberService")
	private IOrgMemberCardManager orgMemberCardManager;
	
	@BeforeMethod
	public void init()
	{
		System.out.println("Init...");
	}
	

	public static void resetUseStatusForOrgMemberCard() throws ClassNotFoundException, SQLException
	{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://172.28.6.153:8066/Organization";
		String user="tmycat";
		String pws="Orguc.mycat";
		String sql="update org_member_card set use_status=1 where card_id=338042 and org_id=338041";
		Class.forName(driver);
		Connection conn=(Connection) DriverManager.getConnection(url,user,pws);
		
		Statement st=(Statement) conn.createStatement();
		int count=st.executeUpdate(sql);
		System.out.println(count);
	}
	
	@AfterMethod
	public void destroy()
	{
		System.out.println("destroy...");
	}
	
	
	//Update Organization Member Card
	@Test(dataProvider="updateOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="更新组织成员名片")
	public void updateOrgMemberCardTest(String caseId, String title, String tag, String cardId, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
		OrgMemberCard orgMemberCard=null;
		System.out.println("result=");
	
		if(tag.equalsIgnoreCase("fail1"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setOrgId(Long.parseLong(orgId));
		}else if(tag.equalsIgnoreCase("fail2"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
		}else
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
			orgMemberCard.setOrgId(Long.parseLong(orgId));
		}
		
		
		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.updateOrgMemberCard(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//Delete Organization Member card
	@Test(dataProvider="deleteOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="删除组织名片成员测试")
	public void deleteOrgMemberCardTest(String caseId, String title, String tag, String cardId, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
		OrgMemberCard orgMemberCard=null;
		
		if(tag.equalsIgnoreCase("fail1"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setOrgId(Long.parseLong(orgId));
		}else if(tag.equalsIgnoreCase("fail2"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
		}else
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
			orgMemberCard.setOrgId(Long.parseLong(orgId));
		}
		
		
		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		ServiceResult result=orgMemberCardManager.deleteOrgMemberCard(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getOrgMemberCardById", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取组织名片成员")
	public void getOrgMemberCardByIdTest(String caseId, String title, String tag, String cardId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
		OrgMemberCard orgMemberCard=null;
		
		if(tag.equalsIgnoreCase("fail1"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
		}else
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
		}
		
		
		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.getOrgMemberCardById(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getOrgMemberCardById", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取简单组织名片成员")
	public void getSimpleOrgMemberCardByIdTest(String caseId, String title, String tag, String cardId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
		OrgMemberCard orgMemberCard=null;
		
		if(tag.equalsIgnoreCase("fail1"))
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
		}else
		{
			orgMemberCardInfo=new OrgMemberCardInfo();
			orgMemberCard=new OrgMemberCard();
			orgMemberCard.setCardId(Long.parseLong(cardId));
		}
		
		
		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.getSimpleOrgMemberCardById(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getOrgMemberCardListByIdList", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过id列表获取组织成员名片")
	public void getOrgMemberCardListByIdListTest(String caseId, String title, String tag, String cardId, String expCon, String comments)
	{
		IdListInfo idCardList=new IdListInfo();
		List<Long> list=new ArrayList<Long>();
		
		if(tag.equalsIgnoreCase("fail1"))
		{
		}else
		{
			
			list.add(Long.parseLong(cardId));
		}
		
		
		
		idCardList.setIdList(list);
		OrgMemberCardListResult result=orgMemberCardManager.getOrgMemberCardListByIdList(idCardList);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	@Test(dataProvider="getOrgMemberCardListByIdList", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过id列表获取组织成员名片")
	public void getSimpleOrgMemberCardListByIdListTest(String caseId, String title, String tag, String cardId, String expCon, String comments)
	{
		IdListInfo idCardList=new IdListInfo();
		List<Long> list=new ArrayList<Long>();
		
		if(tag.equalsIgnoreCase("fail1"))
		{
		}else
		{
			
			list.add(Long.parseLong(cardId));
		}
		
		
		
		idCardList.setIdList(list);
		OrgMemberCardListResult result=orgMemberCardManager.getSimpleOrgMemberCardListByIdList(idCardList);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	//Added by zhujiansheng, need to figure out how to deal with the re-grant the member card
	@Test(dataProvider="grantOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="授权组织成员测试")
	public void grantOrgMemberCardTest(String caseId, String title, String tag, String userId, String cardId, String orgId, String expCon, String comments) 
	{
		OrgMemberCardInfo orgMemberCardInfo=new OrgMemberCardInfo();
 		OrgMemberCard orgMemberCard=new OrgMemberCard(); 
 		
 		if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 		}else if(tag.equalsIgnoreCase("fail1"))
 		{

 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCard.setUserId(Long.parseLong(userId));
 		}
 		
 		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.grantOrgMemberCard(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="grantOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="授权组织成员测试-针对无用户id")
	public void grantOrgMemberCardForNoUserIdTest(String caseId, String title, String tag, String userId, String cardId, String orgId, String expCon, String comments) 
	{
		OrgMemberCardInfo orgMemberCardInfo=new OrgMemberCardInfo();
 		OrgMemberCard orgMemberCard=new OrgMemberCard(); 
 		
 		if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 		}else if(tag.equalsIgnoreCase("fail1"))
 		{

 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCard.setUserId(Long.parseLong(userId));
 		}
 		
 		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.grantOrgMemberCardForNoUserId(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="takeBackOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="收回组织名片测试")
	public void takeBackOrgMemberCardTest(String caseId, String title, String tag, String cardId, String orgId, String expCon, String comments) 
	{
		OrgMemberCardInfo orgMemberCardInfo=new OrgMemberCardInfo();
 		OrgMemberCard orgMemberCard=new OrgMemberCard(); 
 		
 		if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 		}else if(tag.equalsIgnoreCase("fail1"))
 		{
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 		}else
 		{
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 		}
 		
 		orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
		OrgMemberCardResult result=orgMemberCardManager.takeBackOrgMemberCard(orgMemberCardInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	//retrieve member org card by node id 
	@Test(dataProvider="getOrgMemberCardIdListByNodeId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="根据组织成员数id获取组织成员")
	public void getOrgMemberCardIdListByNodeIdTest(String caseId, String title, String tag, String nodeId, String orgId, String expCon, String comments)
	{
		MemberNodeRelationInfo memberNodeRelationInfo=null;
		MemberNodeRelation memberNodeRelation = null;
		
		if(tag.equalsIgnoreCase("null"))
		{
		}else if(tag.equalsIgnoreCase("fail"))
		{
			memberNodeRelationInfo=new MemberNodeRelationInfo();
			memberNodeRelation=new MemberNodeRelation();
			memberNodeRelation.setOrgId(Long.parseLong(orgId));
			memberNodeRelationInfo.setMemberNodeRelation(memberNodeRelation);
		}else if(tag.equalsIgnoreCase("fail1"))
		{
			memberNodeRelationInfo=new MemberNodeRelationInfo();
			memberNodeRelation=new MemberNodeRelation();
			memberNodeRelation.setOrgId(Long.parseLong(nodeId));
			memberNodeRelationInfo.setMemberNodeRelation(memberNodeRelation);
		}else
		{
			memberNodeRelationInfo=new MemberNodeRelationInfo();
			memberNodeRelation=new MemberNodeRelation();
			memberNodeRelation.setOrgId(Long.parseLong(nodeId));
			memberNodeRelation.setNodeId(Long.parseLong(orgId));
			memberNodeRelationInfo.setMemberNodeRelation(memberNodeRelation);
		}
		
		IdListResult result=orgMemberCardManager.getOrgMemberCardIdListByNodeId(memberNodeRelationInfo);
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	//通过组织id获取组织成员名片(包含分页)
	@Test(dataProvider="getOrgMemberCardListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取组织成员名片(包含分页)")
	public void getOrgMemberCardListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}else if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}else
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		
 		OrgMemberCardListResult result=orgMemberCardManager.getOrgMemberCardListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
 		
	}
	
	//通过组织id获取组织成员名片简单信息
	@Test(dataProvider="getSimpleOrgMemberCardListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取组织成员名片(包含分页)")
	public void getSimpleOrgMemberCardListByOrgIdTest(String caseId, String title, String tag, String orgId, String pageId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}else if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}else
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 			orgMemberCardInfo.setPage(Integer.parseInt(pageId));
 		}
 		
 		OrgMemberCardListResult result=orgMemberCardManager.getSimpleOrgMemberCardListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//通过组织id获取组织成员名片id列表
	@Test(dataProvider="getOrgMemberCardIdListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取组织成员名片id列表")
	public void getOrgMemberCardIdListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}else if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}else
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		
 		IdListResult result=orgMemberCardManager.getOrgMemberCardIdListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//通过成员名片id获取组织成员名片
	@Test(dataProvider="getOrgMemberCardListByMemberCardId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过成员名片id获取组织成员名片")
	public void getOrgMemberCardListByMemberCardIdTest(String caseId, String title, String tag, String cardId, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}else if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		else if(tag.equalsIgnoreCase("success"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		
 		OrgMemberCardListResult result=orgMemberCardManager.getOrgMemberCardListByMemberCardId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	
	@Test(dataProvider="getOrgCustomerListByMemberCardId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过成员名片id获取组织成员名片")
	public void getOrgCustomerListByMemberCardIdTest(String caseId, String title, String tag, String cardId, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}else if(tag.equalsIgnoreCase("fail"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setOrgId(Long.parseLong(cardId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		else if(tag.equalsIgnoreCase("success"))
 		{
 			orgMemberCardInfo=new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			orgMemberCard.setCardId(Long.parseLong(cardId));
 			orgMemberCard.setOrgId(Long.parseLong(orgId));
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 		}
 		
 		OrgCustomerListResult result=orgMemberCardManager.getOrgCustomerListByMemberCardId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//把成员添加到多个组织树节点(部门)
	@Test(dataProvider="addOrgMembersToNode", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="把成员添加到多个组织树节点(部门)")
	public void addOrgMembersToNodeTest(String caseId, String title, String tag, String Id, String orgId, String memberId, String expCon, String comments)
	{
		IdListRelationInfo idListRelationInfo=null;
		
 		//System.out.println("test");
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else
 		{
 			idListRelationInfo=new IdListRelationInfo();
 			idListRelationInfo.setId(Long.parseLong(Id));
	 		List<Long> list=new ArrayList<Long>();
	 		list.add(Long.parseLong(memberId));
	 		idListRelationInfo.setIdList(list);
	 		idListRelationInfo.setOrgId(Long.parseLong(orgId));
 		}
 		
 		ServiceResult result=orgMemberCardManager.addOrgMembersToNode(idListRelationInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//把成员从某个部门删除
	@Test(dataProvider="deleteOrgMemberFromNode", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="把成员从某个部门删除")
	public void deleteOrgMemberFromNodeTest(String caseId, String title, String tag, String nodeId, String orgId, String memberId, String expCon, String comments)
	{
		MemberNodeRelationInfo memberNodeRelationInfo=null;
		MemberNodeRelation memberNodeRelation=null;
 		System.out.println("test");
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else
 		{
 			memberNodeRelationInfo=new MemberNodeRelationInfo();
 			memberNodeRelation=new MemberNodeRelation();
 			try
 			{
	 			memberNodeRelation.setNodeId(nodeId!="" ? Long.parseLong(nodeId) : null);
	 			memberNodeRelation.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
	 			memberNodeRelation.setMemberId(memberId!="" ? Long.parseLong(memberId) : null);
 			}catch(NumberFormatException e)
 			{
 				System.out.println("Parsing long exception, ignore it...");
 			}
 			memberNodeRelationInfo.setMemberNodeRelation(memberNodeRelation);
 		}
 		
 		ServiceResult result=orgMemberCardManager.deleteOrgMemberFromNode(memberNodeRelationInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	
	//通过组织id获取当前组织的所有员工名片关系
	@Test(dataProvider="getMemberNodeRelationListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取当前组织的所有员工名片关系")
	public void getMemberNodeRelationListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		MemberNodeRelationInfo memberNodeRelationInfo=null;
		MemberNodeRelation memberNodeRelation=null;
 		//System.out.println("test");
 		
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else
 		{
 			memberNodeRelationInfo=new MemberNodeRelationInfo();
 			memberNodeRelation=new MemberNodeRelation();
 			try
 			{
	 			memberNodeRelation.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
 			}catch(NumberFormatException e)
 			{
 				System.out.println("Parsing long exception, ignore it...");
 			}
 			memberNodeRelationInfo.setMemberNodeRelation(memberNodeRelation);
 		}
 		
 		MemberNodeRelationListResult result=orgMemberCardManager.getMemberNodeRelationListByOrgId(memberNodeRelationInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	
	@Test(dataProvider="getNoNodeRelationMemberCardIdListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取当前组织的所有未分配部门的员工名片id列表")
	public void getNoNodeRelationMemberCardIdListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else 
 		{
 			orgMemberCardInfo= new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			try{
 				orgMemberCard.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
 			}catch(NumberFormatException e)
 			{}
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 			
 		}
 		
 		IdListResult result= orgMemberCardManager.getNoNodeRelationMemberCardIdListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getNoNodeRelationSimpleOrgMemberCardListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取当前组织的所有未分配部门组织成员名片简单信息")
	public void getNoNodeRelationSimpleOrgMemberCardListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		//System.out.println("test");
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else 
 		{
 			orgMemberCardInfo= new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			try{
 				orgMemberCard.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
 			}catch(NumberFormatException e)
 			{}
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 			
 		}
 		
 		OrgMemberCardListResult result= orgMemberCardManager.getNoNodeRelationSimpleOrgMemberCardListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}

	@Test(dataProvider="getNoNodeRelationSimpleOrgMemberCardListByOrgId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过组织id获取当前组织的所有未分配部门组织成员名片简单信息")
	public void getNoNodeRelationOrgMemberCardListByOrgIdTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else 
 		{
 			orgMemberCardInfo= new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			try{
 				orgMemberCard.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
 			}catch(NumberFormatException e)
 			{}
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 			
 		}
 		
 		OrgMemberCardListResult result= orgMemberCardManager.getNoNodeRelationOrgMemberCardListByOrgId(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="addOrgMemberCard", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="添加组织成员名片")
	public void addOrgMemberCardTest(String caseId, String title, String tag, String orgId,String name, String expCon, String comments)
	{
		OrgMemberCardInfo orgMemberCardInfo=null;
 		OrgMemberCard orgMemberCard=null;
 		if(tag.equalsIgnoreCase("null"))
 		{}
 		else 
 		{
 			orgMemberCardInfo= new OrgMemberCardInfo();
 			orgMemberCard=new OrgMemberCard();
 			try{
 				orgMemberCard.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
 				orgMemberCard.setName(name);
 			}catch(NumberFormatException e)
 			{}
 			orgMemberCardInfo.setOrgMemberCard(orgMemberCard);
 			
 		}
 		
 		OrgMemberCardResult result= orgMemberCardManager.addOrgMemberCard(orgMemberCardInfo);
 		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	
	
}
