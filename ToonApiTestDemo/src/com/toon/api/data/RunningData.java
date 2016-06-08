package com.toon.api.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.toon.api.base.HttpTestStep;
import com.toon.api.base.TestCase;
import com.toon.api.utils.Log;

public class RunningData {

	//运行日志
	public static List<String> runLog=new ArrayList<String>();
		
	//http请求结果集
	public static Map<String,HttpTestStep> httpResult=new LinkedHashMap<String,HttpTestStep>();
	
	//执行的测试用例集合
	public static List<TestCase> caseList=new LinkedList<TestCase>();
	
	//全局变量
	private static Map<Object,Object> globalProperties=new HashMap<Object,Object>();
	
	//获取全局变量
	public static Object getProperty(Object key){
		if(globalProperties.keySet().contains(key)){
			return globalProperties.get(key);
		}else{
			Log.logInfo("全局变量中不存在["+key+"]的变量");
			return null;
		}
	}
	
	//设置全局变量
	public static void setProperty(Object key,Object value,boolean isCover){
		if(globalProperties.keySet().contains(key)){
			if(isCover){
				globalProperties.remove(key);
				globalProperties.put(key, value);
				Log.logInfo("全局变量["+key.toString()+"]的value值被替换为["+value.toString()+"]");
			}
		}else{
			globalProperties.put(key, value);
		}
	}
}
