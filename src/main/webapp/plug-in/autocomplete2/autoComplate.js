/**
 * 调用自动补全所有的框
 * @author 罗茂
 * @param parameters 所有的参数
 */
function autoComplete(parameters) {
	// className: "User", "Port"等
	for(var className in parameters) {
		// completeArray: json数组, 定义了需要自定补全的输入框
		var completeArray = parameters[className];
		for(var i = 0;i < completeArray.length;i++) {
			complete(completeArray[i], className);
		};
		// 一组三列框的json
	};
	
	// 在所有的补全完成之后, 动态添加css文件
	$("<link>").attr({
	    rel: "stylesheet",
	    type: "text/css",
	    // href: "/logistics/plug-in/autocomplete2/autoComplate.css"
	    href: contextPath + "/plug-in/autocomplete2/autoComplate.css"
	}).appendTo("head");
}

/**
 * 补全一组三联框
 * @author 罗茂
 * @param complete: json, 是一组需要自动完成的三联框列名称以及补全的input的id
 * @param className: string, 类名, 发送请求的时候用到
 * @return 无
 */
function complete(complete, className) {
	var inputBox = new Array(); // inputBox是三个补全框的Object对象
	var visibleColumn = new Array(); // 需要显示的列
	var index = 0; // 根据id获取多个补全框
	for(var j in complete) {
		// 字段是否可用
		visibleColumn[index] = j;
		// 如果没有值, 那么那一列不需要补全
		if(!isEmpty(complete[j])) {
			inputBox[index] = $("#" + complete[j]);
		}
		index++;
		// 补全框对应的输入框的值
		// alert(complete["column" + index]);
	}
	// inputBox[0]是第一个输入框, 坚挺
	inputBox[0].on("keyup mouseup change", function(event) {
    	// $(this).val($(this).val().trim()); // 去除value的空格, 可能导致无法输入的问题
    	// 值没有变化, 不用发送请求
    	if($(this).val() == $(this).attr("oldValue")) {
    		// table第二次点击时候可能是空值, 不要用table.show();
    		$("#" + $(this).prop("id") + "AutoComplete").show();
    		return;
    	}
    	// initializeTable(inputBox, table, thead, tbody_data, tbody_more);
		// 根据id判断表格是否存在
		if($("#" + $(this).prop("id") + "AutoComplete").length != 0) {
   			// clearTimeout(inputBox[0].attr("timer"));
   			$("#" + $(this).prop("id") + "AutoComplete").remove();
		}
    	// 表格, 表头, 显示数据的tbody, 加载更多的tbody
    	var table = $("<table id='" + this.id + "AutoComplete'>"); // 创建table, id为输入框的id + AutoComplete
    	var thead = $("<thead></thead>"); 
    	var tbody_data = $("<tbody>"); 
    	var tbody_more = $("<tbody><tr><td colspan='3' align='center'><a href='#' onclick='return false;'>点击加载更多</a></td></tr></tbody>"); // 创建加载更多的tbody
    	// 把thead和tbody加入到table, 把table加入到body
    	table.append(thead).append(tbody_data).append(tbody_more).appendTo("body");
        // 显示数据
        showData(inputBox, table, thead, tbody_data, tbody_more, className, visibleColumn);
		$(this).attr("oldValue", $(this).val());
	});
}

/**
 * ajax查询数据, 并且显示到thead
 * @author 罗茂
 * @param className: 需要的类名
 * @param tbody_data: 显示数据的tbody
 * @return 无
 */
