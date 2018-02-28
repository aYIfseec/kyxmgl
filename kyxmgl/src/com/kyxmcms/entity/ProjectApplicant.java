package com.kyxmcms.entity;

public class ProjectApplicant {
    private Integer proAppId;

    private String proId;

    private String applicantId;

    private Integer applicantType;

    public Integer getProAppId() {
        return proAppId;
    }

    public void setProAppId(Integer proAppId) {
        this.proAppId = proAppId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId == null ? null : applicantId.trim();
    }

    public Integer getApplicantType() {
        return applicantType;
    }

    public void setApplicantType(Integer applicantType) {
        this.applicantType = applicantType;
    }
}