<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"%>
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
	href="${pageContext.request.contextPath}/css/company/add.css">
<meta charset="UTF-8">
<title>企业信息新增</title>
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
	<form id="col_form" method="post"
		action="${pageContext.request.contextPath}/company/companyEdit"
		enctype="multipart/form-data">
		<!-- <div>
			<p class="module">1</p>
			<p class="moduleTitle">手动添加</p>
		</div> -->
		<table>
			<input type="hidden" name="id" value="${company.id}">
			<tr>
				<td><label class="letter">登陆账号：</label><input
					name="companyNumber" id="companyNumber"
					value="${company.companyNumber}" required="required"></td>
				<td><label class="letter">企业名称：</label><input
					name="companyName" id="companyName" value="${company.companyName}"
					required="required"></td>
			</tr>
			<tr>
				<td><label class="letter">联系电话：</label><input name="companyTel"
					id="companyTel" value="${company.companyTel}" required="required">
				</td>
				<td><label class="letter">企业邮箱：</label><input
					name="companyEmail" id="companyEmail"
					value="${company.companyEmail}" required="required"></td>
			</tr>
			<tr>
				<td><label style="width: 23.5%;">统一社会信用代码：</label><input
					name="uscc" id="uscc" value="${company.uscc}" required="required">
				</td>
				<td><label style="width: 24%;">组织机构代码：</label><input
					name="organizationCode" id="organizationCode"
					value="${company.organizationCode}"></td>
			</tr>
			<tr>
				<td><label class="letter">企业网址：</label><input
					name="companyWebsite" id="companyWebsite"
					value="${company.companyWebsite}"></td>
				<td></td>
			</tr>
			<tr>
				<td><label>企业Logo：</label><input type="file" name="logoFile"
					class="headImg"></td>
				<td>
					<c:if test="${company!=null&&company.companyLogo!=null}">
						<button type="button" onclick="javascript:download('${company.companyLogo}')">查看上传文件</button>
					</c:if>
					<c:if test="${company!=null&&company.companyLogo==null}">
						<label style="color: red">未上传文件</label>
					</c:if>
				</td>
					
			</tr>
			<tr>
				<td><label style="width: 23%;">营业执照附件：</label><input
					type="file" name="licenseFile" class="headImg"
					style="margin-top: -11%;"></td>
				<td>
					<c:if test="${company!=null&&company.companyLicense!=null}">
						<button type="button" onclick="javascript:download('${company.companyLicense}')">查看上传文件</button>
					</c:if>
					<c:if test="${company!=null&&company.companyLicense==null}">
						<label style="color: red">未上传文件</label>
					</c:if>
				</td>
			</tr>

			<tr>
				<td colspan="2" style="width: 800px;"><label class="topAddr">企业地址：</label>
					<select name="provinceId" id="provinceSel" class="addrSel"></select>
					<select name="cityId" id="citySel" class="addrSel"></select> <select
					name="countyId" id="countySel" class="addrSel"></select></td>
			</tr>
			<tr>
				<td class="fixLength"><label class="topAddr">详细住址：</label><input
					name="companyAddress" id="companyAddress"
					value="${company.companyAddress}" class="tmpLength"
					required="required"></td>
			</tr>
			<tr style="width: 1000px">
				<td style="width: 1000px"><label class="letter">公司介绍：</label> <textarea
						id="editor" name="companyIntroduce" type="text/plain"
						style="width: width: 810px; margin: -2% 8.5%;">${company.companyIntroduce}</textarea>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td><label style="margin-top: 2%;">备注：</label> <textarea
						rows="" cols="" name="companyRemark" id="companyRemark">${company.companyRemark}</textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	//$("#companyRemark").val("");//清空文本框

	var operate = "${operate}";
	if (operate == "show") {
		$("#submitBtn").remove();
	}
	//提示错误信息
	//getErrorMsg();
	//文件下载
	function download(path){
		window.location.href="${pageContext.request.contextPath}/downLoad?path="+path;
	}
	//获取地址
	var provinceId = "${student.stuProvince.id}";
	var cityId = "${student.stuCity.id}";
	var countyId = "${student.stuCounty.id}";
	getProvinces("provinceSel", provinceId, "citySel", cityId, "countySel",
			countyId);
</script>
</html>