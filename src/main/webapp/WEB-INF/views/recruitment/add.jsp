<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.addrSel {
	width: 28%;
	margin-left: -0.5%;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/recruitment/add.css">
<meta charset="UTF-8">
<title>招聘信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs"><span>招聘信息</span> > <c:if
				test="${operate=='add'}">
				<span>招聘信息新增</span>
			</c:if> <c:if test="${operate=='update'}">
				<span>招聘信息修改</span>
			</c:if> <c:if test="${operate=='show'}">
				<span>招聘信息查看</span>
			</c:if></li>
	</div>
	<form id="col_form" method="post"
		action="${pageContext.request.contextPath}/company/recruitmentEdit"
		enctype="multipart/form-data">
		<!-- <div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div> -->
		<table>
			<input type="hidden" name="id" value="${recruitment.id}">
			<tr>
				<td><label class="letter">招聘单位：</label><select
					name="companyId" id="companySel"
					value="${recruitment.rcCompany.companyName}"></select></td>
				<td><label class="letter">招聘职位：</label><input
					name="rcJob" id="rcJob" value="${recruitment.rcJob}"
					required="required"></td>
			</tr>
			<tr style="width: 1000px">
				<td style="width: 1000px"><label class="letter">职位描述：</label> <textarea
						id="editor" name="rcContent" type="text/plain"
						style="width:width: 810px; margin: -2% 8.5%;" >${recruitment.rcContent }</textarea>
    			</td>
			</tr>
			<tr></tr>
			<tr>
				<td><label style="margin-top: 4%;">备注：</label> <textarea rows="" cols=""
						name="recruitmentRemark" id="recruitmentRemark">${recruitment.rcRemark}</textarea>
				</td>
			</tr>
		</table>
		<!-- <div>
			<p class="module">2</p>
			<p class="moduleTitle">批量导入</p>
		</div>
		<div id="addBatch">
			<button type="button" class="templet">模板下载</button>
			<input type="file" name="stuFile" id="stuFile">
		</div> -->
		<div id="btn">
			<button type="submit" id="submitBtn">提交</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	//$("#recruitmentRemark").val("");//清空文本框

	var operate = "${operate}";
	if (operate == "show") {
		$("#submitBtn").remove();
	}
	//提示错误信息
	//getErrorMsg();
	var companyId = "${recruitment.rcCompany.id}";
	getAllCompanies("companySel",companyId);
</script>
</html>