package com.kyxmcms.entity;

public class ProType {
	
	private Integer proTypeId;
	private String proTypeName;
	
	public ProType() {
	}
	
	public ProType(Integer proTypeId, String proTypeName) {
		this.proTypeId = proTypeId;
		this.proTypeName = proTypeName;
	}
	public Integer getProTypeId() {
		return proTypeId;
	}
	public void setProTypeId(Integer proTypeId) {
		this.proTypeId = proTypeId;
	}
	public String getProTypeName() {
		return proTypeName;
	}
	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}
	@Override
	public String toString() {
		return "ProType [proTypeId=" + proTypeId + ", proTypeName="
				+ proTypeName + "]";
	}
	
}
