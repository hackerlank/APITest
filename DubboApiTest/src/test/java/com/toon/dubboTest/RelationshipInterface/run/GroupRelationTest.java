package com.toon.dubboTest.RelationshipInterface.run;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.parameter.ServiceResult;
import com.systoon.relationship.inter.ICardFriendRelationManager;
import com.systoon.relationship.inter.ICardRecommendRelationManager;
import com.systoon.relationship.inter.IGroupRelationManager;
import com.systoon.relationship.inter.IUserContactsManager;
import com.systoon.relationship.parameter.CardFriendRelationInfo;
import com.systoon.relationship.parameter.CardFriendRelationListResult;
import com.systoon.relationship.parameter.CardFriendRelationResult;
import com.systoon.relationship.parameter.CardRecommendRelationInfo;
import com.systoon.relationship.parameter.CardRecommendRelationListResult;
import com.systoon.relationship.parameter.CardRecommendRelationResult;
import com.systoon.relationship.parameter.GroupRelationInfo;
import com.systoon.relationship.parameter.GroupRelationListInfo;
import com.systoon.relationship.parameter.GroupRelationResult;
import com.systoon.relationship.parameter.IdListInfo;
import com.systoon.relationship.parameter.IdListResult;
import com.systoon.relationship.parameter.UserBlackRecordInfo;
import com.systoon.relationship.parameter.UserBlackRecordListResult;
import com.systoon.relationship.parameter.UserContactsInfo;
import com.systoon.relationship.parameter.UserContactsListResult;
import com.systoon.relationship.parameter.UserContactsResult;
import com.systoon.relationship.pojo.CardFriendRelation;
import com.systoon.relationship.pojo.CardRecommendRelation;
import com.systoon.relationship.pojo.GroupRelation;
import com.systoon.relationship.pojo.UserBlackRecord;
import com.systoon.relationship.pojo.UserContacts;
import com.toon.dataprovider.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

//import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations = { "classpath:applicationContext_Relationship.xml" })

public class GroupRelationTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(GroupRelationTest.class);
	
	@Resource(name="groupRelationService")
