<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>登录</title>
	<link rel="stylesheet" href="${basePath}resources/layui/css/layui.css">
	<!-- 
	<link rel="stylesheet" href="${basePath}resources/layui/css/layui.css">
	<link rel="stylesheet" href="${basePath}resources/css/userDefine.css">
	 -->
	<link rel="stylesheet" href="${basePath}resources/css/index.css">
</head>

<body>
	<!-- 
	<div class="layui-layout layui-layout-admin">
		<div class ="page-center">
			<div class="text-center">
				系统登录
			</div>
			<div class="box" style="background:#ff6">
				<div class="layui-input-inline text-center">
					<input type="text" name="userName" id="userName"  
						placeholder="请输入用户名" autocomplete="off" class="layui-input mt15" value="">
					<input type="password" name="password" id="password"
						placeholder="请输入密码" autocomplete="off" class="layui-input mt15" value="">
						
					<div class ="mt15">
						<a href="javascript:doLogin()" class="layui-btn">登录</a>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	-->
	

<div class="login-middle">
  <div class="content">
    <ul>
      <li>
		<span class="input-label">用户名：</span>
        <input type="text" id="userName" class="input-box" value="" placeholder="">
      </li>
      <li>
        <span class="input-label">密 &nbsp;&nbsp; 码：</span>
        <input id="password" type="password" class="input-box" value="" placeholder="">
      </li>
      <li class="btn-login" id="loginSubmit" onclick="doLogin()">登录</li>
    </ul>
  </div>
</div>
	
	<script src="${basePath}resources/js/jquery.min.js" type="text/javascript"></script>
	<script src="${basePath}resources/layer/layer.js" type="text/javascript"></script>
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		$('#password').bind('keypress', function(event) {
        	if (event.keyCode == "13") {              
            	event.preventDefault();
            	$('#loginSubmit').click();  
        	} 
    	});
		
		function doLogin() {
			var user = $("#userName").val();
			var pswd = $("#password").val();
			if("" == user || "" == pswd) {
			    layer.msg('请填写账号密码!', {
					icon : 2,
					time : 700
				});
				return;
			}
			$.ajax({
				type : "GET",
				dataType : "text",
				async : false,//不加直接error
				url : '${basePath}login?value=login',
				data : {"userName": user, "password": pswd},
				success : function(msg) {
				//alert(msg == 'true');
						if (msg == "true") {
							window.location.href = '${basePath}home?value=main';
						} else {
							layer.alert('用户名或密码错误！', {closeBtn : 1,anim : 5});
						}
						document.getElementById("password").value = "";
				}
			});
			//return false;
		}
	</script>
</body>
</html>