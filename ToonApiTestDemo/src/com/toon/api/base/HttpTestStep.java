package com.toon.api.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.toon.api.data.RunningData;
import com.toon.api.types.HttpMethodType;
import com.toon.api.types.TestStepType;
import com.toon.api.utils.Log;
import com.toon.api.utils.SendHttpClient;
import com.toon.api.utils.StringUtils;

public class HttpTestStep extends TestStep{
	
	public List<Object> responseInfos=null;
	
	//��������ģʽ��GET POST
	private String method;
		
	//http����ͷ��Ϣ
	private Map<String,String> headers=null;
	
	public HttpTestStep(TestCase testCase) {
		super(testCase);	
		this.testStepType=TestStepType.HTTPREQUEST;
	}
	
	private List<TestProperty> properties=null;
	
	public void setProperty(List<TestProperty> properties){
		this.properties=properties;
	}

	@Override
	public void run() {
		//super.run();
		if(StringUtils.IsNullorEmpty(url)){
			Log.logError(this.fatherCase.testCaseName,"�ӿ������ַΪ��");
			return ;
		}
		
		switch(this.method){			
			case HttpMethodType.POST:
				this.responseInfos=SendHttpClient.doHttpPost(this.url, this.headers, this.paramString);
				//this.responseInfos=HttpUtils.doPostFormReq(this.url, this.headers, this.paramMap,"utf-8");
				break;
			case HttpMethodType.GET:
				this.responseInfos=SendHttpClient.doHttpGet(this.url, this.headers, this.paramString);
				break;
			default:
				this.responseInfos=SendHttpClient.doHttpPost(this.url, this.headers, this.paramString);
				//this.responseInfos=HttpUtils.doPostFormReq(this.url, this.headers, this.paramMap,"utf-8");
				break;
		}
		
		if(responseInfos==null||responseInfos.get(1)==null||responseInfos.get(1).toString().trim().length()==0){
			this.fatherCase.flag=false;
			this.fatherCase.failLog.add("HTTP�����쳣,�����ݷ��أ���鿴���²����Ƿ���ȷ:");
			this.fatherCase.failLog.add("url:"+this.url);
			StringBuffer sb=new StringBuffer("");
			for(Object key:this.headers.keySet()){
				sb.append("["+key.toString()+":"+this.headers.get(key).toString()+"]");
			}
			this.fatherCase.failLog.add("headers:"+sb.toString());
			this.fatherCase.failLog.add(this.paramString);
			for(String log:this.fatherCase.failLog){
				Log.logInfo(log);
			}
			StringBuffer sbFailureLog=new StringBuffer("");
			for(String log:this.fatherCase.failLog){
				sbFailureLog.append(log+"\r\n");
			}
			Assert.fail(sbFailureLog.toString());
		}
		
		//ֻ�������˼����http�����¼����־��
		if(this.havaCheckPoint){
			RunningData.httpResult.put(this.fatherCase.testName, this);
		}
		
		//�ж��Ƿ���м��㣬������ڼ�����ִ�м���
		if(this.havaCheckPoint){
			for(CheckPoint checkPoint:this.checkPointList){
				checkPoint.check();
			}
			
			if(!this.fatherCase.flag){
				for(String log:this.fatherCase.failLog){
					Log.logInfo(log);
				}
				StringBuffer sbFailureLog=new StringBuffer("");
				for(String log:this.fatherCase.failLog){
					sbFailureLog.append(log+"\r\n");
				}
				Assert.fail(sbFailureLog.toString());
			}
		}
	}
	

	//�������󷽷�
	public void setMethod(String method){
		this.method=method.toUpperCase();
	}

	//��������ͷ��Ϣ
	public void setHeaders(String key,String value){
		if(headers==null){
			headers=new HashMap<String,String>();
		}
		headers.put(key, value);
	}
	
	//�ӿڵ�ַ
	private String url;
	//���������ַ
	public void setUrl(String url){
		this.url=url;
	}
	
	//������
	private String paramString;
	private Map<String,String> paramMap;
	//���ò�����
	public void setParam(String paramString){
		this.paramString=paramString;
		if(paramString.contains("=")){
			paramMap=new HashMap<String,String>();
			String[] params=paramString.split("=");
			paramMap.put(params[0], params[1]);
		}
	}
	
	public String getParamString(){
		return paramString;
	}
}
