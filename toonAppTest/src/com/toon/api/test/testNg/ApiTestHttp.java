package com.toon.api.test.testNg;
import util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader; 
//import java.io.UnsupportedEncodingException;

import java.text.SimpleDateFormat;
//import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

import com.toon.api.test.testcase.ApiTestCase;

/**
 * toon手机接口 
 * @author haoyuexun
 * @date   2015.8.4
 */

public class ApiTestHttp {

	
	private static Log log = LogFactory.getLog(ApiTestHttp.class); 
	static int counts = 1 ;  	// 测试次数	
	static String[] returnMsg = new String[2];

	
	/**
	 * 
	 * @param caseId
	 * @param caseName
	 * @param interfaceType
	 * @param testURL
	 * @param deviceId
	 * @param platform
	 * @param platformVersion
	 * @param appVersion
	 * @param mobile
	 * @param bizKey
	 * @param expContent
	 * @return  返回字符数组 0-接口返回结果  1-接口请求
	 * @throws IOException
	 */
	static String[] testApi(String caseId, String caseName, String interfaceType, String testURL, String deviceId, String platform, String platformVersion,
			String appVersion, String mobile, String bizKey, String if_token, String expContent) throws IOException{
		StringBuffer buffer = new StringBuffer();
		String[] returnMsg = new String[2];
		String outmsg = "";
	
		Map<String, String> header =  ApiTestCase.getHeader();
		Map<String, String> params = ApiTestCase.getTestCase(mobile, deviceId, platform, platformVersion, appVersion, bizKey, if_token);
		String datas = "";  
		for(Map.Entry<String, String> entryset : params.entrySet()){
			 datas = entryset.getValue();
		}

		System.out.println(datas+ "\n" + header);
		
		log.debug(datas + "\n" + header);
		
		System.out.println("测试地址："+ testURL);
		buffer.append("测试地址："+ testURL + "\r\n");
		
		System.out.println("用例编号：" + caseId + "  用例描述："+ caseName + "  预期结果：" );
		buffer.append("用例编号：" + caseId + "  用例描述："+ caseName+ "  预期结果：" + "11" +"\r\n");
		System.out.println("请求是：" + datas);
		buffer.append("请求是：" + datas + "\r\n");
		
		buffer.append("============================================\r\n");
		System.out.println("============================================");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				
		Long totalTestTime = 0L;
		Long averageTestTime= 0L ;
		
		for(int j=0; j<counts; j++){		//性能测试使用
			Long startTime = System.currentTimeMillis();
			String testDate = df.format(new Date());
			System.out.println("测试开始时间为："+ testDate);
			buffer.append("测试开始时间为："+ testDate + "\r\n");
			
			outmsg = util.HttpUtil.doPostFormReq(testURL, header, params, "utf-8");
			
			Long endTime = System.currentTimeMillis();	
			Long useTime = endTime - startTime;	
			totalTestTime += useTime;
			
			System.out.println("============================================\r\n" +  interfaceType +" 该接口响应时间为：" + useTime + "毫秒\r\n============================================");
			buffer.append("============================================\r\n"+  interfaceType +" 该接口响应时间为：" + useTime + "毫秒\r\n============================================\r\n");
				
			System.out.println("返回结果是：" + outmsg);
			
			if(!outmsg.contains("result")){
	        	continue;
	        }
			String jsonStr = util.JsonUtil.jsonFormatter(outmsg);
			System.out.println("返回的报文为:\n " + jsonStr );
			buffer.append("\n返回的报文为：\n" + jsonStr + "\r\n" );
			
			String expContentPattern = ".*" + expContent + ".*" ;
			boolean testResult = util.StringUtil.findStrByRegEx(outmsg, expContentPattern);
			
			System.out.println(outmsg + "\n"+ expContentPattern);
			if(testResult){
				System.out.println("============================================\r\nTrue!   预期和实际返回一致,包含-->" + expContent );
				buffer.append("\n============================================\r\nTrue!   预期和实际返回一致，包含--> " + expContent + "\r\n");
			}else{
				System.out.println("============================================\r\nFail! 用例编号:" + caseId + "   预期和实际返回码不一致：   预期返回：" + expContent );
				buffer.append("\n============================================\r\nFail! 用例编号:" + caseId + "    预期和实际返回码不一致：  预期返回：" + expContent  +"\r\n");
			}
					
					/*	
						ObjectMapper mapper = new ObjectMapper();  
						JsonNode node = mapper.readTree(outmsg).get(0);   // 返回的都是json数组
						String bodyJson = node.get("Body").toString();
						String fact_status = mapper.readTree(bodyJson).get("Status").toString();
						
//						String jsonStr = util.JsonUtil.jsonFormatter(outmsg);
						
						if (expStatus.trim().equalsIgnoreCase(fact_status)  ){  //
							System.out.println("============================================\r\nTrue!   预期和实际返回一致为status： " + fact_status );
							buffer.append("\n============================================\r\nTrue!   预期和实际返回一致为status： " + fact_status + "\r\n");
						}else {
							System.out.println("============================================\r\nFail!   预期和实际返回码不一致：预期status：" + expStatus+ "   实际返回：" + fact_status );
							buffer.append("\n============================================\r\nFail!    预期和实际返回码不一致：预期status：" + expStatus+ "   实际返回：" + fact_status  +"\r\n");
						}   
					*/
						
					}
					
				
					averageTestTime = totalTestTime/counts;
					System.out.println("============================================\r\n" +  interfaceType +" 该接口请求" + counts + "次的总时间是"+ totalTestTime + "毫秒\r\n============================================");
					System.out.println("============================================\r\n" +  interfaceType +" 该接口请求" + counts + "次的平均时间是"+ averageTestTime + "毫秒\r\n============================================");
					buffer.append("============================================\r\n" +  interfaceType +" 该接口请求" + counts + "次的平均时间是"+ averageTestTime + "毫秒\r\n========================================================================================\n");
					String testDate2 = df.format(new Date());
					System.out.println("测试结束时间为："+ testDate2);
	
			
					returnMsg[0] = outmsg;
					returnMsg[1] = datas;
					
					return returnMsg;
		
	}
}
