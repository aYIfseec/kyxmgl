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
				<li class="knowledge"><a href="#">系统数据管理</a>
					<ul class="sub-menu">
					</ul>
				</li>
			</ul>
		</div>
		<!--左边导航菜单 end-->
		
		<!-- 中间内容 -->
		<div class="content">
			<div class="position">
				<div class="f_left">当前位置：系统数据-</div>
			</div>
			<div class="box">
				<iframe></iframe>
			</div>
		</div>
		<!-- 中间内容end -->

		<!--底部   静态引入-->
		<%@ include file="bottom.jsp"%>
	</div>
</body>
</html>