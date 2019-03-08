/*
 * @fileName 仓储
 * @author DengYu
 * @createDate 2018/5/21 
 */

/*入库量柱状图    Start*/
(function () {
var in_Index=[100,210,260,410,580,600,680,820];//入库量
var average_index=[500,500,500,500,500,500,500,500]//均值
var in_rate=[70,80,75,85,90,80,85,75]
//柱状图
var option = {
	//柱状图名称
	title:{
		text:'入库',
		x:'2%',
		y:'2%'
	},
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:'80%',
		height:'65%'
	}
	],
//图表右上角的图例
    legend:[
    	{
		icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 85,
		data:['入库量','平均值'],
		
	},
    	{
    		icon:'rect',
			itemWidth:15,
			itemHeight:4,
			y:'3%',
			right: 20,
			data:['入库率'],
    		
    	}
    ],
//定义x轴
xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},            
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },
//第2条x轴      
       {
            type : 'category',//坐标轴类型
            show:false,
            axisLine: {show:false},
            axisTick: {show:false},
            axisLabel: {show:false},
            splitArea: {show:false},
            splitLine: {show:false},            
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },        

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'件',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},             
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
        },
        {	
            type:'value',//坐标轴类型
            name:'',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}%'},
        },        
    ],
//系列列表    
    series : [
    //入库量
        {
            name:'入库量',//图表名字
            type:'bar',		//图表类型
            barWidth:24,//柱状图柱宽
            itemStyle: {	//图形样式
              normal: {/*color: 'rgb(101, 135, 81,0.85)'*/color:'rgb(30,144,255,0.7)'},
            },
            data:in_Index //柱状图数据
        },
        {
            name:'平均值',//图表名字
            type:'bar',		//图表类型
            xAxisIndex:'1',
            barWidth:24,//柱状图柱宽
            itemStyle: {	//图形样式
              normal: {/*color: 'rgb(101, 135, 81,0.4)'*/color:'rgb(30,105,191,0.4)'},
            },
            data:average_index //柱状图数据
        },    
        {
        	name:'入库率',
        	type:'line',
        	smooth:true,
        	yAxisIndex:'1',
            symbol:'circle',
            symbolSize:'8',        	
        	lineStyle:{color:'#99a4ff',width:'3',type:'solid'},
            itemStyle:{
            	normal:{
            			borderColor:'#fff',borderWidth:2,
            			color:function (params) { //拐点颜色回调
							if (params.value >=85){
								return 'rgb(200,22,29,0.85)';
						    }else if(params.value <85&&params.value >=80){
						        return 'rgb(231,37,32,0.4)';
						    }else{
								return 'rgb(222,113,98,0.6)';
						    }
						}
          			 }
            	},
        	data:in_rate
        }
        

    ]
};	 
 	 myChart = echarts.init(document.getElementById('storage_in'));//获取id
   	 myChart.setOption(option);
  })();
/*入库量柱状图   end*/



/*出库量柱状图    Start*/
(function () {
var out_Index=[100,210,260,410,580,600,680,820];//出库量
var average_index=[500,500,500,500,500,500,500,500]//均值
var out_rate=[70,80,75,85,90,80,85,75]
//柱状图
var option = {
	//柱状图名称
	title:{
		text:'出库',
		x:"2%",
		y:'2%'
	},
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:"80%",
		height:"65%"
	}
	],
//图表右上角的图例
	legend:[
    	{
		icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 85,
		data:['出库量','平均值'],
		
	},
    	{
    		icon:'rect',
			itemWidth:15,
			itemHeight:4,
			y:'3%',
			right: 20,
			data:['出库率'],
    		
    	}
    ],
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },
//第一条x轴      
       {
            type : 'category',//坐标轴类型
            show:false,
            axisLine: {show:false},
            axisTick: {show:false},
            axisLabel: {show:false},
            splitArea: {show:false},
            splitLine: {show:false},            
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },        

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'件',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
        },
        {	
            type:'value',//坐标轴类型
            name:'',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}%'},
        },        
    ],
//系列列表    
    series : [
    //出库量
        {
            name:'出库量',//图表名字
            type:'bar',		//图表类型
            barWidth:24,//柱状图柱宽
            itemStyle: {	//图形样式
              normal: {color: 'rgb(30,144,255,0.7)'},
            },
            data:out_Index //柱状图数据
        },
        {
            name:'平均值',//图表名字
            type:'bar',		//图表类型
            xAxisIndex:'1',
            barWidth:24,//柱状图柱宽
            itemStyle: {	//图形样式
            	normal: {color: 'rgb(30,105,191,0.4)'},
            },
            data:average_index //柱状图数据
        },    
        {
        	name:'出库率',
        	type:'line',
        	smooth:true,
        	yAxisIndex:'1',
            symbol:'circle',
            symbolSize:'8',        	
        	lineStyle:{color:'#99a4ff',width:'3',type:'solid'},
            itemStyle:{
            	normal:{
            			borderColor:'#fff',borderWidth:2,
            			color:function (params) { //拐点颜色回调
            				if (params.value >=85){
								return 'rgb(200,22,29)';
						    }else if(params.value <85&&params.value >=80){
						        return 'rgb(231,37,32)';
						    }else{
								return 'rgb(222,113,98)';
						    }
						}
          			 }
            	},
        	data:out_rate
        }        
    ]
};
 	 myChart = echarts.init(document.getElementById('storage_out'));//获取id
   	 myChart.setOption(option);
  })();
