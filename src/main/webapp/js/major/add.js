/**表单提交**/
$("#submitBtn").click(function(){
	var majorName = $("#majorName").val().trim();
	var majFile = $("#majFile").val().trim();
	var remark = $("#marjorRemark").val();
	remark = remark.replace(/[\r\n]/g, "");
	remark = remark.replace(/\ +/g,"");
	$("#marjorRemark").val(remark);
	debugger;
	if(majorName.length==0&&majFile.length==0){
		swal("","院系不能为空！","error");
		return false;
	}
	$("#maj_form").submit();
})

