package com.toon.dubboTest.OrganizationInterface.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.systoon.organization.inter.IOrgTreeManager;
import com.systoon.organization.parameter.OrgTreeNodeInfo;
import com.systoon.organization.parameter.OrgTreeNodeListResult;
import com.systoon.organization.parameter.OrgTreeNodeResult;
import com.systoon.organization.parameter.RelationInfo;
import com.systoon.organization.pojo.OrgTreeNode;
import com.systoon.parameter.ServiceResult;
import com.toon.dataprovider.OrgCardDataProvider;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import util.JsonUtil;
import util.StringUtil;

import javax.annotation.Resource;

@ContextConfiguration(locations = "classpath:applicationContextOrganization.xml")
public class OrgTreeManagerTest extends AbstractTestNGSpringContextTests{

	@Resource
	IOrgTreeManager orgTreeManager;
	
	@BeforeMethod
	public void init()
	{
		System.out.println("Test initialized...");
	}
	
	@AfterMethod
	public void destroy()
	{
		System.out.println("Test destroyed...");
	}
	
	//添加树（公司）节点
	@Test(dataProvider="addOrgTreeNode", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="添加树(公司组织)节点")
	public void addOrgTreeNodeTest(String caseId, String title, String tag, String orgId, String parentId, String dimension, String name, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		if(tag.equalsIgnoreCase("fail"))
		{
			if(orgId=="" && parentId=="" && dimension=="")
			{
			}else
			{
				orgTreeNodeInfo=new OrgTreeNodeInfo();
				orgTreeNode=new OrgTreeNode();
				try
				{
					orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
					orgTreeNode.setParentId(parentId!="" ? Long.parseLong(parentId) : null);
					orgTreeNode.setDimensionId(dimension!="" ? Long.parseLong(dimension) : null) ;
					orgTreeNode.setName(name);
					orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
				}catch(NumberFormatException e)
				{
				}
			}
		}if(tag.equalsIgnoreCase("dup"))
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setParentId(parentId!="" ? Long.parseLong(parentId) : null);
				orgTreeNode.setDimensionId(dimension!="" ? Long.parseLong(dimension) : null) ;
				orgTreeNode.setName(name);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setParentId(parentId!="" ? Long.parseLong(parentId) : null);
				orgTreeNode.setDimensionId(dimension!="" ? Long.parseLong(dimension) : null) ;
				if(name.indexOf("+rnd")!=-1)
				{
					name=name.replace("+rnd", System.currentTimeMillis()/100000+"");
				}
				orgTreeNode.setName(name);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		OrgTreeNodeResult result=orgTreeManager.addOrgTreeNode(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//通过id查询树节点信息
	@Test(dataProvider="getOrgTreeNodeById", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过id查询树节点信息")
	public void getOrgTreeNodeByIdTest(String caseId, String title, String tag, String orgId, String nodeId, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setNodeId(nodeId!="" ? Long.parseLong(nodeId) : null);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		OrgTreeNodeResult result=orgTreeManager.getOrgTreeNodeById(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//更新树节点信息
	@Test(dataProvider="updateOrgTreeNode", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="更新树节点信息")
	public void updateOrgTreeNodeTest(String caseId, String title, String tag, String orgId, String nodeId, String parentId, String dimension, String name, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setNodeId(nodeId!="" ? Long.parseLong(nodeId) : null);
				orgTreeNode.setParentId(parentId!="" ? Long.parseLong(parentId) : null);
				orgTreeNode.setDimensionId(dimension!="" ? Long.parseLong(dimension) : null);
				orgTreeNode.setName(name);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		ServiceResult result=orgTreeManager.updateOrgTreeNode(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//删除树节点信息
	@Test(dataProvider="deleteOrgTreeNode", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="删除树节点信息")
	public void deleteOrgTreeNodeTest(String caseId, String title, String tag, String orgId, String nodeId, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setNodeId(nodeId!="" ? Long.parseLong(nodeId) : null);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		ServiceResult result=orgTreeManager.deleteOrgTreeNode(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="validateOrgTreeNodeName", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="验证同一级别 组织树名称不能重复")
	public void validateOrgTreeNodeNameTest(String caseId, String title, String tag, String orgId, String nodeId, String name, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setNodeId(nodeId!="" ? Long.parseLong(nodeId) : null);
				orgTreeNode.setName(name!="" ? name : null);
				orgTreeNode.setParentId(0l);
				orgTreeNode.setDimensionId(0l);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		//System.out.println("test");
		ServiceResult result=orgTreeManager.validateOrgTreeNodeName(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	
	//通过父id获取某一维度下的所有子节点信息
	@Test(dataProvider="getOrgTreeNodesByParentId", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="通过父id获取某一维度下的所有子节点信息")
	public void getOrgTreeNodesByParentIdTest(String caseId, String title, String tag, String orgId, String parentId, String dimension, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNode.setParentId(parentId!="" ? Long.parseLong(parentId) : null);
				orgTreeNode.setDimensionId(dimension!="" ? Long.parseLong(dimension) : null);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		//System.out.println("test");
		OrgTreeNodeListResult result=orgTreeManager.getOrgTreeNodesByParentId(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	@Test(dataProvider="getBaseDimOrgTree", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="获取基本维度的组织树")
	public void getBaseDimOrgTreeTest(String caseId, String title, String tag, String orgId, String expCon, String comments)
	{
		OrgTreeNodeInfo orgTreeNodeInfo=null;
		OrgTreeNode orgTreeNode=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			orgTreeNodeInfo=new OrgTreeNodeInfo();
			orgTreeNode=new OrgTreeNode();
			try
			{
				orgTreeNode.setOrgId(orgId!="" ? Long.parseLong(orgId) : null);
				orgTreeNodeInfo.setOrgTreeNode(orgTreeNode);
			}catch(NumberFormatException e)
			{
			}
		}
		
		//System.out.println("test");
		OrgTreeNodeListResult result=orgTreeManager.getBaseDimOrgTree(orgTreeNodeInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
	//批量获取组织树节点信息 objId组织id
	@Test(dataProvider="getOrgTreeNodesByIdList", dataProviderClass=OrgCardDataProvider.class, enabled=true, description="批量获取组织树节点信息 objId组织id")
	public void getOrgTreeNodesByIdListTest(String caseId, String title, String tag, String orgId, String nodeIdlist, String expCon, String comments)
	{
		RelationInfo relationInfo=null;
		
		if(tag.equalsIgnoreCase("null"))
		{}else
		{
			try
			{
				relationInfo=new RelationInfo();
				if(nodeIdlist=="")
				{}
				else
				{
					List<Long> idList=new ArrayList<Long>();
					String[] list=nodeIdlist.split("-");
					for(String num : list)
					{
						idList.add(Long.parseLong(num));
					}
					relationInfo.setIdList(idList);
				}
				relationInfo.setObjId(orgId!="" ? Long.parseLong(orgId) : null);
				
			}catch(NumberFormatException e)
			{
			}
		}
		
		//System.out.println("test");
		OrgTreeNodeListResult result=orgTreeManager.getOrgTreeNodesByIdList(relationInfo);
		System.out.println("CaseID: "+caseId+" CaseTitle: "+title+" finised test running.");
		StringUtil.AssertByRegEx(JsonUtil.getJsonString(result), expCon, comments);
	}
	
}
