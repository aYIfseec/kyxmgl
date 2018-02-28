package com.kyxmcms.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.CompanyType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.CompanyService;
import com.kyxmcms.service.impl.CompanyServiceImpl;
import com.kyxmcms.util.ExportExcelUtil;


/**
 * 
 * @author HuangYK
 *
 * @date 2017-7
 */
@WebServlet("/company")
public class CompanyServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private static final int SELECT_ALL_INFO = 1;
	private static final int SELECT_ID_NAME = 2;
	
	
	private CompanyService companyService = new CompanyServiceImpl();
	
	public void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//搜索条件
		String condition = "";
		String str = request.getParameter("condition");
		String conditionDispaly = request.getParameter("conditionDispaly");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		//分页参数
		Pagination<Company> pagination = new Pagination<Company>();
		// 默认15条
		//pagination.setPageSize(4);
		// 获取前台pagination，不为空则是翻页；为空则为初始。
		String pageNum = request.getParameter("page");
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		pagination.setTotalItemsCount(companyService.getCount(condition));
		List<Company> companyList = companyService.getAllCompany(condition, pagination, SELECT_ALL_INFO);
		pagination.setItems(companyList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("condition", str);
		request.setAttribute("conditionDispaly", conditionDispaly);
		request.getRequestDispatcher("/WEB-INF/pages/company.jsp").forward(request, response);
	}
	
	//流加载页面
	public void selectPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("dataType", "company");
		request.setAttribute("selectType", request.getParameter("selectType"));
		request.setAttribute("selectId", request.getParameter("selectId"));
		request.getRequestDispatcher("/WEB-INF/pages/selectPage.jsp").forward(
				request, response);
	}
	//流加载
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 分页参数
		Pagination<Company> pagination = new Pagination<Company>();
		// 默认15条
		pagination.setPageSize(30);// 测试用，实际值可能是50?
		// 获取前台pagination，不为空则是翻页；为空则为初始。
		String pageNum = request.getParameter("page");
		System.out.println("流加载"+pageNum);
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		response.setContentType("application/json; charset=utf-8");
		String returnStr = "{\"pages\":1,\"data\":";
		pagination.setTotalItemsCount(companyService.getCount(""));
		returnStr = returnStr.replace("1", pagination.getTotalPageCount() + "");
		List<Company> companyList = companyService.getAllCompany("",
				pagination, SELECT_ID_NAME);
		// list-->json
		Gson gson = new Gson();
		String str = gson.toJson(companyList);
		returnStr += str + "}";
		System.out.println(returnStr);
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
		
	}
	
	// 添加单位/编辑信息 页面
	public void companyAddPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String companyId = request.getParameter("companyId");
		Company company = null;
		List<List<Project>> proList = null;
		if (companyId != null && !companyId.equals("")) {
			// 获得单位基本信息
			company = companyService.getCompanyById(companyId);
			// 获得该单位参与的项目名与id,,,要修改
			proList = companyService.getJoinProject(companyId);
			
			request.setAttribute("proList",proList.get(1));
			request.setAttribute("cyproList",proList.get(0));
		}
		request.setAttribute("com",company);
		
		List<CompanyType> comTypeList = companyService.getAllComType();
		request.setAttribute("companyTypeList", comTypeList);
		
		request.getRequestDispatcher("/WEB-INF/pages/companyAdd.jsp").forward(
				request, response);
	}
	
	
	// 添加单位
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Company com = new Company();
		try {
			BeanUtils.populate(com, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println(com);
		
		String returnStr = "success";
		//根据有无id判断 修改 还是 添加
		if (com.getCompanyId() != null && !com.getCompanyId().equals("")) {
			int res = 0;
			res = companyService.updateCompany(com);
			if (res == 0) {
				returnStr = "fail";
			}
		} else {
			// 查其是否已存在数据库中
			boolean isExist = companyService.isExist(com.getCompanyName(), null);
			if (isExist) {
				returnStr = "exist";
			} else {
				int res = 0;
				res = companyService.insertCompany(com);
				if (res == 0) {
					returnStr = "fail";
				}
			}
		}
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
		return;
	}
	
	//删除单位
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration companyIds = request.getParameterNames();
		boolean res = companyService.deleteById(companyIds);
		response.setContentType("text/html; charset=UTF-8");
		if (res) {
			response.getOutputStream().write("success".getBytes("UTF-8"));
		} else {
			response.getOutputStream().write("fail".getBytes("UTF-8"));
		}
	}
	
	//导入页面
	public void importExcelPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("uploadPath", "upload?value=company");
		request.setAttribute("getExcelPath", "company?value=getExcelTemplate");
		
		request.getRequestDispatcher("/WEB-INF/pages/uploadExcel.jsp").forward(request, response);
	}
	
	//导出company
	public void exprotExcel(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//搜索条件
		String condition = "";
		String str = request.getParameter("condition");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		List<Company> comList = companyService.getAllCompany(condition);
		//考虑获取前台的？
		String[] arr = {"单位名称","单位类型","研究方向","单位地址","单位网址","单位简介"};
		String[] fields = {"companyName","companyTypeName","cResDirection",
				"companyAddress", "companyWebUrl", "companyInfo"};
		ExportExcelUtil.export(request,response, comList, arr, fields, "合作单位信息表.xls");
	}
	
	public void getExcelTemplate(HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		String path = request.getSession().getServletContext().getRealPath("") + "/download/company.xls";
		ExportExcelUtil.downloadFile(request, response, path, "批量导入单位模板.xls");
	}
}
