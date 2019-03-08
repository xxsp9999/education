// JavaScript Document
$(document).ready(function(){

			 var headerheight= $("#layout").width()*0.055;
			 $(".header").height(headerheight);
			  var dyheight= $("#layout").width()*0.269;
			 $(".dynamic-right").height(dyheight);
			  var dynamicheight= $("#layout").width()*0.065;
			 $(".dynamic-hei li").height(dynamicheight);
			 var newsheight= $(".news-div").width()*0.33;
			 var footerheight= $("#layout").width()*0.26;
			 $(".footer").height(footerheight);
			 $(".news-div").height(newsheight);
			 var f14_px_413 = $("#layout").width()*0.032;
			$(".word-news1,.word-news2").css('font-size',f14_px_413+'px');
			 var f15_px_413 = $("#layout").width()*0.038;
			$(".wenzi").css('font-size',f15_px_413+'px');
			 var f25_px_800 = $("#layout").width()*0.031;
			$(".pic-word-title-m").css('font-size', f25_px_800+'px');
			 var f18_px_800 = $("#layout").width()*0.0225;
			$(".pic-word-m").css('font-size', f18_px_800+'px');
			 var f13_px = $("#layout").width()*0.0098;
			$(".nav li,.box-word,.teacher-content").css('font-size',f13_px+'px');
			 var f15_px = $("#layout").width()*0.011;
			$(".box-word,.gongye,.shuju,.teacher-in,.dynamic-content,.pic-word-title,.anniu li").css('font-size',f15_px+'px');
			 var f16_px = $("#layout").width()*0.0123;
			$(".teacher-detail,.teacher-name,.dynamic-title,.month,.footer,.footbar,.rongyu-content").css('font-size',f16_px+'px');
			 var f18_px = $("#layout").width()*0.0138;
			$(".learnmoreabout").css('font-size',f18_px+'px');
			 var f12_px = $("#layout").width()*0.0093;
			$(".box-word li,.learnmore,.pic-word").css('font-size',f12_px+'px');
			 var f12_px = $("#layout").width()*0.0093;
			  var f28_px = $("#layout").width()*0.0215;
			$(".teacher-title,.dynamic").css('font-size',f28_px+'px');
			  var f40_px = $("#layout").width()*0.03;
			$(".day").css('font-size',f40_px+'px');
			$(".box-small").css('font-size',f12_px+'px');

			 $(".anniu li").mouseenter(function(){
				 $(this).children(".qq-icon-1").css("visibility","hidden");
				  $(this).children(".qq-icon-2").css("visibility","visible");
										   });
			 $(".anniu li").mouseleave(function(){
				 $(this).children(".qq-icon-1").css("visibility","visible");
				  $(this).children(".qq-icon-2").css("visibility","hidden");
										   });
			 $(".adv-left").mouseenter(function(){
				 $(".graybtn").css("opacity","0");
				  $(".pbtn").css("opacity","1");
										   });
			 $(".adv-left").mouseleave(function(){
				  $(".graybtn").css("opacity","1");
				  $(".pbtn").css("opacity","0");
										   });
			  $(".adv-right").mouseenter(function(){
				 $(".graybtn2").css("opacity","0");
				  $(".pbtn2").css("opacity","1");
										   });
			 $(".adv-right").mouseleave(function(){
				  $(".graybtn2").css("opacity","1");
				  $(".pbtn2").css("opacity","0");

										   });



			 $(".teacher-left").mouseenter(function(){
				 $(".graybtn3").css("opacity","0");
				  $(".pbtn3").css("opacity","1");
										   });
			 $(".teacher-left").mouseleave(function(){
				  $(".graybtn3").css("opacity","1");
				  $(".pbtn3").css("opacity","0");
										   });
			  $(".teacher-right").mouseenter(function(){
				 $(".graybtn4").css("opacity","0");
				  $(".pbtn4").css("opacity","1");
										   });
			 $(".teacher-right").mouseleave(function(){
				  $(".graybtn4").css("opacity","1");
				  $(".pbtn4").css("opacity","0");

										   });



			 $(".left-anniu").mouseenter(function(){
				 $(".left-anniu-pic1").css("opacity","0");
				  $(".left-anniu-pic2").css("opacity","1");
										   });
			 $(".left-anniu").mouseleave(function(){
				  $(".left-anniu-pic1").css("opacity","1");
				  $(".left-anniu-pic2").css("opacity","0");
										   });
			  $(".right-anniu").mouseenter(function(){
				 $(".right-anniu-pic1").css("opacity","0");
				  $(".right-anniu-pic2").css("opacity","1");
										   });
			 $(".right-anniu").mouseleave(function(){
				  $(".right-anniu-pic1").css("opacity","1");
				  $(".right-anniu-pic2").css("opacity","0");

										   });


			 $(".nav-close").click(function(){
				$(".mobnav").fadeOut(100);
											});

			 $(".open").click(function(){
				$(".mobnav").fadeIn(100);
											});

				$(".intro1,.intro2,.intro3,.intro4").css("opacity","1");

						   });


