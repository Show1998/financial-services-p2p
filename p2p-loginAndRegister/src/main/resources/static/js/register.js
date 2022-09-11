//错误提示
function showError(id,msg) {
	$("#"+id+"Ok").hide();
	$("#"+id+"Err").html("<i></i><p>"+msg+"</p>");
	$("#"+id+"Err").show();
	$("#"+id).addClass("input-red");
}
//错误隐藏
function hideError(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id).removeClass("input-red");
}
//显示成功
function showSuccess(id) {
	$("#"+id+"Err").hide();
	$("#"+id+"Err").html("");
	$("#"+id+"Ok").show();
	$("#"+id).removeClass("input-red");
}


//打开注册协议弹层
function alertBox(maskid,bosid){
	$("#"+maskid).show();
	$("#"+bosid).show();
}
//关闭注册协议弹层
function closeBox(maskid,bosid){
	$("#"+maskid).hide();
	$("#"+bosid).hide();
}

//注册协议确认
$(function() {
	$("#agree").click(function(){
		var ischeck = document.getElementById("agree").checked;
		if (ischeck) {
			$("#btnRegist").attr("disabled", false);
			$("#btnRegist").removeClass("fail");
		} else {
			$("#btnRegist").attr("disabled","disabled");
			$("#btnRegist").addClass("fail");
		}
	});
	//给输入框绑定事件
	$("#phone").blur(function () {
		var phone = $.trim($("#phone").val());
		if(phone == ''){
			showError("phone","请输入手机号")
		}else if(!/^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\d{8}$/.test(phone)){
			showError("phone","请输入正确的手机号！");
		}else {
			//查询数据库，手机号是否可用
			$.ajax({
				url:"/p2p/page/checkPhone",
				type:"get",
				data:{"phone":phone},
				success:function (data){
					if(data.result == 1){
						showSuccess("phone")
					}else showError("phone","此手机号已经注册！请直接登录！")
				},
				error:function () {
					showError("phone","系统繁忙，请稍后重试！")
				}
			})
			// showSuccess("phone");
		}
	})
	$("#phone").focus(function () {
		//if(有错误)就清空这个对话框
		if ($("#phoneErr").text() != ""){
			$("#phone").val("")
			hideError("phone")
		}
	})
	//给密码框绑定事件
	$("#loginPassword").blur(function () {
		var loginPwd = $.trim($("#loginPassword").val())
		if(loginPwd == '' || loginPwd == null){
			showError("loginPassword","密码不能为空！")
		}else if(loginPwd.length < 6 || loginPwd.length > 20){
			showError("loginPassword","密码要在6到20位之间！")
		}else if(!/^[A-Za-z0-9]{4,40}$/.test(loginPwd)){
			showError("loginPassword","密码只能包含大小写字母和数字！")
		} else if(!/^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*/.test(loginPwd)){
			showError("loginPassword","密码应该同时包含数字和字母！")
		}
		else {
			showSuccess("loginPassword")
		}
	})
	$("#loginPassword").focus(function () {

		//if(有错误)就清空这个对话框
		if ($("#loginPasswordErr").text() != ""){
			$("#loginPassword").val("")
			hideError("loginPassword")
		}
	})

	$("#messageCode").blur(function () {
		var messageCode = $.trim($("#messageCode").val());
		if(messageCode == ''){
			showError("messageCode","验证码不能为空！")
		}
	})
	$("#messageCode").focus(function () {

		//if(有错误)就清空这个对话框
		if ($("#messageCodeErr").text() != ""){
			$("#messageCode").val("")
			hideError("messageCode")
		}
	})

	$("#messageCodeBtn").click(function () {
		//隐藏验证码提示错误
		hideError("messageCode");
		//触发验证避免修改
		$("#phone").blur();
		$("#loginPassword").blur();

		var errorText = $("div[id$='Err']").text();

		var phone = $.trim($("#phone").val());
		if(errorText == ""){
			//请求后台，发送短信验证码
			$.ajax({
				url:"/p2p/page/sendMessage",
				type:"post",
				data:{
					"phone":phone
				},
				success:function (data) {
					if (data == 1 ){
						alert("验证码已发送，注意查收！")
						//开始倒计时
						if (!$("#messageCodeBtn").hasClass("on")){
							$.leftTime(60,function (d) {
								if(d.status){
									$("#messageCodeBtn").text(d.s == '00' ? '60秒重新获取':d.s + '秒重新获取')
									$("#messageCodeBtn").addClass("on")
								}else {
									$("#messageCodeBtn").removeClass("on")
									$("#messageCodeBtn").text("获取验证码")
								}
							})
						}
					} else if (data == -1){
						showError("messageCode","系统繁忙，请稍后再试！")
					} else if (data == -2){
						showError("messageCode","短信发送失败")
					}
				},
				error:function () {
					showError("messageCode","系统异常")
				}
			})
		}
	})

	//给注册框绑定单机事件
	$("#btnRegist").click(function () {
		//触发验证避免修改
		$("#phone").blur();
		$("#loginPassword").blur();
		$("#messageCode").blur();

		//看是否可以注册
		var errorText = $("div[id$='Err']").text();
		if(errorText == ""){
			var phone = $.trim($("#phone").val());
			var loginPassword = $.trim($("#loginPassword").val())
			var messageCode = $.trim($("#messageCode").val())
			$("#loginPassword").val($.md5(loginPassword))
			$.ajax({
				url: "/p2p/auth/checkAndRegister",
				type: "post",
				data: {
					"phone":phone,
					"loginPassword": $.md5(loginPassword),
					"messageCode":messageCode
				},
				success:function (data) {
					if (data == 1){
						//注册成功
						alert("注册成功！请尽快实名认证！！")
						window.location.href = "/p2p/page/login"
					}else if (data == 0){
						showError("loginPassword","错误！")
					}else if(data == -1){
						showError("messageCode","验证码错误！")
						$("#loginPassword").val("")
						$("#messageCode").val("")
					}
				},
				error:function () {
					alert("注册失败！请重试！")
				}
			})
		}
	})
});
