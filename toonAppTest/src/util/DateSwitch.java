package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class DateSwitch {
	
	public static void main(String[] args){
		Long javaDate = System.currentTimeMillis();
	System.out.println(javaDate+ "转换成PHP时间戳为：" +String.valueOf(javaDate).substring(0, 10));
		CstDateToStr("1424770600");
		getTimestamp("2014-11-13 15:35:00");
		getTimestamp("2014-11-13 18:00:00");
		
/*		ClassLoader cl = ClassLoader.
		try {
			cl.loadClass("HelloWorld");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Class.forName("HelloWorld");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
	}
	
	/**
	 *     将unix毫秒时间戳转换成 字符串类型
	 * @param unixDateMillis  unix的毫秒时间
	 * @return  2014-12-25 17:45:38|312 类型的字符串
	 */
		   
	public static String CstUnixDateToStr (String unixDateMillis){
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS"); 

		   long unixLong = 0; 
		   String date = ""; 
		   try { 
		   unixLong = Long.parseLong(unixDateMillis) ; 
		   } catch(Exception ex) { 
		   System.out.println("String转换Long错误，请确认数据可以转换！"); 
		   }
		   try { 
		   date = fm1.format(unixLong); 

		   } catch(Exception ex) { 
		   System.out.println("String转换Date错误，请确认数据可以转换！"); 
		   } 
			
		   return date;
	}
	
	/**
	 *  将2014-12-25 17:45:38|312 类型的字符串   转转成unix 毫秒时间戳
	 * @param date
	 * @return unix 毫秒时间戳
	 */
	public static String getTimeMillisStamp(String unixDateMillis){  
	    
	   Date date1 = null;
//	   long l;
	try {
		date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS").parse(unixDateMillis);
	} catch (ParseException e) {
		e.printStackTrace();
	}  

	 System.out.println("转为为时间戳为：" + date1.getTime()/1000);      
	
	 return String.valueOf(date1.getTime()/1000);
	 

	} 	  
		   
	public static String CstDateToStr (String unixDate){
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		   long unixLong = 0; 
		   String date = ""; 
		   try { 
		   unixLong = Long.parseLong(unixDate) * 1000; 
		   } catch(Exception ex) { 
		   System.out.println("String转换Long错误，请确认数据可以转换！"); 
		   }
		   try { 
		   date = fm1.format(unixLong); 

		   } catch(Exception ex) { 
		   System.out.println("String转换Date错误，请确认数据可以转换！"); 
		   } 
		   System.out.println(date); 
		   
	
		   return date;
	}

	public static String getTimestamp(String date){  
	    
	   Date date1 = null;
//	   long l;
	try {
		date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
	} catch (ParseException e) {
		e.printStackTrace();
	}  

	      
//	 Long time = date1.getTime()/1000 ;
	 System.out.println("转为为时间戳为：" + date1.getTime()/1000);      
	
	 return String.valueOf(date1.getTime()/1000);
	 

	} 
	

	 


}
