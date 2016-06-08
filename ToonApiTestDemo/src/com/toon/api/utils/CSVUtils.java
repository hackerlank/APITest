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

	//CSV文件分隔符
	private final static String NEW_LINE_SEPARATOR="\n";
	
	/**写入csv文件
	 * @param headers 列头
	 * @param data 数据内容
	 * @param filePath 创建的csv文件路径
	 * @throws IOException **/
	public static void writeCsv(String[] headers,List<String[]> data,String filePath) throws IOException{
		
		//初始化csvformat
		CSVFormat formator = CSVFormat.EXCEL.withRecordSeparator(NEW_LINE_SEPARATOR);
		
		//创建FileWriter对象
		FileWriter fileWriter=new FileWriter(filePath);
		
		//创建CSVPrinter对象
		CSVPrinter printer=new CSVPrinter(fileWriter,formator);
		
		//写入列头数据
		printer.printRecord(headers);
		
		if(null!=data){
			//循环写入数据
			for(String[] lineData:data){
				
				printer.printRecord(lineData);
				
			}
		}
		
		fileWriter.close();
		printer.close();
		
		System.out.println("CSV文件创建成功,文件路径:"+filePath);		
	}
	
	/**读取csv文件
	 * @param filePath 文件路径
	 * @param headers csv列头
	 * @param removeHraders 是否移除列头
	 * @return CSVRecord 列表
	 * @throws IOException **/
	public static List<CSVRecord> readCSV(String filePath,String[] headers,boolean removeHeader) throws IOException{
		
		//创建CSVFormat
        CSVFormat formator = CSVFormat.DEFAULT.withHeader(headers);
        
        FileReader fileReader=new FileReader(filePath);
        
        //创建CSVParser对象
//        CSVParser parser=new CSVParser(fileReader,formator);
        CSVParser parser=CSVParser.parse(new File(filePath), Charset.forName("gbk"), formator);
        
        List<CSVRecord> records=parser.getRecords();
        
        parser.close();
        fileReader.close();
		//获取所有的记录数
			
		if(removeHeader)
			//移除列头
			records.remove(0);
		
		return records;
	}
	
	/**读取csv文件,返回无列头记录
	 * @param filePath 文件路径
	 * @param headers csv列头
	 * @param removeHraders 是否移除列头
	 * @return CSVRecord 列表
	 * @throws IOException **/
	public static List<CSVRecord> readCSVWithNoHeader(String filePath,String[] headers) throws IOException{	
		return readCSV(filePath,headers,true);
	}
}
