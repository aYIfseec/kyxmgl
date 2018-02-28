package com.kyxmcms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 黄勇康
 * @date   2017
 * @功能 字符转码  过滤器
 */
public class CharsetFilter implements Filter  {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		
		//图片服务器地址
		//req.getSession().setAttribute("imgUrl", "http://118.89.164.202:8888");
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}


}
