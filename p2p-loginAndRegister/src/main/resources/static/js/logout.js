
$(function () {

    $("#exitbutton").click(function () {
    	$.ajax({
    		url: "/p2p/auth/logout",
    		type: "get",
    		success:function (data){
    			if (data == "1"){
    				alert("账号退出成功！")
    				window.location.href = "/p2p/index"
    				// alert("登陆成功")
    			}else {
    				alert("系统繁忙请稍后再试！")
    			}
    		}
    	})
    })
});
