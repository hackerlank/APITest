package com.toon.dubboTest.PassportInterface.run;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;


import com.systoon.passport.inter.IUserCardManager;
import com.systoon.passport.inter.IUserManager;
import com.systoon.passport.parameter.UserCardInfo;
import com.systoon.passport.parameter.UserCardResult;
import com.systoon.passport.parameter.UserInfo;
import com.systoon.passport.pojo.User;
import com.systoon.passport.pojo.UserCard;
import com.toon.dataprovider.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

//import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import util.DubboToonUtil;


@ContextConfiguration(locations = { "classpath:applicationContext.xml" })

public class CardTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(CardTest.class);
	
	@Resource(name="userCardService")

//	@Autowired  
	 
	 private IUserCardManager cardManager;
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
	 	// addUserCard 添加用户名片
	 	@Test(dataProvider="card_addUserCard",dataProviderClass=ToonCardDataProvider.class, enabled = true, description="添加用户名片")
	 	public void testAddUserCard(String caseId, String caseName, String interfaceType, String mobile,String cardName,
	 			String introduction, String bigImage, String avatar, String useStatus,String exChangeMode, String expContent){
	 
	 		
	 		UserCard userCard = new UserCard();
	 		UserCardInfo userCardInfo = new UserCardInfo();
	 		Long userId = util.DubboToonUtil.getUserId(mobile);
	 		userCard.setNickname(cardName);
	 		userCard.setUserId(userId);
	 		userCard.setIntroduction(introduction);
	 		userCard.setExchangeMode(Integer.parseInt(exChangeMode))  ; // 1 开放模式 2 申请模式 3 验证模式
	 		userCard.setBigImage(bigImage);   //背景图
	 		userCard.setAvatar(avatar);   // 图片Id,缺省为标准url
	 		userCard.setUseStatus(Integer.parseInt(useStatus)) ;  // 0 停用 1 启用 2 转让中 3 授权中
	 		
	 		userCardInfo.setUserCard(userCard);
	 		
	 		UserCardResult userCardResult = cardManager.addUserCard(userCardInfo);
	 		String result = util.JsonUtil.getJsonString(userCardResult);
	 		System.out.println("addUserCard---"+ result); 
	 		log.info("addUserCard---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加用户名片");
	 			 	
	 	}
	 	
	 	// updateUserCard 更新用户名片  userCard.cardId、userCard.userId、userCard.cardType必须
	 	@Test(dataProvider="card_updateUserCard",dataProviderClass=ToonCardDataProvider.class, enabled = true, description="更新用户名片")
	 	public void testUpdateUserCard(String caseId, String caseName, String interfaceType, String mobile, String userId, String cardId,
	 			String cardName, String introduction, String expContent) throws InterruptedException{

	 		UserCard userCard = new UserCard();
	 		UserCardInfo userCardInfo = new UserCardInfo();

	 		userCard.setUserId(Long.parseLong(userId));
	 		userCard.setCardId(Long.parseLong(cardId));
	 		userCard.setNickname(cardName);
	 		userCard.setIntroduction(introduction);
	 		userCardInfo.setUserCard(userCard);
	 		Thread.sleep(500);
	 		UserCardResult userCardResult = cardManager.updateUserCard(userCardInfo);
	 		String result = util.JsonUtil.getJsonString(userCardResult);
	 		System.out.println("updateUserCard---"+ result); 
	 		log.info("updateUserCard---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "更新用户名片");
	 	}
	 	
	 	// getUserCardById 通过id获取用户名片
}
