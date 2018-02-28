package com.kyxmcms.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kyxmcms.entity.Applicant;
import com.kyxmcms.entity.CountPro;
import com.kyxmcms.service.StatisticsService;
import com.kyxmcms.service.impl.StatisticsServiceImpl;
import com.kyxmcms.util.ExportExcelUtil;


@WebServlet("/statistics")
public class StatisticsServlet extends BaseServlet{
	
	private StatisticsService statisticsService = new StatisticsServiceImpl();
	private static final long serialVersionUID = 4228230618577009819L;
	
	public void main(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String countType = request.getParameter("countType");
		if (countType == null || countType.equals("")) {
			countType = "month";
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
		String start = request.getParameter("start");
		if (start == null || start.equals("")) {
			Date date = new Date();
			Calendar currently = new GregorianCalendar();
			currently.setTime(date);
			currently.add(Calendar.YEAR, -1);
			date = currently.getTime();
			start = sdf.format(date);
		}
		String end = request.getParameter("end");
		if (end == null || end.equals("")) {
			end = sdf.format(new Date());
		}
		
		List<CountPro> list = new ArrayList<CountPro>();
        list = statisticsService.countProject(start, end, countType);
		request.setAttribute("list", list);
		request.getRequestDispatcher("WEB-INF/pages/statistics.jsp").forward(request, response);
	}
	
	// 导出
		public void exprotExcel(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			String countType = request.getParameter("countType");
			if (countType == null || countType.equals("")) {
				countType = "month";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        
			String start = request.getParameter("start");
			if (start == null || start.equals("")) {
				Date date = new Date();
				Calendar currently = new GregorianCalendar();
				currently.setTime(date);
				currently.add(Calendar.YEAR, -1);
				date = currently.getTime();
				start = sdf.format(date);
			}
			String end = request.getParameter("end");
			if (end == null || end.equals("")) {
				end = sdf.format(new Date());
			}
			
			List<CountPro> list = new ArrayList<CountPro>();
	        list = statisticsService.countProject(start, end, countType);
	        
	        String[] title = {"时间","项目数量","累计工业或农业总产值", "累计销售收入", "累计利润总额", "累计缴税总额", "累计创汇额", "新增累计工业或农业总产值", 
					"新增累计销售收入", "新增累计利润总额", "新增累计缴税总额", "新增累计创汇额",
					"增加就业人数", "培养科技创新人才", "取得博士学位人数", "取得硕士学位", "晋升高级职称人数", "晋升中级职称人数",
					"新产品/软件（个）", "新设备（台/件）", "新材料（种）", "新技术", "新工艺（项）",
					"申请专利数", "授权专利数", "发明专利申请数", "发明专利授权数", "软件著作权（项）申请数", "软件著作权（项）授权数",
					"发表论文数（篇）省级以上科技刊物", "发表论文数（篇）其他刊物", "国家级奖项", "省级奖项", "市级奖项",
					"其他类型奖项","制定企业技术标准","制定行业技术标准", "制定国家技术标准", "制定国际技术标准"
			};
			String[] fields = {"title", "count", "industryVal", "saleVal", "profit", "payTaxes","foreignExchange",
					"addIndustryVal","addSaleVal","addProfit","addPayTaxes","addForeignExchange",
					"employment","personnel","doctor","master","senior","mid","newProd","newEqNum",
					"newMaterial", "newTechnique", "newProcess",
					"patentApply", "patentAdopt", "invPatApply", "invPatAdopt", "sftCopyrightApply",
					"sftCopyrightAdopt", "provincePaper", "otherPaper", "nationalAwards", "provincialAwards",
					"municipalAwards", "otherAwards", "ets", "indts", "nts", "intts" };
			
			ExportExcelUtil.export(request, response, list, title, fields,
					"项目统计表.xls");
		}
}