/*出库量柱状图    end*/



/*库存使用    Start*/
(function () {
var in_Index=[100,210,260,410,580,600,680,820];//库存量
var usage_rate=[10,20,30,40,50,60,70,81];
//柱状图
var option = {
	title:{
		text:'库存使用',
		x:"2%",
		y:'2%'
	},
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:"80%",
		height:"65%"
	}
	],
//图表右上角的图例
	legend:[
    	{
		icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 85,
		data:['库存量'],
		
	},
    	{
    		icon:'rect',
			itemWidth:15,
			itemHeight:4,
			y:'3%',
			right: 20,
			data:['使用率'],
    		
    	}
    ],
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'件',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
            
        },
//第二条y轴        
        {	
            type:'value',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel: { //坐标轴刻度标签
            	formatter: '{value}%'}
        }
    ],
//系列列表    
    series : [
    //库存量
        {
            name:'库存量',//图表名字
            type:'bar',		//图表类型
            barWidth:24,//柱状图柱宽
            itemStyle: {	//图形样式
              normal: {color: 'rgb(30,144,255,0.7)'},
            },
            data:in_Index //柱状图数据
        },
        {
        	name:'使用率',
        	type:'line',
        	smooth:true,
        	symbolSize:0,
        	lineStyle:{color:'#99a4ff',width:3,type:'solid'},
        	itemStyle:{color:'#99a4ff'},
        	yAxisIndex:'1',
        	data:usage_rate
        }

    ]
};
 	 myChart = echarts.init(document.getElementById('usage_rate'));//获取id
   	 myChart.setOption(option);
  })();
/*库存使用    end*/


/*易耗品汇总 金额    Start*/
(function () {
var consumableIndex=[10,20,35,40,45,60,70,81];
var option = {
	title:{
		text:'易耗品汇总',
		x:'2%',
		y:'2%'
	},	
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:'85%',
		height:'65%'
	}
	],
//图表右上角的图例
    legend:{
    	icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 20,
		data:['易耗品金额'],
    },
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
            boundaryGap:false,
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'万元',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
            
        },
    ],
//系列列表    
    series : [
        {
        	name:'易耗品金额',
        	type:'line',
        	symbolSize:0,
        	lineStyle:{color:'#1E69BF',width:3,type:'solid'},
        	itemStyle:{color:'#1E69BF'},
        	areaStyle:{color:'rgb(30,144,255,0.7)'},
        	data:consumableIndex
        }
    ]
};
   	 myChart = echarts.init(document.getElementById('consumable_sum_amount'));//获取id
   	 myChart.setOption(option);
  })();
/*易耗品汇总   金额 end*/


/*易耗品汇总 数量    Start*/
(function () {
var width=$("#store_asset_amount").width();
var height=$("#store_asset_amount").height();
//	alert('w='+width+'h='+height)
//var width = document.getElementById("consumable_sum_amount").offsetWidth;
//var height =document.getElementById("consumable_sum_amount").offsetHeight;
$("#consumable_sum_quantity").css("width", width).css("height", height);
var myChart = echarts.init(document.getElementById('consumable_sum_quantity'));//获取id
var consumableIndex=[10,20,35,40,45,60,70,81];
var option = {
	title:{
		text:'易耗品汇总',
		x:"2%",
		y:'2%'
	},	
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:'85%',
		height:'65%'
	}
	],
//图表右上角的图例
    legend:{
    	icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 20,
		data:['易耗品金额'],
    },
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'万元',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
            
        },
    ],
//系列列表    
    series : [
        {
        	name:'易耗品金额',
        	type:'bar',
        	barWidth:24,
        	symbolSize:0,
        	lineStyle:{color:'#1E69BF',width:3,type:'solid'},
        	itemStyle:{color:'rgb(30,144,255,0.7)'},
        	data:consumableIndex
        }
    ]
};

   	 myChart.setOption(option);
  })();
/*易耗品汇总   数量 end*/




/*库存资产汇总  金额   Start*/
(function () {
var storeIndex=[50,45,35,40,45,60,70,81];
var option = {
	title:{
		text:'库存资产',
		x:'2%',
		y:'2%'
	},
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:'85%',
		height:'65%'
	}
	],
//图表右上角的图例
    legend:{
    	icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 20,
		data:['库存资产'],
    },
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
            boundaryGap:false,
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'万元',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
            
        },
    ],
