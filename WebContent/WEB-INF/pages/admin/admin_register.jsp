<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
</head>
<body>
	<form id="RegisterForm" action="${pageContext.request.contextPath}/webRegisterController" method="post">
		<input type="hidden" name="role" id="role" value="1"/>
		手机号：<input type="text" id="userName" name="userName" class="username"/>
		<br/>
		密码：<input type="password" name="password" class="password"/>
		<br/>
		确认密码：<input type="password" name="newPassword" class="newPsassword"/>
		<br/>
		验证码：<input type="text" name="verCode" class="verCode"/>
			 <input type="button" id="sendCode" name="sendCode" value="免费获取验证码" onclick="sendVerCode(this)"/>
		<br/>
			<input type="submit" value ="注册">
			<span id="login_result">${register_result}</span>
	</form>	
	
	
	<script
		src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/js/register.js"></script>
</body>
</html>