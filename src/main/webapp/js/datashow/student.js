//学生数据展示
$(document).ready(function() {
	var chart = document.getElementById('studentChart');
	var chartData = echarts.init(chart);

	chartData.setOption({
		title : {
			text : '学生男女比例',
			//subtext: '纯属虚构',
			x : 'center',// 以x轴作为参考，调整位置
			textStyle : {
				fontWeight : 'normal', // 标题颜色
				color : 'black'
			},
			
		},
		// 当鼠标移动到图形上时，显示数据
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		toolbox : {
			show : true,
			feature : {
				show : true,
				dataView : {// 数据展示
					show : true
				},
				saveAsImage : {// 保存为图片
					show : true
				},
				restore : {// 还原，类似于重新加载
					show : true
				},
			}

		},
		legend : {
			orient : 'vertical',// 图列摆放的类型（横或竖）
			x : 'left',// 位置调整
			data : [],
			textStyle : {
				fontWeight : 'normal', // 标题颜色
				color : 'black'
			},
		},
		xAxis : {// x轴
			show : false,
			data : []
		},
		yAxis : {// y轴
			show : false
		},
		series : [ {// 展示类型
			type : 'pie',
			data : []
		} ]
	});
	loadData(chartData);// 加载数据
	$("#go").click(function() {
		loadData(chartData);// 加载数据
	})
});
// 加载数据
function loadData(chartData) {
	var facultyId = $("#facultySel").val();
	var majorId = $("#majorSel").val();
	var stuClassId = $("#stuClassSel").val();
	debugger;
	chartData.showLoading();// 数据开始加载（加载显示）
	$.post(path + "/student/getStudentAnalyseData", {
		"facId" : facultyId,
		"majId" : majorId,
		"stuClassId" : stuClassId,
	}, function(datas) {
		debugger;
		data = datas.data;
		chartData.setOption({
			// 在已有图形的基础上追加数据
			legend : {
				data : data.name,
			},
			series : [ {
				name : '性别',
				data : data
			} ]
		});
	})
	chartData.hideLoading();// 数据加载完成，隐藏数据加载图标
}
// 获取学院专业班级
getFuculties("facultySel", "", "", "majorSel", "stuClassSel", "");