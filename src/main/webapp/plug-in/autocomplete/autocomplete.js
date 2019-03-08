function autocompletePlus3(head, data, firstId, secendId, linkmanId) {
	var headarray = head;

	var projects = data;

	var autoid = firstId;
	var autoid2 = secendId;
	var autoid3 = linkmanId;
	var highClass = "highclass";
	$("#" + autoid).autocomplete({
		tablehead : headarray,
		minLength : 0,
		source : projects,
		select : function(event, ui) {
			if (ui.item.id == -1) {
				$("#" + autoid).val(null);
				$("#" + autoid2).val(null);
				$("#" + autoid3).val(null);
				return false;
			}
			$("#" + autoid).val(ui.item.value);
			$("#" + autoid2).val(ui.item.label);
			$("#" + autoid3).val(ui.item.id);
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
		var inputChar = $("#" + autoid).val();
		if(item.label!= undefined){
			label = item.label.replace(item.label, replaceHigh(inputChar,
					item.label, highClass));
		}else{
			label = item.label;
		}
		if(item.value!= undefined){
			value = item.value.replace(item.value, replaceHigh(inputChar,
					item.value, highClass));
		}else{
			value = item.value;
		}
		if(item.desc!= undefined){
			desc = item.desc.replace(item.desc, replaceHigh(inputChar, item.desc,
					highClass));
		}else{
			desc = item.desc;
		}
		return $("<table class='autocomplete'>").append(
				"<tr class='" + row + "'><td>" + value + "</td><td>" + label
						+ "</td><td>" + desc + "</td></tr>").appendTo(ul);
	}
}
