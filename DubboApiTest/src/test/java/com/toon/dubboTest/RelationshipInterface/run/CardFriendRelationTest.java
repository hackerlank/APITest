package com.toon.dubboTest.RelationshipInterface.run;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.parameter.ServiceResult;
import com.systoon.relationship.inter.ICardFriendRelationManager;
import com.systoon.relationship.parameter.CardFriendRelationInfo;
import com.systoon.relationship.parameter.CardFriendRelationListResult;
import com.systoon.relationship.parameter.CardFriendRelationResult;
import com.systoon.relationship.parameter.IdListInfo;
import com.systoon.relationship.parameter.IdListResult;
import com.systoon.relationship.parameter.UserBlackRecordInfo;
import com.systoon.relationship.parameter.UserBlackRecordListResult;
import com.systoon.relationship.pojo.CardFriendRelation;
import com.systoon.relationship.pojo.UserBlackRecord;
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

public class CardFriendRelationTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(CardFriendRelationTest.class);
	
	@Resource(name="cardFriendRelationService")
//	@Autowired  
	 
	 private ICardFriendRelationManager cardFriendRelationManager;
	
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
	 	// getCardFriendRelationListByCardId	通过名片id获取名片朋友关系列表
	 	@Test (dataProvider="cardFriendRelation_getCardFriendRelationListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id获取名片朋友关系列表")
	  	public void testGetCardFriendRelationListByCardId(String caseId, String caseName, String interfaceType, String cardid, String expContent){
	
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
	 		Long cardId = Long.parseLong(cardid);
	 		cardFriendRelation.setCardId(cardId);
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getCardFriendRelationListByCardId(cardFriendRelationInfo);
	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getCardFriendRelationListByCardId---"+ result); 
	 		log.info("getCardFriendRelationListByCardId---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取名片朋友关系列表");
	 		
	 	}
	 	// getSimpleCardFriendRelationListByCardId   通过名片id获取名片朋友关系简单信息(不含朋友头像等信息)列表(黑名单除外)
	 	@Test (dataProvider="cardFriendRelation_getCardFriendRelationListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id获取名片朋友关系列表")
	 	public void testGetSimpleCardFriendRelationListByCardId(String caseId, String caseName, String interfaceType, String cardid, String expContent){
			
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
	 		Long cardId = Long.parseLong(cardid);
	 		cardFriendRelation.setCardId(cardId);
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getSimpleCardFriendRelationListByCardId(cardFriendRelationInfo);
	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ result); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取名片朋友关系简单信息(不含朋友头像等信息)列表(黑名单除外)");
	 		
	 	}
	 	
	  // getSimpleCardFriendRelationListByCardIdList	通过多个名片id获取名片朋友关系简单信息(不含朋友头像等信息)列表(黑名单除外)
	 	@Test (dataProvider="cardFriendRelation_getSimpleCardFriendRelationListByCardIdList",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过多个名片id获取名片朋友关系简单信息(不含朋友头像等信息)列表(黑名单除外)")
	 	public void testGetSimpleCardFriendRelationListByCardIdList(String caseId, String caseName, String interfaceType, String cardids, String expContent){
				 		
	 		Long[] arrCardId =util.StringUtil.stringArray2LongArray(cardids.split(":"));
	 		List<Long> cardIdList = Arrays.asList(arrCardId);
	 	
	 		IdListInfo idListInfo = new IdListInfo();
	 		idListInfo.setIdList(cardIdList);
	 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getSimpleCardFriendRelationListByCardIdList(idListInfo);
	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ result); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取名片朋友关系简单信息(不含朋友头像等信息)列表(黑名单除外)");
	 		
	 	}
	 	
	 	// getCardFriendRelationListByUserId	通过用户id获取名片朋友关系列表
	 	
	 	@Test (dataProvider="cardFriendRelation_getCardFriendRelationListByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过用户id获取名片朋友关系列表")
	 	public void testGetCardFriendRelationListByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//	 		Long cardId = Long.parseLong(cardid);
