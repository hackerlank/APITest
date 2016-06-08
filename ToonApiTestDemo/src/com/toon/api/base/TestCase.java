package com.toon.api.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.toon.api.data.RunningData;
import com.toon.api.utils.Log;

public class TestCase {
	
	//������������
	public String testCaseName;
	//���Զ������
	public String testCaseID;
	//������������test����
	public String testName;
	//�����־
	public boolean flag=true;	
	//ʧ����־
	public List<String> failLog=new ArrayList<String>();
	//ִ����־
	public List<String> runLog=new ArrayList<String>();
	//���Բ����б�
	private List<TestStep> testSteps=null;
	//ִ��ʱ��
	private String runTime;
	//��Ӳ��Բ���
	public void addTestStep(TestStep testStep){
		if(this.testSteps==null){
			this.testSteps=new LinkedList<TestStep>();
		}
		this.testSteps.add(testStep);
	}
	
	public List<TestStep> getTestSteps(){
		return testSteps;
	}
	
	//��ȡ����ִ��ʱ��
	public String getRunTime(){
		return this.runTime;
	}
	
	//ִ�в�������
	public void run(){
		this.runTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		if(this.testSteps!=null){
			for(TestStep testStep:testSteps){
				testStep.run();
			}
		}else{
			Log.logWarn(this.testCaseName, "δ���ò��Բ���");
		}
	}
	
	public String toString(){
		return "��������Id:"+this.testCaseID;
	}
	
	public void refreshParams(){
		for(TestStep testStep:testSteps){
			if(testStep instanceof HttpTestStep){
				String paramString=((HttpTestStep)testStep).getParamString();
				
				if(paramString.contains("${ticket}")){
					paramString=paramString.replace("${ticket}", RunningData.getProperty("ticket").toString());
				}
				if(paramString.contains("${token}")){
					paramString=paramString.replace("${token}", RunningData.getProperty("token").toString());
				}
				if(paramString.contains("${deviceId}")){
					paramString=paramString.replace("${deviceId}", RunningData.getProperty("deviceId").toString());
				}
				
				((HttpTestStep)testStep).setParam(paramString);
			}
		}
	}
}
