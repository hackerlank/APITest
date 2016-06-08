package com.toon.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVUtils {

	//CSV�ļ��ָ���
	private final static String NEW_LINE_SEPARATOR="\n";
	
	/**д��csv�ļ�
	 * @param headers ��ͷ
	 * @param data ��������
	 * @param filePath ������csv�ļ�·��
	 * @throws IOException **/
	public static void writeCsv(String[] headers,List<String[]> data,String filePath) throws IOException{
		
		//��ʼ��csvformat
		CSVFormat formator = CSVFormat.EXCEL.withRecordSeparator(NEW_LINE_SEPARATOR);
		
		//����FileWriter����
		FileWriter fileWriter=new FileWriter(filePath);
		
		//����CSVPrinter����
		CSVPrinter printer=new CSVPrinter(fileWriter,formator);
		
		//д����ͷ����
		printer.printRecord(headers);
		
		if(null!=data){
			//ѭ��д������
			for(String[] lineData:data){
				
				printer.printRecord(lineData);
				
			}
		}
		
		fileWriter.close();
		printer.close();
		
		System.out.println("CSV�ļ������ɹ�,�ļ�·��:"+filePath);		
	}
	
	/**��ȡcsv�ļ�
	 * @param filePath �ļ�·��
	 * @param headers csv��ͷ
	 * @param removeHraders �Ƿ��Ƴ���ͷ
	 * @return CSVRecord �б�
	 * @throws IOException **/
	public static List<CSVRecord> readCSV(String filePath,String[] headers,boolean removeHeader) throws IOException{
		
		//����CSVFormat
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);
        
        FileReader fileReader=new FileReader(filePath);
        
        //����CSVParser����
//        CSVParser parser=new CSVParser(fileReader,formator);
        CSVParser parser=CSVParser.parse(new File(filePath), Charset.forName("gbk"), formator);
        
        List<CSVRecord> records=parser.getRecords();
        
        parser.close();
        fileReader.close();
		//��ȡ���еļ�¼��
			
		if(removeHeader)
			//�Ƴ���ͷ
			records.remove(0);
		
		return records;
	}
	
	/**��ȡcsv�ļ�,��������ͷ��¼
	 * @param filePath �ļ�·��
	 * @param headers csv��ͷ
	 * @param removeHraders �Ƿ��Ƴ���ͷ
	 * @return CSVRecord �б�
	 * @throws IOException **/
	public static List<CSVRecord> readCSVWithNoHeader(String filePath,String[] headers) throws IOException{	
		return readCSV(filePath,headers,true);
	}
}
