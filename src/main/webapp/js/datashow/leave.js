$(document).ready(
		function() {
			/* 请假折线图 */
			var chart = document.getElementById('leave');
			var chartData = echarts.init(chart);
			chartData.setOption({
				title : {
					x : 'center',// 以x轴作为参考，调整位置
					text : '请假统计',
				},
				tooltip : {
					trigger : 'axis'
				},
				legend : {
					orient : 'vertical',// 图列摆放的类型（横或竖）
					x : 'left',// 位置调整
					data : [ "没有请假" ],
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
					name : '请假次数',
					type : 'bar',
					data : [],
				}, ]
			});
			/* 请假饼图 */
			var chart2 = document.getElementById('leave2');
			var chartData2 = echarts.init(chart2);
			chartData2.setOption({
				title : {
					text : '请假统计',
					// subtext: '纯属虚构',
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
					data : [ "没有请假" ],
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
			getStudentAnalyseData(chartData, chartData2);// 加载数据
			$("#go").click(
					function() {
						var beginTime = $("#start").val();
						var endTime = $("#end").val();
						var facId = $("#facultySel").val();
						var majId = $("#majorSel").val();
						var gradeId = $("#gradeSel").val();
						var eduClassId = $("#stuClassSel").val();
						var leaveInfoType = $("#leaveInfoType").val();
						var content = $("#content").val();
						getStudentAnalyseData(chartData, chartData2, beginTime,
								endTime, facId, majId, gradeId, eduClassId,
								leaveInfoType, content);// 加载数据
					})
		});
// 加载数据
function getStudentAnalyseData(chartData, chartData2, beginTime, endTime,
		facId, majId, gradeId, eduClassId, leaveInfoType, content) {
	chartData.showLoading();// 数据开始加载（加载显示）
	chartData2.showLoading();// 数据开始加载（加载显示）
	$.post(path + "/datashow/getStudentLeaveData", {
		"start" : beginTime,
		"end" : endTime,
		"facId" : facId,
		"majId" : majId,
		"gradeId" : gradeId,
		"eduClassId" : eduClassId,
		"leaveInfoType" : leaveInfoType,
		"content" : content,
	}, function(data) {
		debugger;
		// 柱状图
		/*if(data.xData.length==0){
			data.xData = 0;
		}*/
		chartData.setOption({
			xAxis : [ {
				data : data.xData,
			} ],
			series : [ {
				data : data.yData,
			}, ]
		});

		/*if(data.name.length==0){
			data.name="没有请假";
		}*/
		// 饼图
		chartData2.setOption({
			// 在已有图形的基础上追加数据
			legend : {
				data : data.name,
			},
			series : [ {
				name : '请假类型',
				data : data.data
			} ]
		});
	})
	chartData.hideLoading();// 数据加载完成，隐藏数据加载图标
	chartData2.hideLoading();// 数据加载完成，隐藏数据加载图标
}
