<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 网页中引入绝对路径 -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />

<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet"
	href="<%=path%>/assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=path%>/assets/css/font-awesome.min.css" />

<!-- page specific plugin styles -->
<link rel="stylesheet"
	href="<%=path%>/assets/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/datepicker.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/ui.jqgrid.css" />
<!-- text fonts -->


<!-- ace styles -->
<link rel="stylesheet" href="<%=path%>/assets/css/ace.min.css" />

<!--[if lte IE 9]>
 <link rel="stylesheet" href="<%=path%>/assets/css/ace-part2.min.css" />
  <![endif]-->
<link rel="stylesheet"
	href="<%=path%>/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="<%=path%>/assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
 <link rel="stylesheet" href="<%=path%>/assets/css/ace-ie.min.css" />
  <![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="<%=path%>/assets/js/ace-extra.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
  <script src="<%=path%>/assets/js/html5shiv.js"></script>
  <script src="<%=path%>/assets/js/respond.min.js"></script>
  <![endif]-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/plug-in/jquery.ztree.v3/css/zTreeStyle.css" />
<script src="<%=path%>/assets/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/jquery.ztree.v3/jquery.ztree.core.min.js"></script>
<script src="${pageContext.request.contextPath}/plug-in/jquery.ztree.v3/jquery.ztree.excheck.min.js"></script>
<!-- sweetAlert插件 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/sweetAlert/js/sweet-swal.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/sweetAlert/css/sweet-swal.css" />
<style type="text/css">
ul {
	list-style: none;
	padding: 0;
}

#menu .tree1 {
	padding: 14px 14px 14px 39px;
	color: #6F93FF;
	font-size: 16px;
	font-family: "黑体";
	border-bottom: solid 1px #eee;
}

#menu #tree_root {
	overflow: auto;
}


#menu #tree_root li span {
	display: block;
	height: 18px;
	line-height: 18px;
	color: #4D6878;
	cursor: pointer;
}

#menu #tree_root li span.tree2 {
	padding: 0px 6px 6px 20px;
	margin-top:10px;
}

#menu #tree_root li span.tree3 {
	padding: 0px 6px 6px 34px;
	margin-top:10px;
}

#menu #tree_root li span.tree4 {
	padding: 0px 6px 6px 48px;
	margin-top:10px;
}

#menu #tree_root li span.tree5 {
	padding: 0px 6px 6px 62px;
	margin-top:10px;
}

#menu li .hover,#menu li span:hover {
	background-color: #e9edf6;
}



#menu ul li b {
	font-weight: normal;
	position: relative;
	padding-left: 16px;
}

#menu ul li b:before {
	display: block;
	font-size: 0;
	top: 5px;
	left: 0;
	content: "";
	width: 4px;
	height: 4px;
	border: solid 1px transparent;
	transform: rotate(45deg);
	-o-transform: rotate(45deg);
	-ms-transform: rotate(45deg);
	-moz-transform: rotate(45deg);
	-webkit-transform: rotate(45deg);
	position: absolute;
}

#menu ul li .On:before,#menu ul li .On2Off:before {
	top: 3px;
	border-bottom-color: #999;
	border-right-color: #999;
}

#menu ul li .Off:before {
	top: 5px;
	border-top-color: #999;
	border-right-color: #999;
}

#menu ul li .On2Off:before {
	transform: rotate(0deg);
	-o-transform: rotate(0deg);
	-ms-transform: rotate(0deg);
	-moz-transform: rotate(0deg);
	-webkit-transform: rotate(0deg);
}
</style>


</head>

<body class="no-skin">
	<div class="main-content">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
		      try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
		    </script>

			<ul class="breadcrumb">
				<li><a href="#">权限管理</a></li>

				<li><a href="#">权限分配</a></li>
			</ul>
			<!-- /.breadcrumb -->

			<div class="nav-search" id="nav-search">
			<!--左侧栏搜索  -->
			<!-- 	<form class="form-search">
					<span class="input-icon"> <input type="text"
						placeholder="搜索 ..." class="nav-search-input" id="search"
						autocomplete="off" /> <i
						class="ace-icon fa fa-search nav-search-icon"></i>
					</span>
				</form> -->
			</div>
			<!-- /.nav-search -->
		</div>
		<div class="page-content">

			<div class="row">
				<div class="col-xs-12">
					<div class="row">
						<div class="col-xs-5">
							<div class="widget-box widget-color-blue2"
								style="min-height: 550px;!important;">
								<div class="widget-header">
									<h4 class="widget-title lighter smaller">${role.name }正在进行模块分配</h4>
								</div>

								<div class="widget-body">
									<div class="widget-main padding-8">
										 <div>
										 	<ul id="tree" class="ztree"></ul> 
										 </div>
										 <button onclick="javascript:saveDeptModule()">保存</button>
									</div>
								</div>
							</div>
						</div>
						<!-- /.col -->

						<script type="text/javascript">
        var $path_base = "..";//this will be used for editurl parameter
      </script>
						<!-- PAGE CONTENT ENDS -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.page-content -->
		</div>
		