function showData(inputBox, table, thead, tbody_data, tbody_more, className, visibleColumn, pageNo) {
	if(pageNo == undefined) {
		pageNo = 1;
	}
	// searchStr=" + encodeURI(inputBox[0].val())
	$.post(contextPath + "/complete/get" + className, "searchStr=" + inputBox[0].val() + "&pageNo=" + pageNo, function(data, status){
		var json = $.parseJSON(data);
		var jsonHead = json.head[0];
		// 设置表头
		if(thead.find("tr").length != 1) {
			thead.find("tr").remove();
			var thead_tr = $("<tr>");
			for(var j = 0;j < visibleColumn.length;j++) {
				thead_tr.append("<td>" + jsonHead[visibleColumn[j]] + "</td>");
			}
			thead.append(thead_tr);
		};  				
		var jsonData = json.data;
    	// 不是新加入就要删除table的显示数据的body的行
    	if(pageNo == 1) {
			tbody_data.children("tr").remove();
    	}
    	// 添加行
		for(var i in jsonData) {
			// jsonData的一行
			var row = jsonData[i];
			// 表格的table
			var tbody_tr = $("<tr>");
			for(var j = 0;j < visibleColumn.length;j++) {
				tbody_tr.append("<td>" + row[visibleColumn[j]] + "</td>");
				tbody_data.append(tbody_tr);
			}
			// alert(visibleColumn.length); // 3
			// 原有的方式没有限制列数, 全部显示出来了
			// tbody_data.append("<tr><td>"+row.column1+"</td><td>"+row.column2+"</td><td>"+row.column3+"</td></tr>");
		}
		
		/*
		var childrens = table.children();
		for(var i = 0;i< childrens.length;i++) {
			alert(childrens[i].tagName);
		}
		alert("子元素的长度: " + table.children().length);
		*/
        // 行的点击事件, 设置值并且隐藏
        tbody_data.children("tr").click(function () {
        	for(var i = 0;i< inputBox.length;i++) {
        		if(inputBox[i] != undefined) {
	                // inputBox[0].val($(this).children("td:eq(0)").text());
	                // inputBox[1].val($(this).children("td:eq(1)").text());
	                // inputBox[2].val($(this).children("td:eq(2)").text());
	                inputBox[i].val($(this).children("td:eq(" + i + ")").text());
        		}
        	}
            table.hide();
        });
		// 加载更多, 追加
		tbody_more.find("a").click(function() {
			$(this).attr("pageNo", pageNo);
			showData(inputBox, table, thead, tbody_data, tbody_more, className, visibleColumn, (pageNo + 1));
		});
		
        if(json.hasNext) {
        	tbody_more.show();
        } else {
        	tbody_more.hide();
        }
        // 绑定事件
        bindEvent(inputBox[0], table);
        // 设置样式
    	table.css({
            "display": "block",
            "position": "absolute",
            // "left": left + "px",
            // "top": top + "px",
            "left": inputBox[0][0].offsetLeft + "px",
            "top": (inputBox[0][0].offsetTop + inputBox[0][0].offsetHeight) + "px",
			"z-index": 99, 
			"font-size": "14px"
        });
    	table.addClass("autoCompleteTable");
    	tbody_data.addClass("autoComplateData");
	});
}
/**
 * 给第一个输入框和表格绑定mouseenter和mouseleave事件
 * @author 罗茂
 * @param input: 第一个输入框
 * @param table: 表格
 * @return 无
 */
function bindEvent(input, table) {
    // 移除第一个输入框和表格原来绑定的事件
    input.unbind("mouseleave mouseenter");
    table.unbind("mouseleave mouseenter");
 	// 第一个输入框鼠标离开事件
	input.mouseleave(function (evevt) {
    	var timer = setTimeout(function() {
        	table.hide();
        }, 500);
        input.attr("timer", timer);
        evevt.stopPropagation();
    });
	// 表格鼠标离开事件
    table.mouseleave(function (evevt) {
    	var timer = setTimeout(function() {
        	table.hide();
        }, 500);
        input.attr("timer", timer);
        evevt.stopPropagation();
    });
	// 第一个输入框鼠标进入事件
    input.mouseenter(function (evevt) {
    	// table.hide();
        clearTimeout(input.attr("timer"));
        evevt.stopPropagation();
    });
    // 表格鼠标进入事件
    table.mouseenter(function (evevt) {
    	// table.hide();
        clearTimeout(input.attr("timer"));
        evevt.stopPropagation();
    });
}
/**
 * 创建table, 并且设置样式
 * @Description: 写在里面了
 * @author 罗茂
 * @param input: 输入框
 * @return 无
 */
/*
function createTable(input) {
	if($("#" + this.id + "AutoComplete").length != 0) {
		return true;
	}
	// 创建table, 设置表格的class为autoCompleteTable, id为输入框的id + AutoComplete
	table = $("<table id='" + this.id + "AutoComplete' class='autoCompleteTable'>");
	// 创建thead
	thead = $("<thead></thead>");
	// 创建显示数据的tbody
	tbody_data = $("<tbody class='autoCompleteData'></tbody>");
	// 创建加载更多的tbody
	tbody_more = $("<tbody><tr><td colspan='3' align='center'><a href='#' onclick='return false;'>点击加载更多</a></td></tr></tbody>");
	// 把thead和tbody加入到table
	table.append(thead).append(tbody_data).append(tbody_more);
	// 设置table格的位置绝对定位, 显示, table在input的正下方(table距离页面左的距离 = input距离页面左的距离, table距离页面顶部的距离 = input距离页面顶部的距离 + 输入框的高度)
    $(table).css({
        "display": "block",
        "position": "absolute",
        "left": this.offsetLeft + "px",
        "top": (this.offsetTop + this.offsetHeight) + "px"
    });
}
*/

