package com.toon.api.test.testcase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.FileResourceUtil;

import com.toon.api.test.dao.ToonAppDaoService;
import com.toon.api.test.daoMapping.ToonAppMapper;


public class ApiTestCase {
	
	private static final String MD5_KEY = "siyuan_native_ticket_key";

	public static void updateTokenValue() throws IOException
	{
		String testServer = "";
		FileResourceUtil fru = new FileResourceUtil("token.properties");
//		fru.setConfigFile("token.properties");
		String phoneList[] = null;
		if(ApiTestCase.getTestServer().equals("test")){
			testServer = "test";
			String phoneListTest[]={"13581535754"};
			phoneList = phoneListTest;
		}else if(ApiTestCase.getTestServer().equals("online")){
			testServer = "online";
			String phoneListOnline[]={"15300136026","13141255550"};
			phoneList = phoneListOnline;
		}		
//		 phoneList[]={"13581535754","15300136026","13141255550"};   // , "13501007293" , "18611775555"
		
		for(int i=0; i<phoneList.length; i++)
		{
			if(!ApiTestCase.checkStatus(testServer,phoneList[i]))
			{
				String token=ApiTestCase.getUserToken(testServer,phoneList[i]);
				System.out.println("返回的token--" + token + "\n" + fru.getConfigFile());	
				fru.updatePropertiesFile(phoneList[i], token);
				System.out.println("-----" + phoneList[i]);
			}
		}
	}
	
	public static String getUserToken(String testServer, String phone)
	{
		String Url = "";
		String bizKey = "";
		if(testServer.equals("test")){
			Url="http://172.28.6.111:8080/user/userLogin";
		}else if(getTestServer().equals("online")){
			Url="http://124.251.88.61:18087/user/userLogin";
		}
		bizKey="{\"mobile\" : \""+ phone + "\", \"loginType\" : \"3\"}";
		Map<String, String> header = ApiTestCase.getHeader();
		Map<String, String> params = ApiTestCase.getTestCase(phone, "1234", "ios", "3.0", "8.2", bizKey, "no");
//		String datas = "";
//		
//		for(Map.Entry<String, String> entryset : params.entrySet()){
//			datas = entryset.getValue();
//		}

		String outmsg = util.HttpUtil.doPostFormReq(Url, header, params, "utf-8");
		Pattern pattern2 = Pattern.compile("token\":\"(.*?)\",\"tick");
		Matcher matcher2 = pattern2.matcher(outmsg);
		while(matcher2.find()){
	
			return matcher2.group(1);
		}	
		return null;
	}
	
	/*
	 * Check current token is valid or not
	 */
	public static boolean checkStatus(String testServer, String phone)
	{
		String verifyUrl = "";
		if(testServer.equals("test")){
			verifyUrl="http://172.28.6.111:8080/user/userCheckTokenLogin";
		}else if(testServer.equals("online")){
			verifyUrl="http://124.251.88.61:18087/user/userCheckTokenLogin";
		}

		String epxContent="\"result\":\"success\".*\"code\":\"0\"";
		Map<String, String> header =  ApiTestCase.getHeader();
		Map<String, String> params = ApiTestCase.getTestCase(phone, "1", "ios", "3.0", "8.2", "{}", "yes");
		
		String outmsg = util.HttpUtil.doPostFormReq(verifyUrl, header, params, "utf-8");
		return util.StringUtil.findStrByRegEx(outmsg, epxContent);
	}
	
	public static String getTestServer(){
		FileResourceUtil fruConfig = new FileResourceUtil("config.properties");
//		fruConfig.setConfigFile("config.properties");
		String testServer = fruConfig.getConfigValue("server");
		return testServer;
	}
	
	public static String getUserid_online(String mobile){
		FileResourceUtil fru_userOnline = new FileResourceUtil("user_online.properties");
//		fru_userOnline.setConfigFile("user_online.properties");
		String userid = fru_userOnline.getConfigValue(mobile);
		return userid;
	}
	
	/**
	 * 通过mobile获取userid
	 * @param mobile
	 * @return
	 */
	
	public static String getUserId(String mobile){
		String userid = "";
		System.out.println(getTestServer());
		if(getTestServer().equals("test")){
			ToonAppDaoService.openSqlSession();
			ToonAppMapper toonAppMapper = ToonAppDaoService.getToonAppDao();
			userid = toonAppMapper.getUserId(mobile);
			ToonAppDaoService.closeSqlSession();
		}else if(getTestServer().equals("online")){
			userid = getUserid_online(mobile);
		}
		return userid;
	}

