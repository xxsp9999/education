$("#saveButton").on("click",function post(){
	$("#saveButton").prop("disabled",true);
	$("#saveButton").removeClass("button-blue");
	$("#saveButton").addClass("button-gray");
	if(checkName()&&checkDepart()&&checkTel()&&checkNumber()){
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/user/update",
			data : $("#formArea").serialize(),
			dataType : "json",
			success : function(msg){
				alert("修改成功");
				window.location.href = document.referrer;
			},
			error : function(){
				swal("","error","error");
				$("#saveButton").prop("disabled",false);
				$("#saveButton").removeClass("button-gray");
				$("#saveButton").addClass("button-blue");
			}
		}); 
	}else{
		$("#saveButton").prop("disabled",false);
		$("#saveButton").removeClass("button-gray");
		$("#saveButton").addClass("button-blue");
		return false;
	}
});