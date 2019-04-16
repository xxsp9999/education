<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../public.jsp"  %>
     <!--新增系统用户界面   RanYang 2019-3-4  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings/account/add.css">
<title>添加账号</title>
</head>
<body>
	<!-- 面包屑 -->
	<div id="head">
		<li id="crumbs">
			<span>系统设置</span>
			<span class="iconfont iconarrow-right"></span>
			<span>账号管理</span>
			<span class="iconfont iconarrow-right"></span>
			<span>添加账号</span>
		</li>
	</div>
	<form type="post" id="account" action="${pageContext.request.contextPath}/user/addAccount" style="width:70%;border:1px solid #efefef;margin:0 auto;margin-top:72px;padding:0px;"> 
		<h2 style="margin:0 auto;background:#316190;color:#fff;height:40px;line-height:40px;">添加账号</h2>
		<ul style="margin-top:45px;margin-bottom:70px;">
			<li>
				<input type="hidden" name="id" value="${user1.id}"><!-- 用来记录客户合作记录id，不能删除 -->
				<label class="number">工号：</label>
				<input type="text" id="number" value="${user1.number }" name="number"/>
				<label class="name">姓名：</label>
				<input type="text" id="name" value="${user1.name }" name="name"/>
				<label class="phone">电话：</label>		
				<input type="text" id="phone" value="${user1.phone }" name="phone"/>
			</li>
			<li>
				<label class="department">部门：</label>
				<select id="department" name="department"></select>
				<label class="staffJob">职位：</label>
				<input type="text" id="staffJob" value="${user1.staffJob }" name="staffJob"/>
				<label class="state">账号状态：</label>		
				<select id="state" name="state">
	                <option value="-1">请选择</option>
	                <option value="1">正常</option>
	                <option value="0">停用</option>
		        </select>
			</li>
			<li>
				
				<label class="pwd">密码：</label>
				<input class="pwd" type="password" id="password" value="" name="password"/>
				<label class="sex">性别：</label>	
				<select id="sex" name="sex">
						<option id="boy" value="男">男</option>
						<option id="girl" value="女">女</option>
					</select>
			</li>
		</ul>
		<div class="button" style="margin-bottom:15px;margin-left:45%;">
			<input type="button" id="submitBut" value="提交"/>
			<input type="button" id="back" value="返回" onclick="javascript:history.back()"/>
		</div>
	</form>
	<script type="text/javascript">
		var departmentId = "${user1.department.id}";//部门id
		var state = "${user1.state}";//用户账号状态
		
		debugger;
		var show = "${operate}";//查看标记 如果operate="show",表示查看
		var sex = "${user1.sex}";
		if (sex == "男"){
			$("#boy").prop('selected',true);
		}else {
			$("#girl").prop('selected',true);
		}
		var passValue= setTimeout(() => {
						$("#department").val(departmentId).trigger("change");
						$("#state").val(state).trigger("change");
					}, 100);
		
		/*根据点击的按钮动态显示隐藏保存按钮  */
		if (show == "show"){
			$("#submitBut").hide();
			$(".pwd").hide();
			$("#crumbs span").eq($("#crumbs span").length-1).text("查看账号信息");
			$("h2").html("查看账号信息");
		}else if (show == "update"){
			$(".pwd").remove();
			$("#crumbs span").eq($("#crumbs span").length-1).text("修改账号信息");
			$("h2").html("修改账号信息");
		}else{
			$("#submitBut").show();
			clearTimeout(passValue);//关闭延时函数
		}
		
		function checkTel(id){
			debugger;
		    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
		    var isMob=/^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
		    var value=$(id).val();
		    if(isMob.test(value)||isPhone.test(value)){//格式正确
		        return true;
		    }
		    else{		//格式错误
		    	return false;
		    }
		    }
		/*
		 * @create: 2019年3月5日 
		 * @author: XieXing 
		 * @description:获取部门
		 */
		 $.ajax({
			 	url:path+"/system//getDepts",
				type:"post",
				dataType:"json",
				data:{
					
				},
				success:function(data){
					$("#department").empty();
					var str = "";
					for(var i=0;i<data.length;i++){
						str+="<option value="+data[i].id+">"+data[i].name+"</option>";
					}
					$("#department").append(str);
				},
				error:function(){
					swal("","网络错误","error");
				}
			})
			
		/**
		 * @create: 2019年3月6日 
		 * @author: RanYang 
		 * @description:表单提交
		**/
		 $("#submitBut").click(function(){
			 var number = $("#number").val();
			 var sex = $("#sex").val();
			 var name = $("#name").val();
			 var phone = $("#phone").val();
			 var staffJob = $("#staffJob").val();
			 var state = $("#state").val()+"";
			 var password = $("#password").val();
			 
			 //非空判断
			 if(number == null || number == ""){
				 swal("","工号不能为空！","error");
				 return false;
			 }
			 if(name == null || name == ""){
				 swal("","姓名不能为空！","error");
				 return false;
			 }
			 
			 if(phone == null || phone == ""){
				 swal("","电话不能为空！","error");
				 return false;
			 }
			 if(checkTel("#phone")==false){
					swal("","电话格式错误！","error");
					return false;
				}
			 if(staffJob == null || staffJob == ""){
				 swal("","职位不能为空！","error");
				 return false;
			 }
			 if(state == -1){
				 swal("","请选择账号状态！","error");
				 return false;
			 }
			 if (show == "add"){
				 if(password == null || password == ""){
					 swal("","密码不能为空！","error");
					 return false;
				 }
			 }else {
				 $("#account").submit();
			}
				 $("#account").submit();
			 
		 })
		 
		 /*
		  * @create: 2019年3月6日 
		  * @author: RanYang 
		  * @description:错误提示
		  */
		 function errorTips(val,msg){
		 	if(val==null || val ==""){
		 		 swal("",msg,"error");
		 		 return false;
		 	}
		 }
	</script>			
</body>
</html>