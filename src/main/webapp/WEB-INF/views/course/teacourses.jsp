<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../public.jsp"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的课程列表</title>
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
			<span>我的课程列表</span>
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
	<h2 id="jqTableTitle">我的课程列表</h2>
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
			url:path+"/course/getChooseCourseList?content="+content,
			datatype: 'json',
			
			//设置表格横向滚动条，自适应单元格宽度
			/* rownumbers: true,
	        shrinkToFit: false,
	        autoScroll: true, */
			
			width: jqTablew,
			height: 350,
			colNames: ['课程代码','课程中文名','课程外文名','课程学分','上课老师','上课季节','课程容量','已选人数','备注'],
			colModel: [
				{name: 'tcCourse.cNo', index: 'cNo', align: 'center'},
				{name: 'tcCourse.cName', index: 'cName', align: 'center'},
				{name: 'tcCourse.cEnglishName', index: 'cEnglishName', align: 'center'},
				{name: 'tcCourse.cScore', index: 'cScore', align: 'center'},
				{name: 'tcTeacher.teaName', index: 'teaName', align: 'center'},
				{name: 'tcSeason', index: 'tcSeason', align: 'center'},
				{name: 'tcCapacity', index: 'tcCapacity', align: 'center'},
				{name: 'tcSelected', index: 'tcSelected', align: 'center'},
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
				$.post(path+"/course/getTeaCourseById",{
					"id":id,
				},function(result){
					var tmpSeason = result.tcSeason;
					$("#season option").each(function(index,value){
						var tmp = $(this).val();
						if(tmp==tmpSeason){
							$(this).attr("selected",true); 
						}
					})
					$("#teaCourseId").val(result.id);
					$("#teacherName").val(result.tcTeacher.teaName);
					$("#myModalLabel").text(result.tcCourse.cNo+" "+result.tcCourse.cName+" "+result.tcCourse.cEnglishName);
					$("#modelCapacity").val(result.tcCapacity);
					getTeachers("modelTeacher",result.tcTeacher.id);
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
	}).navButtonAdd('#pager', {
		caption: '修改',
		buttonicon: 'iconfont iconweibiaoti2010104',
		onClickButton: function(){//按钮点击函数
			var id = $("#table").jqGrid('getGridParam', "selarrrow");     
			if (id.length != 1) {
	            swal("", "请选择一门课程！", "error");
	            return false;
	        }
            $.post(path+"/course/getTeaCourseById",{
				"id":id+"",
			},function(result){
				var tmpSeason = result.tcSeason;
				$("#season option").each(function(index,value){
					var tmp = $(this).val();
					if(tmp==tmpSeason){
						$(this).attr("selected",true); 
					}
				})
				$("#teaCourseId").val(result.id);
				$("#teacherName").val(result.tcTeacher.teaName);
				$("#modelCapacity").val(result.tcCapacity);
				$("#myModalLabel").text(result.tcCourse.cNo+" "+result.tcCourse.cName+" "+result.tcCourse.cEnglishName);
				getTeachers("modelTeacher",result.tcTeacher.id);
			})
			$("#modalBtn").click();
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
	       	layer.alert('确认删除此课程吗？', {
			    skin: 'layui-layer-molv' //样式类名  自定义样式
			    ,closeBtn: 1    // 是否显示关闭按钮
			    ,anim: 1 //动画类型
			    ,btn: ['确认','取消'] //按钮
			    ,icon: 6    // icon
			    ,yes:function(){
			    	$.ajax({
	           			url:"${pageContext.request.contextPath}/course/deleteTeaCourseById",
	           			type:"post",
	           			dataType:"json",
	           			data:{
	           				id:ids+"",
	           			},
	           			success:function(data){
	           				if(data.status==true){
	           					layer.alert("删除成功！", {icon: 6}); 
	           					$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
	           				}else{
	           					layer.alert("网络异常，删除失败！", {icon: 6}); 
	           				}
	           			},
	           			error:function(){
	           				layer.alert("网络异常，删除失败！", {icon: 6}); 
	           			}
	           		});
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
	                   url: "${pageContext.request.contextPath}/course/getChooseCourseList",
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
<!-- 传值隐藏文本框（课程id） -->
<input type="hidden" id="teaCourseId">
<input type="hidden" id="teacherName">
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
				<label>学院</label><select id="modelFaculty" style="width: 30%;border-radius: 5px;"></select>
				<label>专业</label><select id="major" style="width: 30%;border-radius: 5px;"></select><br><br>
				<label>内容</label><input id="modelContent" style="width: 30%; height: 27px;border-radius: 5px;" placeholder="教师名/工号"></input>
				<button class="btn-primary" style="width: 10%;margin: 0 10%;border-radius: 5px;font-size: 15px;" id="modelSearch">查询</button>
				<hr>
				<label>老师</label><select id="modelTeacher" style="width: 30%;border-radius: 5px;"></select>
				<label>课程容量</label><input id="modelCapacity" style="width: 26%;height: 30px;border-radius: 5px;" onkeyup="value=value.replace(/^(0+)|[^\d]+/g,'')">
				<label>上课季节</label>
				<select id="season" style="border-radius: 5px;">
					<option value=''>请选择</option>
					<option value="春">春季</option>
					<option value="秋">秋季</option>
				</select>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/teacher/add.js"></script>
<script type="text/javascript">
	getFaculty("modelFaculty");
	getTeachers("modelTeacher",null);
	$("#modelSearch").click(function(){
		var facId = $("#modelFaculty").val();
		var majId = $("#major").val();
		var content = $("#modelContent").val();
		getTeachers("modelTeacher",null,facId,majId,content);
	})
	$("#confirm").click(function(){
		var teacherName = $('#teacherName').val();
		var season = $("#season").val();
		var teacherId = $("#modelTeacher").val();//教师id
		var capacity = $("#modelCapacity").val();
		if(teacherName.length<1){
			layer.alert("请选择老师！", {icon: 6});
			return false;
		}
		if(capacity.length<1){
			layer.alert("请填写课程容量！", {icon: 6});
			return false;
		}
		if(season.length<1){
			layer.alert("请选择上课季节！", {icon: 6});
			return false;
		}
		layer.alert('确认选择'+teacherName+'老师来上此课吗？', {
		    skin: 'layui-layer-molv' //样式类名  自定义样式
		    ,closeBtn: 1    // 是否显示关闭按钮
		    ,anim: 1 //动画类型
		    ,btn: ['确认','取消'] //按钮
		    ,icon: 6    // icon
		    ,yes:function(){
				var teaCourseId = $("#teaCourseId").val();
				$.post("${pageContext.request.contextPath}/course/courseScheduleUpdate",{
					"id":teaCourseId,
					"teacherId":teacherId,
					"capacity":capacity,
					"season":season,
				},function(result){
					if(result.msg=="修改成功"){
						$("#modelClose").click();
						$("#table").jqGrid("setGridParam").trigger("reloadGrid", [{}]);
					}
					layer.alert(result.msg, {icon: 6}); 
				})
		    }
		    ,btn2:function(){
		    	 layer.closeAll();
		    }});
		
	})
</script>
</body>
</html>