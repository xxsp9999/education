<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/college/add.css">
<meta charset="UTF-8">
<title>学院信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>院系信息</span>
			>
			<c:if test="${operate=='add'}"><span>学院信息新增</span></c:if>
			<c:if test="${operate=='update'}"><span>学院信息修改</span></c:if>
			<c:if test="${operate=='show'}"><span>学院信息查看</span></c:if>
		</li>
	</div>
	<form id="col_form" method="post" action="${pageContext.request.contextPath}/college/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" name="id" value="${faculty.id}">
			<tr>
				<td>
					<label class="letter">学院名称：</label><input name="facName" id="facName" value="${faculty.facName}"> 
				</td>
				<td>
					<label class="letter">学院代码：</label><input name="facNumber" id="facNumber" value="${faculty.facNumber }"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">备注：</label>
					<textarea rows="" cols="" name="facRemark" id="facRemark" value="${faculty.facRemark}">
					</textarea>
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
	$("#facRemark").val("");//清空文本框
	
	var operate = "${operate}";
	if(operate == "show"){
		$("#submitBtn").remove();
	}
	//提示错误信息
	getErrorMsg();
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/college/add.js"></script>
</html>