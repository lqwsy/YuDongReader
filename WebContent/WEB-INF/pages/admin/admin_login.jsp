<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>鱼洞阅读后台管理系统登录</title>
		<link rel="stylesheet" href="static/css/amazeui.min.css" />
		<link rel="stylesheet" href="static/css/admin.css">
		<link rel="stylesheet" href="static/css/app.css">
	</head>
	<body data-type="login">
		<div class="am-g myapp-login">
			<div class="myapp-login-logo-block  tpl-login-max">
				<div class="myapp-login-logo-text">
					<div class="myapp-login-logo-text">
						鱼洞阅读后台管理系统<span> 登录</span> <i class="am-icon-skyatlas"></i>
					</div>
				</div>
				<div class="login-font">
				</div>
				<div class="am-u-sm-10 login-am-center">
					<form class="am-form"  id="adminLoginForm" role="form" action="${pageContext.request.contextPath}/webLoginController" method="post">
						<fieldset>
							<input type="hidden" name="role" id="role" value="1"/>
							<div class="am-form-group">
								<input type="text" class="" name="userName" id="doc-ipt-email-1" placeholder="输入账号">
							</div>
							<div class="am-form-group">
								<input type="password" class="" name="password"  id="doc-ipt-pwd-1" placeholder="输入密码">
							</div>
							<p><button type="submit" class="am-btn am-btn-default">登录</button></p>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<script src="static/js/jquery.min.js"></script>
		<script src="static/js/amazeui.min.js"></script>
		<script src="static/js/app.js"></script>
	</body>
</html>