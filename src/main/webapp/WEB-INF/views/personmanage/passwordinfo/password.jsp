<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/personmanage/passwordinfo/password.css">
<title>修改密码</title>
</head>
<body>
<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>个人中心</span>
			<span class="iconfont iconarrow-right"></span>
			<span>修改密码</span>
		</li>
	</div>
	<div id="add" style="width: 55%; margin-top: 50px">
		<div class="title">
			<h2>修改密码</h2>
		</div>
		<!--主体内容  -->
			<form type="post" id="passwordInfo" action="${pageContext.request.contextPath}/person/resetPasswords">
		<div id="customer" style="height: 280px">
			<ul>
				<li>
					<input type="hidden" name="id" value="${user.id}"><!-- 用来记录客户单位id，不能删除 -->
					<input type="hidden" name="originalPassword" id="originalPassword" value="${user.password}">
					<span>旧密码：</span>
					<input type="password" name="password" id="password" style="width: 180px;text-indent: 18px">
					<span class="iconfont icon-mima1" style="position:absolute;left:535px;top:175px;width:auto;font-size:18px;"></span>
				</li>
				<li>
					<span>新密码：</span>
					<input type="password" name="newPassword" id="newPassword" maxlength="16" style="width: 180px;text-indent: 18px"/>
					<span class="iconfont icon-mima1" style="position:absolute;left:535px;top:245px;width:auto;font-size:18px;"></span>
					<label class="hint" id="hint_newPsw">&nbsp;</label>
				</li>
				<li>
					<span>确认密码：</span>
					<input type="password" name="confirm" id="confirm" maxlength="16" style="width: 180px;text-indent: 18px"/>
					<span class="iconfont icon-mima1" style="position:absolute;left:535px;top:315px;width:auto;font-size:18px;"></span>
					<label class="hint" id="hint_pswConfirm">&nbsp;两次密码不一致</label>
				</li>
			</ul>
			<button type="button" id="save">保存</button>
		</div>
		</form>
	</div>

	<script type="text/javascript">
	 
	/*  $("#confirm").blur(function(){
		 var newPassword = $("#newPassword").val().trim();
		 var confirm = $("#confirm").val().trim();
			if((newPassword.length > 0) && (confirm.length > 0)){
				 if(newPassword != confirm){
					 $("#confirm").css("border-color", "#ff0000");
					 swal("","新密码与确认密码不一致,请重新输入！","error");
					 ("#confirm").empty();
				 }
			}
		}) */
			/*限制密码只能为字母或数字，且位数只能为6-16位*/
			/* $("#newPsw").blur(function(){
				$("#hint_newPsw").hide()
			}) */
			var isok = true;
			$("#newPassword").keyup(function(){
				var reg1=/^[0-9]*$/;                         							 //全部为数字
				var reg2=/^[a-zA-Z]{6,16}/;                   							 //全为字母
				var reg3=/^[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]{6,16}/;       			 //全为符号
				var reg4=/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;      			 //字母和数字的组合
				//var reg4=/^[\da-zA-Z]*\d+[a-zA-Z]{6,16}/; 
				//var reg5=/^[\d-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*\d+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]{6,16}/;        			 //字符和数字的组合
				//var reg6=/^[-a-zA-Z`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]*[a-zA-Z]+[-`=\\\[\];',./~!@#$%^&*()_+|{}:"<>?]{6,16}/;        //子母和 数字的组合
				//var reg7 = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
				var str = document.getElementById("newPassword").value;
				if(str.length<6||str.length>16){
					isok = false;
					$("#hint_newPsw").text("密码的长度为6-16位").css("display","inline");
					$("#hint_newPsw").text("密码的长度为6-16位").css("color","red");
				}else if(str.length>=6&&str.length<=8){
					isok = true;
					$("#hint_newPsw").text("密码强度：弱").css("display","inline");
					$("#hint_newPsw").text("密码强度：弱").css("color","red");
				}else if(str.length>=9&&str.length<=12){
					isok = true;
					$("#hint_newPsw").text("密码强度：中").css("display","inline");
					$("#hint_newPsw").text("密码强度：中").css("color","blue");
				}else if(str.length>=13&&str.length<=16){
					isok = true;
					$("#hint_newPsw").text("密码强度：强").css("display","inline");
					$("#hint_newPsw").text("密码强度：强").css("color","#008000");
				}
			}),
			
			$("#confirm").keyup(function(){
					
				if($("#newPassword").val()==$("#confirm").val()){
					
					$("#hint_pswConfirm").css("display","none");
				}else{
					
					$("#hint_pswConfirm").css("display","inline");
				}	
			}),
	/*
	 * @create: 2019年3月1日 
	 * @author: XieXing 
	 * @description:表单提交
	 */
	 $("#save").click(function(event){
		 var newPassword = $("#newPassword").val().trim();//非空判断  
		 var confirm = $("#confirm").val().trim();
		 var originalPassword = $("originalPassword").val();
		 var password = $("#password").val().trim();
		 debugger;
		 if(newPassword.length==0){
	 		 swal("","新密码不能为空!","error");
	 		 return false;
	 	 }
		 
		 if(confirm.length == 0){
			 swal("","确认密码不能为空！","error");
			 return false;
		 }
		 if(newPassword != confirm){
			 swal("","新密码与确认密码不一致,请重新输入！","error");
			$("#confirm").val("");
			 return false;
		 }
		
       		$.ajax({
       			url:"${pageContext.request.contextPath}/person/resetPasswords",
       			type:"post",
       			data:{
       				password:password,
       				newPassword:newPassword
       			},
       			success:function(data){
       				if(data.status==true){
       					/* swal("","密码修改成功！","success");
       					$("input").val(""); */
       					debugger;
       					top.location.href="${pageContext.request.contextPath}/logout";
       				}else if(data.status==false){
       					swal("","原密码输入错误，请重新输入！","error");
       					$("input").val("");
       				}
       			},
       			error:function(){
       				swal("","网络异常，重置失败！","error");
       			}
       		});
		 })
		

	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/fun.js"></script>
</body>
</html>