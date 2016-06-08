package com.toon.api.test.humun;
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


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;

import com.toon.api.test.testcase.ApiTestCase;
import com.toon.api.test.testcase.WriteTestReport;

/**
 * toon手机接口 
 * @author haoyuexun
 * @date   2015.8.4
 */

public class ApiTestRun_Common {

	
	private static Log log = LogFactory.getLog(ApiTestRun_Common.class); 
	static int counts = 1 ;  	// 测试次数	
	static FileResourceUtil fru = new FileResourceUtil("config.properties");
	static String resultFile = "";
	
	
	
	
	
	public static void main(String[] args)  {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String resultFile = fru.getConfigValue("resultFile");
		String fileName = fru.getConfigValue("caseFile");
				
		String outsString = "";
		try {
			outsString = readFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileUtil.writefileOnly(resultFile, outsString, "utf-8");
		try {
			WriteTestReport.analyFileAndWriteFile(resultFile, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("finished ");
		
	}
	
	
	static String readFile(String fileName) throws IOException {
		StringBuffer buffer = new StringBuffer();
		
		String outmsg = "";
		BufferedReader bin = null;

		try { 
			bin = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(fileName)), "gbk"));
			String inputLine;
			int numTestcase = 0;
			while ((inputLine = bin.readLine()) != null) {
				if (!inputLine.isEmpty()) {
						
					String[] inouts = inputLine.split("\t",12);
				
					if (inouts.length != 12) {
						
						System.out.println("测试用例参数数量不正确！");
						buffer.append("测试用例参数数量不正确！");
						continue;
					}
					  ++numTestcase;
		
					String caseId = inouts[0];
					String caseName = inouts[1].trim();
					String interfaceType = inouts[2].trim();
					String testURL = inouts[3].trim();
					String deviceId = inouts[4].trim();	
					String platform = inouts[5];
					String platformVersion = inouts[6];
					String appVersion = inouts[7];
					String mobile = inouts[8].trim();
					String bizKey = inouts[9].trim();
					String if_token = inouts[10].trim();
					String expContent = inouts[11].trim().split("\t")[0];   //防止最后有注释影响判断结果

					
					if(interfaceType.equals("")){		//排除非测试用例的情况{
						continue;
					}
					
					Map<String, String> header =  ApiTestCase.getHeader();
					
					Map<String, String> params = ApiTestCase.getTestCase(mobile, deviceId, platform, platformVersion, appVersion, bizKey, if_token);
	
					String datas = "";
					
					for(Map.Entry<String, String> entryset : params.entrySet()){
									 datas = entryset.getValue();
					}

//					System.out.println(datas+ "\n" + header);
					
					log.debug(datas + "\n" + header);
					
					System.out.println("测试地址："+ testURL);
					buffer.append("测试地址："+ testURL + "\r\n");
					
					System.out.println("用例编号：" + caseId + "  用例描述："+ caseName + "  预期结果包含：" + expContent);
					buffer.append("用例编号：" + caseId + "  用例描述："+ caseName+ "  预期结果包含：" + expContent +"\r\n");
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
						
						String expContentPattern = "";
						if(!expContent.equalsIgnoreCase("")){
//							expContentPattern = ".*" + expContent + ".*" ;
							expContentPattern = expContent;
						}else{
							expContentPattern = "该case无预期内容";
						}
						boolean testResult = util.StringUtil.findStrByRegEx(outmsg, expContentPattern);
						
						System.out.println(outmsg + "\n"+ expContentPattern);
						if(testResult){
							System.out.println("============================================\r\nTrue!   预期和实际返回一致,包含-->" + expContent );
							buffer.append("\n============================================\r\nTrue!   预期和实际返回一致，包含--> " + expContent + "\r\n");
						}else{
							System.out.println("============================================\r\nFail!用例编号：" + caseId + "   预期和实际返回码不一致：   预期返回：" + expContent );
							buffer.append("\n============================================\r\nFail!用例编号：" + caseId + "   预期和实际返回码不一致：  预期返回：" + expContent  +"\r\n");
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
				}	//性能测试使用
			}	
			
			System.out.println("测试用例总数为：" + numTestcase);
			buffer.append("测试用例总数为：" + numTestcase + "\n");
		} catch (Exception e) {
			
			System.out.println("An error occurred during file reading " + e);
			
		} finally {
			bin.close();
		}
			
		return buffer.toString();
	}
}
