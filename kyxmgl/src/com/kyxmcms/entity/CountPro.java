package com.kyxmcms.entity;

import java.math.BigDecimal;

public class CountPro {
	
	private String title;
	
	private Long count;
	
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
    private BigDecimal employment;//增加就业人数

    private BigDecimal personnel;//培养科技创新人数

    private BigDecimal doctor;//取得博士学位人数

    private BigDecimal master;//取得硕士学位人数

    private BigDecimal senior;//晋升高级职称人数

    private BigDecimal mid;//晋升中级职称人数

    private BigDecimal newProd;//新产品(软件个数)
    
    private BigDecimal newEqNum;//新设备

    private BigDecimal newMaterial;//新材料

    private BigDecimal newTechnique;//新技术

    private BigDecimal newProcess;//新工艺
    
    
    /**16
     * 项目产生的成果
     */
    private BigDecimal patentApply;//申请专利数

    private BigDecimal patentAdopt;//授权专利数

    private BigDecimal invPatApply;//发明专利申请数

    private BigDecimal invPatAdopt;//发明专利授权数

    private BigDecimal sftCopyrightApply;//软件著作权申请数

    private BigDecimal sftCopyrightAdopt;//软件著作权授权数

    private BigDecimal provincePaper;//发表论文数(省级以上科技刊物)

    private BigDecimal otherPaper;//发表论文数(其他刊物)

    private BigDecimal nationalAwards;//国家级奖项

    private BigDecimal provincialAwards;//省级奖项

    private BigDecimal municipalAwards;//市级奖项

    private BigDecimal otherAwards;//其他类型奖项
    
    private BigDecimal ets;//企业技术标准

    private BigDecimal indts;//行业技术标准

    private BigDecimal nts;//国家技术标准

    private BigDecimal intts;//国际(外国)技术标准

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
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

	public BigDecimal getEmployment() {
		return employment;
	}

	public void setEmployment(BigDecimal employment) {
		this.employment = employment;
	}

	public BigDecimal getPersonnel() {
		return personnel;
	}

	public void setPersonnel(BigDecimal personnel) {
		this.personnel = personnel;
	}

	public BigDecimal getDoctor() {
		return doctor;
	}

	public void setDoctor(BigDecimal doctor) {
		this.doctor = doctor;
	}

	public BigDecimal getMaster() {
		return master;
	}

	public void setMaster(BigDecimal master) {
		this.master = master;
	}

	public BigDecimal getSenior() {
		return senior;
	}

	public void setSenior(BigDecimal senior) {
		this.senior = senior;
	}

	public BigDecimal getMid() {
		return mid;
	}

	public void setMid(BigDecimal mid) {
		this.mid = mid;
	}

	public BigDecimal getNewProd() {
		return newProd;
	}

	public void setNewProd(BigDecimal newProd) {
		this.newProd = newProd;
	}

	public BigDecimal getNewEqNum() {
		return newEqNum;
	}

	public void setNewEqNum(BigDecimal newEqNum) {
		this.newEqNum = newEqNum;
	}

	public BigDecimal getNewMaterial() {
		return newMaterial;
	}

	public void setNewMaterial(BigDecimal newMaterial) {
		this.newMaterial = newMaterial;
	}

	public BigDecimal getNewTechnique() {
		return newTechnique;
	}

	public void setNewTechnique(BigDecimal newTechnique) {
		this.newTechnique = newTechnique;
	}

	public BigDecimal getNewProcess() {
		return newProcess;
	}

	public void setNewProcess(BigDecimal newProcess) {
		this.newProcess = newProcess;
	}

	public BigDecimal getPatentApply() {
		return patentApply;
	}

	public void setPatentApply(BigDecimal patentApply) {
		this.patentApply = patentApply;
	}

	public BigDecimal getPatentAdopt() {
		return patentAdopt;
	}

	public void setPatentAdopt(BigDecimal patentAdopt) {
		this.patentAdopt = patentAdopt;
	}

	public BigDecimal getInvPatApply() {
		return invPatApply;
	}

	public void setInvPatApply(BigDecimal invPatApply) {
		this.invPatApply = invPatApply;
	}

	public BigDecimal getInvPatAdopt() {
		return invPatAdopt;
	}

	public void setInvPatAdopt(BigDecimal invPatAdopt) {
		this.invPatAdopt = invPatAdopt;
	}

	public BigDecimal getSftCopyrightApply() {
		return sftCopyrightApply;
	}

	public void setSftCopyrightApply(BigDecimal sftCopyrightApply) {
		this.sftCopyrightApply = sftCopyrightApply;
	}

	public BigDecimal getSftCopyrightAdopt() {
		return sftCopyrightAdopt;
	}

	public void setSftCopyrightAdopt(BigDecimal sftCopyrightAdopt) {
		this.sftCopyrightAdopt = sftCopyrightAdopt;
	}

	public BigDecimal getProvincePaper() {
		return provincePaper;
	}

	public void setProvincePaper(BigDecimal provincePaper) {
		this.provincePaper = provincePaper;
	}

	public BigDecimal getOtherPaper() {
		return otherPaper;
	}

	public void setOtherPaper(BigDecimal otherPaper) {
		this.otherPaper = otherPaper;
	}

	public BigDecimal getNationalAwards() {
		return nationalAwards;
	}

	public void setNationalAwards(BigDecimal nationalAwards) {
		this.nationalAwards = nationalAwards;
	}

	public BigDecimal getProvincialAwards() {
		return provincialAwards;
	}

	public void setProvincialAwards(BigDecimal provincialAwards) {
		this.provincialAwards = provincialAwards;
	}

	public BigDecimal getMunicipalAwards() {
		return municipalAwards;
	}

	public void setMunicipalAwards(BigDecimal municipalAwards) {
		this.municipalAwards = municipalAwards;
	}

	public BigDecimal getOtherAwards() {
		return otherAwards;
	}

	public void setOtherAwards(BigDecimal otherAwards) {
		this.otherAwards = otherAwards;
	}

	public BigDecimal getEts() {
		return ets;
	}

	public void setEts(BigDecimal ets) {
		this.ets = ets;
	}

	public BigDecimal getIndts() {
		return indts;
	}

	public void setIndts(BigDecimal indts) {
		this.indts = indts;
	}

	public BigDecimal getNts() {
		return nts;
	}

	public void setNts(BigDecimal nts) {
		this.nts = nts;
	}

	public BigDecimal getIntts() {
		return intts;
	}

	public void setIntts(BigDecimal intts) {
		this.intts = intts;
	}
    
}
