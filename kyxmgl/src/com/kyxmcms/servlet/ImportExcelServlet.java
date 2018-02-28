package com.kyxmcms.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kyxmcms.service.ApplicantService;
import com.kyxmcms.service.CompanyService;
import com.kyxmcms.service.ProjectService;
import com.kyxmcms.service.impl.ApplicantServiceImpl;
import com.kyxmcms.service.impl.CompanyServiceImpl;
import com.kyxmcms.service.impl.ProjectServiceImpl;
import com.kyxmcms.util.ReadExcelUtil;
/**
 * 
 * @author HuangYK
 *
 * @功能  导入
 */
@WebServlet("/upload")
public class ImportExcelServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	private CompanyService companyService = new CompanyServiceImpl();
	private ApplicantService applicantService = new ApplicantServiceImpl();
	private ProjectService projectService = new ProjectServiceImpl();
	
	//导入
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value = request.getParameter("value");
		
		InputStream in = request.getInputStream();  
        StringBuffer sb = new StringBuffer();  
        int count = 0;  
        while(true){  
            int a = in.read();  
            sb.append((char)a);
            if(a == '\r')
                count++;
            if(count==4){
            	in.read();  
                break;
            }
        }  
        String title = sb.toString();  
        String[] titles = title.split("\r\n");  
        String fileName = titles[1].split(";")[2].split("=")[1].replace("\"","");  
System.out.println(fileName);
		
		List<List<Object>> listob = null;
		response.setContentType("application/json; charset=utf-8");
		String returnStr = "{\"code\":1,\"msg\":\"A\",\"data\":\"returnData\"}";
		//0失败，1成功，A信息
		try {
			listob = ReadExcelUtil.getListByExcel(in, fileName);
			Map<String, String> resMap = null;
			try {
				if ("company".equals(value)) {
					resMap = companyService.insertCompany(listob);
				} else if ("applicant".equals(value)) {
					resMap = applicantService.insertApplicant(listob);
				} else if ("project".equals(value)) {
					System.out.println("导入项目");
					resMap = projectService.insertProject(listob);
				}
				if (resMap.get("result").equals("fail")) {
					returnStr = returnStr.replace("1", "0");
					returnStr = returnStr.replace("A", "导入数据失败！");
				} else {
					returnStr = returnStr.replace("A", "导入成功！");
				}
				resMap.remove("result");
				//Gson gson = new GsonBuilder().enableComplexMapKeySerialization() .create();
				//String data = gson.toJson(resMap);
				String data = resMap.get("data");
				System.out.println(data);
				returnStr = returnStr.replace("returnData", data);
			} catch (Exception e) {
				returnStr = returnStr.replace("1", "0");
				returnStr = returnStr.replace("A", "文件内容格式有误！");
				e.printStackTrace();
			}
		} catch (Exception e) {
			returnStr = returnStr.replace("1", "0");
			returnStr = returnStr.replace("A", "文件格式有误！");
			e.printStackTrace();
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(returnStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (in != null) {
				in.close();
			}
		}
	}
}
