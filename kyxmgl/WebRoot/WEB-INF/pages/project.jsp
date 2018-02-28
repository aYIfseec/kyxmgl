<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>宜春市科研合作项目管理系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link href="${basePath}resources/layui/css/layui.css" rel="stylesheet">
<link href="${basePath}resources/css/global.css" rel="stylesheet">

<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>


<style type="text/css">
.sel{ /*选中行的背景颜色*/ 
    background-color:#f1f1f1;
}
table {
table-layout: fixed;
}
table tr td{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
th{
    background-color:#efefef;
}
#area{
	font-size:14;
}
#andor{
	width:70px; margin-left:15px;float:left;color:#666;font-size:14;
}
#compare{
	width:55px; margin-right:0px;float:left;color:#666;font-size:14;
}
#searchType{
	width:100px;float:left;color:#666;font-size:14;
}
#search-btn, #del-condition{
	width:50px;
}
#del-condition{
	margin-left:32px;
}
</style>
</head>


<body>
	<!--顶部  动态引入-->
	<jsp:include page="top.jsp" flush="true" />

	<div class="wrap">
		<!--左边导航菜单-->
		<div class="nav">
			<ul class="accordion">
				<li class="knowledge"><a href="#">科研项目管理</a>
					<ul class="sub-menu">
						<br>
						<li><button id="addProject-btn" class="layui-btn layui-btn-small layui-btn-warm">
								<i class="layui-icon">&#xe61f;</i> 新增项目
							</button>
						</li>
						<br>
						<!-- 导入导出按钮 -->
						<li>
							<button id="exportExcel" class="layui-btn layui-btn-small layui-btn-normal">
								<< 导出</button>
						</li>
						<br>
						<li>
							<button id="importExcel" class="layui-btn layui-btn-small layui-btn-normal">
								导入 >></button>
						</li>
						<br>
						<hr>
						<!-- 高级搜索 字体有待调整 -->
						<select id="searchType" class="layui-input"
							style="width:100px;float:left;">
							<option value="">-请选择-</option>
							<option value="proNum" id="proNum">项目编号</option>
							<option value="proName" id="proName">项目名</option>
							<option value="applicantName" id="applicantName">负责人</option>
							<option value="companyName" id="companyName">承担单位</option>
							<option value="proStartTime" id="proStartTime">起始时间</option>
							<option value="proEndTime" id="proEndTime">结束时间</option>
						</select>
						<select id="andor" class="layui-input">
							<option value="">且/或</option>
							<option value="And" id="And">并且</option>
							<option value="Or" id="Or">或者</option>
						</select><br> <br>
						<input type="text" placeholder="请输入" class="layui-input"
							style="width:185px;font-size:14;"  id="value-input"/>
						<select id="compare" style="display:none;" class="layui-input">
							<option value="<=" id="<="><=</option>
							<option value=">=" id=">=">>=</option>
							<option value="=" id="=">=</option>
						</select>
						<input id="LAY_demorange_s" style="display:none;width:130px;font-size:14;" class="layui-input" placeholder="起始时间 年-月-日">
						<input id="LAY_demorange_e" style="display:none;width:130px;font-size:14;" class="layui-input" placeholder="结束时间 年-月-日">
						<br>
						<li>
							<button id="add-condition" class="layui-btn layui-btn-small layui-btn-warm">
								添加条件</button>
						</li> <br>
						<li>
							<button id="del-condition" class="layui-btn layui-btn-small layui-btn-danger">
								清空</button>
							<button id="search-btn" class="layui-btn layui-btn-small">搜索</button>
						</li><br>
						<textarea id="area" disabled="true" rows="4" cols="28"
							class="layui-textarea">${conditionDispaly}</textarea>
						<input id="condition" type="hidden" value="${condition}">
					</ul>
				</li>
			</ul>
		</div>
		<!--左边导航菜单 end-->
		
		<!-- 中间内容 -->
		<div class="content">
			<div class="position">
				<div class="f_left">当前位置：科研合作项目管理-项目列表</div>
			</div>
			<div class="box">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title">
						<li class="layui-this">项目基本信息</li>
						<li>
						</li>
					</ul>
					<form class="layui-form">
					<div class="layui-tab-content">
						<div class="layui-tab-item layui-show" style="height:500px;">
						<!--  style="overflow-y:scroll;height:450px;width:100%;" -->
								<div style="overflow-x:scroll;width:100%;">
										<table class="list-table" id="list-table" style="width:4250px;">
										<thead>
											<colgroup>
    											<col style="width:40px;"><col style="width:160px;"><col style="width:170px;">
    											<col style="width:120px;"><col style="width:120px;"><col style="width:170px;">
	  											<col style="width:120px;"><col style="width:120px;"><col style="width:90px;">
	  											<col style="width:160px;"><col style="width:90px;"><col style="width:90px;">
    											<col style="width:90px;"><col style="width:90px;"><col style="width:90px;">
  												<col style="width:100px;">
    											<col style="width:90px;"><col style="width:90px;"><col style="width:90px;">
	  											<col style="width:90px;"><col style="width:80px;"><col style="width:80px;">
	  											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:70px;"><col style="width:70px;"><col style="width:70px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:110px;">
	  											<col style="width:110px;"><col style="width:100px;"><col style="width:100px;">
	  											<col style="width:120px;"><col style="width:90px;"><col style="width:80px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:90px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:80px;">
  											</colgroup>
											<tr style="height:45px;">
												<th><input type="checkbox" name="checkAll"
													lay-filter="checkAll" title=" "></th>
												<th><strong>项目编号</strong></th>
												<th><strong>项目名称</strong></th>
												<th class="can_click"><strong>
												<a href="">
												项目起始时间 ▼</a></strong>
												</th>
												<th class="can_click"><strong>
												<a href="">
												项目结束时间 ▼</a></strong>
												</th>
												<th><strong>项目类型</strong></th>
												<th><strong>归口处室</strong></th>
												<th><strong>技术来源</strong></th>
												<th><strong>项目负责人</strong></th>
												<th><strong>承担单位</strong></th>
												<th><strong>累计工/农业总产值</strong></th>
												<th><strong>累计销售收入</strong></th>
												<th><strong>累计利润总额</strong></th>
												<th><strong>累计缴税总额</strong></th>
												<th><strong>累计创汇额</strong></th>
												<th><strong>新增累计工/农业总产值</strong></th>
												<th><strong>新增累计销售收入</strong></th>
												<th><strong>新增累计利润总额</strong></th>
												<th><strong>新增累计缴税总额</strong></th>
												<th><strong>新增累计创汇额</strong></th>

												<th><strong>增加就业人数</strong></th>
												<th><strong>培养科技创新人才</strong></th>
												<th><strong>取得博士学位人数</strong></th>
												<th><strong>取得硕士学位人数</strong></th>
												<th><strong>晋升高级职称人数</strong></th>
												<th><strong>晋升中级职称人数</strong></th>
												<th><strong>新产品/软件(个)</strong></th>
												<th><strong>新设备(台/件)</strong></th>
												<th><strong>新材料(种)</strong></th>
												<th><strong>新技术(项)</strong></th>
												<th><strong>新工艺(项)</strong></th>

												<th><strong>申请专利数(项)</strong></th>
												<th><strong>授权专利数(项)</strong></th>
												<th><strong>发明专利申请数(项)</strong></th>
												<th><strong>发明专利授权数(项)</strong></th>
												<th><strong>软件著作权申请数(项)</strong></th>
												<th><strong>软件著作权授权数(项)</strong></th>
												<th><strong>省级以上科技刊物/论文(篇)</strong></th>
												<th><strong>其他刊物/论文(篇)</strong></th>
												<th><strong>国家级奖项(项)</strong></th>
												<th><strong>省级奖项(项)</strong></th>
												<th><strong>市级奖项(项)</strong></th>
												<th><strong>其它类型奖项(项)</strong></th>
												<th><strong>制定企业技术标准</strong></th>
												<th><strong>制定行业技术标准</strong></th>
												<th><strong>制定国家技术标准</strong></th>
												<th><strong>制定国际技术标准</strong></th>
											</tr>
										</thead>
										
									<!-- 	
									</table>
									</form>
									<form class="layui-form">
										<table class="list-table" id="list-table" style="width:4250px">
										<tbody>
											<colgroup>
    											<col style="width:50px;"><col style="width:160px;"><col style="width:160px;">
    											<col style="width:120px;"><col style="width:120px;"><col style="width:170px;">
	  											<col style="width:120px;"><col style="width:120px;"><col style="width:90px;">
	  											<col style="width:160px;"><col style="width:90px;"><col style="width:90px;">
    											<col style="width:90px;"><col style="width:90px;"><col style="width:90px;">
  												<col style="width:100px;">
    											<col style="width:90px;"><col style="width:90px;"><col style="width:90px;">
	  											<col style="width:90px;"><col style="width:80px;"><col style="width:80px;">
	  											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:70px;"><col style="width:70px;"><col style="width:70px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:100px;">
	  											<col style="width:100px;"><col style="width:100px;"><col style="width:100px;">
	  											<col style="width:120px;"><col style="width:90px;"><col style="width:80px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:90px;">
    											<col style="width:80px;"><col style="width:80px;"><col style="width:80px;">
    											<col style="width:80px;">
  											</colgroup>
  											 -->
											<!-- pagination.items或者pagination.getItems() -->
											<c:choose>
												<c:when
													test="${pagination.items != null && fn:length(pagination.items) != 0}">
													<c:forEach items="${pagination.items}" var="item1"
														varStatus="statu">
														<tr>
															<td><input type="checkbox" name="proId[${item1.proId}]"
																lay-filter="checkOne" value="${item1.proId}" title="">
																<div class="layui-unselect layui-form-checkbox">
																	<span> </span><i class="layui-icon"></i>
																</div></td>
															<td>${item1.proNum}</td>
															<td>${item1.proName}</td>
															<td>${item1.proStartTime}</td>
															<td>${item1.proEndTime}</td>
															<td>${item1.proTypeName}</td>
															<td>${item1.proAscription}</td>
															<td>${item1.techSrc}</td>
															<td>${item1.proLeaderName}</td>
															<td>${item1.proCompanyName}</td>

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
												</c:when>
												<c:otherwise>
													<div class="layui-form-item">
														<tr>
															<td colspan="5">暂无项目</td>
														</tr>
													</div>
												</c:otherwise>
											</c:choose>
										</tbody>
									</table>
								
							</div>
							<div style="float:left;margin:10px 20px 0 0;">
									<button class="layui-btn layui-btn-small layui-btn-danger"
										lay-submit lay-filter="delete">批量删除</button>
								</div>
								<!-- 分页 div -->
								<div id="page" style="float:left"></div>
								<div style="float:left;margin:15px 10px 20px 0;">
									<p>共${pagination.totalItemsCount}条</p>
								</div>
						</div>
					</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 中间内容end -->
