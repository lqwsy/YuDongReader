<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html style="overflow: hidden;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我的图书</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="keywords" content="">
		<script type="application/x-javascript">
			addEventListener("load", function() {
				setTimeout(hideURLbar, 0);
			}, false);

			function hideURLbar() {
				window.scrollTo(0, 1);
			}
		</script>
		<link href="static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
		<link href="static/css/style.css" rel="stylesheet" type="text/css">
		<link href="static/css/font-awesome.css" rel="stylesheet">
		<link rel="stylesheet" href="static/css/icon-font.min.css" type="text/css">
		<script src="static/js/jquery-1.10.2.min.js"></script>
		<script src="static/js/css3clock.js"></script>
		<script src="static/js/skycons.js"></script>
	</head>
	<body style="">
		<div class="page-container">
			<div class="left-content">
				<div class="inner-content">
					<div class="header-section">
						<div class="top_menu">
							<div class="profile_details_left" style="height: 70px;">
								<h3 style="color: white;">欢迎来到鱼洞阅读</h3>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="outter-wp">
						<div class="sub-heard-part">
							<ol class="breadcrumb m-b-0">
								<li>
									<a href="#">首页</a>
								</li>
								<li class="active">我的图书</li>
							</ol>
						</div>
						<div class="graph-visual tables-main">
							<h2 class="inner-tittle">图书列表</h2>
							<div class="graph">
								<div class="tables">
									<table class="table">
										<thead>
											<tr>
												<th>ID</th>
												<th>图书名称</th>
												<th>图书作者</th>
												<th>上传时间</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${!empty sessionScope.cur_user_books}">
												<c:forEach items="${sessionScope.cur_user_books}" var="mybook">
													<tr>
														<th scope="row">${mybook.bookId}</th>
														<td>${mybook.bookName}</td>
														<td>${mybook.bookAuthor}</td>
														<td><fmt:formatDate value='${mybook.uploadTime}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
														<c:choose>
															<c:when test="${mybook.bookState==1}">
																<td>未审核</td>
															</c:when>
															<c:otherwise>
																<td>已审核</td>
															</c:otherwise>
														</c:choose>
														<td>
															<a href="${pageContext.request.contextPath}/myBookInfo?bookId=${mybook.bookId}">编辑</a> |
															<a href="#">删除</a>
														</td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
					<footer>
						<p>Copyright © 2014-2018.YUDONG Inc. All rights reserved.</p>
					</footer>
				</div>
			</div>
			<div class="sidebar-menu">
				<header class="logo">
					<a href="#"><span id="logo"><h1>鱼洞阅读</h1></span></a>
				</header>
				<div style="border-top:1px solid rgba(69, 74, 84, 0.7)"></div>
				<div class="down">
					<a href="${pageContext.request.contextPath}/myProfile"><img src="static/img/${sessionScope.cur_user.headImage}"></a>
					<a href="${pageContext.request.contextPath}/myProfile"><span class=" name-caret">用户：${sessionScope.cur_user.userName}</span></a>
					<p>昵称：${sessionScope.cur_user.userNickName}</p>
					<ul>
						<li>
							<a class="tooltips" href="${pageContext.request.contextPath}/myProfile"><span>个人信息</span><i class="lnr lnr-user"></i></a>
						</li>
						<li>
							<a class="tooltips" href="${pageContext.request.contextPath}/webLogout?type=2"><span>退出</span><i class="lnr lnr-power-switch"></i></a>
						</li>
					</ul>
				</div>
				<!--//down-->
				<div class="menu">
					<ul id="menu">
						<li>
							<a href="${pageContext.request.contextPath}/myBook">
							<i class="lnr lnr-layers"></i> 
							<span>我的图书</span></a>
						</li>
						<li id="menu-academico">
							<a href="#">
								<i class="lnr lnr-book"></i> 
								<span>图书管理</span> 
								<span class="fa fa-angle-right" style="float: right"></span>
							</a>
							<ul id="menu-academico-sub">
								<li id="menu-academico-avaliacoes">
									<a href="${pageContext.request.contextPath}/uploadBook">图书上传</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<script>
			var toggle = true;
			$(".sidebar-icon").click(function() {
				if(toggle) {
					$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
					$("#menu span").css({
						"position": "absolute"
					});
				} else {
					$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
					setTimeout(function() {
						$("#menu span").css({
							"position": "relative"
						});
					}, 400);
				}
				toggle = !toggle;
			});
		</script>
		<script src="static/js/jquery.nicescroll.js"></script>
		<script src="static/js/scripts.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
	</body>
</html>