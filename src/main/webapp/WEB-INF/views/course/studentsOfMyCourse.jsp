<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的学生列表</title>
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
			<span>我的学生列表</span>
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
	    <div class="layui-input-inline" style="vertical-align:unset;position:relative;">
	    	 <label>课程</label><select id="nationalId"></select>
	    </div>
	   	<div class="layui-input-inline" style="vertical-align:unset">
			<input placeholder="搜索内容" name="content" id="content">
		</div>
		<button id="searchBtn">查询</button>
	</li>
</div>

	<!--jQgrid  -->
	<h2 id="jqTableTitle">我的选课列表</h2>
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
			url:path+"/course/getStudentsOfMyCourseList?content="+content,
			datatype: 'json',
			
			//设置表格横向滚动条，自适应单元格宽度
			/* rownumbers: true,
	        shrinkToFit: false,
	        autoScroll: true, */
			
			width: jqTablew,
			height: 350,
			colNames: ['学号','姓名','学院','专业','班级','课程代码','课程中文名','课程外文名','课程学分','分数','状态','选课时间','备注'],
			colModel: [
				{name: 'scStudent.stuNumber', index: 'scCourse.cNo', align: 'center'},
				{name: 'scStudent.stuName', index: 'scCourse.cNo', align: 'center'},
				{name: 'scStudent.stuFaculty.facName', index: 'scCourse.cNo', align: 'center'},
				{name: 'scStudent.stuMajor.majorName', index: 'scCourse.cNo', align: 'center'},
				{name: 'scStudent.stuClass.claName', index: 'scCourse.cNo', align: 'center'},
				{name: 'scTeaCourse.tcCourse.cNo', index: 'scCourse.cNo', align: 'center'},
				{name: 'scTeaCourse.tcCourse.cName', index: 'scCourse.cName', align: 'center'},
				{name: 'scTeaCourse.tcCourse.cEnglishName', index: 'scCourse.cEnglishName', align: 'center'},
				{name: 'scTeaCourse.tcCourse.cScore', index: 'scCourse.cScore', align: 'center'},
				{name: 'scScore', index: 'scScore', align: 'center'},
				{name: 'scState', index: 'scState', align: 'center'},
				{name: 'scDate', index: 'scDate', align: 'center'},
				{name: 'scRemark', index: 'scRemark', align: 'center'},
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
				$.post(path+"/course/getStuCourseById",{
					"id":id,
				},function(result){
					$("#myModalLabel").text("打分");
					$("#stuCourseId").val(result.id);
					$("#modelNumber").val(result.scStudent.stuNumber);
					$("#modelName").val(result.scStudent.stuName);
					$("#modelCourse").val(result.scTeaCourse.tcCourse.cName);
				})
				$("#modalBtn").click();
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
		caption: '打分',
		buttonicon: 'iconfont icondafen',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow"); 
			if (id.length != 1) {
	            swal("", "请选择一门课进行打分！", "error");
	            return false;
	        }
			$.post(path+"/course/getStuCourseById",{
				"id":id+"",
			},function(result){
				$("#myModalLabel").text("打分");
				$("#stuCourseId").val(result.id);
				$("#modelNumber").val(result.scStudent.stuNumber);
				$("#modelName").val(result.scStudent.stuName);
				$("#modelCourse").val(result.scTeaCourse.tcCourse.cName);
			})
			$("#modalBtn").click();
		}
	}).navButtonAdd('#pager', {
		caption: '修改',
		buttonicon: 'iconfont iconweibiaoti2010104',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow"); 
			if (id.length != 1) {
	            swal("", "请选择一门课进行分数修改！", "error");
	            return false;
	        }
			$.post(path+"/course/getStuCourseById",{
				"id":id+"",
			},function(result){
				$("#myModalLabel").text("修改分数");
				$("#stuCourseId").val(result.id);
				$("#modelNumber").val(result.scStudent.stuNumber);
				$("#modelName").val(result.scStudent.stuName);
				$("#modelCourse").val(result.scTeaCourse.tcCourse.cName);
				$("#modelScore").val(result.scScore);
				$("#modelRemark").val(result.scRemark);
			})
			$("#modalBtn").click();
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
	                   url: "${pageContext.request.contextPath}/course/getStudentsOfMyCourseList",
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
<!-- 传值隐藏文本框（学生课程id） -->
<input type="hidden" id="stuCourseId">
<!--模态框触发按钮  -->
<button data-toggle="modal" data-target="#myModal" id="modalBtn" style="display: none"></button>
<!-- 模态框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="margin: 1% 19%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel" style="align-content: center">
				</h4>
			</div>
			<div class="modal-body" style="align-content: center">
				<label>学号</label><input id="modelNumber" style="width: 26%;height: 30px;border-radius: 5px;" readonly="readonly">
				<label>姓名</label><input id="modelName" style="width: 26%;height: 30px;border-radius: 5px;" readonly="readonly">
				<label>课程</label><input id="modelCourse" style="width: 26%;height: 30px;border-radius: 5px;" readonly="readonly">
				<hr>
				<label>分数</label><input id="modelScore" style="width: 26%;height: 30px;border-radius: 5px;" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
				<label>备注</label><input id="modelRemark" style="width: 50%;height: 30px;border-radius: 5px;">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal" id="modelClose">关闭
				</button>
				<button type="button" class="btn btn-primary" id="confirm">
					确认
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 模态框 -->
<script type="text/javascript">
	$("#confirm").click(function(){
		var stuCourseId = $("#stuCourseId").val();
		var modelScore = $('#modelScore').val();
		var modelRemark = $("#modelRemark").val();
		if(modelScore.length<1){
			layer.alert("请填写分数！", {icon: 6});
			return false;
		}
		layer.alert('确认打分吗？', {
		    skin: 'layui-layer-molv' //样式类名  自定义样式
		    ,closeBtn: 1    // 是否显示关闭按钮
		    ,anim: 1 //动画类型
		    ,btn: ['确认','取消'] //按钮
		    ,icon: 6    // icon
		    ,yes:function(){
				var teaCourseId = $("#teaCourseId").val();
				$.post("${pageContext.request.contextPath}/course/stuCourseScore",{
					"id":stuCourseId,
					"score":modelScore,
					"remark":modelRemark,
				},function(result){
					layer.alert("打分成功!", {icon: 6}); 
					$("#modelClose").click();
					$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
				})
		    }
		    ,btn2:function(){
		    	 layer.closeAll();
		    }});
		
	})
</script>
</body>
</html>