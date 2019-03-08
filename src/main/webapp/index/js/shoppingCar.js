$(function(){
	document.onselectstart = function (e) {
	    e.returnValue = false;
	};
	var inHtml="";
	var tbody=document.querySelector("tbody");
	var getgoods=document.querySelector("#getgoods");
	var section=document.querySelector("section");
	var number=document.querySelector("#goodsnum");
	//合计商品的数量和价格  以及加载div里面的图片
	var trs=tbody.rows;
	ids = [];
	nums = [];
	//console.log(trs);
	var div=document.querySelector(".one");
	total();
	function total(){
		ids = [];
	    var num= 0,sum= 0,html="";
	    var goodsnum=document.querySelector("#goodsnum");
	    for(var i=0;i<trs.length;i++){
	        if(trs[i].querySelector("input[type='checkbox']").checked){
	        	ids[i] = trs[i].querySelector("input[type='checkbox']").value;
	        	//ids[ids.length] = trs[i].querySelector("input[type='checkbox']").value
	            num+=parseInt(trs[i].querySelector("input[type='text']").value);
	            nums[i] = trs[i].querySelector("input[type='text']").value;
	            sum+=parseFloat(trs[i].cells[4].innerText);
	            console.log(num+"-----"+sum);
	            var imgsrc = trs[i].cells[1].querySelector("img").src;
	            html+="<figure><img src='"+imgsrc+"' alt=''><span index='"+i+"'>取消选择</span></figure>";
	            //console.log(html);
	        }
	    }
	    $("#paypage").click(function(){
	    	var commodityId = "";
	    	var commoditynumber = "";
	    	for(var index in ids){
	    		if(index < (ids.length-1)){
	    			commodityId += ids[index] + ","
	    		}else{
	    			commodityId += ids[index]
	    		}
	    	}
	    	for(var index in nums){
	    		if(index < (nums.length-1)){
	    			commoditynumber += nums[index] + ","
	    		}else{
	    			commoditynumber += nums[index]
	    		}
	    	}
	    	
	    	if(commodityId == ""){
	    		alert("请选择要购买的商品");
	    	}else{
	    		var commodityId = commodityId+"";
		    	var commoditynumber = commoditynumber+"";
		    	window.location.href=path+"/art/BuyArt?commodityId="+commodityId+"&commoditynumber="+commoditynumber;
	    	}
		});
	    
	    goodsnum.innerText=num;
	    document.querySelector("#totalprice").innerText=sum.toFixed(2);
	    div.innerHTML=html;
	    if(num==0){
	        section.className="";
	    }
	}

	/*
	 * date:2018年12月21日
	 * author:fanchaojun
	 * desc:总价实时响应
	 */
	/*$(".check").change(function(){
		debugger;
		total();
	})*/

	//一个总的事件代理，用来调用
	function agentEvent(parents,child,event,func){
	    parents['on'+event]=function(e){
	        e=e||window.event;
	        var etarget= e.target|| e.srcElement;
	        if(etarget.nodeName==child.toUpperCase()){
	            func.call(etarget);//回调函数
	        }
	    }
	}


	//弹出框的事件代理
	agentEvent(div,"span","click",function(){
	    var index=parseInt(this.getAttribute("index"));
	    var input =trs[index].cells[0].querySelector("input");
	    input.checked=false;
	    input.onclick();
	    total();
	});


	//处理加减商品 以及删除一行商品

	for( i=0;i<trs.length;i++){
	    agentEvent(trs[i],"span","click",function(){
	        var className=this.className;
	        var input;
	        var tr=this.parentNode.parentNode;

	        switch (className){
	            case "add":
	                input=this.previousSibling.previousSibling;
	                var repertory = $(this).siblings("label").text();
	                 repertory = parseInt(repertory);
	                if(input.value < repertory){
	                	input.value++;
		                tr.cells[4].innerText=(tr.cells[2].innerText*input.value).toFixed(2);
	                }
	                break;
	            case "reduce":
	                input=this.nextSibling.nextSibling;
	                if(input.value>1){
	                    input.value--;
	                    tr.cells[4].innerText=(tr.cells[2].innerText*input.value).toFixed(2);
	                }
	                break;
	            case "delete":
	                if(confirm("你确定要删除吗？")){
	                    tbody.deleteRow(tr.rowIndex-1);
	                }
	        }
	        total();
	    })
	}


	//处理文本框的输入内容

	for(i=0;i<trs.length;i++){
	    trs[i].querySelector("input[type='text']").onkeyup=function(){
	        if(!/^[1-9]\d*$/.test(this.value)){
	            this.value='1';
	        }
	        var tr=this.parentNode.parentNode;
	        tr.cells[4].innerText=(tr.cells[2].innerText*this.value).toFixed(2);
	        total();
	    };
	}


	//处理全部删除
//	var DelAll=document.querySelector("#DelAll");
//	DelAll.addEventListener("click",function(){
//	    if(confirm("你确定要删除全部吗?")){
//	        for(i=0;i<trs.length;i++){
//	            //alert(trs[i].querySelector("input[type='checkbox']").nodeName);
//	            if(trs[i].querySelector("input[type='checkbox']").checked){
//	                //alert(trs[0]);
//	                tbody.removeChild(trs[i]);
//	                i--;
//	            }
//	            total();
//	        }
//	    }else{
//	    	
//	    }
//	},false);


	//处理全选框
	var checks=document.querySelectorAll(".check");
	var checkAlls=document.querySelectorAll(".checkAll");
	var c = document.querySelectorAll(".c");
	//console.log(checks.length);
	//console.log(checkAlls.length);
	for(i=0;i<checks.length;i++){
	    checks[i].onclick=function() {
	        if (this.classList.contains("checkAll")) {
	            for (var j = 0; j < checks.length; j++) {
	                checks[j].checked = this.checked;
	            }
	        }
	        if (!this.checked){
	            checkAlls[0].checked = checkAlls[1].checked = false;
	        }
	      
	        total();
	    }
	}
	//选中所有商品时全选选中
	$(".c").change(function(){
		var len=$(".c:checked").length;
		if(len==$(".c").length){
			$(".checkAll").prop("checked",true);
		}else{
			$(".checkAll").prop("checked",false);
		}
	})
	//点击展开或收起已选商品

	console.log(number);
	getgoods.onclick=function(){
	    if(number.innerText!="0"){
	        section.classList.toggle("show");
	    }

	};
//购物车界面勾选取消商品价格变化    ChenCongLiang	2019-1-5
$(document).on("click","#check",function(){
	total();//计算价格方法
})
//购物车界面全选取消商品价格变化    ChenCongLiang	2019-1-5
$(".checkAll").on("click",function(){
	total();//计算价格方法
})
	/*//合计商品的数量和价格  以及加载div里面的图片
	var $trs = $("tbody tr");
	var $checks = $("tbody input.check");
	var $checkAll = $(".checkAll");
	var $goodsNum = $("#goodsnum");//下面的section显示已选商品
	
	$checkAll.click(function () {
		$("div input.check").prop("checked", this.checked);
	    totalAll();
	});
	
	
	$checks.click(function () {
	    check();
	});
	$("section input.fanCheck").click(function () {
	    $checks.each(function () {
	        this.checked = !this.checked;
	    });
	    check();
	});
	//计算单行价格
	function total(tr) {
	    var number = tr.find("td").eq(3);//找到数量
	    
	    var price = (parseInt(tr.find("td").eq(2).text()) * parseInt(number.find(":text").val())).toFixed(2);
	   
	    tr.find("td").eq(4).text(price);//把算出的价格给小计
	    totalAll();
	}
	//更新section上的总数和价格，动态加载图片
	function totalAll() {
	    var num = 0, sum = 0, html = "";
	    var checkbox;
	    $trs.each(function () {
	        //后面加一个0是把他转成dom对象
	        if ($(this).find(":checkbox")[0].checked) {
	            $(this).addClass('on');
	            num += parseInt($(this).find(":text").val());//计算已选商品的数量
	            sum += parseFloat($(this).find("td").eq(4).text());//计算总计价格
	            console.log(num+"----"+sum);
	            html += "<figure><img src='" + $(this).find('img').attr("src") + "' alt=''><span index='" + $(this).index() + "'>取消选择</span></figure>";
	        } else {
	            $(this).removeClass();
	        }
	    });
	    $goodsNum.html(num);
	    $("#totalprice").html(sum.toFixed(2));
	    $(".one").html(html);
	    if(num==0){
	        $("section").removeClass();
	    }
	    

	}

	//点击选中的函数
	function check() {
	    if ($checks.length != $("tbody input.check:checked").length) {
	        $checkAll.prop("checked", false);
	        totalAll();
	    } else {
	        $checkAll.prop("checked", true);
	        totalAll();
	    }
	}
	//为每一行的元素添加事件代理
	$trs.on("click", "span", function () {
	    if($(this).parent().parent().find(":checkbox").checked){
	    if ($(this).hasClass("add")) {
	        $(this).prev().val(parseInt($(this).prev().val()) + 1);
	        total($(this).parents("tr"));
	    } else if ($(this).hasClass("reduce")) {
	        if ($(this).next().val() > 1) {
	            $(this).next().val($(this).next().val() - 1);
	            total($(this).parents("tr"));
	        }
	    } else {
	        if (confirm("您确定要删除此商品吗？")) {
	            $(this).parents("tr").remove();
	            totalAll();
	        }
	    }
	    // }

	});
	//给数量框绑定keyup事件让用户可以手动输入数量
	$trs.find(":text").bind("keyup",function(){
	   var flag=/^\d+$/.test($(this).val());//输入的只能为整数数字
	    $(this).val(flag ? $(this).val() : 1);//输入的数字只能是1以上的
	    total($(this).parents("tr"));
	});
	//处理已选商品的图片那里显示隐藏
	$("#getgoods").click(function(){
	    if($goodsNum.text()!=0){
	        $("section").toggleClass("show");
	    }
	});
	//处理取消选择,事件代理
	$(".one").on("click","span",function(){
	    var index=$(this).attr("index");
	    $trs.eq(index).find(":checkbox")[0].checked=false;
	    totalAll();
	});
	//处理全部删除
	$("#DelAll").click(function (){
	    if($goodsNum.text()!=0){
	        if(confirm("您确定要全部删除吗?")){
	            $trs.find(":checked").parents("tr").remove();
	        }
	    }else{
	        alert("请选择要删除的商品！");
	        totalAll();
	    }
	});
	//默认最开始全部选中,模拟执行一次
	$checkAll.eq(0).click(function(){
	    $checks.attr("checked",true);
	}).click();

*/
	
})
