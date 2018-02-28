package com.kyxmcms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.transaction.SystemException;

import com.kyxmcms.entity.ProType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;

public interface ProjectService {

	int getAllProjectCount(String condition);

	List<Project> getAllProject(String condition, Pagination<Project> pagination);

	boolean isExist(Connection conn, String proNum);

	String insertProject(Connection conn, Project project) throws Exception;

	List<ProType> getAllProType();

	Project getProjectByProId(String proId);

	Map<String, String> insertProject(List<List<Object>> listob) throws Exception;

	boolean deleteById(Enumeration proIds);
	
}
