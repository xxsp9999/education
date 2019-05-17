//各角色数据展示
$(document).ready(function() {
	var chart = document.getElementById('allmember');
	var chartData = echarts.init(chart);
	chartData.setOption({
		title : {
			x : 'center',// 以x轴作为参考，调整位置
			text : '各角色数据统计',
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			orient : 'vertical',// 图列摆放的类型（横或竖）
			x : 'left',// 位置调整
		},
		toolbox : {
			show : true,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				magicType : {
					show : true,
					type : [ 'line', 'bar' ]
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}
		},
		calculable : true,
		xAxis : [ {
			type : 'category',
			data : []
		} ],
		yAxis : [ {
			type : 'value'
		} ],
		series : [ {
			name : '人数',
			type : 'bar',
			data : [],
		}, ]
	});
	loadAllMemberData(chartData);// 加载数据
});
// 加载数据
function loadAllMemberData(chartData) {
	debugger;
	chartData.showLoading();// 数据开始加载（加载显示）
	$.post(path + "/datashow/getAllMemberAnalyseData", {}, function(datas) {
		debugger;
		chartData.setOption({
			xAxis : [ {
				data : datas.xData,
			} ],
			series : [ {
				data : datas.yData,
			}, ]
		});
	})
	chartData.hideLoading();// 数据加载完成，隐藏数据加载图标
}