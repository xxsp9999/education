<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="../../public.jsp" %>
    <!--权限界面  ChenCongLiang 2019-3-5  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>权限列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/settings/role/list.css">
</head>
<body>
<!--面包屑  -->
	<div id="head">
		<li id="crumbs">
			<span>系统设置</span>
			>
			<span>权限管理</span>
		</li>
	</div>
	<!--搜索  -->
	<div id="search">
		<li>
			<div class="layui-input-inline" style="vertical-align:unset">
		        <input type="text" class="layui-input" id="test3" placeholder="开始时间">
		        <span class="iconfont iconriqi" style="position:absolute;right:5px;top:-5px;"></span>
		      </div>
			<div class="layui-input-inline" style="vertical-align:unset">
		        <input type="text" class="layui-input" id="test4" placeholder="结束时间">
		        <span class="iconfont iconriqi" style="position:absolute;right:5px;top:-5px;"></span>
		      </div>
			<input placeholder="搜索内容" name="content" id="content">
			<button id="searchBtn">查询</button>
		</li>
	</div>
	<!--jQgrid  -->
	<h2 id="jqTableTitle">角色信息列表</h2>
	<div id="jqTable" style="width: 96%; margin: 0 auto;"></div>

<script>
drawTable();
//客户信息jqgrid
function drawTable(){
		$("#jqTable").empty();
		var txt = '<table id="table"></table> <div id="pager"></div>';
		$("#jqTable").append(txt);
		
		var jqTablew = $("#jqTable").css('width');		//获取jq的宽度
		jqTablew = parseInt(jqTablew);
		debugger;
		$("#table").jqGrid({
			url:path+"/settings/getRoList",
			datatype: 'json',
			
			//设置表格横向滚动条，自适应单元格宽度
			/* rownumbers: true,
	        shrinkToFit: false,
	        autoScroll: true, */
			
			width: jqTablew,
			height: 350,
			colNames: ["角色名称","创建时间",'创建人','操作'],
			colModel: [
						

				{name: 'name', index: 'name', align: 'center'},
				{name: 'createDate', index: 'createDate', align: 'center'},
				{name:'createUser.name',index:'createUser.name', align:"center"}, 

				
				 {name:'opear',index:'opear', width:80, align:"center",formatter:function(cellvalue,options,rowObject){
	  	        	 var id = rowObject.id;
	  	        	 return "<a href='${pageContext.request.contextPath}/role/addModuleRole?id="+id+"'>分配模块</a>";
	  	         }}, 
			],
			rowNum: 10,		//每页显示多少条
			rowList: [10, 20, 30, 99999],		//可供用户选择一页显示多少条
			pager: '#pager',
			rownumbers: true,		//新增表格序号列
			multiselect: true,		//显示复选框
			sortname: 'id',
			sortorder: "desc",		//显示总条数
			viewrecords: true,
			
			//加上editable:true;设置单元格为可编辑
			/*cellEdit:true,	
			cellsubmit:"clientArray",*/
			
			//数据加载完成后执行函数(没数据时的空数据显示)
			loadComplete : function() {
				var table = this;
				var rowNum = $("#table").jqGrid('getGridParam','records');
				if (rowNum==0){
					if($("#norecords").html() == null){
						$("#table").parent().append("<div id='norecords'  style='text-algin:center;'>没有查询记录！</div>");
					}
					$("#norecords").show();
				}else{//如果存在记录，则隐藏提示信息。
					$("#norecords").hide();
				}	
			},
			
			//行双击触发事件
			ondblClickRow:function(id){
				window.location.href="${pageContext.request.contextPath }/settings/toRoAdd?id="+id+"&operate=update";
			}
		});
	}

	//左下角按钮设置
	jQuery("#table").jqGrid('navGrid', '#pager', {
		add: false,		//添加
		addtext: '新增',
		
		edit: false,		//编辑
		edittext: '修改',
		
		del: false,		//删除
		deltext: '删除',
		
		search: false,		//搜索
		searchtext: '查找',
		
		refresh: false,		//刷新
		refreshtext: '刷新',
		
		view: false,		//查看记录
		viewtext: '记录'
	})
	.navButtonAdd('#pager', {
		caption: '新增',
		buttonicon: 'iconfont iconzengjia',
		onClickButton: function(){//按钮点击函数
	       window.location.href="${pageContext.request.contextPath }/settings/toRoAdd?operate=add";
		}
	}).navButtonAdd('#pager', {
		caption: '修改',
		buttonicon: 'iconfont iconweibiaoti2010104',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow");                                      
            if (id.length != 1) {
                swal("", "请选择一条记录进行修改！", "error");
                return false;
            }
			window.location.href="${pageContext.request.contextPath }/settings/toRoAdd?id="+id+"&operate=update";
		}
	}).navButtonAdd('#pager', {
		caption: '查看',
		buttonicon: 'iconfont iconchakan',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow");                                      
            if (id.length != 1) {
                swal("", "请选择一条记录进行查看！", "error");
                return false;
            }
			window.location.href="${pageContext.request.contextPath }/settings/toRoAdd?id="+id+"&operate=show";
            
		}
	}).navButtonAdd('#pager', {
		caption: '删除',
		buttonicon: 'iconfont iconshanchu',
		onClickButton: function(){//按钮点击函数
			var ids = $("#table").jqGrid('getGridParam', "selarrrow"); 
	       	if(ids.length<1){
	       		swal("","请至少选择一条数据","error");
	       		return false;
	       	}
           	swalShow("确定删除吗？","warning",function(){
           		$.ajax({
           			url:"${pageContext.request.contextPath}/settings/deleteRoles",
           			type:"post",
           			dataType:"json",
           			data:{
           				ids:ids+"",
           			},
           			success:function(data){
           				if(data.status==true){
           					$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
           				}else{
           					swal("","网络异常，删除失败！","error");
           				}
           			},
           			error:function(){
           				swal("","网络异常，删除失败！","error");
           			}
           		});
           	});
		}
	})
	$("#searchBtn").click(function(){
		 $("#table").jqGrid("setGridParam", {
                   url: "${pageContext.request.contextPath}/settings/getRoList",
                   mtype: "post",
                   dataType: "json",
                   postData: {
                  	 start:$("#test3").val(),
                  	 end:$("#test4").val(),
                     content: $("#content").val(),
                   }
         }).trigger("reloadGrid",[{page:1}]);
	});
	resetLeftStyle();
	pagesIcon();
	/* 时间区间 */
