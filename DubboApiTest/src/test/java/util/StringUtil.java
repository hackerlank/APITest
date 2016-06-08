package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.testng.Assert;

public class StringUtil {

	/**
	 * @param args
	 */
	
	
	
	public static int strCount(String srcString, String target) {
		int count = 0;  
	    int index = 0;   
	    while(true){  
	    	index = srcString.indexOf(target); 
//	    	System.out.println(index);
	    	if(index>0){
	    		count++;
	    		srcString = srcString.substring(index+1);
//	    		System.out.println(srcString);
	    	}else{
	    		break;
	    	}
	    }
	   	
	   return count; 
	}
	
	/*
	 * 版本号3.0.0 转换成double 3.00模式
	 */
	
	public static String versionParse(String version){
		int pos1 = 0;
		String versionFirst="";
		String versionSecond= "";
		if(strCount(version,".")>1){
			 pos1 = version.indexOf(".");
			 versionFirst = version.substring(0, pos1+1);
			 versionSecond = version.substring(pos1+1).trim();
			 versionSecond = versionSecond.replaceAll("\\.", "");
			 version = versionFirst + versionSecond;
		}
		
		return version;
	}
	
	/**
	 * 通过正则表达式来assert
	 * @param input 待查找的字符
	 * @param patternString 正则表达式pattern
	 * @param info 额外信息
	 */
	
	public static void AssertByRegEx(String input, String patternString, String info) {
		patternString = regEx2String(patternString);
		Pattern pattern = Pattern.compile(patternString,Pattern.DOTALL);   //在 dotall 模式中，表达式 . 可以匹配任何字符，包括行结束符。默认情况下，此表达式不匹配行结束符。 
		Matcher matcher = pattern.matcher(input);
		boolean find = matcher.find();
		Assert.assertEquals(find, true, "接口请求为：" + info + "\n接口返回为：" + input+" \n接口预期为：" + patternString);
	}
	
	public static boolean findStrByRegEx(String input, String patternString){
//		patternString = regEx2String(patternString);
		Pattern pattern = Pattern.compile(patternString,Pattern.DOTALL);   //在 dotall 模式中，表达式 . 可以匹配任何字符，包括行结束符。默认情况下，此表达式不匹配行结束符。 
		Matcher matcher = pattern.matcher(input);
		boolean find = matcher.find();
		
		return find;
	}
	/**
	 * 从url得到服务器地址
	 * @param url  http://172.28.6.111:8080/user/userLogin
	 * @return 172.28.6.111:8080
	 */
	public static String[] getHostByUrl(String url){
		int posStart= url.indexOf("//");
		int posEnd = url.indexOf("/",posStart+2);
		String host = url.substring(posStart+2, posEnd);
		String[] hostInfo = host.split(":");
		return hostInfo;
		
	}
	
	public static String regEx2String(String regEx){
		String commonString = regEx.replace("{", "\\{").replace("}", "\\}").replace("[", "\\[").replace("]", "\\]").replace("?", "\\?");
		return commonString;
		
	}
	
	/**
	 * 将字符串数组转换成Long数组
	 * @param array
	 * @return
	 */
	public static Long[] stringArray2LongArray(String[] array){
		Long[] longArray = new Long[array.length];//首先进行数组长度的定义
		for (int i = 0; i < array.length; i++) { //把值赋予数组
			longArray[i] = Long.parseLong(array[i]) ;
		}
		return longArray;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		String s1 = "{\"result\":\"success\",\"data\":{\"status\":\"1\",\"userId\":\"535\",\"userName\":\"13581535754\",\"token\":\"0a118d4c-f752-45b0-8d1c-e24cde5040be\",\"ticket\":\"8C880BEBA8D14AA270C5F9F6F9DB19BD\",\"buildCard\":\"1\",\"returnMsg\":\"登录成功\",\"userInfo\":{\"userId\":\"535\",\"password\":\"15E9A34A5892EFA82857093F6B6A60D3\",\"teleCode\":\"86\",\"mobilePhone\":\"13581535754\",\"status\":\"1\",\"loginProtectStatus\":\"1\"},\"loginErrorTimes\":\"3\",\"userTopic\":\"s_535\"},\"code\":\"0\"}";
		
		System.out.println(findStrByRegEx(s1,""));
		
		String s2 = "https://172.28.6.111:8080/user/userLogin";
		String host[] = getHostByUrl(s2);
		System.out.println("-----" + host[0] + "----" + host[1]);

	}
}
