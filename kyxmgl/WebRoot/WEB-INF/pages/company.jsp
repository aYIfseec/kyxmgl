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
table tr td, table tr th{
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
th{
    background-color:#efefef;
}
#andor{
	width:70px; margin-left:15px;float:left;color:#666;font-size:14;
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
				<li class="knowledge"><a href="#">单位管理</a>
					<ul class="sub-menu">
						<br>
						<li><button id="addCompany-btn" class="layui-btn layui-btn-small layui-btn-warm">
								<i class="layui-icon">&#xe61f;</i> 添加单位
							</button>
						</li>
						<br>
						<!-- 导入导出按钮 -->
						<li>
							<button class="layui-btn layui-btn-small layui-btn-normal" id="exportExcel">
								<< 导出</button>
						</li>
						<br>
						<li>
							<button class="layui-btn layui-btn-small layui-btn-normal" id="importExcel">
								导入 >></button>
						</li>
						<br>
						<hr>

						<!-- 搜索 -->
						<select id="searchType" class="layui-input">
							<option value="">-请选择-</option>
							<option value="companyName" id="companyName">单位名称</option>
							<option value="companyTypeName" id="companyTypeName">单位类型</option>
							<option value="companyAddress" id="companyAddress">单位地址</option>
							<option value="cResDirection" id="cResDirection">研究方向</option>
						</select>
						<select id="andor" class="layui-input">
							<option value="">且/或</option>
							<option value="And" id="And">并且</option>
							<option value="Or" id="Or">或者</option>
						</select>
						<br><br>
						<input type="text" placeholder="请输入" class="layui-input" id="value-input" value=""
							style="width:185px;font-size:14;" /><br>
						<li>
							<button id="add-condition" class="layui-btn layui-btn-small layui-btn-warm">
								添加条件</button>
						</li><br>
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
				<div class="f_left">当前位置：合作单位管理-单位列表</div>
			</div>
			<div class="box">
				<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
					<ul class="layui-tab-title">
						<li class="layui-this">单位基本信息</li>
						<li></li>
					</ul>
					<form class="layui-form">
					
					<div class="layui-tab-content">
							<div class="layui-tab-item layui-show" style="height:450px;">
								<!--  style="overflow-y:scroll;height:450px;width:100%;" -->

								<div style="overflow-x:scroll;width:100%;">
									<table class="list-table" id="list-table" style="width:1080px;">
										<thead>
										<colgroup>
											<col style="width:40px;">
											<col style="width:170px;">
											<col style="width:160px;">
											<col style="width:220px;">
											<col style="width:120px;">
											<col style="width:150px;">
											<col style="width:220px;">
										</colgroup>
										<tr>
											<th><input type="checkbox" name="checkAll"
												lay-filter="checkAll" title=" ">
											</th>
											<th><strong>单位名称</strong>
											</th>
											<th><strong>单位类型</strong>
											</th>
											<th><strong>单位地址</strong>
											</th>
											<th><strong>研究方向</strong>
											</th>
											<th><strong>单位网址</strong>
											</th>
											<th><strong>单位简介</strong>
											</th>
										</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when
													test="${pagination.items != null && fn:length(pagination.items) != 0}">
													<c:forEach items="${pagination.items}" var="item1"
														varStatus="statu">
														<tr>
															<td><input type="checkbox"
																name="companyId[${item1.companyId}]"
																lay-filter="checkOne" value="${item1.companyId}"
																title=" ">
																<div class="layui-unselect layui-form-checkbox">
																	<span> </span><i class="layui-icon"></i>
																</div></td>
															<td>${item1.companyName}</td>
															<td>${item1.companyTypeName}</td>
															<td>${item1.companyAddress}</td>
															<td>${item1.cResDirection}</td>
															<td><a href="http://${item1.companyWebUrl}"
																target="_blank">${item1.companyWebUrl}</a></td>
															<td>${item1.companyInfo}</td>
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
layui.use(['element', 'laypage', 'layer', 'form'], function(){
  var element = layui.element() ,
  jq = layui.jquery ,
  form = layui.form() ,
  laypage = layui.laypage;

  /**单击  tr 双击<500ms*/
  var _time = 500;
  jq('#list-table tr').dblclick(function(e){
        clearTimeout(_time);
        var companyId = $(this).find("input[lay-filter='checkOne']").val();
        //alert(companyId);
        layer.open({
      		type: 2,
      		icon: 2,
      		shadeClose: true,
      		area: ['500px','540px'],
      		title: '编辑单位信息',
      		content: '${basePath}company?value=companyAddPage&companyId=' + companyId
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
  
  //新增单位
  jq('#addCompany-btn').click(function(){
    layer.open({
      type: 2,
      icon: 2,
      shadeClose: true,
      area: ['500px','540px'],
      title: '添加单位',
      content: '${basePath}company?value=companyAddPage'
    });
  });
  
  //导入
  jq('#importExcel').click(function(){
    layer.open({
		type:2,
		title: '合作单位批量导入',
		content: ['${basePath}company?value=importExcelPage'],
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
      //alert(jq('#condition').val());
      window.location.href='${basePath}company?value=exprotExcel&condition='
         + encodeURIComponent(jq('#condition').val());
      layer.closeAll('dialog'); //关闭信息框
    });
  });
  
  //添加搜索条件
  jq('#add-condition').click(function(){
  	//先判断已有条件是否为空，为空则不需要先输And/Or
  	var condition = jq('#condition').val();
  	var searchType = jq('#searchType').val();
  	var andOr = jq('#andor').val();
  	var value = jq('#value-input').val();
  	if (condition != "" && condition != null) {
  		if (andOr == "" || searchType == "" || value == "") {
  			layer.msg('请填写完整【并/或，条件，条件值】',
  			 {icon:5,anim: 6,time: 2500}
  			 );
  			return;
  		}
  		var searchTypeDisplay = jq('#'+searchType).html();
  		var andOrDisplay = jq('#'+andOr).html();
  		
  		var str = "\n" + andOrDisplay + "\n";
  		str += searchTypeDisplay+"="+value;
  		jq('#area').val(jq('#area').val()+str);
  		var val = " " + andOr + " ";
  		val += searchType + " like '%" + value + "%'";
  		jq('#condition').val(condition+val);
  	} else {
  		if (searchType == "" || value == "") {
  			layer.msg('请填写完整【条件，条件值】',
  			 {icon:5,anim: 6,time: 2500}
  			 );
  			return;
  		}
  		var searchTypeDisplay = jq('#'+searchType).html();
  		jq('#area').val(searchTypeDisplay+"="+value);
  		jq('#condition').val(searchType + " like '%" + value + "%'");
  	}
  	//alert(jq('#condition').val());
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
	window.location.href = '${basePath}company?value=main&condition='
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
  //console.log(data.field);
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
    layer.confirm('确定删除选中数据?', function(index){
      loading = layer.load(2, {
        shade: [0.2,'#000'] //0.2透明度的白色背景
      });
      var param = data.field;
      jq.get('${basePath}company?value=delete', param, function(data){
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
        location.href = '${basePath}company?value=main&page='
         + e.curr + '&condition=' + encodeURIComponent(jq('#condition').val()) 
         + '&conditionDispaly='+encodeURIComponent(jq('#area').val());
      }
    }
  });
});

</script>


		<!--顶部   静态引入-->
		<%@ include file="bottom.jsp"%>
	</div>
</body>
</html>