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
 * toon�ֻ��ӿ� 
 * @author haoyuexun
 * @date   2015.8.4
 */

public class ApiTestHttp {

	
	private static Log log = LogFactory.getLog(ApiTestHttp.class); 
	static int counts = 1 ;  	// ���Դ���	
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
	 * @return  �����ַ����� 0-�ӿڷ��ؽ��  1-�ӿ�����
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
		
		System.out.println("���Ե�ַ��"+ testURL);
		buffer.append("���Ե�ַ��"+ testURL + "\r\n");
		
		System.out.println("������ţ�" + caseId + "  ����������"+ caseName + "  Ԥ�ڽ����" );
		buffer.append("������ţ�" + caseId + "  ����������"+ caseName+ "  Ԥ�ڽ����" + "11" +"\r\n");
		System.out.println("�����ǣ�" + datas);
		buffer.append("�����ǣ�" + datas + "\r\n");
		
		buffer.append("============================================\r\n");
		System.out.println("============================================");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
				
		Long totalTestTime = 0L;
		Long averageTestTime= 0L ;
		
		for(int j=0; j<counts; j++){		//���ܲ���ʹ��
			Long startTime = System.currentTimeMillis();
			String testDate = df.format(new Date());
			System.out.println("���Կ�ʼʱ��Ϊ��"+ testDate);
			buffer.append("���Կ�ʼʱ��Ϊ��"+ testDate + "\r\n");
			
			outmsg = util.HttpUtil.doPostFormReq(testURL, header, params, "utf-8");
			
			Long endTime = System.currentTimeMillis();	
			Long useTime = endTime - startTime;	
			totalTestTime += useTime;
			
			System.out.println("============================================\r\n" +  interfaceType +" �ýӿ���Ӧʱ��Ϊ��" + useTime + "����\r\n============================================");
			buffer.append("============================================\r\n"+  interfaceType +" �ýӿ���Ӧʱ��Ϊ��" + useTime + "����\r\n============================================\r\n");
				
			System.out.println("���ؽ���ǣ�" + outmsg);
			
			if(!outmsg.contains("result")){
	        	continue;
	        }
			String jsonStr = util.JsonUtil.jsonFormatter(outmsg);
			System.out.println("���صı���Ϊ:\n " + jsonStr );
			buffer.append("\n���صı���Ϊ��\n" + jsonStr + "\r\n" );
			
			String expContentPattern = ".*" + expContent + ".*" ;
			boolean testResult = util.StringUtil.findStrByRegEx(outmsg, expContentPattern);
			
			System.out.println(outmsg + "\n"+ expContentPattern);
			if(testResult){
				System.out.println("============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ��,����-->" + expContent );
				buffer.append("\n============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ�£�����--> " + expContent + "\r\n");
			}else{
				System.out.println("============================================\r\nFail! �������:" + caseId + "   Ԥ�ں�ʵ�ʷ����벻һ�£�   Ԥ�ڷ��أ�" + expContent );
				buffer.append("\n============================================\r\nFail! �������:" + caseId + "    Ԥ�ں�ʵ�ʷ����벻һ�£�  Ԥ�ڷ��أ�" + expContent  +"\r\n");
			}
					
					/*	
						ObjectMapper mapper = new ObjectMapper();  
						JsonNode node = mapper.readTree(outmsg).get(0);   // ���صĶ���json����
						String bodyJson = node.get("Body").toString();
						String fact_status = mapper.readTree(bodyJson).get("Status").toString();
						
//						String jsonStr = util.JsonUtil.jsonFormatter(outmsg);
						
						if (expStatus.trim().equalsIgnoreCase(fact_status)  ){  //
							System.out.println("============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ��Ϊstatus�� " + fact_status );
							buffer.append("\n============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ��Ϊstatus�� " + fact_status + "\r\n");
						}else {
							System.out.println("============================================\r\nFail!   Ԥ�ں�ʵ�ʷ����벻һ�£�Ԥ��status��" + expStatus+ "   ʵ�ʷ��أ�" + fact_status );
							buffer.append("\n============================================\r\nFail!    Ԥ�ں�ʵ�ʷ����벻һ�£�Ԥ��status��" + expStatus+ "   ʵ�ʷ��أ�" + fact_status  +"\r\n");
						}   
					*/
						
					}
					
				
					averageTestTime = totalTestTime/counts;
					System.out.println("============================================\r\n" +  interfaceType +" �ýӿ�����" + counts + "�ε���ʱ����"+ totalTestTime + "����\r\n============================================");
					System.out.println("============================================\r\n" +  interfaceType +" �ýӿ�����" + counts + "�ε�ƽ��ʱ����"+ averageTestTime + "����\r\n============================================");
					buffer.append("============================================\r\n" +  interfaceType +" �ýӿ�����" + counts + "�ε�ƽ��ʱ����"+ averageTestTime + "����\r\n========================================================================================\n");
					String testDate2 = df.format(new Date());
					System.out.println("���Խ���ʱ��Ϊ��"+ testDate2);
	
			
					returnMsg[0] = outmsg;
					returnMsg[1] = datas;
					
					return returnMsg;
		
	}
}
