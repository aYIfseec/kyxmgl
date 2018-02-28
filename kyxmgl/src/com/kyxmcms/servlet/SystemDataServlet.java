package com.kyxmcms.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/systemData")
public class SystemDataServlet extends BaseServlet{
	
	private static final long serialVersionUID = -465120416579951189L;
	
	public void main(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/pages/systemData.jsp").forward(
				request, response);
	}
}