//系列列表    
    series : [
        {
        	name:'库存资产',
        	type:'line',
        	symbolSize:0,
        	lineStyle:{color:'#1E69BF',width:3,type:'solid'},
        	itemStyle:{color:'#1E69BF'},
        	areaStyle:{color:'rgb(30,144,255,0.7)'},
        	data:storeIndex
        }
    ]
};
   	 myChart = echarts.init(document.getElementById('store_asset_amount'));//获取id
   	 myChart.setOption(option);
  })();
/*库存资产汇总 金额    end*/


/*库存资产汇总  数量   Start*/
(function () {
var width = document.getElementById("store_asset_amount").offsetWidth;
var height =document.getElementById("store_asset_amount").offsetHeight;	
$("#store_asset_quantity").css("width", width).css("height", height);
var myChart = echarts.init(document.getElementById('store_asset_quantity'));//获取id
var storeIndex=[50,45,35,40,45,60,70,81];
var option = {
	title:{
		text:'库存资产',
		x:'2%',
		y:'2%'
	},
//网格 图表位置
	grid:[
	{	
		bottom:30,
		width:"85%",
		height:'65%'
	}
	],
//图表右上角的图例
    legend:{
    	icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 20,
		data:['库存资产'],
    },
//定义x轴
    xAxis : [
//第一条x轴      
       {
            type : 'category',//坐标轴类型
			axisLine: {show:false},
			axisTick:{show:false},               
          	axisLabel:{		  //坐标轴刻度标签
          		interval:0,
          		fontSize:10,
          		rotate:25,
          		formatter:'5月{value}日'
          	},
            data : ['1','2','3','4','5','6','7','8']//坐标轴数据
        },

    ],
//定义y轴
    yAxis : [
//第一条y轴    
        {	
            type:'value',//坐标轴类型
            name:'件',	 //坐标轴名称
			axisLine: {show:false},
			axisTick:{show:false},               
            axisLabel:{	 //坐标轴刻度标签
            	formatter:'{value}'},
            
        },
    ],
//系列列表    
    series : [
        {
        	name:'库存资产',
        	type:'bar',
        	barWidth:24,
        	symbolSize:0,
        	lineStyle:{color:'#1E69BF',width:3,type:'solid'},
        	itemStyle:{color:'rgb(30,144,255,0.7)'},
        	data:storeIndex
        }
    ]
};
   	 myChart.setOption(option);
  })();
/*库存资产汇总 数量   end*/


/*资产分类占比 饼图 start*/
(function () {
var option = {
    title : {
        text: '资产分类占比',
        x:"2%",
        y:'2%'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend:{
    	icon:'rect',
		itemWidth:9,
		itemHeight:9,
		y:'3%',
		right: 20,
		data:['分类1','分类2','分类3','分类4',],
    },
	color:['#1E69BF', '#1E90FF','#A5C3E5','#658751'],
    series : [
        {
            name: '资产分类',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            label:{
            	normal:{
            		formatter:'{b} {d}%'
            	}
            },
            data:[
                {value:4488, name:'分类1'},
                {value:3107, name:'分类2'},
                {value:2167, name:'分类3'},
                {value:238, name:'分类4'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
    myChart = echarts.init(document.getElementById('asset_category'));
    myChart.setOption(option);
    })();
/*资产分类占比 End*/

/*区域分布*/
(function(){
	var map = echarts.init(document.getElementById("map"));
			var option = {
				tooltip:{
					trigger:'item',
					formatter:function(params){
						switch( params.name){
							case '安徽':
							case '浙江':
							case '广东':
							case '江苏':
							case '湖北':
							return 	[params.name,params.value];
						}
					}
				},
				dataRange:{
					x: 10000,
					y: 100000,
					itemWidth:12,
					itemHeight:12,
					splitList:[
						{start:500,label:'建设中'},
						{start:200,end:500,label:'拓展中'}
					],
					color:['#2bd9a9','#99a4ff']//['#658751','#A5C3E5']
				},
				series:[
					{
						type:'map',
						map:'china',
						label:{
							show:true,
							color: '#1b1b1b'
						},
						itemStyle:{
							normal:{areaColor: '#f0f0f0',
								borderColor:'#cfcfcf',label:{show:true}},
						},
						emphasis:{
							itemStyle:{
								normal:{label:{show:true}},
								areaColor:'#dde0e1',//'#e2e2e2',
								borderWidth:1,
								borderColor:'#cfcfcf'//'blue'
							},
						},
						data:[
						    {name:"南海诸岛",value:0,label:{show:false}, itemStyle:{normal:{opacity:0,}}},  
							{name:'江苏',value:600},
							{name:'浙江',value:600},
							{name:'广东',value:600},
							{name:'湖北',value:200},
							{name:'安徽',value:200},
						]
					}
				]
			};
			map.setOption(option);
			map.on('click',function(params){
				switch( params.name){
					case '安徽':
					case '浙江':
					case '广东':
					case '江苏':
					case '湖北':
				//window.top.location.href='common/FCJ/map/L1/L1.html'
				}
			});
			
})();