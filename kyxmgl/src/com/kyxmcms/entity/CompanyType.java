package com.kyxmcms.entity;

public class CompanyType {
	private Integer companyTypeId;
	
	private String companyTypeName;

	public CompanyType() {
	}
	
	public CompanyType(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	
	public CompanyType(Integer companyTypeId, String companyTypeName) {
		this.companyTypeId = companyTypeId;
		this.companyTypeName = companyTypeName;
	}

	public Integer getCompanyTypeId() {
		return companyTypeId;
	}

	public void setCompanyTypeId(Integer companyTypeId) {
		this.companyTypeId = companyTypeId;
	}

	public String getCompanyTypeName() {
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
	}
	
	
}
