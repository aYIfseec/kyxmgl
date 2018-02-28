package com.kyxmcms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kyxmcms.entity.Admin;
import com.kyxmcms.service.AdminService;
import com.kyxmcms.service.impl.AdminServiceImpl;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImpl();
	
	public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String userName = request.getParameter("userName");
		//获得登录时输入的用户名
		String password = request.getParameter("password");
		Admin admin = adminService.selectUser(userName);//查该用户
		
		String stringer = "false";
		
		//用户验证
		if(admin != null && admin.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			admin.setPassword("");
			session.setAttribute("admin", admin);
			stringer = "true";
		}
		response.getOutputStream().write(stringer.getBytes("UTF-8"));
	}
}
