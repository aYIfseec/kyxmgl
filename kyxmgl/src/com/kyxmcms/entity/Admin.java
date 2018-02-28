package com.kyxmcms.entity;

public class Admin {
	private int adminId;
	private String adminName;
	private String adminRealName;
	private String password;
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminRealName() {
		return adminRealName;
	}
	public void setAdminRealName(String adminRealName) {
		this.adminRealName = adminRealName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName
				+ ", adminRealName=" + adminRealName + ", password=" + password
				+ "]";
	}
	
}
