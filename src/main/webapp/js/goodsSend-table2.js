//填单发货界面下面的托运单列表表格
pageInit3();

function pageInit3(){
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
  	url:"sendList",
    	datatype: "json",
    
    	autowidth:true,
		shrinkToFit:false,//禁止按比例缩放   
		autoScroll: true,
	
		height: "280px",
       	colNames:['序号','调拨托运单号','出库单号','合同号','代理商简称','承运商','最终客户','数量','体积','订单类型 ','包装物类型','包装料品','发货日期',/*'入库承运商',*/'DC区域','客户名称','收货人','电话','地址','发运方式'],
       	colModel:[
         	{name:'id',index:'id',width:70,align:"center",hidden:true},
         	{name:'sendNumber',index:'sendNumber',width:148,align:"center"},
         	{name:'number',index:'number',width:159,align:"center"},
	        {name:'contractNumber',index:'contractNumber',width:177,align:"center"},
	        {name:'agent',index:'agent',align:"center"},
	        {name:'carrier',index:'carrier',align:"center"},
	        {name:'finalCustomerAddress',index:'finalCustomerAddress',align:"center"},
	        {name:'amount',index:'amount',align:"center"},
	        {name:'volume',index:'volume',align:"center"},
	        {name:'orderType',index:'orderType',align:"center"},
	        {name:'goodsType',index:'goodsType',align:"center"},
	        {name:'packingMaterial',index:'packingMaterial',align:"center"},
	        {name:'outOpDate',index:'outOpDate',align:"center"},
	        /*{name:'goodsInCarrier',index:'goodsInCarrier',align:"center"},*/
	        {name:'dcWarehouse',index:'dcWarehouse',align:"center"},
	        {name:'cusName',index:'cusName',align:"center"},
	        {name:'consignee',index:'consignee',align:"center"},
	        {name:'tel',index:'tel',align:"center"},
	        {name:'address',index:'address',align:"center"},
	        {name:'shipVia',index:'shipVia',align:"center"}
       	],
       	rowNum:8,
       	/*rowList:[10,20,30],*/
       	pager: '#listTool',
       	sortname: 'id',
       	viewrecords: true,
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
			}else{//如果存在记录，则隐藏提示信息。
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
    	},
    	subGridRowExpanded: function(subgrid_id, row_id) {
	  		var subgrid_table_id, pager_id;
	  	    subgrid_table_id = subgrid_id+"_t";
	  	    pager_id = "p_"+subgrid_table_id;
	  	    $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
	  	    jQuery("#"+subgrid_table_id).jqGrid({
	  	        url:"${pageContext.request.contextPath}/goodsOut/getDetailList?goodsOutStorageId="+row_id,
	  	        datatype: "json",
		  	    rownumbers: true,
	  	        colNames: ['货位架','商品编号','商品名称','规格','出库数量','单位',/* '商品单价', */'总体积(m^3)','总重量(kg)','明细备注'],
	  	        colModel: [
	  	          {name:"warehouse.shelves",index:"warehouse.shelves",width:100,align:"center"},
	  	          {name:"goods.rfidCode",index:"goods.rfidCode",width:200,align:"center"},
	  	          {name:"goods.name",index:"goods.name",width:200,align:"center"},
	  	          {name:"goods.spec",index:"goods.spec",width:70,align:"center",sortable:false},
	  	          {name:"removeAmount",index:"removeAmount",width:70,align:"center",sortable:false},
	  	          {name:"goods.measurementUnit",index:"goods.measurementUnit",width:70,align:"center",sortable:false},
	  	          {name:"sumVolume",index:"sumVolume",width:70,align:"center",sortable:false},
	  	          {name:"sumWeight",index:"sumWeight",width:70,align:"center",sortable:false},
	  	          {name:"remark",index:"remark",width:150,align:"center",sortable:false},
	  	        ],
	  	        rowNum:20,
	  	        pager: pager_id,
	  	        sortname: 'num',
	  	        sortorder: "asc",
	  	        height: '100%',
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
	  	}
  	});
	//左下方按钮 初始化
	jQuery("#list").jqGrid('navGrid','#listTool',
	{  	//navbar options
		refresh: true,
		refreshtext:"刷新",
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
    	caption : " 撤回",
    	buttonicon : "ace-icon fa fa-mail-reply grey",
    	onClickButton : function() {
   			var ids = $("#list").jqGrid('getGridParam',"selarrrow");
			if(ids.length==0){
				swal("","请先选中记录","error");
				return false;
			}
			$.ajax({
				url:"backOut",
				type:"POST",
				traditional:true,
				dataType:"json",
				data:{
					ids:ids,
				},
				success:function(data){
					if(data.status == "success"){
						swal("","撤回成功","success");
						$("#list").jqGrid().trigger("reloadGrid");
						$("#table").jqGrid().trigger("reloadGrid");
					}
					if(data.status == "error"){
						swal("","失败","error");
					}
				}
			})
    	}
    });
  
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
}

	