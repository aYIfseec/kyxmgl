package com.kyxmcms.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.CompanyType;

public interface CompanyDao {
	
    /**
     * 添加一个单位信息
     * @param record 一个单位对象
     * @return 添加成功返回1，否则返回0
     */
    int insert(Company record);

    /**
     * 添加一个单位的部分信息
     * @param record 一个单位对象
     * @return 添加成功返回1，否则返回0
     * @throws SQLException 
     */
    int insertSelective(Connection conn, Company record) throws SQLException;

	List<Company> selectByProId(String proId);

	List<CompanyType> getAllComType();

	int isExist(Connection conn, String companyName);

	Company selectById(String companyId);

	int updateCompany(Company com);

	int deleteById(String substring);

	int insert(List<Company> companys);

	List<Company> selectAllCompany(String condition);

	Company getCompanyId(Connection conn, String name);
    

}