<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<form id="loginForm" action="${pageContext.request.contextPath}/webLoginController" method="post">
		<input type="hidden" name="role" id="role" value="2"/>
		用户名：<input type="text" name="userName" class="username"/>
		<br/>
		密码：<input type="password" name="password" class="password"/>
		<br/>
			<input type="submit" value ="提交">
			<span id="login_result">${login_result}</span>
	</form>
</body>
</html>