/*
 * 2018/9/13
 * CCL
 * 面包屑的箭头
 */
(function(){
	var $i = $("#head i");
	$i.addClass('iconfont icon-xiayiye1');
})();

/*
 * 2018/9/11 FCJ 日期时间插件(限制结束日期与开始日期的范围)
 */
function chooseDate(idStart, idEnd, localIndex, txt){
	// 初始日期选择
	layui.use('laydate', function(){
		laydate = layui.laydate;
		laydate.render({		
			elem: idStart,
			done:function(value, data, e){
				// 开始日期
				reg = /[0-9]+/g;
				thisVal = value.match(reg);
				thisIntVal = '';
				for (let thisVals of thisVal) {
					thisIntVal += thisVals;
				}
				
				// 结束日期
				var endVal = $(idEnd).val();
				endVal = endVal.match(reg);
				endIntVal = '';
				for (let endVals of endVal) {
					endIntVal += endVals;
				}
				
				// 判断
				if(thisIntVal > endIntVal){
					$(idEnd).val('');
				}
			}
		});
	});
	
	// 结束日期选择
	layui.use('laydate', function(){
		laydate = layui.laydate;
		laydate.render({		// 结束日期
			elem: idEnd,
			done:function(value, data, e){
				// 结束日期
				reg = /[0-9]+/g;
				thisVal = value.match(reg);
				thisIntVal = '';
				for (let thisVals of thisVal) {
					thisIntVal += thisVals;
				}
				
				// 开始日期
				var startVal = $(idStart).val();
				startVal = startVal.match(reg);
				startIntVal = '';
				for (let startVals of startVal) {
					startIntVal += startVals;
				}
				
				// 判断
				if(thisIntVal < startIntVal){
					$(idEnd).val('');
					checkTips(txt, idEnd, localIndex)
				}
			}
		});
	});
}
// 日期时间插件(限制结束日期与开始日期的范围)...end

/*
 * 2018/9/13 FCJ jqGrid表格公共样式
 */
function resetIcon(){		// 重置jqGrid工具栏按钮样式
	$("#pager_left span").removeClass('ui-icon');		// 左侧菜单栏
	$("#pager_left span").css({
		width: '20px',
		height: '20px'
	});

	// 头部文字居中
	$(".ui-jqgrid-sortable").css({
		textAlign: "center"
	})

	// 头部背景及边线颜色
	$(".ui-widget-content .ui-state-default").css({
		border: 'none',
		background: '#f5f5f5'
	})

	// table边框颜色
	$(".ui-widget-content").css({
		border: '1px solid #e1e1e1'
	})
	// 首页icon
	$("#first_pager").empty();
	var homeTxt = '<span class="iconfont icon-page-first" style="color: #000; border-radius: 12px; border: 1px solid gray; padding: 2px; background: #fff; margin-right: 10px; "></span>'
	$("#first_pager").append(homeTxt);

	// 上一页icon
	$("#prev_pager").empty();
	var upTxt = '<span class="iconfont icon-shangyiye1" style="color: #000; border-radius: 12px; border: 1px solid gray; padding: 2px; font-size: 15px; background: #fff;"></span>'
	$("#prev_pager").append(upTxt);

	// 下一页icon
	$("#next_pager").empty();
	var downTxt = '<span class="iconfont icon-xiayiye" style="color: #000; border-radius: 12px; border: 1px solid gray; padding: 2px 1px 2px 4px; font-size: 15px; background: #fff; margin-right: 10px;"></span>'
	$("#next_pager").append(downTxt);

	// 最后一页icon
	$("#last_pager").empty();
	var lastTxt = '<span class="iconfont icon-page-last" style="margin-right: 10px;color: #000; border-radius: 12px; border: 1px solid gray; padding: 2px; background: #fff;"></span>'
	$("#last_pager").append(lastTxt);

	// 下拉框icon
	/*
	 * $(".ui-pg-selbox").css({ width: '90px' })
	 */
	var opt = $(".ui-pg-selbox option");
	var optTxt;
	for(var i = 0; i < opt.length; i++){
// optTxt = opt.eq(i).text()+'条/页';
		optTxt = opt.eq(i).text();
		$(".ui-pg-selbox option").eq(i).text(optTxt);
	}
}
// jqGrid表格公共样式...end

/*
 * 2018/09/13 FCJ jqGrid表格
 */

