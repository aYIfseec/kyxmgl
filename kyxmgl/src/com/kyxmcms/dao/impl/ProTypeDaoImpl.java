package com.kyxmcms.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.ProTypeDao;
import com.kyxmcms.entity.ProType;

public class ProTypeDaoImpl implements ProTypeDao {

	public List<ProType> getAllProType() {
		String sql = "select proTypeId, proTypeName from t_pro_type";
		List<ProType> proTypeList = null;
		try {
			proTypeList = BaseDao.operQuery(sql, null, ProType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proTypeList;
	}

	public ProType getTypeId(Connection conn, String TypeName) throws Exception {
		String sql = "select proTypeId from t_pro_type where proTypeName = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(TypeName);
		List<ProType> typeList = BaseDao.operQuery(conn, sql, list, ProType.class);
		return typeList.size() > 0 ? typeList.get(0) : null;
	}

}
