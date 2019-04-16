<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"  %>
    <!--个人资料详细界面   HuanZiyi 2019-3-18  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/personmanage/personalinfo/bimain.css">
<title>个人资料详细界面</title>
</head>
<body>
	<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>个人中心</span>
			<span class="iconfont iconarrow-right"></span>
			<span>个人资料</span>
			<!-- <span>查看个人资料</span> -->
		</li>
	</div>
	<div id="add">
		<div class="title">
			<h2>个人资料</h2>
		</div>
		<!--主体内容  -->
		<form id="customer" type="post" action="${pageContext.request.contextPath}/person/updateUser" style="height: 240px">
			<div>
				<ul>
					<li>
						<input type="hidden" name="id" value="${user.id}"><!-- 用来记录客户单位id，不能删除 -->
						<span>工号</span>
						<input name="number" id="number" value="${user.number}">
					</li>
					<li>
						<span>姓名</span>
						<input type="text" name="name" id="name" value="${user.name}">
					</li>
					<li>
						<span>账号状态</span>
						<select id="state" name="state" disabled="disabled">
							<option id="normal" value="1">正常</option>
							<option id="stop" value="0">停用</option>
						</select>
					</li>
					<li>
						<span>电话</span>
						<input type="text" name="phone" id="phone" value="${user.phone}">
					</li>
					<li>
						<span>工作职位</span>
						<input name="staffJob" id="staffJob" value="${user.staffJob }">
					</li>
					<li>
						<span>部门</span>
						<select id="department" name="department" disabled="disabled"></select>
					</li>
				</ul>
			</div>
		</form>
		<div id="buttonKey" style="margin-left: 48%;padding-bottom: 10px">
			<button type="button" id="saveUser">修改</button>
			<!-- <button type="button" onclick="javascript:history.back()">返回</button> -->
		</div>
	</div>

	<script type="text/javascript">
	  var show = "${operate}"; 
		/*根据获取到的性别动态显示性别*/
		var state = "${user.state}";
		if (state == 1){
			$("#normal").prop('selected',true);
		}else if(state == 0) {
			$("#stop").prop('selected',true);
		}
		
		
		 if (show == "update"){ 
			 $("#crumbs span").eq($("#crumbs span").length-1).text("修改个人资料");
			$("#saveUser").html("保存");
			
		}
		else{
			$("#crumbs span").eq($("#crumbs span").length-1).text("查看个人资料");
			$("#number").attr("readonly","true");
			$("#name").attr("readonly","true");
			$("#phone").attr("readonly","true");
			$("#staffJob").attr("readonly","true");
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
			 * @create: 2019年3月18日 
			 * @author: HuanZiyi
			 * @description:表单提交
			 */
			 $("#saveUser").click(function(event){
				 //var show = "${operate}"; 
				 if(show=="update"){
					 
				 
				
				 //非空判断  
				 var number = $("#number").val();
				 var name = $("#name").val();
				 var phone = $("#phone").val();
				 var staffJob = $("#staffJob").val();
				 
				 if(number == null || number == ""){
			 		 swal("","账号不能为空!","error");
			 		 return false;
			 	 }else if(singleNotNull(number) == false){
			 		swal("","账号不能输入空格!","error");
			 		 return false;
				}
				 if(name == null || name == ""){
			 		 swal("","姓名不能为空!","error");
			 		 return false;
			 	 }else if(singleNotNull(name) == false){
				 		swal("","姓名不能输入空格!","error");
				 		 return false;
					}
				 if(phone == null || phone == ""){
			 		 swal("","电话不能为空!","error");
			 		 return false;
			 	 }else if(singleNotNull(phone) == false){
				 		swal("","电话不能输入空格!","error");
				 		 return false;
				 }else if(checkTel("#phone")==false){
						swal("","电话格式错误！","error");
						return false;
				}
				 if(staffJob == null || staffJob == ""){
			 		 swal("","工作职位不能为空!","error");
			 		 return false;
			 	 }else if(singleNotNull(staffJob) == false){
				 		swal("","工作职位不能输入空格!","error");
				 		 return false;
					}
				$("#customer").submit();
			 }
		 else {
				window.location.href="${pageContext.request.contextPath }/person/toBiAdd?operate=update";
			}
			 });
		/*
		 * @create: 2019年3月18日 
		 * @author: HuanZiyi 
		 * @description:获取单位
		 */
		 $.ajax({
				url:path+"/system/getDepts",
				type:"post",
				dataType:"json",
				data:{
					
				},
				success:function(data){
					$("#department").empty();
					debugger;
					var tmpId = "${user.department.id}";
					var str = "<option value=''>选择所在单位</option>";
					for(var i=0;i<data.length;i++){
						if(tmpId==data[i].id){
							str+="<option value="+data[i].id+" selected>"+data[i].name+"</option>";
						}else {
							str+="<option value="+data[i].id+">"+data[i].name+"</option>";
						}
					}
					$("#department").append(str);
				},
				error:function(){
					swal("","网络错误","error");
				}
			})
	</script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/fun.js"></script>
</body>

</html>