<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personmanage/personalinfo/bimain.css">
<title>学生个人资料详细界面</title>
</head>
<body>
	<div id="head">
		<li id="crumbs"><span>个人中心</span> > <span>个人资料</span></li>
	</div>
	<!-- 个人主体信息 -->
	<div id="pic">
		<div class="img">
			<c:if test="${student.stuImg!=null}">
				<img src="${pageContext.request.contextPath}/system/loadImgOnline?path=${student.stuImg}" class="img" onerror="this.src='${pageContext.request.contextPath}/assets/avatars/loyer.png'">
			</c:if>
			<c:if test="${student.stuImg==null}">
				<img class="img" src="${pageContext.request.contextPath}/assets/avatars/loyer.png" alt="未上传照片" />
			</c:if>
		</div>
		</div>
		<!-- <div>
			<label class="imgLetter">个人照片</label>
		</div> -->
	</div>
	<div id="info">
		<form action="">
			<table>
				<th></th>
				<tbody>
					<tr>
						<td align="right"><label>姓名:</label></td>
						<td><label class="txt">${student.stuName}</label></td>
						<td align="right"><label>学号:</label></td>
						<td><label class="txt">${student.stuNumber }</label></td>
					</tr>
					<tr>
						<td align="right"><label>性别:</label></td>
						<td><label class="txt">${student.stuSex }</label></td>
						<td align="right"><label>名族:</label></td>
						<td><label class="txt">${student.stuNationality }</label></td>
					</tr>
					<tr>
						<td align="right"><label>证件号码:</label></td>
						<td><label class="txt">${student.stuId }</label></td>
						<td align="right"><label>家庭住址:</label></td>
						<td><label class="txt">${student.stuAddr }</label></td>
					</tr>
					<tr>
						<td align="right"><label>出生日期:</label></td>
						<td><label class="txt"><fmt:formatDate value="${student.stuBirth }" type="date" pattern="yyyy-MM-dd"/></label></td>
						<td align="right"><label>入职时间:</label></td>
						<td><label class="txt"><fmt:formatDate value="${student.stuEntranceDate }" type="date" pattern="yyyy-MM-dd"/></label></td>
					</tr>
					<tr>
						<td align="right"><label>联系电话:</label></td>
						<td><label class="txt">${student.stuPhone }</label></td>
						<td align="right"><label>邮箱:</label></td>
						<td><label class="txt">${student.stuEmail }</label></td>
					</tr>
					<tr>
						<td align="right"><label>学院:</label></td>
						<td><label class="txt">${student.stuFaculty.facName }</label></td>
						<td align="right"><label>专业:</label></td>
						<td><label class="txt">${student.stuMajor.majorName }</label></td>
					</tr>
					<tr>
						<td align="right"><label>备注:</label></td>
						<td><label class="txt">${student.stuRemark }</label></td>
					</tr>
				</tbody>

			</table>
		</form>
	</div>
</body>
</html>