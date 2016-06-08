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

	//������־
	public static List<String> runLog=new ArrayList<String>();
		
	//http��������
	public static Map<String,HttpTestStep> httpResult=new LinkedHashMap<String,HttpTestStep>();
	
	//ִ�еĲ�����������
	public static List<TestCase> caseList=new LinkedList<TestCase>();
	
	//ȫ�ֱ���
	private static Map<Object,Object> globalProperties=new HashMap<Object,Object>();
	
	//��ȡȫ�ֱ���
	public static Object getProperty(Object key){
		if(globalProperties.keySet().contains(key)){
			return globalProperties.get(key);
		}else{
			Log.logInfo("ȫ�ֱ����в�����["+key+"]�ı���");
			return null;
		}
	}
	
	//����ȫ�ֱ���
	public static void setProperty(Object key,Object value,boolean isCover){
		if(globalProperties.keySet().contains(key)){
			if(isCover){
				globalProperties.remove(key);
				globalProperties.put(key, value);
				Log.logInfo("ȫ�ֱ���["+key.toString()+"]��valueֵ���滻Ϊ["+value.toString()+"]");
			}
		}else{
			globalProperties.put(key, value);
		}
	}
}
