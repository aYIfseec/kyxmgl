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
</style>
</head>


<body>
	<!--顶部  动态引入-->
	<jsp:include page="top.jsp" flush="true" />

	<div class="wrap">
		<!--左边导航菜单-->
		<div class="nav">
			<ul class="accordion">
				<li class="knowledge"><a href="#">系统用户管理</a>
					<ul class="sub-menu">
						<br>
						<li><button id="addApplicant-btn" class="layui-btn layui-btn-small layui-btn-warm">
								<i class="layui-icon">&#xe61f;</i> 添加人员
							</button>
						</li><br><br>
						<hr><br>
						
						<input type="text" placeholder="关键字" class="layui-input" id="key" value="${key}"
							style="width:185px;font-size:14;" /><br>
						<li><button id="search-btn" class="layui-btn layui-btn-small">搜索用户</button>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<!--左边导航菜单 end-->
		
		<!-- 中间内容 -->
		<div class="content">
			<div class="position">
				<div class="f_left">当前位置：系统用户-</div>
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
								<div style="overflow-x:scroll;/*width:590px;*/">
									<table class="list-table" id="list-table" style="/*width:590px;*/">
										<thead>
										<colgroup>
											<!-- 
											<col style="width:40px;">
											<col style="width:170px;">
											<col style="width:160px;">
											<col style="width:220px;">
											 -->
										</colgroup>
										<tr><!--  
											<th><input type="checkbox" name="checkAll"
												lay-filter="checkAll" title=" ">
											</th>-->
											<th><strong>账号</strong>
											</th>
											<th><strong>用户名</strong>
											</th>
											<th><strong>操作</strong>
											</th>
										</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when
													test="${pagination.items != null && fn:length(pagination.items) != 0}">
													<c:forEach items="${pagination.items}" var="item1"
														varStatus="statu">
														<tr><!-- 
															<td><input type="checkbox"
																name="companyId[${item1.adminId}]"
																lay-filter="checkOne" value="${item1.adminId}"
																title=" ">
																<div class="layui-unselect layui-form-checkbox">
																	<span> </span><i class="layui-icon"></i>
																</div></td> -->
															<td>${item1.adminName}</td>
															<td>${item1.adminRealName}</td>
															<td>
																<a href="javascript:modify('${item1.adminId}', '${item1.adminName}');">修改密码</a> | 
																<a href="javascript:del('${item1.adminId}', '${item1.adminName}');">删除</a>
															</td>
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

		<!--底部   静态引入-->
		<%@ include file="bottom.jsp"%>
	</div>
	<script src="${basePath}resources/js/table.js" type="text/javascript"></script>
<script src="${basePath}resources/js/jquery.min.js"></script>
<script type="text/javascript">
layui.use(['element', 'laypage', 'layer', 'form'], function(){
  var element = layui.element() ,
  jq = layui.jquery ,
  form = layui.form() ,
  laypage = layui.laypage;

  //新增人员
  jq('#addApplicant-btn').click(function(){
    layer.open({
      type: 2,
      icon: 2,
      shadeClose: false,
      area: ['400px','350px'],
      title: '添加用户',
      content: '${basePath}admin?value=addAdmin'
    });
  });
  
  
  //搜索
  jq('#search-btn').click(function(){
  	var conditionValue = jq('#key').val();
  	conditionValue = encodeURIComponent(conditionValue);
	window.location.href = '${basePath}admin?value=main&key=' + conditionValue;
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
      jq.get('${basePath}admin?value=deleteAdmin', param, function(data){
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
        location.href = '${basePath}admin?value=main&page='
         + e.curr + '&key=' + encodeURIComponent(jq('#key').val());
      }
    }
  });
});

function modify(id, name) {
	layui.use('layer', function(){
		layer.open({
			title :'修改密码',
			area: ['250px', '300px'],
			type: 2,
			scrollbar: false,
			content: '${basePath}admin?value=modifyOtherPwd&adminId='
				+ id + '&adminName=' + name
		});
	});
}
function del(id, name) {
  layui.use('layer',function(){
	layer.confirm('确定删除账号【'+ name +'】?', function(index){
      loading = layer.load(2, {
        shade: [0.2,'#000'] //0.2透明度的白色背景
      });
      $.get('${basePath}admin?value=delete&adminId='+id, null, function(data){
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
  });
}

</script>
</body>
</html>