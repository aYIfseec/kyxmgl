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
</head>
<body>
		<!-- 提供页内搜索 -->
		<!-- 中间内容  后台传选择类型（人、单位）  -->
	<div class="layui-tab-brief main-tab-container"
		style="margin-left:25px;">
		<form class="layui-form">
			<ul id="data-list">
			<!-- 
				<li>
					<a class="layui-btn layui-btn-mini select-input"
						type="button" name="applicant" title="${item.companyId}">
					${item.companyName}</a>
				</li><br>
			 -->
			</ul>
		</form>
		<!-- 
		<c:choose>
			<c:when test="${pagination.lastPage}">
					<p>没有更多了</p>
			</c:when>
			<c:otherwise>
				<a href="${basePath}${dataType}?value=selectPage$selectType=${selectType}&page=${pagination.pageNum}" class="">加载更多</a>
			</c:otherwise>
		</c:choose>
		 -->
		<div id="page"></div>
	</div>
	<!-- 中间内容end -->
	<script src="${basePath}resources/js/jquery.min.js"></script>
<script type="text/javascript">
  var dataType = '${dataType}';
  var selectId = '${selectId}';
  var div = parent.document.getElementById("div-"+selectId);
  var hidden = parent.document.getElementById("hidden-"+selectId);

  function select(obj) {//selectPage点击事件
  	var name = obj.innerHTML;//单位名/姓名
  	//已经是特殊颜色说明是取消选择
    if (obj.className == 'layui-btn layui-btn-mini select-input layui-btn-danger') {
        var objS = div.childNodes;//去匹配父页面中已经选择的
        for (var i = 0; i < objS.length; i++) {
            if (objS[i].innerHTML.indexOf(name) >= 0) {
                objS[i].click();//projectAdd页面 点击删除事件
                break;
            }
        }
        //改成正常样式
        obj.className = 'layui-btn layui-btn-mini select-input';
    } else {//选中
      if ("one" != '${selectType}') {//多选
        div.innerHTML = div.innerHTML
        	+"<button class='layui-btn layui-btn-small selected-btn' id='"
        	+obj.title+"' type='button' title='点击移除' onclick='delSelected(this)'>"
        	+name+"</button>";
        hidden.value = hidden.value+obj.title+",";
        obj.className = 'layui-btn layui-btn-mini select-input layui-btn-danger';
      } else {//单选
      	div.innerHTML = "<button class='layui-btn layui-btn-small selected-btn' id='"
        	+obj.title+"' type='button' title='点击移除' onclick='delSelected(this)'>"
        	+name+"</button>";
        if (hidden.value != "" && hidden.value != null) {
        	document.getElementById(hidden.value).className = 'layui-btn layui-btn-mini select-input';
        }
        hidden.value = obj.title;
        obj.className = 'layui-btn layui-btn-mini select-input layui-btn-danger';
      }
    }
  }

layui.use([ 'form', 'flow'], function() {
	var form = layui.form(), 
	jq = layui.jquery,
	flow = layui.flow;
  //流加载
  flow.load({
    elem: '#data-list',//DOM id
    done: function(page, next){ //到达临界点（默认滚动触发），触发下一页
      var lis = [];
      div = parent.document.getElementById("div-"+selectId);
      var hiddenVal = hidden.value;
      $.get('${basePath}${dataType}?value=list&page='+page, function(res){
        layui.each(res.data, function(index, item){
          var className = 'layui-btn layui-btn-mini select-input';
          var disName, disId;
          if (dataType == "company") {
          	disName = item.companyName;
          	disId = item.companyId;
          } else {
          	disName = item.applicantName;
          	disId = item.applicantId;
          }
          //加载时，有匹配的则显示选中
          if (hiddenVal.indexOf(disId) >= 0) {
          	className = 'layui-btn layui-btn-mini select-input layui-btn-danger';
          }
          
          lis.push('<li><a class="'+ className 
          	  +'" type="button" id="'+ disId +'" title="'
          	  +disId+'" onclick="select(this)">'+ disName +'</a></li><br>');
        }); 
        //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
        next(lis.join(''), page < res.pages);
      });
    }
  });

});
  
</script>
</body>
</html>