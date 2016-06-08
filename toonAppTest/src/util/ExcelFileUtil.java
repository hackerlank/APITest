package util;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ExcelFileUtil {
	/**
	 * ��ȡExcel�ļ�������
	 * 
	 * @param file
	 *            ����ȡ���ļ�
	 * @return
	 */
	public static String readExcel(File file) {
		StringBuffer sb = new StringBuffer();
		Workbook wb = null;
		try {
			// ����Workbook��������������
			wb = Workbook.getWorkbook(file);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wb == null)
			return null;
		// �����Workbook����֮�󣬾Ϳ���ͨ�����õ�Sheet��������������
		Sheet[] sheet = wb.getSheets();
		if (sheet != null && sheet.length > 0) {
			// ��ÿ�����������ѭ��
			for (int i = 0; i < sheet.length; i++) {
				// �õ���ǰ�����������
				int rowNum = sheet[i].getRows();
				for (int j = 0; j < rowNum; j++) {
					// �õ���ǰ�е����е�Ԫ��
					Cell[] cells = sheet[i].getRow(j);
					if (cells != null && cells.length > 0) {
						// ��ÿ����Ԫ�����ѭ��
						for (int k = 0; k < cells.length; k++) {
							// ��ȡ��ǰ��Ԫ���ֵ
							String cellValue = cells[k].getContents();
							sb.append(cellValue + "\t");
						}
					}
					sb.append("\r\n");
				}
			}
		}
		// ���ر���Դ���ͷ��ڴ�
		wb.close();
		return sb.toString();
	}

	/**
	 * ����һ��Excel�ļ�
	 * 
	 * @param fileName
	 *            Ҫ���ɵ�Excel�ļ���
	 */
	public static void writeExcel(String fileName, String content) {
		WritableWorkbook wwb = null;
		try {
			// ����Ҫʹ��Workbook��Ĺ�����������һ����д��Ĺ�����(Workbook)����
			wwb = Workbook.createWorkbook(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// ����һ����д��Ĺ�����
			// Workbook��createSheet������������������һ���ǹ���������ƣ��ڶ����ǹ������ڹ������е�λ��
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			String[] t = content.split("\r\n");
			String s[][] = new String[t.length][];
			for (int m = 0; m < t.length; m++) {
				s[m] = t[m].split("\t");
			}
			// ���濪ʼ��ӵ�Ԫ��
			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < s[i].length; j++) {
					// ������Ҫע����ǣ���Excel�У���һ��������ʾ�У��ڶ�����ʾ��
					Label labelC = new Label(j, i,s[i][j]);
					try {
						// �����ɵĵ�Ԫ����ӵ���������
						ws.addCell(labelC);
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}

				}
			}

			try {
				// ���ڴ���д���ļ���
				wwb.write();
				// �ر���Դ���ͷ��ڴ�
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}

}
