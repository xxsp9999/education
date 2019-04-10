/**表单提交**/
$("#submitBtn").click(function(){
	var facName = $("#facName").val().trim();
	var stuFile = $("#stuFile").val().trim();
	var remark = $("#facRemark").val();
	remark = remark.replace(/[\r\n]/g, "");
	remark = remark.replace(/\ +/g,"");
	$("#facRemark").val(remark);
	debugger;
	if(facName.length==0&&stuFile.length==0){
		swal("","院系不能为空！","error");
		return false;
	}
	$("#col_form").submit();
})

