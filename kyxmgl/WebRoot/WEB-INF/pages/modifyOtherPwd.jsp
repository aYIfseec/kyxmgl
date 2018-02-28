<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="${basePath}resources/layui/css/layui.css"  media="all">
	<link rel="stylesheet" href="${basePath}resources/css/userDefine.css">
  </head>
  <body>
<div class="ml15 mt15">
	<form id="userForm">
		<div class="layui-input-inline">
			<label>修改【${adminName}】的密码</label>
			<input type="hidden" value="${adminId}" id="adminId">
			<input type="password" name="newPwd" id="newPwd"  
				placeholder="新密码" autocomplete="off" class="layui-input mt15" value="">
			<input type="password" name="newPwdConfirm" id="newPwdConfirm"  
				placeholder="新密码确认" autocomplete="off" class="layui-input mt15" value="">
		</div>
	</form>

	<div class ="mt15">
		<a href="javascript:confirm()" class="layui-btn">确认</a>
	</div>
</div>

<script src="${basePath}resources/layui/layui.js" charset="utf-8"></script>
<script src="${basePath}resources/js/jquery.min.js" charset="utf-8"></script>

<script charset="utf-8">
	function confirm(){
		if ($('#newPwd').val() == null || $('#newPwd').val() == '') {
			layui.use('layer', function(){
				var layer = layui.layer;
				layer.msg('请输入新密码');
			});
			return;
		}
		
		if($('#newPwd').val() != $('#newPwdConfirm').val() ){
			layui.use('layer', function(){
				var layer = layui.layer;
				layer.msg('两次输入的新密码不一样');
			});  
			return;
		}
		
		$.ajax({    
			url:'${basePath}admin?value=update&adminId='
				+ $('#adminId').val() + '&newPwd=' + $('#newPwd').val(),
			data:null,
		    type:'GET',
		    dataType: 'text', 
		    success:function(data){
		    	if (data == "true") {
		    		layui.use('layer', function(){
				    	var layer = layui.layer;
		    			layer.msg('修改成功', {icon: 1, time: 2000}, function() {
							parent.layer.closeAll();
						});
		    	    });
		    	} else {
		    		layui.use('layer', function(){
				    	var layer = layui.layer;
		    			layer.msg('修改失败', {icon: 2, time: 2000});
		    	    });
		    	}
				
		    },
			error:function(){
			},
			complete:function(){
			}
		});
	}
</script>
  </body>
</html>
