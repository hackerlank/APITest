package com.toon.dataprovider;

import org.testng.annotations.DataProvider;

import util.CsvUtil;


public class OrgCardDataProvider {

	@DataProvider(name="getParamInfo", parallel=false)
	public static Object[][] getParamInfo() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getIndustryAndCategory.csv");
	}
	
	@DataProvider(name="getOrgCardInfo",parallel=false)
	public static Object[][] getOrgCardInfo() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_addOrgCard.csv");
	}
	
	@DataProvider(name="updateOrgCardInfo", parallel=false)
	public static Object[][] updateOrgCardInfo() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_updateOrgCard.csv");
	}
	
	@DataProvider(name="getOrgCardById", parallel=false)
	public static Object[][] getOrgCardById() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgCardById.csv");
	}
	
	@DataProvider(name="getOrgCardListByIdList", parallel=false)
	public static Object[][] getOrgCardListByIdList() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgCardListByIdList.csv");
	}
	
	@DataProvider(name="getOrgCardListByUserId", parallel=false)
	public static Object[][] getOrgCardListByUserIdList() throws Exception 
	{
		return CsvUtil.getCsvData("/data/org_getOrgCardListByUserId.csv");
	}
	
	@DataProvider(name="getOrgCardAndMemberCardListByUserId", parallel=false)
	public static Object[][] getOrgCardAndMemberCardListByUserId() throws Exception 
	{
		return CsvUtil.getCsvData("/data/org_getOrgCardAndMemberCardListByUserId.csv");
	}
	
	@DataProvider(name="getUserCardRelationListByUserId", parallel=false)
	public static Object[][] getUserCardRelationListByUserId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getUserCardRelationListByUserId.csv");
	}
	
	@DataProvider(name="getUserCardRelationListByUserCardRelation", parallel=false)
	public static Object[][] getUserCardRelationListByUserCardRelation() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getUserCardRelationListByUserCardRelation.csv");
	}
	
	@DataProvider(name="getOrgCustomerListByOrgId", parallel=false)
	public static Object[][] getOrgCustomerListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgCustomerListByOrgId.csv");
	}
	
	@DataProvider(name="getOrgExternalContactsByOrgId", parallel=false)
	public static Object[][] getOrgExternalContactsByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgExternalContactsByOrgId.csv");
	}
	
	@DataProvider(name="saveOrgExternalContacts", parallel=false)
	public static Object[][] saveOrgExternalContacts() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_saveOrgExternalContacts.csv");
	}
	
	@DataProvider(name="deleteOrgExternalContacts", parallel=false)
	public static Object[][] deleteOrgExternalContacts() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_deleteOrgExternalContacts.csv");
	}

	@DataProvider(name="updateOrgMemberCard", parallel=false)
	public static Object[][] updateOrgMemberCard() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_updateOrgMemberCard.csv");
	}
	
	@DataProvider(name="deleteOrgMemberCard", parallel=false)
	public static Object[][] deleteOrgMemberCard() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_deleteOrgMemberCard.csv");
	}
	
	@DataProvider(name="getOrgMemberCardById", parallel=false)
	public static Object[][] getOrgMemberCardById() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardById.csv");
	}
	
	@DataProvider(name="getOrgMemberCardListByIdList", parallel=false)
	public static Object[][] getOrgMemberCardListByIdList() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardListByIdList.csv");
	}
	
	@DataProvider(name="grantOrgMemberCard", parallel=false)
	public static Object[][] grantOrgMemberCard() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_grantOrgMemberCard.csv");
	}
	
	@DataProvider(name="takeBackOrgMemberCard", parallel=false)
	public static Object[][] takeBackOrgMemberCard() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_takeBackOrgMemberCard.csv");
	}
	
	@DataProvider(name="getOrgMemberCardIdListByNodeId", parallel=false)
	public static Object[][] getOrgMemberCardIdListByNodeId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardIdListByNodeId.csv");
	}
	
	@DataProvider(name="getOrgMemberCardListByOrgId", parallel=false)
	public static Object[][] getOrgMemberCardListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardListByOrgId.csv");
	}
	
	@DataProvider(name="getSimpleOrgMemberCardListByOrgId", parallel=false)
	public static Object[][] getSimpleOrgMemberCardListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getSimpleOrgMemberCardListByOrgId.csv");
	}
	
	@DataProvider(name="getOrgMemberCardIdListByOrgId", parallel=false)
	public static Object[][] getOrgMemberCardIdListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardIdListByOrgId.csv");
	}
	
	@DataProvider(name="getOrgMemberCardListByMemberCardId", parallel=false)
	public static Object[][] getOrgMemberCardListByMemberCardId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgMemberCardListByMemberCardId.csv");
	}
	
	@DataProvider(name="getOrgCustomerListByMemberCardId", parallel=false)
	public static Object[][] getOrgCustomerListByMemberCardId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgCustomerListByMemberCardId.csv");
	}
	
	@DataProvider(name="addOrgMembersToNode", parallel=false)
	public static Object[][] addOrgMembersToNode() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_addOrgMembersToNode.csv");
	}
	
	
	@DataProvider(name="deleteOrgMemberFromNode", parallel=false)
	public static Object[][] deleteOrgMemberFromNode() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_deleteOrgMemberFromNode.csv");
	}
	
	@DataProvider(name="getMemberNodeRelationListByOrgId", parallel=false)
	public static Object[][] getMemberNodeRelationListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getMemberNodeRelationListByOrgId.csv");
	}
	
	
	@DataProvider(name="getNoNodeRelationMemberCardIdListByOrgId", parallel=false)
	public static Object[][] getNoNodeRelationMemberCardIdListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getNoNodeRelationMemberCardIdListByOrgId.csv");
	}
	
	@DataProvider(name="getNoNodeRelationSimpleOrgMemberCardListByOrgId", parallel=false)
	public static Object[][] getNoNodeRelationSimpleOrgMemberCardListByOrgId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getNoNodeRelationSimpleOrgMemberCardListByOrgId.csv");
	}
	
	@DataProvider(name="addOrgTreeNode", parallel=false)
	public static Object[][] addOrgTreeNode() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_addOrgTreeNode.csv");
	}
	
	@DataProvider(name="getOrgTreeNodeById", parallel=false)
	public static Object[][] getOrgTreeNodeById() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgTreeNodeById.csv");
	}
	
	@DataProvider(name="updateOrgTreeNode", parallel=false)
	public static Object[][] updateOrgTreeNode() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_updateOrgTreeNode.csv");
	}
	
	@DataProvider(name="deleteOrgTreeNode", parallel=false)
	public static Object[][] deleteOrgTreeNode() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_deleteOrgTreeNode.csv");
	}
	
	@DataProvider(name="validateOrgTreeNodeName", parallel=false)
	public static Object[][] validateOrgTreeNodeName() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_validateOrgTreeNodeName.csv");
	}
	
	@DataProvider(name="getOrgTreeNodesByParentId", parallel=false)
	public static Object[][] getOrgTreeNodesByParentId() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgTreeNodesByParentId.csv");
	}
	
	@DataProvider(name="getBaseDimOrgTree", parallel=false)
	public static Object[][] getBaseDimOrgTree() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getBaseDimOrgTree.csv");
	}
	
	@DataProvider(name="getOrgTreeNodesByIdList", parallel=false)
	public static Object[][] getOrgTreeNodesByIdList() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_getOrgTreeNodesByIdList.csv");
	}
	
	@DataProvider(name="addOrgMemberCard", parallel=false)
	public static Object[][] addOrgMemberCard() throws Exception
	{
		return CsvUtil.getCsvData("/data/org_addOrgMemberCard.csv");
	}
	
}
