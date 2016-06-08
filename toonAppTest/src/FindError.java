
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class FindError {
	public static void main(String[] args) throws IOException {
		File file = new File("executelog");
//		File filew = new File("executewlog");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		StringBuffer sb = new StringBuffer();
		
		String tmpString;
		while((tmpString=br.readLine())!=null) {
			if(tmpString.contains("Fail!") || tmpString.contains("Total tests run:")) {
				sb.append(tmpString+"\n");
			}
		
		}	
		writefileOnly("executewlog", sb.toString(), "utf-8");
		
		/*file = new File("executewlog");
		filew = new File("executew2log");
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filew), "utf-8"));
		
		while((tmpString=br.readLine())!=null) {
			String tmpString2 = br.readLine();
			if(!tmpString2.contains("Failures: 0,")) {
				bw.write(tmpString+"\r\n");
				bw.write(tmpString2+"\r\n\r\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();*/
		
		System.out.println("½Å±¾Ö´ÐÐÍê±Ï!");
	}
	
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
}
