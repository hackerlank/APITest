package com.toon.api.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Type {

	/**
	 * @param 类型名称，CheckPoint ,  HttpMethod , TestStep
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * **/
	public static List<Object> getTypes(String typeName) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		String className="com.toon.api.types.";
		List<Object> fieldValues=new ArrayList<Object>();
		switch(typeName){
			case "CheckPoint": className+="CheckPointType";break;
			case "HttpMethod": className+="HttpMethodType";break;
			case "TestStep": className+="TestStepType";break;
			default: return null;
		}
		@SuppressWarnings("rawtypes")
		Class ownerClass = Class.forName(className);
		Field[] fields = ownerClass.getFields();
		if(fields!=null){
			for(Field field:fields){
				fieldValues.add(field.get(ownerClass));
			}
		}
		return fieldValues;
	}
	
	/**判断是否包含类型
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws ClassNotFoundException **/
	public static boolean isContainsType(Object key,String typeName) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException{
		boolean isContains=false;
		List<Object> types=getTypes(typeName);
		for(Object object:types){
			if(key.toString().equals(object.toString())){
				isContains=true;
				break;
			}
		}
		return isContains;
	}
}
