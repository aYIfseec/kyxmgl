package com.kyxmcms.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.kyxmcms.dao.ApplicantDao;
import com.kyxmcms.dao.ProjectDao;
import com.kyxmcms.dao.impl.ApplicantDaoImpl;
import com.kyxmcms.dao.impl.BaseDao;
import com.kyxmcms.dao.impl.ProjectDaoImpl;
import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.ApplicantService;
/**
 * 
 * @author HuangYK
 *
 * @功能
 */
public class ApplicantServiceImpl implements ApplicantService {

	private ApplicantDao applicantDao = new ApplicantDaoImpl();
	private ProjectDao projectDao = new ProjectDaoImpl();
	
	public List<Applicant> getApplicantByProId(String proId) {
		return applicantDao.selectByProId(proId);
	}

	public Map<String, String> insertApplicant(List<List<Object>> list) throws SQLException {
		// 存放合格数据，待插入数据库
		List<Applicant> applicants = new ArrayList<Applicant>();
		// 存放结果，及 不合格的数据 反馈
		Map<String, String> dataMap = new HashMap<String, String>();
		// 存放上传的excel中得applicantId用以excel查重
		Set<String> appIds = new HashSet<String>();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String data = "";
		
		Connection conn = BaseDao.getConn();
		conn.setAutoCommit(false);

		for (int i = 0; i < list.size(); i++) {
			List<Object> e = list.get(i);
			Applicant app = new Applicant();
			try {
				String id = (String) e.get(1);
				if (appIds.contains(id)) {
					data += ("第" + (i + 2) + "行，excel中的重复数据！<br>");
					continue;
				}
				appIds.add(id);
				if (applicantDao.isExist(id, conn)) {
					data += ("第" + (i + 2) + "行， 系统中已存在该数据！<br>");
					continue;
				}
				app.setApplicantId(id);
				app.setApplicantName((String) e.get(0));
				app.setApplicantSex((String) e.get(2));
				date = sdf.parse((String) e.get(3));
				app.setApplicantBirth(new Date(date.getTime()));
				app.setApplicantEdu((String) e.get(4));
				app.setApplicantUniv((String) e.get(5));
				app.setaResDirection((String) e.get(6));
				app.setApplicantTel((String) e.get(7));
				app.setApplicantMailbox((String) e.get(8));
				app.setApplicantInfo((String) e.get(9));
				applicants.add(app);// 将合格数据存放至List
			} catch (Exception exception) {
				data += ("第" + (i + 2) + "行，数据格式有误！<br>");
				exception.printStackTrace();
			}
		}
		// 无合格数据
		if (applicants.size() == 0) {
			data = "无有效数据！<br>" + data;
		}
		// 批量插入
		int res = applicantDao.insert(conn, applicants);
		conn.commit();
		BaseDao.releaseAll(null, null, conn);

		String resStr = "success";
		if (res <= 0) {
			resStr = "fail";
		}
		// 反馈信息
		dataMap.put("data", data);
		dataMap.put("result", resStr);
		return dataMap;
	}

	public int getCount(String condition) {
		return BaseDao.getCount("t_applicant", "applicantId", condition);
	}

	public List<Applicant> getAllApplicant(String condition,
			Pagination<Applicant> pagination, int type) {
		String sql = null;
		if (type == 1) {
			sql = "select * from t_applicant "+condition+" limit ?, ?";
		} else {
			sql = "select applicantId, applicantName from t_applicant order by applicantName limit ?, ?";
		}
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(pagination.getStart());
		parameterList.add(pagination.getPageSize());
		List<Applicant> resultList = null;
		try {
			System.out.println(sql);
			resultList = BaseDao.operQuery(sql, parameterList, Applicant.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public String insertApplicant(Applicant app) throws SQLException {
		String returnStr = "success";
		Connection conn = BaseDao.getConn();
		// 查其是否已存在数据库中
		boolean isExist = applicantDao.isExist(app.getApplicantId(), conn);
		if (isExist) {
			returnStr = "exist";
		} else {
			int res = 0;
			res = applicantDao.insertSelective(conn, app);
			if (res == 0) {
				returnStr = "fail";
			}
		}
		BaseDao.releaseAll(null, null, conn);
		return returnStr;
	}

	public List<Object> getApplicantById(String applicantId) {
		List<Object> list = new ArrayList<Object>();
		Connection conn = BaseDao.getConn();
		
		Applicant app = applicantDao.selectByPrimaryKey(conn, applicantId);
		List<Project> pList = applicantDao.getProjectByApplicantId(conn, applicantId, 0);
		List<Project> pList2 = applicantDao.getProjectByApplicantId(conn, applicantId, 1);
		
		list.add(app);
		list.add(pList);
		list.add(pList2);
		
		BaseDao.releaseAll(null, null, conn);
		return list;
	}

	public int updateApplicant(Applicant app) {
		
		return applicantDao.updateByPrimaryKeySelective(app);
	}

	public boolean deleteById(Enumeration applicantIds) {
		String ids = "";
		//枚举类型只能遍历一次？
		while(applicantIds.hasMoreElements()){
            String value = (String)applicantIds.nextElement();//调用nextElement方法获得元素
            if (!value.equals("value")) {
            	ids += "'" + 
            			value.substring(value.indexOf("[")+1, value.indexOf("]"))
            			+ "', ";
            }
        }
		if (ids.equals("")) {
			return false;
		}
		return applicantDao.deleteById(new String(ids.substring(0, ids.length()-2))) > 0;
	}

	public List<Applicant> getAllApplicant(String condition) {
		return applicantDao.getApplicantByCondition(condition);
	}

}
