package com.toon.api.factory;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVRecord;
import com.toon.api.base.CheckPoint;
import com.toon.api.base.HttpTestStep;
import com.toon.api.base.TestCase;
import com.toon.api.base.TestStep;
import com.toon.api.base.TransforTestStep;
import com.toon.api.dbproviders.DBProvider;
import com.toon.api.sysinfo.BodyCreator;
import com.toon.api.types.CheckPointType;
import com.toon.api.types.TestStepType;
import com.toon.api.utils.CSVUtils;
import com.toon.api.utils.ParseXml;

public class CaseFactoryDemo {

	/**�������������б�
	 * @param filePath ���������ļ�·��
	 * @throws Exception **/
	public static void createTestCases(String filePath,String header) throws Exception{
		String testName=filePath.substring(filePath.lastIndexOf("/")+1,filePath.lastIndexOf("."));
		String[] headers=header.split(",");
		List<CSVRecord> records=CSVUtils.readCSV(filePath, headers, true);
		List<String> caseIds=getCaseIdList(records);
		if(caseIds!=null&&caseIds.size()>0){
			int size=caseIds.size();
			Object[][] cases=new Object[size][1];
			for(int i=0;i<size;i++){
				TestCase testCase=createTestCase(records,caseIds.get(i),testName);
				cases[i][0]=testCase;
			}
			DBProvider.cases=cases;
		}	
	}

	/**��ȡ���Բ����б�**/
	public static List<String> getCaseIdList(List<CSVRecord> records){
		List<String> caseIds=new ArrayList<String>();
		int size=records.size();
		for(int i=0;i<size;i++){
			String caseId=records.get(i).get("caseId");
			if(caseIds.size()==0||!caseIds.contains(caseId)){
				caseIds.add(caseId);
			}
		}
		return caseIds;
	}
		
	/**����������������**/
	public static TestCase createTestCase(List<CSVRecord> records, String caseId,String testName) {
		TestCase testCase=new TestCase();
		testCase.testName=testName;
		testCase.testCaseID=caseId;
		for(CSVRecord record:records){
			if(record.get("caseId").equals(caseId)){
				testCase.testCaseName=record.get("title");
				break;
			}
		}		
		for(CSVRecord record:records){
			if(record.get("caseId").equals(caseId)){
				testCase.addTestStep(createTestStep(record,testCase));
			}
		}
		return testCase;
	}

	/**�������Բ���**/
	public static TestStep createTestStep(CSVRecord record, TestCase testCase) {
		TestStep testStep=null;
		int testStepType=Integer.parseInt(record.get("testStepType"));
		switch(testStepType){
			case TestStepType.HTTPREQUEST:
				testStep=createHttpTestStep(record,testCase);
				break;
			case TestStepType.DATATRANSFOR:
				testStep=createDataTransforTestStep(record,testCase);
				break;
		}
		return testStep;
	}

	/**����HttpTestStep**/
	private static HttpTestStep createHttpTestStep(CSVRecord record, TestCase testCase) {
		HttpTestStep httpTestStep=null;
		//��������case
		httpTestStep=new HttpTestStep(testCase);
		//���õ��÷���
		httpTestStep.setMethod(record.get("method"));
		//���ýӿڵ�ַ
		httpTestStep.setUrl(record.get("url"));
		//���õ��ò���
		httpTestStep.setParam(BodyCreator.createRequestBody(record.get("param")));
		//����headers
		String headerString=record.get("headers").trim();
		if(!"".equals(headerString)){
			String[] headers=headerString.split(",");
			for(String header:headers){
				String[] h=header.split(":");
				httpTestStep.setHeaders(h[0], h[1]);
			}
		}
		//���ü���
		String checkString=record.get("checkPoints").trim();
		if(!"".equals(checkString)){
			String[] checks=checkString.split(";");
			for(String check:checks){
				CheckPoint checkPoint=createCheckPoint(check,httpTestStep);
				httpTestStep.addCheckPoint(checkPoint);
			}	
		}
		return httpTestStep;
	}

	/**��������**/
	private static CheckPoint createCheckPoint(String check, HttpTestStep httpTestStep) {
		CheckPoint checkPoint=new CheckPoint(httpTestStep);
		String[] checkPointInfo=check.split(":");
		try{
			switch(checkPointInfo[0]){
				case "CONTAINS":
					checkPoint.setCheckPointType(CheckPointType.CONTAINS); 				
					break;
				case "JSONPATHCOUNT":
					checkPoint.setCheckPointType(CheckPointType.JSONPATHCOUNT); 
					break;
				case "JSONPATHMATCH":
					checkPoint.setCheckPointType(CheckPointType.JSONPATHMATCH); 
					break;
				case "NOTCONTAINS":
					checkPoint.setCheckPointType(CheckPointType.NOTCONTAINS); 
					break;
				case "HTTPSTATUSCODE":
					checkPoint.setCheckPointType(CheckPointType.HTTPSTATUSCODE); 
					break;
				case "RESPONSESLA":
					checkPoint.setCheckPointType(CheckPointType.RESPONSESLA); 
					break;
			}
			checkPoint.setExpected(checkPointInfo[1]);
			return checkPoint;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}

	/**����TransforTestStep**/
	private static TransforTestStep createDataTransforTestStep(CSVRecord record, TestCase testCase) {
		// TODO Auto-generated method stub
		return null;
	}

	/**������¼
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException **/
	public static TestCase createLoginTestCase() throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception {
		ParseXml pxConfig=new ParseXml("config.xml");
		String phone=pxConfig.getElementText("config/toon/account/phone");
		String password=pxConfig.getElementText("config/toon/account/password");
		String url=pxConfig.getElementText("config/toon/account/url");
		String loginJson="toonKey={\"authKey\":{\"appVersion\":\"3.1.4\",\"deviceId\":\"${deviceId}\",\"platform\":\"android\",\"platformVersion\":\"19\",\"userId\":\"2109\"},\"bizKey\":\"{\\\"channel\\\":\\\"2000\\\",\\\"deviceName\\\":\\\"MI 3\\\",\\\"deviceToken\\\":\\\"${deviceId}\\\",\\\"imei\\\":\\\"${deviceId}\\\",\\\"loginType\\\":\\\"1\\\",\\\"mobile\\\":\\\"${phone}\\\",\\\"password\\\":\\\"${password}\\\",\\\"teleCode\\\":\\\"0086\\\",\\\"uuid\\\":\\\"${deviceId}\\\"}\"}";
		loginJson=loginJson.replace("${phone}", phone).replace("${password}", password);
		TestCase login=new TestCase();
		login.testName="�û��ӿ�";
		login.testCaseID="�û���¼";
		login.testCaseName="�û���¼";
		HttpTestStep httpTestStep=null;
		//��������case
		httpTestStep=new HttpTestStep(login);
		httpTestStep.setMethod("POST");
		httpTestStep.setParam(loginJson);
		httpTestStep.setUrl(url);
		String headerString="Content-Type:application/x-www-form-urlencoded;charset=utf-8";
		if(!"".equals(headerString)){
			String[] headers=headerString.split(",");
			for(String header:headers){
				String[] h=header.split(":");
				httpTestStep.setHeaders(h[0], h[1]);
			}
		}
		CheckPoint checkPoint=new CheckPoint(httpTestStep);
		checkPoint.setCheckPointType(CheckPointType.JSONPATHMATCH);
		checkPoint.setExpected("$.data.returnMsg=��¼�ɹ�");
		httpTestStep.addCheckPoint(checkPoint);
		login.addTestStep(httpTestStep);
		return login;
	}
}
