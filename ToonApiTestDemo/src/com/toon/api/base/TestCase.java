package com.toon.api.base;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.toon.api.data.RunningData;
import com.toon.api.utils.Log;

public class TestCase {
	
	//测试用例名称
	public String testCaseName;
	//测试动力编号
	public String testCaseID;
	//测试用例所属test名称
	public String testName;
	//结果标志
	public boolean flag=true;	
	//失败日志
	public List<String> failLog=new ArrayList<String>();
	//执行日志
	public List<String> runLog=new ArrayList<String>();
	//测试步骤列表
	private List<TestStep> testSteps=null;
	//执行时间
	private String runTime;
	//添加测试步骤
	public void addTestStep(TestStep testStep){
		if(this.testSteps==null){
			this.testSteps=new LinkedList<TestStep>();
		}
		this.testSteps.add(testStep);
	}
	
	public List<TestStep> getTestSteps(){
		return testSteps;
	}
	
	//获取用例执行时间
	public String getRunTime(){
		return this.runTime;
	}
	
	//执行测试用例
	public void run(){
		this.runTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		if(this.testSteps!=null){
			for(TestStep testStep:testSteps){
				testStep.run();
			}
		}else{
			Log.logWarn(this.testCaseName, "未设置测试步骤");
		}
	}
	
	public String toString(){
		return "测试用例Id:"+this.testCaseID;
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
