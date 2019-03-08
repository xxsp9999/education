
/**
 * @description 三列查询库
 * @param head
 * @param data
 * @param id
 * 杨世明
 */
function autocompletePlus3(head,data,firstId,secendId,linkmanId,number) {
		/*- 自定义的表头 -
		var headarray = ${requestScope.jsonHead};*/
	    var headarray = head;
	  
	
		/*- 自动补全的数据源 -
		var projects = ${requestScope.costomerData};*/		
	    var projects=data;
	    
	    var autoid=firstId;
	    var autoid2=secendId;
	    var autoid3=linkmanId;
		/*- 高亮样式名 -*/
		var highClass = "highclass"; 
		$("#"+autoid).autocomplete({
			/*- 自定义表头的赋值 -*/
			tablehead : headarray,
			minLength : 0,
			source : projects,
			select : function(event, ui) {
				if (ui.item.id == -1) {
					$("#"+autoid).val(null);
					$("#"+autoid2).val(null);
					$("#"+autoid3).val(null);
					return false;
				}
				$("#"+autoid).val(ui.item.value);
				$("#"+autoid2).val(ui.item.label);
				//$("#"+autoid2).val(ui.item.id);
				$("#"+autoid3).val(ui.item.id);
				if(number==1){
					 getShipper();
				}else if(number==2){
					getConsignee();
				}else if(number==3){
					getNotifier();
				}
				return false;
			}
		}).autocomplete("instance")._renderItem = function(ul, item, index) {
			var row;
			if (index == 0) {
				row = 'trhead';
			} else if (index % 2 == 1) {
				row = 'coloreven';
			} else {
				row = 'colorodd';
			}
			var inputChar = $("#"+autoid).val();
			label = item.label.replace(item.label, replaceHigh(inputChar,
					item.label, highClass));
			value = item.value.replace(item.value, replaceHigh(inputChar,
					item.value, highClass));
			desc = item.desc.replace(item.desc, replaceHigh(inputChar,
					item.desc, highClass));
			return $("<table class='autocomplete'>").append(
					"<tr class='"+row+"'><td>" + value + "</td><td>" + label
							+ "</td><td>" + desc + "</td></tr>").appendTo(ul);
		};
	};