<!-- ZTree树形插件 -->  
<!-- 角色模块树显示层  start -->
<script type="text/javascript">
	$(function() {
		var treeNodes = ${json}; 
		//var nodes = ${requestScope.nodes};
		var nodes = [
		             {"checked":"true","id":"1","isParent":"","name":"功能模块","nocheck":"","open":"","pId":"","type":""},
			             {"checked":"true","id":"2","isParent":"","name":"销售管理","nocheck":"","open":"","pId":"1","type":""},
				             {"checked":"true","id":"3","isParent":"","name":"我的客户","nocheck":"","open":"","pId":"2","type":""},
					             {"checked":"true","id":"4","isParent":"","name":"查看","nocheck":"","open":"","pId":"3","type":""},
					             {"checked":"true","id":"5","isParent":"","name":"新增","nocheck":"","open":"","pId":"3","type":""},
					             {"checked":"true","id":"6","isParent":"","name":"修改","nocheck":"","open":"","pId":"3","type":""},
					             {"checked":"true","id":"7","isParent":"","name":"删除","nocheck":"","open":"","pId":"3","type":""},
				             {"checked":"true","id":"8","isParent":"","name":"我的订单","nocheck":"","open":"","pId":"2","type":""},
					             {"checked":"true","id":"9","isParent":"","name":"查看","nocheck":"","open":"","pId":"8","type":""},
					             {"checked":"true","id":"10","isParent":"","name":"新增","nocheck":"","open":"","pId":"8","type":""},
					             {"checked":"true","id":"11","isParent":"","name":"修改","nocheck":"","open":"","pId":"8","type":""},
					             {"checked":"true","id":"12","isParent":"","name":"删除","nocheck":"","open":"","pId":"8","type":""},
	];
		showtree(treeNodes);
	});
</script>
<!-- 角色模块显示层  end -->
<!-- 树工具  start -->
<script type="text/javascript">
	function showtree(data) {
		var setting = {
			check : {
				enable : true,//复选框
				autoCheckTrigger : true,//让所有自动关联 的节点都出发 onChecked 事件
				//chkStyle:"radio",//圆圈单选
				chkStyle : "checkbox", //自动匹配选项
				showLine : true, //是否显示节点间的连线
				chkboxType : {
					"Y" : "ps",
					"N" : "ps"
				}//关联子父级
			},
			data : {
				simpleData : {
					enable : true, //树状
					idKey : "id", //父节点
					pIdKey : "pId", //子节点
					rootPId : 0,//根节点  
				}
			}
		};

		$(document).ready(function() {//初始化ztree对象     
			var zTreeDemo = $.fn.zTree.init($("#tree"), setting, data);
			var treeObj = $.fn.zTree.getZTreeObj("tree");
			treeObj.expandAll(false); 
		});
	}
</script>
<!-- 保存部门所拥有的权限  -->
<script type="text/javascript">
	var roleId = "${role.id}";
	function saveDeptModule() {
		var treeObj = $.fn.zTree.getZTreeObj("tree");
		var nodes = treeObj.getCheckedNodes(true);
		var treeData = JSON.stringify(nodes);
		$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/role/add",
					data : {
						"treeData" : treeData,
						"roleId" : roleId
					},
					dataType : "json",
					success : function(data) {
						if(data.status == "success"){
							swal("","保存成功","success");
							window.location.href = '${pageContext.request.contextPath}/settings/toRoList';
						}
					},
					error : function() {
						swal("","网络错误,保存失败!","error");
					}
		});
	}
</script> 
<!-- 错误屏蔽 但可能有错 -->
<!-- <script type="text/javascript">
   $("input.[checkbox]").click(function (){
      $(this).css("background","yellow").css("color","#fff")
   })
</script> -->
</body>
</html>
