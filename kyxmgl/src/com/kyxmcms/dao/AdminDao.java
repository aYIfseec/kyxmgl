package com.kyxmcms.dao;


import com.kyxmcms.entity.Admin;

public interface AdminDao {
	
	int deleteByPrimaryKey(int adminId);
	
	int insert(Admin admin);
	
	int insertSelective(Admin admin);
	
	int updateByPrimaryKeySelective(Admin admin);
	
	Admin selectByPrimaryKey(int adminId);
	
	Admin selectUser(String username);
	
}
