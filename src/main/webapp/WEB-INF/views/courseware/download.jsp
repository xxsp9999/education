<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
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
<meta charset="UTF-8">
<title>课件新增</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs"><span>课件管理</span> >
				<span>课件下载</span>
		</li>
	</div>
	<form id="col_form" method="post"
		action="${pageContext.request.contextPath}/courseware/edit"
		enctype="multipart/form-data">
		<table>
			<input type="hidden" name="id" value="${courseware.id}">
			<tr>
				<td><label>对应课程：</label><input name="teaCourseId"
					id="teaCourseId" required="required" style="width: 30%;" value="${courseware.csTc.tcCourse.cName}"></td>
			</tr>
			
			<tr id="filed">
				<td><label>已传文件:</label>
					<div id="fileDiv">
					</div>
				</td>
				
				
			</tr>
			<tr>
				<td><label>课件说明：</label><input name="explain" id="explain"
					value="${courseware.explain }" required="required" style="margin-top: 3%;"></td>
			</tr>
			<tr>
				<td><label style="margin-top: 2%;">备注：</label> <textarea
						rows="" cols="" name="csremark" id="Csremark">${courseware.csremark }</textarea>
				</td>
			</tr>
		</table>
		<div id="btn">
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	//$("#companyRemark").val("");//清空文本框

	var coursewareId = "${courseware.id}";
	var tmpId = "${courseware.csTc.id}"
	
	//文件下载
	function download(path){
		window.location.href="${pageContext.request.contextPath}/downLoad?path="+path;
	}
	//文件删除
	function deleteFile(id){
		$.post("${pageContext.request.contextPath}/courseware/deleteAttachment",{
			"id":id,
		},function(result){
			getAttachments();
		})
	}
	if(coursewareId!=""){
		getAttachments();
	}
	//获取文件
	function getAttachments(){
		$.post("${pageContext.request.contextPath}/courseware/getAttachments",{
			"coursewareId":coursewareId,
		},function(result){
			if(result.length==0){
				$("#filed").remove();
			}else{
				$("#fileDiv").empty();
				var str = "";
				for(var i=0;i<result.length;i++){
					str+="<label>"+result[i].fileName+"</label><button type='button' onclick=javascript:download('"+result[i].path+"')>下载</button>";
				}
				$("#fileDiv").append(str);
			}
		})
	}
</script>
</html>