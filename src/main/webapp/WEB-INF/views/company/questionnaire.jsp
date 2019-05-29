<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/company/questionnaire.css" />
		<title>求职问卷调查</title>
	</head>
	<body>
		<!--面包屑  -->
		<div id="head">
				<li id="crumbs">
					<span>招聘信息</span>
					>
					<span>求职调查</span>
				</li>
		</div>
		<form id="container" method="post" action="${pageContext.request.contextPath}/company/questionnaireEdit">
			<table>
				<tr>
					<td colspan="2">求职问卷调查</td>
				</tr>
				<tr>
					<td>职业类型</td>
					<td><textarea name="qnType" id="qnType" placeholder="如技术，产品，设计，运营，市场，人才等，多个职业类型请用空格分隔" readonly="readonly" class="txt">${questionnaire.qnType }</textarea></td>
				</tr>
				<tr>
					<td>具体工作</td>
					<td><textarea name="qnContent" id="qnContent" placeholder="如java，php,web前端等，多个具体工作请用空格分隔" readonly="readonly" class="txt">${questionnaire.qnContent }</textarea></td>
				</tr>
				<tr>
					<td>工作地点</td>
					<td><textarea name="qnAddress" id="qnAddress" placeholder="如北京，上海，多个工作地点请用空格分隔" readonly="readonly" class="txt">${questionnaire.qnAddress }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><button type="button" id="btn">修改</button></td>
				</tr>
			</table>
		</form>
		<script type="text/javascript">
			$("#btn").click(function(){
				debugger;
				var btnName = $("#btn").text();
				if(btnName=="修改"){
					$(".txt").attr("readonly",false);
					$("#btn").text("保存");
				}else{
					var qnType = $("#qnType").val();
					var qnContent = $("#qnContent").val();
					var qnAddress = $("#qnAddress").val();
					if(qnType.length==0){
						swal("","职业类型不能为空！","error");
						return false;
					}
					if(qnContent.length==0){
						swal("","具体工作不能为空！","error");
						return false;
					}
					if(qnAddress.length==0){
						swal("","工作地点不能为空！","error");
						return false;
					}
					$("#container").submit();
				}
			})
		</script>
	</body>
</html>