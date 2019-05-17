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
	href="${pageContext.request.contextPath}/css/student/add.css">
<meta charset="UTF-8">
<title>学生信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs"><span>学生</span> > <c:if
				test="${student.id==null}">
				<span>学生信息新增</span>
			</c:if> <c:if test="${student.id!=null}">
				<span>学生信息修改</span>
			</c:if></li>
	</div>
	<form id="stu_form" method="post"
		action="${pageContext.request.contextPath}/student/edit"
		enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" value="${student.id}" name="id">
			<tr>
				<td><label class="letter">姓名：</label><input name="stuName"
					id="stuName" value="${student.stuName }" class="notNull"></td>
				<td><label class="letter">学号：</label><input name="stuNumber"
					id="stuNumber" value="${student.stuNumber }" class="notNull">
				</td>
			</tr>
			<tr>
				<td><label class="letter">性别：</label><select name="stuSex"
					id="stuSex" class="notNull">
						<option value="">请选择</option>
						<option value="男"
							<c:if test="${student.stuSex=='男'}">selected</c:if>>男</option>
						<option value="女"
							<c:if test="${student.stuSex=='女'}">selected</c:if>>女</option>
				</select></td>
				<td><label class="letter">民族：</label><select
					name="stuNationality" id="stuNationality" class="notNull">
						<option value="">请选择</option>
				</select></td>
			</tr>
			<tr>
				<td><label class="letter">电话：</label><input name="stuPhone"
					id="stuPhone" class="notNull" value="${student.stuPhone }">
				</td>
				<td><label class="letter">邮箱：</label><input name="stuEmail"
					id="stuEmail" class="notNull" value="${student.stuEmail }">
				</td>
			</tr>
			<tr>
				<td><label class="letter">学院：</label><select name="facultyId"
					id="faculty" class="notNull"></select></td>
				<td><label class="letter">专业：</label><select name="majorId"
					id="major"></select></td>
			</tr>
			<tr>
				<td><label class="letter">年级：</label><select name="gradeId"
					id="gradeSel" class="notNull"></select></td>
				<td><label class="letter">班级：</label><select name="stuClassId"
					id="stuClassId" class="notNull"></select></td>
			</tr>
			<tr>
				<td><label class="letter">照片：</label><input type="file"
					name="img" class="headImg"></td>
				<td></td>
			</tr>
			<tr>
				<td><label>入学时间：</label><input name="stuEntranceDate"
					id="stuEntranceDate" class="notNull"><span
					class="timePic iconfont icontime"></span></td>
				<td><label>出生日期：</label><input name="stuBirth" id="stuBirth"
					class="notNull"><span class="timePic2 iconfont icontime"></span>
				</td>
			</tr>
			<tr>
				<td class="fixLength"><label class="topId">身份证号：</label><input
					name="stuId" id="stuId" value="${student.stuId }"
					class="notNull tmpLength"></td>
			</tr>
			<tr>
				<td colspan="2" style="width: 800px;"><label class="topAddr">家庭住址：</label>
					<select name="provinceId" id="provinceSel" class="addrSel"></select>
					<select name="cityId" id="citySel" class="addrSel"></select> <select
					name="countyId" id="countySel" class="addrSel"></select></td>
			</tr>
			<tr>
				<td class="fixLength"><label class="topAddr">详细住址：</label><input
					name="stuAddr" id="stuAddr" value="${student.stuAddr}"
					class="notNull tmpLength"></td>
			</tr>
			<tr>
				<td colspan="2"><label>备注：</label> <textarea rows="" cols=""
						name="stuRemark">${student.stuRemark}</textarea></td>
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
			<button type="submit" id="submitBtn">提交</button>
			<button type="reset" id="resetBtn">重置</button>
			<button type="button" onclick="javascript:history.back()"
				id="backBtn">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/student/add.js"></script>
<script type="text/javascript">
	//标签非空判断标识 前提标签有class:notNull
	$(".notNull").attr("required", "required");

	//民族处理
	var tmpNation = "${student.stuNationality}";
	getNationals(tmpNation, "stuNationality");

	/**获取学院 专业 班级*/
	var facId = "${student.stuFaculty.id}";
	var majId = "${student.stuMajor.id}";
	var eduClassId = "${student.stuClass.id}";
	getFuculties("faculty",facId,majId,"major","stuClassId",eduClassId);
	/** 重置和返回按钮控制*/
	var operate = "${operate}";
	if (operate == null || operate == "") {
		$("#backBtn").remove();
	} else {
		$("#resetBtn").remove();
	}

	//设置入学时间
	var stuEntranceDate = "${student.stuEntranceDate}";
	if (stuEntranceDate != null && stuEntranceDate.length > 0) {
		$("#stuEntranceDate").val(crtTimeFtt(stuEntranceDate));
	}

	//设置出生日期
	var stuBirth = "${student.stuBirth}";
	if (stuBirth != null && stuBirth.length > 0) {
		$("#stuBirth").val(crtTimeFtt(stuBirth));
	}
	//获取年级
	var gradeId = "${student.stuClass.claGrade.id}";
	getGrades("gradeSel",gradeId,"stuClassId",eduClassId);
	//获取地址
	var provinceId = "${student.stuProvince.id}";
	var cityId = "${student.stuCity.id}";
	var countyId = "${student.stuCounty.id}";
	getProvinces("provinceSel",provinceId,"citySel",cityId,"countySel",countyId);
</script>
</html>