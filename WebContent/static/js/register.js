function sendVerCode(obj) {
	var userName = $("#userName").val();

	if (userName.replace(/(^s*)|(s*$)/g, "").length == 11) {
		$.ajax({
			url : "sendVerCodeController",
			data : {
				userName : userName
			},
			type : "post",
			success : function(data) {
				time(obj);
			}
		});
	} else {
		alert("请输入11位手机号");
	}

}

var wait = 60;
function time(o) {
	if (wait == 0) {
		o.removeAttribute("disabled");
		o.text = "获取验证码";
		wait = 60;
	} else {
		o.setAttribute("disabled", true);
		o.text = "重新发送(" + wait + ")";
		wait--;
		setTimeout(function() {
			time(o)
		}, 1000)
	}
}