function drawTable(colModel, colModelNames, url){		// jqGrid表格
	var colModel = [];
	for(var i=0; i<colModelNames.length; i++){
		colModel[i] = {
				name: colModelNames[i],
				index: colModelNames[i],
				align: 'center',
		}
	}
	$("#jqTable").empty();
	var txt = '<table id="table"></table> <div id="pager"></div>';
	$("#jqTable").append(txt);

	var jqTablew = $("#jqTable").width();		// 获取jq的宽度

	$("#table").jqGrid({
		width: jqTablew,
		height: 350,
		url:url,
		datatype: 'json',
		rownumbers: true,
        shrinkToFit: false,// 禁止按比例缩放
        autoScroll: true,
		colNames: colNames,
		colModel: colModel,
// cellEdit:true, //单元格可编辑
		rowNum: 10,		// 每页显示多少条
		rowList: [10, 20, 30],		// 可供用户选择一页显示多少条
		pager: '#pager',
		rownumbers: true,		// 新增表格序号列
		multiselect: true,		// 显示复选框
		sortorder: "desc",		// 显示总条数
		viewrecords: true,
		loadComplete : function() {
			var table = this;
			var rowNum = $("#table").jqGrid('getGridParam','records');
			if (rowNum==0){
				if($("#norecords").html() == null){
					$("#table").parent().append("</pre><div id='norecords'  style='text-algin:center;'>没有查询记录！</div><pre>");
				}
				$("#norecords").show();
			}else{// 如果存在记录，则隐藏提示信息。
				$("#norecords").hide();
			}
		},
	});
}
// jqGrid表格...end

/*
 * 2018-09-30 fanchaojun 自定义下拉框
 */
function mySelect(inputId, selectHead, selectBody, selectData, selectDefalutVal, incomeVal){
	var iconXala = " icon-xiala1";
	var iconShangla = "icon-shangla";
	$(selectHead).siblings("i").addClass(iconXala);
	 $(selectHead).siblings("i").css({
		 right: "0px",
		 top: "2px",
		 fontSize: "26px"
	 })
	// select显示默认值
	var lis = "";
	for(var i = 0; i < selectData.length; i++){
		lis += "<li>" + selectData[i] + "</li>";
	}
	var select = $(selectBody);
	select.append(lis);
	$(selectHead).text(selectDefalutVal);

	// 显示传入值
	if(incomeVal.length > 8){
		incomeVal1 = incomeVal.substr(0, 8)+'...'
	}else{
		incomeVal1 = incomeVal;
	}
	var $li = $(selectBody).find("li");
	if(incomeVal != ""){
		$(selectHead).text(incomeVal1);
		for(var i = 0; i < $li.length; i++){
			if(incomeVal == $li.eq(i).text()){
				$li.eq(i).addClass("selectLi")
			}
		}
	}
	
	// 鼠标移入下拉框
	$(selectHead).click(function(e){
		if($(this).siblings('ul').css("display") == "none"){
			$(this).siblings("i").removeClass(iconXala);
			$(this).siblings("i").addClass(iconShangla);
			$(this).siblings('ul').show();
		}else{
			$(this).siblings("i").removeClass(iconShangla);
			$(this).siblings("i").addClass(iconXala);
			$(this).siblings('ul').hide();
		}
		e.stopPropagation();
	})
	
	// 点击下拉框选项
	$(selectBody).find("li").click(function(e){
		$(inputId).val($(this).text());
		
		$(this).addClass('selectLi').siblings('li').removeClass('selectLi');
		
		var txt = $(this).text();
		if(txt.length > 8){
			txt = txt.substr(0, 8)+'...'
		}
		
		$(selectHead).text(txt);
// e.stopPropagation();//打开：选择后下拉框不收起
	})
	
	// 移出select框
	/*
	 * $(selectBody).mouseleave(function(){ $(this).hide();
	 * $(this).siblings('i').removeClass(iconShangla);
	 * $(this).siblings('i').addClass(iconXala); })
	 */
	$("body").click(function(){
		$(selectBody).hide();
		$(selectBody).siblings('i').removeClass(iconShangla);
		$(selectBody).siblings('i').addClass(iconXala);
	})
}
// ...自定义下拉框end

/*
 * 2018-10-08 fanchaojun 自定义下拉框页面加载数据显示
 */
