<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>课程列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/course/list.css">
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
			<span>选课管理</span>
			>
			<span>课程列表</span>
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
	<h2 id="jqTableTitle">课程列表</h2>
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
			url:path+"/course/getStuChooseCourseList?content="+content,
			datatype: 'json',
			
			//设置表格横向滚动条，自适应单元格宽度
			/* rownumbers: true,
	        shrinkToFit: false,
	        autoScroll: true, */
			
			width: jqTablew,
			height: 350,
			colNames: ['课程代码','课程中文名','课程外文名','课程学分','上课老师','课程容量','课程余量','备注'],
			colModel: [
				{name: 'tcCourse.cNo', index: 'tcCourse.cNo', align: 'center'},
				{name: 'tcCourse.cName', index: 'tcCourse.cName', align: 'center'},
				{name: 'tcCourse.cEnglishName', index: 'tcCourse.cEnglishName', align: 'center'},
				{name: 'tcCourse.cScore', index: 'tcCourse.cScore', align: 'center'},
				{name: 'tcTeacher.teaName', index: 'tcTeacher.teaName', align: 'center'},
				{name: 'tcCapacity', index: 'tcCapacity', align: 'center'},
				{name: 'tcAllowance', index: 'tcAllowance', align: 'center'},
				{name: 'cRemark', index: 'cRemark', align: 'center'},
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
				layer.alert("确认选择该课程吗？", {
				    skin: 'layui-layer-molv' //样式类名  自定义样式
				    ,closeBtn: 1    // 是否显示关闭按钮
				    ,anim: 1 //动画类型
				    ,btn: ['确认','取消'] //按钮
				    ,icon: 6    // icon
				    ,yes:function(){
				    	$.post("${pageContext.request.contextPath}/course/stuChoose",{
							"id":id+"",
						},function(result){
							if(result.msg==null){
								$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
								layer.alert("选课成功，请到我的选课查看选课信息", {icon: 6}); 
							}else{
								layer.alert(result.msg, {icon: 6}); 
							}
							$("#modelClose").click();
						})
				    }
				    ,btn2:function(){
				    	 layer.closeAll();
				    }});
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
		caption: '选择',
		buttonicon: 'iconfont iconzengjia',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow");             
			if (id.length == 0) {
	            swal("", "请至少选择一门课！", "error");
	            return false;
	        }
			layer.alert("确认选择该课程吗？", {
			    skin: 'layui-layer-molv' //样式类名  自定义样式
			    ,closeBtn: 1    // 是否显示关闭按钮
			    ,anim: 1 //动画类型
			    ,btn: ['确认','取消'] //按钮
			    ,icon: 6    // icon
			    ,yes:function(){
			    	$.post("${pageContext.request.contextPath}/course/stuChoose",{
						"id":id+"",
					},function(result){
						if(result.msg==null){
							$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
							layer.alert("选课成功，请到我的选课查看选课信息", {icon: 6}); 
						}else{
							layer.alert(result.msg, {icon: 6}); 
						}
						$("#modelClose").click();
					})
			    }
			    ,btn2:function(){
			    	 layer.closeAll();
			    }});
		}
	})
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
	                   url: "${pageContext.request.contextPath}/course/getStuChooseCourseList",
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