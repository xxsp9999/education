/*
 * @create: 2019年2月27日 
 * @author: XieXing 
 * @description:获取当前时间 yyyy-MM-dd
 */
function getNowDate(){
	var tmpDate = new Date();
	var month = tmpDate.getMonth()+1;//月份加1
	return tmpDate.getFullYear()+"-"+month+"-"+tmpDate.getDate();
}

/*
 * @create: 2019年3月1日 
 * @author: XieXing 
 * @description:错误提示
 */
function errorTips(val,msg){
	if(val==null || val ==""){
		 swal("",msg,"error");
		 return false;
	}
}

/*
 * @create: 2019年2月27日 
 * @author: XieXing 
 * @description:获取用户数据
 */
function getUsers(){
	$.ajax({
		url:path+"/",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			return data;
		},
		error:function(){
			swal("","网络错误","error");
		}
	})
}