//	@Autowired  
	 
	 private IGroupRelationManager groupRelationManager;
	
	  	@BeforeMethod
	    // 在每个测试用例方法之前都会执行
	    public void init() {
	 		System.out.println("init.....");
	  	}

	  	@AfterMethod
	    // 在每个测试用例执行完之后执行
	    public void destroy() {
	 		System.out.println("after.....");
	    }
	 	// addGroupRelation	添加群体关系
	 	@Test (dataProvider="groupRelation_addGroupRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加群体关系")
	  	public void testAddGroupRelation(String caseId, String caseName, String interfaceType, String mobile, String cardId, String groupId, String expContent){
	
	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
	 		GroupRelation groupRelation = new GroupRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		groupRelation.setGroupId(Long.parseLong(groupId));
	 		groupRelation.setGroupType(1);
	 		groupRelation.setCardId(Long.parseLong(cardId));
	 		groupRelation.setStatus(1);
	 		groupRelation.setDndStatus(1);
	 		groupRelationInfo.setGroupRelation(groupRelation);
	 		
	 		GroupRelationResult groupRelationResult = groupRelationManager.addGroupRelation(groupRelationInfo);
	 		String result= util.JsonUtil.getJsonString(groupRelationResult);
	 		System.out.println("addGroupRelation---"+ result); 
	 		log.info("addGroupRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加群体关系");
	 	}
	 	
	 	// updateGroupRelation 更新群体关系
	 	@Test (dataProvider="groupRelation_updateGroupRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="更新群体关系")
	  	public void testUpdateGroupRelation(String caseId, String caseName, String interfaceType, String mobile, String cardId, String groupId, String expContent){
	
	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
	 		GroupRelation groupRelation = new GroupRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		groupRelation.setGroupId(Long.parseLong(groupId));
	 		groupRelation.setGroupType(1);
	 		groupRelation.setCardId(Long.parseLong(cardId));
	 		groupRelation.setStatus(1);
	 		groupRelation.setDndStatus(1);
	 		groupRelationInfo.setGroupRelation(groupRelation);
	 		
	 		GroupRelationResult groupRelationResult = groupRelationManager.updateGroupRelation(groupRelationInfo);
	 		String result= util.JsonUtil.getJsonString(groupRelationResult);
	 		System.out.println("updateGroupRelation---"+ result); 
	 		log.info("updateGroupRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "更新群体关系");
	 	}
	 	
	 	// deleteGroupRelation  删除群体关系(指群员的退出)
	 	@Test (dataProvider="groupRelation_deleteGroupRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="删除群体关系(指群员的退出)")
	  	public void testDeleteGroupRelation(String caseId, String caseName, String interfaceType, String mobile, String cardId, String groupId, String expContent){
	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
	 		GroupRelation groupRelation = new GroupRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		groupRelation.setGroupId(Long.parseLong(groupId));
	 		groupRelation.setGroupType(1);
	 		groupRelation.setCardId(Long.parseLong(cardId));
	 		groupRelation.setStatus(1);
	 		groupRelation.setDndStatus(1);
	 		groupRelationInfo.setGroupRelation(groupRelation);
	 		
	 		ServiceResult serviceResult = groupRelationManager.deleteGroupRelation(groupRelationInfo);
	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("deleteGroupRelation---"+ result); 
	 		log.info("deleteGroupRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "删除群体关系(指群员的退出)");
	 	}
	 	
	 	// batchAddGroupRelation 批量添加群体关系
	 	@Test (dataProvider="groupRelation_batchAddGroupRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="批量添加群体关系")
	  	public void testBatchAddGroupRelation(String caseId, String caseName, String interfaceType, String mobile, String cardIds, String groupId, String expContent){
	 		GroupRelationListInfo groupRelationListInfo = new GroupRelationListInfo();
	 		List<GroupRelation> groupRelationList =  new ArrayList<GroupRelation>();
	 		Long[] arrCardId = util.StringUtil.stringArray2LongArray(cardIds.split(":"));	 		
	 		for(Long cardId:arrCardId){
	 			GroupRelation groupRelation = new GroupRelation();
	 			groupRelation.setCardId(cardId);
	 			groupRelation.setCardType(1);
	 			groupRelationList.add(groupRelation);
	 		}
	 		groupRelationListInfo.setGroupId(Long.parseLong(groupId));
	 		groupRelationListInfo.setGroupType(1);
	 		groupRelationListInfo.setGroupRelationList(groupRelationList);

	 		ServiceResult serviceResult = groupRelationManager.batchAddGroupRelation(groupRelationListInfo);
	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("batchAddGroupRelation---"+ result); 
	 		log.info("batchAddGroupRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "批量添加群体关系");
	 	}
	 	
	 		// batchDeleteGroupRelation  批量删除群体关系(指群员的退出)
	 	 	@Test (dataProvider="groupRelation_batchDeleteGroupRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="批量删除群体关系(指群员的退出)")
	 	  	public void testBatchDeleteGroupRelation(String caseId, String caseName, String interfaceType, String mobile, String cardIds, String groupId, String expContent){
	 	 		GroupRelationListInfo groupRelationListInfo = new GroupRelationListInfo();
	 	 		List<GroupRelation> groupRelationList =  new ArrayList<GroupRelation>();
	 	 		Long[] arrCardId = util.StringUtil.stringArray2LongArray(cardIds.split(":"));	 		
	 	 		for(Long cardId:arrCardId){
	 	 			GroupRelation groupRelation = new GroupRelation();
	 	 			groupRelation.setCardId(cardId);
	 	 			groupRelation.setCardType(1);
	 	 			groupRelationList.add(groupRelation);
	 	 		}
	 	 		groupRelationListInfo.setGroupId(Long.parseLong(groupId));
	 	 		groupRelationListInfo.setGroupType(1);
	 	 		groupRelationListInfo.setGroupRelationList(groupRelationList);

	 	 		ServiceResult serviceResult = groupRelationManager.batchDeleteGroupRelation(groupRelationListInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("deleteGroupRelation---"+ result); 
	 	 		log.info("deleteGroupRelation---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "批量删除群体关系(指群员的退出)");
	 	 	}
	 	 	
	 	 	// getGroupRelationListByGroupId 通过群体id查询群员列表(含名片头像昵称等信息) 参数是groupId 和 groupType(必传)
	 	 	@Test (dataProvider="groupRelation_getGroupRelationListByGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过群体id查询群员列表(含名片头像昵称等信息)")
	 	  	public void testGetGroupRelationListByGroupId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelation.setGroupType(1);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getGroupRelationListByGroupId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getGroupRelationListByGroupId---"+ result); 
	 	 		log.info("getGroupRelationListByGroupId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过群体id查询群员列表(含名片头像昵称等信息)");
	 	 	}
	 	 	
	 	 	// getNewGroupRelationListByGroupId  通过群体id查询待审核群员列表(含名片头像昵称等信息) 参数是groupId 和 groupType(必传)
	 		@Test (dataProvider="groupRelation_getNewGroupRelationListByGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过群体id查询待审核群员列表(含名片头像昵称等信息)")
	 	  	public void testGetNewGroupRelationListByGroupId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelation.setGroupType(1);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getNewGroupRelationListByGroupId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getGroupRelationListByGroupId---"+ result); 
	 	 		log.info("getGroupRelationListByGroupId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过群体id查询待审核群员列表(含名片头像昵称等信息)");
	 	 	}
	 		
	 		// getSimpleGroupRelationListByGroupId  通过群体id查询群员关系列表 参数是groupId 和 groupType(必传)
	 		@Test (dataProvider="groupRelation_getSimpleGroupRelationListByGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过群体id查询群员关系列表")
	 		public void testGetSimpleGroupRelationListByGroupId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelation.setGroupType(1);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getSimpleGroupRelationListByGroupId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getSimpleGroupRelationListByGroupId---"+ result); 
	 	 		log.info("getSimpleGroupRelationListByGroupId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过群体id查询群员关系列表");
	 	 	}
	 		
	 		// getCardIdListByGroupId  通过用户id获取群员名片id列表 参数是groupId 和 groupType(必传)
	 		@Test (dataProvider="groupRelation_getCardIdListByGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过用户id获取群员名片id列表 ")
	 		public void testGetCardIdListByGroupId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelation.setGroupType(1);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getSimpleGroupRelationListByGroupId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getCardIdListByGroupId---"+ result); 
	 	 		log.info("getCardIdListByGroupId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取群员名片id列表 ");
	 	 	}
	 		
	 		// getSimpleGroupRelationListByUserId  通过用户id获取群体id列表 参数是userId和groupType(可选)
			@Test (dataProvider="groupRelation_getCardIdListByGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过用户id获取群员名片id列表 ")
	 		public void testGetSimpleGroupRelationListByUserId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		Long userId = util.DubboToonUtil.getUserId(mobile);
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelation.setGroupType(1);
		 		groupRelation.setUserId(userId);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getSimpleGroupRelationListByUserId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getSimpleGroupRelationListByUserId---"+ result); 
	 	 		log.info("getSimpleGroupRelationListByUserId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取群员名片id列表 ");
	 	 	}
	 		
	 		// getSimpleGroupRelationListByCardId 通过名片id获取群体id列表 参数是cardId和groupType(可选)
			@Test (dataProvider="groupRelation_getSimpleGroupRelationListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id获取群体id列表 ")
	 		public void testGetSimpleGroupRelationListByCardId(String caseId, String caseName, String interfaceType, String mobile, String cardId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setCardId(Long.parseLong(cardId));
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getSimpleGroupRelationListByCardId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getSimpleGroupRelationListByCardId---"+ result); 
	 	 		log.info("getSimpleGroupRelationListByCardId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取群体id列表 ");
	 	 	}
			
			// getGroupIdListByCardId  通过名片id获取群体id列表 
			@Test (dataProvider="groupRelation_getGroupIdListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id获取群体id列表 ")
			public void tesGetGroupIdListByCardId(String caseId, String caseName, String interfaceType, String mobile, String cardId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setCardId(Long.parseLong(cardId));
		 		groupRelation.setGroupType(1);
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getGroupIdListByCardId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getGroupIdListByCardId---"+ result); 
	 	 		log.info("getGroupIdListByCardId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取群体id列表 ");
	 	 	}
			
			// getGroupRelationByCardIdAndGroupId 获取某个名片id在某个群体中的关系信息cardId、groupId、groupType 必传
			@Test (dataProvider="groupRelation_getGroupRelationByCardIdAndGroupId",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取某个名片id在某个群体中的关系信息")
			public void tesGetGroupRelationByCardIdAndGroupId(String caseId, String caseName, String interfaceType, String mobile, String groupId, String cardId, String expContent){
	 	 		GroupRelationInfo groupRelationInfo = new GroupRelationInfo();
		 		GroupRelation groupRelation = new GroupRelation();
		 		groupRelation.setCardId(Long.parseLong(cardId));
		 		groupRelation.setGroupType(1);
		 		groupRelation.setGroupId(Long.parseLong(groupId));
		 		groupRelationInfo.setGroupRelation(groupRelation);
		 		ServiceResult serviceResult = groupRelationManager.getGroupRelationByCardIdAndGroupId(groupRelationInfo);
	 	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 	 		System.out.println("getGroupRelationByCardIdAndGroupId---"+ result); 
	 	 		log.info("getGroupRelationByCardIdAndGroupId---"+ result);
	 	 		util.StringUtil.AssertByRegEx(result, expContent, "获取某个名片id在某个群体中的关系信息 ");
	 	 	}
			
	 		
}
