<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow: hidden;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书上传</title>
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
							<li><a href="${pageContext.request.contextPath}/myBook?pageNum=1">首页</a></li>
							<li class="active">图书上传</li>
						</ol>
					</div>
					<div class="forms-main">
						<h2 class="inner-tittle">填写图书信息</h2>
						<div class="graph-form">
							<div class="form-body">
								<form id="bookUploadForm" action="${pageContext.request.contextPath }/bookUploadController" method="post" enctype="multipart/form-data">
									<input type="hidden" id="uploadResult" value="${upload_result}">
									<div class="form-group">
										<label for="bookName">图书名称</label> <input type="text"
											class="form-control" id="bookName" name="bookName" placeholder="图书名称">
									</div>
									<div class="form-group">
										<label for="bookAuthor">图书作者</label> <input type="text"
											class="form-control" id="bookAuthor" name="bookAuthor" placeholder="图书作者">
									</div>
									<div class="form-group">
										<label for="bookIntroduction">图书简介</label>
										<textarea name="bookIntroduction" id="bookIntroduction"
											cols="100" rows="10" class="form-control1"
											placeholder="简介字数介于5到100个字"></textarea>
									</div>
									<div class="form-group">
										<label for="bookClassificationId">图书分类</label> 
										<select name="bookClassificationId">
											<option value="1">小说</option>
											<option value="2">文学</option>
											<option value="3">传记</option>
											<option value="4">历史</option>
											<option value="5">经济</option>
											<option value="6">管理</option>
											<option value="7">励志</option>
										</select>
									</div>
									<div class="form-group">
										<label for="bookFile">图书文件</label> 
										<input type="file" id="bookFile" name="bookFile">
										<p class="help-block">请上传小于20M的txt格式文件.</p>
									</div>
									<div class="form-group">
										<label for="bookimgFile">图书封面</label> 
										<input type="file" id="bookimgFile" name="bookimgFile">
										<p class="help-block">请上传小于20k的90x110尺寸的jpg、png文件.</p>
									</div>
								<button type="submit" class="btn btn-default">上传</button>
								</form>
							</div>
						</div>
					</div>
					<div class="forms-inner">
						<div class="set-1">
							<div class="clearfix"></div>
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
			<a href="#"> 
				<span id="logo"><h1>鱼洞阅读</h1></span>
			</a> 
		</header>
		<div style="border-top: 1px solid rgba(69, 74, 84, 0.7)"></div>
		<div class="down">
			<a href="${pageContext.request.contextPath}/myProfile"><img src="static/img/${sessionScope.cur_user.headImage}"></a> 
			<a href="${pageContext.request.contextPath}/myProfile"><span class=" name-caret">用户：${sessionScope.cur_user.userName}</span></a>
			<p>昵称：${sessionScope.cur_user.userNickName}</p>
			<ul>
				<li><a class="tooltips" href="${pageContext.request.contextPath}/myProfile"> <span>个人信息</span><i
						class="lnr lnr-user"></i>
				</a></li>
				<li><a class="tooltips"
					href="${pageContext.request.contextPath}/webLogout?type=2"> <span>退出</span><i
						class="lnr lnr-power-switch"></i>
				</a></li>
			</ul>
		</div>
		<!--//down-->
		<div class="menu">
			<ul id="menu">
				<li><a href="${pageContext.request.contextPath}/myProfile">
						<i class="lnr lnr-layers"></i> <span>个人设置</span>
				</a></li>
				<li id="menu-academico"><a href="#"> <i
						class="lnr lnr-book"></i> <span>图书管理</span> <span
						class="fa fa-angle-right" style="float: right"></span>
				</a>
				<ul id="menu-academico-sub">
					<li id="menu-academico-boletim"><a
						href="${pageContext.request.contextPath}/myBook?pageNum=1">我的图书</a></li>
					<li id="menu-academico-avaliacoes"><a
						href="${pageContext.request.contextPath}/uploadBook">图书上传</a></li>
					<li id="menu-academico-avaliacoes"><a
						href="${pageContext.request.contextPath}/myDeleteBooks">已删除图书</a></li>
				</ul></li>
			</ul>
		</div>
	</div>
	<div class="clearfix"></div>
	<script>
		$(document).ready(function(){
			 var result = $("#uploadResult").val();
			 if(result!=null && result!=undefined && result==1){
				 alert("上传成功");
			 }
		});
		var toggle = true;
		$(".sidebar-icon").click(
				function() {
					if (toggle) {
						$(".page-container").addClass("sidebar-collapsed")
								.removeClass("sidebar-collapsed-back");
						$("#menu span").css({
							"position" : "absolute"
						});
					} else {
						$(".page-container").removeClass("sidebar-collapsed")
								.addClass("sidebar-collapsed-back");
						setTimeout(function() {
							$("#menu span").css({
								"position" : "relative"
							});
						}, 400);
					}
					toggle = !toggle;
				});
	</script>
	<!--js -->
	<script src="static/js/jquery.nicescroll.js"></script>
	<script src="static/js/scripts.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
