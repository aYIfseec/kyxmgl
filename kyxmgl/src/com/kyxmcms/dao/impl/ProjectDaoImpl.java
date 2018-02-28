package com.kyxmcms.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.ProjectDao;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.ProType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.util.BeanToListUtil;

public class ProjectDaoImpl implements ProjectDao {

	public int deleteByPrimaryKey(Connection conn, String proId) throws SQLException {
		String sql = "delete from t_project where proId = '" + proId + "'";
		return BaseDao.operUpdate(conn, sql, null);
	}

	public int insert(Project project) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Connection conn, Project project) throws SQLException {//47个字段
		String sql = "insert into t_project value(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
				", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" +
				", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (conn == null) {
			conn = BaseDao.getConn();
			int res = BaseDao.operUpdate(conn, sql, BeanToListUtil.beanToList(project, Project.class, 47));
			BaseDao.releaseAll(null, null, conn);
			return res;
		}
		return BaseDao.operUpdate(conn, sql, BeanToListUtil.beanToList(project, Project.class, 47));
	}

	public Project selectByPrimaryKey(String proId) {
		String sql = "select proId, proNum, proName, proStartTime, b.applicantName as proLeaderName, "
				+ "proLeaderId, c.companyName as proCompanyName, proCompanyId, "
				+ "proEndTime, proType, proAscription, techSrc, "
				+ "industryVal, saleVal, profit, payTaxes, "
				+ "foreignExchange, addIndustryVal, addSaleVal, "
				+ "addProfit, addPayTaxes, addForeignExchange, "
				+ "employment, personnel, doctor, master, senior, "
				+ "mid, newProd, newEqNum, newMaterial, "
				+ "newTechnique, newProcess, patentApply, "
				+ "patentAdopt, invPatApply, invPatAdopt, "
				+ "sftCopyrightApply, sftCopyrightAdopt, "
				+ "provincePaper, otherPaper, nationalAwards, "
				+ "provincialAwards, municipalAwards, otherAwards, " +
				"ets, indts, nts, intts  "
				+ "from t_project as a, t_applicant as b, t_company as c "
				+ "where proLeaderId = applicantId and proCompanyId = companyId " +
				"and a.proId=?";
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(proId);
		List<Project> resultList = null;
		try {
			resultList = BaseDao.operQuery(sql, parameterList, Project.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList.size() > 0 ? resultList.get(0) : null;
	}

	public int updateByPrimaryKeySelective(Project project) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Project> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Project> selectByCondition(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount(String condition) {
		return BaseDao.getCount("t_project", "proId", condition);
	}

	public List<Project> getProject(String condition, Pagination<Project> pagination) {
		String sql = "select proId, proNum, proName, proStartTime, b.applicantName as proLeaderName, "
				+ "proLeaderId, c.companyName as proCompanyName, proCompanyId, "
				+ "proEndTime, proTypeName, proAscription, techSrc, "
				+ "industryVal, saleVal, profit, payTaxes, "
				+ "foreignExchange, addIndustryVal, addSaleVal, "
				+ "addProfit, addPayTaxes, addForeignExchange, "
				+ "employment, personnel, doctor, master, senior, "
				+ "mid, newProd, newEqNum, newMaterial, "
				+ "newTechnique, newProcess, patentApply, "
				+ "patentAdopt, invPatApply, invPatAdopt, "
				+ "sftCopyrightApply, sftCopyrightAdopt, "
				+ "provincePaper, otherPaper, nationalAwards, "
				+ "provincialAwards, municipalAwards, otherAwards, "
				+ "ets, indts, nts, intts  "
				+ "from t_project as a LEFT JOIN t_applicant as b "
				+ "on proLeaderId = applicantId LEFT JOIN t_company as c "
				+ "on proCompanyId = companyId LEFT JOIN t_pro_type as d on d.proTypeId=a.proType "
				+ condition;
		//order by proStartTime desc
		List<Object> parameterList = new ArrayList<Object>();
		if (pagination != null) {
			sql += " order by proStartTime desc limit ?, ?";
			parameterList.add(pagination.getStart());
			parameterList.add(pagination.getPageSize());
		}
		List<Project> resultList = null;
		try {
			resultList = BaseDao.operQuery(sql, parameterList, Project.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int isExist(Connection conn, String proNum) {
		String sql = "select 1 from t_project where proNum = ? limit 1";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(proNum);
		Integer res = BaseDao.isExist(conn, sql, parameters);
		if (res == null || res == 0) {
			return 0;
		}
		return res;
	}

	public List<List<Project>> getProjectByCompanyId(String companyId) {
		//项目主要承担人还是要存在t_pro_company中
		String sql = "select A.proId, proName " +
				"from t_project as A, t_pro_company as B " +
				"where A.proId=B.proId and companyId=?";
		String sql2 = "select proId, proName from t_project " +
				"where proCompanyId=?";
		List<Object> parameeters = new ArrayList<Object>();
		parameeters.add(companyId);
		List<Project> proList = null;
		try {
			proList = BaseDao.operQuery(sql, parameeters, Project.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<List<Project>> pLists = new ArrayList<List<Project>>();
		pLists.add(proList);
		try {
			proList = BaseDao.operQuery(sql2, parameeters, Project.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pLists.add(proList);
		return pLists;
	}
	
	public int insertProApplicant(Connection conn, String proId, String appId, String lxAppId, String[] appIds) throws SQLException {
		String sql = "insert into t_pro_app(proId, applicantId, applicantType) values";
		String value = "";
		for (String s: appIds) {
			if (s.equals("")) continue;
			value += "('" + proId + "', '";
			value += s + "', "+2+"),";
		}
		if (lxAppId != null && !lxAppId.equals("")) {
			value += "('" + proId + "', '";
			value += lxAppId + "', "+1+"),";
		}
		if (appId != null && !appId.equals("")) {
			value += "('" + proId + "', '";
			value += appId + "', "+0+"),";
		}
		if (value.equals("")) {
			return 0;
		}
		value = new String(value.substring(0, value.length()-1));
	System.out.println(sql+value);
		return BaseDao.operUpdate(conn, sql+value, null);
	}
	
	public int insertProCompany(Connection conn, String proId, String comId, String[] comIds) throws SQLException {
		String sql = "insert into t_pro_company(proId, companyId, isMain) values";
		String value = "";
		for (String s: comIds) {
			if (s.equals("")) continue;
			value += "('" + proId + "', '";
			value += s + "', "+2+"),";
		}
		if (comId != null && !comId.equals("")) {
			value += "('" + proId + "', '";
			value += comId + "', "+1+"),";
		}
		if (value.equals("")) {
			return 0;
		}
		value = new String(value.substring(0, value.length()-1));
	System.out.println(sql+value);
		return BaseDao.operUpdate(conn, sql+value, null);
	}

	public int deleteById(String ids) {
		String sql = "delete from t_project where proId in(" + ids + ")";
		return BaseDao.operUpdate(sql, null);
	}
}
