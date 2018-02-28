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
.div-select{
	position: absolute;
	top:1px;
	left:120px;
}
.selected-btn{
	margin:3px;
}
.layui-nav , li{
	background-color:#fff;
}
</style>

</head>

<body>
	<div class="layui-tab-brief main-tab-container">
		<form class="layui-form">
			<!-- 项目人员基本信息 -->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>项目人员基本信息</legend>
			</fieldset><br>
			<div class="layui-form-item">
				<label class="layui-form-label">姓名</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="applicantName" value="${app.applicantName}" lay-verify="required"
						autocomplete="off" placeholder="请输入姓名" class="layui-input">
				</div>
				<label class="layui-form-label">身份证号</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="applicantId" value="${app.applicantId}" lay-verify="required"
						autocomplete="off" placeholder="请输入身份证号" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">性别</label>
				<div class="layui-input-inline input-custom-width">
      				<input type="radio" name="applicantSex" value="男" title="男" <c:if test="${app.applicantSex == '男' || app.applicantSex == ''}">checked</c:if>>
      				<input type="radio" name="applicantSex" value="女" title="女" <c:if test="${app.applicantSex == '女'}">checked</c:if>>
    			</div>
				<label class="layui-form-label">出生日期</label>
				<div class="layui-input-inline input-custom-width">
					<input name="applicantBirth" value="${app.applicantBirth}" class="layui-input" placeholder="年-月-日" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})" lay-verify="required">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">学历</label>
				<div class="layui-input-inline input-custom-width">
					<select name="applicantEdu" lay-verify="">
					    <c:choose>
							<c:when test="${proTypeList != null && fn:length(proTypeList) != 0}">
								<option value="">请选择</option>
								<c:forEach items="${proTypeList}" var="item" varStatus="statu">
									<c:choose>
										<c:when test="${item.proTypeId == pro.proType}">
											<option value="${item.proTypeId}" selected="selected">${item.proTypeName}</option>
										</c:when>
										<c:otherwise>
											<option value="${item.proTypeId}">${item.proTypeName}</option>
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
				<label class="layui-form-label">毕业院校</label>
				<div class="layui-input-inline input-custom-width">
					<input name="applicantUniv" value="${app.applicantUniv}" class="layui-input" placeholder="" lay-verify="">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">研究方向</label>
				<div class="layui-input-inline input-custom-width">
					<input name="aResDirection" value="${app.aResDirection}" class="layui-input" placeholder="" lay-verify="">
				</div>
				<label class="layui-form-label">电话</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="applicantTel" value="${app.applicantTel}" class="layui-input" placeholder="" lay-verify=""/>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">邮箱</label>
				<div class="layui-input-inline input-custom-width">
					<input name="applicantMailbox" value="${app.applicantMailbox}" class="layui-input" placeholder="" lay-verify="">
				</div>
				<label class="layui-form-label">简介</label>
				<div class="layui-input-inline input-custom-width">
					<textarea name="applicantInfo" value="" class="layui-textarea" placeholder="" lay-verify="">${app.applicantInfo}</textarea>
				</div>
			</div>
			<!-- 人员基本信息 end -->
			
				<div class="layui-form-item">
				<div class="layui-input-block">
					<input class="layui-btn layui-btn-small layui-btn-danger" type="reset" style="margin-left:210px;" value="清空">
					<button class="layui-btn layui-btn-small" lay-submit="" lay-filter="cate_add" style="margin-left:50px;">保存</button>
				</div>
			</div>
		</form>
		<c:if test="${app != null}">
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
layui.use([ 'element', 'form', 'laydate' ],function() {
  var element = layui.element(), form = layui.form(), jq = layui.jquery;
  
  //监听提交
  form.on('submit(cate_add)', function(data) {
	var param = data.field;
	var message = "添加";
	if ('${opType}' == "update") {
		message = "修改";
	}
	$.ajax({
		type: 'POST',
		url: '${basePath}applicant?opType=${opType}',
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
        		layer.msg(message+'成功', {
					icon : 1,
					time : 1000
				}, function() {
					//location.reload();//do something
					//方便调试暂时注释，后期解开
					layer.close(loading);
				});
        	} else if(returnMsg == "exist"){
        		layer.close(loading);
        		layer.msg('添加失败，该项目编号已存在！', {
					icon : 2,
					time : 1000
				});
        	} else {
        		layer.close(loading);
        		layer.msg(message+'失败', {
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
