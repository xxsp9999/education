<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请假审核</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	function searchTask(){
		$("#dg").datagrid('load',{
			"s_name":$("#s_name").val()
		});
	}
	
	function tipTask(){
		window.open("${pageContext.request.contextPath}/leave/toStudentLeaveData");
	}
	function formatAction(val,row){
		return "<a href='javascript:openFinishTaskTab("+row.id+")'>办理任务</a>&nbsp;<a target='_blank' href='${pageContext.request.contextPath}/task/showCurrentView?taskId="+row.id+"'>查看当前流程图</a>"
	}
	
	function openFinishTaskTab(taskId){
		$.post("${pageContext.request.contextPath}/task/redirectPage",{taskId:taskId},function(result){
			url="${pageContext.request.contextPath}"+result.url+"?taskId="+taskId;
			//window.parent.openTab('办理任务',url,'icon-check');
			location.href=url;
			
		},"json"); 
		
	}

</script>
</head>
<body style="margin: 1px">
<table id="dg" title="请假管理>请假审核" class="easyui-datagrid"
  fitColumns="true" pagination="true" rownumbers="true"
  url="${pageContext.request.contextPath}/task/taskPage" fit="true" toolbar="#tb">
 <thead>
 	<tr>
 		<th field="cb" checkbox="true" align="center"></th>
 		<th field="id" width="100" align="center">任务ID</th>
 		<th field="name" width="100" align="center">任务名称</th>
 		<th field="createTime" width="100" align="center">创建时间</th>
 		<th field="action" width="100" align="center" formatter="formatAction">操作</th>
 	</tr>
 </thead>
</table>
<div id="tb">
 <div>
 	&nbsp;任务名称&nbsp;<input type="text" id="s_name" size="20" onkeydown="if(event.keyCode==13) searchTask()"/>
 	<a href="javascript:searchTask()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
 	<!-- <a href="javascript:tipTask()" class="easyui-linkbutton" iconCls="icon-tip" plain="true">请假统计</a> -->
 </div>
</div>

</body>
</html>