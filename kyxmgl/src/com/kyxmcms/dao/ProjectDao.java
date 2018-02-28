package com.kyxmcms.dao;



import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kyxmcms.entity.ProType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;

public interface ProjectDao {
	int deleteById(String substring);
	
	public int deleteByPrimaryKey(Connection conn, String proId) throws SQLException;
    
    int insert(Project project);//插入项目

    int insertSelective(Connection conn, Project project) throws SQLException;
    
    Project selectByPrimaryKey(String proId);//通过主键查询项目信息
    
    int updateByPrimaryKeySelective(Project project);//通过主键修改项目信息

    List<Project> selectAll();//查找所有的项目
    
    List<Project> selectByCondition(String sql);//按条件查找项目

	int getCount(String condition);

	List<Project> getProject(String condition, Pagination<Project> pagination);

	int isExist(Connection conn, String proNum);


	List<List<Project>> getProjectByCompanyId(String companyId);
	
	int insertProApplicant(Connection conn, String proId, String appId, String lxAppId, String[] appIds) throws SQLException;
	
	int insertProCompany(Connection conn, String proId, String comId, String[] comIds) throws SQLException;
}