package com.kyxmcms.entity;

public class ProjectCompany {
    private Integer proCompanyId;

    private String proId;

    private String companyId;

    private Integer isMain;

    public Integer getProCompanyId() {
        return proCompanyId;
    }

    public void setProCompanyId(Integer proCompanyId) {
        this.proCompanyId = proCompanyId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }
}