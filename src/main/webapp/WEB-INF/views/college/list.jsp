<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学院信息列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/college/list.css">
<style type="text/css">
	.ui-left{
		 padding-left: 20%;
	}
</style>
</head>
<body>
<!--面包屑  -->
<div id="head">
		<li id="crumbs">
			<span>院系信息</span>
			>
			<span>院系信息列表</span>
		</li>
	</div>

<!-- 搜索 -->
<div id="search" style="height:40px">
	<li>
		
		<div class="layui-input-inline" style="vertical-align:unset;position:relative;">
	      <input type="text" class="layui-input" id="test3" placeholder="开始时间">
	      <span class="iconfont icontime" style="position:absolute;right:5px;top:-2px;"></span>
	    </div>
	    <div class="layui-input-inline" style="vertical-align:unset;position:relative;">
	      <input type="text" class="layui-input" id="test4" placeholder="结束时间">
	      <span class="iconfont icontime" style="position:absolute;right:5px;top:-2px;"></span>
	    </div>
	   	<div class="layui-input-inline" style="vertical-align:unset">
		<input placeholder="搜索内容" name="content" id="content">
		</div>
		<button id="searchBtn">查询</button>
	</li>
</div>

	<!--jQgrid  -->
	<h2 id="jqTableTitle">院系信息列表</h2>
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
		var content = $("#content").val();
		debugger;
		$("#table").jqGrid({
			url:path+"/college/getList?content="+content,
			datatype: 'json',
			
			//设置表格横向滚动条，自适应单元格宽度
			/* rownumbers: true,
	        shrinkToFit: false,
	        autoScroll: true, */
			
			width: jqTablew,
			height: 350,
			colNames: ['学院代码','学院名称','添加时间','备注'],
			colModel: [
				{name: 'facNumber', index: 'facNumber', align: 'center'},
				{name: 'facName', index: 'facName', align: 'center'},
				{name: 'facAddTime', index: 'facAddTime', align: 'center'},
				{name: 'facRemark', index: 'facRemark', align: 'center'},
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
				/*去掉多余图标  */
				/* debugger;
				for(var i=1;i<$("#table tbody tr").length;i++){
					$("#table tbody tr").eq(i).find("td").eq(2).find("a").find("span").removeClass("ui-icon");
				}
				$("#table tbody tr td").click(function(){
					$(this).find("a").find("span").removeClass("ui-icon");
					var mm = this.parentNode;
					var mm1 = mm.nextSibling;
					$(mm1).find("td").eq(1).find("span").removeClass("ui-icon");
					
				}) */
			},
			subGrid: true,//子表格
			// define the icons in subgrid
	  	    subGridOptions: {
	  	          "plusicon"  : "fa fa-plus",
	  	          "minusicon" : "fa fa-minus",
	  	          "openicon"  : "fa fa-share",
	  	      // load the subgrid data only once
	  	      // and the just show/hide
	  	      "reloadOnExpand" : false,
	  	      // select the row when the expand column is clicked
	  	      "selectOnExpand" : false,
	  	    },
	  	     subGridRowExpanded: function(subgrid_id, row_id) {
	  	      var subgrid_table_id, pager_id;
	  	      subgrid_table_id = subgrid_id+"_t";
	  	      pager_id = "p_"+subgrid_table_id;
	  	      $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
	  	      jQuery("#"+subgrid_table_id).jqGrid({
	  	        url:"${pageContext.request.contextPath}/major/getList?facId="+row_id,
	  	        datatype: "json",
		  	    rownumbers: true,
		  	 	autoScroll: false,   
	  	        colNames: ['专业代码','专业名称','添加时间','备注'],
	  	        colModel: [
	  	          {name:"majorNumber",index:"majorNumber",align:"center",width:"200px"},
	  	          {name:"majorName",index:"majorName",align:"center",width:"200px"},
	  	          {name:"majAddTime",index:"majAddTime",align:"center",width:"200px"},
	  	          {name:"marjorRemark",index:"marjorRemark",align:"center",sortable:false,width:"200px"},
	  	        ],
	  	        rowNum:20,
  	            pager: pager_id,
  	            sortname: 'num',
  	            sortorder: "asc",
  	            height: '100%',
  	            multiselect: true,
  	            width: jqTablew,
	  	        loadComplete : function() {
	  	        	$(".ui-pg-div").find("span").removeClass("ui-icon");//移除class:ui-icon
	  	        	$(".ui-jqgrid-bdiv").css({
	  	        		overflowX: "hidden"
	  	        	});
	  	        	$(".ui-subgrid").find(".ui-jqgrid-view").css({
	  	        		overflowX: "scroll",
	  	        		 width: "100%"
	  	        	})
					var table = this;
					var rowNum = $(this).jqGrid('getGridParam','records');
					if (rowNum==0){
						if($("#norecords"+subgrid_table_id).html() == null){
							$(this).parent().append("<div id='norecords"+subgrid_table_id+"' style='text-align:center;'>没有查询记录！</div>");
						}
						$("#norecords"+subgrid_table_id).show();
					}else{//如果存在记录，则隐藏提示信息。
						$("#norecords"+subgrid_table_id).hide();
					}	
					setTimeout(function(){
						/* styleCheckbox(table);
						updateActionIcons(table); */
						updatePagerIcons(table);
						/* enableTooltips(table); */
					}, 0);
				},
				ondblClickRow:function(id){
					window.location.href="${pageContext.request.contextPath}/major/toAdd?id="+id+"&operate=update&facId="+row_id;
				}
	  	      });
	  	    var subThead = $(".ui-jqgrid-labels");
			for(var i = 1; i < subThead.length; i++){
				$(".ui-jqgrid-labels").eq(i).css({
					background: "rgba(128,128,128,0.2)",
				});
				/* $(".ui-jqgrid-labels").eq(i).find("div").css({
					color: "white"
				}) */
			}
			//左下方按钮 初始化
			jQuery("#"+subgrid_table_id).jqGrid('navGrid','#'+pager_id,
				{  //navbar options
					edit: false,
					editicon : 'ace-icon fa fa-pencil blue',
					add: false,
					addicon : 'ace-icon fa fa-plus-circle purple',
					del: false,
					delicon : 'ace-icon fa fa-trash-o red',
					search: false,
					searchicon : 'ace-icon fa fa-search orange',
					refresh: true,
					refreshtext:"刷新",
					refreshicon : 'ace-icon fa fa-refresh green',
					view: false,
					viewicon : 'ace-icon fa fa-search-plus grey',
					position: 'left',
				}).navButtonAdd('#'+pager_id, {
					caption: '新增',
					buttonicon: 'ui-left iconfont iconzengjia',
					onClickButton: function(){//按钮点击函数
				       window.location.href="${pageContext.request.contextPath}/major/toAdd?operate=add&facId="+row_id;
					}
				}).navButtonAdd('#'+pager_id, {
					caption: '修改',
					buttonicon: 'ui-left iconfont iconweibiaoti2010104',
					onClickButton: function(){//按钮点击函数
						var id = $("#"+subgrid_table_id).jqGrid('getGridParam', "selarrrow");                                      
			            if (id.length != 1) {
			                swal("", "请选择一条记录进行修改！", "error");
			                return false;
			            }
						window.location.href="${pageContext.request.contextPath}/major/toAdd?id="+id+"&operate=update&facId="+row_id;
					}
				}) 
	  	      //去除下方的滚动条
	  	   	 // $("#"+subgrid_table_id ).closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
	  	    }
	  	  });
		};

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
	       window.location.href="${pageContext.request.contextPath}/college/toAdd?operate=add";
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
			window.location.href="${pageContext.request.contextPath}/college/toAdd?id="+id+"&operate=update";
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
			window.location.href="${pageContext.request.contextPath}/college/toAdd?id="+id+"&operate=show";
		}
	})/* .navButtonAdd('#pager', {
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
           			url:"${pageContext.request.contextPath}/daily/deleteDailys",
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
	}) */
	 //去除下方的滚动条
   	  //$("#table").closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
	  //去掉右边的 一小块白色的 ，并修改ui.jqgrid.css的第12行
	  //$("#gbox_table").find("table").css("width","100%");
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

		$("#searchBtn").click(function(){
			 $("#table").jqGrid("setGridParam", {
	                   url: "${pageContext.request.contextPath}/college/getList",
	                   mtype: "post",
	                   dataType: "json",
	                   postData: {	                	   	             
	                	   start: $("#test3").val(),
	                    	 end: $("#test4").val(),
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