package com.kyxmcms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.kyxmcms.dao.StatisticsDao;
import com.kyxmcms.entity.CountPro;

public class StatisticsDaoImpl implements StatisticsDao {

	public List<CountPro> countProject(String start, String end, String countType) {
		StringBuffer condition = new StringBuffer();
		List<CountPro> list = new ArrayList<CountPro>();
		if (countType.equals("month")) {
			condition.append("'%Y年 %m月'");
		} else if(countType.equals("year")) {
			condition.append("'%Y年'");
		} else {
			return list;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DATE_FORMAT(proStartTime,").append(condition).append(") as title, ");
		sql.append("count(*) as count, sum(industryVal) as industryVal, sum(addProfit) as addProfit, ");
		sql.append("sum(saleVal) as saleVal, sum(profit) as profit, sum(payTaxes) as payTaxes, ");
		sql.append("sum(foreignExchange) as foreignExchange, sum(addIndustryVal) as addIndustryVal, ");
		sql.append("sum(addSaleVal) as addSaleVal, sum(addPayTaxes) as addPayTaxes, sum(addForeignExchange) as addForeignExchange, ");
		
		sql.append("sum(employment) as employment, sum(personnel) as personnel, sum(doctor) as doctor, ");
		sql.append("sum(mid) as mid, sum(senior) as senior, sum(master) as master, ");
		sql.append("sum(newMaterial) as newMaterial, sum(newEqNum) as newEqNum, sum(newProd) as newProd, ");
		sql.append("sum(patentApply) as patentApply, sum(newProcess) as newProcess, sum(newTechnique) as newTechnique, ");
		sql.append("sum(invPatAdopt) as invPatAdopt, sum(invPatApply) as invPatApply, sum(patentAdopt) as patentAdopt, ");
		sql.append("sum(provincePaper) as provincePaper, sum(sftCopyrightAdopt) as sftCopyrightAdopt, sum(sftCopyrightApply) as sftCopyrightApply, ");
		sql.append("sum(provincialAwards) as provincialAwards, sum(nationalAwards) as nationalAwards, sum(otherPaper) as otherPaper, ");
		sql.append("sum(ets) as ets, sum(otherAwards) as otherAwards, sum(municipalAwards) as municipalAwards, ");
		sql.append("sum(intts) as intts, sum(nts) as nts, sum(indts) as indts ");
		
		sql.append("from t_project where proStartTime between ? and ? group by title");
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(start);
		parameters.add(end);
		
		try {
			list = BaseDao.operQuery(sql.toString(), parameters, CountPro.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
