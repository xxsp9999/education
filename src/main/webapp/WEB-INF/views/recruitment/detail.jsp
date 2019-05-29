<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.addrSel {
	width: 28%;
	margin-left: -0.5%;
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/recruitment/add.css">
<meta charset="UTF-8">
<title>招聘信息新增</title>
<!-- 富文本框 要用的时候才去引入相应的js,否则会报"cannot read property offsetleft'of null"错误-->
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/ueditor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath }/ueditor/public.js"></script>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>招聘信息</span> > 
			<span>招聘信息查看</span>
		</li>
	</div>
	<form id="col_form" method="post"
		action="${pageContext.request.contextPath}/company/recruitmentEdit"
		enctype="multipart/form-data">
		<table>
			<input type="hidden" name="id" value="${recruitment.id}">
			<tr>
				<td><label class="letter">招聘单位：</label><input
					name="companyId" readonly="readonly"
					value="${recruitment.rcCompany.companyName}"/></td>
				<td><label class="letter">招聘职位：</label><input
					name="rcJob" id="rcJob" value="${recruitment.rcJob}"
					required="required" readonly="readonly"></td>
			</tr>
			<tr>
				<td><label class="letter">联系电话：</label><input
					name="companyId" readonly="readonly"
					value="${recruitment.rcCompany.companyTel}"/></td>
				<td><label class="letter">联系邮箱：</label><input
					name="rcJob" id="rcJob" value="${recruitment.rcCompany.companyEmail}"
					required="required" readonly="readonly"></td>
			</tr>
			
			<tr style="width: 1000px">
				<td style="width: 1000px"><label class="letter">职位描述：</label>
    				<div style="margin-left: 8.5%;width: 83%;height: auto;background: white;">${recruitment.rcContent }</div>
    			</td>
			</tr>
			<tr>
				<td><label class="letter">公司网站：</label><a
					href="${recruitment.rcCompany.companyWebsite}" target="_blank">${recruitment.rcCompany.companyWebsite}</a></td>
				<td></td>
			</tr>
			<tr></tr>
			<tr>
				<td><label style="margin-top: 4%;">备注：</label> <textarea rows="" cols=""
						name="recruitmentRemark" id="recruitmentRemark" readonly="readonly">${recruitment.rcRemark}</textarea>
				</td>
			</tr>
		</table>
		<div id="btn">
			<button type="button" id="submitBtn">投递</button>
			<button type="button" onclick="javascript:history.back()">返回</button>
		</div>
	</form>
</body>
<script type="text/javascript">
	$("#submitBtn").click(function(){
		layer.alert('确认投递吗？', {
		    skin: 'layui-layer-molv' //样式类名  自定义样式
		    ,closeBtn: 1    // 是否显示关闭按钮
		    ,anim: 1 //动画类型
		    ,btn: ['确认','取消'] //按钮
		    ,icon: 6    // icon
		    ,yes:function(){
		    	$("#modelClose").click();
				var courseId = $("#val").val();//课程id
				$.post("${pageContext.request.contextPath}/company/deliver",{
					"id":"${recruitment.id}",
				},function(result){
					layer.alert("投递成功！", {icon: 6}); 
				})
		    }
		    ,btn2:function(){
		    	 layer.closeAll();
		    }});
	})
</script>
</html>