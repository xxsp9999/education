<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/grade/add.css">
<meta charset="UTF-8">
<title>年级信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>年级信息</span>
			>
			<c:if test="${operate=='add'}"><span>年级信息新增</span></c:if>
			<c:if test="${operate=='update'}"><span>年级信息修改</span></c:if>
			<c:if test="${operate=='show'}"><span>年级信息查看</span></c:if>
		</li>
	</div>
	<form id="col_form" method="post" action="${pageContext.request.contextPath}/grade/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" name="id" value="${grade.id}">
			<tr>
				<td>
					<label class="letter">年级名称：</label><input name="gradeName" id="gradeName" value="${grade.gradeName}"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">备注：</label>
					<textarea rows="" cols="" name="gradeRemark" id="gradeRemark">${grade.gradeRemark}</textarea>
				</td>
			</tr>
		</table>
		<div>
			<p class="module">2</p>
			<p class="moduleTitle">批量导入</p>
		</div>
		<div id="addBatch">
			<button type="button" class="templet">模板下载</button>
			<input type="file" name="stuFile" id="stuFile">
		</div>
		<div id="btn">
			<button type="button" id="submitBtn">提交</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	//$("#gradeRemark").val("");//清空文本框
	
	var operate = "${operate}";
	if(operate == "show"){
		$("#submitBtn").remove();
	}
	//提示错误信息
	getErrorMsg();
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/grade/add.js"></script>
</html>