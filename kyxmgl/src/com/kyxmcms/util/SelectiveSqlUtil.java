package com.kyxmcms.util;

import java.lang.reflect.Field;

/**
 * 
 * @author HuangYK
 *
 */
public class SelectiveSqlUtil {
	
	/**
	 * 获得选择性的insert sql语句
	 * @param tableName
	 * @param obj
	 * @param clas
	 * @return sql
	 */
	public static <T> String getSelectiveInsertSql(String tableName, Object obj, Class<T> clas) {
		String sql = "insert into " + tableName + " (";
		String sql2 = "value(";
		Field[] fields = clas.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Object value = null;
			try {
				value = fields[i].get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null) {
				String name = fields[i].getName();
				sql += name + ", ";
				sql2 += "?, ";
			}
		}
		sql = new String(sql.substring(0, sql.length()-2) + ")");
		sql2 = new String(sql2.substring(0, sql2.length()-2) + ")");
		return sql + sql2;
	}
	
	
	/**
	 * 获得选择性的Update sql语句
	 * @param tableName
	 * @param obj
	 * @param clas
	 * @return sql
	 */
	public static <T> String getSelectiveUpdateSql(String tableName, Object obj, String where ,Class<T> clas) {
		String sql = "update " + tableName + " set ";
		String condition = " where ";
		Field[] fields = clas.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Object value = null;
			try {
				value = fields[i].get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			if (value != null) {
				String name = fields[i].getName();
				if (name.equals(where)) {
					condition += name + "=?";
				} else {
					sql += name + "=?, ";
				}
			}
		}
		sql = new String(sql.substring(0, sql.length()-2));
		return sql + condition;
	}
}
