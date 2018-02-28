package com.kyxmcms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.CompanyType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;

public interface CompanyService {

	int getCount(String condition);

	List<Company> getAllCompany(String condition, Pagination<Company> pagination, int type);

	List<Company> getCompanyByProId(String proId);

	Company getCompanyById(String companyId);

	List<CompanyType> getAllComType();

	boolean isExist(String companyName, Connection conn);

	Map<String, String> insertCompany(List<List<Object>> list) throws SQLException;

	int updateCompany(Company com);

	boolean deleteById(Enumeration companyIds);

	int insertCompany(Company com);

	List<List<Project>> getJoinProject(String companyId);

	List<Company> getAllCompany(String condition);

}
