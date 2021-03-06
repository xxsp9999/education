<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/images/title.jpg" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-3.3.1.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css" />
<!-- 有可能控制后台主页 -->
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/index.css" /> --%>
<!-- ace styles -->


<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/assets/css/ace-rtl.min.css" />
<!-- iconfont -->
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/index/js/iconfont.css"> --%>

<!-- layui -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/index/js/layui/css/layui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/index/js/layui/css/modules/layer/default/layer.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/index/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/index/js/layui/lay/modules/layer.js"></script>
<!-- bootstrap & fontawesome -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery-ui.custom.min.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
<!-- swal弹框 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/sweetAlert/css/sweet-swal.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/sweetAlert/js/sweet-swal.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/iconfont.css">

<!-- ace settings handler -->

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/publicJS/drag/js/css/normalize.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/js/publicJS/drag/js/css/default.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/publicJS/drag/js/dist/draggabilly.pkgd.min.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common/fun.js"></script>


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/common/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/common/jqGrid/css/css/ui-lightness/jquery-ui-1.8.16.custom.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/jqGrid/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/common/jqGrid/js/jquery.jqGrid.src.js"></script>


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/common/public.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/common/public.js"></script>

<!-- path -->
<script>
	path = "${pageContext.request.contextPath }";
	loginState = "${user}";
</script>
</head>
</html>