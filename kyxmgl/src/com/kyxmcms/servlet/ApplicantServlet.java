package com.kyxmcms.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.kyxmcms.dao.impl.BaseDao;
import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.ApplicantService;
import com.kyxmcms.service.impl.ApplicantServiceImpl;
import com.kyxmcms.util.ExportExcelUtil;
/**
 * 
 * @author HuangYK
 *
 * @date 2017-7
 */
@WebServlet("/applicant")
public class ApplicantServlet extends BaseServlet{
	private static final long serialVersionUID = 5452127096975356759L;
	private static final int SELECT_ALL_INFO = 1;
	private static final int SELECT_ID_NAME = 2;
	
	private ApplicantService applicantService = new ApplicantServiceImpl();
	
	public void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取搜索条件
		String condition = "";
		String str = request.getParameter("condition");
		String conditionDispaly = request.getParameter("conditionDispaly");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		Pagination<Applicant> pagination = new Pagination<Applicant>();
		// 获取前台pagination信息，不为空则是翻页；为空则为初始。
		String pageNum = request.getParameter("page");
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		pagination.setTotalItemsCount(applicantService.getCount(condition));
		List<Applicant> applicantList = applicantService.getAllApplicant(condition, pagination, SELECT_ALL_INFO);
		pagination.setItems(applicantList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("condition", str);
		request.setAttribute("conditionDispaly", conditionDispaly);
		request.getRequestDispatcher("/WEB-INF/pages/applicant.jsp").forward(request, response);
	}
	
	// 添加单位/编辑信息 页面
	public void applicantAddPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String applicantId = request.getParameter("applicantId");
		List<Object> resList = null;
		String opType = "add";
		if (applicantId != null && !applicantId.equals("")) {
			// 获得人员所有信息
			resList = applicantService.getApplicantById(applicantId);
			
			request.setAttribute("app", (Applicant)resList.get(0));
			request.setAttribute("proList", (List<Project>)resList.get(1));
			request.setAttribute("cyproList", (List<Project>)resList.get(2));
			opType = "update";
		}
		request.setAttribute("opType", opType);
		request.getRequestDispatcher("/WEB-INF/pages/applicantAdd.jsp")
				.forward(request, response);
	}
	
	// 添加/修改 单位
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Applicant app = new Applicant();
		try {
			BeanUtils.populate(app, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		String opType = request.getParameter("opType");
		String returnStr = "success";
		
		if (opType.equals("update")) {
			int res = 0;
			res = applicantService.updateApplicant(app);
			if (res == 0) {
				returnStr = "fail";
			}
		} else {
			try {
				returnStr = applicantService.insertApplicant(app);
			} catch (SQLException e) {
				returnStr = "fail";
				e.printStackTrace();
			}
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
		return;
	}

	// 删除单位
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration applicantIds = request.getParameterNames();
		boolean res = applicantService.deleteById(applicantIds);
		response.setContentType("text/html; charset=UTF-8");
		if (res) {
			response.getOutputStream().write("success".getBytes("UTF-8"));
		} else {
			response.getOutputStream().write("fail".getBytes("UTF-8"));
		}
	}

	// 导入页面
	public void importExcelPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("uploadPath", "upload?value=applicant");
		request.setAttribute("getExcelPath", "applicant?value=getExcelTemplate");

		request.getRequestDispatcher("/WEB-INF/pages/uploadExcel.jsp").forward(
				request, response);
	}
	
	// 导出applicant
	public void exprotExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 搜索条件
		String condition = "";
		String str = request.getParameter("condition");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		List<Applicant> appList = applicantService.getAllApplicant(condition);
		// 考虑获取前台的？
		String[] arr = { "姓名", "身份证号", "性别", "出生日期", "学历", "毕业院校",
				"研究方向", "电话", "邮箱", "简介"};
		String[] fields = { "applicantName", "applicantId", "applicantSex",
				"applicantBirth", "applicantEdu", "applicantUniv",
				"aResDirection", "applicantTel", "applicantMailbox","applicantInfo"};
		ExportExcelUtil.export(request, response, appList, arr, fields,
				"项目人员信息表.xls");
	}

	public void getExcelTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String path = request.getSession().getServletContext().getRealPath("")
				+ "/download/applicant.xls";
		ExportExcelUtil.downloadFile(request, response, path, "批量导入项目人员模板.xls");
	}

	// 流加载页面
	public void selectPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("dataType", "applicant");
		request.setAttribute("selectType", request.getParameter("selectType"));
		request.setAttribute("selectId", request.getParameter("selectId"));
		request.getRequestDispatcher("/WEB-INF/pages/selectPage.jsp").forward(
				request, response);
	}

	// 流加载
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 分页参数
		Pagination<Applicant> pagination = new Pagination<Applicant>();
		// 默认15条
		pagination.setPageSize(30);// 测试用，实际值可能是50?
		// 获取前台pagination，不为空则是翻页；为空则为初始。
		String pageNum = request.getParameter("page");
		System.out.println("流加载" + pageNum);
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		response.setContentType("application/json; charset=utf-8");
		String returnStr = "{\"pages\":1,\"data\":";
		pagination.setTotalItemsCount(applicantService.getCount(""));
		returnStr = returnStr.replace("1", pagination.getTotalPageCount() + "");
		List<Applicant> applicantList = applicantService.getAllApplicant("",
				pagination, SELECT_ID_NAME);
		// list-->json
		Gson gson = new Gson();
		String str = gson.toJson(applicantList);
		returnStr += str + "}";
		System.out.println(returnStr);
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
	}
}
