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
	
	//����׷�ӵ�д���ļ�
	public static void writefile(String filename, String content){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename),true);   //haoyx modify  ����
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
	 * ��ĳ���뽫����д���ļ���   ��׷���ϴ����ݣ�
	 * @param filename
	 * @param content
	 * @param encode
	 */
	public static void writefile(String filename, String content,String encode){
		 FileOutputStream out = null;  
		 try {   
	            out = new FileOutputStream(new File(filename),true);   //haoyx modify  ����
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
	
	
	//ÿ�θ���
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
	 * ��ĳ���뽫����д���ļ���   �����ԭ�����ݣ�
	 * @param filename  �ļ�ȫ·��   eg:  d:/123.txt
	 * @param content
	 * @param encode �ַ�����
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
	
	//���ɵ�.txt�ļ�����Ϊgb2312
	public static void writefileNew(String filename, String content){
		 FileOutputStream out = null;  
		 try {   
//			 	byte[] bytes = content.getBytes("gb2312");
			
	            out = new FileOutputStream(new File(filename));   //�滻
	            out.write(content.getBytes("gb2312"));	//��GB2312д���ļ�
	            out.write("\r\n".getBytes());
//	            out.write("//=========================================".getBytes());
//	            out.write("\r\n".getBytes());
	            out.flush();
	            out.close();
		 }catch(Exception e){
		 }
	}
	
	/**
	 * ��ȡĳĿ¼��ָ�����͵��ļ�
	 * 
	 * @param string �����ļ���
	 * @param ext  �����ļ�����  eg:  txt  jpg
	 * @throws Exception
	 */
	
	public static ArrayList<File> acceptFiles(String string,String ext){
		List<File> arrFiles = new ArrayList<File>();
		File f = new File(string);
		try {	 
			String[] files = f.list();// ���ظ�Ŀ¼�������ļ����ļ�������
//			Arrays.sort(files); // ����
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
	 * ��ĳ�ֱ�������ļ�����
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
	 * ɾ��Ŀ¼�������ļ�
	 * @param filePath  Ŀ¼ eg:D:/haoyuexun/1/
	 */
	public static void deletePathAllFile(String filePath){
		
		File f;
		try {
			f = new File(filePath);
			String[] files = f.list();// ���ظ�Ŀ¼�������ļ����ļ�������

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
	 * ɾ��ָ�����ļ�
	 * @param fileName  ��ɾ���ļ�ȫ·�� eg:D:/haoyuexun/1/1.txt
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
	
	
  
	
	

