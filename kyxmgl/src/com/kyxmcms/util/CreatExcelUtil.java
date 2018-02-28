package com.kyxmcms.util;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class CreatExcelUtil {

	
	/**
	 * 设置sheet表头信息
	 * @param headersInfo
	 * @param sheet
	 */
	public static void outputHeaders(String[] headersInfo,HSSFSheet sheet ){
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headersInfo.length; i++) {
			sheet.setColumnWidth(i, 4000);
			row.createCell(i).setCellValue(headersInfo[i]);
		}
	}
	
	public static void outputColumns(String[] headersInfo,
			List columnsInfo,HSSFSheet sheet,int rowIndex ){
		HSSFRow row ;
		int headerSize = headersInfo.length;
		int columnSize = columnsInfo.size();
		//循环插入多少行
		for (int i = 0; i < columnsInfo.size(); i++) {
			row = sheet.createRow(rowIndex+i);
			Object obj = columnsInfo.get(i);
			//循环每行多少列
			for (int j = 0; j < headersInfo.length; j++) {
				Object value = getFieldValueByName(headersInfo[j],obj);
				if (value == null) {
					value = "";
				}
				row.createCell(j).setCellValue(value.toString());
			}
		}
		
	}
	/**
	 * 根据对象的属性获取值
	 * @param string
	 * @param obj
	 * @return
	 */
	private static Object getFieldValueByName(String fieldName, Object obj) {
		String firstLetter = fieldName.substring(0,1).toUpperCase();
		String getter = "get" +firstLetter + fieldName.substring(1);
		try {
			Method method = obj.getClass().getMethod(getter, new Class[]{});
			Object value = method.invoke(obj, new Object[]{});
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("属性不存在！");
			return null;
		} 
	}
}
