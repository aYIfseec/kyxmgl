package com.kyxmcms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Project;

public interface ApplicantDao {
    int deleteByPrimaryKey(String applicantId);

    int insert(Applicant record);
    
    int insert(Connection conn, List<Applicant> apps);

    int insertSelective(Connection conn, Applicant record) throws SQLException;

    Applicant selectByPrimaryKey(Connection conn, String applicantId);

    int updateByPrimaryKeySelective(Applicant record);

    int updateByPrimaryKey(Applicant record);
    
    List<Applicant> selectAll();

	List<Applicant> selectByProId(String proId);

	boolean isExist(String applicantId, Connection conn);

	List<Project> getProjectByApplicantId(Connection conn, String applicantId, int i);

	int deleteById(String string);

	List<Applicant> getApplicantByCondition(String condition);

}