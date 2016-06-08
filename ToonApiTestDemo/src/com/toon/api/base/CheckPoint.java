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
	
	//��������
	private int checkPointType;
	//���ü�������
	public void setCheckPointType(int type) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, Exception{
		if(Type.isContainsType(type, "CheckPoint")){
			this.checkPointType=type;
		}else{
			Log.logError("case["+this.testStep.fatherCase.testCaseName+"] set the wrong check point type,please check");
		}
	}

	
	//����ֵ(����������Ϊ1ʱ��ֱ�Ӹ�ֵ����Ϊsqlʱ������ֵΪ���õĽ��)
	private String expected;
	//���������ַ���
	public void setExpected(String exString){
		this.expected=exString;
	}
	
	//����˶�
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
	
	//У������Լ���
	private void checkContains(){
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		if(!responseString.contains(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[CONTAINS] Ŀ�걨���в������ַ���["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[CONTAINS] Ŀ�걨���а����ַ���["+this.expected+"]");
		}
//		assertResult.assertEquals(((HttpTestStep)this.testStep).responseInfos.get(1).toString().contains(this.expected),
//				true,"Ŀ�걨���в������ַ���["+this.expected+"]");
	}
	
	//У��Http����״̬
	private void checkHttpStatusCode(){
		String httpCode=((HttpTestStep)this.testStep).responseInfos.get(0).toString();
		if(!httpCode.equals(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[HTTPSTATUSCODE] Http״̬����֤ʧ�ܣ�ʵ������״̬Ϊ["+httpCode+"],��������״̬Ϊ["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[HTTPSTATUSCODE] Http״̬����֤ͨ��");
		}
//		assertResult.assertEquals(((HttpTestStep)this.testStep).responseInfos.get(0).toString(),this.expected,
//				"http����״̬Ϊ["+((HttpTestStep)this.testStep).responseInfos.get(0).toString()+"],������״̬["+this.expected+"]����");
	}
	
	//У��jsonPath����Ӧ�ڵ�����
	private void checkJsonPathCount(){
		String[] jsonExpressionValue=this.expected.trim().split("=");
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		XJsonPathOprator xJsonPathOprator=new XJsonPathOprator(responseString);
		int count=0;
		try{
			count=xJsonPathOprator.getJsonCount(jsonExpressionValue[0]);
			if(!String.valueOf(count).equals(jsonExpressionValue[1])){
				this.testStep.fatherCase.flag=false;
				this.testStep.fatherCase.failLog.add("[JSONPATHCOUNT] jsonPath["+jsonExpressionValue[0]+"]��Ӧ�ڵ�������Ԥ�ڲ�����ʵ�ʽڵ�����Ϊ["+count+"],�����ڵ�����Ϊ["+jsonExpressionValue[1]+"]");
			}else{
				this.testStep.fatherCase.runLog.add("[JSONPATHCOUNT] jsonPath��Ӧ�ڵ�����������һ��");
			}
//			assertResult.assertEquals(String.valueOf(count), jsonExpressionValue[1],
//				"jsonPath["+jsonExpressionValue[0]+"]��Ӧ�ڵ�������Ԥ�ڲ�����ʵ�ʽڵ�����Ϊ["+count+"],�����ڵ�����Ϊ["+jsonExpressionValue[1]+"]");
		}catch(Exception ex){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("�ش�ֵ������ȷ��json��ʽ,�ش��ַ���Ϊ:"+responseString);
			//assertResult.assertEquals(true, false,"�ش�ֵ������ȷ��json��ʽ,�ش��ַ���Ϊ:"+responseString);
		}
	}
	
	//У��json�ڵ�ֵ
	private void checkJsonPathMatch(){
		String[] jsonExpressionValue=this.expected.trim().split("=");
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		XJsonPathOprator xJsonPathOprator=new XJsonPathOprator(responseString);
		try{
			String value=xJsonPathOprator.getJsonValue(jsonExpressionValue[0]);
			if(!value.equals(jsonExpressionValue[1].trim())){
				this.testStep.fatherCase.flag=false;
				this.testStep.fatherCase.failLog.add("[JSONPATHMATCH] jsonPath["+jsonExpressionValue[0]+"]��Ӧ�ڵ�ֵ��Ԥ�ڲ�����ʵ�ʽڵ�ֵΪ["+value+"],�����ڵ�ֵΪ["+jsonExpressionValue[1].trim()+"]");
			}else{
				this.testStep.fatherCase.runLog.add("[JSONPATHMATCH] jsonPath��Ӧ�ڵ�ֵ������һ��");
			}
//			assertResult.assertEquals(value, jsonExpressionValue[1].trim(),
//				"jsonPath["+jsonExpressionValue[0]+"]��Ӧ�ڵ�ֵ��Ԥ�ڲ�����ʵ�ʽڵ�ֵΪ["+value+"],�����ڵ�ֵΪ["+jsonExpressionValue[1].trim()+"]");
		}catch(Exception ex){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("�ش�ֵ������ȷ��json��ʽ,�ش��ַ���Ϊ:"+responseString);
//			assertResult.assertEquals(true, false,"�ش�ֵ������ȷ��json��ʽ,�ش��ַ���Ϊ:"+responseString);
		}
	}
	
	//����ַ���������
	private void checkNotContains(){
		String responseString=((HttpTestStep)this.testStep).responseInfos.get(1).toString();
		if(responseString.contains(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[NOTCONTAINS] Ŀ�걨���а���Ŀ��ֵ["+this.expected+"]");
		}else{
			this.testStep.fatherCase.runLog.add("[NOTCONTAINS] Ŀ�걨���в�����Ŀ��ֵ["+this.expected+"]");
		}
//		assertResult.assertEquals(!((HttpTestStep)this.testStep).responseInfos.get(1).toString().contains(this.expected),true
//				,"����������У��ʧ�ܣ�Ŀ�걨���а���Ŀ��ֵ["+this.expected+"]");
	}
	
	//���ӿ�����ʱ��
	private void checkResponseSLA(){
		int actual=Integer.parseInt(String.valueOf(((HttpTestStep)this.testStep).responseInfos.get(2)));
		if(actual>Integer.parseInt(this.expected)){
			this.testStep.fatherCase.flag=false;
			this.testStep.fatherCase.failLog.add("[RESPONSESLA] �ӿ�����ʱ�䳬��Ԥ�ڣ�ʵ�ʵ���ʱ��Ϊ["+actual+"ms],��������ʱ�䷶ΧΪ["+this.expected+"ms]��");
		}else{
			this.testStep.fatherCase.runLog.add("[RESPONSESLA] �ӿڵ���ʱ����������");
		}
//		assertResult.assertEquals(actual<=Integer.parseInt(this.expected), true
//				,"�ӿ�����ʱ�䳬��Ԥ�ڣ�ʵ�ʵ���ʱ��Ϊ["+actual+"ms],��������ʱ�䷶ΧΪ["+this.expected+"ms]��");
	}
}
