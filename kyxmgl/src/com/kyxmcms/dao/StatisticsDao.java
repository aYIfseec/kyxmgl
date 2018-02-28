package com.kyxmcms.dao;

import java.util.List;

import com.kyxmcms.entity.CountPro;

public interface StatisticsDao {
	
	List<CountPro> countProject(String condition, String end, String countType);
}
