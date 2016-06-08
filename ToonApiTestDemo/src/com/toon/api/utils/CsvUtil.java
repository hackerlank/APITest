package com.toon.api.utils;

/**
 * 处理csv文件工具类
 * @author haoyuexun
 *
 */

public class CsvUtil {
	/**
	 * 获取csv文件内容
	 * @param csvFilePath  项目的相对路径
	 * @param removeHeader 是否要用第一行的数据
	 * @return	Object[][]
	 * @throws Exception
	 */
	public static Object[][] getCsvData(String csvFilePath, boolean removeHeader) throws Exception {
//		String path = System.getProperty("user.dir");
////		System.out.println(path+ csvFilePath);
////		InputStream in = Utils.class.getResourceAsStream(csvFilePath);
//		InputStream in = new FileInputStream(new File(path + csvFilePath));
//		
//		Object[][] data = (new CSVParser(new InputStreamReader(in, "gbk"),CSVStrategy.EXCEL_STRATEGY)).getAllValues();
//		if (removeHeader) {
//			Object[][] dataWithoutTitle = Arrays.copyOfRange(data, 1, data.length);
//			return dataWithoutTitle;
//		} else {
//			return data;
//		}
		return null;
	}
	/**
	 * 获取csv数据，除了第一行
	 * @param csvFilePath 对项目的相对路径
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getCsvData(String csvFilePath) throws Exception {
		return getCsvData(csvFilePath, true);
	}
}
