<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/teacher/add.css">
<meta charset="UTF-8">
<title>老师信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>老师</span>
			>
			<c:if test="${teacher.id==null}"><span>老师信息新增</span></c:if>
			<c:if test="${teacher.id!=null}"><span>老师信息修改</span></c:if>
		</li>
	</div>
	<form id="stu_form" method="post" action="${pageContext.request.contextPath}/teacher/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" value="${teacher.id}" name="id">
			<tr>
				<td>
					<label class="letter">姓名：</label><input name="teaName" id="teaName" value="${teacher.teaName }" class="notNull"> 
				</td>
				<td>
					<label class="letter">工号：</label><input name="teaNumber" id="teaNumber" value="${teacher.teaNumber }" class="notNull">
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">性别：</label><select name="teaSex" id="teaSex" class="notNull">
						<option value="">请选择</option>
						<option value="男" <c:if test="${teacher.teaSex=='男'}">selected</c:if> >男</option>
						<option value="女" <c:if test="${teacher.teaSex=='女'}">selected</c:if> >女</option>
					</select>
				</td>
				<td>
					<label class="letter">民族：</label><select name="teaNationality" id="teaNationality" class="notNull">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">电话：</label><input name="teaPhone" id="teaPhone" class="notNull" value="${teacher.teaPhone }"> 
				</td>
				<td>
					<label class="letter">邮箱：</label><input name="teaEmail" id="teaEmail" class="notNull" value="${teacher.teaEmail }"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">职称：</label><select name="teaTitleId" id="teaTitle" class="notNull"></select> 
				</td>
				<td></td>
			</tr>
			<tr>
				<td>
					<label>入职时间：</label><input name="teaEntranceDate" id="teaEntranceDate" class="notNull"><span class="timePic iconfont icontime"></span> 
				</td>
				<td>
					<label>出生日期：</label><input name="teaBirth" id="teaBirth" class="notNull"><span class="timePic2 iconfont icontime"></span> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topId">身份证号：</label><input name="teaId" id="teaId" value="${teacher.teaId }" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topAddr">家庭住址：</label><input name="teaAddr" id="teaAddr" value="${teacher.teaAddr}" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<label>备注：</label>
					<textarea rows="" cols="" name="stuRemark">${teacher.teaRemark}</textarea> 
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
			<button type="submit" id="submitBtn">提交</button>
			<button type="reset" id="resetBtn">重置</button>
			<button type="button" onclick="javascript:history.back()" id="backBtn">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/add.js"></script>
<script type="text/javascript">
	//标签非空判断标识 前提标签有class:notNull
	$(".notNull").attr("required","required");
	
	//民族处理
	var tmpNation = "${teacher.teaNationality}";
	getNationals(tmpNation,"teaNationality");

	/**获取职称 */
	$.ajax({
		url:path+"/teatitle/getAllTitles",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#teaTitle").empty();
			var str = "<option value=''>请选择</option>";
			var teaTitle = "${teacher.teaTitle.id}";
			for(var i=0;i<data.length;i++){
				if(teaTitle == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].titleName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].titleName+"</option>";
				}
			}
			$("#teaTitle").append(str);
		},
		error:function(){
			swal("","获取职称失败","error");
		}
	})
	
	/** 重置和返回按钮控制*/
	var operate = "${operate}";
	if(operate==null || operate==""){
		$("#backBtn").remove();
	}else{
		$("#resetBtn").remove();
	}
	
	//设置入学时间
	var teaEntranceDate = "${teacher.teaEntranceDate}";
	if(teaEntranceDate!=null&&teaEntranceDate.length>0){
		$("#teaEntranceDate").val(crtTimeFtt(teaEntranceDate));
	}
	
	//设置出生日期
	var teaBirth = "${teacher.teaBirth}";
	if(teaBirth!=null&&teaBirth.length>0){
		$("#teaBirth").val(crtTimeFtt(teaBirth));
	}
</script>
</html>