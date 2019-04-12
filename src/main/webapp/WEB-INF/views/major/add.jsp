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
			<c:if test="${major.id==null}"><span>专业信息新增</span></c:if>
			<c:if test="${major.id!=null}"><span>专业信息修改</span></c:if>
		</li>
	</div>
	<form id="maj_form" method="post" action="${pageContext.request.contextPath}/major/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" name="id" value="${major.id}"><!--专业id  -->
			<input type="hidden" name="facId" value="${faculty.id}"><!--学院id  -->
			<tr>
				<td>
					<label class="letter">专业名称：</label><input name="majorName" id="majorName" value="${major.majorName}"> 
				</td>
				<td>
					<label class="letter">专业代码：</label><input name="majorNumber" id="majorNumber" value="${major.majorNumber }"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">所属学院：</label><input name="facName" id="facName" value="${faculty.facName}" readonly="readonly"> 
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">备注：</label>
					<textarea rows="" cols="" name="marjorRemark" id="marjorRemark" value="${major.marjorRemark}">
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
			<input type="file" name="majFile" id="majFile">
		</div>
		<div id="btn">
			<button type="button" id="submitBtn">提交</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	$("#marjorRemark").val("");//清空文本框
	
	var operate = "${operate}";
	if(operate == "show"){
		$("#submitBtn").remove();
	}
	//提示错误信息
	getErrorMsg();
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/major/add.js"></script>
</html>