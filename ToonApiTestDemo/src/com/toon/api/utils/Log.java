package com.toon.api.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;


/**
 * @Package com.aoyou.test.util
 * @ClassName Log
 * @Description ��־��ӡģ��.��־����:Info<Warn<Error
 * 3�������ӿڷ���:logError/logInfo/logWarn
 * 
 */
 
public class Log {
	
	private static Logger logger;
	private static String filePath="log4j.properties";
	private static boolean flag=false;
	
	private static synchronized void getPropertyFile(){	
		logger=Logger.getLogger("");
		PropertyConfigurator.configure(new File(filePath).getAbsolutePath());
		flag=true;
	}
	
	/**
	 * ��ʼ����飬������״ε������¼����ļ�·��
	 */
	private static void getFlag(){		
		if(flag == false)
			Log.getPropertyFile();
	}
	
	/**
	 * ��ӡInfo�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 * @param caseName ��������name
	 */
	public static void logInfo(String caseName,String message){
		Log.getFlag();
		logger.info("["+caseName+"]"+message);
		reporter(0,message,caseName);
	}
	
	/**
	 * ��ӡInfo�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 * @param caseName ��������name
	 */
	public static void logInfo(String message){
		Log.getFlag();
		logger.info(message);
	}
	
	/**
	 * ��ӡError�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 * @param caseName ��������name
	 */
	public static void logError(String caseName,String message){
		Log.getFlag();
		logger.error("["+caseName+"]"+message);
		reporter(1,message,caseName);
	}
	
	/**
	 * ��ӡError�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 * @param caseName ��������name
	 */
	public static void logError(String message){
		Log.getFlag();
		logger.error(message);
	}
	
	/**
	 * ��ӡWarning�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 * @param caseName ��������name
	 */
	public static void logWarn(String caseName,String message){
		Log.getFlag();
		logger.warn("["+caseName+"]"+message);
		reporter(2,message,caseName);
	}
	
	/**
	 * ��ӡWarning�������־
	 * @param message ��Ҫ��ӡ����Ϣ
	 */
	public static void logWarn(String message){
		Log.getFlag();
		logger.warn(message);
	}
	
	public static void reporter(int level,String message,String caseName){
		switch(level){
		case 0:
			Reporter.log("��PASS��"+" ["+caseName+"]"+message);
			//Data.reporterLog.add("��PASS��"+" []"+message);
			break;
		case 1:
			Reporter.log("��FAILED�� "+" ["+caseName+"]"+message);
			//Data.reporterLog.add("��FAILED�� "+" []"+message);
			break;	
		case 2:
			Reporter.log("��WARNING�� "+" ["+caseName+"]"+message);
			//Data.reporterLog.add("��WARNING�� "+" []"+message);
			break;	
		default:System.out.println("���漶�����!");	
		}
		
	}
	
	
}
