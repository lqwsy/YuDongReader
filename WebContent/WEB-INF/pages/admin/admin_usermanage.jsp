<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="js cssanimations">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>鱼洞阅读后台管理系统</title>
	<meta name="apple-mobile-web-app-title" content="Amaze UI">
	<link rel="stylesheet" href="static/css/amazeui.min.css">
	<link rel="stylesheet" href="static/css/admin.css">
	<link rel="stylesheet" href="static/css/app.css">
</head>
<body data-type="generalComponents">
	<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<a href="javascript:;" class="tpl-logo"> <img
			src="static/img/weblogo.png" alt="">
		</a>
	</div>
	<!-- <button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: &#39;#topbar-collapse&#39;}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button> -->
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
			<li class="am-dropdown" data-am-dropdown="" data-am-dropdown-toggle="">
				<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
					<span class="tpl-header-list-user-nick">超级管理员</span> 
					<span class="tpl-header-list-user-ico"> 
					<img src="static/img/user01.png"></span>
				</a>
				<ul class="am-dropdown-content">
					<li>
						<a href="${pageContext.request.contextPath}/webLogout?type=1">
							<span class="am-icon-power-off"></span> 退出
						</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="${pageContext.request.contextPath}/webLogout" class="tpl-header-list-link"> 
				<span class="am-icon-sign-out tpl-header-list-ico-out-size"></span>
				</a>
			</li>
		</ul>
	</div>
	</header>
	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">功能列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item"><a href="#"
						class="nav-link active"> <i class="am-icon-home"></i> <span>首页</span>
					</a></li>
					<li class="tpl-left-nav-item">
						<!-- 打开状态 a 标签添加 active 即可   --> 
						<a href="javascript:;" class="nav-link tpl-left-nav-link-list active"> 
							<i class="am-icon-bar-chart"></i> 
							<span>系统管理</span> 
							<!-- 列表打开状态的i标签添加 tpl-left-nav-more-ico-rotate 图表即90°旋转  -->
							<i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
						</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block">
							<li>
								<!-- 打开状态 a 标签添加 active 即可   --> 
								<a href="${pageContext.request.contextPath}/adminUserManager?role=3" class="active">
									<span>用户管理</span>
								</a>
								<a href="${pageContext.request.contextPath}/adminBookManager">
									<span>图书管理</span>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="tpl-content-wrapper">
			<div class="tpl-content-page-title">用户管理</div>
			<ol class="am-breadcrumb">
				<li><a href="#" class="am-icon-home">首页</a></li>
				<li><a href="#">系统管理</a></li>
				<li class="am-active">用户管理</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 用户列表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select id="userRole" data-am-selected="{btnSize: &#39;sm&#39;}" style="display: none;">
									<c:choose>
										<c:when test="${select_role==1}">
											<option value="3">所有</option>
											<option value="1" selected="selected">超级管理员</option>
											<option value="2">普通用户</option>
										</c:when>
										<c:when test="${select_role==2}">
											<option value="3">所有</option>
											<option value="1">超级管理员</option>
											<option value="2" selected="selected">普通用户</option>
										</c:when>
										<c:otherwise>
											<option value="3" selected="selected">所有</option>
											<option value="1">超级管理员</option>
											<option value="2">普通用户</option>
										</c:otherwise>
									</c:choose>
								</select>
							</div>
						</div>
						<!-- <div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<input type="text" class="am-form-field"> <span class="am-input-group-btn">
									<button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
										type="button"></button>
								</span>
							</div>
						</div> -->
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table
									class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-id">ID</th>
											<th class="table-title">账号</th>
											<th class="table-type">昵称</th>
											<th class="table-type">类型</th>
											<th class="table-type">状态</th>
											<th class="table-date am-hide-sm-only">注册日期</th>
											<th class="table-set">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty sessionScope.admin_all_users}">
											<c:forEach items="${sessionScope.admin_all_users}" var="user" varStatus="index">
											<tr>
												<td>${user.userId}</td>
												<td><a href="#">${user.userName}</a></td>
												<td>${user.userNickName}</td>
												<c:choose>
													<c:when test="${user.role==1}">
														<td>超级管理员</td>
													</c:when>
													<c:otherwise>
														<td>普通用户</td>
													</c:otherwise>
												</c:choose>
												<c:choose>
													<c:when test="${user.userState==1}">
														<td>正常</td>
													</c:when>
													<c:otherwise>
														<td>冻结</td>
													</c:otherwise>
												</c:choose>
												<td  class="am-hide-sm-only"><fmt:formatDate value='${user.registeTime}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
												<td>
													<div class="am-btn-toolbar">
														<div class="am-btn-group am-btn-group-xs">
															<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onClick="turnToUserDetail(${index.count})">
																<span class="am-icon-pencil-square-o"></span>查看修改
															</button>
														</div>
													</div>
												</td>
											</tr>
											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<div class="am-cf">
									<div class="am-fr">
										<ul class="am-pagination tpl-pagination">
											<li class="am-disabled"><a href="#">«</a></li>
											<li class="am-active"><a href="#">1</a></li>
											<li><a href="#">»</a></li>
										</ul>
									</div>
								</div>
								<hr>

							</form>
						</div>
					</div>
				</div>
				<div class="tpl-alert"></div>
			</div>
		</div>
	</div>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/amazeui.min.js"></script>
	<script src="static/js/app.js"></script>
	<script type="text/javascript">
		function turnToUserDetail(count) {
		
		  /* var f=document.createElement("form");
		  f.action="/YuDongReader/userDetail";
		  f.method="post";//指定为post
		  f.innerHTML="<input type='hidden' name='count' value='"+count+"'/>";
		  document.appendChild(f);
		  f.submit() */
			window.location.href = "/YuDongReader/userDetail?count="+count;
			window.event.returnValue = false;
		}

		function deleteUser(userId) {
			alert("deleteUser");
		}

		$('#userRole').change(function(){
			window.location.href = "/YuDongReader/adminUserManager?role="+$(this).val();
			window.event.returnValue = false;
		});
	</script>

</body>
</html>