package com.toon.api.test.testcase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class WriteTestReport {
	
	public static void 	analyFileAndWriteFile(String filePath, String encode) throws IOException {
		File file = new File(filePath);

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encode));  
		StringBuffer sb = new StringBuffer();
		String tmpString;
		int failCount = 0;
		while((tmpString=br.readLine())!=null) {
			if(tmpString.contains("Fail!�������")) {
				sb.append(tmpString + "\n");
				failCount += 1;
			}
		}
		sb.append("���������ܹ�:" + failCount);
		
		util.FileUtil.writefile(filePath, sb.toString(), encode);
		
		System.out.println("����������ɱ������");
	}
}