//	 		cardFriendRelation.setCardId(300156L);
	 		Long userId = util.DubboToonUtil.getUserId(mobile);

	 		cardFriendRelation.setUserId(userId);
	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getCardFriendRelationListByUserId(cardFriendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
	 	}
	 	
	 	// addCardFriendRelation	添加名片朋友关系
	 	@Test //(dataProvider="cardFriendRelation_addCardFriendRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加名片朋友关系")
	 	public void testAddCardFriendRelation(){ //(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//	 		Long cardId = Long.parseLong(cardid);
//	 		cardFriendRelation.setCardId(300156L);
//	 		Long userId = util.DubboToonUtil.getUserId(mobile);

//	 		cardFriendRelation.setUserId(userId);
//	 		cardFriendRelation.setCardId(333474L);
	 		cardFriendRelation.setFeedId("c_333474");
//	 		cardFriendRelation.setFriendCardId(333328L);  // DorLin工作
	 		cardFriendRelation.setFriendFeedId("c_333328");

	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationResult cardFriendRelationListResult = cardFriendRelationManager.addCardFriendRelation(cardFriendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
//	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
	 	}
	 	
	 	// acceptCardFriendRelation	接受名片朋友关系
	 	@Test 
	 	public void testAcceptCardFriendRelation(){ //(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//	 		Long cardId = Long.parseLong(cardid);
//	 		cardFriendRelation.setCardId(300156L);
//	 		Long userId = util.DubboToonUtil.getUserId(mobile);

//	 		cardFriendRelation.setUserId(userId);
//	 		cardFriendRelation.setCardId(333474L);
	 		cardFriendRelation.setFeedId("c_333474");
//	 		cardFriendRelation.setFriendCardId(333328L);  // DorLin工作
	 		cardFriendRelation.setFriendFeedId("c_333328");

	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationResult cardFriendRelationListResult = cardFriendRelationManager.acceptCardFriendRelation(cardFriendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
//	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
	 	}
	 	
	 	
	 	// refuseCardFriendRelation   拒绝名片朋友关系
		@Test 
	 	public void testRefuseCardFriendRelation(){ //(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//	 		Long cardId = Long.parseLong(cardid);
//	 		cardFriendRelation.setCardId(300156L);
//	 		Long userId = util.DubboToonUtil.getUserId(mobile);

//	 		cardFriendRelation.setUserId(userId);
//	 		cardFriendRelation.setCardId(333474L);
	 		cardFriendRelation.setFeedId("c_333474");
//	 		cardFriendRelation.setFriendCardId(333328L);  // DorLin工作
	 		cardFriendRelation.setFriendFeedId("c_333328");

	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationResult cardFriendRelationListResult = cardFriendRelationManager.refuseCardFriendRelation(cardFriendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
//	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
	 	}
		
		
		// updateCardFriendRelation  更新朋友关系信息
		@Test 
	 	public void testUpdateCardFriendRelation(){ //(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
	 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
	 		
	 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//	 		Long cardId = Long.parseLong(cardid);
//	 		cardFriendRelation.setCardId(300156L);
//	 		Long userId = util.DubboToonUtil.getUserId(mobile);

//	 		cardFriendRelation.setUserId(userId);
//	 		cardFriendRelation.setCardId(333474L);
	 		cardFriendRelation.setFeedId("c_333474");
//	 		cardFriendRelation.setFriendCardId(333328L);  // DorLin工作
	 		cardFriendRelation.setFriendFeedId("c_333328");

	 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
	 		CardFriendRelationResult cardFriendRelationResult = cardFriendRelationManager.updateCardFriendRelation(cardFriendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardFriendRelationResult);
 			
	 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
	 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
	 		
//	 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
	 	}
		
		
		// deleteCardFriendRelation  删除好友(解除好友关系)
				@Test 
			 	public void testDeleteCardFriendRelation(){ //(String caseId, String caseName, String interfaceType, String mobile, String expContent){
			 		
			 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
			 		
			 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
//			 		Long cardId = Long.parseLong(cardid);
//			 		cardFriendRelation.setCardId(300156L);
//			 		Long userId = util.DubboToonUtil.getUserId(mobile);

//			 		cardFriendRelation.setUserId(userId);
//			 		cardFriendRelation.setCardId(333474L);
			 		cardFriendRelation.setFeedId("c_333474");
//			 		cardFriendRelation.setFriendCardId(333328L);  // DorLin工作
			 		cardFriendRelation.setFriendFeedId("c_333328");

			 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
			 		CardFriendRelationResult cardFriendRelationResult = cardFriendRelationManager.deleteCardFriendRelation(cardFriendRelationInfo);

			 		String result= util.JsonUtil.getJsonString(cardFriendRelationResult);
		 			
			 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
			 		
//			 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id获取名片朋友关系列表");
			 	}
				
				
				// getNewCardFriendRelationListByUserId  通过用户id查询所有新请求添加的朋友 即未接受的朋友
				@Test(dataProvider="cardFriendRelation_getNewCardFriendRelationListByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过用户id查询所有新请求添加的朋友 即未接受的朋友")
			 	public void testGetNewCardFriendRelationListByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent){
			 		
			 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
			 		
			 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
			 		Long userId = util.DubboToonUtil.getUserId(mobile);

			 		cardFriendRelation.setUserId(userId);
			 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
			 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getNewCardFriendRelationListByUserId(cardFriendRelationInfo);
			 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
		 			
			 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id查询所有新请求添加的朋友 即未接受的朋友");
			 	}
		
				
				// getNewCardFriendRelationListByCardId  通过名片id查询所有新请求添加的朋友 即未接受的朋友
				@Test(dataProvider="cardFriendRelation_getNewCardFriendRelationListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id查询所有新请求添加的朋友 即未接受的朋友")
			 	public void testGetNewCardFriendRelationListByCardId(String caseId, String caseName, String interfaceType, String cardId, String expContent){
			 		
			 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
			 		
			 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
			 		
			 		cardFriendRelation.setCardId(Long.parseLong(cardId));
			 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
			 		CardFriendRelationListResult cardFriendRelationListResult = cardFriendRelationManager.getCardFriendRelationListByCardId(cardFriendRelationInfo);
			 		String result= util.JsonUtil.getJsonString(cardFriendRelationListResult);
		 			
			 		System.out.println("getSimpleCardFriendRelationListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("getSimpleCardFriendRelationListByCardId---"+ result);
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id查询所有新请求添加的朋友 即未接受的朋友");
			 	}
				
				// checkIfFriend  检查是否好友关系
				@Test (dataProvider="cardFriendRelation_checkIfFriend",dataProviderClass=ToonDataProvider.class, enabled = true, description="检查是否好友关系")
			 	public void testCheckIfFriend(String caseId, String caseName, String interfaceType, String cardId, String friendCardId, String expContent){
			 		
			 		CardFriendRelationInfo cardFriendRelationInfo = new CardFriendRelationInfo();
			 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
			 		
			 		cardFriendRelation.setCardId(Long.parseLong(cardId));
			 		cardFriendRelation.setFriendCardId(Long.parseLong(friendCardId));
			 		cardFriendRelationInfo.setCardFriendRelation(cardFriendRelation);
			 		ServiceResult serviceResult = cardFriendRelationManager.checkIfFriend(cardFriendRelationInfo);
			 		String result= util.JsonUtil.getJsonString(serviceResult);
		 			
			 		System.out.println("checkIfFriend---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("checkIfFriend---"+ result);
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "检查是否好友关系");
			 	}
				
				// queryUserBlack  查询用户黑名单
				@Test (dataProvider="cardFriendRelation_queryUserBlack",dataProviderClass=ToonDataProvider.class, enabled = true, description="查询用户黑名单")
			 	public void testQueryUserBlack(String caseId, String caseName, String interfaceType, String mobile, String expContent){
			 		
			 		UserBlackRecordInfo userblackrecordinfo = new UserBlackRecordInfo();
			 		UserBlackRecord userBlackRecord = new UserBlackRecord();
			 		Long userId = util.DubboToonUtil.getUserId(mobile);
//			 		userBlackRecord.setUserId(userId);
//			 		userBlackRecord.setCardId(323327L);
//			 		userBlackRecord.setFeedId("c_323327");
			 		userBlackRecord.setBlackUserId(3L);
			 		userblackrecordinfo.setUserBlackRecord(userBlackRecord);
			 		UserBlackRecordListResult  userBlackRecordListResult  = cardFriendRelationManager.queryUserBlack(userblackrecordinfo);
			 		String result= util.JsonUtil.getJsonString(userBlackRecordListResult);
		 			
			 		System.out.println("queryUserBlack---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("queryUserBlack---"+ result);
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "查询用户黑名单");
			 	}
				
				// getUserBlackDetail  查询单个被拉黑明细列表
				@Test //(dataProvider="",dataProviderClass=ToonDataProvider.class, enabled = true, description="查询单个被拉黑明细列表")
			 	public void testGetUserBlackDetail (){//(String caseId, String caseName, String interfaceType, String mobile, String expContent){
			 		
			 		UserBlackRecordInfo userblackrecordinfo = new UserBlackRecordInfo();
			 		UserBlackRecord userBlackRecord = new UserBlackRecord();
			 		
//			 		Long userId = util.DubboToonUtil.getUserId(mobile);	
//			 		userBlackRecord.setUserId(userId);
//			 		userBlackRecord.setFeedId("c_333474");
//			 		userBlackRecord.setCardId(333474L);
//			 		userBlackRecord.setBlackCardId(324527L);
			 		
			 		userBlackRecord.setBlackUserId(3L);
			 		userblackrecordinfo.setUserBlackRecord(userBlackRecord);
			 		UserBlackRecordListResult  userBlackRecordListResult  = cardFriendRelationManager.getUserBlackDetail(userblackrecordinfo);
			 		String result= util.JsonUtil.getJsonString(userBlackRecordListResult);
		 			
			 		System.out.println("queryUserBlack---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("queryUserBlack---"+ result);
			 		
			 		String expContent = "\"returnMessage\":\"查询成功\"";
			 		util.StringUtil.AssertByRegEx(result, expContent, "查询单个被拉黑明细列表");
			 	}
				
				// updateUserBlackProcessStatus 更新用户黑名单处理状态
				@Test 
			 	public void testUpdateUserBlackProcessStatus(){//(String caseId, String caseName, String interfaceType, String mobile, String expContent){
			 		
			 		UserBlackRecordInfo userblackrecordinfo = new UserBlackRecordInfo();
			 		UserBlackRecord userBlackRecord = new UserBlackRecord();
			 		userBlackRecord.setBlackUserId(3L);

			 		userblackrecordinfo.setUserBlackRecord(userBlackRecord);
			 		ServiceResult  serviceResult  = cardFriendRelationManager.updateUserBlackProcessStatus(userblackrecordinfo);
			 		String result= util.JsonUtil.getJsonString(serviceResult);
		 			
			 		System.out.println("queryUserBlack---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("queryUserBlack---"+ result);
			 		String expContent = "\"returnMessage\":\"更新成功\"";
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "更新用户黑名单处理状态");
			 	}
				
				// getFriendCardIdListByCardId    通过名片id获取朋友名片id列表
				@Test (dataProvider="cardFriendRelation_getFriendCardIdListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过名片id获取朋友名片id列表")
			 	public void testGetFriendCardIdListByCardId(String caseId, String caseName, String interfaceType, String cardId, String expContent){
					CardFriendRelationInfo cardfriendrelationinfo = new CardFriendRelationInfo ();
			 		CardFriendRelation cardFriendRelation = new CardFriendRelation();
			 		cardFriendRelation.setCardId(Long.parseLong(cardId));
			 		cardfriendrelationinfo.setCardFriendRelation(cardFriendRelation);
			 		
			 		IdListResult idListResult  = cardFriendRelationManager.getFriendCardIdListByCardId(cardfriendrelationinfo);
			 		String result= util.JsonUtil.getJsonString(idListResult);
		 			
			 		System.out.println("getFriendCardIdListByCardId---"+ util.JsonUtil.jsonFormatter(result)); 
			 		log.info("getFriendCardIdListByCardId---"+ result);
			 		
			 		util.StringUtil.AssertByRegEx(result, expContent, "通过名片id获取朋友名片id列表");
			 	}
}
