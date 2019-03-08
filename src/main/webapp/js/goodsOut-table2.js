/*出库管理->填单出库：下面的出库列表*/

function pageInit2(){
	var grid_selector = "#list";
	var pager_selector = "#listTool";
	
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
   jQuery("#list").jqGrid({

	   	url:"outList?ids="+ids,
    	datatype: "json",
    	//横向滚动条
    	autowidth:true,
		shrinkToFit:false,   
		autoScroll: true,
	
		height: "280px",
       	colNames:['序号','出库单号','托运单号','订单类型 ','合同号','包装物类型','包装料品','数量','体积','出货日期','承运商','代理商简称','最终客户地址','DC区域'],
       	colModel:[
         	{name:'id',index:'id',width:70,align:"center",hidden:false},
         	{name:'number',index:'number',width:130,align:"center"},
         	{name:'deliverNumber',index:'deliverNumber',width:100,align:"center"},
	        {name:'orderType',index:'orderType',align:"center"},
	        {name:'contractNumber',index:'contractNumber',align:"center"},
	        {name:'goodsType',index:'goodsType',align:"center"},
	        {name:'packingMaterial',index:'packingMaterial',align:"center"},
	        {name:'amount',index:'amount',align:"center"},
	        {name:'volume',index:'volume',align:"center"},
	        {name:'deliverDate',index:'deliverDate',align:"center"},
	        {name:'carrier',index:'carrier',align:"center",editable:true,edittype:'select',editoptions: 
       		{value:getGroupContractNum()}},
	        {name:'agent',index:'agent',align:"center"},
	        {name:'finalCustomerAddress',index:'finalCustomerAddress',align:"center"},
	        {name:'dcWarehouse',index:'dcWarehouse',align:"center"}
        	/*{name:'goodsType',index:'goodsType',align:"center"},
         	{name:'orderType',index:'orderType',align:"center"},
         	{name:'deliverAmount',index:'deliverAmount',align:"center"},
         	{name:'volume',index:'volume',align:"center"},
         	{name:'weight',index:'weight',align:"center"},
         	{name:'goodsDetailed',index:'goodsDetailed',align:"center"},
         	{name:'packingNotes',index:'packingNotes',align:"center"},*/
       	],
       	rowNum:8,
       	/*rowList:[10,20,30],*/
       	pager: '#listTool',
       	sortname: 'id',
       	viewrecords: true,
       	editurl:"${pageContext.request.contextPath}/goodsOut/saveCarriers",
       	sortorder: "desc",
   
       	multiselect: true,
  	   	loadComplete : function() {
			var table = this;
			var rowNum = $("#list").jqGrid('getGridParam','records');
			if (rowNum==0){
			if($("#norecords").html() == null){
				$("#list").parent().append("</pre><div id='norecords'  style='text-algin:center;'>没有查询记录！</div><pre>");
			}
			$("#norecords").show();
			}else{
				//如果存在记录，则隐藏提示信息。
				$("#norecords").hide();
			}							
			setTimeout(function(){
				updatePagerIcons(table);
			}, 0);
		},
    	subGrid: true,
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
    	}
    	/*subGridRowExpanded: function(subgrid_id, row_id) {
	  		var subgrid_table_id, pager_id;
	  	    subgrid_table_id = subgrid_id+"_t";
	  	    pager_id = "p_"+subgrid_table_id;
	  	    $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
	  	    jQuery("#"+subgrid_table_id).jqGrid({
	  	        url:"outList?ids="+ids,
	  	        datatype: "json",
		  	    rownumbers: true,
	  	        colNames: ['代理商简称','合同号','产品类型','订单类型','发货数量','体积','重量','产品明细','包装备注'],
	  	        colModel: [
	  	          {name:"agent",index:"agent",width:150,align:"center"},
	  	          {name:"contractNumber",index:"contractNumber",width:200,align:"center"},
	  	          {name:"goodsType",index:"goodsType",width:200,align:"center"},
	  	          {name:"orderType",index:"orderType",width:200,align:"center"},
	  	          {name:"amount",index:"amount",width:150,align:"center",sortable:false},
	  	          {name:"volume",index:"volume",width:100,align:"center",sortable:false},
	  	          {name:"weight",index:"weight",width:100,align:"center",sortable:false},
	  	          {name:"detailed",index:"detailed",width:150,align:"center"},
	  	          {name:"packageRemark",index:"packageRemark",width:200,align:"center"},
	  	        ],
	  	        rowNum:20,
	  	        pager: pager_id,
	  	        sortorder: "asc",//排序的顺序，升序或降序
	  	        height: '100%',
	  	        width:'100%',
	  	        loadComplete : function() {
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
						updatePagerIcons(table);
					}, 0);
				},
		  	});
	  		$(window).triggerHandler('resize.jqGrid');//调节合适的宽度	
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
			});
	  	    //去除下方的滚动条
	  	   	$("#"+subgrid_table_id ).closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
	  	}*/
  	});
	/*//左下方按钮 初始化
	jQuery("#list").jqGrid('navGrid','#listTool',
	{  	
		refresh: true,
		refreshtext:"刷新",
		refreshicon : 'ace-icon fa fa-refresh green',		
		add: false,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del: false,
		delicon : 'ace-icon fa fa-trash-o red',
		edittext:"修改",
		edit: false,
		editicon : 'ace-icon fa fa-pencil blue',
		search: false,
		searchicon : 'ace-icon fa fa-search orange',
		view: false,
		viewicon : 'ace-icon fa fa-search-plus grey',
		position: 'left',
	});
  
 	//去掉右边的 一小块白色的 ，并修改ui.jqgrid.css的第12行
  	$("#gbox_table").find("table").css("width","100%"); 
  
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
}
	*/
   
	$(window).triggerHandler('resize.jqGrid');//调节合适的宽度	
		//左下方按钮 初始化
		jQuery("#list").jqGrid('navGrid','#listTool',
		{  //navbar options
			refresh: true,
			refreshtext:"刷新",
			refreshicon : 'ace-icon fa fa-refresh green',
			edittext:"修改",
			edit: true,
			editicon : 'ace-icon fa fa-pencil blue',
			add: true,
			addtext:"新增",
			addicon : 'ace-icon fa fa-plus-circle purple',
			del: true,
			deltext:"删除",
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
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				if(form.data('styled')) return false;

				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);

				form.data('styled', true);
				
			},
			onClick : function(e) {
				swal("","成功","success");
			}
		})
  //去除下方的滚动条
 	  //$("#table").closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'hidden' });
	  
	  //去掉右边的 一小块白色的 ，并修改ui.jqgrid.css的第12行
	  $("#gbox_table").find("table").css("width","100%");
	  
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
	
