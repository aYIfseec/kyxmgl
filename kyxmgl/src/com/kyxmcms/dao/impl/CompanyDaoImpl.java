package com.kyxmcms.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.CompanyDao;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.CompanyType;
import com.kyxmcms.util.BeanToListUtil;
import com.kyxmcms.util.SelectiveSqlUtil;

public class CompanyDaoImpl implements CompanyDao {


	public int insert(Company record) {
		String sql = SelectiveSqlUtil.getSelectiveInsertSql("t_company", record, Company.class);
		List<Object> list = BeanToListUtil.getParameterList(record, Company.class);
		return BaseDao.operUpdate(sql, list);
	}

	public int insertSelective(Connection conn, Company record) throws SQLException {
		String sql = SelectiveSqlUtil.getSelectiveInsertSql("t_company", record, Company.class);
		List<Object> list = BeanToListUtil.getParameterList(record, Company.class);
		return BaseDao.operUpdate(conn, sql, list);
	}
	
	public List<Company> selectByProId(String proId) {
		String sql = "select A.companyId, companyName, isMain " +
				"from t_company as A, t_pro_company as B " +
				"where A.companyId=B.companyId and B.proId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(proId);
		List<Company> comList = null;
		try {
			comList = BaseDao.operQuery(sql, list, Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comList;
	}

	public List<CompanyType> getAllComType() {
		String sql = "select companyTypeId, companyTypeName from t_company_type";
		List<CompanyType> list = null;
		try {
			list = BaseDao.operQuery(sql, null, CompanyType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int isExist(Connection conn, String companyName) {
		String sql = "select 1 from t_company where companyName = ? limit 1";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(companyName);
		Integer res = BaseDao.isExist(conn, sql, parameters);
		if (res == null || res == 0) {
			return 0;
		}
		return res;
	}

	public Company selectById(String companyId) {
		String sql = "select * from t_company where companyId = ?";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(companyId);
		List<Company> comList = null;
		try {
			comList = BaseDao.operQuery(sql, parameters, Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comList.size() > 0 ? comList.get(0) : null;
	}

	public int updateCompany(Company com) {
		String sql = SelectiveSqlUtil.getSelectiveUpdateSql("t_company", com, "companyId", Company.class);
		List<Object> list = BeanToListUtil.getParameterList(com, "companyId",  Company.class);
		return BaseDao.operUpdate(sql, list);
	}

	public int deleteById(String ids) {
		String sql = "delete from t_company where companyId in(" + ids + ")";
		return BaseDao.operUpdate(sql, null);
	}

	public int insert(List<Company> companys) {
		String sql = "insert into t_company(companyId, " +
				"companyName, companyType, companyAddress, " +
				"cResDirection, companyInfo, companyWebUrl) values";
		String value = "";
		for (Company com: companys) {
			value += "('" + com.getCompanyId() + "', '";
			value += com.getCompanyName() + "', ";
			value += com.getCompanyType() + ", '";
			value += com.getCompanyAddress() + "', '";
			value += com.getcResDirection() + "', '";
			value += com.getCompanyInfo() + "', '";
			value += com.getCompanyWebUrl() + "'),";
		}
		if (value.equals("")) {
			return 0;
		}
		value = new String(value.substring(0, value.length()-1));
	System.out.println(sql+value);
		return BaseDao.operUpdate(sql+value, null);
	}

	public List<Company> selectAllCompany(String condition) {
		String sql = "select companyId, companyName, companyTypeName, " +
				"companyAddress, cResDirection, companyInfo, companyWebUrl, companyType " +
				"from t_company as A left join t_company_type as B " +
				"on A.companyType = B.companyTypeId " + condition;
		List<Company> resultList = null;
		try {
			resultList = BaseDao.operQuery(sql, null, Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public Company getCompanyId(Connection conn, String name) {
		String sql = "select companyId from t_company where companyName = ?";
		List<Object> p = new ArrayList<Object>();
		p.add(name);
		List<Company> list = null;
		try {
			list = BaseDao.operQuery(conn, sql, p, Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list.size() > 0 ? list.get(0) : null;
	}
}
