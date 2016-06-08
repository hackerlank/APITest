package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.PriorityQueue;



public class FileUtil {
	
	//可以追加的写入文件
	public static void writefile(String filename, String content){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename),true);   //haoyx modify  增加
	            out.write(content.getBytes());
	            out.write("\r\n".getBytes());
	            out.write("===================================================".getBytes());
	            out.write("\r\n".getBytes());
	            out.flush();
	            out.close();
		 }catch(Exception e){
		 }
	}
	/**
	 * 以某编码将内容写到文件中   （追加上次内容）
	 * @param filename
	 * @param content
	 * @param encode
	 */
	public static void writefile(String filename, String content,String encode){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename),true);   //haoyx modify  增加
	            out.write(content.getBytes(encode));
	            /*out.write("\r\n".getBytes());
	            out.write("===================================================".getBytes());
	            out.write("\r\n".getBytes());*/
	            out.flush();
	            out.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	
	//每次更新
	public static void writefileOnly(String filename, String content){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename));   
	            out.write(content.getBytes());
	            out.write("\r\n".getBytes());
	            out.write("===================================================".getBytes());
	            out.write("\r\n".getBytes());
	            out.flush();
	            out.close();
		 }catch(Exception e){
			e.printStackTrace();
		 }
	}
	/**
	 * 以某编码将内容写到文件中   （清除原来内容）
	 * @param filename  文件全路径   eg:  d:/123.txt
	 * @param content
	 * @param encode 字符编码
	 */
	public static void writefileOnly(String filename, String content,String encode){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename));   
	            out.write(content.getBytes(encode));
	            out.write("\r\n".getBytes());
//	            out.write("===================================================".getBytes());
//	            out.write("\r\n".getBytes());
	            out.flush();
	            out.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	//生成的.txt文件保存为gb2312
	public static void writefileNew(String filename, String content){
		 FileOutputStream out = null;  
		 try {   
//			 	byte[] bytes = content.getBytes("gb2312");
			
	            out = new FileOutputStream(new File(filename));   //替换
	            out.write(content.getBytes("gb2312"));	//以GB2312写入文件
	            out.write("\r\n".getBytes());
//	            out.write("//=========================================".getBytes());
//	            out.write("\r\n".getBytes());
	            out.flush();
	            out.close();
		 }catch(Exception e){
		 }
	}
	
	/**
	 * 获取某目录中指定类型的文件
	 * 
	 * @param string 查找文件夹
	 * @param ext  查找文件类型  eg:  txt  jpg
	 * @throws Exception
	 */
	
	public static ArrayList<File> acceptFiles(String string,String ext){
		List<File> arrFiles = new ArrayList<File>();
		File f = new File(string);
		try {	 
			String[] files = f.list();// 返回该目录下所有文件及文件夹数组
//			Arrays.sort(files); // 排序
				for(int i=0; i<files.length;i++) {
					if (files[i].endsWith("." + ext)) {
						arrFiles.add(new File(files[i])); 
					}
				}	
		}catch(Exception e){
			
			System.out.println("error!");
		}
		return  (ArrayList<File>) arrFiles;		
	}
	
	
	public static String readFile(String fileName) throws IOException{
		StringBuffer buffer = new StringBuffer();
		BufferedReader bin = null;
		String testName;
		int count = 0;
		try {
			bin = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(fileName)),"gbk"));
			String inputLine;
			while ((inputLine = bin.readLine()) != null) {
//				count++;
//				/*if(count == 5) {
//					testName = inputLine;	
//				}*/
//				bin.readLine().equalsIgnoreCase("");
					buffer.append(inputLine);
					System.out.println(inputLine);
					buffer.append("\r\n");								
			}
		}catch (Exception e) {
			System.out.println("An error occurred during file reading " + e);
		}
		finally{
			bin.close();
			
		}
		return buffer.toString();
	}
	/**
	 * 已某种编码读出文件内容
	 * @param fileName
	 * @param encode
	 * @return   
	 * @throws IOException
	 */
	public static String readFile(String fileName,String encode) throws IOException{
		StringBuffer buffer = new StringBuffer();
		BufferedReader bin = null;
		String testName;
		int count = 0;
		try {
			bin = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(fileName)),encode));
			String inputLine;
			while ((inputLine = bin.readLine()) != null) {
//				count++;
//				/*if(count == 5) {
//					testName = inputLine;	
//				}*/
//				bin.readLine().equalsIgnoreCase("");
					buffer.append(inputLine);
					System.out.println(inputLine);
					buffer.append("\r\n");
			
					
											
			}
		}catch (Exception e) {
			System.out.println("An error occurred during file reading " + e);
		}
		finally{
			bin.close();
			
		}
		return buffer.toString();
	}
	/**
	 * 删除目录下所有文件
	 * @param filePath  目录 eg:D:/haoyuexun/1/
	 */
	public static void deletePathAllFile(String filePath){
		
		File f;
		try {
			f = new File(filePath);
			String[] files = f.list();// 返回该目录下所有文件及文件夹数组

			for(int i=0; i<files.length;i++) {
				File ff = new File(filePath +files[i]);
				System.out.println(filePath+ files[i]);
				
				if (ff.exists()){
					ff.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 删除指定的文件
	 * @param fileName  待删除文件全路径 eg:D:/haoyuexun/1/1.txt
	 */
	public static void deleteFile(String fileName){
		
		File ff;
		try {
			ff = new File(fileName);
					
				if (ff.exists()){
					ff.delete();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void main(String[] args){
		String ff = "D:/haoyuexun/1/";
		String f = "D:/haoyuexun/1/1.txt";
		deletePathAllFile(ff);
		
	}
}
	
	
  
	
	

