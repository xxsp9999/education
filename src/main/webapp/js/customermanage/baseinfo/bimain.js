/*
 * @create: 2019年2月27日 
 * @author: XieXing 
 * @description:Ajax请求方法
 */
function ajaxFun(){
	$.ajax({
		url:"",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			
		},
		error:function(){
			swal("","网络错误","error");
		}
	})
}
