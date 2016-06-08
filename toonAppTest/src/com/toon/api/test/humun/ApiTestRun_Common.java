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
 * toon�ֻ��ӿ� 
 * @author haoyuexun
 * @date   2015.8.4
 */

public class ApiTestRun_Common {

	
	private static Log log = LogFactory.getLog(ApiTestRun_Common.class); 
	static int counts = 1 ;  	// ���Դ���	
	static FileResourceUtil fru = new FileResourceUtil("config.properties");
	static String resultFile = "";
	
	
	
	
	
	public static void main(String[] args)  {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
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
						
						System.out.println("��������������������ȷ��");
						buffer.append("��������������������ȷ��");
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
					String expContent = inouts[11].trim().split("\t")[0];   //��ֹ�����ע��Ӱ���жϽ��

					
					if(interfaceType.equals("")){		//�ų��ǲ������������{
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
					
					System.out.println("���Ե�ַ��"+ testURL);
					buffer.append("���Ե�ַ��"+ testURL + "\r\n");
					
					System.out.println("������ţ�" + caseId + "  ����������"+ caseName + "  Ԥ�ڽ��������" + expContent);
					buffer.append("������ţ�" + caseId + "  ����������"+ caseName+ "  Ԥ�ڽ��������" + expContent +"\r\n");
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
						
						String expContentPattern = "";
						if(!expContent.equalsIgnoreCase("")){
//							expContentPattern = ".*" + expContent + ".*" ;
							expContentPattern = expContent;
						}else{
							expContentPattern = "��case��Ԥ������";
						}
						boolean testResult = util.StringUtil.findStrByRegEx(outmsg, expContentPattern);
						
						System.out.println(outmsg + "\n"+ expContentPattern);
						if(testResult){
							System.out.println("============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ��,����-->" + expContent );
							buffer.append("\n============================================\r\nTrue!   Ԥ�ں�ʵ�ʷ���һ�£�����--> " + expContent + "\r\n");
						}else{
							System.out.println("============================================\r\nFail!������ţ�" + caseId + "   Ԥ�ں�ʵ�ʷ����벻һ�£�   Ԥ�ڷ��أ�" + expContent );
							buffer.append("\n============================================\r\nFail!������ţ�" + caseId + "   Ԥ�ں�ʵ�ʷ����벻һ�£�  Ԥ�ڷ��أ�" + expContent  +"\r\n");
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
				}	//���ܲ���ʹ��
			}	
			
			System.out.println("������������Ϊ��" + numTestcase);
			buffer.append("������������Ϊ��" + numTestcase + "\n");
		} catch (Exception e) {
			
			System.out.println("An error occurred during file reading " + e);
			
		} finally {
			bin.close();
		}
			
		return buffer.toString();
	}
}
