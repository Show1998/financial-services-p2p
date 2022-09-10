
//同意实名认证协议
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

	$("#phone").blur(function () {
		var phone = $.trim($("#phone").val());
		if (phone == ""){
			showError("phone","手机号不能为空")
		}else if (/^1[1-9]\d{9}$/.test(phone)){
			showError("phone","请输入正确的手机号")
		}else {
			showSuccess("phone")
		}
	});

	$("#realName").blur(function () {
		var realName = $.trim($("#realName").val());
		if (realName == ""){
			showError("realName","请输入姓名")
		} else if (!/^[\u4e00-\u9fa5]$/.test(realName)){
			showError("realName","请输入正确的名字")
		}else {
			showSuccess("realName")
		}
	})

	$("#idCard").blur(function () {
		var idCard = $.trim($("#realName").val());
		if (idCard == ""){
			showError("idCard","请输入身份证号码")
		} else if (!/^\d{8,18}|[0-9x]{8,18}|[0-9X]{8,18}?$/.test(realName)){
			showError("idCard","请输入正确的身份证号码")
		}else {
			showSuccess("idCard")
		}
	})

	$("#btnRegist").click(function () {
		$("#phone").blur()
		$("#realName").blur()
		$("#idCard").blur()
		var errorText = $("div[id$='Err']").text();
		if (errorText == ""){
			
		}
	})
});
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