$(window).resize(function(){
			 var dyheight= $("#layout").width()*0.269;
			 $(".dynamic-right").height(dyheight);
			 var f14_px_413 = $("#layout").width()*0.032;
			$(".word-news1,.word-news2").css('font-size',f14_px_413+'px');
			 var f15_px_413 = $("#layout").width()*0.038;
			$(".wenzi").css('font-size',f15_px_413+'px');
			 var f18_px_800 = $("#layout").width()*0.0225;
			$(".pic-word-m").css('font-size', f18_px_800+'px');
			 var f25_px_800 = $("#layout").width()*0.031;
			$(".pic-word-title-m").css('font-size', f25_px_800+'px');
			var headerheight= $("#layout").width()*0.055;
			 $(".header").height(headerheight);
			  var newsheight= $(".news-div").width()*0.33;
			 $(".news-div").height(newsheight);
			   var dynamicheight= $("#layout").width()*0.065;
			 $(".dynamic-hei li").height(dynamicheight);
			 var footerheight= $("#layout").width()*0.26;
			 $(".footer").height(footerheight);
			  var f13_px = $("#layout").width()*0.0098;
			$(".nav li,.box-word,.teacher-content").css('font-size',f13_px+'px');
			 var f15_px = $("#layout").width()*0.011;
			$(".box-word,.gongye,.shuju,.teacher-in,.dynamic-content,.pic-word-title,.anniu li").css('font-size',f15_px+'px');
			 var f16_px = $("#layout").width()*0.0123;
			$(".teacher-detail,.teacher-name,.month,.footer,.footbar,.dynamic-title,.rongyu-content").css('font-size',f16_px+'px');
			 var f18_px = $("#layout").width()*0.0138;
			$(".learnmoreabout").css('font-size',f18_px+'px');
			 var f12_px = $("#layout").width()*0.0093;
			$(".box-word li,.learnmore").css('font-size',f12_px+'px');
			 var f12_px = $("#layout").width()*0.0093;
			$(".box-small,.pic-word").css('font-size',f12_px+'px');
			 var f28_px = $("#layout").width()*0.0215;
			$(".teacher-title,.dynamic").css('font-size',f28_px+'px');
			  var f40_px = $("#layout").width()*0.03;
			$(".day").css('font-size',f40_px+'px');
						   })


var scrolltop;
$(window).scroll(
	function(){
		scrolltop=$(this).scrollTop();
		console.log($(this).scrollTop());
		if(scrolltop>($('.intro3').offset().top+100)){
			$('.teacher-title').css('transform','translateX(0%)');
			$('.teacher-title').css('-webkit-transform','translateX(0%)');
			$('.teacher-title').css('opacity','1');
			$('.teacher-detail').css('transform','translateX(0%)');
			$('.teacher-detail').css('-webkit-transform','translateX(0%)');
			$('.teacher-detail').css('opacity','1');

		}

 }
);



jQuery.fn.shen = function(){
		var con = $(this).html();
		$(this).attr('title',con);
		var html;
		if(con.length>100){
			html = con.substring(0,250);
		}
		$(this).html(html+"...");
	}
	$('.profile-detail p').shen();

