<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/course/add.css">

<meta charset="UTF-8">
<title>课程信息编辑</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs"><span>课程信息</span> > <c:if
				test="${operate=='add'}">
				<span>课程信息新增</span>
			</c:if> <c:if test="${operate=='update'}">
				<span>课程信息修改</span>
			</c:if> <c:if test="${operate=='show'}">
				<span>课程信息查看</span>
			</c:if></li>
	</div>
	<form id="cur_form" method="post"
		action="${pageContext.request.contextPath}/course/edit"
		enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" name="id" value="${course.id}">
			<tr>
				<td><label class="letter">课程中文名：</label><input name="cName"
					id="cName" value="${course.cName}"></td>
				<td><label class="letter">课程外文名：</label><input name="cEnglishName"
					id="cEnglishName" value="${course.cEnglishName}"></td>
			</tr>
			<tr>
				<td><label class="letter">课 程 代 码：</label><input name="cNo"
					id="cNo" value="${course.cNo }"></td>
				<td><label class="letter">课 程 学 分：</label><input name="cScore"
					id="cScore" value="${course.cScore}"></td>
			</tr>
			<tr>
				<td><label class="letter">备注：</label> <textarea rows="" cols=""
						name="cRemark" id="cRemark">${course.cRemark}
					</textarea></td>
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
	$("#cRemark").val("");//清空文本框

	var operate = "${operate}";
	if (operate == "show") {
		$("#submitBtn").remove();
	}
	//提示错误信息
	//getErrorMsg();
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/course/add.js"></script>
</html>