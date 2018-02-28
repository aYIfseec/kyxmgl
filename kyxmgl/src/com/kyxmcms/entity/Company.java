package com.kyxmcms.entity;

/**
 * 合作单位
 * 
 * @author mi
 * 
 */
public class Company {

	private String companyId;

	private String companyName;// 合作单位名称

	private Integer companyType;// 单位类型

	private String companyAddress;// 单位地址

	private String cResDirection;// 单位研究方向

	private String companyInfo;// 简要信息

	private String companyWebUrl;// 该单位网址
	
	/***/
	private String companyTypeName;// 单位类型名
	
	private Integer isMain;//主要承担单位？
	
	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

	public Company() {}
/*
	public Company(String name) {
		companyName = name;
	}

	

	public Company(String companyName, String companyType,
			String companyAddress, String cResDirection, String companyInfo,
			String companyWebUrl) {
		this.companyName = companyName;
		this.companyType = companyType;
		this.companyAddress = companyAddress;
		this.cResDirection = cResDirection;
		this.companyInfo = companyInfo;
		this.companyWebUrl = companyWebUrl;
	}*/
	
	public Company(String companyId, String companyName, int companyType,
			String companyAddress, String cResDirection, String companyWebUrl, String companyInfo) {
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyType = companyType;
		this.companyAddress = companyAddress;
		this.cResDirection = cResDirection;
		this.companyInfo = companyInfo;
		this.companyWebUrl = companyWebUrl;
	}
	
	public Company(String companyId2, String proCompanyName) {
		this.companyId = companyId2;
		this.companyName = proCompanyName;
	}

	
	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}

	/**
	 * @return the companyWebUrl
	 */
	public String getCompanyWebUrl() {
		return companyWebUrl;
	}

	/**
	 * @param companyWebUrl
	 *            the companyWebUrl to set
	 */
	public void setCompanyWebUrl(String companyWebUrl) {
		this.companyWebUrl = companyWebUrl;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress == null ? null : companyAddress
				.trim();
	}
	
	//7-18下午，小写改为了大写
	public String getCResDirection() {
		return cResDirection;
	}
	//7-18下午，小写改为了大写
	public String getcResDirection() {
		return cResDirection;
	}

	public void setcResDirection(String cResDirection) {
		this.cResDirection = cResDirection == null ? null : cResDirection
				.trim();
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo == null ? null : companyInfo.trim();
	}

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyName="
				+ companyName + ", companyType=" + companyType
				+ ", companyAddress=" + companyAddress + ", cResDirection="
				+ cResDirection + ", companyInfo=" + companyInfo
				+ ", companyWebUrl=" + companyWebUrl + "]";
	}
}