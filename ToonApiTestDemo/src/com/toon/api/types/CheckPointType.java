package com.toon.api.types;

public interface CheckPointType {

	//是否包含字符串0
	public static int CONTAINS=1;
	//jsonpath存在的数量
	public static int JSONPATHCOUNT=2;
	//jsonpath值匹配
	public static int JSONPATHMATCH=3;
	//不包含字符串
	public static int NOTCONTAINS=4;
	//http请求状态
	public static int HTTPSTATUSCODE=5;
	//调用时长判断
	public static int RESPONSESLA=6;
	 
}
