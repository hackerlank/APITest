package com.toon.api.utils;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class XJsonPathOprator {

	private String jsonString;
	
	public XJsonPathOprator(String jsonString){
		this.jsonString=jsonString;
	}
	
	/** ����xjsonpath��ȡjson�ڵ�ֵ
	 * @param jsonPathExpression xjsonpath���ʽ
	 * @return �ڵ�ֵ 
	 */
	public String getJsonValue(String jsonPathExpression){
		return JsonPath.read(this.jsonString, jsonPathExpression).toString();
	}
	
	/** ����xjsonpath��ȡjson�ڵ�ֵ
	 * @param jsonPathExpression xjsonpath���ʽ
	 * @return �ڵ����� 
	 */
	public int getJsonCount(String jsonPathExpression){
		int count=0;
		List<Object> objs=JsonPath.read(this.jsonString, jsonPathExpression);
		if(objs!=null)
			count=objs.size();
		return count;
	}
}
