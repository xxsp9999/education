<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager/add.css">
<meta charset="UTF-8">
<title>管理员信息新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>管理员</span>
			>
			<c:if test="${manager.id==null}"><span>管理员信息新增</span></c:if>
			<c:if test="${manager.id!=null}"><span>管理员信息修改</span></c:if>
		</li>
	</div>
	<form id="stu_form" method="post" action="${pageContext.request.contextPath}/manager/edit" enctype="multipart/form-data">
		<div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div>
		<table>
			<input type="hidden" value="${manager.id}" name="id">
			<tr>
				<td>
					<label class="letter">姓名：</label><input name="managerName" id="managerName" value="${manager.managerName }" class="notNull"> 
				</td>
				<td>
					<label class="letter">工号：</label><input name="managerNumber" id="managerNumber" value="${manager.managerNumber }" class="notNull">
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">性别：</label><select name="managerSex" id="managerSex" class="notNull">
						<option value="">请选择</option>
						<option value="男" <c:if test="${manager.managerSex=='男'}">selected</c:if> >男</option>
						<option value="女" <c:if test="${manager.managerSex=='女'}">selected</c:if> >女</option>
					</select>
				</td>
				<td>
					<label class="letter">民族：</label><select name="managerNationality" id="managerNationality" class="notNull">
						<option value="">请选择</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label class="letter">电话：</label><input name="managerPhone" id="managerPhone" class="notNull" value="${manager.managerPhone }"> 
				</td>
				<td>
					<label class="letter">邮箱：</label><input name="managerEmail" id="managerEmail" class="notNull" value="${manager.managerEmail }"> 
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
					<label>入职时间：</label><input name="managerEntranceDate" id="managerEntranceDate" class="notNull"><span class="timePic iconfont icontime"></span> 
				</td>
				<td>
					<label>出生日期：</label><input name="managerBirth" id="managerBirth" class="notNull"><span class="timePic2 iconfont icontime"></span> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topId">身份证号：</label><input name="managerId" id="managerId" value="${manager.managerId }" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr>
				<td class="fixLength">
					<label class="topAddr">家庭住址：</label><input name="managerAddr" id="managerAddr" value="${manager.managerAddr}" class="notNull tmpLength"> 
				</td>
			</tr>
			<tr >
				<td colspan="2">
					<label>备注：</label>
					<textarea rows="" cols="" name="managerRemark">${manager.managerRemark}</textarea> 
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manager/add.js"></script>
<script type="text/javascript">
	//标签非空判断标识 前提标签有class:notNull
	$(".notNull").attr("required","required");
	
	//民族处理
	var tmpNation = "${manager.managerNationality}";
	getNationals(tmpNation,"managerNationality");
	
	/** 重置和返回按钮控制*/
	var operate = "${operate}";
	if(operate==null || operate==""){
		$("#backBtn").remove();
	}else{
		$("#resetBtn").remove();
	}
	
	//设置入职时间
	var managerEntranceDate = "${manager.managerEntranceDate}";
	if(managerEntranceDate!=null&&managerEntranceDate.length>0){
		$("#managerEntranceDate").val(crtTimeFtt(managerEntranceDate));
	}
	
	//设置出生日期
	var managerBirth = "${manager.managerBirth}";
	if(managerBirth!=null&&managerBirth.length>0){
		$("#managerBirth").val(crtTimeFtt(managerBirth));
	}
	
	//入职时间控件
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		laydate.render({
			elem : '#managerEntranceDate'
		});
	})
	//出生日期控件
	layui.use('laydate', function() {
		var laydate = layui.laydate;
		laydate.render({
			elem : '#managerBirth'
		});
	})
</script>
</html>