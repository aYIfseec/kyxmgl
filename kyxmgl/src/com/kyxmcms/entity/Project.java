package com.kyxmcms.entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 科技项目表
 * @author mi
 *
 */
public class Project {
    private String proId;

    private String proNum;//项目编号

    private String proName;//科研项目名

    private String proLeaderId;//项目负责人id
    
    private String proCompanyId;//承担企业id
    
    private Date proStartTime;//项目起始时间

    private Date proEndTime;//项目完成时间

    private Integer proType;//项目类型

    private String proAscription;//项目归口处室,归属部门

    private String techSrc;//技术来源
    
    /**10+10
     * 经济效益
     */
    private BigDecimal industryVal;//累计工业(农业)产值

    private BigDecimal saleVal;//累计销售收入

    private BigDecimal profit;//累计利润总和

    private BigDecimal payTaxes;//累计缴税综合

    private BigDecimal foreignExchange;//累计创汇综合

    private BigDecimal addIndustryVal;//新增

    private BigDecimal addSaleVal;//新增

    private BigDecimal addProfit;//新增

    private BigDecimal addPayTaxes;//新增

    private BigDecimal addForeignExchange;//新增
    
    
    /**11
     * 社会效益
     */
    private Integer employment;//增加就业人数

    private Integer personnel;//培养科技创新人数

    private Integer doctor;//取得博士学位人数

    private Integer master;//取得硕士学位人数

    private Integer senior;//晋升高级职称人数

    private Integer mid;//晋升中级职称人数

    private Integer newProd;//新产品(软件个数)
    
    private Integer newEqNum;//新设备

    private Integer newMaterial;//新材料

    private Integer newTechnique;//新技术

    private Integer newProcess;//新工艺
    
    
    /**16
     * 项目产生的成果
     */
    private Integer patentApply;//申请专利数

    private Integer patentAdopt;//授权专利数

    private Integer invPatApply;//发明专利申请数

    private Integer invPatAdopt;//发明专利授权数

    private Integer sftCopyrightApply;//软件著作权申请数

    private Integer sftCopyrightAdopt;//软件著作权授权数

    private Integer provincePaper;//发表论文数(省级以上科技刊物)

    private Integer otherPaper;//发表论文数(其他刊物)

    private Integer nationalAwards;//国家级奖项

    private Integer provincialAwards;//省级奖项

    private Integer municipalAwards;//市级奖项

    private Integer otherAwards;//其他类型奖项
    
    private Integer ets;//企业技术标准

    private Integer indts;//行业技术标准

    private Integer nts;//国家技术标准

    private Integer intts;//国际(外国)技术标准
    
    
    /** 以下字段数据库t_project表中不存在，为方便前台到servlet的传值  **/
    private String proLeaderName;//项目负责人姓名
    
    private String lxApplicantsName;//项目联系人姓名
	private String lxApplicantsId;//项目联系人id
    
    private String cyApplicantsName;//项目参与人姓名
    private String cyApplicantsId;//项目参与人id
    
    private String proCompanyName;//承担单位名字
    
    private String hzCompanysName;//合作单位名字
    private String hzCompanysId;//合作单位id
    
