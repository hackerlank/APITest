package com.toon.api.utils;

public class StringUtils {

	//�ж϶����Ƿ�Ϊ�ջ��߿��ַ���
	public static boolean IsNullorEmpty(String obj){
		if(obj==null || obj==""){
			return true;
		}else{
			return false;
		}
	}
	
	//�ж϶����Ƿ�Ϊ��
	public static boolean IsNull(String obj){
		if(obj==null){
			return true;
		}else{
			return false;
		}
	}
}
