package com.kyxmcms.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author HuangYK
 *
 * @功能
 */
public class BeanToListUtil {
	/**
	 * 将java bean转为 List<Object>
	 * @param obj
	 * @param clas
	 * @param limit
	 * @return
	 */
	public static <T> List<Object> beanToList(Object obj, Class<T> clas, int limit) {
		Field[] fields = clas.getDeclaredFields();
		if (limit > fields.length) {
			limit = fields.length;
		}
		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < limit; i++) {
			fields[i].setAccessible(true);
			Object value = null;
			try {
				value = fields[i].get(obj);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			list.add(value);
		}
		return list.size() > 0 ? list : null;
	}
	
	/**
	 * 获得java bean中属性不为null的ParameterList
	 * @param obj
	 * @param clas
	 * @return List<Object>
	 */
	public static <T> List<Object> getParameterList(Object obj, Class<T> clas) {
		Field[] fields = clas.getDeclaredFields();
		List<Object> list = new ArrayList<Object>();
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
				list.add(value);
			}
		}
		return list.size() > 0 ? list : null;
	}
	
	/**
	 * 
	 * @param obj
	 * @param lastParam
	 * @param clas
	 * @return
	 */
	public static <T> List<Object> getParameterList(Object obj, String last, Class<T> clas) {
		Field[] fields = clas.getDeclaredFields();
		List<Object> list = new ArrayList<Object>();
		Object lastVal = null;
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
				if (name.equals(last)) {
					lastVal = value;
				} else {
					list.add(value);
				}
			}
		}
		list.add(lastVal);
		return list.size() > 0 ? list : null;
	}
}
