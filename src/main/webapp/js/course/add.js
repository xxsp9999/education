/**表单提交**/
$("#submitBtn").click(function(){
	var facName = $("#cNo").val().trim();
	var stuFile = $("#stuFile").val().trim();
	var remark = $("#cRemark").val();
	remark = remark.replace(/[\r\n]/g, "");
	remark = remark.replace(/\ +/g,"");
	$("#cRemark").val(remark);
	if(facName.length==0&&stuFile.length==0){
		swal("","课程不能为空！","error");
		return false;
	}
	$("#cur_form").submit();
})

