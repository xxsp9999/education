package pers.xx.edu.utils;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFWbUtils {

	public static XSSFCellStyle getCellStyle(XSSFWorkbook xwb) {
		XSSFCellStyle xcs = xwb.createCellStyle();
		// 水平对齐方式
		xcs.setAlignment(HorizontalAlignment.CENTER);
		// 垂直对齐方式
		xcs.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		// 设置字体
		XSSFFont baseFont = xwb.createFont();
		// 字体类型
		baseFont.setFontName("宋体");
		// 字体大小
		baseFont.setFontHeightInPoints((short) 12);
		xcs.setFont(baseFont);

		return xcs;
	}

	public static Workbook addWorkBook(List<List<String>> listEntity, String[] header,String sheetName) {
		XSSFWorkbook wb = null;
		XSSFSheet xsh = null;
		XSSFRow row = null;
		XSSFCellStyle xcs = null;
		Cell cell = null;
		List<String> list = null;
		try {
			wb = new XSSFWorkbook();
			xsh = wb.createSheet(sheetName);
			xcs = getCellStyle(wb);
			for (int i = 0; i <= listEntity.size(); i++) {
				row = xsh.createRow(i);
				if (i == 0) {
					for (int j = 0; j < header.length; j++) {
						cell = row.createCell(j);
						cell.setCellStyle(xcs);
						cell.setCellValue(header[j]);
						xsh.autoSizeColumn((short) j);
					}
				} else {
					list = listEntity.get(i - 1);
					for (int j = 0; j < header.length; j++) {
						cell = row.createCell(j);
						cell.setCellStyle(xcs);
						cell.setCellValue(list.get(j));
						/*xsh.autoSizeColumn((short) j);*/
					}
				}
			}
			return wb;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}
}
