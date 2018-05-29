<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>鱼洞阅读登录</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
		<link href="static/css/style.css" rel='stylesheet' type='text/css' />
		<link href="static/css/font-awesome.css" rel="stylesheet">
		<link rel="stylesheet" href="static/css/icon-font.min.css" type='text/css' />
		<script src="static/js/jquery-1.10.2.min.js"></script>
	</head>
	<body>
		<div class="error_page">
			<div class="error-top">
				<h2 class="inner-tittle page">鱼洞阅读</h2>
				<div class="login">
					<h3 class="inner-tittle t-inner">登录</h3>
					<form id="loginForm" method="post" action="${pageContext.request.contextPath}/webLoginController">
						<input type="hidden" name="role" id="role" value="2"/>
						<input type="text" name="userName" id="userName" class="text" placeholder="账号">
						<input type="password" name="password" id="password" placeholder="密码">
						<div class="submit">
							<input type="submit" onclick="myFunction()" value="登录">
						</div>
						<!-- <div class="clearfix">
							
						</div> -->
						<div class="new">
							<!-- <p><label class="checkbox11"><input type="checkbox" name="checkbox"><i> </i>忘记密码 ?</label></p> -->
							<p class="sign">还没有账号 ?
								<a href="${pageContext.request.contextPath}/webRegister">立即注册</a>
							</p>
							<div class="clearfix"></div>
						</div>
					</form>
					<label id="login_result">${login_result}</label>
				</div>
			</div>
		</div>
		<div class="footer">
			<p>Copyright © 2014-2018.YUDONG Inc. All rights reserved.</p>
		</div>
		<script src="static/js/jquery.nicescroll.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/scripts.js"></script>
		<script src="static/js/login.js"></script>
	</body>
</html>