package com.kyxmcms.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;


import com.kyxmcms.entity.Admin;
import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Company;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.AdminService;
import com.kyxmcms.service.impl.AdminServiceImpl;


@WebServlet("/admin")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();
	

	public void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//搜索条件
		String str = request.getParameter("key");
		if (str == null) {
			str = "";
		}
		
		Pagination<Admin> pagination = new Pagination<Admin>();
		String pageNum = request.getParameter("page");
		// 获取前台pagination，不为空则是翻页；为空则为初始。
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		
		pagination.setTotalItemsCount(adminService.getCount(str));
		List<Admin> list = adminService.getAdmin(str, pagination);
		pagination.setItems(list);
		
		request.setAttribute("pagination", pagination);
		request.setAttribute("key", str);
		request.getRequestDispatcher("WEB-INF/pages/user.jsp").forward(request, response);
	}
	
	public void modifySelfPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/modifySelfPwd.jsp").forward(request, response);
	}
	public void modifyOtherPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		
		request.setAttribute("adminId",adminId);
		request.setAttribute("adminName",adminName);
		request.getRequestDispatcher("/WEB-INF/pages/modifyOtherPwd.jsp").forward(request, response);
	}
	public void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String oldPswd = request.getParameter("oldPwd");
		String newPswd = request.getParameter("newPwd");
		String adminIdStr = request.getParameter("adminId");
		System.out.println(adminIdStr);
		Admin newAdmin = new Admin();
		if (adminIdStr == null) {
			HttpSession session = request.getSession();
			Admin admin = (Admin)session.getAttribute("admin");
			newAdmin.setAdminId(admin.getAdminId());
		} else {
			newAdmin.setAdminId(Integer.parseInt(adminIdStr));
		}
		newAdmin.setPassword(newPswd);
		System.out.println(newAdmin.toString());
		
		String stringer = "false";
		response.setContentType("text/html; charset=UTF-8");
		
		if (oldPswd != null && !oldPswd.equals("")) {
			if (!oldPswd.equals(adminService.select(newAdmin.getAdminId()).getPassword())) {
				stringer = "error";
				response.getOutputStream().write(stringer.getBytes("UTF-8"));
				return;
			}
		}
		
		if(adminService.updateAdmin(newAdmin)) {
			stringer = "true";
		}
		response.getOutputStream().write(stringer.getBytes("UTF-8"));
	}
	
	
	public void delete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		int id = Integer.parseInt(adminId);
		
		String returnStr = "fail";
		if (adminService.delete(id)) {
			returnStr = "success";
		}
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
	}
	
	public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/userAdd.jsp").forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Admin admin = new Admin();
		try {
			BeanUtils.populate(admin, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		String returnStr = "fail";
		if (adminService.selectUser(admin.getAdminName()) != null) {
			returnStr = "exist";
			response.getOutputStream().write(returnStr.getBytes("UTF-8"));
			return;
		}
		if (adminService.insertAdmin(admin)) {
			returnStr = "success";
		}
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
	}
	
	//退出
	public void adminLogout(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();//销毁session
		response.sendRedirect("login.jsp");
	}
}
