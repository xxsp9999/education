/*
 * @create: 2019年2月27日 
 * @author: XieXing 
 * @description:获取当前时间 yyyy-MM-dd
 */
function getNowDate() {
	var tmpDate = new Date();
	var month = tmpDate.getMonth() + 1;// 月份加1
	return tmpDate.getFullYear() + "-" + month + "-" + tmpDate.getDate();
}

/*
 * @create: 2019年3月1日 @author: XieXing @description:错误提示
 */
function errorTips(val, msg) {
	if (val == null || val == "") {
		swal("", msg, "error");
		return false;
	}
}

/*
 * @create: 2019年2月27日 @author: XieXing @description:获取用户数据
 */
function getUsers() {
	$.ajax({
		url : path + "/",
		type : "post",
		dataType : "json",
		data : {

		},
		success : function(data) {
			return data;
		},
		error : function() {
			swal("", "网络错误", "error");
		}
	})
}

/*
 * @create: 2019年2月27日 @author: XieXing @description:获取民族
 */
function getNationals(tmpNation, selId) {
	$("#" + selId).empty();
	var national = [ "汉族", "壮族", "满族", "回族", "苗族", "维吾尔族", "土家族", "彝族", "蒙古族",
			"藏族", "布依族", "侗族", "瑶族", "朝鲜族", "白族", "哈尼族", "哈萨克族", "黎族", "傣族",
			"畲族", "傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族", "羌族",
			"土族", "仫佬族", "锡伯族", "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "布朗族",
			"塔吉克族", "阿昌族", "普米族", "鄂温克族", "怒族", "京族", "基诺族", "德昂族", "保安族",
			"俄罗斯族", "裕固族", "乌孜别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族" ];
	var str = "<option value=''>请选择</option>";
	for (i = 0; i < national.length; i++) {
		if (tmpNation == national[i]) {
			str += "<option value=" + national[i] + " selected>" + national[i]
					+ "</option>";
		} else {
			str += "<option value=" + national[i] + ">" + national[i]
					+ "</option>";
		}
	}
	$("#" + selId).append(str);
}
/**文本框非空验证 */
function validateFeeling(input) {
	if (input.value.length==0) {
		input.setCustomValidity('此项为必填');
		return false;
	}
}

/*
 * @create: 2019年3月14日 
 * @author: XieXing 
 * @description:空字符串判断
 */
function singleNotNull(str) {
	str = str.trim();
	if (str.length > 0) {
		return true;
	}else{
		return false;
	}
}

/*
 * @create: 2019年3月29日 
 * @author: XieXing 
 * @description:字符串截取
 */
function subStr(str){
	if(str!=null && str.length>8){
		str = str.substring(0,8);
		debugger;
		return str+"...";
	}else{
		return str;
	}
}

/*
 * @create: 2019年1月12日 @author: XieXing @description:字符串时间格式化
 */
function crtTimeFtt(val) {
	if (val != null) {
		var date = new Date(val);
		return date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
				+ date.getDate();
	}
}

var tmpFacId = "";
var tmpMajId = "";
/*
 * @create: 2019年5月6日 @author: XieXing @description:获取学院
 */
function getFuculties(facultySec,facId,majId,majorSec,stuClassSec,eduClassId){
	$.ajax({
		url:path+"/college/getAllFaculties",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#"+facultySec).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(facId == data[i].id){
					tmpFacId = facId;
					str += "<option value="+data[i].id+" selected class='tmpFac'>"+data[i].facName+"</option>";
				}else{
					str += "<option value="+data[i].id+" class='tmpFac'>"+data[i].facName+"</option>";
				}
			}
			$("#"+facultySec).append(str);
			getMajorsByFacultyId(facId,majId,majorSec,stuClassSec,eduClassId);
			$("#"+facultySec).on("change",function(){
				var facId = $(this).val();
				tmpFacId = facId;
				getMajorsByFacultyId(facId,majId,majorSec,stuClassSec,eduClassId);
			})
		},
		error:function(){
			swal("","获取学院失败","error");
		}
	})
}
/**获取专业*/
function getMajorsByFacultyId(facId,majId,majorSec,stuClassSec,eduClassId){
	$.ajax({
		url:path+"/major/getMajorsByFacultyId",
		type:"post",
		dataType:"json",
		data:{
			"facId":facId,
		},
		success:function(data){
			$("#"+majorSec).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(majId == data[i].id){
					tmpMajId = majId;
					str += "<option value="+data[i].id+" selected class='tmpFac'>"+data[i].majorName+"</option>";
				}else{
					str += "<option value="+data[i].id+" class='tmpFac'>"+data[i].majorName+"</option>";
				}
			}
			$("#"+majorSec).append(str);
			getCla(stuClassSec,eduClassId,facId,majId);
			
			$("#"+majorSec).on("change",function(){
				var majId = $(this).val();
				tmpMajId = majId;
				getCla(stuClassSec,eduClassId,facId,majId);
			})
		},
		error:function(){
			swal("","获取专业失败","error");
		}
	})
}

