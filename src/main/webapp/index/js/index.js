$(function(){
	//页面加载完成之后执行
	$("#gridTable").jqGrid({
		datatype: 'json',
		url:"${pageContext.request.contextPath}/art/list",
		mtype: 'POST',
		height:250,
		colNames:['艺术品名称','艺术品简介','审核提交日期','审核状态','审核通过日期'],
		colModel:[
			{name:'pname',index:'pname',width:90},
			{name:'detail',index:'detail',width:250},
			{name:'sdate',index:'sdate',width:'120',sorttype:"date"},
			{name:'state',index:'state',width:90},
			{name:'edate',index:'edate',width:'100',sorttype:"date"}
		],
		sortname:'sdate',
		sortorder:'desc',
		viewrecords:true,
		rowNum:10,
		rowList:[10,20,30],
		pager:"#pager2",
		rownumbers:true,
		altRows:true,
		caption:"个人中心-艺术品管理"
	}).navGrid("#pager2",{edit:true,add:false,del:true});

});
