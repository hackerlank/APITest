package com.toon.api.utils;

/**
 * ����csv�ļ�������
 * @author haoyuexun
 *
 */

public class CsvUtil {
	/**
	 * ��ȡcsv�ļ�����
	 * @param csvFilePath  ��Ŀ�����·��
	 * @param removeHeader �Ƿ�Ҫ�õ�һ�е�����
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
	 * ��ȡcsv���ݣ����˵�һ��
	 * @param csvFilePath ����Ŀ�����·��
	 * @return
	 * @throws Exception
	 */
	public static Object[][] getCsvData(String csvFilePath) throws Exception {
		return getCsvData(csvFilePath, true);
	}
}
