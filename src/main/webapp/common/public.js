/* 2018-11-19 fanchaojun jqGrid表格*/
//左侧工具栏按钮样式
function resetLeftStyle(){
	//平均分配每个按钮所占td的宽度
	$("#pager_left").find("tbody").find(".ui-state-disabled").remove();
	var tdLen = $("#pager_left").find("tbody tr td").length;//工具栏按钮数量
	var tdW = Math.floor(100/tdLen);
	$("#pager_left").find("table").css({width: "100%"});
	$("#pager_left").find("tbody tr td").attr("style", "display: inline-block");
	$("#pager_left").find("tbody tr td").css({
		width: tdW + "%",
		position: "relative",
	})
	debugger;
	//每个按钮td中的内容居中
	var divW = $("#pager_left").find("tbody tr td div").css("width");
	var halfDivW = -Math.floor(parseInt(divW)/2);
	$("#pager_left").find("tbody tr td div").css({
		position: "absolute",
		left: "50%",
		marginLeft: halfDivW + "px",
		color: "#327dd0"
	})
	$(".iconfont").removeClass("ui-icon");
}
//分页按钮样式
function pagesIcon(){	
	debugger;
	//首页icon
	$("#first_pager").empty();
	var homeTxt = '<span class="iconfont icondiyiye" style="color: #000"></span>'
	$("#first_pager").append(homeTxt);
	
	//上一页icon
	$("#prev_pager").empty();
	var upTxt = '<span class="iconfont iconhoutui" style="color: #000"></span>'
	$("#prev_pager").append(upTxt);
	
	//下一页icon
	$("#next_pager").empty(); 
	var downTxt = '<span class="iconfont iconqianjin" style="color: #000"></span>'
	$("#next_pager").append(downTxt);
	
	//最后一页icon
	$("#last_pager").empty();
	var lastTxt = '<span class="iconfont iconzuihouyiye" style="margin-right: 10px; color: #000"></span>'
	$("#last_pager").append(lastTxt);
	
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
	
	$("#pager_center").css("color", "gray");
	$(".iconfont").removeClass("ui-icon");
}
