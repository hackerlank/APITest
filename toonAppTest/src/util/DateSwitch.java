package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class DateSwitch {
	
	public static void main(String[] args){
		Long javaDate = System.currentTimeMillis();
	System.out.println(javaDate+ "ת����PHPʱ���Ϊ��" +String.valueOf(javaDate).substring(0, 10));
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
	 *     ��unix����ʱ���ת���� �ַ�������
	 * @param unixDateMillis  unix�ĺ���ʱ��
	 * @return  2014-12-25 17:45:38|312 ���͵��ַ���
	 */
		   
	public static String CstUnixDateToStr (String unixDateMillis){
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS"); 

		   long unixLong = 0; 
		   String date = ""; 
		   try { 
		   unixLong = Long.parseLong(unixDateMillis) ; 
		   } catch(Exception ex) { 
		   System.out.println("Stringת��Long������ȷ�����ݿ���ת����"); 
		   }
		   try { 
		   date = fm1.format(unixLong); 

		   } catch(Exception ex) { 
		   System.out.println("Stringת��Date������ȷ�����ݿ���ת����"); 
		   } 
			
		   return date;
	}
	
	/**
	 *  ��2014-12-25 17:45:38|312 ���͵��ַ���   תת��unix ����ʱ���
	 * @param date
	 * @return unix ����ʱ���
	 */
	public static String getTimeMillisStamp(String unixDateMillis){  
	    
	   Date date1 = null;
//	   long l;
	try {
		date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss|SSS").parse(unixDateMillis);
	} catch (ParseException e) {
		e.printStackTrace();
	}  

	 System.out.println("תΪΪʱ���Ϊ��" + date1.getTime()/1000);      
	
	 return String.valueOf(date1.getTime()/1000);
	 

	} 	  
		   
	public static String CstDateToStr (String unixDate){
		SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

		   long unixLong = 0; 
		   String date = ""; 
		   try { 
		   unixLong = Long.parseLong(unixDate) * 1000; 
		   } catch(Exception ex) { 
		   System.out.println("Stringת��Long������ȷ�����ݿ���ת����"); 
		   }
		   try { 
		   date = fm1.format(unixLong); 

		   } catch(Exception ex) { 
		   System.out.println("Stringת��Date������ȷ�����ݿ���ת����"); 
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
	 System.out.println("תΪΪʱ���Ϊ��" + date1.getTime()/1000);      
	
	 return String.valueOf(date1.getTime()/1000);
	 

	} 
	

	 


}
