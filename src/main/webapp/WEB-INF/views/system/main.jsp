<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%-- ${pageContext.request.contextPath} --%>
<!DOCTYPE html>
<html lang="en" style="overflow-x: hidden">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>智能教育系统</title>

<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/title.jpg"/>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/public.css" />
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/publicJS/drag/js/css/normalize.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/publicJS/drag/js/css/default.css" />
	
<!-- jquery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/js/publicJS/drag/js/dist/draggabilly.pkgd.min.js"></script>
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jquery-ui.custom.min.css" />

<!-- text fonts -->
<!-- bootstrap & fontawesome -->

<!-- ace styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace.min.css" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-part2.min.css" />
	<![endif]-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace-skins.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/index.css" />



<!--[if lte IE 9]>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-ie.min.css" />
	<![endif]-->

<!-- inline styles related to this page -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/iconfont.css">
<!-- ace settings handler -->
<script
	src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
	<script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
	<![endif]-->
<!-- sweetAlert插件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/sweetAlert/js/sweet-swal.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/sweetAlert/css/sweet-swal.css" />
<style type="text/css">
#siderbar, #sidebar li, #sidebar  a, #sidebar  a:hover {
	color: white;
	border-color: #f2bd76;
	background-color: #316182;
}

#navbar {
	background: -moz-linear-gradient(left, #21406a, #4893a6);
	background: -webkit-linear-gradient(left, #21406a, #4893a6);
	background: -o-linear-gradient(left, #21406a, #4893a6);
}

.ace-nav>li>a:hover {
	opacity: 0.7;
}

.ace-nav>li>a:focus, .ace-nav>li.open>a {
	color: white;
}

.ace-nav>li {
	height: 60px;
	line-height: 60px;
	border: none;
}

.ace-nav>li>a {
	margin-top: 6px;
	margin-right: 8px;
	height: 50px;
	line-height: 80px;
}

.ace-nav>li>a>dt, .ace-nav>li>a>dd {
	text-align: center;
}

.user-info {
	vertical-align: 100%;
}

.main-content {
	margin-left: 43px;
}

.menu-min>ul>li>a>img {
	padding-left: 3px;
}

img {
	vertical-align: baseline;
}

.f_flipy {
	-moz-transform: scaleY(-1);
	-webkit-transform: scaleY(-1);
	-o-transform: scaleY(-1);
	transform: scaleY(-1);
	/*IE*/
	filter: FlipV;
}
#zhegai{width: 100%; height: 100%; position: absolute; z-index: 9991; top: 60px; display: none;}
#infoPrompt{width: 700px;height: 300px;border: 1px solid gray;position: absolute;
			margin-left: -350px;z-index:1000;left: 50%; top: 60px; box-shadow:0 0 5px #000 inset; opacity: 1;}
#infoTitle{background: white;}
#infoTitle label{display: inline-block; width: 660px; height: 30px; line-height: 30px; text-indent: 10px;}
#infoTitle label:hover{cursor: move}
#infoTitle span{display: inline-block; cursor: pointer; width: 30px; height: 30px; line-height: 30px; text-align:center;}
#infoContent{padding-bottom: 15px; overflow-y: scroll; width: 100%; height: 270px; background: #316182; padding-top: 10px;}
#infoContent p{line-height: 30px; text-indent: 20px; font-size: 18px; color: white; width: 90%; margin: 0 auto;}
.aData{width:60px; line-height: 20px; color: gray; font-size: 12px; background: #ececec; border: 1px solid gray; padding: 2px 5px;}

#infoNarrowzhegai{width: 100%; height: 100%; position: absolute; top: 60px; left: 15%;  z-index: 1000; display: none;}
#infoNarrow{display: none; width: 150px; height: 50px; background: #f0a669; color: white; position: absolute; top: 60px; right: 0;  z-index: 1001;}
#infoNarrow span{display: inline-block; width: 50px; line-height: 50px; text-align: center;}
#infoNarrow span:hover{cursor: move;}
#infoNarrow p{display: inline-block; font-size: 16px; line-height: 50px; width: 95px; cursor: pointer;}
</style>
</head>

<body class="no-skin" style="background-color: #2f3440; height: 800px; width: 100%; overflow-y: hidden; position: relative;">
	<!-- 信息缩小框 -->
	<!-- <div id="infoNarrowzhegai"></div>
	<div id="infoNarrow">
		<span id="infoNarrowTitle" class="iconfont icon-xiaoxi"></span>
		<p id="infoNarrowContent">提示消息</p>
	</div> -->
	<!-- 信息提示框-->
	
	<div id="zhegai">
		<div id="infoPrompt">
			<div id="infoTitle">
				<label>信息提示</label>
				<span class="iconfont icon-guanbi"></span>
			</div>
			<div id="infoContent">
			</div>
		</div>
	</div>
	
	
	<!--头部导航开始-->
	<div id="navbar" class="navbar navbar-default"
		style="color: white; height: 60px;">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<div class="navbar-header pull-left"
				style="width: 50%; vertical-align: middle">
				<%-- <a href="${pageContext.request.contextPath}/main"
					class="navbar-brand"
					style="height: 60px; line-height: 60px; width: 30%;"> <img style="width: 90%;height:48px;margin-top:-3px;display: block;" src="${pageContext.request.contextPath}/images/icon/logo2.png" alt="">
					<div style="width: 180px; height: 50px; position: absolute;">
						<span style="width: 50px; height: 50px; display: inline-block;">
							<img src="${pageContext.request.contextPath}/images/13.png"
							width="100%" height="100%";>
						</span> <span
							style="display: inline-block; font-size: 30px; height: 100%; line-height: 50px; width: 126px; position: relative; right: 0; top: -13px;">中国石油</span>
					</div>
				</a> --%>
				<img src="${pageContext.request.contextPath}/images/title.jpg" style="width: 47px;margin-top: 6px;border-radius: 5px;"/>
				<div style="display: inline-block; border: 1px solid #ffffff;width: 1px;height: 29px;margin-top: 13px; vertical-align: top;margin-right: 3px;    margin-left: 3px;"></div>
				<span style="display: inline-block;vertical-align: top;font-size: 26px;margin-top: 7px;">智能教育系统</span>
				<!-- <p style="float: left; height: 60px; width: 45%; font-size: 26px; font-weight: bold; line-height: 60px; margin: 0;margin-left:8%;">齐杉科技CRM系统</p>  -->
				
			</div>
			
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation" style="position: absolute; right: 0">
				<ul class="nav ace-nav">
					<li class="light-blue"><a data-toggle="dropdown" href="#"
						class="dropdown-toggle"> <img class="nav-user-photo"
							src="assets/avatars/loyer.png" alt="Jason's Photo"
							style="float: left; margin-top: 1px;" /> <span class="user-info"
							style="display: block; padding: auto 0; top: 12px;"> <span>欢迎您,</span>
								${loginInfo.userName}
						</span> <i class="ace-icon fa fa-caret-down"
							style="position: relative; top: -22px;"></i>
					</a>

						<ul style="min-width:138px;right:7px;"
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- <li>
							<a href="#">
								<i class="ace-icon fa fa-cog"></i>
								设置
							</a>
						</li> -->

							<li><a
								href="${pageContext.request.contextPath}/person/toBiMain"
								target="menuFrame"> <i class="ace-icon fa fa-user"></i> 个人资料
							</a></li>
							<li class="divider"></li>
							
							<li style="position: relative;"><a id="infoBtn" href="${pageContext.request.contextPath}/person/toPassword"
								target="menuFrame"> <i  class="iconxiugaimima"></i> 修改密码<span class="infoTishi" style="position: absolute; top: 3; right: 58px;"></span>
							</a></li>
						</ul></li>
						
					<li style="width: 21px; height: 60px; cursor: pointer;  display: none;">
						<span id="infoNarrowTitle" class="iconfont icon-xiaoxi" style=""></span>
					</li>
						
					<script>
						
					</script>
						
					<li><a href="${pageContext.request.contextPath}/main">
							<dl>
								<dt>
									<img style="width: 27px; height: 27px; margin-left: 12px"
										src="${pageContext.request.contextPath}/images/shouye.png" />
								</dt>
								<dd style="margin-left: 10px">返回首页</dd>
							</dl>
					</a></li>
					<li><a href="${pageContext.request.contextPath}/logout">
							<dl>
								<dt>
									<img style="width: 27px; height: 27px;; margin-left: 12px"
										src="${pageContext.request.contextPath}/images/tuichu.png" />
								</dt>
								<dd style="margin-left: 10px">退出系统</dd>
							</dl>
					</a></li>
				</ul>
			</div>
		</div>
		<!-- /.navbar-container -->
	</div>
	<!--头部导航结束-->
	
	<!-- <script>
		var bodyH = $("body").eq(0).css("height");
		bodyH = parseInt(bodyH);
		$("#zhegai").css({
			height: (bodyH-100)+"px"
		})
		
		$("#infoNarrow").mouseover(function(){
			$("#infoNarrowzhegai").show()
		})
		$("#infoNarrowzhegai").click(function(){
			$(this).hide()
		})
		//$('#infoNarrow').draggabilly();
		$('#infoNarrow').draggabilly({handle: '#infoNarrowTitle',containment: true}); 
		
		//弹出框
		$('#infoPrompt').click(function(e){e.stopPropagation()})
		$('#infoPrompt').draggabilly({handle: '#infoTitle',containment: true}); 
		$("#infoTitle span").click(function(event){
			$("#zhegai").hide();
			$("#infoNarrow").show();
		}) 
		$("#infoNarrow p").click(function(){
			$("#zhegai").show();
			$("#infoNarrow").hide();
		})

		//弹出框动态内容
		$.ajax({
	   	    url:"${pageContext.request.contextPath}/task/getList",
	        type:"post",
	        dataType:"json",
	        success: function(data){
	        	if(data != ""){
	        		$("#zhegai").show();
	        		$(".infoTishi").addClass("layui-badge-dot");
	        		$("#infoNarrowTitle").parent("li").show();
	        		
	        		setInterval(infoFlash, 1000);
	        	} 
	        	var ps = "";
	        	for(var i = 0; i < data.length; i++){
	        		ps += "<p style='margin-bottom: 10px;'><a class='tanchuA' style='color: white; font-size: 18px;' target='menuFrame' href='" + data[i].taskUrl + "'><span class='iconfont icon-xiaoxitishi' style='background: #316182;color: white; margin-right: 5px'></span>" + data[i].message + "</a></p>"
	        	}
	        	$("#infoContent").append(ps);
	        	$(".tanchuA").click(function(){
	        		$("#zhegai").hide();
	        		$("#infoNarrow").show();
	        		$("#infoNarrowzhegai").hide();
	        	})
	        },
		   	error:function(){
		   	}
	   });
		
		$("#infoBtn").click(function(){
			if($("#zhegai").css("display") == "none"){
				$("#zhegai").show();
			}else{
				$("#zhegai").hide();
			}
		})
		
		function infoFlash(){
			setTimeout(() => {
				$("#infoNarrowTitle").show();
			}, 1000);
			setTimeout(() => {
				$("#infoNarrowTitle").hide();
			}, 500);
		}
		$("#infoNarrowTitle").parent("li").click(function(){
			$("#zhegai").show();
		})
	</script>	 -->
	
	<div class="main-container" id="main-container" style="height: 100%">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>
		<div class="main-container-inner" style="height: 100%">
			<!--左侧菜单栏-->
			<div id="sidebar" class="sidebar " style="">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>
				<!--左侧菜单栏开始-->
				<ul class="nav nav-list">
					<script type="text/javascript">
						/* .nav-list > li .submenu > li.active > a > .menu-icon, */
						$(document).ready(
								function() {
									//清除左侧菜单栏所有li标签active类
									function clearActive() {
										$("#sidebar .nav-list li").removeClass(
												"active");
									}
									function addActive(e) {
										$(e).parent().addClass("active");
									}
									clearActive();

									$("#sidebar ul.nav-list ul li").children(
											"a").click(function() {
										clearActive();
										addActive(this);
										return true;
									});
								});
					</script>

					<c:forEach items="${menus}" var="menu">
						<li class=""><a href="#" class="dropdown-toggle"> <span
								class="iconfont"
								style="width: 20px; height: 20px; display: inline-block;"></span>
								<span class="menu-text">${menu.module.name}</span> <c:if
									test="${fn:length(menu.subModule) > 0}">
									<b class="arrow fa fa-angle-down"></b>
								</c:if>
						</a> <c:if test="${fn:length(menu.subModule) > 0}">
								<b class="arrow"></b>
								<ul class="submenu">
									<c:forEach items="${menu.subModule}" var="subMenu">
										<li class=""><a
											href="${pageContext.request.contextPath}${subMenu.url}"
											target="menuFrame"> <i
												class="menu-icon fa fa-caret-right"></i> ${subMenu.name }
										</a> <b class="arrow"></b></li>
									</c:forEach>
								</ul>
							</c:if></li>
					</c:forEach>
					<script>
						/*
						 * @create: 2019年3月21日 
						 * @author: XieXing 
						 * @description:左侧菜单图标
						 */
						 
						var iconfont = ['','','iconxuesheng-copy',
								'iconlaoshi', 'iconfudaoyuan',
								'iconlingdao','iconxueyuanzhuanye','iconguanliyuan','iconuser','iconshezhi'
								];
						var $iconfont = $(".iconfont");
						for (var i = 0; i < $iconfont.length; i++) {
							$iconfont.eq(i).addClass(iconfont[i]);
						}
					</script>
				</ul>

				<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse"
					style="background-color: #316182; border-color: #316182">
					<i class="ace-icon fa fa-angle-double-left"
						data-icon1="ace-icon fa fa-angle-double-left"
						data-icon2="ace-icon fa fa-angle-double-right"></i>
				</div>
				<div style="height: 800px; width: 100%; background-color: #316182"
					class=""></div>
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
				<!--左侧菜单栏结束-->
			</div>


			<!--右侧内容区域-->
			<iframe id="menuFrame" name="menuFrame"
				style="height: 86%; overflow-x: hidden;" frameborder="0"
				src="${pageContext.request.contextPath}/images/bg1.jpg">
				</iframe>

			<script type="text/javascript">
				var size = window.setInterval("setSize()", 500);
				function setSize() {
					$("#menuFrame").contents().find("img").css("width", "100%");
					$("#menuFrame").contents().find("img")
							.css("height", "100%");
					//clearInterval(size);
				} 
			</script>
			<script>
				$("#menuFrame").click(function(){
					alert(111)
				//	$(".user-menu").hide()
				})
			</script>
			<!-- <div style="" class="main-content">

	</div> -->
			<!-- /.主体内容-->



			<!--尾部区域-->
		</div>
		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
		<div class="footer_bq"
			style="position: fixed; bottom: 0px; width: 100%; height: 20px; text-align: center; background-color: #316182;">
			<span class="footer_js" style="color: #fff;font-size: 15px;"> Powered <a
				href="#" style="color: #fff;" target="blank">By 谢兴</a>
			</span>
		</div>
	</div>
	<!-- 内容区域 -->

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery-2.1.1.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
<![endif]-->

	<!--[if !IE]> -->
	<script type="text/javascript">
		window.jQuery
				|| document
						.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'>"
								+ "<"+"/script>");
	</script>

	<!-- <![endif]-->

	<!--[if IE]>
<script type="text/javascript">
	window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->



	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery-ui.custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.ui.touch-punch.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/markdown/markdown.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/markdown/bootstrap-markdown.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.hotkeys.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap-wysiwyg.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootbox.min.js"></script>

	<!-- ace scripts -->
	<script
		src="${pageContext.request.contextPath}/assets/js/ace-elements.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/ace.min.js"></script>
	<script type="text/javascript">
		var w = screen.availWidth;
		if (w == 1440) {
			$("#menuFrame").css({
				height : "92%"
			})
		} else if (w == 1366) {
			$("#menuFrame").css({
				height : "72%"
			})
		} else if (w == 1680) {
			$("#menuFrame").css({
				height : "92%"
			})
			$("body").css({
				height: "893px"
			})
		} else if (w == 1920) {
			$("#menuFrame").css({
				height : "89%"
			});
			$("body").css({
				height: "922px"
			})
		}
		$(".nav li a").click(function() {
			$(this).find("b").toggleClass("f_flipy");
			$(this).parent().siblings().find("b").removeClass("f_flipy");
		});
		//收缩展开左边部分刷新当前页面
		$("#sidebar-collapse").click(
				function() {
					document.getElementById('menuFrame').contentWindow.location
							.reload(true);
					var size = window.setInterval("setSize()", 500);
					function setSize() {
						$("#menuFrame").contents().find("img").css("width",
								"100%");
						$("#menuFrame").contents().find("img").css("height",
								"100%");
						clearInterval(size);
					}
				})
	</script>
	
	
</body>
</html>
