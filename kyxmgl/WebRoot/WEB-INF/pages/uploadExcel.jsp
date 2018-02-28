<%@ page language="java" pageEncoding="utf-8"%>
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
<link href="${basePath}resources/css/global.css" rel="stylesheet">
<style type="text/css">
.layui-upload-button{
	right: 100;top:105;
}
</style>

</head>

<body>
	<fieldset class="layui-elem-field">
		<legend>导入说明</legend>
		<div class="layui-field-box">支持拖拽上传，支持xls（Excel 97-2003）
		及xlsx（Excel 2007）格式。
		  <a href="${basePath}${getExcelPath}" style="color:#2e8ded">
		  	点击下载Excel模板</a>
		</div>
	</fieldset>
	
	<div class="layui-box layui-upload-button">
	<form target="layui-upload-iframe" method="post" key="set-mine" enctype="multipart/form-data" action="${basePath}upload?value=company">
	  <input type="file" name="uploadfile" id="uploadfile" lay-type="file" class="layui-upload-file">
	</form>
	<span class="layui-upload-icon" ><i class="layui-icon"></i>请选择Excel文件</span>
	</div>
	
	<!-- 
	<input type="file" name="uploadfile" id="uploadfile" lay-type="file"
		class="layui-upload-file">
	<!-- 
	<div class="layui-progress" lay-showPercent="yes" style="margin-top:40px;">
  		<div class="layui-progress-bar" lay-percent="90%"></div>
	</div>
	 -->
	<script src="${basePath}resources/js/jquery.min.js"></script>
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
	layui.use(['upload','element'], function() {
		layui.upload({
			url : '${basePath}${uploadPath}',
			dataType:'JSON',
			title : '请选择Excel文件',
			elem : '#uploadfile', //指定原始元素，默认直接查找class="layui-upload-file"
			method : 'post',
			ext : 'xls|xlsx', //控制格式
			before : function(input) {
//console.log(input.files[0].name);
				var index = layer.load(0, {
					shade: false,
				});
			},
			success : function(res) {
			//res {code:"0/1", msg: "", data:{}}
				layer.closeAll('loading');
				if (res.code == 1) {
					top.layer.alert(res.msg+"<br>"+res.data, {icon: 1, area: ['350px', '300px']});
					//location.reload();//刷s新
				} else {
					top.layer.alert(res.msg+"<br>"+res.data, {icon: 2, area: ['350px', '300px']});
				}
			}
		});
		layui.element();
	});
</script>
</body>
</html>
