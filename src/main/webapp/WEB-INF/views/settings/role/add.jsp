<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../public.jsp"%>
<!--新增权限管理用户界面   HuanZiyi 2019-3-8  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/settings/role/add.css">
<title>添加账号</title>
</head>
<body>
	<!-- 面包屑 -->
	<div id="head">
		<li id="crumbs"><span>系统设置</span> <span
			class="iconfont iconarrow-right"></span> <span>权限管理</span> <span
			class="iconfont iconarrow-right"></span> <span>添加客户权限</span></li>
	</div>
	<div id="add">
		<!-- title -->
		<div class="add_Title">
			<h2>修改权限</h2>
		</div>
		<!-- 内容 -->
		<div class="add_Wrap">
			<form type="post" class="layui-form" id="roleInfo"
				action="${pageContext.request.contextPath}/settings/saveRoles">
				<!-- 角色名称： -->
				<div class="layui-form-item">
					<input type="hidden" name="id" value="${role.id}"> <label
						class="layui-form-label name">角色名称：</label>
					<div class="layui-input-block">
						<input type="text" id="name" name="name" value="${role.name}"
							required lay-verify="required" placeholder="请输入角色名称"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<!-- 角色代码： -->
				<div class="layui-form-item">
					<input type="hidden" name="id" value="${role.id}"> <label
						class="layui-form-label code">角色代码：</label>
					<div class="layui-input-block">
						<input type="text" id="code" value="${role.code}" name="code"
							lay-verify="required" placeholder="请输入角色代码" autocomplete="off"
							class="layui-input">
					</div>
				</div>
		</div>
		<!-- 操作按钮 -->
		<div class="btns">
			<button type="button" id="submitBut" value="提交"
				class="layui-btn layui-btn-normal layui-btn">提交</button>
			<button type="button" id="back" value="返回"
				onclick="javascript:history.back()"
				class="layui-btn layui-btn-normal layui-btn">返回</button>
		</div>
		</form>
	</div>


	<script type="text/javascript">
		var show = "${operate}";//查看标记 如果operate="show",表示查看

		/*根据点击的按钮动态显示隐藏保存按钮  */
		if (show == "show") {
			$("#submitBut").hide();
			$("#crumbs span").eq($("#crumbs span").length-1).text("查看角色信息");
			$("h2").html("查看角色信息");
		} else if (show == "add") {
			$("#submitBut").show();
			$("h2").html("新增角色信息");
		} else {
			$("#submitBut").show();
			$("#crumbs span").eq($("#crumbs span").length-1).text("修改角色信息");
			$("h2").html("修改角色信息");
		}
		/**
		 * @create: 2019年3月8日 
		 * @author: HuanZiyi 
		 * @description:表单提交
		 **/
		$("#submitBut").click(function() {
			var name = $("#name").val();
			var code = $("#code").val();

			//非空判断
			if (name == null || name == "") {
				swal("", "角色名称不能为空！", "error");
				return false;
			}
			if (code == null || code == "") {
				swal("", "角色代码不能为空！", "error");
				return false;
			}
			$("#roleInfo").submit();

		})
	</script>
</body>
</html>