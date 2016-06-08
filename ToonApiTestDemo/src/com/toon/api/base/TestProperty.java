package com.toon.api.base;

public class TestProperty {

	private String propertyType;
	
	private String key;
	
	private String value;
	
	public void setPropertyType(String propertyType){
		this.propertyType=propertyType;
	}
	
	public String getPropertyType(){
		return propertyType;
	}
	
	public void setPropertyKey(String key){
		this.key=key;
	}
	
	public String getPropertyKey(){
		return key;
	}
	
	public void setPropertyValue(String value){
		this.value=value;
	}
	
	public String getPropertyValue(){
		return value;
	}
}
