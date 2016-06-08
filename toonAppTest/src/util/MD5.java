package util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;


public class MD5 { 
	
//	private static CRC32 crc32 = new CRC32();
	
	public static String md5s(String plainText) {  
		try {   
			MessageDigest md = MessageDigest.getInstance("MD5");   
			try {
				md.update(plainText.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}   
			byte b[] = md.digest();   
			int i;   
			StringBuffer buf = new StringBuffer("");   
			for (int offset = 0; offset < b.length; offset++) {    
				i = b[offset];    
				if (i < 0)     i += 256;    
				if (i < 16)     buf.append("0");    
				buf.append(Integer.toHexString(i));   
			}   
			return buf.toString();
//			logger.debug("16位的加密 result: " + buf.toString().substring(8, 24));// 16位的加密  
		} catch (NoSuchAlgorithmException e) {   
			e.printStackTrace();  
		} 
		return null;
	} 
	/*
	 * 对应 PHP md5($a,true)
	 */
	public static byte[] md5Bin(String plainText) {  
		 
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				try {
					md.update(plainText.getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}   
				byte b[] = md.digest(); 
				return b;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}   	    
	} 
		
	 public static int getSubNum(String a,String b){
		  int num=0;
		  String str=a;
		  int index=a.indexOf(b);
		  while(index!=-1){
			   num++;
			   str=str.substring(index+b.length()-1);
			   index=str.indexOf(b);
		  }
		  	return num;
		 }
	 /**
	  * 到家会员表分库分表 index
	  * @param mobile
	  * @return  table_indexs
	  */
	 public static String getTableIndexUser(String mobile){
		CRC32 crc32 = new CRC32(); 
		crc32.update(md5s(mobile).getBytes());
		String tableIndexUser = String.valueOf( crc32.getValue()%300+1 )   ;          
		return tableIndexUser;
		 
	 }
	 
	 public static String getCheckDigit(String msg){
		 CRC32 crc32= new CRC32(); 
	
		crc32.update(md5Bin(msg));
			

		 String checkStr = String.valueOf( crc32.getValue());
		 
		 return checkStr;
	 }

	  public static void main(String[] args) {
		
			
		
		String msg2 = "{\"ServiceCode\":\"00005\"}";
		String msg = "{\"CityID\":\"1\",\"Address\":\"北京大学\",\"ServiceCode\":\"00005\"}";

		System.out.println(getCheckDigit(msg));   
		

		String mb = "18810041024";
		System.out.println(getTableIndexUser(mb));
		
		
	  }
	 

}
