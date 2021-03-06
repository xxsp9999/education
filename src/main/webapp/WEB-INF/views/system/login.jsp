<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="../public.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="zh-CN" style="height:100%">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智能教育系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/skin.css" />
<style>
	.login_main {
	    overflow-x: hidden;
	    overflow-y:hidden;
	    width: 1000px;
	    margin: 0 auto;
	    margin-top: 9%;
	   /*  background: url(${pageContext.request.contextPath}/images/login-green.jpg) center top no-repeat; */ 
	   /*  background: url(${pageContext.request.contextPath}/images/) center top no-repeat;  */
	    background-size:100% 100%;
	    overflow: hidden; 
	}
	.homePage{
		position:absolute;
		top:20px;
		/*right:80px;*/
	}
	.homePage a{
		color:#fff;
		font-size:18px;
	}
	.homePage a:hover{
		color:#e4393c;
	}
	#download{
		font-size:14px;
		text-decoration:underline;
		color:#888;
		opacity:1;
	}
	#download:hover{
		color:red;
	}

	#back-img{
		width: 100%;
		height: 100%;
		filter: blur(0px);
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
	}
	.login_table:hover{
		opacity: 1;
	}
	.login_table:hover + #back-img{
		filter: blur(2px);
	}
	.animated.fadeInDown{
		animation-fill-mode: none;
		animation-duration: 0.6s;
	}
	input.animated.flipInX.flipInX1 {
		animation-duration: 1.5s;
		-webkit-animation-duration: 1.5s;
	}
	input.animated.flipInX.flipInX2 {
		animation-duration: 2.5s;
		-webkit-animation-duration: 2.5s;
	}
	input.animated.flipInX.flipInX3 {
		animation-duration: 3.5s;
		-webkit-animation-duration: 3.5s;
	}
	#loginbtn,#reset{
		width:85px;
		height:35px;
		font-size:15px;
		line-height:35px;
		margin-left:40px;
	}
	
	
</style>
<script type="text/javascript">
	if(self != top){
		top.location.href = "${pageContext.request.contextPath}/system/toLogin";
	}	
</script>
</head>
<body class="login_main" style="position: relative; background: url('${pageContext.request.contextPath}/images/bg.jpg') no-repeat;background-size:100% 100%;">
	<table class="login_table animated fadeInDown">
		<tr>
			<td id="right_cont">
				<table height="50%" width="100%">
					<tr>
						<td width="15%" rowspan="5">&nbsp;</td>
						<td valign="top" id="form">
							<form id="loginid" method="post">
								<table valign="top" width="100%">
									<tr>
										<td colspan="2"><h2 class="login_title"
												style="font-size: 29px; font-family:'微软雅黑';margin-left: -13%;">智能教育系统</h2></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><span id="msg"
											style="border: 1px; background: #CCC; color: #F00; font-size: 16px;"></span>
										</td>
									</tr>
									<tr>
										<td>帐&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
										<td><input class="animated flipInX flipInX1" type="text"
											name="number" id="userNumber" /></td>
									</tr>
									<tr>
										<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
										<td><input class="animated flipInX flipInX2"
											type="password" name="password" id="pwd" /></td>
									</tr>
									<tr>
										<td>验证码：</td>
										<td><input class="animated flipInX flipInX3" type="text"
											name="code" id="code" style="width: 67px;" autocomplete="off" />
											<img
											src="${pageContext.request.contextPath}/system/code?x='+Math.random()"
											onclick="this.src='${pageContext.request.contextPath}/system/code?x='+Math.random()"
											alt="验证码" title="切换图片" style="vertical-align: middle" /></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="login_btn" style="text-align:center;"><button type="button"
				id="loginbtn">登录</button> <button
				type="button" id="reset">重置</button></td>
		</tr>
		<tr>
			<td style="margin-right: 20px;"></td>
		</tr>
	</table>
	<div class="footer_bq"
		style="position: absolute; /* bottom: 25px; */top:77%; right: 73px; width: 81%; height: 20px; text-align: center; opacity: 0.7;">
		<span style="font-size: 18px; color: white; margin-right: -98%;">
			@Powered By 谢兴 </span>
	</div>
	<script type="text/javascript">
		$(function() {
			setTimeout(function() {
				$(".appLoad").css("display", "block");
			}, 1000);
			$(document).on("keypress", function() {
				if (event.keyCode == "13") {
					$("#loginbtn").trigger("click");
				}
			});
			$("#loginbtn").click(function() {
				var number = $("#userNumber").val().trim();
				var pwd = $("#pwd").val().trim();
				var code = $("#code").val().trim();
				if (number.length == 0) {
					$("#msg").text("用户名不能为空");
					return;
				}
				if (pwd.length == 0) {
					$("#msg").text("密码不能为空");
					return;
				}
				if (code.length == 0) {
					$("#msg").text("验证码不能为空");
					return;
				}
				var formData = $.ajax({
					url : path + "/doLogin",
					type : "post",
					data : $("#loginid").serialize(),
					dataType : "json",
					success : function(data) {
						if (data.status == true) {
							window.location.href = path + "/main"
						} else {
							swal("", data.message, "error");
						}
					},
					error : function() {
						swal("", "网络错误！", "error");
					}
				});
			});

			$("#name").focus(function() {
				$("#number").css("border-color", "#375a78");
			});
			$("#pwd").focus(function() {
				$("#pwd").css("border-color", "#375a78");
			});
			$("#code").focus(function() {
				$("#code").css("border-color", "#375a78");
			});

			$("#reset").click(function() {
				$("#userNumber").val("");
				$("#pwd").val("");
				$("#code").val("");
				//使重置后用户名框获得焦点
				$("#name").focus();
			});
		});
	</script>
	<%@include file="../common/warn.jsp"%>
</body>
</html>