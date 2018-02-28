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
</style>

</head>

<body>
	<div class="layui-tab-brief main-tab-container">
		<form class="layui-form">
			<!-- 项目基本信息 -->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>项目基本信息</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">项目编号</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="proNum" value="${pro.proNum}" lay-verify="required"
						autocomplete="off" placeholder="请输入项目编号" class="layui-input">
				</div>
				<label class="layui-form-label">项目名称</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="proName" value="${pro.proName}" lay-verify="required"
						autocomplete="off" placeholder="请输入项目名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">项目起始时间</label>
				<div class="layui-input-inline input-custom-width">
					<input name="proStartTime" value="${pro.proStartTime}" class="layui-input" placeholder="年-月-日" id="LAY_demorange_s" lay-verify="required">
				</div>
				<label class="layui-form-label">项目结束日期</label>
				<div class="layui-input-inline input-custom-width">
					<input name="proEndTime" value="${pro.proEndTime}" class="layui-input" placeholder="年-月-日" id="LAY_demorange_e" lay-verify="required">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">项目类型</label>
				<div class="layui-input-inline input-custom-width">
					<select name="proType" lay-verify="required">
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
				<label class="layui-form-label">归口处室</label>
				<div class="layui-input-inline input-custom-width">
					<select name="proAscription" lay-verify="required">
					    <option value="">请选择</option>
						<option value="市发展改革委">市发展改革委</option>
      					<option value="市科技创新委">市科技创新委</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">技术来源</label>
				<div class="layui-input-inline input-custom-width">
					<input name="techSrc" value="${pro.techSrc}" class="layui-input" placeholder="" lay-verify="required">
				</div>
				<label class="layui-form-label">项目负责人</label>
				<div class="layui-input-inline input-custom-width">
					<input type="hidden" name="proLeaderId" value="${pro.proLeaderId}" id="hidden-select1"/>
					<div class="layui-box layui-upload-button" feedback-type="one" style="left:0;width:113px;" feedback-data="applicant" feedback-id="select1">
					<!-- i中空格不能删 -->
						<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
					</div>
					<div id="div-select1" class="div-select" title="select1">
					<c:if test="${pro.proLeaderName != null && pro.proLeaderName != ''}">
						<button class='layui-btn layui-btn-small selected-btn' id='${pro.proLeaderId}' type='button'
						 title='点击移除' onclick="delSelected(this)">${pro.proLeaderName}</button>
					</c:if>
					</div>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">项目联系人</label>
				<div class="layui-input-inline input-custom-width">
					<c:choose>
						<c:when test="${appList != null && fn:length(appList) != 0}">
							<c:forEach items="${appList}" var="item" varStatus="statu">
								<c:if test="${item.applicantType == 1}">
									<input type="hidden" name="lxApplicantsId" id="hidden-select2"
										value="${item.applicantId}" />
									<div class="layui-box layui-upload-button" feedback-type="one"
										style="left:0;width:113px;" feedback-data="applicant"
										feedback-id="select2">
										<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
									</div>
									<div id="div-select2" class="div-select" title="select2">
										<button class='layui-btn layui-btn-small selected-btn'
											id='${item.applicantId}' type='button' title='点击移除'
											onclick="delSelected(this)">${item.applicantName}</button>
									</div>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<input type="hidden" name="lxApplicantsId" id="hidden-select2"
								value="" />
							<div class="layui-box layui-upload-button" feedback-type="one"
								style="left:0;width:113px;" feedback-data="applicant"
								feedback-id="select2">
								<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
							</div>
							<div id="div-select2" class="div-select" title="select2"></div>
						</c:otherwise>
					</c:choose>
				</div>
				<label class="layui-form-label" style="margin-left:310px">承担单位</label>
				<div class="layui-input-inline input-custom-width">
					<input type="hidden" name="proCompanyId" value="${pro.proCompanyId}" id="hidden-select4" value=""/>
					<div class="layui-box layui-upload-button" feedback-type="one" style="left:0;width:113px;" feedback-data="company" feedback-id="select4">
						<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
					</div>
					<div id="div-select4" class="div-select" title="select4">
						<c:if test="${pro.proCompanyName != null && pro.proCompanyName != ''}">
							<button class='layui-btn layui-btn-small selected-btn' id='${pro.proCompanyId}' type='button'
						 		title='点击移除' onclick="delSelected(this)">${pro.proCompanyName}</button>
						</c:if>
					</div>
				</div>
			</div><br>
			<div class="layui-form-item">
				<label class="layui-form-label">项目参与人</label>
				<div class="layui-input-inline input-custom-width" style="width:750;">
					<input type="hidden" name="cyApplicantsId" id="hidden-select3" value="${pro.cyApplicantsId}"/>
					<div class="layui-box layui-upload-button" style="left:0;width:113px;" feedback-type="more" feedback-data="applicant" feedback-id="select3">
						<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
					</div>
					<div id="div-select3" class="div-select" title="select3">
						<c:if test="${appList != null && fn:length(appList) != 0}">
							<c:forEach items="${appList}" var="item" varStatus="statu">
								<c:if test="${item.applicantType == 2}">
									<button class='layui-btn layui-btn-small selected-btn'
										id='${item.applicantId}' type='button' title='点击移除'
										onclick="delSelected(this)">${item.applicantName}</button>
								</c:if>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div><br><br><br>
			
			<div class="layui-form-item">
				<label class="layui-form-label">合作单位</label>
				<div class="layui-input-inline input-custom-width" style="width:750;">
					<input type="hidden" name="hzCompanysId" id="hidden-select5" value="${pro.hzCompanysId}"/>
					<div class="layui-box layui-upload-button" style="left:0;width:113px;" feedback-type="more" feedback-data="company" feedback-id="select5">
						<span class="layui-upload-icon"><i class="layui-icon"></i>库中选择</span>
					</div>
					<div id="div-select5" class="div-select" title="select5">
						<c:if test="${comList != null && fn:length(comList) != 0}">
							<c:forEach items="${comList}" var="item" varStatus="statu">
								<c:if test="${item.isMain == 2}">
									<button class='layui-btn layui-btn-small selected-btn'
										id='${item.companyId}' type='button' title='点击移除'
										onclick="delSelected(this)">${item.companyName}</button>
								</c:if>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div><br><br><br>
			<!-- 项目基本信息 end -->
			
			<!-- 经济效益-->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>项目经济效益（单位：万元）</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">累计工业或农业总产值</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="industryVal" value="${pro.industryVal == null ? '0.00' : pro.industryVal}" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">累计销售收入</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="saleVal" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="请输入项目名称" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">累计利润总额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="profit" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">累计缴税总额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="payTaxes" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">累计创汇额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="foreignExchange" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">新增累计工业/农业总产值</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="addIndustryVal" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新增累计销售收入</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="addSaleVal" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">新增累计利润总额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="addProfit" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新增累计缴税总额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="addPayTaxes" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">新增累计创汇额</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="addForeignExchange" value="0.00" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<!-- 经济效益 end-->
			
			<!-- 社会效益-->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>项目社会效益</legend>
			</fieldset>
			<div class="layui-form-item">
				<label class="layui-form-label">增加就业人数</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="employment" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">培养科技创新人才</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="personnel" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">取得博士学位人数</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="doctor" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">取得硕士学位人数</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="master" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">晋升高级职称人数</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="senior" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">晋升中级职称人数</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="mid" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新产品/软件(个)</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="newProd" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">新设备(台/件)</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="newEqNum" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新材料(种)</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="newMaterial" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
				<label class="layui-form-label">新技术</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="newTechnique" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">新工艺(项)</label>
				<div class="layui-input-inline input-custom-width">
					<input type="text" name="newProcess" value="0" lay-verify="required"
						autocomplete="off" placeholder="" class="layui-input">
				</div>
			</div>
			<!-- 社会效益 end-->
			
			<!-- 产出成果-->
			<fieldset class="layui-elem-field layui-field-title" style="width:100%">
  				<legend>项目产出成果</legend>
			</fieldset>
			<table class="layui-table">
				<tr>
					<td rowspan="2">专利数（项）</td>
					<div class="layui-form-item">
						<td><label class="layui-form-label">申请数</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="patentApply" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
						<td><label class="layui-form-label">其中发明专利</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="invPatApply" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
					</div>
				</tr>
				<tr>
					<div class="layui-form-item">
						<td><label class="layui-form-label">授权数</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="patentAdopt" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
						<td><label class="layui-form-label">其中发明专利</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="invPatAdopt" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
					</div>
				</tr>
				<tr>
					<td>软件著作权（项）</td>
					<div class="layui-form-item">
						<td><label class="layui-form-label">申请数</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="sftCopyrightApply" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
						<td><label class="layui-form-label">授权数</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="sftCopyrightAdopt" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
					</div>
				</tr>
				<tr>
					<td>发表论文数/科技刊物（篇）</td>
					<div class="layui-form-item">
						<td><label class="layui-form-label">省级以上</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="provincePaper" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
						<td><label class="layui-form-label">其它</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="otherPaper" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
					</div>
				</tr>
				
				<tr>
					<td rowspan="2">获奖数（项）</td>
					<div class="layui-form-item">
						<td><label class="layui-form-label">国家级</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="nationalAwards" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
						<td><label class="layui-form-label">省部级</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="provincialAwards" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
					</div>
				</tr>
				<tr>
					<div class="layui-form-item">
						<td><label class="layui-form-label">市级</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="municipalAwards" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
						<td><label class="layui-form-label">其它</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="otherAwards" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
					</div>
				</tr>
				
				<tr>
					<td rowspan="2">制定技术标准数（项）</td>
					<div class="layui-form-item">
						<td><label class="layui-form-label">企业标准</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="ets" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
						<td><label class="layui-form-label">国家标准</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="nts" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div></td>
					</div>
				</tr>
				<tr>
					<div class="layui-form-item">
						<td><label class="layui-form-label">行业标准</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="indts" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
						<td><label class="layui-form-label">国际 标准</label>
							<div class="layui-input-inline input-custom-width">
								<input type="text" name="intts" value="0"
									lay-verify="required" autocomplete="off" placeholder=""
									class="layui-input">
							</div>
						</td>
					</div>
				</tr>
			</table>
			<!--产出成果 end-->
			
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit="" lay-filter="cate_add">确定保存</button>
				</div>
			</div>
		</form>
	</div>
	<script src="${basePath}resources/js/jquery.min.js"></script>
	<script src="${basePath}resources/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