	public static Map<String, String> getTestCase( String mobile, String deviceId, String platform, String platformVersion,
			String appVersion, String bizKey, String if_token) {
		

		String userToken  = "";
		String bodyStr = "";
		String data = "";
		String userId = "";
		String ticket ="";
		Map<String, String> params = new HashMap<String, String>();
		userId = getUserId(mobile); 
		FileResourceUtil fru_token = new FileResourceUtil("token.properties");
//		fru_token.setConfigFile("token.properties");
		//只对case中"mobile":""替换成自动获取的，其余的还用case中原有的数据
		if(bizKey.contains("\"mobile\":\"\"") || bizKey.contains("\"deviceId\":\"\"") ){
			bizKey = bizKey.replaceAll("\"mobile\":\"\"", "\"mobile\":\""+ mobile +"\"").replaceAll("\"deviceId\":\"\"", "\"deviceId\":\"" + deviceId + "\"");
		}

//		bizKey = bizKey.replaceAll("\"mobile\":\"[0-9]{10,}\"", "\"mobile\":\""+ mobile +"\"").replaceAll("\"deviceId\":\"[0-9]{15,}\"", "\"deviceId\":\"" + deviceId + "\"");
		if(if_token.equalsIgnoreCase("yes")){
			userToken = fru_token.getConfigValue(mobile);
			if(platform.equalsIgnoreCase("ios")){
				deviceId = userToken;
				bizKey = bizKey.replaceAll("\"deviceId\":\"[0-9]{15,}\"", "\"deviceId\":\"" + userToken+ "\"");
			}
		}

//		userId = getUserId(mobile);   
		ticket = genTicket(userId, deviceId);  
		
		if(bizKey.contains("\"userId\":\"\"") || bizKey.contains("\"token\":\"\"") ){
			bizKey = bizKey.replaceAll("\"userId\":\"\"","\"userId\":\"" + userId +"\"").replaceAll("\"token\":\"\"", "\"token\":\"" + userToken + "\"");
		}
	
//		bizKey = bizKey.replaceAll("\"userId\":\"[0-9]{1,}\"","\"userId\":\"" + userId +"\"").replaceAll("\"token\":\".*\"", "\"token\":\"" + userToken + "\"")	;	
	
		if(bizKey.equals("")){
			if(if_token.equalsIgnoreCase("yes")){
				data = "{\"authKey\":{\"appVersion\":\"" + appVersion +"\",\"deviceId\":\"" + deviceId + "\",\"platform\":\"" + platform + "\",\"platformVersion\":\"" + platformVersion  + "\",\"ticket\":\"" + ticket 
						+"\",\"userId\":\"" + userId +"\",\"userToken\":\"" + userToken + "\"}}";
			}else{
				data = "{\"authKey\":{\"appVersion\":\"" + appVersion +"\",\"deviceId\":\"" + deviceId + "\",\"platform\":\"" + platform + "\",\"platformVersion\":\"" + platformVersion  + "\"}}";
			}
		}else {
			if(!bizKey.contains("\\")){
				bodyStr = bizKey.replaceAll("\\\"", "\\\\\"");
			}else{
				bodyStr = bizKey;
			}
			if(if_token.equalsIgnoreCase("yes")){
				data = "{\"authKey\":{\"appVersion\":\"" + appVersion +"\",\"deviceId\":\"" + deviceId + "\",\"platform\":\"" + platform + "\",\"platformVersion\":\"" + platformVersion  + "\",\"ticket\":\"" + ticket 
						+"\",\"userId\":\"" + userId +"\",\"userToken\":\"" + userToken + "\"},\"bizKey\":\"" + bodyStr + "\"}";
			}else{
				data = "{\"authKey\":{\"appVersion\":\"" + appVersion +"\",\"deviceId\":\"" + deviceId + "\",\"platform\":\"" + platform + "\",\"platformVersion\":\"" + platformVersion  + "\"},\"bizKey\":\"" + bodyStr + "\"}";
			}
		}
		
		// 防止测试用例重复数据
		if(data.contains("+rnd")){
			data = data.replace("+rnd", ""+System.currentTimeMillis());
		}
		params.put("toonKey", data);
		return params;
	}	
	
	public static Map<String, String> getHeader() {
	
		Map<String, String> header = new HashMap<String, String>();
		header.put("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
		
		return header;
	}
	
				
		/**
		 * ticket生成规则
		 * @param userId
		 * @param deviceId
		 * @return
		 */
		public static String genTicket(String userId,String deviceId){
			return util.MD5.md5s(userId + "|" + deviceId + "|" + MD5_KEY).toUpperCase();
		}
}