/**
 * 初始化表格
 * @Deprecated 不需要初始化了
 * @author 罗茂
 * @param complete: 是一组需要自动完成的三联框列名称以及补全的input的id
 * @param className: 类名
 * @return 无
 */
/*
function initializeTable(inputBox, table, thead, tbody_data, tbody_more) {
	// 根据id判断表格是否存在
	if($("#" + inputBox[0][0].id + "AutoComplete").length == 0) {
    	table = $("<table id='" + inputBox[0][0].id + "AutoComplete'>"); // 创建table, id为输入框的id + AutoComplete
    	thead = $("<thead></thead>"); // thead
    	tbody_data = $("<tbody>"); // 显示数据的tbody
    	tbody_more = $("<tbody><tr><td colspan='3' align='center'><a href='#' onclick='return false;'>点击加载更多</a></td></tr></tbody>"); // 创建加载更多的tbody
    	// 把thead和tbody加入到table
    	table.append(thead);
    	table.append(tbody_data);
    	table.append(tbody_more);
    	// 把table加入到body
    	table.appendTo("body");
	} else {
		table = $("#" + inputBox[0][0].id + "AutoComplete");
    	thead = table.find("thead");
    	tbody_data = table.find("tbody:eq(0)");
    	tbody_more = table.find("tbody:eq(1)");
	}
}
*/
/**
 * 设置样式 	
 * @Deprecated	--使用了动态加载css文件, 不需要再来通过js设置样式
 * @author 罗茂
 * @param table
 * @param thead
 * @param tbody_data
 * @param tbody_more
 * @return 无
 */
/*
function setStyle(table, thead, tbody_data, tbody_more) {
	// 为表格设置样式
	thead.css({
		"font-family": "宋体"
	});
	tbody_data.css({
		"max-width": "220px",
		"max-height": "280px",
		"font-family": "楷体"
	});
	thead.find("tr").css({
		"background-color": "#5B9BD5",
		"color": "white"
	});
	thead.find("td").css({
        "padding": "5px 10px",
    	"margin": "2px 2px"
	});
	thead.find("td").css({
		"text-align": "center"
	});
	tbody_data.find("tr:odd").css({
		"background-color": "#EAEFF7"
	});
	tbody_data.find("tr:even").css({
		"background-color": "#D2DEEF"
	});
	tbody_data.find("tr").hover(function() {
		// $(this).attr("currenyColor", $(this).css("background-color"));
		$(this).css("background-color", pink);
	}, function() {
		// $(this).attr("currenyColor", $(this).css("background-color"));
		$(this).css("background-color", pink);
	});
	tbody_data.find("td").css({
        "padding": "5px 10px",
    	"marginmargin": "2px 2px",
    	"max-width": "150px",
		"overflow": "scroll",
    	"word-wrap": "break-word",
		"text-overflow":"ellipsis",
		"overflow": "hidden"
	});
}
*/


$(function(jqqqqqqqqqq) {
//	$.extend({
//		"getMax": function(param1, param2) {
//			return param1 > param2? param1 : param2;
//		}
//	});
//	alert("Max is: " + $.getMax(3, 2));
//	
//	$.fn.extend({
//		alertWhileClick : function(value) {
//			$(this).click(function() {
//				alert(value);
//				alert($(this).prop("tagName"));
//			});
//		}
//	});
//	$("#input1").alertWhileClick("你点击了!");
//	
//	var $input1 = $("#input1");
//	var $input2 = $("#input2");
//	// alert($input1.length);
//	// alert($.extend($input1, $input2).length);
//	$input1.css("background-color", "red");
//	$input2.css("background-color", "green");
//	$.extend($input1, $input2).css("background-color", "cyan");
//	alert(jqqqqqqqqqq("#input1")[0].style["background-color"]);
});