/**获取年级*/
function getGrades(gradeSec,gradeId,stuClassSec,eduClassId){
	$.ajax({
		url:path+"/grade/getAllGrades",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			debugger;
			$("#"+gradeSec).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(gradeId == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].gradeName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].gradeName+"</option>";
				}
			}
			$("#"+gradeSec).append(str);
			getCla(stuClassSec,eduClassId,tmpFacId,tmpMajId,gradeId);
			$("#"+gradeSec).on("change",function(){
				var gradeId = $(this).val();
				getCla(stuClassSec,eduClassId,tmpFacId,tmpMajId,gradeId);
			})
		},
		error:function(){
			swal("","获取年级失败","error");
		}
	})
}

/**获取班级*/
function getCla(stuClassSec,eduClassId,facId,majId,gradeId){
	debugger;
	$.ajax({
		url:path+"/educlass/getAllClasses",
		type:"post",
		dataType:"json",
		data:{
			"facId":facId,
			"majId":majId,
			"gradeId":gradeId,
		},
		success:function(data){
			$("#"+stuClassSec).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(eduClassId == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].claName+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].claName+"</option>";
				}
			}
			$("#"+stuClassSec).append(str);
		},
		error:function(){
			swal("","获取班级失败","error");
		}
	})
}


/*获取省份*/
function getProvinces(provinceSel,provinceId,citySel,cityId,countySel,countyId){
	$.post(path + "/address/getProvinces", {}, function(data) {
		$("#"+provinceSel).empty();
		var str = "<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			if(provinceId == data[i].id){
				str += "<option value="+data[i].id+" selected>"+data[i].provinceName+"</option>";
			}else{
				str += "<option value="+data[i].id+">"+data[i].provinceName+"</option>";
			}
		}
		$("#"+provinceSel).append(str);
		if (provinceId != null && provinceId.length > 0) {
			getCities(provinceId,citySel,cityId,countySel,countyId);
		}
		$("#"+provinceSel).on("change", function() {
			provinceId = $(this).val();
			if (provinceId != "") {
				getCities(provinceId,citySel,cityId,countySel,countyId);
			} else {
				$("#"+citySel).empty();
			}
		})
	})
}
/*获取市级地区*/
function getCities(provinceId,citySel,cityId,countySel,countyId){
	$.post(path + "/address/getCities", {
		"pid":provinceId,
	}, function(data) {
		$("#"+citySel).empty();
		var str = "<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			if(cityId == data[i].id){
				str += "<option value="+data[i].id+" selected>"+data[i].cityName+"</option>";
			}else{
				str += "<option value="+data[i].id+">"+data[i].cityName+"</option>";
			}
		}
		$("#"+citySel).append(str);
		if (cityId != null && cityId.length > 0) {
			getCounties(cityId,countySel,countyId);
		}
		$("#"+citySel).on("change", function() {
			cityId = $(this).val();
			if (cityId != "") {
				getCounties(cityId,countySel,countyId);
			} else {
				$("#"+countySel).empty();
			}
		})
	})
}
/*获取县（区）地区*/
function getCounties(cityId,countySel,countyId){
	$.post(path + "/address/getCounties", {
		"cid":cityId,
	}, function(data) {
		$("#"+countySel).empty();
		var str = "<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			if(countyId == data[i].id){
				str += "<option value="+data[i].id+" selected>"+data[i].countyName+"</option>";
			}else{
				str += "<option value="+data[i].id+">"+data[i].countyName+"</option>";
			}
		}
		$("#"+countySel).append(str);
	})
}
/**
 * @description 获取公司
 * @param companySel
 * @param companyId
 * @returns
 */
function getAllCompanies(companySel,companyId){
	$.post(path + "/company/getAllCompanies", {}, function(data) {
		$("#"+companySel).empty();
		var str = "<option value=''>请选择</option>";
		for(var i=0;i<data.length;i++){
			if(companyId == data[i].id){
				str += "<option value="+data[i].id+" selected>"+data[i].companyName+"</option>";
				$("#"+companySel).attr("disabled","disabled");
				break;
			}else{
				str += "<option value="+data[i].id+">"+data[i].companyName+"</option>";
			}
		}
		$("#"+companySel).append(str);
	})
}
//获取老师
function getTeachers(teacherSel,tmpId,facId,majId,content){
	$.ajax({
		url:path+"/teacher/getAllTeachers",
		type:"post",
		dataType:"json",
		data:{
			facId:facId,
			majId:majId,
			content:content,
		},
		success:function(data){
			$("#"+teacherSel).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(tmpId == data[i].id){
					str += "<option value="+data[i].id+" selected>"+data[i].teaName+" "+data[i].teaNumber+"</option>";
				}else{
					str += "<option value="+data[i].id+">"+data[i].teaName+" "+data[i].teaNumber+"</option>";
				}
			}
			$("#"+teacherSel).append(str);
			
			$("#"+teacherSel).on("change", function() {
				var teacherName = $("#"+teacherSel).find("option:selected").text().trim().split(" ")[0];
				$("#teacherName").val(teacherName);
			})
		},
		error:function(){
			swal("","获取老师失败","error");
		}
	})
}

