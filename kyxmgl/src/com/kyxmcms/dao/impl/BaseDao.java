package com.kyxmcms.dao.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.entity.ProType;

/**
 * @author 黄勇康
 * @date 2017-4-21
 * @connect database
 */

public class BaseDao {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://yourMysqlServiceAddress:3306/kyxmgl"
			+ "?useUnicode=true&characterEncoding=UTF-8"
			+ "&useOldAliasMetadataBehavior=true";
	private static final String USER = "yourUserName";
	private static final String PASS = "YourPassword";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("未找到驱动类");
			e.printStackTrace();
		}
	}

	/**
	 * 获得连接
	 * 
	 * @return Connection
	 */
	public static Connection getConn() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("连接数据库" + conn);
		} catch (SQLException e) {
			System.out.println("连接数据库失败");
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 增删改
	 * 
	 * @param sql
	 * @param parameterList
	 * @return int
	 */
	public static int operUpdate(String sql, List<Object> parameterList) {
		Connection conn = null;
		PreparedStatement pste = null;
		int res = 0;
		conn = getConn();
		try {
			pste = conn.prepareStatement(sql);
			// 注入参数
			if (parameterList != null && parameterList.size() > 0) {
				for (int i = 0; i < parameterList.size(); i++) {
					pste.setObject(i + 1, parameterList.get(i));
				}
			}
			res = pste.executeUpdate();// 执行
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(null, pste, conn);// 关闭连接
		}
		return res;
	}

	/**
	 * 查询操作（查）
	 * 
	 * @param sql
	 * @param parameterList
	 * @param clas
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> operQuery(String sql, List<Object> parameterList,
			Class<T> clas) throws Exception {
		Connection conn = null;
		PreparedStatement pste = null;// 预处理语句
		ResultSet rs = null;// 结果集
		List<T> list = new ArrayList<T>();
		conn = getConn();
		try {
			pste = conn.prepareStatement(sql);
			// 注入参数
			if (parameterList != null) {
				for (int i = 0; i < parameterList.size(); i++) {
					pste.setObject(i + 1, parameterList.get(i));
				}
			}
			rs = pste.executeQuery();// 执行
			ResultSetMetaData rsmd = rs.getMetaData();
			while (rs.next()) {
				T m = clas.newInstance();// 利用反射实例化对象
				for (int j = 0; j < rsmd.getColumnCount(); j++) {
					// 从结果集结构中取得字段名
					String col_name = rsmd.getColumnName(j + 1);

					// 从结果集中取得值
					Object value = rs.getObject(col_name);

					Field field = clas.getDeclaredField(col_name);

					field.setAccessible(true);// 类中的成员变量为private,故必须进行此操作
					field.set(m, value);
				}
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(rs, pste, conn);// 关闭连接，释放资源
		}
		return list;
	}

	// 获得总记录数
	public static int getCount(String tableName, String feild, String condition) {
		int num = 0;
		String sql = "select count("+feild+") from " + tableName + " " + condition;
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pste = null;
		ResultSet res = null;
		conn = getConn();
		try {
			pste = conn.prepareStatement(sql);
			res = pste.executeQuery();
			if (res.next()) {
				num = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(res, pste, conn);// 关闭连接，释放资源
		}
		return num;
	}

	/**
	 * 释放资源，关闭连接
	 * 
	 * @param res
	 * @param pste
	 * @param conn
	 */
	public static void releaseAll(ResultSet res, PreparedStatement pste,
			Connection conn) {
		try {
			if (res != null)
				res.close();
			if (pste != null)
				pste.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Integer isExist(Connection conn, String sql, List<Object> parameters) {
		PreparedStatement pste = null;// 预处理语句
		ResultSet rs = null;// 结果集
		Integer res = null;
		try {
			pste = conn.prepareStatement(sql);
			// 注入参数
			if (parameters != null) {
				for (int i = 0; i < parameters.size(); i++) {
					pste.setObject(i + 1, parameters.get(i));
				}
			}
			rs = pste.executeQuery();// 执行
			while (rs.next()) {
				res = rs.getInt(1);
				System.out.println(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseAll(rs, pste, null);// 关闭连接，释放资源
		}
		return res;
	}

	public static int operUpdate(Connection conn, String sql,
			List<Object> parameterList) throws SQLException {
		PreparedStatement pste = null;
		int res = 0;
		pste = conn.prepareStatement(sql);
		// 注入参数
		if (parameterList != null && parameterList.size() > 0) {
			for (int i = 0; i < parameterList.size(); i++) {
				pste.setObject(i + 1, parameterList.get(i));
			}
		}
		res = pste.executeUpdate();// 执行
		releaseAll(null, pste, null);
		return res;
	}

	public static <T> List<T> operQuery(Connection conn, String sql, List<Object> parameterList,
			Class<T> clas) throws Exception {
		PreparedStatement pste = null;// 预处理语句
		ResultSet rs = null;// 结果集
		List<T> list = new ArrayList<T>();
		pste = conn.prepareStatement(sql);
		// 注入参数
		if (parameterList != null) {
			for (int i = 0; i < parameterList.size(); i++) {
				pste.setObject(i + 1, parameterList.get(i));
			}
		}
		rs = pste.executeQuery();// 执行
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			T m = clas.newInstance();// 利用反射实例化对象
			for (int j = 0; j < rsmd.getColumnCount(); j++) {
				// 从结果集结构中取得字段名
				String col_name = rsmd.getColumnName(j + 1);

				// 从结果集中取得值
				Object value = rs.getObject(col_name);

				Field field = clas.getDeclaredField(col_name);

				field.setAccessible(true);// 类中的成员变量为private,故必须进行此操作
				field.set(m, value);
			}
			list.add(m);
		}
		return list;
	}

}