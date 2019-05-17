/**XieXing */
/** 点击学院获取相应专业*/

function getFaculty(facSel,facId,majId){
	/**获取学院 */
	$.ajax({
		url:path+"/college/getAllFaculties",
		type:"post",
		dataType:"json",
		data:{
			
		},
		success:function(data){
			$("#"+facSel).empty();
			var str = "<option value=''>请选择</option>";
			for(var i=0;i<data.length;i++){
				if(facId == data[i].id){
					str += "<option value="+data[i].id+" selected class='tmpFac'>"+data[i].facName+"</option>";
				}else{
					str += "<option value="+data[i].id+" class='tmpFac'>"+data[i].facName+"</option>";
				}
			}
			$("#"+facSel).append(str);
			if(facId!=null && facId.length>0){
				getMajorsByFacultyId(facId,majId);
			}
			$("#"+facSel).on("change",function(){
				var facId = $(this).val();
				if(facId!=""){
					getMajorsByFacultyId(facId);
				}else{
					$("#major").empty();
				}
			})
		},
		error:function(){
			swal("","获取学院失败","error");
		}
	})
}
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
		elem : '#teaEntranceDate'
	});
})
//出生日期控件
layui.use('laydate', function() {
	var laydate = layui.laydate;
	laydate.render({
		elem : '#teaBirth'
	});
})