layui.use([ 'element', 'form', 'laydate' ],function() {
  var element = layui.element(), form = layui.form(), jq = layui.jquery;
  
  //结束时间>开始时间控制
  var laydate = layui.laydate;
  var start = {
    //min: laydate.now(),
    max: '2099-06-16 23:59:59',
    istoday: false,
    choose: function(datas){
      end.min = datas; //开始日选好后，重置结束日的最小日期
      end.start = datas //将结束日的初始值设定为开始日
    }
  };
  var end = {
    //min: laydate.now(),
    max: '2099-06-16 23:59:59',
    istoday: false,
    choose: function(datas){
      start.max = datas; //结束日选好后，重置开始日的最大日期
    }
  };
  
  document.getElementById('LAY_demorange_s').onclick = function(){
    start.elem = this;
    laydate(start);
  }
  document.getElementById('LAY_demorange_e').onclick = function(){
    end.elem = this;
    laydate(end);
  }
  //结束时间>开始时间控制 end
  
  //监听提交
  form.on('submit(cate_add)', function(data) {
	var param = data.field;
	$.ajax({
		type: 'POST',
		url: '${basePath}home?value=addProject',
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
        		layer.msg('添加失败，该项目编号已存在！', {
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
  
  //选择项目负责人、参与人、单位
  jq('.layui-upload-button').click(function(){
  	var id = jq(this).attr('feedback-id');//select 1-5
  	var dataType = jq(this).attr('feedback-data');//com app
  	var selectType = jq(this).attr('feedback-type');//one more
    layer.open({
      type: 2,
      icon: 2,
      //skin: 'layui-layer-molv',
      shadeClose: true,
      shade: false,
      //maxmin: true,
      area: ['320px','250px'],
      title: '选择',
      content: '${basePath}'+dataType+
      '?value=selectPage&selectType='+
      selectType+'&selectId='+id ,
      success: function(layero, index) {
      	//alert(layero);
      }
    });
    //'input-'+id
    //'hidden-'+id
    
  });
  
});
function delSelected(obj) {
	var idStr = obj.parentNode.title;
	var hidden = document.getElementById('hidden-'+idStr);
  	var values = hidden.value.replace(" ","").split(",");
  	alert(obj.id);
  	var newVal = "";
  	for (var i = 0; i < values.length-1; i++) {
  		if (values[i] != obj.id) {
  			newVal += values[i]+",";
  		}
    }
    //newVal = newVal.slice(0, newVal.length-1);
  	//改变hidden中的值
  	hidden.value = newVal;
  	obj.remove();
  	return false;
}
</script>
</body>
</html>
