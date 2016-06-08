package com.toon.api.utils;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class XJsonPathOprator {

	private String jsonString;
	
	public XJsonPathOprator(String jsonString){
		this.jsonString=jsonString;
	}
	
	/** 根据xjsonpath获取json节点值
	 * @param jsonPathExpression xjsonpath表达式
	 * @return 节点值 
	 */
	public String getJsonValue(String jsonPathExpression){
		return JsonPath.read(this.jsonString, jsonPathExpression).toString();
	}
	
	/** 根据xjsonpath获取json节点值
	 * @param jsonPathExpression xjsonpath表达式
	 * @return 节点数量 
	 */
	public int getJsonCount(String jsonPathExpression){
		int count=0;
		List<Object> objs=JsonPath.read(this.jsonString, jsonPathExpression);
		if(objs!=null)
			count=objs.size();
		return count;
	}
}
