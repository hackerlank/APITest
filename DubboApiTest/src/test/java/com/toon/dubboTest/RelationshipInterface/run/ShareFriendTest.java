package com.toon.dubboTest.RelationshipInterface.run;


import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.parameter.ServiceResult;
import com.systoon.relationship.inter.ICardFriendRelationManager;
import com.systoon.relationship.inter.ICardRecommendRelationManager;
import com.systoon.relationship.inter.IShareFriendManager;
import com.systoon.relationship.inter.IUserContactsManager;
import com.systoon.relationship.parameter.CardFriendRelationInfo;
import com.systoon.relationship.parameter.CardFriendRelationListResult;
import com.systoon.relationship.parameter.CardFriendRelationResult;
import com.systoon.relationship.parameter.CardRecommendRelationInfo;
import com.systoon.relationship.parameter.CardRecommendRelationListResult;
import com.systoon.relationship.parameter.CardRecommendRelationResult;
import com.systoon.relationship.parameter.IdListInfo;
import com.systoon.relationship.parameter.IdListResult;
import com.systoon.relationship.parameter.ShareFriendsInfo;
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

public class ShareFriendTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(ShareFriendTest.class);
	
	@Resource(name="shareFriendService")
//	@Autowired  
	 
	 private IShareFriendManager shareFriendManager;
	
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
	 	// saveShareFriends	保存共享通讯录的好友清单
	 	@Test (dataProvider="shareFriend_saveShareFriends",dataProviderClass=ToonDataProvider.class, enabled = true, description="保存共享通讯录的好友清单")
	  	public void testAddUserContacts(String caseId, String caseName, String interfaceType, String mobile, String cardId, String friendCardIds,  String expContent){
	 		ShareFriendsInfo shareFriendsInfo = new ShareFriendsInfo();
	  		Long[] arrFriendCardid = util.StringUtil.stringArray2LongArray(friendCardIds.split(":"));
	 		List<Long> friendCardIdList = Arrays.asList(arrFriendCardid);
	 		shareFriendsInfo.setCardId(Long.parseLong(cardId));
	 		shareFriendsInfo.setFriendCardIdList(friendCardIdList);
	 		ServiceResult serviceResult =  shareFriendManager.saveShareFriends(shareFriendsInfo);
	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("saveShareFriends---"+ result); 
	 		log.info("saveShareFriends---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "保存共享通讯录的好友清单");
	 	}
	 	
	 	// getShareFriendsByCardId  获取共享通讯录的好友清单
	 	@Test (dataProvider="shareFriend_getShareFriendsByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取共享通讯录的好友清单")
	  	public void testGetShareFriendsByCardId(String caseId, String caseName, String interfaceType, String mobile, String cardId, String expContent){
	 		ShareFriendsInfo shareFriendsInfo = new ShareFriendsInfo();
	 		shareFriendsInfo.setCardId(Long.parseLong(cardId));
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		shareFriendsInfo.setUserId(userId);
	 		IdListResult  idListResult =  shareFriendManager.getShareFriendsByCardId(shareFriendsInfo);
	 		String result= util.JsonUtil.getJsonString(idListResult);
	 		System.out.println("getShareFriendsByCardId---"+ result); 
	 		log.info("getShareFriendsByCardId---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "获取共享通讯录的好友清单");
	 	}
	 	
	 	// getBeShareFriendsByUserId 查询为我提供通讯录共享的好友清单
	 	@Test (dataProvider="shareFriend_getBeShareFriendsByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="查询为我提供通讯录共享的好友清单")
	  	public void testGetBeShareFriendsByUserId(String caseId, String caseName, String interfaceType, String mobile, String cardId, String expContent){
	 		ShareFriendsInfo shareFriendsInfo = new ShareFriendsInfo();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		shareFriendsInfo.setUserId(userId);
	 		CardFriendRelationListResult  CardFriendRelationListResult =  shareFriendManager.getBeShareFriendsByUserId(shareFriendsInfo);
	 		String result= util.JsonUtil.getJsonString(CardFriendRelationListResult);
	 		System.out.println("getBeShareFriendsByUserId---"+ result); 
	 		log.info("getBeShareFriendsByUserId---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "查询为我提供通讯录共享的好友清单");
	 	}
}