<!-- 宽度活动的td js -->
<script src="${basePath}resources/js/table.js" type="text/javascript"></script>

<script type="text/javascript">
layui.use(['element', 'laypage', 'layer', 'form','laydate'], function(){
  var element = layui.element()
  ,jq = layui.jquery
  ,form = layui.form()
  ,laypage = layui.laypage;
  
  var laydate = layui.laydate;
  var start = {
    //min: laydate.now(),
    max: '2099-06-16 23:59:59',
    istoday: false,
    choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas; //将结束日的初始值设定为开始日
    }
  };
  var end = {
    //min: laydate.now(),
    max: '2099-06-16 23:59:59',
    istoday: false,
    choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('LAY_demorange_s').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('LAY_demorange_e').onclick = function(){
    end.elem = this;
    laydate(end);
  }
  
  /**单击    双击<500ms*/
  var _time = 500;
  jq('#list-table tr').dblclick(function(e){
        clearTimeout(_time);
        var proId = $(this).find("input[lay-filter='checkOne']").val();
        //alert(proId);
        layer.open({
      		type: 2,
      		icon: 2,
      		shadeClose: true,
      		maxmin: true,
      		area: ['900px','550px'],
      		title: '编辑项目',
      		content: '${basePath}home?value=projectAddPage&proId=' + proId
    	});
    }).click(function(e){//单击行变色
        clearTimeout(_time);
        var obj = $(this);
        var flag = $(this).hasClass("sel");
        //alert('hello');
        _time = setTimeout(function(){
        //alert('hello2');
    		if(flag){
        		//若已经有样式,点击时删除该样式,取消checkbox的勾(checked属性)  
        		obj.removeClass("sel");
        		//obj.find("input[type='checkbox']").prop("checked",false);   
    		} else {
        		//若没有样式,点击时添加该样式,添加checkbox的勾(checked属性)   
        		obj.addClass("sel");
        		//obj.find("input[type='checkbox']").prop("checked",true);  
    		}  
    	}, 300);
  });  
  
  //新增项目
  jq('#addProject-btn').click(function(){
  	//var id = jq(this).attr('feedback-id');
    layer.open({
      type: 2,
      icon: 2,
      shadeClose: true,
      maxmin: true,
      area: ['900px','550px'],
      title: '添加项目',
      content: '${basePath}home?value=projectAddPage'
    });
  });
  
  //导入
  jq('#importExcel').click(function(){
    layer.open({
		type:2,
		title: '科研项目批量导入',
		content: ['${basePath}home?value=importExcelPage'],
	 	area: ['350px','250px'],
	 	btn: ['取消'],
	 	btnAlign: 'c',//按钮居中
	 	btn2: function(index, layero){
     		layer.close(index);
     	},
	 	shade: 0.1,//背景颜色透明度
	 	shadeClose: false,//是否点击背景关闭, shade不为0
	 	resize: false,
	});
  });
  
  //导出
  jq('#exportExcel').click(function(){
    layer.confirm('确定导出当前条件下所有数据?', function(index){
      window.location.href='${basePath}home?value=exprotExcel&condition='
         + encodeURIComponent(jq('#condition').val());
      layer.closeAll('dialog'); //关闭信息框
    });
  });
  
  //添加搜索条件，特殊情况：条件名为时间
  jq('#add-condition').click(function(){
  	//先判断已有条件是否为空，为空则不需要先输And/Or
  	var condition = jq('#condition').val();
  	var searchType = jq('#searchType').val();
  	var andOr = jq('#andor').val();
  	var value = jq('#value-input').val();
  	var compare = jq('#compare').val();
  	if (condition != "" && condition != null) {
  		var mark = 0;
  		if (searchType == "proStartTime" || searchType == "proEndTime") {
  	  		value = searchType == "proStartTime" ? jq('#LAY_demorange_s').val() : jq('#LAY_demorange_e').val();
  	  		mark = 1;
  	  	}
  		if (andOr == "" || searchType == "" || value == "") {
  			layer.msg('请填写完整【且/或，条件，条件值】',
  			 {icon:5,anim: 6,time: 2500}
  			 );
  			return;
  		}
  		var searchTypeDisplay = jq('#'+searchType).html();
  		var andOrDisplay = jq('#'+andOr).html();
  		
  		var str = "\n" + andOrDisplay + "\n";
  		var val = " " + andOr + " ";
  		if (mark == 1) {
  			str += searchTypeDisplay+compare+"'"+value+"'";
  			val += searchType +compare+ "'"+value+"'";
  		}
  		else {
  			str += searchTypeDisplay+"="+value;
  			val += searchType + " like '%" + value + "%'";
  		}
  		jq('#area').val(jq('#area').val()+str);
  		jq('#condition').val(condition+val);
  	} else {
  		var mark = 0;
  		if (searchType == "proStartTime" || searchType == "proEndTime") {
  	  		value = searchType == "proStartTime" ? jq('#LAY_demorange_s').val() : jq('#LAY_demorange_e').val();
  	  		mark = 1;
  	  	}
  		if (searchType == "" || value == "") {
  			layer.msg('请填写完整【条件，条件值】',
  			 {icon:5,anim: 6,time: 2500}
  			);
  			return;
  		}
  		var searchTypeDisplay = jq('#'+searchType).html();
  		if (mark == 1) {
  			jq('#area').val(searchTypeDisplay+compare+"'"+value+"'");
  			jq('#condition').val(searchType +compare+ "'"+value+"'");
  			return;
  		}
  		jq('#area').val(searchTypeDisplay+"="+value);
  		jq('#condition').val(searchType + " like '%" + value + "%'");
  	}
  	//alert(jq('#condition').val());
  });
  //改变输入框
  jq('#searchType').change(function(){
  	var searchType = jq('#searchType').val();
  	if(searchType == "proStartTime") {
  		jq("#value-input").css('display','none');
  		jq("#LAY_demorange_s").css('display','');
  		jq("#LAY_demorange_e").css('display','none');
  		//jq("#andor").css('display','none');
  		jq("#compare").css('display','');
  	} else if(searchType == "proEndTime") {
  		jq("#LAY_demorange_s").css('display','none');
  		jq("#LAY_demorange_e").css('display','');
  		jq("#value-input").css('display','none');
  		//jq("#andor").css('display','none');
  		jq("#compare").css('display','');
  	} else {
  		jq("#LAY_demorange_s").css('display','none');
  		jq("#LAY_demorange_e").css('display','none');
  		jq("#value-input").css('display','');
  		//jq("#andor").css('display','');
  		jq("#compare").css('display','none');
  	}
  });
  
  //清空搜索条件
  jq('#del-condition').click(function(){
  	jq('#condition').val("");
  	jq('#searchType').val("");
  	jq('#andor').val("");
  	jq('#area').val("");
  });
  
  //搜索
  jq('#search-btn').click(function(){
  	var conditionValue = jq('#condition').val();
  	/*不输条件默认显示所有
  	if (conditionValue == "") {
  		layer.msg('请输入搜索条件',
  			 {icon:5,anim: 6,time: 2500}
  		);
  		//return;
  	}*/
  	conditionValue = encodeURIComponent(conditionValue);
	window.location.href = '${basePath}home?value=main&condition='
		+conditionValue + '&conditionDispaly=' 
		+ encodeURIComponent(jq('#area').val());
  });
  
  //全选
  form.on('checkbox(checkAll)', function(data){
    if(data.elem.checked){
      jq("input[type='checkbox']").prop('checked',true);
    }else{
      jq("input[type='checkbox']").prop('checked',false);
    }
    form.render('checkbox');
  });  

  form.on('checkbox(checkOne)', function(data){
    var is_check = true;
    if(data.elem.checked){
      jq("input[lay-filter='checkOne']").each(function(){
        if(!jq(this).prop('checked')){ is_check = false; }
      });
      if(is_check){
        jq("input[lay-filter='checkAll']").prop('checked',true);
      }
    }else{
      jq("input[lay-filter='checkAll']").prop('checked',false);
    } 
    form.render('checkbox');
  });

  //监听批量删除
  form.on('submit(delete)', function(data){
    //判断是否有选项
    var is_check = false;
    jq("input[lay-filter='checkOne']").each(function(){
      if(jq(this).prop('checked')){ is_check = true; }
    });
    if(!is_check){
      layer.msg('请选择数据', {icon: 2,anim: 6,time: 1000});
      return false;
    }
    //确认删除
    layer.confirm('确定批量删除?', function(index){
      var param = data.field;
      loading = layer.load(2, {
        shade: [0.2,'#000'] //0.2透明度的白色背景
      });
      
      jq.get('${basePath}home?value=delete',param,function(data){
        if (data == "success") {
          layer.close(loading);
          layer.msg('已删除', {icon: 1, time: 1000});
          location.reload();//刷新
        } else {
          layer.close(loading);
          layer.msg('删除失败！', {icon: 2,anim: 6, time: 1000});
        }
      });
    });
    return false;
  });
  
  
/*分页js代码*/
  var pagesNum = ${pagination.totalPageCount};
  var currNum = ${pagination.pageNum};
  laypage({
    cont: $('#page'),//DOM id
    skip: true,//任意跳页
    skin: '#25c484',//分页按钮颜色
    pages: pagesNum, //总页数，只有一页时不会显示分页栏
    groups: 5, //连续显示分页数
    curr: currNum,
    jump: function(e, first){ //触发分页后的回调
      if(!first){ //一定要加此判断，否则初始时会无限刷新
        loading = layer.load(2, {
          shade: [0.2,'#000'] //0.2透明度的白色背景
        });
        location.href = '${basePath}home?value=main&page='
         + e.curr + '&condition=' + encodeURIComponent(jq('#condition').val()) 
         + '&conditionDispaly='+encodeURIComponent(jq('#area').val());
      }
    }
  });
})
</script>


		<!--顶部   静态引入-->
		<%@ include file="bottom.jsp"%>
	</div>
</body>
</html>