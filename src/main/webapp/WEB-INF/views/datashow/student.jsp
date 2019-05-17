<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>学生数据统计</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/title.jpg" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/datashow/myecharts.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/fun.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/echarts.js"></script>
</head>
<body class="background-pic">
	<!-- 各角色数据统计开始 -->
	<div id="allmemberArea">
		<div id="allmember"></div>
	</div>
	<!-- 各角色数据统计结束 -->
	<!-- 学生展示开始 -->
	<div id="studentArea">
		<div id="search">
			<form class="form-inline">
				<div class="form-group">
					<label for="facultySel">学院</label> <select class="form-control"
						id="facultySel"></select>
				</div>
				<div class="form-group">
					<label for="majorSel">专业</label> <select class="form-control"
						id="majorSel"></select>
				</div>
				<div class="form-group">
					<label for="stuClassSel">班级</label> <select class="form-control"
						id="stuClassSel"></select>
				</div>
				<button type="button" class="btn btn-primary" id="go">搜索</button>
			</form>
		</div>
		<div id="studentChart"></div>
	</div>
	<!-- 学生展示结束 -->

	<!-- 学生地理分布开始 -->
	<!-- <div id="studentMapArea">
		<div id="studentMap"></div>
	</div> -->
	<!-- 学生地理分布结束 -->
	<script>
		var path = "${pageContext.request.contextPath}";
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/datashow/student.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/datashow/allmember.js"></script>
	<%-- <script type="text/javascript"
		src="${pageContext.request.contextPath }/js/datashow/studentMap.js"></script> --%>
</body>
</html>
