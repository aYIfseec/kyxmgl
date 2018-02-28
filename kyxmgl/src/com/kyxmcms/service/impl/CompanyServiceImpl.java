package com.kyxmcms.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.kyxmcms.dao.CompanyDao;
import com.kyxmcms.dao.ProjectDao;
import com.kyxmcms.dao.impl.BaseDao;
import com.kyxmcms.dao.impl.CompanyDaoImpl;
import com.kyxmcms.dao.impl.ProjectDaoImpl;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.CompanyType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.CompanyService;
/**
 * 
 * @author HuangYK
 *
 * @功能
 */
public class CompanyServiceImpl implements CompanyService {

	private CompanyDao companyDao = new CompanyDaoImpl();
	private ProjectDao projectDao = new ProjectDaoImpl();
	
	public int getCount(String condition) {
		return BaseDao.getCount("t_company", "companyId", condition);
	}

	public List<Company> getAllCompany(String condition, Pagination<Company> pagination, int type) {
		String sql = null;
		if (type == 1) {
			sql = "select companyId, companyName, companyTypeName, " +
				"companyAddress, cResDirection, companyInfo, companyWebUrl, companyType " +
				"from t_company as A left join t_company_type as B " +
				"on A.companyType = B.companyTypeId " +
				condition +
				" limit ?, ?";
		} else {
			sql = "select companyId, companyName from t_company order by companyName limit ?, ?";
		}
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(pagination.getStart());
		parameterList.add(pagination.getPageSize());
		List<Company> resultList = null;
		try {
			System.out.println(sql);
			resultList = BaseDao.operQuery(sql, parameterList, Company.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public List<Company> getCompanyByProId(String proId) {
		return companyDao.selectByProId(proId);
	}

	public Company getCompanyById(String companyId) {
		return companyDao.selectById(companyId);
	}

	public List<CompanyType> getAllComType() {
		return companyDao.getAllComType();
	}

	public boolean isExist(String companyName, Connection conn) {
		int res = 0;
		if (conn == null) {
			conn = BaseDao.getConn();
			res = companyDao.isExist(conn, companyName);
			BaseDao.releaseAll(null, null, conn);
		} else {
			res = companyDao.isExist(conn, companyName);
		}
		return res > 0;
	}

	public int insertCompany(Company com) {
		com.setCompanyId(UUID.randomUUID().toString());
		return companyDao.insert(com);
	}

	public int updateCompany(Company com) {
		return companyDao.updateCompany(com);
	}

	public boolean deleteById(Enumeration companyIds) {
		String ids = "";
		//枚举类型只能遍历一次？
		while(companyIds.hasMoreElements()){
            String value = (String)companyIds.nextElement();//调用nextElement方法获得元素
            if (!value.equals("value")) {
            	ids += "'" + 
            			value.substring(value.indexOf("[")+1, value.indexOf("]"))
            			+ "', ";
            }
        }
		if (ids.equals("")) {
			return false;
		}
		return companyDao.deleteById(new String(ids.substring(0, ids.length()-2))) > 0;
	}

	public Map<String, String> insertCompany(List<List<Object>> list) throws SQLException {
		//存放合格数据，待插入数据库
		List<Company> companys = new ArrayList<Company>();
		//存放结果，及 不合格的数据 反馈
		Map<String, String> dataMap = new HashMap<String, String>();
		//存放上传的excel中得单位名称用以excel查重
		Set<String> names = new HashSet<String>();
		String data = "";
		
		Connection conn = BaseDao.getConn();
		conn.setAutoCommit(false);
		
		for (int i = 0; i < list.size(); i++) {
			List<Object> e = list.get(i);
			try {
				String id = UUID.randomUUID().toString();
				String name = (String) e.get(0);
				if (names.contains(name)) {
					data += ("第" + (i+2) + "行，excel中的重复数据！<br>");
					//excel序号1开始，第一行为表头，故+2
					continue;
				}
				names.add(name);
				if (this.isExist(name, conn)) {
					data += ("第" + (i+2) + "行， 系统中已存在该数据！<br>");
					continue;
				}
				int type = Integer.parseInt((String) e.get(1));
				// 看需求，excel表中类型可否直接填数值
				// 要求填中文类型的话从数据库读取List<> --> Map 根据键值判断
				String address = (String) e.get(2);
				String direction = (String) e.get(3);
				String webUrl = (String) e.get(4);
				String info = (String) e.get(5);
				Company com = new Company(id, name, type, address, direction,
						webUrl, info);
				companys.add(com);//将合格数据存放至List
			} catch (Exception exception) {
				data += ("第" + (i+2) + "行，数据格式有误！<br>");
			}
		}
		conn.commit();//提交事务
		conn.setAutoCommit(true);
		BaseDao.releaseAll(null, null, conn);
		//无合格数据
		if (companys.size() == 0) {
			data = "无有效数据！<br>"+data;
		}
		//批量插入
		int res = companyDao.insert(companys);
		
		String resStr = "success";
		if (res <= 0) {
			resStr = "fail";
		}
		//反馈信息
		dataMap.put("data", data);
		dataMap.put("result", resStr);
		return dataMap;
	}

	public List<List<Project>> getJoinProject(String companyId) {
		return projectDao.getProjectByCompanyId(companyId);
	}
	
	public List<Company> getAllCompany(String condition) {
		return companyDao.selectAllCompany(condition);
	}
}
