package com.kyxmcms.dao;

import java.sql.Connection;
import java.util.List;

import com.kyxmcms.entity.ProType;

public interface ProTypeDao {
	List<ProType> getAllProType();

	ProType getTypeId(Connection conn, String string) throws Exception;
}
