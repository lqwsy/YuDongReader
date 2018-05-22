<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="js cssanimations">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>鱼洞阅读后台管理系统</title>
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
					class="tpl-header-list-user-ico"> <img
						src="static/img/user01.png"></span>
			</a>
				<ul class="am-dropdown-content">
					<!-- <li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li> -->
					<li><a
						href="${pageContext.request.contextPath}/webLogout?type=1"><span
							class="am-icon-power-off"></span> 退出</a></li>
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
					<li class="tpl-left-nav-item"><a href=""
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
								<!-- 打开状态 a 标签添加 active 即可   --> <a
								href="${pageContext.request.contextPath}/adminUserManager">
									<i class="am-icon-angle-right"></i> <span>用户管理</span>
							</a> <a href="${pageContext.request.contextPath}/adminBookManager"
								class="active"> <i class="am-icon-angle-right"></i> <span>图书管理</span>
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
				<li class="am-active">图书管理</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 列表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select data-am-selected="{btnSize: &#39;sm&#39;}"
									style="display: none;">
									<option value="option1">小说</option>
									<option value="option2">文学</option>
									<option value="option3">传记</option>
									<option value="option3">历史</option>
									<option value="option3">经济</option>
									<option value="option3">管理</option>
									<option value="option3">励志</option>
								</select>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<input type="text" class="am-form-field"> <span
									class="am-input-group-btn">
									<button
										class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search"
										type="button"></button>
								</span>
							</div>
						</div>
					</div>
					<div class="am-g">
						<div class="am-u-sm-12">
							<form class="am-form">
								<table
									class="am-table am-table-striped am-table-hover table-main">
									<thead>
										<tr>
											<th class="table-check"><input type="checkbox"
												class="tpl-table-fz-check"></th>
											<th class="table-id">ID</th>
											<th class="table-title">书名</th>
											<th class="table-type">作者</th>
											<th class="table-type">分类</th>
											<th class="table-author am-hide-sm-only">上传者</th>
											<th class="table-date am-hide-sm-only">上传日期</th>
											<th class="table-type">下载量</th>
											<th class="table-set">操作</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td><input type="checkbox"></td>
											<td>1</td>
											<td><a href="#">斗破苍穹</a></td>
											<td>天蟾豆</td>
											<td class="am-hide-sm-only">小说</td>
											<td>阿尔卑斯</td>
											<td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
											<td>1524</td>
											<td>
												<div class="am-btn-toolbar">
													<div class="am-btn-group am-btn-group-xs">
														<button
															class="am-btn am-btn-default am-btn-xs am-text-secondary" onClick="turnToBookDetail()">
															<span class="am-icon-pencil-square-o"></span> 审核
														</button>
														<button
															class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only" onClick="deleteBook()">
															<span class="am-icon-trash-o"></span> 删除
														</button>
													</div>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
								<div class="am-cf">
									<div class="am-fr">
										<ul class="am-pagination tpl-pagination">
											<li class="am-disabled"><a href="#">«</a></li>
											<li class="am-active"><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
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
	<script type="text/javascript">
		function turnToBookDetail() {
			window.location.href = "/YuDongReader/bookDetail";
			window.event.returnValue = false;//这个属性放到提交表单中的onclick事件中在这次点击事件不会提交表单，如果放到超链接中则在这次点击事件不执行超链接href属性
		}

		function deleteBook() {
			alert("deleteBook");
		}
	</script>
	<script src="static/js/jquery.min.js"></script>
	<script src="static/js/amazeui.min.js"></script>
	<script src="static/js/app.js"></script>
</body>
</html>