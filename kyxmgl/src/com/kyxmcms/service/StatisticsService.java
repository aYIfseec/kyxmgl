package com.kyxmcms.service;

import java.util.List;

import com.kyxmcms.entity.CountPro;

public interface StatisticsService {
	
	List<CountPro> countProject(String start, String end, String countType);
	
}
