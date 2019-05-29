<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<style type="text/css">
.addrSel {
	width: 28%;
	margin-left: -0.5%;
}
li{
   list-style-type :none;
}

</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/courseware/add.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/common/public.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/iconfont.css">	
<title>实践新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs"><span>实践管理</span> > <c:if
				test="${operate=='add' || operate == null}">
				<span>实践新增</span>
			</c:if> <c:if test="${operate=='update'}">
				<span>实践修改</span>
			</c:if> <c:if test="${operate=='show'}">
				<span>实践查看</span>
			</c:if></li>
	</div>
	<form id="col_form" method="post"
		action="${pageContext.request.contextPath}/studentpractice/edit"
		enctype="multipart/form-data">
		<table>
			<input type="hidden" name="id" value="${studentPractice.id}">
			<tr>
				<td><label>实践事项：</label><input name="itemName"
					id="itemName" required="required" value="${studentPractice.itemName }"/></td>

			</tr>
			<tr>
				<td><label>获奖情况：</label><select name="prizeSituation"
					id="prizeSituation" required="required" style="width: 30%;">
						<option value=''>无</option>
						<option <c:if test="${studentPractice.prizeSituation=='优秀奖'}">selected</c:if>>优秀奖</option>
						<option <c:if test="${studentPractice.prizeSituation=='先进个人'}">selected</c:if>>先进个人</option>
						<option <c:if test="${studentPractice.prizeSituation=='特等奖'}">selected</c:if>>特等奖</option>
						<option <c:if test="${studentPractice.prizeSituation=='一等奖'}">selected</c:if>>一等奖</option>
						<option <c:if test="${studentPractice.prizeSituation=='二等奖'}">selected</c:if>>二等奖</option>
						<option <c:if test="${studentPractice.prizeSituation=='三等奖'}">selected</c:if>>三等奖</option>
						<option <c:if test="${studentPractice.prizeSituation=='其他'}">selected</c:if>>其他</option>
				</select></td>
			</tr>


			<tr>
				<td><label>对应操评：</label><input name="practiceScore" id="practiceScore"
					value="${studentPractice.practiceScore }" required="required" style="width: 30%;margin-top: 3%;"></td>
			</tr>
			<tr style="display: none">
				<td><label>审核状态：</label><input name="checkState" id="checkState"
					value="${studentPractice.checkState}" style="width: 30%;margin-top: 3%;"></td>
			</tr>
			<tr>
				<td><label>实践时间：</label><input type="date" name="date" id="praceticeDate"
					 required="required" style="width: 30%;margin-top: 3%;" autocomplete="off"></td>
			</tr>
			<tr>
				<td><label>相关证书：</label><input name="file"
					type="file" style="border:1px solid skyblue;width: 30%;margin-top: 3%;"><label id="fileName"></label><button type="button" onclick="javascript:download('${studentPractice.certificatePath}')">查看</button></td>
			</tr>	
			<tr>
				<td><label style="margin-top: 2%;">备注：</label> <textarea
						rows="" cols="" name="file" id="practiceRemark">${studentPractice.practiceRemark }</textarea>
				</td>
			</tr>
		</table>
		<div id="btn">
			<button type="submit" id="submitBtn">提交</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">

	var date = "${studentPractice.praceticeDate }";
	if(date.length>0){
		$("#praceticeDate").val(crtTimeFtt(date));
	}
	
	var path = "${studentPractice.certificatePath}";
	if(path.length>0){
	     var obj=path.lastIndexOf("/");
	     var fileName = path.substr(obj+1);
	     $("#fileName").text(fileName);	
	}
	var operate = "${operate}";
	if (operate == "add" || operate==null || operate=="") {
		$("#filed").remove();
	}
	//文件下载
	function download(path){
		window.location.href="${pageContext.request.contextPath}/downLoad?path="+path;
	}
	//文件删除
	/* function deleteFile(id){
		$.post("${pageContext.request.contextPath}/studentPractice/deleteAttachment",{
			"id":id,
		},function(result){
			getAttachments();
		})
	} */
	//时间格式化
	function crtTimeFtt(val) {
		if (val != null) {
			var date = new Date(val);
			var mm = date.getMonth() + 1;
			if(mm<10){
				mm = "0"+mm;
			}
			var dd = date.getDate();
			if(dd<10){
				dd = "0"+dd;
			}
			return date.getFullYear() + '-' + mm + '-'
					+ dd;
		}
	}
</script>
</html>