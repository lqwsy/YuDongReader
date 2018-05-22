<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="js cssanimations">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>图书详情</title>
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
	<button
		class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: &#39;#topbar-collapse&#39;}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
			<li class="am-dropdown" data-am-dropdown=""
				data-am-dropdown-toggle=""><a
				class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
					<span class="tpl-header-list-user-nick">超级管理员</span><span
					class="tpl-header-list-user-ico"> <img src="static/img/user01.png"></span>
			</a>
				<ul class="am-dropdown-content">
					<li><a href="${pageContext.request.contextPath}/webLogout?type=1"><span class="am-icon-power-off"></span> 退出</a>
					</li>
				</ul></li>
			<li><a href="#" class="tpl-header-list-link"><span
					class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a></li>
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
						<!-- 打开状态 a 标签添加 active 即可   --> <a href="javascript:;"
						class="nav-link tpl-left-nav-link-list active"> <i
							class="am-icon-bar-chart"></i> <span>系统管理</span> <!-- 列表打开状态的i标签添加 tpl-left-nav-more-ico-rotate 图表即90°旋转  -->
							<i
							class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
					</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block">
							<li>
								<!-- 打开状态 a 标签添加 active 即可   --> <a href="user_manage.html">
									<i class="am-icon-angle-right"></i> <span>用户管理</span>
							</a> <a href="book_manage.html" class="active"> <i
									class="am-icon-angle-right"></i> <span>图书管理</span>
							</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<div class="tpl-content-wrapper">
			<ol class="am-breadcrumb">
				<li><a href="#" class="am-icon-home">系统管理</a></li>
				<li><a href="book_manage.html">图书管理</a></li>
				<li class="am-active">图书详情</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 图书详情
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="tpl-form-body tpl-form-line">
							<form class="am-form tpl-form-line-form">
								<intpu type="hidden" name="bookId" id="bookId" value="">
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">图书名称</label>
									<div class="am-u-sm-9">
										<input type="text" class="tpl-form-input" id="user-name"
											readonly="readonly" placeholder="图书名称">
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">图书作者</label>
									<div class="am-u-sm-9">
										<input type="text" class="tpl-form-input" id="user-name"
											readonly="readonly" placeholder="图书作者">
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-phone" class="am-u-sm-3 am-form-label">图书分类
									</label>
									<div class="am-u-sm-9">
										<select id="classificationType" readonly="readonly">
											<option value="1">小说</option>
											<option value="2">文学</option>
											<option value="3">传记</option>
											<option value="4">历史</option>
											<option value="5">经济</option>
											<option value="6">管理</option>
											<option value="7">励志</option>
										</select>
									</div>
								</div>

								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">上传时间</label>
									<div class="am-u-sm-9">
										<input type="text" placeholder="上传时间" readonly="readonly">
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-weibo" class="am-u-sm-3 am-form-label">图书封面</label>
									<div class="am-u-sm-9">
										<div class="am-form-group am-form-file">
											<div class="tpl-form-file-img">
												<img src="static/img/a5.png" alt="">
											</div>
										</div>

									</div>
								</div>
								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">图书简介</label>
									<div class="am-u-sm-9">
										<textarea readonly="readonly" class="" rows="10"
											id="user-intro" placeholder="请输入图书简介，介于5-100个字"></textarea>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">图书下载</label>
									<div class="am-u-sm-9">
										<a href="static/book/book001.txt" download="book001.txt">下载图书</a>
									</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="button"
											class="am-btn am-btn-primary tpl-btn-bg-color-success ">审核</button>
										<button type="button"
											class="am-btn am-btn-primary tpl-btn-bg-color-success ">返回</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/amazeui.min.js"></script>
	<script src="static/js/app.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var type = 2;
					$("#classificationType").find("option[value = '" + type + "']").attr("selected","selected");
				});
	</script>
</body>
</html>