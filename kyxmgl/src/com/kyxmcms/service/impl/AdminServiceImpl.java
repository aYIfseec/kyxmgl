package com.kyxmcms.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.AdminDao;
import com.kyxmcms.dao.impl.AdminDaoImpl;
import com.kyxmcms.dao.impl.BaseDao;
import com.kyxmcms.entity.Admin;
import com.kyxmcms.entity.Company;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.AdminService;

public class AdminServiceImpl implements AdminService{

	private AdminDao adminDao = new AdminDaoImpl();
	
	public Admin selectUser(String adminName) {
		return adminDao.selectUser(adminName);
	}

	public Admin select(int adminId) {
		return adminDao.selectByPrimaryKey(adminId);
	}
	
	public int getCount(String condition) {
		condition = "'%" + condition + "%'";
		StringBuffer str = new StringBuffer(" where adminRealName like " + condition);
		str.append(" or adminName like " + condition);
		return BaseDao.getCount("t_admin", "adminId", str.toString());
	}

	public List<Admin> getAdmin(String condition, Pagination<Admin> pagination) {
		condition = "'%" + condition + "%'";
		StringBuffer str = new StringBuffer(" where adminRealName like " + condition);
		str.append(" or adminName like " + condition);
		String sql = "select adminId, adminName, adminRealName from t_admin "+ str +" limit ?, ?";
		List<Object> parameterList = new ArrayList<Object>();
		parameterList.add(pagination.getStart());
		parameterList.add(pagination.getPageSize());
		List<Admin> resultList = null;
		try {
			resultList = BaseDao.operQuery(sql, parameterList, Admin.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public boolean updateAdmin(Admin newAdmin) {
		return adminDao.updateByPrimaryKeySelective(newAdmin) > 0;
	}

	public boolean insertAdmin(Admin admin) {
		return adminDao.insert(admin) > 0;
	}

	public boolean delete(int id) {
		return adminDao.deleteByPrimaryKey(id) > 0;
	}

}
