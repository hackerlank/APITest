package com.toon.dubboTest.RelationshipInterface.run;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.parameter.ServiceResult;
import com.systoon.relationship.inter.ICardFriendRelationManager;
import com.systoon.relationship.inter.ICardRecommendRelationManager;
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
import com.systoon.relationship.pojo.CardFriendRelation;
import com.systoon.relationship.pojo.CardRecommendRelation;
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

public class CardRecommendRelationTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(CardRecommendRelationTest.class);
	
	@Resource(name="cardRecommendRelationService")
//	@Autowired  
	 
	 private ICardRecommendRelationManager cardRecommendRelationManager;
	
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
	 	// addCardRecommendRelation	添加推荐关系
	 	@Test (dataProvider="cardRecommendRelation_addCardRecommendRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加推荐关系")
	  	public void testAddCardRecommendRelation(String caseId, String caseName, String interfaceType, String mobile, String feedId, String recommendFeedId, String expContent){
	
	 		CardRecommendRelationInfo cardRecommendRelationInfo = new CardRecommendRelationInfo();
	 		CardRecommendRelation cardRecommendRelation = new CardRecommendRelation();
	 		
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		cardRecommendRelation.setUserId(userId);
	 		cardRecommendRelation.setFeedId(feedId);
	 		cardRecommendRelation.setRecommendFeedId(recommendFeedId);
	 		
	 		cardRecommendRelationInfo.setCardRecommendRelation(cardRecommendRelation);
	 		CardRecommendRelationResult cardRecommendRelationResult = cardRecommendRelationManager.addCardRecommendRelation(cardRecommendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardRecommendRelationResult);
 			
	 		System.out.println("addCardRecommendRelation---"+ result); 
	 		log.info("addCardRecommendRelation---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加推荐关系");
	 	}
	 	
	 	// updateCardRecommendRelation 更新推荐关系
		@Test (dataProvider="cardRecommendRelation_updateCardRecommendRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="更新推荐关系")
	  	public void testUpdateCardRecommendRelation(String caseId, String caseName, String interfaceType, String mobile, String feedId, String recommendFeedId, String expContent){
	
	 		CardRecommendRelationInfo cardRecommendRelationInfo = new CardRecommendRelationInfo();
	 		CardRecommendRelation cardRecommendRelation = new CardRecommendRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		cardRecommendRelation.setUserId(userId);
	 		cardRecommendRelation.setFeedId(feedId);
	 		cardRecommendRelation.setRecommendFeedId(recommendFeedId);
	 		
	 		cardRecommendRelationInfo.setCardRecommendRelation(cardRecommendRelation);
	 		CardRecommendRelationResult cardRecommendRelationResult = cardRecommendRelationManager.updateCardRecommendRelation(cardRecommendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardRecommendRelationResult);
	 		System.out.println("addCardRecommendRelation---"+ result); 
	 		log.info("addCardRecommendRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "更新推荐关系");
	 	}
		
		// deleteCardRecommendRelation	删除推荐关系
		@Test (dataProvider="cardRecommendRelation_deleteCardRecommendRelation",dataProviderClass=ToonDataProvider.class, enabled = true, description="删除推荐关系")
	  	public void testDeleteCardRecommendRelation(String caseId, String caseName, String interfaceType, String mobile, String feedId, String recommendFeedId, String expContent){
	
	 		CardRecommendRelationInfo cardRecommendRelationInfo = new CardRecommendRelationInfo();
	 		CardRecommendRelation cardRecommendRelation = new CardRecommendRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		cardRecommendRelation.setUserId(userId);
	 		cardRecommendRelation.setFeedId(feedId);
	 		cardRecommendRelation.setRecommendFeedId(recommendFeedId);
	 		
	 		cardRecommendRelationInfo.setCardRecommendRelation(cardRecommendRelation);
	 		ServiceResult serviceResult =  cardRecommendRelationManager.deleteCardRecommendRelation(cardRecommendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("deleteCardRecommendRelation---"+ result); 
	 		log.info("deleteCardRecommendRelation---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "删除推荐关系");
	 	}
		
		// getCardRecommendRelationListByCardId	获取推荐关系列表 参数 名片id 和 推荐状态（可选）
		@Test (dataProvider="cardRecommendRelation_getCardRecommendRelationListByCardId",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取推荐关系列表 参数 名片id 和 推荐状态（可选）")
	  	public void testGetCardRecommendRelationListByCardId(String caseId, String caseName, String interfaceType, String mobile, String feedId,  String expContent){
	
	 		CardRecommendRelationInfo cardRecommendRelationInfo = new CardRecommendRelationInfo();
	 		CardRecommendRelation cardRecommendRelation = new CardRecommendRelation();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		cardRecommendRelation.setUserId(userId);
	 		cardRecommendRelation.setFeedId(feedId);
	 		
	 		cardRecommendRelationInfo.setCardRecommendRelation(cardRecommendRelation);
	 		CardRecommendRelationListResult  cardRecommendRelationListResult  =  cardRecommendRelationManager.getCardRecommendRelationListByFeedId(cardRecommendRelationInfo);

	 		String result= util.JsonUtil.getJsonString(cardRecommendRelationListResult);
	 		System.out.println("getCardRecommendRelationListByCardId---"+ result); 
	 		log.info("getCardRecommendRelationListByCardId---"+ result);
	 		util.StringUtil.AssertByRegEx(result, expContent, "获取推荐关系列表 参数 名片id 和 推荐状态（可选）");
	 	}
}
