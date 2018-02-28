package com.kyxmcms.service.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.kyxmcms.dao.ApplicantDao;
import com.kyxmcms.dao.CompanyDao;
import com.kyxmcms.dao.ProTypeDao;
import com.kyxmcms.dao.ProjectDao;
import com.kyxmcms.dao.impl.ApplicantDaoImpl;
import com.kyxmcms.dao.impl.BaseDao;
import com.kyxmcms.dao.impl.CompanyDaoImpl;
import com.kyxmcms.dao.impl.ProTypeDaoImpl;
import com.kyxmcms.dao.impl.ProjectDaoImpl;
import com.kyxmcms.entity.ProType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.ProjectService;
/**
 * 
 * @author HuangYK
 *
 * @功能
 */
public class ProjectServiceImpl implements ProjectService {

	private ProjectDao projectDao = new ProjectDaoImpl();
	private ProTypeDao proTypeDao = new ProTypeDaoImpl();
	private CompanyDao companyDao = new CompanyDaoImpl();
	private ApplicantDao applicantDao = new ApplicantDaoImpl();

	public int getAllProjectCount(String condition) {
		return projectDao.getCount(condition);
	}

	public List<Project> getAllProject(String condition, Pagination<Project> pagination) {
		List<Project> list = projectDao.getProject(condition, pagination);
		return list;
	}
	
	public boolean deleteById(Enumeration proIds) {
		String ids = "";
		while(proIds.hasMoreElements()){
            String value = (String)proIds.nextElement();//调用nextElement方法获得元素
            if (!value.equals("value")) {
            	ids += "'" + 
            			value.substring(value.indexOf("[")+1, value.indexOf("]"))
            			+ "', ";
            }
        }
		if (ids.equals("")) {
			return false;
		}
		return projectDao.deleteById(new String(ids.substring(0, ids.length()-2))) > 0;
	}
	
	public boolean isExist(Connection conn, String proNum) {
		if (conn == null) {
			conn = BaseDao.getConn();
			boolean res = projectDao.isExist(conn, proNum) > 0;
			BaseDao.releaseAll(null, null, conn);
			return res;
		}
		return projectDao.isExist(conn, proNum) > 0;
	}

