package com.kyxmcms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.AdminDao;
import com.kyxmcms.entity.Admin;

public class AdminDaoImpl implements AdminDao {

	public int deleteByPrimaryKey(int adminId) {
		String sql = "delete from t_admin where adminId = ?";
		List<Object> list = new ArrayList<Object>(1);
		list.add(adminId);
		return BaseDao.operUpdate(sql, list);
	}

	public int insert(Admin admin) {
		String sql = "insert into t_admin value(0,?,?,?)";
		List<Object> list = new ArrayList<Object>(1);
		list.add(admin.getAdminName());
		list.add(admin.getAdminRealName());
		list.add(admin.getPassword());
		return BaseDao.operUpdate(sql, list);
	}

	public int insertSelective(Admin admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeySelective(Admin admin) {
		String sql = "update t_admin set password = ? where adminId=?";
		List<Object> list = new ArrayList<Object>(2);
		list.add(admin.getPassword());
		list.add(admin.getAdminId());
		return BaseDao.operUpdate(sql, list);
	}

	public Admin selectByPrimaryKey(int adminId) {
		String sql = "select * from t_admin where adminId = ?";
		List<Object> list = new ArrayList<Object>(1);
		list.add(adminId);
		List<Admin> admins = null;
		try {
			admins = BaseDao.operQuery(sql, list, Admin.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins.size() > 0 ? admins.get(0) : null;
	}

	public Admin selectUser(String adminName) {
		String sql = "select * from t_admin where adminName = ?";
		List<Object> list = new ArrayList<Object>(1);
		list.add(adminName);
		List<Admin> admins = null;
		try {
			admins = BaseDao.operQuery(sql, list, Admin.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admins.size() > 0 ? admins.get(0) : null;
	}

}
