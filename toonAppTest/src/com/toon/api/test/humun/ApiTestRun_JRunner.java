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


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.*;

import com.toon.api.test.testcase.ApiTestCase;

public class ApiTestRun_JRunner {

	/**
	 * 多线程执行无线测试用例
	 * @author haoyuexun
	 * 
	 */
	static String[] returnMsg ;
	static String logStr = "";
	 	// 线程数
	static FileResourceUtil fru = new FileResourceUtil("config.properties");
	
	static int ThreadNum  = Integer.parseInt(fru.getConfigValue("jrunner_threadNum"));



	final static int TestMinSecond = 1000*0 ;  // 测试时间-毫秒 
	
	public static void main(String[] args)  {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		
//		FileResourceUtil fru = new FileResourceUtil("config.properties");
		String fileName = fru.getConfigValue("caseFile");
		String resultFile = fru.getConfigValue("jrunner_resultFile");

		String outsString = "";
		try {
			outsString = readFile(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileUtil.writefileOnly(resultFile, outsString,"utf8");
		System.out.println("finished ");
	}
	
	
	static String readFile(String fileName) throws IOException {
		StringBuffer buffer = new StringBuffer();
		BufferedReader bin = null;
		String outmsg = "";
		
		try { 
			bin = new BufferedReader(new InputStreamReader(new FileInputStream(
					new File(fileName)), "gbk"));
			String inputLine;
			while ((inputLine = bin.readLine()) != null) {
				if (!inputLine.isEmpty()) {
					String[] inouts = inputLine.split("\t",12);

					if (inouts.length != 12) {
						System.out.println("测试用例参数数量不正确！");
						buffer.append("测试用例参数数量不正确！");
						continue;
					}
					String caseId = inouts[0];
					String caseName = inouts[1];
					String interfaceType = inouts[2];
					String testURL = inouts[3];
					String deviceId = inouts[4];	
					String platform = inouts[5];
					String platformVersion = inouts[6];
					String appVersion = inouts[7];
					String mobile = inouts[8];
					String bizKey = inouts[9];
					String if_token = inouts[10];
					String expContent = inouts[11].trim().split("\t")[0];   //防止最后有注释影响判断结果
//					expContent = StringUtil.regEx2String(expContent);
					
					if(interfaceType.equals("")){		//排除非测试用例的情况
						continue;
					}
				
					Map<String, String> header =  ApiTestCase.getHeader();
					Map<String, String> params = ApiTestCase.getTestCase(mobile, deviceId, platform, platformVersion, appVersion, bizKey, if_token);
					String datas = "";
					for(Map.Entry<String, String> entryset : params.entrySet()){
									 datas = entryset.getValue();
					}

					
					buffer.append("*********************** Begin testcase **************************************\n");
					System.out.println("*********************** Begin testcase **************************************");
					
					System.out.println("测试地址："+ testURL);
					buffer.append("测试地址："+ testURL + "\r\n");
					
					System.out.println("用例编号：" + caseId + "  用例描述："+ caseName + "  预期结果：" + expContent);
					buffer.append("用例编号：" + caseId + "  用例描述："+ caseName+ "  预期结果：" + expContent +"\r\n");
					System.out.println("请求为：" + params);
					buffer.append("请求为：" + params + "\r\n");

					buffer.append("============================================\r\n");
					System.out.println("============================================");
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					String testDate = df.format(new Date());
					System.out.println("测试开始时间为："+ testDate);
					buffer.append("测试开始时间为："+ testDate + "\r\n");
					Long startTime = System.currentTimeMillis();
					Long endTime = System.currentTimeMillis();
					String[] testWebSite = util.StringUtil.getHostByUrl(testURL);
					String webSite = testWebSite[0];
					int passPort = Integer.parseInt(testWebSite[1]);					
					while(endTime - startTime <= TestMinSecond ){    // 请求时间代码还需要完善
						returnMsg =	util.HttpClientMultiThreaded.httpClintPostForm(webSite, testURL, passPort, ThreadNum, header, params, "utf-8");
							
						endTime = System.currentTimeMillis();
					}  // 请求时间代码还需要完善
				
					outmsg =  returnMsg[0];
					logStr = returnMsg[1];		
				
					buffer.append("\n多线程日志......\n" + logStr + "\r\n" );	
					
					/*
					ObjectMapper mapper = new ObjectMapper();  
					JsonNode node = mapper.readTree(outmsg).get(0);   // 返回的都是json数组
					String bodyJson = node.get("Body").toString();
					String fact_status = mapper.readTree(bodyJson).get("Status").toString();
					String jsonStr = util.JsonUtil.jsonFormatter(outmsg);
					
					System.out.println("返回的报文为:\n " + jsonStr );
					buffer.append("\n返回的报文为：\n" + jsonStr + "\r\n" );
					
					if (expStatus.trim().equals(fact_status)){  //
						System.out.println("============================================\r\nTrue!   预期和实际返回一致为status： " + fact_status + "\r\n" );
						buffer.append("\n============================================\r\nTrue!   预期和实际返回一致为status： " + fact_status + "\r\n\r\n");
					}else {
						System.out.println("============================================\r\nFail!   预期和实际返回码不一致：预期status：" + expStatus+ "   实际返回：" + fact_status + "\r\n" );
						buffer.append("\n============================================\r\nFail!    预期和实际返回码不一致：预期status：" + expStatus+ "   实际返回：" + fact_status  +"\r\n\r\n");
					}
					*/
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
						System.out.println("============================================\r\nFail!   预期和实际返回码不一致：   预期返回：" + expContent );
						buffer.append("\n============================================\r\nFail!    预期和实际返回码不一致：  预期返回：" + expContent  +"\r\n");
					}
					buffer.append("*********************** End testcase **************************************\n");
					System.out.println("*********************** End testcase **************************************");
				}				
			}	
		} catch (Exception e) {
			System.out.println("An error occurred during file reading " + e);
		} finally {
			bin.close();
		}
		return buffer.toString();
	}
}
