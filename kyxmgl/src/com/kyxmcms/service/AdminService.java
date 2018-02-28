package com.kyxmcms.service;

import java.util.List;

import com.kyxmcms.entity.Admin;
import com.kyxmcms.page.Pagination;

public interface AdminService {

	Admin selectUser(String userName);

	public Admin select(int adminId);
	
	int getCount(String condition);

	List<Admin> getAdmin(String condition, Pagination<Admin> pagination);

	boolean updateAdmin(Admin newAdmin);

	boolean insertAdmin(Admin admin);

	boolean delete(int id);
	
}
