/**表单提交**/
$("#submitBtn").click(function(){
	var facName = $("#gradeName").val().trim();
	var stuFile = $("#stuFile").val().trim();
	var remark = $("#gradeRemark").val();
	remark = remark.replace(/[\r\n]/g, "");
	remark = remark.replace(/\ +/g,"");
	$("#gradeRemark").val(remark);
	debugger;
	if(facName.length==0&&stuFile.length==0){
		swal("","年级不能为空！","error");
		return false;
	}
	$("#col_form").submit();
})

