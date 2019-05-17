<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/class/add.css">
<meta charset="UTF-8">
<title>班级信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>班级信息</span>
			>
			<c:if test="${operate=='add'}"><span>班级信息新增</span></c:if>
			<c:if test="${operate=='update'}"><span>班级信息修改</span></c:if>
			<c:if test="${operate=='show'}"><span>班级信息查看</span></c:if>
		</li>
	</div>
	<form id="col_form" method="post" action="${pageContext.request.contextPath}/educlass/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" name="id" value="${eduClass.id}">
			<tr>
				<td>
					<label class="letter">班级名称：</label><input name="claName" id="claName" value="${eduClass.claName}"> 
				</td>
				<td>
					<label class="letter">班级代码：</label><input name="claNumber" id="claNumber" value="${eduClass.claNumber }"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">所属年级：</label><select name="gradeId" id="gradeId"></select>
				</td>
				<td>
					<label class="letter">班主任：</label><select name="teaId" id="teaId" style="margin-left: 4.5%;"></select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">备注：</label>
					<textarea rows="" cols="" name="claRemark" id="claRemark">${eduClass.claRemark}
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
			<input type="file" name="stuFile" id="stuFile">
		</div>
		<div id="btn">
			<button type="button" id="submitBtn">提交</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	$("#claRemark").val("");//清空文本框
	
	var operate = "${operate}";
	if(operate == "show"){
		$("#submitBtn").remove();
	}
	//提示错误信息
	//getErrorMsg();
	
	//获取年级
	$.ajax({
		url:path+"/grade/getAllGrades",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#gradeId").empty();
			var str = "<option value=''>请选择</option>";
			var tmpId = "${eduClass.claGrade.id}";
			for(var i=0;i<data.length;i++){
				if(tmpId == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].gradeName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].gradeName+"</option>";
				}
			}
			$("#gradeId").append(str);
		},
		error:function(){
			swal("","获取年级失败","error");
		}
	})
	
	//获取班主任
	$.ajax({
		url:path+"/teacher/getAllTeachers",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#teaId").empty();
			var str = "<option value=''>请选择</option>";
			var tmpId = "${eduClass.claHeadteacher.id}";
			for(var i=0;i<data.length;i++){
				if(tmpId == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].teaName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].teaName+"</option>";
				}
			}
			$("#teaId").append(str);
		},
		error:function(){
			swal("","获取班主任失败","error");
		}
	})
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/class/add.js"></script>
</html>