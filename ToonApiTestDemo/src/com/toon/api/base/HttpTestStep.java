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
	
	//设置请求模式，GET POST
	private String method;
		
	//http请求头信息
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
			Log.logError(this.fatherCase.testCaseName,"接口请求地址为空");
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
			this.fatherCase.failLog.add("HTTP请求异常,无数据返回，请查看以下参数是否正确:");
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
		
		//只将设置了检查点的http请求记录到日志中
		if(this.havaCheckPoint){
			RunningData.httpResult.put(this.fatherCase.testName, this);
		}
		
		//判断是否具有检查点，如果存在检查点则执行检查点
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
	

	//设置请求方法
	public void setMethod(String method){
		this.method=method.toUpperCase();
	}

	//设置请求头信息
	public void setHeaders(String key,String value){
		if(headers==null){
			headers=new HashMap<String,String>();
		}
		headers.put(key, value);
	}
	
	//接口地址
	private String url;
	//设置请求地址
	public void setUrl(String url){
		this.url=url;
	}
	
	//参数串
	private String paramString;
	private Map<String,String> paramMap;
	//设置参数串
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
