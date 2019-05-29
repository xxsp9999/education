<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/student/score.css" />
</head>
<body>
	<div id="head">
		<li id="crumbs"><span>学生信息</span> >
			
				<span>推荐学生信息</span>
		</li>
	</div>
	<div>

		<div id="baseInfo">
			<table>
				<h3>基本信息</h3>
				<tr>
					<td>姓名</td>
					<td>${student.stuName}</td>
					<td>学号</td>
					<td>${student.stuNumber}</td>
				</tr>
				<tr>
					<td>学院</td>
					<td>${student.stuFaculty.facName}</td>
					<td>专业</td>
					<td>${student.stuMajor.majorName}</td>
				</tr>
				<tr>
					<td>年级</td>
					<td>${student.stuClass.claGrade.gradeName}</td>
					<td>班级</td>
					<td>${student.stuClass.claName}</td>
				</tr>
				<tr>
					<td>电话</td>
					<td>${student.stuPhone}</td>
					<td>邮箱</td>
					<td>${student.stuEmail}</td>
				</tr>
			</table>
		</div>
		<div>
			<table id="scoreInfo">
				<h3>成绩信息表</h3>
			</table>
		</div>
		<div>
			<table id="practiceInfo">
				<h3>实践信息</h3>
			</table>
		</div>
		<br/>
		<div style="text-align: center;">
			<button class="btn btn-primary" id="print">打印</button>
			<button class="btn btn-default" onclick="javascript:history.back()" id="back">返回</button>
		</div>
		<br/>
	</div>
	<script type="text/javascript">
		//获取成绩信息
		$.post("${pageContext.request.contextPath}/course/getStudentScores",{
			"stuId":"${student.id}",
		},function(result){
			$("#scoreInfo").empty();
			var str="<tr><th>课程号</th><th>课程名</th><th>成绩</th><th>状态</th><th>备注</th></tr>"
			for(var i=0;i<result.length;i++){
				str += "<tr><td>"+result[i].cNo+"</td><td>"+result[i].cName+"</td><td>"+result[i].scScore+"</td><td>"+result[i].scState+"</td><td>"+result[i].scRemark+"</td></tr>";
			}
			debugger;
			$("#scoreInfo").append(str);
		})
		
		//获取参赛信息
		$.post("${pageContext.request.contextPath}/studentpractice/getPractice",{
			"stuId":"${student.id}",
		},function(result){
			debugger;
			$("#practiceInfo").empty();
			var str="<tr><th>实践事项</th><th>获奖情况</th><th>证书</th></tr>"
			for(var i=0;i<result.length;i++){
				str += "<tr><td>"+result[i].itemName+"</td><td>"+result[i].prizeSituation+"</td><td><button onclick=javascript:download('"+result[i].certificatePath+"') >查看证书</button></td></tr>";
			}
			$("#practiceInfo").append(str);
		})
		function download(path){
			if(path.length==0){
				swal("","未上传证书","error");
				return false;
			}
			window.location.href="${pageContext.request.contextPath}/downLoad?path="+path;
		}
		$("#print").click(function(){
			$("#back").hide();
			$("#print").hide();
			window.print();
		})
	</script>
</body>
</html>