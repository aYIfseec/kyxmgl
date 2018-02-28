package com.kyxmcms.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcelUtil {

	// 导出
	public static void export(HttpServletRequest request,
			HttpServletResponse response, List dataList, String[] titles,
			String[] fields, String fileName) throws IOException {
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		String userAgent = request.getHeader("User-Agent");
		byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
				: fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
		fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		response.setHeader("Content-disposition",
				String.format("attachment; filename=\"%s\"", fileName));
		// 创建Excel
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet0");
		try {
			CreatExcelUtil.outputHeaders(titles, sheet);
			CreatExcelUtil.outputColumns(fields, dataList, sheet, 1);
			// 获取输出流，写入excel 并关闭
			ServletOutputStream out = response.getOutputStream();
			wb.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 文件下载
	public static void downloadFile(HttpServletRequest request,
			HttpServletResponse response, String filePath, String fileName) throws Exception {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(new FileInputStream(new String(filePath.getBytes("UTF-8"))));
		} catch (FileNotFoundException e) {
			response.getOutputStream().write("暂无该文件！".getBytes("UTF-8"));
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (bis != null) {
			bos = new BufferedOutputStream(response.getOutputStream());
			long fileLength = new File(filePath).length();

			response.setCharacterEncoding("UTF-8");
			response.setContentType("multipart/form-data");
			/*
			 * 解决各浏览器的中文乱码问题
			 */
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes()
					: fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-8")处理safari的乱码问题
			fileName = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
			response.setHeader("Content-disposition",
					String.format("attachment; filename=\"%s\"", fileName));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
}