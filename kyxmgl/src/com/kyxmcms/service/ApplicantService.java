package com.kyxmcms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;

public interface ApplicantService {

	List<Applicant> getApplicantByProId(String proId);

	Map<String, String> insertApplicant(List<List<Object>> list) throws SQLException;
	
	String insertApplicant(Applicant app) throws SQLException;

	int getCount(String condition);

	List<Applicant> getAllApplicant(String string, Pagination<Applicant> pagination, int selectType);

	List<Object> getApplicantById(String applicantId);

	int updateApplicant(Applicant app);

	boolean deleteById(Enumeration applicantIds);

	List<Applicant> getAllApplicant(String condition);

}
