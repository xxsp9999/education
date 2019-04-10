<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/student/add.css">
<meta charset="UTF-8">
<title>学生信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>学生</span>
			>
			<span>学生信息新增</span>
		</li>
	</div>
	<form id="stu_form">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<tr>
				<td>
					<label class="letter">姓名：</label><input name="stuName" id="stuName"> 
				</td>
				<td>
					<label class="letter">学号：</label><input name="stuNumber" id="stuNumber">
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">性别：</label>
					<select name="stuSex" id="stuSex">
						<option value="">请选择</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
				</td>
				<td>
					<label class="letter">民族：</label>
					<select name="stuNationality" id="national">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">电话：</label><input name="stuPhone" id="stuPhone"> 
				</td>
				<td>
					<label class="letter">邮箱：</label><input name="stuEmail" id="stuEmail"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">学院：</label><input name="stuAddr" id="stuAddr"> 
				</td>
				<td>
					<label class="letter">专业：</label><input name="stuMajor" id="stuMajor"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>入学时间：</label><input name="stuEntranceDate" id="stuEntranceDate"> 
				</td>
				<td>
					<label>出生日期：</label><input name="stuBirth" id="stuBirth"> 
				</td>
			</tr>
			<tr>
				<td>
					<label>家庭住址：</label><input name="stuAddr" id="stuAddr"> 
				</td>
				<td>
					<label>身份证号：</label><input name="stuId" id="stuId"> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<label>备注：</label>
					<textarea rows="" cols="" name="remark"></textarea> 
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
			<button type="button">提交</button>
			<button type="reset">重置</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	//获取民族
	getNationals();
</script>
</html>