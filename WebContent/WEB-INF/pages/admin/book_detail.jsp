<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<a href="javascript:;" class="tpl-logo" style="margin:20px 0;"><img src="static/img/weblogo.png" alt=""></a>
	</div>
	<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
		data-am-collapse="{target: &#39;#topbar-collapse&#39;}">
		<span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
	</button>
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
			<li class="am-dropdown" data-am-dropdown="" data-am-dropdown-toggle="">
				<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
					<span class="tpl-header-list-user-nick">超级管理员</span>
					<span class="tpl-header-list-user-ico"> <img src="static/img/user01.png"></span>
				</a>
				<ul class="am-dropdown-content">
					<li>
						<a href="${pageContext.request.contextPath}/webLogout?type=1">
						<span class="am-icon-power-off"></span> 退出</a>
					</li>
				</ul>
			</li>
			<!-- <li><a href="#" class="tpl-header-list-link">
				<span class="am-icon-sign-out tpl-header-list-ico-out-size"></span></a>
			</li> -->
		</ul>
	</div>
	</header>
	<div class="tpl-page-container tpl-page-header-fixed">
		<div class="tpl-left-nav tpl-left-nav-hover">
			<div class="tpl-left-nav-title">功能列表</div>
			<div class="tpl-left-nav-list">
				<ul class="tpl-left-nav-menu">
					<li class="tpl-left-nav-item">
						<!-- 打开状态 a 标签添加 active 即可   --> 
						<a href="javascript:;" class="nav-link tpl-left-nav-link-list active"> 
							<i class="am-icon-home"></i> 
							<span>系统管理</span> 
							<!-- 列表打开状态的i标签添加 tpl-left-nav-more-ico-rotate 图表即90°旋转  -->
							<i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
						</a>
						<ul class="tpl-left-nav-sub-menu" style="display: block">
							<li>
								<!-- 打开状态 a 标签添加 active 即可   --> 
								<a href="${pageContext.request.contextPath}/adminUserManager?role=3&pageNum=1">
									<span>用户管理</span>
								</a>
								<a href="${pageContext.request.contextPath}/adminBookManager?pageNum=1"  class="active">
									<span>图书管理</span>
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
				<li><a href="${pageContext.request.contextPath}/adminBookManager?pageNum=1">图书管理</a></li>
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
							<form id="book-detail-form" class="am-form tpl-form-line-form" action="${pageContext.request.contextPath}/checkBook" method="post">
								<div class="am-form-group">
									<input type="hidden" id="bookId" name="bookId" value="${countBook.bookId}">
									<label for="user-name" class="am-u-sm-3 am-form-label">图书名称</label>
									<div class="am-u-sm-9">
										<input type="text" class="tpl-form-input" id="bookName" value="${countBook.bookName}" readonly="readonly">
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name" class="am-u-sm-3 am-form-label">图书作者</label>
									<div class="am-u-sm-9">
										<input type="text" class="tpl-form-input" id="bookAuthor" value="${countBook.bookAuthor}" readonly="readonly">
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-phone" class="am-u-sm-3 am-form-label">图书分类
									</label>
									<div class="am-u-sm-9">
										<input type="hidden" id="bookClassificationId" value="${countBook.bookClassificationId}">
										<select id="classificationType" disabled="disabled">
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
										<input type="text" value="<fmt:formatDate value='${countBook.uploadTime}' pattern='yyyy-MM-dd hh:mm:ss' />" readonly="readonly">
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">图书简介</label>
									<div class="am-u-sm-9">
										<textarea readonly="readonly" class="" rows="10" id="bookIntroduction">${countBook.bookIntroduction}</textarea>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-weibo" class="am-u-sm-3 am-form-label">图书封面</label>
									<div class="am-u-sm-9">
										<div class="am-form-group am-form-file">
											<div class="tpl-form-file-img">
												<img src="static/bookimg/${countBook.bookCoverPath}" alt="">
											</div>
										</div>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-intro" class="am-u-sm-3 am-form-label">图书下载</label>
									<div class="am-u-sm-9">
										<a href="static/book/${countBook.bookLocation}" download="${countBook.bookName}.txt">下载图书</a>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-phone" class="am-u-sm-3 am-form-label">图书状态 </label>
									<div class="am-u-sm-9">
										<select id="bookStateSelected" name="bookState" data-am-selected="{searchBox: 0}">
											<c:choose>
												<c:when test="${countBook.bookState==1}">
													<option value="1"  selected="selected">未审核</option>
													<option value="2">审核通过</option>
												</c:when>
												<c:when test="${countBook.bookState==2}">
													<option value="1">未审核</option>
													<option value="2" selected="selected">审核通过</option>
												</c:when>
											</c:choose>
										</select>
									</div>
								</div>
								<div class="am-form-group">
									<label class="am-u-sm-3 am-form-label">审核信息</label>
									<div class="am-u-sm-9">
										<input type="text" id="audit" value="${countBook.audit}" name="audit">
									</div>
								</div>
								<div class="am-form-group">
									<div class="am-u-sm-9 am-u-sm-push-3">
										<button type="submit"
											class="am-btn am-btn-primary tpl-btn-bg-color-success ">确认修改</button>
										<button type="button"
											class="am-btn am-btn-primary tpl-btn-bg-color-success " onClick="javascript:history.back(-1)">返回</button>
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
					var type = $('#bookClassificationId').val();
					if(type!=null || type!=undefined){
						$("#classificationType").find("option[value = '" + type + "']").attr("selected","selected");
					}
					if (!$.AMUI.support.mutationobserver) {
						$("#classificationType").trigger('changed.selected.amui');
					}
				});
		function checkBook(){
			/* var bookStates = $('#bookStateSelected').val();
			window.location.href = "/YuDongReader/checkBook?bookState="+bookStates+"&bookId="+bookId;
			window.event.returnValue = false; */
			return true;
		}
	</script>
</body>
</html>