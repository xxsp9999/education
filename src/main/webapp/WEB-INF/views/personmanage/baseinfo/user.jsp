<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/personmanage/personalinfo/bimain.css">
<title>个人资料详细界面</title>
</head>
<body>
	<div id="head">
		<li id="crumbs"><span>个人中心</span> > <span>个人资料</span></li>
	</div>
	<!-- 个人主体信息 -->
	<div id="pic">
		<div class="img">
			<img class="img"
				src="${pageContext.request.contextPath}/assets/avatars/loyer.png"
				alt="未上传照片" />
		</div>
	</div>
	<div id="info">
		<form action="">
			<table>
				<th></th>
				<tbody>
					<tr>
						<td align="right"><label>姓名:</label></td>
						<td><label class="txt">${user.name}</label></td>
						<td align="right"><label>学号:</label></td>
						<td><label class="txt">${user.number }</label></td>
					</tr>
					<tr>
						<td align="right"><label>性别:</label></td>
						<td><label class="txt">男</label></td>
						<td align="right"><label>名族:</label></td>
						<td><label class="txt">白</label></td>
					</tr>
					<tr>
						<td align="right"><label>证件号码:</label></td>
						<td><label class="txt">522423199412069778</label></td>
						<td align="right"><label>专业:</label></td>
						<td><label class="txt">计算机科学与技术</label></td>
					</tr>
					<tr>
						<td align="right"><label>班级:</label></td>
						<td><label class="txt">计算机151</label></td>
						<td align="right"><label>学历:</label></td>
						<td><label class="txt">本科</label></td>
					</tr>
					<tr>
						<td align="right"><label>出生日期:</label></td>
						<td><label class="txt">1994-12-6</label></td>
						<td align="right"><label>入学时间:</label></td>
						<td><label class="txt">2015-09-01</label></td>
					</tr>
					<tr>
						<td align="right"><label>联系电话:</label></td>
						<td><label class="txt">${user.phone }</label></td>
						<td align="right"><label>家庭住址:</label></td>
						<td><label class="txt">贵州省黔西县拉土村水草沟二组</label></td>
					</tr>
					<tr>
						<td align="right"><label>备注:</label></td>
						<td><textarea></textarea></td>
					</tr>
				</tbody>

			</table>
		</form>
	</div>
</body>
</html>