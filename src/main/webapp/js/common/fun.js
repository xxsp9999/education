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

/*
 * @create: 2019年2月27日 
 * @author: XieXing 
 * @description:获取民族
 */
function getNationals(){
	var national = [
        "汉族", "壮族", "满族", "回族", "苗族", "维吾尔族", "土家族", "彝族", "蒙古族", "藏族", "布依族", "侗族", "瑶族", "朝鲜族", "白族", "哈尼族",
        "哈萨克族", "黎族", "傣族", "畲族", "傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族", "羌族", "土族", "仫佬族", "锡伯族",
        "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "布朗族", "塔吉克族", "阿昌族", "普米族", "鄂温克族", "怒族", "京族", "基诺族", "德昂族", "保安族",
        "俄罗斯族", "裕固族", "乌孜别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族"
        ];
	window.onload = function ()
	{
	    var nat = document.getElementById ("national");
	    for (i = 0; i < national.length; i++)
	    {
	        var option = document.createElement ('option');
	        option.value = i;
	        var txt = document.createTextNode (national[i]);
	        option.appendChild (txt);
	        nat.appendChild (option);
	    }
	}
}