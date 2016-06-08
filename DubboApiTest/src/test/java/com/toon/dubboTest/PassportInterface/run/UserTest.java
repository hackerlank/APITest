package com.toon.dubboTest.PassportInterface.run;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.systoon.parameter.ServiceResult;
import com.systoon.passport.inter.IUserManager;

import com.systoon.passport.parameter.DeviceInfo;
import com.systoon.passport.parameter.DeviceListResult;
import com.systoon.passport.parameter.IdListInfo;
import com.systoon.passport.parameter.UserCommonInformationInfo;
import com.systoon.passport.parameter.UserCommonLocationInfo;
import com.systoon.passport.parameter.UserInfo;
import com.systoon.passport.parameter.UserListResult;
import com.systoon.passport.parameter.UserResult;
import com.systoon.passport.parameter.UserStatusInfo;
import com.systoon.passport.parameter.UserUpdatePwdInfo;
import com.systoon.passport.pojo.Device;
import com.systoon.passport.pojo.User;
import com.systoon.passport.pojo.UserCommonInformation;
import com.systoon.passport.pojo.UserCommonLocation;
import com.systoon.securityuserdata.parameter.MobilePhoneListInfo;
import com.toon.dataprovider.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

//import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;



@ContextConfiguration(locations = { "classpath:applicationContext.xml" })

public class UserTest extends AbstractTestNGSpringContextTests {
	private static final Log log = LogFactory.getLog(UserTest.class);
	
	@Resource(name="userService")
//	@Autowired  
	 
