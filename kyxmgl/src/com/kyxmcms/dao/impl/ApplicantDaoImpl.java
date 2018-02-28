package com.kyxmcms.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.ApplicantDao;
import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.Project;
import com.kyxmcms.util.BeanToListUtil;
import com.kyxmcms.util.SelectiveSqlUtil;

public class ApplicantDaoImpl implements ApplicantDao {

	public int deleteByPrimaryKey(String applicantId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(Applicant record) {
		/*String sql = "insert into t_applicant (applicant_name,applicant_sex,applicant_birth,applicant_edu," +
				"applicant_univ,a_res_direction,applicant_info,applicant_tel,applicant_mailbox,applicant_imgurl) values(?,?,?,?,?,?,?,?,?,?)";
		return BaseDao.operUpdate(sql, BeanToListUtil.beanToList(record, Applicant.class, limit));*/
		return 0;
	}

	public int insertSelective(Connection conn, Applicant record) throws SQLException {
		String sql = SelectiveSqlUtil.getSelectiveInsertSql("t_applicant", record, Applicant.class);
		List<Object> list = BeanToListUtil.getParameterList(record, Applicant.class);
		return BaseDao.operUpdate(conn, sql, list);
	}

	public Applicant selectByPrimaryKey(Connection conn, String applicantId) {
		String sql = "select *  from t_applicant where applicantId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(applicantId);
		List<Applicant> appList = null;
		try {
			appList = BaseDao.operQuery(conn, sql, list, Applicant.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appList.size() > 0 ? appList.get(0) : null;
	}

	public int updateByPrimaryKeySelective(Applicant record) {
		String sql = SelectiveSqlUtil.getSelectiveUpdateSql("t_applicant", record, "applicantId", Applicant.class);
		List<Object> list = BeanToListUtil.getParameterList(record, "applicantId",  Applicant.class);
		return BaseDao.operUpdate(sql, list);
	}

	public int updateByPrimaryKey(Applicant record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Applicant> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Applicant> selectByProId(String proId) {
		String sql = "select A.applicantId, applicantName, applicantType " +
				"from t_applicant as A, t_pro_app as B " +
				"where A.applicantId=B.applicantId and B.proId=?";
		List<Object> list = new ArrayList<Object>();
		list.add(proId);
		List<Applicant> appList = null;
		try {
			appList = BaseDao.operQuery(sql, list, Applicant.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appList;
	}

	public boolean isExist(String applicantId, Connection conn) {
		String sql = "select 1 from t_applicant where applicantId = ? limit 1";
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(applicantId);
		Integer res = BaseDao.isExist(conn, sql, parameters);
		if (res == null || res == 0) {
			return false;
		}
		return res > 0;
	}

	public List<Project> getProjectByApplicantId(Connection conn,
			String applicantId, int type) {
		String sql = "select A.proId, proName " +
				"from t_project as A, t_pro_app as B " +
				"where A.proId=B.proId and applicantId=?";
		String sql2 = "select proId, proName from t_project " +
				"where proLeaderId=?";
		List<Object> parameeters = new ArrayList<Object>();
		parameeters.add(applicantId);
		List<Project> proList = null;
		try {
			if (type == 1) {
				proList = BaseDao.operQuery(conn, sql, parameeters, Project.class);
			} else {
				proList = BaseDao.operQuery(conn, sql2, parameeters, Project.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proList;
	}

	public int deleteById(String ids) {
		String sql = "delete from t_applicant where applicantId in(" + ids + ")";
		return BaseDao.operUpdate(sql, null);
	}

	public List<Applicant> getApplicantByCondition(String condition) {
		String sql = "select * from t_applicant " + condition;
		List<Applicant> resultList = null;
		try {
			resultList = BaseDao.operQuery(sql, null, Applicant.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public int insert(Connection conn, List<Applicant> apps) {
		String sql = "insert into t_applicant(applicantId, " +
				"applicantName, applicantSex, applicantBirth, " +
				"applicantEdu, applicantUniv, aResDirection, " +
				"applicantTel, applicantMailbox, applicantInfo) values";
		String value = "";
		for (Applicant app: apps) {
			value += "('" + app.getApplicantId() + "', '";
			value += app.getApplicantName() + "', '";
			value += app.getApplicantSex() + "', '";
			value += app.getApplicantBirth() + "', '";
			value += app.getApplicantEdu() + "', '";
			value += app.getApplicantUniv() + "', '";
			value += app.getaResDirection() + "', '";
			value += app.getApplicantTel() + "', '";
			value += app.getApplicantMailbox() + "', '";
			value += app.getApplicantInfo() + "'),";
		}
		if (value.equals("")) {
			return 0;
		}
		value = new String(value.substring(0, value.length()-1));
	System.out.println(sql+value);
		try {
			return BaseDao.operUpdate(conn, sql+value, null);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	

}
