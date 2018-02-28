package com.kyxmcms.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.SystemException;

import org.apache.commons.beanutils.BeanUtils;

import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.Company;
import com.kyxmcms.entity.ProType;
import com.kyxmcms.entity.Project;
import com.kyxmcms.page.Pagination;
import com.kyxmcms.service.ApplicantService;
import com.kyxmcms.service.CompanyService;
import com.kyxmcms.service.ProjectService;
import com.kyxmcms.service.impl.ApplicantServiceImpl;
import com.kyxmcms.service.impl.CompanyServiceImpl;
import com.kyxmcms.service.impl.ProjectServiceImpl;
import com.kyxmcms.util.ExportExcelUtil;
/**
 * 
 * @author HuangYK
 *
 * @date 2017-7
 */
@WebServlet("/home")
public class ProjectServlet extends BaseServlet{
	
	private static final long serialVersionUID = 1L;
	
	private ProjectService projectService = new ProjectServiceImpl();
	private ApplicantService applicantService = new ApplicantServiceImpl();
	private CompanyService companyService = new CompanyServiceImpl();

	public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String condition = "";
		String str = request.getParameter("condition");
		String conditionDispaly = request.getParameter("conditionDispaly");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		
		Pagination<Project> pagination = new Pagination<Project>();
		//默认15条
		//pagination.setPageSize(pageSize);
		//获取前台pagination，不为空则是翻页；为空则为初始。
		String pageNum = request.getParameter("page");
		if (pageNum != null) {
			pagination.setPageNum(Integer.parseInt(pageNum));
		}
		
		pagination.setTotalItemsCount(projectService.getAllProjectCount(condition));
		List<Project> projectList = projectService.getAllProject(condition, pagination);
		pagination.setItems(projectList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("condition", str);
		request.setAttribute("conditionDispaly", conditionDispaly);
	//System.out.println(projectList);
		request.getRequestDispatcher("/WEB-INF/pages/project.jsp").forward(request, response);
	}
	
	//添加项目页面
	public void projectAddPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String proId = request.getParameter("proId");
		Project pro = null;
		List<Applicant> appList = null;
		List<Company> comList = null;
		if (proId != null && !proId.equals("")) {
			//获得项目所有信息
			pro = projectService.getProjectByProId(proId);
			//获得联系人、参与人
			appList = applicantService.getApplicantByProId(proId);
			String cyAppIds = "";
			for (int i = 0; i < appList.size(); i++) {
				cyAppIds += appList.get(i).getApplicantId() + ",";
			}
			pro.setCyApplicantsId(cyAppIds);
			//获得合作单位
			comList = companyService.getCompanyByProId(proId);
			String hzComIds = "";
			for (int i = 0; i < comList.size(); i++) {
				hzComIds += comList.get(i).getCompanyId() + ",";
			}
			pro.setHzCompanysId(hzComIds);
		}
		request.setAttribute("pro", pro);
		request.setAttribute("appList", appList);
		request.setAttribute("comList", comList);
		
		//获取项目类型list
		List<ProType> proTypeList = projectService.getAllProType();
		request.setAttribute("proTypeList", proTypeList);
		//获取归口list ? 看需求
		
		request.getRequestDispatcher("/WEB-INF/pages/projectAdd.jsp").forward(request, response);
	}
	
	//添加项目
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project project = new Project();
		//获得前台数据
		try {
			BeanUtils.populate(project, request.getParameterMap());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
System.out.println(project);
		String returnStr = "fail";
		//插入操作
		try {
			returnStr = projectService.insertProject(null, project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回结果
		response.setContentType("text/html; charset=UTF-8");
		response.getOutputStream().write(returnStr.getBytes("UTF-8"));
		return;
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration proIds = request.getParameterNames();
		boolean res = projectService.deleteById(proIds);
		response.setContentType("text/html; charset=UTF-8");
		if (res) {
			response.getOutputStream().write("success".getBytes("UTF-8"));
		} else {
			response.getOutputStream().write("fail".getBytes("UTF-8"));
		}
	}
	
	//导入页面
	public void importExcelPage(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setAttribute("uploadPath", "upload?value=project");
		request.setAttribute("getExcelPath", "home?value=getExcelTemplate");
		request.getRequestDispatcher("/WEB-INF/pages/uploadExcel.jsp").forward(request, response);
	}
	
	// 导出project
	public void exprotExcel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 搜索条件
		String condition = "";
		String str = request.getParameter("condition");
		if (str != null && !"".equals(str)) {
			condition += "where " + str;
		}
		List<Project> proList = projectService.getAllProject(condition, null);
		String[] title = {"项目编号","项目名称","起始时间","完成时间","项目类型","归属部门","技术来源","负责人","承担单位",
				"累计工业或农业总产值", "累计销售收入", "累计利润总额", "累计缴税总额", "累计创汇额", "新增累计工业或农业总产值", 
				"新增累计销售收入", "新增累计利润总额", "新增累计缴税总额", "新增累计创汇额",
				"增加就业人数", "培养科技创新人才", "取得博士学位人数", "取得硕士学位", "晋升高级职称人数", "晋升中级职称人数",
				"新产品/软件（个）", "新设备（台/件）", "新材料（种）", "新技术", "新工艺（项）",
				"申请专利数", "授权专利数", "发明专利申请数", "发明专利授权数", "软件著作权（项）申请数", "软件著作权（项）授权数",
				"发表论文数（篇）省级以上科技刊物", "发表论文数（篇）其他刊物", "国家级奖项", "省级奖项", "市级奖项",
				"其他类型奖项","制定企业技术标准","制定行业技术标准", "制定国家技术标准", "制定国际技术标准"
		};
		String[] fields = {"proNum", "proName", "proStartTime", "proEndTime", 
				"proTypeName", "proAscription", "techSrc", "proLeaderName", "proCompanyName", 
				"industryVal", "saleVal", "profit", "payTaxes","foreignExchange",
				"addIndustryVal","addSaleVal","addProfit","addPayTaxes","addForeignExchange",
				"employment","personnel","doctor","master","senior","mid","newProd","newEqNum",
				"newMaterial", "newTechnique", "newProcess",
				"patentApply", "patentAdopt", "invPatApply", "invPatAdopt", "sftCopyrightApply",
				"sftCopyrightAdopt", "provincePaper", "otherPaper", "nationalAwards", "provincialAwards",
				"municipalAwards", "otherAwards", "ets", "indts", "nts", "intts" };
		ExportExcelUtil.export(request, response, proList, title, fields,
				"科研项目信息表.xls");
	}

	public void getExcelTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = request.getSession().getServletContext().getRealPath("")
				+ "/download/project.xls";
		ExportExcelUtil.downloadFile(request, response, path, "批量导入项目模板.xls");
	}
}
