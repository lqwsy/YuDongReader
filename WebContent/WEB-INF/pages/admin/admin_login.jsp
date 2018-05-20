<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<%=path%>/static/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="<%=path%>/static/css/bootstrapValidator.min.css" type="text/css" rel="stylesheet" />
	<link href="<%=path%>/static/css/adminLogin.css" type="text/css" rel="stylesheet" />
	<title>管理员登录</title>
</head>
<body>
	<div id="login" align="center">
		<form id="adminLoginForm" role="form"
			action="${pageContext.request.contextPath}/webLoginController"
			method="post">
			<input type="hidden" name="role" id="role" value="1"/>
			<div class="form-group">
				<h2>鱼洞阅读后台管理系统</h2>
			</div>
			<div class="form-group form-group-input">
				<input type="text" class="form-control" id="userName"
					name="userName" placeholder="用户名" />
			</div>
			<div class="form-group form-group-input">
				<input type="password" class="form-control" id="password"
					name="password" placeholder="密码" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary login-btn">登录</button>
			</div>
			<div class="form-group">
				<span id="login_result">${login_result}</span>
			</div>
		</form>
	</div>
</body>
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script src="<%=path%>/static/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/bootstrapValidator.min.js"></script>
<script src="<%=path%>/static/js/admin_login.js"></script>
</body>
</html>