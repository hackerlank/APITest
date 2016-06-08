package com.toon.api.factory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.toon.api.base.CheckPoint;
import com.toon.api.base.HttpTestStep;
import com.toon.api.base.TestCase;
import com.toon.api.base.TestStep;
import com.toon.api.base.TransforTestStep;
import com.toon.api.dbproviders.DBProvider;
import com.toon.api.types.CheckPointType;
import com.toon.api.types.TestStepType;
import com.toon.api.utils.CsvUtil;
import com.toon.api.utils.Log;

public class CaseFactory {

	/**��ȡ���в������� 
	 *@return ���������б�**/
	public static void createTestCases(String fileName){
		File file=new File(fileName);
		String testName=file.getName();
		//�Բ��������ļ�����Ϊtest������
		testName=testName.substring(0,testName.lastIndexOf("."));
		try {
			Object[][] allCases=CsvUtil.getCsvData(fileName, true);
			Set<String> caseIdList=new HashSet<String>();
			int total=allCases.length;
			for(int i=0;i<total;i++){
				//System.out.println(allCases[i][1].toString());
				caseIdList.add(allCases[i][1].toString());
			}
			int i=0;
			int count=caseIdList.size();
			DBProvider.cases=new Object[count][2];
			//����case.������������
			for(String caseId : caseIdList){
				DBProvider.cases[i][0]=getCaseInfoByCaseID(allCases,caseId,testName);
				//cases.add(testCase);
				DBProvider.cases[i][1]=testName;
				i++;
			}
			
		} catch (Exception e) {
			Log.logError("��ȡ����csv����ʧ�ܣ���鿴�ļ���ʧ����Ϣ��"+e.getMessage());
		}
		//return cases;
	}
	
	/**������������
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException **/
	public static TestCase createTestCase(List<Object[]> caseInfo) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		TestCase testCase =new TestCase();		
		if(caseInfo!=null&&caseInfo.size()>0){
			int size=caseInfo.size();
			for(int i=0;i<size;i++){
				Object[] obj=caseInfo.get(i);
				TestStep testStep = null;
				switch(Integer.parseInt(obj[6].toString())){
					case TestStepType.HTTPREQUEST :
						testStep=createHttpTestStep(obj,testCase);break;
					case TestStepType.DATATRANSFOR:
						testStep=createTransforTestStep(obj,testCase);break;
				}
				testCase.addTestStep(testStep);
			}
		}
		return testCase;
	}
	
	/**����caseID��ȡcase��Ϣ
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException **/
	public static TestCase getCaseInfoByCaseID(Object[][] source,String caseId,String testName) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		//TestCase testCase =new TestCase();			
		int count=source.length;
		List<Object[]> list=new ArrayList<Object[]>();
		for(int i=0;i<count;i++){
			if(source[i][1].toString().equals(caseId)){
				list.add(source[i]);
			}
		}
		TestCase testCase=createTestCase(list);
		testCase.testCaseID=caseId;
		testCase.testCaseName="";
		testCase.testName=testName;
		return testCase;
	}
	
	/** ��ȡcsv�е�case��������
	 * @param filePath ���������ļ�  
	 * @throws Exception **/
//	public static void createTestCases(String filePath) throws Exception{
////		DBProvider.cases=new Object[5][2];
////		for(int i=0;i<5;i++){
////			TestCase testCase=new TestCase();
////			testCase.testName=filePath;
////			DBProvider.cases[i][0]=testCase;
////			DBProvider.cases[i][1]=i;
////		}
//		Object[][] lines=CsvUtil.getCsvData(filePath, true);
//		
//	}
	
	/**����HttpTestStep
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException **/
	public static HttpTestStep createHttpTestStep(Object[] obj,TestCase testCase) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		HttpTestStep httpTestStep=null;
		//��������case
		httpTestStep=new HttpTestStep(testCase);
		//���õ��÷���
		httpTestStep.setMethod(obj[4].toString());
		//���ýӿڵ�ַ
		httpTestStep.setUrl(obj[5].toString());
		//���õ��ò���
		httpTestStep.setParam(obj[14].toString());
		//����headers
		String headerString=obj[8].toString().trim();
		String[] headers=headerString.split(";");
		for(String header:headers){
			String[] h=header.split(":");
			httpTestStep.setHeaders(h[0], h[1]);
		}
		//���ü���
		String checkString=obj[16].toString().trim();
		String[] checks=checkString.split(";");
		for(String check:checks){
			CheckPoint checkPoint=createCheckPoint(check,httpTestStep);
			httpTestStep.addCheckPoint(checkPoint);
		}
		
		return httpTestStep;
	}
	
	/**����TransforTestStep**/
	public static TransforTestStep createTransforTestStep(Object[] obj,TestCase testCase){
		TransforTestStep transforTestStep=null;
		
		return transforTestStep;
	}
	
	/**�������㣬���ü�������
	 * @param check �����ַ���:JSONPATHMATCH:$.ReturnCode=0
	 * @param testStep ������������
	 * @return �������**/
	public static CheckPoint createCheckPoint(String check,TestStep testStep) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		CheckPoint checkPoint=new CheckPoint(testStep);
		String[] checkPointInfo=check.split(":");
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
	}
}
