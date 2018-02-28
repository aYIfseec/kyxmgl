<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 
	<link href="${basePath}resources/layui/css/layui.css" rel="stylesheet">
	<link href="${basePath}resources/css/global.css" rel="stylesheet">
	 -->
	<!-- 
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
  	 -->
	
	<!-- top css -->
	<link href="${basePath}resources/css/global2.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}resources/css/dm.css" rel="stylesheet" type="text/css"/>
	<link href="${basePath}resources/css/nice-select.css" rel="stylesheet" type="text/css"><!--下拉菜单-->
	
<style type="text/css">
/*宽度小于1110不显示logo*/
@media screen and (max-width:1110px){
.logo{
	width:0px;
	display:none;
}
/*宽度小于810不显示user*/
@media screen and (max-width:810px){
.user{
	width:0px;
	display:none;
}
}
</style>
  </head>
  

<body onload="navDispaly()">
	<!--顶部-->
	<div class="top">
   	  <div class="logo f_left">
        	<a href=""><img src="${basePath}resources/images/logo1.png" width="277" height="50" /></a>        	
        </div>
    	<div class="menu f_left">
        	<ul class="top-ul">
            	<li class="ico_dm ico_dm_on"><a class="" href="${basePath}home?value=main">项目管理</a></li>
                <li class="ico_service ico_service_on"><a class="" href="${basePath}admin?value=main">系统用户</a></li>
                <li class="ico_app_m ico_app_m_on"><a class="" href="${basePath}company?value=main">单位管理</a></li>
                <li class="ico_open ico_open_on"><a class="" href="${basePath}applicant?value=main">项目人员</a></li>
                <li class="ico_source_m ico_source_m_on"><a class="" href="${basePath}statistics?value=main">统计分析</a></li>
                <!-- 
                <li class="ico_data ico_data_on"><a class="" href="${basePath}systemData?value=main">系统数据</a></li>
                 -->
            </ul>
        </div>
        <div class="user f_right">
            <span class="dropdown">
            	<img src="${basePath}resources/images/timg.png" width=35px; height="35px" />
                <div class="dropdown-sub" style="display:none;">
                    <div class="dropdown-sub_box">
                       <ul class="dropmenu">
                         <li><a href="javascript:modifyPwd();">修改密码</a></li>
                         <li style="border:0;"><a href="admin?value=adminLogout">退出</a></li>
                       </ul>
                    </div>
                </div>
            </span>
            <span class="mr8">欢迎您，${admin.adminRealName}</span>
        </div>
    </div>
	<!--顶部 end-->
    
    <script src="${basePath}resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${basePath}resources/js/nav.js" type="text/javascript"></script>
    <script src="${basePath}resources/layui/layui.js"></script>
    <!--弹出层-->
    <!-- 
    <script src="${basePath}resources/js/jquery.reveal.js" type="text/javascript"></script>
	 -->
	<!--弹出层 end-->
    
    <!-- 主导航弹出框 -->
	<script>
        
        $('.dropdown').hover(function() {
            $(this).addClass('dropdown-hover');
            $(".dropdown-sub").show();
        }, function() {
            $(this).removeClass('dropdown-hover');
        });
        
    function modifyPwd(){
		layui.use('layer', function(){
			layer.open({
				title :'修改密码',
				area: ['250px', '300px'],
				type: 2,
				scrollbar: false,
				content: 'admin?value=modifySelfPwd' //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
			});
		});
	}
    </script>
    <!-- 主导航弹出框 end -->
    <!--下拉菜单-->
    <!-- 
    <script src="js/jquery.nice-select.min.js"></script>
    
    <script>
    $(document).ready(function() {
      $('select:not(.ignore)').niceSelect();      
      FastClick.attach(document.body);
    });    
    </script>
    -->
    <!--下拉菜单 end-->
    
</body>
</html>