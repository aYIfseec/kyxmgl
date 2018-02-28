package com.kyxmcms.service.impl;

import java.util.List;

import com.kyxmcms.dao.StatisticsDao;
import com.kyxmcms.dao.impl.StatisticsDaoImpl;
import com.kyxmcms.entity.CountPro;
import com.kyxmcms.service.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {
	public StatisticsDao statisticsDao = new StatisticsDaoImpl();
	public List<CountPro> countProject(String start, String end, String countType) {
		return statisticsDao.countProject(start, end, countType);
	}

}
