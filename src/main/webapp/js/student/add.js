/**XieXing */
/** 点击学院获取相应专业*/
function getMajorsByFacultyId(facId,majId){
	$.ajax({
		url:path+"/major/getMajorsByFacultyId",
		type:"post",
		dataType:"json",
		data:{
			"facId":facId,
		},
		success:function(data){
			$("#major").empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(majId == data[i].id){
					str += "<option value="+data[i].id+" selected class='tmpFac'>"+data[i].majorName+"</option>";
				}else{
					str += "<option value="+data[i].id+" class='tmpFac'>"+data[i].majorName+"</option>";
				}
			}
			$("#major").append(str);
		},
		error:function(){
			swal("","获取专业失败","error");
		}
	})
}
//入学时间控件
layui.use('laydate', function() {
	var laydate = layui.laydate;
	laydate.render({
		elem : '#stuEntranceDate'
	});
})
//出生日期控件
layui.use('laydate', function() {
	var laydate = layui.laydate;
	laydate.render({
		elem : '#stuBirth'
	});
})

