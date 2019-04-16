<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>模块管理</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />

		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" />		
		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/font-awesome.min.css" />

		<!-- page specific plugin styles -->
		<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery-ui.custom.min.css" /> -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ui.jqgrid.css" />
		<!-- text fonts -->


		<!-- ace styles -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-rtl.min.css" />
		<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/log.css" /> --%> 
		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->
		<script src="${pageContext.request.contextPath}/assets/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lte IE 8]>
		<script src="${pageContext.request.contextPath}/assets/js/html5shiv.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/respond.min.js"></script>
		<![endif]-->
		<!--[if !IE]> -->

		<!-- <![endif]-->

		<!--[if IE]>
		<script src="${pageContext.request.contextPath}/assets/js/jquery-1.11.1.min.js"></script>
		<![endif]-->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${pageContext.request.contextPath}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>

		<!-- page specific plugin scripts -->
		<script src="${pageContext.request.contextPath}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/jqGrid/i18n/grid.locale-en.js"></script>

		<!-- ace scripts -->
		<script src="${pageContext.request.contextPath}/assets/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath}/assets/js/ace.min.js"></script>
		<!-- sweetAlert插件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/sweetAlert/js/sweet-swal.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/sweetAlert/css/sweet-swal.css" />    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/publicJS/public.js"></script>
		<style type="text/css">
			.main-content{
				margin-left: 0;
			}
			.btn{
				width: 70px;
				height: 30px;
				text-align: center;
				vertical-align: middle;
			}
			#jqgh_myTable_cb{
				margin-top:-20px;
			}
			.widget-main{
				text-align: center; /*IE*/
				text-align: -moz-center; /*Firefox*/
				text-align: -webkit-center; /*Chrome*/
			}
			.editModule{
				position:absolute;
				left:0;
				top:30%;
				background:#fff;
				border:1px solid #b9b6b6;
				padding:20px;
				z-index:1;
				padding:0;
				display:flex;
				flex-flow:row wrap;
				justify-content:center;
				visibility: hidden;
			}
			.editModule h3{text-align:left;width:100%;background:#eee;margin:0 0 10px 0;padding:8px 10px;color:#148de6}
			#editModule{width:70%;/* margin:20px 15%; */}
			#editModule option{text-align:cenetr;}
			.editModuleButtons{width:100%;height:30px;margin:15px 0;display:flex;justify-content:center;}
			.editModuleButtons .btn{float:left;margin:0 5%;padding:0;}
			.moduleInput{width:85%;margin:5px 7.5%;}
			.moduleInput span{width:28%;text-align:center;display:inline-block;}
			.moduleInput input{width:70%;/* display:inline-block; */}
		</style>

	</head>

	<body class="no-skin">
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
			</script>
			<ul class="breadcrumb">
				<li class="gray-bac">
					<a style="color:black" href="##">系统管理</a>
				</li>

				<li class="gray-bac">
					<a style="color:black" href="##">模块管理</a>
				</li>
			</ul><!-- /.breadcrumb -->
		</div>
		<script type="text/javascript">
			function refresh(){
				location.reload(true);
			}
		</script>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<div class="widget-box widget-color-red4" style="margin-right:-2px;" >
						<div class="widget-header">
							<h5 class="bigger lighter" style="text-align:center;">
							<b style="color: #0f8af7;font-size: 16px;">模块列表</b>
							</h5>
						</div>
						<div class="widget-body">
							<div class="widget-main" style="overflow: hidden;">
							  	<table id="table"></table>
							  	<div id="toolsbar"></div>
							  	<!-- 修改弹框 -->
							  	<div class="row">
							  		<div class="col-xs-5 col-sm-4 col-md-3 col-sm-offset-1 col-xs-offset-1 col-md-offset-1 editModule">
							  			<h3>修改</h3>
							  			<!-- 菜单名称 -->
							  			<div class="moduleInput">
							  				<span>菜单名称</span>  <input id="menuName" type="text">
							  			</div>
							  			<!-- 父级菜单 -->
							  			<div class="moduleInput">
							  				<span>父级菜单</span>
								  			<select  name="parentModule"  id="parentModule" style="height:30px;width:70%;" >
								  				<option value="0">无</option>
								  			</select>
						            	</div>
						            	<!-- 请求类型 -->
						            	<div class="moduleInput">
							  				<span>请求类型</span>  <input id="requestType" type="text">
							  			</div>
							  			<!-- 路径 -->
							  			<div class="moduleInput">
							  				<span>路径</span>  <input id="url" type="text">
							  			</div>
							  			<!-- 备注 -->
							  			<div class="moduleInput">
							  				<span>备注</span>  <input id="remark" type="text">
							  			</div>
							  			<!-- 创建时间 -->
							  			<!-- <div class="moduleInput">
							  				<span>创建时间</span>  <input type="text" value="">
							  			</div> -->
						            	<div class="editModuleButtons">
						            		<button id="editOk" class="btn btn-success">修改</button>
						            		<button id="editCancel" class="btn btn-default">取消</button>
						            	</div>
							  		</div>
							  	</div>
							  	<script type="text/javascript">
							  		var editIds;
							  		function getParentModule(){
											var i;
											$.ajax({
												type : "post",
												async : false,
												url : "${pageContext.request.contextPath}/system/getParentModule",
												success : function(result){
													for(i = 0; i < result.length; i++){
														 var string = result[i].split(":");
														 $("#parentModule").append("<option value='"+string[0]+"'>"+string[1]+"</option>");
													}   
												}
											});
										}
									getParentModule();
								  	$(function(){
								  	  pageInit();
								  	});
								  	function pageInit(){
								  		var grid_selector = "#table";
								  		var pager_selector = "#toolsbar";
								  		
								  		//resize to fit page size
								  		$(window).on('resize.jqGrid', function () {
								  			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
								  		});
								  		//resize on sidebar collapse/expand
								  		var parent_column = $(grid_selector).closest('[class*="col-"]');
								  		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
								  			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
								  				$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
								  			}
								  		});
								  		function myExamine(value,colname){
											  if(value==""){
												  return [false,colname+":不能输入空值!"];
											  }else{
												  return [true,""];
											  }
										  }
								  	  jQuery("#table").jqGrid({
							  	        url:"${pageContext.request.contextPath}/system/module/getList",
								  	    datatype: "json",
								  		rownumbers: true,
								  		shrinkToFit:false,//禁止按比例缩放   
								  		autoScroll: true, 
								  		height: "350px",
								  	       colNames:['序号','菜单名称', '父级菜单', '请求类型', '路径', '备注', '创建时间'],
								  	       colModel:[
								  	         {name:'id',index:'id', width:160,align:"center",hidden:true},
								  	         {name:'name',index:'name', width:187,align:"center",editable:true,editrules:{custom:true,custom_func:myExamine}},
								  	      	 {name:'parentModuleId',index:'parentModuleId', width:224, align:"center",editable:true,formatter:function(cellvalue,options,rowObject){
								  	        	 var json = $.parseJSON('${moduleJSON}');
								  	        	 var parent = rowObject.parent;
								  	        	 if(parent != null){
													var moduleId = rowObject.parent.id;
								            	 	if(moduleId != ""){
								            	 		 var parentModuleId = "";
											             $.each(json,function(key,value){
									            	 		if(moduleId==value.id){
									            	 			parentModuleId = value.name;
																return false;
									            	 		}
								            	 		 });
											             return parentModuleId;
								            	 	}else{
								            	 		return "";
								            	 	}
								  	        	 }else{
								  	        		return "";
								  	        	 }
												},edittype:'select',editoptions:${select}},
								  	         {name:'type',index:'type', width:187, align:"center",editable:true,editrules:{custom:true,custom_func:myExamine}},
								  	         {name:'url',index:'url', width:187, align:"left",editable:true,editrules:{custom:true,custom_func:myExamine}},
								  	         {name:'remark',index:'remark', width:187, align:"center",editable:true,editrules:{custom:true,custom_func:myExamine}},
								  	         {name:'createDate',index:'createDate', width:185, align:"center",editable:true},
								  	       ],
								  	    rowNum:10,
							  	        rowList:[10,20,30,1000],
							  	        pager: '#toolsbar',
							  	        sortname: 'id',
							  	        viewrecords: true,
							  	        sortorder: "desc",
										multiselect: true, //全选的复选框那一列
										editurl:"${pageContext.request.contextPath}/system/module/edit",
								  	    loadComplete : function() {
											var table = this;
											var rowNum = $("#table").jqGrid('getGridParam','records');
											if (rowNum==0){
												if($("#norecords").html() == null){
													$("#table").parent().append("</pre><div id='norecords'>没有查询记录！</div><pre>");
												}
												$("#norecords").show();
											}else{//如果存在记录，则隐藏提示信息。
												$("#norecords").hide();
											}							
											setTimeout(function(){
												/* styleCheckbox(table);
												updateActionIcons(table); */
												updatePagerIcons(table);
												/* enableTooltips(table); */
											}, 0);
										},
									});
								  	pagesSelect();
								  	
								  	$(window).triggerHandler('resize.jqGrid');//调节合适的宽度	
								  	/* jQuery(grid_selector).jqGrid('navGrid','#toolsbar',
											{  //navbar options
										  		refresh: false,
												refreshicon : 'ace-icon fa fa-refresh green',
												edit: false,
												editicon : 'ace-icon fa fa-pencil blue',
												add: false,
												addicon : 'ace-icon fa fa-plus-circle purple',
												del: false,
												delicon : 'ace-icon fa fa-trash-o red',
												search: false,
												searchicon : 'ace-icon fa fa-search orange',
												view: false,
												viewicon : 'ace-icon fa fa-search-plus grey',
												position: 'left',
											}).navButtonAdd(pager_selector,{
												 caption:"刷新",
												 buttonicon:"ace-icon fa fa-refresh green",
												 onClickButton:function(){								
													 $(grid_selector).jqGrid().trigger("reloadGrid");
												 }}
											) */
								  	
											 jQuery(grid_selector).jqGrid('navGrid','#toolsbar',
														{  //navbar options
												 			refresh: true,
											  				refreshtext:'刷新',
															refreshicon : 'ace-icon fa fa-refresh green',
												 			add: true,
															addtext:'增加',
															addicon : 'ace-icon fa fa-plus-circle purple',
													  		
															edit: false,
															edittext:'修改',
															editicon : 'ace-icon fa fa-pencil blue',
															
															del: false,
															delicon : 'ace-icon fa fa-trash-o red',
															search: false,
															searchicon : 'ace-icon fa fa-search orange',
															view: false,
															viewicon : 'ace-icon fa fa-search-plus grey',
															position: 'left',
														},
														{
															//edit record form
															//closeAfterEdit: true,
															//width: 700,
															closeAfterEdit : true,
															reloadAfterSubmit:true,
															recreateForm: true,
															beforeShowForm : function(e) {
																var form = $(e[0]);
																form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
																style_edit_form(form);
															},
															afterShowForm : function(e){
																$("#Act_Buttons .navButton").remove(); 
																$("#Act_Buttons .EditButton").attr("style","text-align:center;");
															},
														},
														{
															//new record form
															//width: 700,
															closeAfterAdd: true,
															recreateForm: true,
															viewPagerButtons: true,
															beforeShowForm : function(e) {
																var form = $(e[0]);
																form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
																		.wrapInner('<div class="widget-header" />')
																style_edit_form(form);
															}
														}).navButtonAdd(pager_selector,{
															 caption:"修改",
															 buttonicon:"ace-icon fa fa-pencil blue",
															 onClickButton:function(){								
																editIds= $(grid_selector).jqGrid('getGridParam', 'selarrrow');
																if(editIds.length==0){
																	swal("","请选择要修改的记录!","error");
																	return false;
																}
																if(editIds.length>1){
																	swal("","一次只能修改一条数据","error")
																}
																else{
																	$('.editModule')[0].style.visibility = 'visible';
																	var rowData = $("#table").jqGrid("getRowData",editIds[0]);
																	$("#menuName").val(rowData.name);
															  		$("#requestType").val(rowData.type);
															  		$("#url").val(rowData.url);
															  		$("#remark").val(rowData.remark);
															  		var obj = document.getElementById("parentModule");
															  		for(i=0;i<obj.length;i++){
																        if(obj[i].text==rowData.parentModuleId)
																            obj[i].selected = true;
																    }
																}
															 }
														}).navButtonAdd(pager_selector,{
															 caption:"删除",
															 buttonicon:"ace-icon fa fa-trash-o red",
															 onClickButton:function(){								
																var ids= $(grid_selector).jqGrid('getGridParam', 'selarrrow');
																if(ids.length==0){
																	swal("","请选择要删除的记录!","error");
																	return false;
																}
																$.ajax({
																	type:"POST",
																	url:"${pageContext.request.contextPath}/system/module/edit",
																	data:{"oper":"del","id":ids+""},
																	dataType:"json",
																	success:function(data){
																		var json = eval(data);
																		if(json.status == "success"){
																			var back = "删除成功!";
																			if(json.msg != null && json.msg != undefined && json.msg != ""){
																				back = json.msg;
																				swal("",back,"error");
																			}
																			else{
																				swal("",back,"success");
																			}
																			$(grid_selector).jqGrid("setGridParam").trigger("reloadGrid",[{}]);
																		}else{
																			swal("","网络错误!","error");
																		}
																	}
																});
																
															 }
														}); 
								  	  
									  $("#gbox_table").find("table").css("width","100%");//去掉右边的 一小块白色的 ，并修改ui.jqgrid.css的第12行
								  	  
								  	 //解决表格下方无分页的图标
									  function updatePagerIcons(table) {
									  	var replacement =
											{
												'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
												'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
												'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
												'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
											};
											$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
												var icon = $(this);
												var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
	
												if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
											});
										}
								  	  
									  function style_edit_form(form) {
											//enable datepicker on "sdate" field and switches for "stock" field
											form.find('input[name=sdate],input[name=edate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
													.end().find('input[name=stock]')
													.addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
											var buttons = form.next().find('.EditButton .fm-button');
											buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
											buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
											buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')

											buttons = form.next().find('.navButton a');
											buttons.find('.ui-icon').hide();
											buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
											buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');
										}
									  
									  function style_delete_form(form) {
											var buttons = form.next().find('.EditButton .fm-button');
											buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
											buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
											buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
										}
								  	}
								  	
								  	
							  	</script>
							  		<script type="text/javascript">
							  		$("#editOk").on("click",function(){
							  			var name = $("#menuName").val();
								  		var parentModuleId = $("#parentModule").val();
								  		var requestType = $("#requestType").val();
								  		var url = $("#url").val();
								  		var remark = $("#remark").val();
							  			$.ajax({
												type:"POST",
												url:"${pageContext.request.contextPath}/system/module/edit",
												data:{
													"oper":"edit",
													"id":editIds+"",
													"name":name,
													"parentModuleId":parentModuleId,
													"type":requestType,
													"url":url,
													"remark":remark
													},
												dataType:"json",
												success:function(data){
													var json = eval(data);
													if(json.status == "success"){
														var back = "修改成功!";
														if(json.msg != null && json.msg != undefined && json.msg != ""){
															back = json.msg;
														}
														swal("",back,"success");
														$('.editModule')[0].style.visibility = 'hidden';
														$("#table").trigger("reloadGrid");
													}else{
														swal("","网络错误!","error");
														$('.editModule')[0].style.visibility = 'hidden';
													}
												},
												error:function(){
													swal("","网络错误!","error");
													$('.editModule')[0].style.visibility = 'hidden';
												}
											})
											$("#menuName").val("");
									  		$("#parentModule").val("");
									  		$("#requestType").val("");
									  		$("#url").val("");
									  		$("#remark").val("");
							  		})
							  		$("#editCancel").on("click",function(){
							  			$('.editModule')[0].style.visibility = 'hidden';
							  		})
							  	</script>
							</div>
						</div>
					</div>
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
	</div><!-- 内容区域 -->
		<!-- basic scripts -->
	</body>
</html>
