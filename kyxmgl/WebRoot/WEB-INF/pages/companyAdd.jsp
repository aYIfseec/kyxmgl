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
.layui-nav , li{
	background-color:#fff;
}

</style>

</head>

<body>
	<div class="layui-tab-brief main-tab-container">
		<form class="layui-form">
			<!-- 单位基本信息 -->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>单位基本信息</legend>
			</fieldset>
			<input type="hidden" name="companyId" value="${com.companyId}">
			<div class="layui-form-item">
				<label class="layui-form-label">单位名称</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="companyName" value="${com.companyName}" lay-verify="required"
						autocomplete="off" placeholder="请输入单位名称" class="layui-input">
				</div>
			</div>			
			<div class="layui-form-item">
				<label class="layui-form-label">单位类型</label>
				<div class="layui-input-inline input-custom-width">
					<select name="companyType" lay-verify="required">
					    <c:choose>
							<c:when test="${companyTypeList != null && fn:length(companyTypeList) != 0}">
								<option value="">请选择</option>
								<c:forEach items="${companyTypeList}" var="item" varStatus="statu">
									<c:choose>
										<c:when test="${item.companyTypeId == com.companyType}">
											<option value="${item.companyTypeId}" selected="selected">${item.companyTypeName}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.companyTypeId}">${item.companyTypeName}</option>
										</c:otherwise>
									</c:choose>
      							</c:forEach>
      						</c:when>
      						<c:otherwise>
      							<option value="">暂无类型可选</option>
      						</c:otherwise>
      					</c:choose>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">研究方向</label>
				<div class="layui-input-inline input-custom-width">
					<input name="cResDirection" value="${com.cResDirection}" class="layui-input" placeholder="" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">单位地址</label>
				<div class="layui-input-inline input-custom-width">
					<input name="companyAddress" value="${com.companyAddress}" class="layui-input" placeholder="" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">单位网址</label>
				<div class="layui-input-inline input-custom-width">
					<input name="companyWebUrl" value="${com.companyWebUrl}" class="layui-input" placeholder="如:www.xxx.com" lay-verify="required">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">单位简介</label>
				<div class="layui-input-inline input-custom-width">
					<textarea name="companyInfo" required lay-verify="required" placeholder="请输入" class="layui-textarea">${com.companyInfo}</textarea>
				</div>
			</div>
			<!-- 单位基本信息 end -->
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<input class="layui-btn layui-btn-small layui-btn-danger" type="reset" value="清空">
					<button class="layui-btn layui-btn-small" lay-submit="" lay-filter="cate_add" style="margin-left:60px;">保存</button>
				</div>
			</div>
		</form>
		<c:if test="${com != null}">
		<!-- 单位负责承担的项目-->
		<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  			<legend>承担的项目</legend>
		</fieldset>
		<ul class="layui-nav" lay-filter="">
		<c:choose>
			<c:when test="${proList != null && fn:length(proList) != 0}">
				<c:forEach items="${proList}" var="item1" varStatus="statu">
					<li class="layui-nav-item">
					<a href="${basePath}home?value=projectAddPage&proId=${item1.proId}" target="_blank" style="color:#36a4f1;">
					${item1.proName}</a></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li class="layui-nav-item"><p>没有承担的项目</p></li>
			</c:otherwise>
		</c:choose>
		</ul>
		
		<!-- 单位参与的项目-->
		<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  			<legend>参与的项目</legend>
		</fieldset>
		<ul class="layui-nav" lay-filter="">
		<c:choose>
			<c:when test="${cyproList != null && fn:length(cyproList) != 0}">
				<c:forEach items="${cyproList}" var="item1" varStatus="statu">
					<li class="layui-nav-item">
					<a href="${basePath}home?value=projectAddPage&proId=${item1.proId}" target="_blank" style="color:#36a4f1;">
					${item1.proName}</a></li>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<li class="layui-nav-item"><p>没有参与的项目</p></li>
			</c:otherwise>
		</c:choose>
		</ul>
		</c:if>
	</div>
	<script src="${basePath}resources/js/jquery.min.js"></script>
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
layui.use([ 'element', 'form'],function() {
  var element = layui.element(), form = layui.form();
  
  //监听提交
  form.on('submit(cate_add)', function(data) {
	var param = data.field;
	var strMsg = '修改';
    if (param.companyId == null || param.companyId == "") {
       strMsg = '添加';
    }
	$.ajax({
		type: 'POST',
		url: '${basePath}company',
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
        		
        		layer.msg(strMsg+'成功', {
					icon : 1,
					time : 1000
				}, function() {
					//location.reload();//do something
					//方便调试暂时注释，后期解开
					layer.close(loading);
				});
        	} else if(returnMsg == "exist"){
        		layer.close(loading);
        		layer.msg('添加失败，该单位已存在！', {
					icon : 2,
					time : 1000
				});
        	} else {
        		layer.close(loading);
        		layer.msg(strMsg+'失败', {
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