    private String proTypeName;//项目类型名
    private String proAscriptionName;//归属部门名
    private String techSrcName;//技术来源名，下拉？
    /** end **/
    
    
    public Project() {}
    
    
    public String getProTypeName() {
		return proTypeName;
	}


	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}


	public String getProAscriptionName() {
		return proAscriptionName;
	}


	public void setProAscriptionName(String proAscriptionName) {
		this.proAscriptionName = proAscriptionName;
	}


	public String getTechSrcName() {
		return techSrcName;
	}


	public void setTechSrcName(String techSrcName) {
		this.techSrcName = techSrcName;
	}


	public Integer getEts() {
		return ets;
	}


	public void setEts(Integer ets) {
		this.ets = ets;
	}


	public Integer getIndts() {
		return indts;
	}


	public void setIndts(Integer indts) {
		this.indts = indts;
	}



	public Integer getNts() {
		return nts;
	}



	public void setNts(Integer nts) {
		this.nts = nts;
	}



	public Integer getIntts() {
		return intts;
	}



	public void setIntts(Integer intts) {
		this.intts = intts;
	}



	public String getProLeaderName() {
		return proLeaderName;
	}

	public void setProLeaderName(String proLeaderName) {
		this.proLeaderName = proLeaderName;
	}

	public String getLxApplicantsName() {
		return lxApplicantsName;
	}

	public void setLxApplicantsName(String lxApplicantsName) {
		this.lxApplicantsName = lxApplicantsName;
	}

	public String getLxApplicantsId() {
		return lxApplicantsId;
	}

	public void setLxApplicantsId(String lxApplicantsId) {
		this.lxApplicantsId = lxApplicantsId;
	}

	public String getCyApplicantsName() {
		return cyApplicantsName;
	}

	public void setCyApplicantsName(String cyApplicantsName) {
		this.cyApplicantsName = cyApplicantsName;
	}

	public String getCyApplicantsId() {
		return cyApplicantsId;
	}

	public void setCyApplicantsId(String cyApplicantsId) {
		this.cyApplicantsId = cyApplicantsId;
	}

	public String getProCompanyName() {
		return proCompanyName;
	}

	public void setProCompanyName(String proCompanyName) {
		this.proCompanyName = proCompanyName;
	}

	public String getHzCompanysName() {
		return hzCompanysName;
	}

	public void setHzCompanysName(String hzCompanysName) {
		this.hzCompanysName = hzCompanysName;
	}

	public String getHzCompanysId() {
		return hzCompanysId;
	}

	public void setHzCompanysId(String hzCompanysId) {
		this.hzCompanysId = hzCompanysId;
	}
    
	public String getProCompanyId() {
		return proCompanyId;
	}

	public void setProCompanyId(String proCompanyId) {
		this.proCompanyId = proCompanyId;
	}

	public String getProId() {
        return proId;
    }

    public void setProId(String string) {
        this.proId = string;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public Date getProStartTime() {
        return proStartTime;
    }

    public void setProStartTime(Date proStartTime) {
        this.proStartTime = proStartTime;
    }

    public Date getProEndTime() {
        return proEndTime;
    }

    public void setProEndTime(Date proEndTime) {
        this.proEndTime = proEndTime;
    }

    public Integer getProType() {
        return proType;
    }

    public void setProType(Integer proType) {
        this.proType = proType;
    }

    public String getProAscription() {
        return proAscription;
    }

    public void setProAscription(String proAscription) {
        this.proAscription = proAscription == null ? null : proAscription.trim();
    }

    public String getTechSrc() {
        return techSrc;
    }

    public void setTechSrc(String techSrc) {
        this.techSrc = techSrc == null ? null : techSrc.trim();
    }

	public String getProLeaderId() {
		return proLeaderId;
	}

	public void setProLeaderId(String proLeaderId) {
		this.proLeaderId = proLeaderId;
	}

	public BigDecimal getIndustryVal() {
		return industryVal;
	}

	public void setIndustryVal(BigDecimal industryVal) {
		this.industryVal = industryVal;
	}

	public BigDecimal getSaleVal() {
		return saleVal;
	}

	public void setSaleVal(BigDecimal saleVal) {
		this.saleVal = saleVal;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getPayTaxes() {
		return payTaxes;
	}

	public void setPayTaxes(BigDecimal payTaxes) {
		this.payTaxes = payTaxes;
	}

	public BigDecimal getForeignExchange() {
		return foreignExchange;
	}

	public void setForeignExchange(BigDecimal foreignExchange) {
		this.foreignExchange = foreignExchange;
	}

	public BigDecimal getAddIndustryVal() {
		return addIndustryVal;
	}

	public void setAddIndustryVal(BigDecimal addIndustryVal) {
		this.addIndustryVal = addIndustryVal;
	}

	public BigDecimal getAddSaleVal() {
		return addSaleVal;
	}

	public void setAddSaleVal(BigDecimal addSaleVal) {
		this.addSaleVal = addSaleVal;
	}

	public BigDecimal getAddProfit() {
		return addProfit;
	}

	public void setAddProfit(BigDecimal addProfit) {
		this.addProfit = addProfit;
	}

	public BigDecimal getAddPayTaxes() {
		return addPayTaxes;
	}

	public void setAddPayTaxes(BigDecimal addPayTaxes) {
		this.addPayTaxes = addPayTaxes;
	}

	public BigDecimal getAddForeignExchange() {
		return addForeignExchange;
	}

	public void setAddForeignExchange(BigDecimal addForeignExchange) {
		this.addForeignExchange = addForeignExchange;
	}

	public Integer getEmployment() {
		return employment;
	}

	public void setEmployment(Integer employment) {
		this.employment = employment;
	}

	public Integer getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Integer personnel) {
		this.personnel = personnel;
	}

	public Integer getDoctor() {
		return doctor;
	}

	public void setDoctor(Integer doctor) {
		this.doctor = doctor;
	}

	public Integer getMaster() {
		return master;
	}

	public void setMaster(Integer master) {
		this.master = master;
	}

	public Integer getSenior() {
		return senior;
	}

	public void setSenior(Integer senior) {
		this.senior = senior;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getNewProd() {
		return newProd;
	}

	public void setNewProd(Integer newProd) {
		this.newProd = newProd;
	}

	public Integer getNewEqNum() {
		return newEqNum;
	}

	public void setNewEqNum(Integer newEqNum) {
		this.newEqNum = newEqNum;
	}

	public Integer getNewMaterial() {
		return newMaterial;
	}

	public void setNewMaterial(Integer newMaterial) {
		this.newMaterial = newMaterial;
	}

	public Integer getNewTechnique() {
		return newTechnique;
	}

	public void setNewTechnique(Integer newTechnique) {
		this.newTechnique = newTechnique;
	}

	public Integer getNewProcess() {
		return newProcess;
	}

	public void setNewProcess(Integer newProcess) {
		this.newProcess = newProcess;
	}

	public Integer getPatentApply() {
		return patentApply;
	}

	public void setPatentApply(Integer patentApply) {
		this.patentApply = patentApply;
	}

	public Integer getPatentAdopt() {
		return patentAdopt;
	}

	public void setPatentAdopt(Integer patentAdopt) {
		this.patentAdopt = patentAdopt;
	}

	public Integer getInvPatApply() {
		return invPatApply;
	}

	public void setInvPatApply(Integer invPatApply) {
		this.invPatApply = invPatApply;
	}

	public Integer getInvPatAdopt() {
		return invPatAdopt;
	}

	public void setInvPatAdopt(Integer invPatAdopt) {
		this.invPatAdopt = invPatAdopt;
	}

	public Integer getSftCopyrightApply() {
		return sftCopyrightApply;
	}

	public void setSftCopyrightApply(Integer sftCopyrightApply) {
		this.sftCopyrightApply = sftCopyrightApply;
	}

	public Integer getSftCopyrightAdopt() {
		return sftCopyrightAdopt;
	}

	public void setSftCopyrightAdopt(Integer sftCopyrightAdopt) {
		this.sftCopyrightAdopt = sftCopyrightAdopt;
	}

	public Integer getProvincePaper() {
		return provincePaper;
	}

	public void setProvincePaper(Integer provincePaper) {
		this.provincePaper = provincePaper;
	}

	public Integer getOtherPaper() {
		return otherPaper;
	}

	public void setOtherPaper(Integer otherPaper) {
		this.otherPaper = otherPaper;
	}

	public Integer getNationalAwards() {
		return nationalAwards;
	}

	public void setNationalAwards(Integer nationalAwards) {
		this.nationalAwards = nationalAwards;
	}

	public Integer getProvincialAwards() {
		return provincialAwards;
	}

	public void setProvincialAwards(Integer provincialAwards) {
		this.provincialAwards = provincialAwards;
	}

	public Integer getMunicipalAwards() {
		return municipalAwards;
	}

	public void setMunicipalAwards(Integer municipalAwards) {
		this.municipalAwards = municipalAwards;
	}

	public Integer getOtherAwards() {
		return otherAwards;
	}

	public void setOtherAwards(Integer otherAwards) {
		this.otherAwards = otherAwards;
	}

	@Override
	public String toString() {
		return "Project [proId=" + proId + ", proNum=" + proNum + ", proName="
				+ proName + ", proLeaderId=" + proLeaderId + ", proStartTime="
				+ proStartTime + ", proEndTime=" + proEndTime + ", proType="
				+ proType + ", proAscription=" + proAscription + ", techSrc="
				+ techSrc + ", industryVal=" + industryVal + ", saleVal="
				+ saleVal + ", profit=" + profit + ", payTaxes=" + payTaxes
				+ ", foreignExchange=" + foreignExchange + ", addIndustryVal="
				+ addIndustryVal + ", addSaleVal=" + addSaleVal
				+ ", addProfit=" + addProfit + ", addPayTaxes=" + addPayTaxes
				+ ", addForeignExchange=" + addForeignExchange
				+ ", employment=" + employment + ", personnel=" + personnel
				+ ", doctor=" + doctor + ", master=" + master + ", senior="
				+ senior + ", mid=" + mid + ", newProd=" + newProd
				+ ", newEqNum=" + newEqNum + ", newMaterial=" + newMaterial
				+ ", newTechnique=" + newTechnique + ", newProcess="
				+ newProcess + ", patentApply=" + patentApply
				+ ", patentAdopt=" + patentAdopt + ", invPatApply="
				+ invPatApply + ", invPatAdopt=" + invPatAdopt
				+ ", sftCopyrightApply=" + sftCopyrightApply
				+ ", sftCopyrightAdopt=" + sftCopyrightAdopt
				+ ", provincePaper=" + provincePaper + ", otherPaper="
				+ otherPaper + ", nationalAwards=" + nationalAwards
				+ ", provincialAwards=" + provincialAwards
				+ ", municipalAwards=" + municipalAwards + ", otherAwards="
				+ otherAwards + "]";
	}
    
}