var referrer = "";//登录后返回页面
referrer = document.referrer;
if (!referrer) {
	try {
		if (window.opener) {
			// IE下如果跨域则抛出权限异常，Safari和Chrome下window.opener.location没有任何属性
			referrer = window.opener.location.href;
		}
	} catch (e) {
	}
}

//按键盘Enter键即可登录
$(document).keyup(function(event){
	if(event.keyCode == 13){
		$("#loginId").click()
	}
});

$(function () {

	$("#loginId").click(function () {
		var id = $.trim($("#phone").val())
		var password = $.trim($("#loginPassword").val())
		if (id == ''){
			alert('请输入正确的手机号');
			$("#loginPassword").val('')
		}
		if (password == ''){
			alert("请输入密码！")
		}
		$("#loginPassword").val($.md5(password))
		$.ajax({
			url:"/p2p/auth/checkAndLogin",
			type:"post",
			data:{
				"loginPassword": $.md5(password),
				"id":id
			},
			success:function (data) {
				if (data == "1"){
					// window.location.href = "/p2p/index"
					alert("登陆成功")
				}else {
					alert("验证错误，请重试！")
				}
			}
		})
	})

});
