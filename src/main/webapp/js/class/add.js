/**表单提交**/
$("#submitBtn").click(function(){
	var facName = $("#claName").val().trim();
	var stuFile = $("#stuFile").val().trim();
	var remark = $("#claRemark").val();
	remark = remark.replace(/[\r\n]/g, "");
	remark = remark.replace(/\ +/g,"");
	$("#claRemark").val(remark);
	debugger;
	if(facName.length==0&&stuFile.length==0){
		swal("","班级不能为空！","error");
		return false;
	}
	$("#col_form").submit();
})

