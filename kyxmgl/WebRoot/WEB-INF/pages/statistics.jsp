<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'statistics.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="${basePath}resources/layui/css/layui.css" rel="stylesheet">
	<link href="${basePath}resources/css/global.css" rel="stylesheet">
  <style type="text/css">
tr>:first-child{  
    position : relative;  
    /*border : solid #A4D3EE 1px;*/
    background:#fff;
}
  </style>
  </head>
  
  <body>
  	<jsp:include page="top.jsp" flush="true" />
  	
  	<div class="wrap">
		<!--左边导航菜单-->
		<div class="nav">
			<ul class="accordion">
				<li class="knowledge"><a href="#">项目统计</a>
					<ul class="sub-menu">
						<br>
						<!-- 导入导出按钮 -->
						<li>
							<button class="layui-btn layui-btn-small layui-btn-normal" id="exportExcel">
								<< 导出</button>
						</li>
						<br>
						<hr>
						<!-- 统计 -->
						<form  class="layui-form">
							<div class="layui-form-item">
								<label class="layui-form-label">时间区间</label>
								<div class="layui-input-inline">
									<input name="proStartTime" value=""
										class="layui-input" placeholder="年-月-日" id="LAY_demorange_s"
										lay-verify="required"><p style="margin:5px 50px;">至 </p>
									<input name="proEndTime" value=""
										class="layui-input" placeholder="年-月-日" id="LAY_demorange_e"
										lay-verify="required">
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-inline input-custom-width" id="countType">
									<input type="radio" name="countType" value="month" title="按月统计" checked>
									<input type="radio" name="countType" value="year" title="按年统计" >
								</div>
							</div>

						</form>
						<li>
							<button id="search-btn" class="layui-btn layui-btn-small">统计</button>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<!--左边导航菜单 end-->
		
		<!-- 中间内容 -->
		<div class="content">
			<div class="position">
				<div class="f_left">当前位置：项目人员管理-人员列表</div>
			</div>
	<div class="box">
	<div id="freezing" style="overflow-x:scroll;width:100%;" onscroll="javascript:freezing()">
					<table class="list-table" id="list-table" style="width:3250px;">
						<thead>
						<colgroup>
							<col style="width:120px;">
							<col style="width:70px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:100px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:90px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:70px;">
							<col style="width:70px;">
							<col style="width:70px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:110px;">
							<col style="width:110px;">
							<col style="width:100px;">
							<col style="width:100px;">
							<col style="width:120px;">
							<col style="width:90px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:90px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
							<col style="width:80px;">
						</colgroup>
						<tr style="height:45px;" id="tablehead">
							<th><strong>时间</strong>
							</th>
							<th><strong>数量</strong>
							</th>
							<th><strong>累计工/农业总产值</strong>
							</th>
							<th><strong>累计销售收入</strong>
							</th>
							<th><strong>累计利润总额</strong>
							</th>
							<th><strong>累计缴税总额</strong>
							</th>
							<th><strong>累计创汇额</strong>
							</th>
							<th><strong>新增累计工/农业总产值</strong>
							</th>
							<th><strong>新增累计销售收入</strong>
							</th>
							<th><strong>新增累计利润总额</strong>
							</th>
							<th><strong>新增累计缴税总额</strong>
							</th>
							<th><strong>新增累计创汇额</strong>
							</th>

							<th><strong>增加就业人数</strong>
							</th>
							<th><strong>培养科技创新人才</strong>
							</th>
							<th><strong>取得博士学位人数</strong>
							</th>
							<th><strong>取得硕士学位人数</strong>
							</th>
							<th><strong>晋升高级职称人数</strong>
							</th>
							<th><strong>晋升中级职称人数</strong>
							</th>
							<th><strong>新产品/软件(个)</strong>
							</th>
							<th><strong>新设备(台/件)</strong>
							</th>
							<th><strong>新材料(种)</strong>
							</th>
							<th><strong>新技术(项)</strong>
							</th>
							<th><strong>新工艺(项)</strong>
							</th>

							<th><strong>申请专利数(项)</strong>
							</th>
							<th><strong>授权专利数(项)</strong>
							</th>
							<th><strong>发明专利申请数(项)</strong>
							</th>
							<th><strong>发明专利授权数(项)</strong>
							</th>
							<th><strong>软件著作权申请数(项)</strong>
							</th>
							<th><strong>软件著作权授权数(项)</strong>
							</th>
							<th><strong>省级以上科技刊物/论文(篇)</strong>
							</th>
							<th><strong>其他刊物/论文(篇)</strong>
							</th>
							<th><strong>国家级奖项(项)</strong>
							</th>
							<th><strong>省级奖项(项)</strong>
							</th>
							<th><strong>市级奖项(项)</strong>
							</th>
							<th><strong>其它类型奖项(项)</strong>
							</th>
							<th><strong>制定企业技术标准</strong>
							</th>
							<th><strong>制定行业技术标准</strong>
							</th>
							<th><strong>制定国家技术标准</strong>
							</th>
							<th><strong>制定国际技术标准</strong>
							</th>
						</tr>
						</thead>
						<tbody >
							<c:if test="${list != null && fn:length(list) != 0}">
								<c:forEach items="${list}" var="item1" varStatus="statu">
									<tr>
										<td><strong>${item1.title}</strong></td>
										<td>${item1.count}</td>
										<td>${item1.industryVal}</td>
										<td>${item1.saleVal}</td>
										<td>${item1.profit}</td>
										<td>${item1.payTaxes}</td>
										<td>${item1.foreignExchange}</td>
										<td>${item1.addIndustryVal}</td>
										<td>${item1.addSaleVal}</td>
										<td>${item1.addProfit}</td>
										<td>${item1.addPayTaxes}</td>
										<td>${item1.addForeignExchange}</td>

										<td>${item1.employment}</td>
										<td>${item1.personnel}</td>
										<td>${item1.doctor}</td>
										<td>${item1.master}</td>
										<td>${item1.senior}</td>
										<td>${item1.mid}</td>
										<td>${item1.newProd}</td>
										<td>${item1.newEqNum}</td>
										<td>${item1.newMaterial}</td>
										<td>${item1.newTechnique}</td>
										<td>${item1.newProcess}</td>

										<td>${item1.patentApply}</td>
										<td>${item1.patentAdopt}</td>
										<td>${item1.invPatApply}</td>
										<td>${item1.invPatAdopt}</td>
										<td>${item1.sftCopyrightApply}</td>
										<td>${item1.sftCopyrightAdopt}</td>
										<td>${item1.provincePaper}</td>
										<td>${item1.otherPaper}</td>
										<td>${item1.nationalAwards}</td>
										<td>${item1.provincialAwards}</td>
										<td>${item1.municipalAwards}</td>
										<td>${item1.otherAwards}</td>
										<td>${item1.ets}</td>
										<td>${item1.indts}</td>
										<td>${item1.nts}</td>
										<td>${item1.intts}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
	</div>
	</div>
	</div>