	 private IUserManager userManager;
	
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
	 	// getUserInfo 获取用户信息，通过手机号或者用户id
	 	@Test(dataProvider="user_getUserInfo",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取用户信息")
	 	public void testGetUserInfo(String caseId, String caseName, String interfaceType, String param, String expContent){
	 		
 			UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(param);
	 		userInfo.setUser(user);
	 		UserResult userResult = userManager.getUserInfo(userInfo);
	 		String result = util.JsonUtil.getJsonString(userResult);
	 		System.out.println("eeeee---"+ result); 
	 		log.info("eeeee---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "获取用户信息");
	 		
	 	}
	 	
	 	// getUserId 通过手机号获取用户id
	 	@Test(dataProvider="user_getUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过手机号获取用户id")
		public void testGetUserId(String caseId, String caseName, String interfaceType, String param, String expContent){
	 		
 			UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(param);
	 		userInfo.setUser(user);
	 		UserResult userResult = userManager.getUserId(userInfo);
	 		String result = util.JsonUtil.getJsonString(userResult);
	 		System.out.println("eeeee---"+ result); 
	 		log.info("eeeee---"+ result);

	 		util.StringUtil.AssertByRegEx(result, expContent, "获取用户ID");
	 	}
	 	
	 	// getExtendUserInfo 获取用户扩展信息
	 	@Test(dataProvider="user_getExtendUserInfo",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取用户扩展信息")
	 	public void testGetExtendUserInfo(String caseId, String caseName, String interfaceType, String param, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(param);
	 		userInfo.setUser(user);
	 		UserResult userResult = userManager.getExtendUserInfo(userInfo);
	 		String result = util.JsonUtil.getJsonString(userResult);
	 		System.out.println("eeeee---"+ result); 
	 		log.info("eeeee---"+ result);

	 		util.StringUtil.AssertByRegEx(result, expContent, "获取用户扩展信息");
	 	}
	    //  queryUserInfo 查询用户信息
	 	@Test(dataProvider="user_queryUserInfo",dataProviderClass=ToonDataProvider.class, enabled = true, description="查询用户信息")
	 	public void testQueryUserInfo(String caseId, String caseName, String interfaceType, String param, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(param);
	 		userInfo.setUser(user);
	 		UserListResult userResultList = userManager.queryUserInfo(userInfo);
	 		String result = util.JsonUtil.getJsonString(userResultList);
	 		System.out.println("eeeee---"+ result); 
	 		log.info("eeeee---"+ result);

	 		util.StringUtil.AssertByRegEx(result, expContent, "多个用户获取用户扩展信息");
	 	}
	 
	 	// getUserListByIdList 通过多个用户id获取user信息
	 	@Test(dataProvider="user_getUserListByIdList",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过多个用户id获取user信息")
	 	public void testGetUserListByIdList(String caseId, String caseName, String interfaceType, String param, String expContent){
	 		IdListInfo idList = new IdListInfo();
	 			 
	 		Long[] arrUserId =util.StringUtil.stringArray2LongArray(param.split(":"));
	 		List<Long> userList = Arrays.asList(arrUserId);
	 		idList.setIdList(userList);
	 		UserListResult userResultList = userManager.getUserListByIdList(idList);
	 		String result = util.JsonUtil.getJsonString(userResultList);
	 		System.out.println("eeeee---"+ result); 
	 		log.info("eeeee---"+ result);

	 		util.StringUtil.AssertByRegEx(result, expContent, "多个用户获信息");
	 	}
	 	
	 	// updatePassword 更改用户密码
	 	@Test(dataProvider="user_updatePassword",dataProviderClass=ToonDataProvider.class, enabled = true, description="更改用户密码")
	 	public void testUpdatePassword(String caseId, String caseName, String interfaceType, String mobile, String oldPwd, String newPwd, String expContent){
	 		UserUpdatePwdInfo userPwdInfo = new UserUpdatePwdInfo ();
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		userPwdInfo.setUserId(userId);
	 		userPwdInfo.setOldPassword(oldPwd);
	 		userPwdInfo.setPassword(newPwd);
	 		
	 		ServiceResult serviceResult = userManager.updatePassword(userPwdInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updatePassword---"+ result); 
	 		log.info("updatePassword---"+ result);

	 		util.StringUtil.AssertByRegEx(result, expContent, "更改用户密码");
	 		
	 	}
	 	
	 	// updateUserInfo 用户修改资料
	 	@Test(dataProvider="user_updateUserInfo",dataProviderClass=ToonDataProvider.class, enabled = true, description="用户修改资料")
	 	public void testUpdateUserInfo(String caseId, String caseName, String interfaceType, String mobile, String birthday, String email, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		UserInfo userInfoNew = new UserInfo(); 
	 		User user = new User();
	 		User userNew = new User();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		userNew = userManager.getUserId(userInfo).getUser();
	 		userNew.setBirthday(birthday);
	 		userNew.setEmail(email);
	 		userInfoNew.setUser(userNew);
	 
	 		ServiceResult serviceResult = userManager.updateUserInfo(userInfoNew);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		
	 		System.out.println("user_updateUserInfo---"+ result); 
	 		log.info("user_updateUserInfo---"+ result);
	 		
	 		//获取老用户信息，查看是否更新成功
	 		ServiceResult serviceResultNew = userManager.getUserInfo(userInfo);
	 		String resultNew = util.JsonUtil.getJsonString(serviceResultNew);
	 		System.out.println("user_updateUserInfo---"+ resultNew); 
	 		log.info("user_updateUserInfo---"+ resultNew);

	 		util.StringUtil.AssertByRegEx(resultNew, expContent, "更新用户信息");
	 	}
	 	
	 	// saveUserDeviceInfo 保存用户设备信息
	 	@Test(dataProvider="user_saveUserDeviceInfo",dataProviderClass=ToonDataProvider.class, enabled = true, description="保存用户设备信息")
	 	public void testSaveUserDeviceInfo(String caseId, String caseName, String interfaceType, String mobile, String channel, String Platform, String AppVersion,String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		DeviceInfo deviceInfo = new DeviceInfo();
	 		Device device = new Device();
	 		
//	 		DeviceInfo deviceInfoSearch = new DeviceInfo();
//	 		Device deviceSearch = new Device();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	
	 		device.setUserId(userId);
	 		device.setChannel(channel);
	 		device.setPlatform(Platform);
	 		device.setAppVersion(AppVersion);

	 		deviceInfo.setDevice(device);
	 		
	 		ServiceResult serviceResult = userManager.saveUserDeviceInfo(deviceInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("user_saveUserDeviceInfo---"+ result); 
	 		log.info("user_saveUserDeviceInfo---"+ result);
	 		
//	 		deviceSearch.setUserId(userId);
//	 		deviceInfoSearch.setDevice(deviceSearch);
//	 		System.out.println(userId);
//	 		ServiceResult serviceResult2 = userManager.getUserDeviceInfoByUserId(deviceInfoSearch);
//	 		String result2 =  util.JsonUtil.getJsonString(serviceResult2);
//	 		System.out.println(result2);
//	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "保存用户设备信息");
	 	}
	 	
	 	// getUserDeviceInfoByUserId 获取用户设备信息
	 	@Test(dataProvider="user_getUserDeviceInfoByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="获取用户设备信息")
	 	public void testGetUserDeviceInfoByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent) {
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		DeviceInfo deviceInfo = new DeviceInfo();
	 		Device device = new Device();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println("userid---" + userId);

	 		device.setUserId(userId);
	 		deviceInfo.setDevice(device);
	 		ServiceResult serviceResult = userManager.getUserDeviceInfoByUserId(deviceInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("getUserDeviceInfoByUserId---"+ result); 
	 		log.info("getUserDeviceInfoByUserId---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "获取用户设备信息");
	 	}
	 	
	 	// getDeviceListByUserIdList 批量获取用户设备信息
	 	@Test (dataProvider="user_getDeviceListByUserIdList",dataProviderClass=ToonDataProvider.class, enabled = true, description="批量获取用户设备信息")
	 	public void testGetDeviceListByUserIdList(String caseId, String caseName, String interfaceType, String param, String expContent){

	 		Long[] arrUserId =util.StringUtil.stringArray2LongArray(param.split(":"));
	 		List<Long> userList = Arrays.asList(arrUserId);

	 		
	 		IdListInfo idListInfo = new IdListInfo();
	 		idListInfo.setIdList(userList);
	 		DeviceListResult deviceListResult = userManager.getDeviceListByUserIdList(idListInfo);
	 		String result = util.JsonUtil.getJsonString(deviceListResult);
	 		System.out.println("getDeviceListByUserIdList---"+ result); 
	 		log.info("getDeviceListByUserIdList---"+ result);
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "批量获取用户设备信息");
	 		
	 	}
	 	
	 	// updateUserStatus 更改用户状态
	 	@Test(dataProvider="user_updateUserStatus",dataProviderClass=ToonDataProvider.class, enabled = true, description="更改用户状态")
	 	public void testUpdateUserStatus(String caseId, String caseName, String interfaceType, String mobile, String status, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserStatusInfo  userStatusInfo = new UserStatusInfo();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		
	 		userStatusInfo.setUserId(userId);
	 		userStatusInfo.setStatus(Integer.parseInt(status));

	 		ServiceResult serviceResult = userManager.updateUserStatus(userStatusInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		
	 		System.out.println("user_updateUserStatus---"+ result); 
	 		log.info("user_updateUserStatus---"+ result);
	 		
//	 		//获取老用户信息，查看是否更新成功
	 		ServiceResult serviceResultNew = userManager.getUserInfo(userInfo);
	 		String resultNew = util.JsonUtil.getJsonString(serviceResultNew);
	 		System.out.println("user_updateUserInfo---"+ resultNew); 
	 		log.info("user_updateUserInfo---"+ resultNew);
	 		
	 		util.StringUtil.AssertByRegEx(resultNew, expContent, "更改用户状态");

	 	}
	 	
	 	// addUserCommonLocation  添加常用地址
	 	@Test(dataProvider="user_addUserCommonLocation",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加常用地址")
	 	public void testAddUserCommonLocation(String caseId, String caseName, String interfaceType, String mobile, String addressName,
	 			String address, String addressDetail, String status, String LocationCoordinate, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserCommonLocation userCommonLocation = new UserCommonLocation();
	 		UserCommonLocationInfo userCommonLocationInfo = new UserCommonLocationInfo();

	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		userCommonLocation.setUserId(userId);
	 		userCommonLocation.setName(addressName);
	 		userCommonLocation.setAddress(address);
	 		userCommonLocation.setLocationDetail(addressDetail);
	 		userCommonLocation.setStatus(Integer.parseInt(status));
	 		userCommonLocation.setLocationCoordinate(LocationCoordinate);
	 		
//	 		userCommonLocationInfo.setUserID(userId);
	 		userCommonLocationInfo.setUserCommonLocation(userCommonLocation);
	 		
	 		ServiceResult serviceResult = userManager.addUserCommonLocation(userCommonLocationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("addUserCommonLocation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加常用地址");
	 	}
	 	
	 	// updateUserCommonLocation  更新用户常用地址
	 	@Test(dataProvider="user_updateUserCommonLocation",dataProviderClass=ToonDataProvider.class, enabled = true, description="更新用户常用地址")
	 	public void testUpdateUserCommonLocation(String caseId, String caseName, String interfaceType, String mobile, String addressName,
	 			String CommonLocationId, String status,  String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserCommonLocationInfo userCommonLocationInfo = new UserCommonLocationInfo();
	 		UserCommonLocation userCommonLocation = new UserCommonLocation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		userCommonLocation.setUserId(userId);
	 		userCommonLocation.setCommonLocationId(Long.parseLong(CommonLocationId));
	 		userCommonLocation.setName(addressName);
	 		userCommonLocation.setStatus(Integer.parseInt(status));
	 		userCommonLocationInfo.setUserCommonLocation(userCommonLocation);
	 		
	 		ServiceResult serviceResult = userManager.updateUserCommonLocation(userCommonLocationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updateUserCommonLocation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "更新用户常用地址");
	 		
	 	}
	 	
	 	// deleteUserCommonLocation  删除用户常用地址
	 	@Test(dataProvider="user_deleteUserCommonLocation",dataProviderClass=ToonDataProvider.class, enabled = true, description="更新用户常用地址")
	 	public void testDeleteUserCommonLocation(String caseId, String caseName, String interfaceType, String mobile, 
	 			String CommonLocationId,   String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserCommonLocationInfo userCommonLocationInfo = new UserCommonLocationInfo();
	 		UserCommonLocation userCommonLocation = new UserCommonLocation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		userCommonLocation.setUserId(userId);
	 		userCommonLocation.setCommonLocationId(Long.parseLong(CommonLocationId));
	 		userCommonLocationInfo.setUserCommonLocation(userCommonLocation);
	 		
	 		ServiceResult serviceResult = userManager.deleteUserCommonLocation(userCommonLocationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("deleteUserCommonLocation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "删除用户常用地址");
	 	}
	 	
	 	// getAllUserCommonLocationsByUserId  通过用户id查询所有常用地址
	 	@Test(dataProvider="user_getAllUserCommonLocationsByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过用户id查询所有常用地址")
	 	public void testGetAllUserCommonLocationsByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		
		 		UserInfo userInfo = new UserInfo();
		 		User user = new User();
		 		UserCommonLocationInfo userCommonLocationInfo = new UserCommonLocationInfo();
		 		UserCommonLocation userCommonLocation = new UserCommonLocation();
		 		
		 		user.setMobilePhone(mobile);
		 		userInfo.setUser(user);
		 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
		 		userCommonLocation.setUserId(userId);
		 		userCommonLocationInfo.setUserCommonLocation(userCommonLocation);
		 		
		 		ServiceResult serviceResult = userManager.getAllUserCommonLocationsByUserId(userCommonLocationInfo);
		 		String result = util.JsonUtil.getJsonString(serviceResult);
		 		System.out.println("getAllUserCommonLocationsByUserId---"+ result); 
		 		log.info("getAllUserCommonLocationsByUserId---"+ result);
		 		
		 		util.StringUtil.AssertByRegEx(result, expContent, "通过用户id查询所有常用地址");
	 	}
	 	
	 	// updateUserAuthMobilePhone  userInfo - 用户userid和手机号必须传   更改用户手机号
	 	@Test(dataProvider="user_updateUserAuthMobilePhone",dataProviderClass=ToonDataProvider.class, enabled = true, description="更换用户手机号")   //谨慎使用该接口，容易导致数据库mycat缓存问题
	 	public void testUpdateUserAuthMobilePhone(String caseId, String caseName, String interfaceType, String mobile, String newMobile, String expContent){

	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserInfo userInfoNew = new UserInfo();
	 		User userNew = new User();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		userNew.setMobilePhone(newMobile);
	 		userNew.setUserId(userId);
	 		userInfoNew.setUser(userNew);

	 		ServiceResult serviceResult =userManager.updateUserAuthMobilePhone(userInfoNew);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updateUserAuthMobilePhone---"+ result); 
	 		
	 	// 改了数据后，不要马上查，不然 读写不一致 导致出错
//	 		UserResult userResult2 = userManager.getUserInfo(userInfoNew);
//	 		String result2 = util.JsonUtil.getJsonString(userResult2);
//	 		System.out.println("查询用户---"+ result2); 
//	 		log.info("eeeee---"+ result2);
//	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "更改用户手机号");
	 	}
	 	
	 	// updateUserAuthEmail  更改安全邮箱  userInfo - 用户userid和邮箱必传必须传
	 	@Test(dataProvider="user_updateUserAuthEmail",dataProviderClass=ToonDataProvider.class, enabled = true, description="更改安全邮箱")
	 	public void testUpdateUserAuthEmail(String caseId, String caseName, String interfaceType, String mobile, String email, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		UserInfo userInfoNew = new UserInfo();
	 		User userNew = new User();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		userNew.setEmail(email);
	 		userNew.setUserId(userId);
	 		userInfoNew.setUser(userNew);
	 		
	 		ServiceResult serviceResult =userManager.updateUserAuthEmail(userInfoNew);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updateUserAuthEmail---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "更改安全邮箱");
	 	}
	 	
	 	// addUserCommonInformation 添加常用资讯
	 	@Test(dataProvider="user_addUserCommonInformation",dataProviderClass=ToonDataProvider.class, enabled = true, description="添加常用资讯")
	 	public void testAddUserCommonInformation(String caseId, String caseName, String interfaceType, String mobile, String Content, String SimpleName,
	 			String status, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();

	 		UserCommonInformationInfo userCommonInformationInfo = new UserCommonInformationInfo();
	 		UserCommonInformation userCommonInformation = new UserCommonInformation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		userCommonInformation.setUserId(userId);
	 		userCommonInformation.setContent(Content);
	 		userCommonInformation.setSimpleName(SimpleName);
	 		userCommonInformation.setStatus(Integer.parseInt(status));
	 		
	 		userCommonInformationInfo.setUserCommonInformation(userCommonInformation);
	 		
	 		ServiceResult serviceResult = userManager.addUserCommonInformation(userCommonInformationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("addUserCommonInformation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加常用资讯");
	 	}
	 	
	 	// updateUserCommonInformation  更新常用资讯
	 	@Test(dataProvider="user_updateUserCommonInformation",dataProviderClass=ToonDataProvider.class, enabled = true, description="更新常用资讯")
	 	public void testUpdateUserCommonInformation(String caseId, String caseName, String interfaceType, String mobile, String commonInformationId, 
	 			String Content, String SimpleName, String status, String expContent) {
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();

	 		UserCommonInformationInfo userCommonInformationInfo = new UserCommonInformationInfo();
	 		UserCommonInformation userCommonInformation = new UserCommonInformation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		userCommonInformation.setUserId(userId);
	 		userCommonInformation.setCommonInformationId(Long.parseLong(commonInformationId));
	 		userCommonInformation.setContent(Content);
	 		userCommonInformation.setSimpleName(SimpleName);
	 		userCommonInformation.setStatus(Integer.parseInt(status));
	 		
	 		userCommonInformationInfo.setUserCommonInformation(userCommonInformation);
	 		
	 		ServiceResult serviceResult = userManager.updateUserCommonInformation(userCommonInformationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updateUserCommonInformation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "添加常用资讯");
	 	}
	 	
	 	// deleteUserCommonInformation 删除常用资讯
	 	@Test(dataProvider="user_deleteUserCommonInformation",dataProviderClass=ToonDataProvider.class, enabled = true, description="删除常用资讯")
	 	public void testDeleteUserCommonInformation(String caseId, String caseName, String interfaceType, String mobile, String commonInformationId, 
	 			 String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();

	 		UserCommonInformationInfo userCommonInformationInfo = new UserCommonInformationInfo();
	 		UserCommonInformation userCommonInformation = new UserCommonInformation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		userCommonInformation.setUserId(userId);
	 		userCommonInformation.setCommonInformationId(Long.parseLong(commonInformationId));		
	 		userCommonInformationInfo.setUserCommonInformation(userCommonInformation);
	 		
	 		ServiceResult serviceResult = userManager.deleteUserCommonInformation(userCommonInformationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("updateUserCommonInformation---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "删除常用资讯");
	 	}
	 	
	 	// getAllUserCommonInformationsByUserId  查询用户的所有常用资讯
	 	@Test(dataProvider="user_getAllUserCommonInformationsByUserId",dataProviderClass=ToonDataProvider.class, enabled = true, description="查询用户的所有常用资讯")
	 	public void testGetAllUserCommonInformationsByUserId(String caseId, String caseName, String interfaceType, String mobile, String expContent){
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();

	 		UserCommonInformationInfo userCommonInformationInfo = new UserCommonInformationInfo();
	 		UserCommonInformation userCommonInformation = new UserCommonInformation();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		userCommonInformation.setUserId(userId);
	 		userCommonInformationInfo.setUserCommonInformation(userCommonInformation);
	 		
	 		ServiceResult serviceResult = userManager.getAllUserCommonInformationsByUserId(userCommonInformationInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("getAllUserCommonInformationsByUserId---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "查询用户的所有常用资讯");
	 	}
	 	
	 	// matchFriendsByAddressBook  通过手机通讯录匹配的好友
	 	@Test(dataProvider="user_matchFriendsByAddressBook",dataProviderClass=ToonDataProvider.class, enabled = true, description="通过手机通讯录匹配的好友")
	 	public void testMatchFriendsByAddressBook(String caseId, String caseName, String interfaceType, String mobile, String phoneNumberList, String expContent) {
	 		
	 		UserInfo userInfo = new UserInfo();
	 		User user = new User();
	 		MobilePhoneListInfo mobilePhoneListInfo = new MobilePhoneListInfo();
	 		
	 		user.setMobilePhone(mobile);
	 		userInfo.setUser(user);
	 		Long userId = userManager.getUserId(userInfo).getUser().getUserId();
	 		System.out.println(userId);
	 		
	 		String[] arrPhoneNumber= phoneNumberList.split(":");
	 		List<String> mobilePhoneList = Arrays.asList(arrPhoneNumber);
	 		
	 		mobilePhoneListInfo.setUserId(userId);
	 		mobilePhoneListInfo.setMobilePhoneList(mobilePhoneList);
	 		
	 		ServiceResult serviceResult = userManager.matchFriendsByAddressBook(mobilePhoneListInfo);
	 		String result = util.JsonUtil.getJsonString(serviceResult);
	 		System.out.println("matchFriendsByAddressBook---"+ result); 
	 		
	 		util.StringUtil.AssertByRegEx(result, expContent, "通过手机通讯录匹配的好友");
	 	
	 	}
}
