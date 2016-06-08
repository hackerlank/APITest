package com.toon.dubboTest.RelationshipInterface.run;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.parameter.ServiceResult;
import com.systoon.relationship.inter.ICardFriendRelationManager;
import com.systoon.relationship.inter.ICardRecommendRelationManager;
import com.systoon.relationship.inter.IUserContactsManager;
import com.systoon.relationship.parameter.CardFriendRelationInfo;
import com.systoon.relationship.parameter.CardFriendRelationListResult;
import com.systoon.relationship.parameter.CardFriendRelationResult;
import com.systoon.relationship.parameter.CardRecommendRelationInfo;
import com.systoon.relationship.parameter.CardRecommendRelationListResult;
import com.systoon.relationship.parameter.CardRecommendRelationResult;
import com.systoon.relationship.parameter.IdListInfo;
import com.systoon.relationship.parameter.IdListResult;
import com.systoon.relationship.parameter.UserBlackRecordInfo;
import com.systoon.relationship.parameter.UserBlackRecordListResult;
import com.systoon.relationship.parameter.UserContactsInfo;
import com.systoon.relationship.parameter.UserContactsListResult;
import com.systoon.relationship.parameter.UserContactsResult;
import com.systoon.relationship.pojo.CardFriendRelation;
import com.systoon.relationship.pojo.CardRecommendRelation;
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

public class UserContactsTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(UserContactsTest.class);
	
	@Resource(name="userContactsService")
//	@Autowired  
	 
	 private IUserContactsManager userContactsManager;
	
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
	 	// addUserContacts	添加用户联系人
	 	@Test (dataProvider="userContacts_addUserContacts",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加用户联系人")
	  	public void testAddUserContacts(String caseId, String caseName, String interfaceType, String mobile, String feedId, String friendMobile, String friendFeedId, String expContent){
	
	 		UserContactsInfo userContactsInfo = new UserContactsInfo();
	 		UserContacts userContacts = new UserContacts();
	 		
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		Long friendUserId = util.DubboToonUtil.getUserId(friendMobile);
	 		userContacts.setFriendCardType(1);
	 		userContacts.setFriendUserId(friendUserId);
	 		userContacts.setFriendFeedId(friendFeedId);
	 		userContacts.setUserId(userId);
	 		
	 		userContactsInfo.setUserContacts(userContacts);
	 		UserContactsResult userContactsResult = userContactsManager.addUserContacts(userContactsInfo);
	 		String result= util.JsonUtil.getJsonString(userContactsResult);
	 		System.out.println("addUserContacts---"+ result); 
	 		log.info("addUserContacts---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加用户联系人");
	 	}
	 	
	 	// getUserContactsListByUserId  获取用户联系人列表
	 	@Test (dataProvider="userContacts_getUserContactsListByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取用户联系人列表")
	  	public void testGetUserContactsListByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		UserContactsInfo userContactsInfo = new UserContactsInfo();
	 		UserContacts userContacts = new UserContacts();
	 		
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		userContacts.setUserId(userId);
	 		
	 		userContactsInfo.setUserContacts(userContacts);
	 		UserContactsListResult userContactsResult = userContactsManager.getUserContactsListByUserId(userContactsInfo);
	 		String result= util.JsonUtil.getJsonString(userContactsResult);
	 		System.out.println("getUserContactsListByUserId---"+ result); 
	 		log.info("getUserContactsListByUserId---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "获取用户联系人列表");
	 	}
	 	
	 	// deleteUserContacts	 删除用户联系人
	 	@Test (dataProvider="userContacts_deleteUserContacts",dataProviderClass=ToonDataProvider.class, enabled = true, description="删除用户联系人")
	  	public void testDeleteUserContacts(String caseId, String caseName, String interfaceType, String mobile, String feedId, String friendMobile, String friendCardId, String expContent){
	 		UserContactsInfo userContactsInfo = new UserContactsInfo();
	 		UserContacts userContacts = new UserContacts();
	 		
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		Long friendUserId = util.DubboToonUtil.getUserId(friendMobile);
	 		userContacts.setUserId(userId);
	 		userContacts.setFriendCardId(Long.parseLong(friendCardId));
	 		userContacts.setFriendUserId(friendUserId);
	 		userContacts.setFriendCardType(1);
	 		userContactsInfo.setUserContacts(userContacts);
	 		ServiceResult serviceResult = userContactsManager.deleteUserContacts(userContactsInfo);
	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("deleteUserContacts---"+ result); 
	 		log.info("deleteUserContacts---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "删除用户联系人");
	 	}
}
