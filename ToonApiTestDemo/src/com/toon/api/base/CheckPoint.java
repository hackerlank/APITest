package com.toon.api.base;

import com.toon.api.types.CheckPointType;
import com.toon.api.utils.Log;
import com.toon.api.utils.Type;
import com.toon.api.utils.XJsonPathOprator;

public class CheckPoint {
	
	private TestStep testStep;
	public CheckPoint(TestStep testStep){
		this.testStep=testStep;
	}
	
	//检查点类型
	private int checkPointType;
	//设置检查点类型
	public void setCheckPointType(int type) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		if(Type.isContainsType(type, "CheckPoint")){
			this.checkPointType=type;
		}else{
			Log.logError("case["+this.testStep.fatherCase.testCaseName+"] set the wrong check point type,please check");
		}
	}

	
	//期望值(当检查点类型为1时，直接赋值；当为sql时，期望值为设置的结果)
	private String expected;
	//设置期望字符串
	public void setExpected(String exString){
		this.expected=exString;
	}
	
	//检查点核对
	public void check(){
		switch(this.checkPointType){
			case CheckPointType.CONTAINS: this.checkContains();break;
			case CheckPointType.HTTPSTATUSCODE: this.checkHttpStatusCode();break;
			case CheckPointType.JSONPATHCOUNT: this.checkJsonPathCount();break;
			case CheckPointType.JSONPATHMATCH: this.checkJsonPathMatch();break;
			case CheckPointType.NOTCONTAINS: this.checkNotContains();break;
			case CheckPointType.RESPONSESLA: this.checkResponseSLA();break;
		}
	}
	
	//校验包含性检查点
	private void checkContains(){
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		if(!responseString.contains(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[CONTAINS] 目标报文中不包含字符串["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[CONTAINS] 目标报文中包含字符串["+this.expected+"]");
		}
//		assertResult.assertEquals(((HttpTestStep)this.testStep).responseInfos.get(1).toString().contains(this.expected),
//				true,"目标报文中不包含字符串["+this.expected+"]");
	}
	
	//校验Http请求状态
	private void checkHttpStatusCode(){
		String httpCode=((HttpTestStep)this.testStep).responseInfos.get(0).toString();
		if(!httpCode.equals(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[HTTPSTATUSCODE] Http状态码验证失败，实际请求状态为["+httpCode+"],期望请求状态为["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[HTTPSTATUSCODE] Http状态码验证通过");
		}
//		assertResult.assertEquals(((HttpTestStep)this.testStep).responseInfos.get(0).toString(),this.expected,
//				"http请求状态为["+((HttpTestStep)this.testStep).responseInfos.get(0).toString()+"],与期望状态["+this.expected+"]不符");
	}
	
	//校验jsonPath所对应节点数量
	private void checkJsonPathCount(){
		String[] jsonExpressionValue=this.expected.trim().split("=");
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		XJsonPathOprator xJsonPathOprator=new XJsonPathOprator(responseString);
		int count=0;
		try{
			count=xJsonPathOprator.getJsonCount(jsonExpressionValue[0]);
			if(!String.valueOf(count).equals(jsonExpressionValue[1])){
				this.testStep.fatherCase.flag=false;
				this.testStep.fatherCase.failLog.add("[JSONPATHCOUNT] jsonPath["+jsonExpressionValue[0]+"]对应节点数量与预期不符，实际节点数量为["+count+"],期望节点数量为["+jsonExpressionValue[1]+"]");
			}else{
				this.testStep.fatherCase.runLog.add("[JSONPATHCOUNT] jsonPath对应节点数量与期望一致");
			}
//			assertResult.assertEquals(String.valueOf(count), jsonExpressionValue[1],
//				"jsonPath["+jsonExpressionValue[0]+"]对应节点数量与预期不符，实际节点数量为["+count+"],期望节点数量为["+jsonExpressionValue[1]+"]");
		}catch(Exception ex){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("回传值不是正确的json格式,回传字符串为:"+responseString);
			//assertResult.assertEquals(true, false,"回传值不是正确的json格式,回传字符串为:"+responseString);
		}
	}
	
	//校验json节点值
	private void checkJsonPathMatch(){
		String[] jsonExpressionValue=this.expected.trim().split("=");
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		XJsonPathOprator xJsonPathOprator=new XJsonPathOprator(responseString);
		try{
			String value=xJsonPathOprator.getJsonValue(jsonExpressionValue[0]);
			if(!value.equals(jsonExpressionValue[1].trim())){
				this.testStep.fatherCase.flag=false;
				this.testStep.fatherCase.failLog.add("[JSONPATHMATCH] jsonPath["+jsonExpressionValue[0]+"]对应节点值与预期不符，实际节点值为["+value+"],期望节点值为["+jsonExpressionValue[1].trim()+"]");
			}else{
				this.testStep.fatherCase.runLog.add("[JSONPATHMATCH] jsonPath对应节点值与期望一致");
			}
//			assertResult.assertEquals(value, jsonExpressionValue[1].trim(),
//				"jsonPath["+jsonExpressionValue[0]+"]对应节点值与预期不符，实际节点值为["+value+"],期望节点值为["+jsonExpressionValue[1].trim()+"]");
		}catch(Exception ex){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("回传值不是正确的json格式,回传字符串为:"+responseString);
//			assertResult.assertEquals(true, false,"回传值不是正确的json格式,回传字符串为:"+responseString);
		}
	}
	
	//检查字符串不包含
	private void checkNotContains(){
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		if(responseString.contains(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[NOTCONTAINS] 目标报文中包含目标值["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[NOTCONTAINS] 目标报文中不包含目标值["+this.expected+"]");
		}
//		assertResult.assertEquals(!((HttpTestStep)this.testStep).responseInfos.get(1).toString().contains(this.expected),true
//				,"不包含检查点校验失败，目标报文中包含目标值["+this.expected+"]");
	}
	
	//检查接口请求时间
	private void checkResponseSLA(){
		int actual=Integer.parseInt(String.valueOf(((HttpTestStep)this.testStep).responseInfos.get(2)));
		if(actual>Integer.parseInt(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[RESPONSESLA] 接口请求时间超出预期，实际调用时间为["+actual+"ms],期望调用时间范围为["+this.expected+"ms]内");
		}else{
			this.testStep.fatherCase.runLog.add("[RESPONSESLA] 接口调用时间在期望内");
		}
//		assertResult.assertEquals(actual<=Integer.parseInt(this.expected), true
//				,"接口请求时间超出预期，实际调用时间为["+actual+"ms],期望调用时间范围为["+this.expected+"ms]内");
	}
}