//开始时间插件
layui.use('laydate', function(){
		var laydate = layui.laydate;
		 laydate.render({
    elem: '#test3'
    ,type: 'date',
    done:function(value, date, endDate){
    	debugger;
    	value = value.replace(/-/g,"");
    	value = value.replace(/:/g,"");
    	value = value.replace(/ /g,"");
    	var start=value;
    	var end=$("#test4").val();
    	end = end.replace(/-/g,"");
    	end = end.replace(/:/g,"");
    	end = end.replace(/ /g,"");
    	if(start>=end&&end!=""){
    		/*$("#test4").val("");
    		$("#test3").val(end);*/
    		//alert("格式不对");
    		layui.use('layer', function(){//弹出框
				var layer = layui.layer;
				 layer.tips('违规的区间范围，请重新选择', '#test3',{
				 	tips:[3,'#000'],
				 });
			})//弹出框
    		setTimeout(() => {
				$("#test4").val("");
			}, 10);
    	}
    }
  });
	})
	//结束时间插件
layui.use('laydate', function(){
		var laydate = layui.laydate;
		 laydate.render({
    elem: '#test4'
    ,type: 'date',
    done:function(value, date, endDate){
    	debugger;
    	value = value.replace(/-/g,"");
    	value = value.replace(/:/g,"");
    	value = value.replace(/ /g,"");
    	var end=value;
    	var start=$("#test3").val();
    	start = start.replace(/-/g,"");
    	start = start.replace(/:/g,"");
    	start = start.replace(/ /g,"");
    	if(start>=end&&start!=""){
    		layui.use('layer', function(){//弹出框
				var layer = layui.layer;
				 layer.tips('违规的区间范围，请重新选择', '#test4',{
				 	tips:[3,'#000'],
				 });
			})//弹出框	
    		setTimeout(() => {
				$("#test4").val("");
			}, 10);
    	}
    }
  });
	})
</script>
</body>
</html>