function getGroupContractNum(){
		var GroupContractNum = "";
		var i;
		$.ajax({
			type : "post",
			async : false,
			url : "${pageContext.request.contextPath}/agentType/getCarries",
			success : function(result){
				for(i = 0; i < result.length; i++){
			    	if(i != result.length - 1){
			    		GroupContractNum += result[i]+":"+result[i]+ ";";
			    	}else{
			    		 GroupContractNum +=result[i]+":"+ result[i]; 
			    	}
				}   
			}
		});
		return GroupContractNum;		//必须有此返回值
	}
/**
* 搜索
*/
$("#search").on("click",function(){
	var beginTime = $("#beginTime").val();
	var endTime = $("#endTime").val();
	var searchCont = $("#fazzyCont").val();
	var goodsSearchWord = $("#goodsSearchWord").val();
	var params = {
		"beginTime" : beginTime,
		"endTime" : endTime,
		"searchCont": searchCont,
		"goodsSearchWord": goodsSearchWord,
	};
	
	$("#list").jqGrid("setGridParam", {
		url:"${pageContext.request.contextPath}/goodsOut/getList",
		mtype:"post",
		dataType : "json",
		postData : params,
	}).trigger("reloadGrid",[{page:1}]);
});

/**
* 搜索
*/
$("#fazzySearch").on("click",function(){
	var searchCont = $("#fazzyCont").val();
	var params = {
		"searchCont" : searchCont,
	};
	$("#list").jqGrid("setGridParam", {
		url:"${pageContext.request.contextPath}/goodsOut/getList",
		dataType : "json",
		postData : params,
	}).trigger("reloadGrid",[{page:1}]);
});