// JavaScript Document
$(document).ready(function(){
	//登录的验证
	username.onblur = function(){
		if(this.validity.valueMissing){
			this.setCustomValidity("用户名不能为空！");			
		}else{
			this.setCustomValidity("");
		}
	},
	password.onblur = function(){
		if(this.validity.valueMissing){
			this.setCustomValidity("密码不能为空！");			
		}else{
			if(this.validity.tooShort){
				this.setCustomValidity("密码长度至少6位！");
			}else{
				this.setCustomValidity("");
			}
		}
	},
	chkpassword.onblur = function(){
		if(this.validity.valueMissing){
			this.setCustomValidity("密码不能为空！");			
		}else{
			var pwd1 = password.value;
			var pwd2 = this.value;
			if(pwd1!=pwd2){
				this.setCustomValidity("两次输入的密码不一致！");	
			}else{
				this.setCustomValidity("");
			}
		}
	},
	phonenumber.onblur = function(){
		if(this.validity.valueMissing){
			this.setCustomValidity("手机号码不能为空！");			
		}else{
			var reg = /^[1][3578][0-9]{9}$/;
			if(!reg.test(this.value)){
				this.setCustomValidity("手机号码格式错误！");
			}else{
				this.setCustomValidity("");
			}
		}
	};
	
	
	
	$("#logins").click(function(){
		$("#login").css("display","block");
		$("#mask").css("display","block");
	});
	
	$("#registers").click(function(){
		$("#register").css("display","block");
		$("#mask").css("display","block");
	});
	
	//处理关闭对话框
	$("#login i.close").click(function(){
		$("#login").css("display","none");
		$("#mask").css("display","none");
	});
	
	$("#register i.close").click(function(){
		$("#register").css("display","none");
		$("#mask").css("display","none");
	});
	
	//点击切换导航条的样式
	
	$("#navbar li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	})
	
});

