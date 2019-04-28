<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/leader/add.css">
<meta charset="UTF-8">
<title>领导信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>领导</span>
			>
			<c:if test="${leader.id==null}"><span>领导信息新增</span></c:if>
			<c:if test="${leader.id!=null}"><span>领导信息修改</span></c:if>
		</li>
	</div>
	<form id="lea_form" method="post" action="${pageContext.request.contextPath}/leader/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" value="${leader.id}" name="id">
			<tr>
				<td>
					<label class="letter">姓名：</label><input name="leaderName" id="leaderName" value="${leader.leaderName }" class="notNull"> 
				</td>
				<td>
					<label class="letter">工号：</label><input name="leaderNumber" id="leaderNumber" value="${leader.leaderNumber }" class="notNull">
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">性别：</label><select name="leaderSex" id="leaderSex" class="notNull">
						<option value="">请选择</option>
						<option value="男" <c:if test="${leader.leaderSex=='男'}">selected</c:if> >男</option>
						<option value="女" <c:if test="${leader.leaderSex=='女'}">selected</c:if> >女</option>
					</select>
				</td>
				<td>
					<label class="letter">民族：</label><select name="leaderNationality" id="leaderNationality" class="notNull">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">电话：</label><input name="leaderPhone" id="leaderPhone" class="notNull" value="${leader.leaderPhone }"> 
				</td>
				<td>
					<label class="letter">邮箱：</label><input name="leaderEmail" id="leaderEmail" class="notNull" value="${leader.leaderEmail }"> 
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">职位：</label><select name="leaderTitle" id="leaderTitle" class="notNull"></select> 
				</td>
				<td>
					<label class="letter">照片：</label><input type="file" name="img" class="headImg">
				</td>
			</tr>
			<!-- <tr>
				<td>
					<label class="letter">学院：</label><select name="facultyId" id="faculty" class="notNull"></select> 
				</td>
				<td>
					<label class="letter">专业：</label><select name="majorId" id="major"></select>
				</td>
			</tr> -->
			<tr>
				<td>
					<label>入职时间：</label><input name="leaderEntranceDate" id="leaderEntranceDate" class="notNull"><span class="timePic iconfont icontime"></span> 
				</td>
				<td>
					<label>出生日期：</label><input name="leaderBirth" id="leaderBirth" class="notNull"><span class="timePic2 iconfont icontime"></span> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topId">身份证号：</label><input name="leaderId" id="leaderId" value="${leader.leaderId }" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topAddr">家庭住址：</label><input name="leaderAddr" id="leaderAddr" value="${leader.leaderAddr}" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<label>备注：</label>
					<textarea rows="" cols="" name="leaderRemark">${leader.leaderRemark}</textarea> 
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/leader/add.js"></script>
<script type="text/javascript">
	//标签非空判断标识 前提标签有class:notNull
	$(".notNull").attr("required","required");
	
	//民族处理
	var tmpNation = "${leader.leaderNationality}";
	getNationals(tmpNation,"leaderNationality");

	/**获取职称 */
	$.ajax({
		url:path+"/leadertitle/getAllTitles",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#leaderTitle").empty();
			var str = "<option value=''>请选择</option>";
			var leaderTitle = "${leader.leaderTitle.id}";
			debugger;
			for(var i=0;i<data.length;i++){
				if(leaderTitle == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].leaTitleName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].leaTitleName+"</option>";
				}
			}
			$("#leaderTitle").append(str);
		},
		error:function(){
			swal("","获取学院失败","error");
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
	var leaderEntranceDate = "${leader.leaderEntranceDate}";
	if(leaderEntranceDate!=null&&leaderEntranceDate.length>0){
		$("#leaderEntranceDate").val(crtTimeFtt(leaderEntranceDate));
	}
	
	//设置出生日期
	var leaderBirth = "${leader.leaderBirth}";
	if(leaderBirth!=null&&leaderBirth.length>0){
		$("#leaderBirth").val(crtTimeFtt(leaderBirth));
	}
	
	//入职时间控件
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		laydate.render({
			elem : '#leaderEntranceDate'
		});
	})
	//出生日期控件
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		laydate.render({
			elem : '#leaderBirth'
		});
	})
</script>
</html>