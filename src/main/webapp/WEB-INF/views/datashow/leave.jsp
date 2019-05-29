<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>请假数据统计</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/title.jpg" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/datashow/myecharts.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/index/js/layui/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/iconfont.css">
<!-- swal弹框 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/sweetAlert/css/sweet-swal.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sweetAlert/js/sweet-swal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/fun.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/echarts.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/index/js/layui/layui.js"></script>
</head>
<body class="background-pic">
	<!-- 请假数据统计开始 -->
	<div id="leaveArea">
		<div id="search">
			<form class="form-inline">
				<div class="form-group">
					<label for="facultySel">学院</label> <select class="form-control" style="width:150px"
						id="facultySel"></select>
				</div>
				<div class="form-group">
					<label for="majorSel">专业</label> <select class="form-control" style="width:150px"
						id="majorSel" ></select>
				</div>
				<div class="form-group">
					<label for="gradeSel">年级</label> <select class="form-control" style="width:150px"
						id="gradeSel" ></select>
				</div>
				<div class="form-group">
					<label for="stuClassSel">班级</label> <select class="form-control" style="width:150px"
						id="stuClassSel" ></select>
				</div>
				<div class="form-group">
					<label for="leaveInfoType">请假类型</label> <select
						class="form-control" id="leaveInfoType" style="width:150px">
						<option value="">请选择</option>
						<option>事假</option>
						<option>病假</option>
						<option>法定假日</option>
					</select>
				</div>
				<br><br>
				<div class="form-group">
					<label for="stuClassSel">开始时间</label> <input type="text"
						name="start" id="start" class="form-control"> <span
						class="iconfont icontime" style="position: relative; right: 22px;"></span>
				</div>
				<div class="form-group">
					<label for="stuClassSel">结束时间</label> <input type="text" name="end"
						id="end" class="form-control"> <span
						class="iconfont icontime"
						style="position: relative;right: 23px;top: 2px;"></span>
				</div>
				<div class="form-group">
					<label for="stuClassSel">内容</label> <input type="text"
						name="content" id="content" class="form-control"> 
				</div>
				<button type="button" class="btn btn-primary" id="go">搜索</button>
			</form>
		</div>
		<div id="leave"></div>
		<div id="leave2"></div>
	</div>
	<!--请假数据统计结束 -->

	<script>
		var path = "${pageContext.request.contextPath}";
		//获取学院专业班级
		getFuculties("facultySel",null,null,"majorSel","stuClassSel",null);
		//获取年级
		getGrades("gradeSel",null,"stuClassSel",null);
		layui.use('laydate', function(){
			var laydate = layui.laydate;
			 laydate.render({
	    elem: '#start'
	    ,type: 'date',
	    done:function(value, date, endDate){
	    	debugger;
	    	value = value.replace(/-/g,"");
	    	value = value.replace(/:/g,"");
	    	value = value.replace(/ /g,"");
	    	var start=value;
	    	var end=$("#test4").val();
	    	end = end.replace(/-/g,"");
	    	end = end.replace(/:/g,"");
	    	end = end.replace(/ /g,"");
	    	if(start>=end&&end!=""){
	    		/*$("#test4").val("");
	    		$("#test3").val(end);*/
	    		//alert("格式不对");
	    		layui.use('layer', function(){//弹出框
					var layer = layui.layer;
					 layer.tips('违规的区间范围，请重新选择', '#test3',{
					 	tips:[3,'#000'],
					 });
				})//弹出框
	    		setTimeout(() => {
					$("#start").val("");
				}, 10);
	    	}
	    }
	  });
		})
		//结束时间插件
	layui.use('laydate', function(){
			var laydate = layui.laydate;
			 laydate.render({
	    elem: '#end'
	    ,type: 'date',
	    done:function(value, date, endDate){
	    	debugger;
	    	value = value.replace(/-/g,"");
	    	value = value.replace(/:/g,"");
	    	value = value.replace(/ /g,"");
	    	var end=value;
	    	var start=$("#test3").val();
	    	start = start.replace(/-/g,"");
	    	start = start.replace(/:/g,"");
	    	start = start.replace(/ /g,"");
	    	if(start>=end&&start!=""){
	    		layui.use('layer', function(){//弹出框
					var layer = layui.layer;
					 layer.tips('违规的区间范围，请重新选择', '#test4',{
					 	tips:[3,'#000'],
					 });
				})//弹出框	
	    		setTimeout(() => {
					$("#end").val("");
				}, 10);
	    	}
	    }
	  });
		})
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/datashow/leave.js"></script>
</body>
</html>