function selectLoad(txt, companyId, selectBodyId, selectHead){// txt页面加载传过来的下拉框内容
    $(companyId).val(txt);
    var li = $(selectBodyId).find('li');
    for(var i = 0; i < li.length; i++){
    	var liTxt = li.eq(i).text();
    	if(txt == liTxt){
    		li.eq(i).addClass("selectLi")
    	}
    }
    if(txt.length > 8){
    	txt = txt.substr(0, 8)+'...'
    }
    $(selectHead).text(txt);
 }
// 自定义下拉框页面加载数据显示...end

/*
 * 2018/10/11 FCJ 表单验证信息提示框...start
 */
function checkTips(checkTxt, idInput, localIndex){		// 验证提示信息
														// 、验证input的id、提示信息位置显示
	layui.use('layer', function(){
		layer = layui.layer;
		tip_index = layer.tips(checkTxt, idInput,{
			time: 0,
			tips: [localIndex, 'gray'],
		});
		$(idInput).click(function(){
			layer.close(tip_index)
		})
	})
}
// 表单验证信息提示框...end

/*
 * 2018/10/16 fanchaojun 动态生成下拉框...start
 */
function loadSelect(selectId, selectData, defalutVal){// 下拉框id,option数组,默认显示值
	var options = "<option selected style='display: none;'></option>";// select显示默认值
	for(var i = 0; i < selectData.length; i++){
		options += "<option value='" + selectData[i] + "'>" + selectData[i] + "</option>";
	}
	var select = $(selectId);
	select.append(options);
	select.find("option").eq(0).text(defalutVal);// select赋值默认值
}
// 动态生成下拉框...end

/*
 * 2018/10/17 fanchaojun 截取文件名
 */
function interceptName(str){
	str = str.split("").reverse().join("");
	var index = str.indexOf("\\");
	if(index < 0){
		index = str.indexOf("/");
	}
	if(index < 0){// 如果为同文件目录路径
		index = str.length;
	}
	str = str.substr(0, index);
	str = str.split("").reverse().join("");
	return str;
}
// 截取文件名...end

// 2018-10-22 fanchaojun 判断某个div是否具有滚动条
function ifDivHasScroll(divId){
		$(divId).eq(0).scrollTop(100);// 控制滚动条下移10px
	    if($(divId).eq(0).scrollTop()>0 ){
	        $(".ui-jqgrid-hbox").css({
	        	paddingRight: getScrollbarWidth()+"px"
	        })
	    }else{
	        // alert("没有滚动条");
	    }
	    $(divId).scrollTop(0);// 滚动条返回顶部
	}
// 判断某个div是否具有滚动条...end

// 2018-10-22 fanchaojun 获取滚动条宽度
function getScrollbarWidth() {
    var odiv = document.createElement('div'),// 创建一个div
        styles = {
            width: '100px',
            height: '100px',
            overflowY: 'scroll'// 让他有滚动条
        }, i, scrollbarWidth;
    for (i in styles) odiv.style[i] = styles[i];
    document.body.appendChild(odiv);// 把div添加到body中
    scrollbarWidth = odiv.offsetWidth - odiv.clientWidth;// 相减
    odiv.remove();// 移除创建的div
    return scrollbarWidth;// 返回滚动条宽度
}
// 获取滚动条宽度...end

// jqgrid下拉框...start
function pagesSelect(){
	$(".ui-pg-selbox").css({
		width: '90px'
	})
	var opt = $(".ui-pg-selbox option");
	var optTxt;
	for(var i = 0; i < opt.length; i++){
		if(i == 3){
			optTxt = "全部";
			$(".ui-pg-selbox option").eq(i).text(optTxt);
		}else{
			optTxt = opt.eq(i).text()+"条/页";
			$(".ui-pg-selbox option").eq(i).text(optTxt);
		}
	}
}
// 弹出框方法：表单提交询问
function myconfirm(value,table){
	layui.use('layer', function(){// 弹出框
		var layer = layui.layer;
		 layer.open({
		        type: 1
		        ,offset: 'center'
		        ,content: '<div style="font-size: 20px; width: 360px; text-align: center; height: 90px; line-height: 100px;">'+ value +'</div>'
		        ,btn: ['确定','取消']
		        ,btnAlign: 'c' // 按钮居中
		        ,shade: 0 // 不显示遮罩
		        ,yes: function(){
		        	$("#"+table).submit();
		        	layer.closeAll();
		        },
		        no:function(){
		        	layer.closeAll();
		        }
		      });
	})// 弹出框
}
//验证数字
function isNumber(a){
	var reg = /^[0-9]*$/;
	if (reg.test(a)) {
		return true;
	}
	return false;
}