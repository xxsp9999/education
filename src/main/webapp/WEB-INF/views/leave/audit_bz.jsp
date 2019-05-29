<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目组长审批</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	function submit(state){
		$("#fm").form("submit",{
			url:'${pageContext.request.contextPath}/task/audit_bz?state='+state,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				var result=eval('('+result+')');
				if(result.success){
					$.messager.alert("系统系统","提交成功！");
					window.location.href="${pageContext.request.contextPath}/leave/checkList"
				}else{
					$.messager.alert("系统系统","提交失败，请联系管理员！");
					return;
				}
			}
		});
	}
</script>
</head>
<body style="margin: 5px">
<div id="p" class="easyui-panel" title="项目组长审批" style="width: 700px;height: 280px;padding: 10px">
	<form id="fm" method="post">
		<table cellspacing="8px">
			<tr>
				<td>请假人：</td>
				<td>
					<input type="text" id="leavePerson" name="leavePerson" readonly="readonly" value="${leave.leaveInfoStu.stuName }"/>
				</td>
				<td>&nbsp;</td>
				<td>请假天数：</td>
				<td>
					<input type="text" id="leaveDays" name="leaveDays" readonly="readonly" value="${leave.leaveInfoDays }"/>
				</td>
			</tr>
			<tr>
				<td valign="top">请假原因：</td>
				<td colspan="4">
					<textarea id="leaveReason" name="leaveReason" rows="2" cols="49" readonly="readonly" >${leave.leaveInfoReason}</textarea>
				</td>
			</tr>
			<tr>
				<td valign="top">批注：</td>
				<td colspan="4">
					<textarea id="comment" name="comment" rows="2" cols="49" class="easyui-validatebox" required="true">${leave.leaveInfoRemark}</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" name="taskId" value="${taskId}"/>
				</td>
				<td colspan="4">
					<a href="javascript:submit(1)" class="easyui-linkbutton" iconCls="icon-ok" >批准</a>&nbsp;&nbsp;
					<a href="javascript:submit(2)" class="easyui-linkbutton" iconCls="icon-no" >驳回</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<div style="padding-top: 5px">
<table id="dg" title="批注列表" class="easyui-datagrid"
  fitColumns="true"
  url="${pageContext.request.contextPath}/task/listHistoryComment?taskId=${param.taskId}" style="width: 700px;height: 200px;">
 <thead>
 	<tr>
 		<th field="time" width="120" align="center">批注时间</th>
 		<th field="userId" width="100" align="center">批注人</th>
 		<th field="message" width="200" align="center">批注信息</th>
 	</tr>
 </thead>
</table>
</div>
</body>
</html>