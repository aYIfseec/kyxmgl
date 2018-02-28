package com.kyxmcms.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author HuangYK
 * 
 * @date 2017-04-23
 * 
 */
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 重写doGet，而不是重写service
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)

	throws ServletException, IOException {
		
		String name = req.getParameter("value");// 获取方法名
		Class c = this.getClass();// 获得当前类的Class对象
		Method method = null;
		if (name == null || name.isEmpty()) {
			//throw new RuntimeException("没有传递value参数,请给出你想调用的方法");
			System.out.println("没有传递value参数");
			try {
				method = c.getMethod("doPost", HttpServletRequest.class,
						HttpServletResponse.class);
				method.invoke(this, req, resp);// 反射调用方法
				return;
			} catch (SecurityException e) {
				throw new RuntimeException("没有找到doPost");
			}  catch (Exception e) {
				System.out.println("你调用的方法doPost,内部发生了异常");
				throw new RuntimeException(e);
			}
		}

		try {
			// 获得Method对象
			method = c.getMethod(name, HttpServletRequest.class,
					HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("没有找到" + name
					+ "带有Request，Response的方法，请检查该方法是否存在");
		}

		try {
			method.invoke(this, req, resp);// 反射调用方法
		} catch (Exception e) {
			System.out.println("你调用的方法" + name + ",内部发生了异常");
			throw new RuntimeException(e);
		}
		
	}
}
