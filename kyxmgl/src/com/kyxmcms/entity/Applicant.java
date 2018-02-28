package com.kyxmcms.entity;

import java.sql.Date;

/**
 * 项目负责人
 * @author mi
 *
 */
public class Applicant {
    private String applicantId;

    private String applicantName;

    private String applicantSex;

    private Date applicantBirth;

    private String applicantEdu;//负责人学历

    private String applicantUniv;//毕业院校

    private String aResDirection;//研究方向

    private String applicantInfo;//简介

    private String applicantTel;//电话

    private String applicantMailbox;//邮箱

    private String applicantImgurl;
    
    private Integer applicantType;//类型

    public Integer getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(Integer applicantType) {
		this.applicantType = applicantType;
	}

	public Applicant() {}
    
    public Applicant(String applicantId2, String proLeaderName) {
		this.applicantId = applicantId2;
		this.applicantName = proLeaderName;
	}

	public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName == null ? null : applicantName.trim();
    }

    public String getApplicantSex() {
        return applicantSex;
    }

    public void setApplicantSex(String applicantSex) {
        this.applicantSex = applicantSex == null ? null : applicantSex.trim();
    }

    public Date getApplicantBirth() {
        return applicantBirth;
    }

    public void setApplicantBirth(Date applicantBirth) {
        this.applicantBirth = applicantBirth;
    }

    public String getApplicantEdu() {
        return applicantEdu;
    }

    public void setApplicantEdu(String applicantEdu) {
        this.applicantEdu = applicantEdu == null ? null : applicantEdu.trim();
    }

    public String getApplicantUniv() {
        return applicantUniv;
    }

    public void setApplicantUniv(String applicantUniv) {
        this.applicantUniv = applicantUniv == null ? null : applicantUniv.trim();
    }

    public String getaResDirection() {
        return aResDirection;
    }

    public void setaResDirection(String aResDirection) {
        this.aResDirection = aResDirection == null ? null : aResDirection.trim();
    }

    public String getApplicantInfo() {
        return applicantInfo;
    }

    public void setApplicantInfo(String applicantInfo) {
        this.applicantInfo = applicantInfo == null ? null : applicantInfo.trim();
    }

    public String getApplicantTel() {
        return applicantTel;
    }

    public void setApplicantTel(String applicantTel) {
        this.applicantTel = applicantTel == null ? null : applicantTel.trim();
    }

    public String getApplicantMailbox() {
        return applicantMailbox;
    }

    public void setApplicantMailbox(String applicantMailbox) {
        this.applicantMailbox = applicantMailbox == null ? null : applicantMailbox.trim();
    }

    public String getApplicantImgurl() {
        return applicantImgurl;
    }

    public void setApplicantImgurl(String applicantImgurl) {
        this.applicantImgurl = applicantImgurl == null ? null : applicantImgurl.trim();
    }
}