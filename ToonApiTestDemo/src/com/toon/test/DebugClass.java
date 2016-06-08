package com.toon.test;

public class DebugClass {

	public static void main(String[] args){
		//zi z=new zi();
		String loginJson="{\"authKey\":{\"appVersion\":\"3.1.4\",\"deviceId\":\"865056026796108\",\"platform\":\"android\",\"platformVersion\":\"19\",\"userId\":\"2109\"},\"bizKey\":\"{\\\"channel\\\":\\\"2000\\\",\\\"deviceName\\\":\\\"MI 3\\\",\\\"deviceToken\\\":\\\"865056026796108\\\",\\\"imei\\\":\\\"865056026796108\\\",\\\"loginType\\\":\\\"1\\\",\\\"mobile\\\":\\\"13911204295\\\",\\\"password\\\":\\\"Fiona19870907\\\",\\\"teleCode\\\":\\\"0086\\\",\\\"uuid\\\":\\\"865056026796108\\\"}\"}";
		
		System.out.println(loginJson);
	}
}

class fu{
	static{
		System.out.println("++++");
	}
	public static int value=6;
}

class zi extends fu{
	static{
		System.out.println("----");
	}
}

