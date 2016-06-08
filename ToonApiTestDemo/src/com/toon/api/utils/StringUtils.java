package com.toon.api.utils;

public class StringUtils {

	//判断对象是否为空或者空字符串
	public static boolean IsNullorEmpty(String obj){
		if(obj==null || obj==""){
			return true;
		}else{
			return false;
		}
	}
	
	//判断对象是否为空
	public static boolean IsNull(String obj){
		if(obj==null){
			return true;
		}else{
			return false;
		}
	}
}
