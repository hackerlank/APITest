package com.toon.api.utils;


/**
 * @Package com.aoyou.test.util
 * @ClassName Config
 * @Description 全局配置，值持久化模块.在每次build前解析config.xml,加载配置信息.防止在测试运行中修改配置产生的影响
 * 
 *  browser:本次运行启动的浏览器类型(chrome/firefox/ie)
 *  browserWaitTime:浏览器加载的最长等待时间，单位为s
 *  objectWaitTime：对象查找的最长等待时间，单位为s
 *  pageWaitTime：页面加载完成的最长等待时间，单位为s
 *  siteName：本次运行的默认出发站点
 *  objectRespository：本次运行依赖的页面对象库路径
 * 
 */

public class Config {
	
	public static int retryTimes;
	public static String SMTPserver;
	public static String from;
	public static String username;
	public static String password ;
	public static String to;
	public static String copyTo;
	public static String filename;
	public static String subject;
	public static String reportAddress;
	public static String projectName;
	public static boolean isSendEmail;
	public static String jenkinsReportPath;
	
	static{		
		ParseXml pxConfig=new ParseXml("config.xml");
		retryTimes=Integer.valueOf(pxConfig.getElementText("/config/retryTimes"));
		SMTPserver=pxConfig.getElementText("/config/mail/SMTPserver");
		from=pxConfig.getElementText("/config/mail/from");
		username=pxConfig.getElementText("/config/mail/username");
		password=pxConfig.getElementText("/config/mail/password");
		to=pxConfig.getElementText("/config/mail/to");
		copyTo=pxConfig.getElementText("/config/mail/copyTo");
		filename=pxConfig.getElementText("/config/mail/filename");
		subject=pxConfig.getElementText("/config/mail/subject"); 
		reportAddress=pxConfig.getElementText("/config/mail/reportAddress"); 
		projectName=pxConfig.getElementText("/config/mail/projectName"); 
		String isSend=pxConfig.getElementText("/config/isSendEmail");
		if(isSend==null||isSend.trim().equals("0")){
			isSendEmail=true;
		}else{
			isSendEmail=false;
		}
		jenkinsReportPath=pxConfig.getElementText("/config/jenkinsReportPath");
	}
	
}