	/**
	 * 事务控制，多表操作
	 */
	public String insertProject(Connection conn, Project project) throws Exception{
		String applicantId = project.getProLeaderId();//负责人
		String lxAppId = project.getLxApplicantsId();//联系人
		String cyAppIdStr = project.getCyApplicantsId();//参与人
	System.out.println(cyAppIdStr);
		String[] cyAppIds = null;
		if (cyAppIdStr != null) {
			cyAppIds = cyAppIdStr.split(",");
		}
		
		String companyId = project.getProCompanyId();//承担单位
		String hzComStr = project.getHzCompanysId();//承担单位
	System.out.println(hzComStr);
		
		boolean isNewConn = false;
		if (conn == null) {
			conn = BaseDao.getConn();
			isNewConn = true;
		}
		String[] hzComIds = null;
		if (hzComStr != null) {
			hzComIds = hzComStr.split(",");
			System.out.println(hzComIds);
			if (!isNewConn) {
				for (int i = 0; i < hzComIds.length; i++) {
					hzComIds[i] = companyDao.getCompanyId(conn, hzComIds[i]).getCompanyId();
				}
			}
		}
		if (!isNewConn) {
			companyId = companyDao.getCompanyId(conn, companyId).getCompanyId();
		}
		
		try {
			int res = 1;
			conn.setAutoCommit(false);// 禁止自动提交
			try {
				if (this.isExist(conn, project.getProNum())) {
					return "exist";
				}
				project.setProId(UUID.randomUUID().toString());
				project.setProLeaderId(applicantId);
				project.setProCompanyId(companyId);
				res = projectDao.insertSelective(conn, project);
				conn.commit();// 提交事务后，插入company才可通过外键约束
			} catch (SQLException e) {
				// 出现异常，回滚
				conn.rollback();
				conn.commit();
				e.printStackTrace();
				return "fail";
			}
			
			//conn = BaseDao.getConn();
			//conn.setAutoCommit(false);
			res = projectDao.insertProCompany(conn, project.getProId(), companyId, hzComIds);
			if (res <= 0) {
				//删除项目
				projectDao.deleteByPrimaryKey(conn, project.getProId());
				conn.commit();
				return "fail";
			}
			
			res = projectDao.insertProApplicant(conn, project.getProId(), applicantId, lxAppId, cyAppIds);
			if (res <= 0) {
				conn.rollback();//先回滚，再删除
				projectDao.deleteByPrimaryKey(conn, project.getProId());
				conn.commit();
				return "fail";
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			return "success";
		} catch (SQLException e) {
			// 出现异常，回滚
			conn.rollback();
			projectDao.deleteByPrimaryKey(conn, project.getProId());
			conn.commit();
			e.printStackTrace();
			return "fail";
		} finally {
			if(conn != null && isNewConn) {
				conn.setAutoCommit(true);
				BaseDao.releaseAll(null, null, conn);
			}
		}
	}

	public List<ProType> getAllProType() {
		return proTypeDao.getAllProType();
	}

	public Project getProjectByProId(String proId) {
		//获得项目基本信息
		return projectDao.selectByPrimaryKey(proId);
	}
	
	//导入
	public Map<String, String> insertProject(List<List<Object>> list) throws Exception {
		// 存放结果，及 不合格的数据 反馈
		Map<String, String> dataMap = new HashMap<String, String>();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = new java.util.Date();
		String data = "";
		
		Connection conn = BaseDao.getConn();
		conn.setAutoCommit(false);

		for (int i = 0; i < list.size(); i++) {
			List<Object> e = list.get(i);
			Project project = new Project();
			try {
				project.setProNum((String) e.get(0));
				project.setProName((String) e.get(1));
				date = sdf.parse((String)e.get(2));
				project.setProStartTime(new Date(date.getTime()));
				date = sdf.parse((String)e.get(3));
				project.setProEndTime(new Date(date.getTime()));
				ProType proType = proTypeDao.getTypeId(conn, (String) e.get(4));
				//抛空指针异常时也算格式错误
				project.setProType(proType.getProTypeId());
				project.setProAscription((String) e.get(5));
				project.setTechSrc((String) e.get(6));
				/****/
				project.setProLeaderId((String) e.get(7));//负责人
				project.setLxApplicantsId((String) e.get(8));//联系人
				project.setCyApplicantsId((String) e.get(9));//参与人
				project.setProCompanyId((String) e.get(10));//承担单位
				project.setHzCompanysId((String) e.get(11));//合作单位
				//经济效益
				project.setIndustryVal(new BigDecimal((String)e.get(12)));
				project.setSaleVal(new BigDecimal((String)e.get(13)));
				project.setProfit(new BigDecimal((String)e.get(14)));
				project.setPayTaxes(new BigDecimal((String)e.get(15)));
				project.setForeignExchange(new BigDecimal((String)e.get(16)));
				project.setAddIndustryVal(new BigDecimal((String)e.get(17)));
				project.setAddSaleVal(new BigDecimal((String)e.get(18)));
				project.setAddProfit(new BigDecimal((String)e.get(19)));
				project.setAddPayTaxes(new BigDecimal((String)e.get(20)));
				project.setAddForeignExchange(new BigDecimal((String)e.get(21)));
				//社会效益
				project.setEmployment(Integer.parseInt((String)e.get(22)));
				project.setPersonnel(Integer.parseInt((String)e.get(23)));
				project.setDoctor(Integer.parseInt((String)e.get(24)));
				project.setMaster(Integer.parseInt((String)e.get(25)));
				project.setSenior(Integer.parseInt((String)e.get(26)));
				project.setMid(Integer.parseInt((String)e.get(27)));
				project.setNewProd(Integer.parseInt((String)e.get(28)));
				project.setNewEqNum(Integer.parseInt((String)e.get(29)));
				project.setNewMaterial(Integer.parseInt((String)e.get(30)));
				project.setNewTechnique(Integer.parseInt((String)e.get(31)));
				project.setNewProcess(Integer.parseInt((String)e.get(32)));
				//项目成果
				project.setPatentApply(Integer.parseInt((String)e.get(33)));
				project.setPatentAdopt(Integer.parseInt((String)e.get(34)));
				project.setInvPatApply(Integer.parseInt((String)e.get(35)));
				project.setInvPatAdopt(Integer.parseInt((String)e.get(36)));
				project.setSftCopyrightApply(Integer.parseInt((String)e.get(37)));
				project.setSftCopyrightAdopt(Integer.parseInt((String)e.get(38)));
				project.setProvincePaper(Integer.parseInt((String)e.get(39)));
				project.setOtherPaper(Integer.parseInt((String)e.get(40)));
				project.setNationalAwards(Integer.parseInt((String)e.get(41)));
				project.setProvincialAwards(Integer.parseInt((String)e.get(42)));
				project.setMunicipalAwards(Integer.parseInt((String)e.get(43)));
				project.setOtherAwards(Integer.parseInt((String)e.get(44)));
				project.setIntts(Integer.parseInt((String)e.get(45)));
				project.setNts(Integer.parseInt((String)e.get(46)));
				project.setIndts(Integer.parseInt((String)e.get(47)));
				project.setEts(Integer.parseInt((String)e.get(48)));
				
			} catch (Exception exception) {
				data += ("第" + (i + 2) + "行，数据格式有误！<br>");
				exception.printStackTrace();
				continue;
			}
			String msg = this.insertProject(conn, project);
			if (msg.equals("exist")) {
				data += ("第" + (i + 2) + "行，系统中已存在！<br>");
			} else if (msg.equals("success")) {
				data += ("第" + (i + 2) + "行，添加成功！<br>");
			} else {
				data += ("第" + (i + 2) + "行，添加失败！<br>");
			}
		}
		
		conn.setAutoCommit(true);
		BaseDao.releaseAll(null, null, conn);
		
		String resStr = "success";
		// 反馈信息
		dataMap.put("data", data);
		dataMap.put("result", resStr);
		return dataMap;
	}
}