</body>
<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
<script src="${basePath}resources/js/jquery.min.js" type="text/javascript"></script>
<script>
layui.use([ 'element', 'form', 'laydate' ],function() {
  var element = layui.element(), form = layui.form(), jq = layui.jquery;
  
  //结束时间>开始时间控制
  var laydate = layui.laydate;
  var start = {
    max: laydate.now(),
    min: '1949-10-01 00:00:00',
    istoday: false,
    choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas; //将结束日的初始值设定为开始日
    }
  };
  var end = {
    max: laydate.now(),
    min: '1949-10-01 00:00:00',
    istoday: false,
    choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  jq('#LAY_demorange_s').click(function(){
    start.elem = this;
    laydate(start);
  });
  jq('#LAY_demorange_e').click(function(){
    end.elem = this;
    laydate(end);
  });
  //结束时间>开始时间控制 end
  
  
  jq('#search-btn').click(function(){
  	var start = jq('#LAY_demorange_s').val();
  	var end = jq('#LAY_demorange_e').val();
  	
  	if (start == "" || end == "") {
  		layer.msg('请选择时间区间',
  			 {icon:5,anim: 6,time: 1500}
  		);
  		return;
  	}
  	var countType = jq('#countType input[name=countType]:checked').val();
  	
	window.location.href = '${basePath}statistics?value=main&countType='
		+countType + '&start=' + start + '&end=' + end;
  });
  
  //导出
  jq('#exportExcel').click(function(){
  	var str = window.location.search;
  	var paramter = str.split("main");
    layer.confirm('确定导出当前条件下所有数据?', function(index){
      window.location.href='${basePath}statistics?value=exprotExcel'+paramter[1];
      layer.closeAll('dialog'); //关闭信息框
    });
  });
  
});

//固定表格首列  
function freezing(){
    $("tr>:first-child").css("left",$("#freezing").scrollLeft()-1);  
    var thWidth=$("#tablehead>:first").width();  
    var step=thWidth/5;//分5次过渡，每step像素过渡一次  
    var scroLeft=$("#freezing").scrollLeft(); //向左滚动的距离  
    var tol=Math.ceil(scroLeft/step);  
    if(scroLeft==0){  
        $("tr>:first-child").removeClass("isScrolling").css("opacity",1);  
    }else if(scroLeft>0&&scroLeft<thWidth){  
        $("tr>:first-child").addClass("isScrolling").css("opacity",0.2*tol);  
    }else{  
        $("tr>:first-child").addClass("isScrolling").css("opacity",1);  
    }  
} 

</script>
<!--底部   静态引入-->
<%@ include file="bottom.jsp"%>
</html>
