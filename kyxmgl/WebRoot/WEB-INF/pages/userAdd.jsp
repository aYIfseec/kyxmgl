<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8"%>
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

<link href="${basePath}resources/layui/css/layui.css" rel="stylesheet">
<link href="${basePath}resources/css/userDefine.css" rel="stylesheet">

</head>

<body>
	<div class="ml15 mt15">
		<form class="layui-form">
				<label class="layui-form-label"><font style="color:red;">*</font>用户名</label>
				<div class="layui-input-inline">
					<input type="text" name="adminName" value="" lay-verify="required"
						autocomplete="off" placeholder="请输入用户名" class="layui-input">
				</div><br><br>
				
				<label class="layui-form-label"><font style="color:red;">*</font>真实姓名</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="adminRealName" value="" lay-verify="required"
						autocomplete="off" placeholder="请输入姓名" class="layui-input">
				</div><br><br>
				
				<label class="layui-form-label"><font style="color:red;">*</font>密码</label>
				<div class="layui-input-inline input-custom-width">
					<input type="password" name="password" id="password" value="" lay-verify="required"
						autocomplete="off" placeholder="请输入密码" class="layui-input">
				</div><br><br>
				
				<label class="layui-form-label"><font style="color:red;">*</font>确认密码</label>
				<div class="layui-input-inline input-custom-width">
					<input type="password" name="repassword" id="repassword" value="" lay-verify="required"
						autocomplete="off" placeholder="请再次输入密码" class="layui-input">
				</div><br><br>
			<!-- 人员基本信息 end -->
				<div class="layui-input-block">
					<input class="layui-btn layui-btn-small layui-btn-danger" type="reset" value="清空">
					<button class="layui-btn layui-btn-small" lay-submit="" lay-filter="cate_add" style="margin-left:50px;">保存</button>
				</div>
		</form>
	</div>
	<script src="${basePath}resources/js/jquery.min.js"></script>
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
layui.use([ 'element', 'form', 'laydate','layer' ],function() {
  var element = layui.element(), form = layui.form();
  var layer = layui.layer, jq = layui.jquery;
  //监听提交
  form.on('submit(cate_add)', function(data) {
    
	var param = data.field;
	if (jq('#repassword').val() != jq('#password').val()){
		layer.msg('两次输入的密码不一样', {
			icon : 2,
			time : 1000
		});
		return false;
	}
	$.ajax({
		type: 'POST',
		url: '${basePath}admin?value=post',
		data: param,
		async: true,//支持异步加载中loading才有效果
		dataType: 'text',
		beforeSend:function(){
			loading = layer.load(0, {
  				shade: [0.7,'#fff'] //0.1透明度的黑色背景
			});
		},
        success: function(returnMsg){
        	if (returnMsg == "success") {
        		layer.msg('添加成功', {
					icon : 1,
					time : 1000
				}, function() {
					//location.reload();//do something
					//方便调试暂时注释，后期解开
					layer.close(loading);
				});
        	} else if(returnMsg == "exist"){
        		layer.close(loading);
        		layer.msg('添加失败，该用户名已被注册！', {
					icon : 2,
					time : 1000
				});
        	} else {
        		layer.close(loading);
        		layer.msg('添加失败', {
					icon : 2,
					anim : 6,
					time : 1000
				});
        	}
		},
		error: function(XMLResponse) {
			layer.close(loading);
			layer.msg('未响应', {
				icon : 2,
				anim : 6,
				time : 1000
			});
		}
	});
	return false;
  });
});

</script>
</body>
</html>
