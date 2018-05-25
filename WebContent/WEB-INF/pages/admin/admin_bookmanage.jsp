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
	<link rel="stylesheet" href="static/css/amazeui.min.css">
	<link rel="stylesheet" href="static/css/admin.css">
	<link rel="stylesheet" href="static/css/app.css">
</head>
<body data-type="generalComponents">
	<header class="am-topbar am-topbar-inverse admin-header">
	<div class="am-topbar-brand">
		<a href="javascript:;" class="tpl-logo" style="margin:20px 0;"> 
			<img src="static/img/weblogo.png" alt="">
		</a>
	</div>
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">
		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list tpl-header-list">
			<li class="am-dropdown" data-am-dropdown="" data-am-dropdown-toggle="">
				<a class="am-dropdown-toggle tpl-header-list-link" href="javascript:;">
					<span class="tpl-header-list-user-nick">超级管理员</span><span class="tpl-header-list-user-ico"> 
					<img src="static/img/user01.png"></span>
				</a>
				<ul class="am-dropdown-content">
					<!-- <li><a href="#"><span class="am-icon-bell-o"></span> 资料</a></li>
					<li><a href="#"><span class="am-icon-cog"></span> 设置</a></li> -->
					<li><a href="${pageContext.request.contextPath}/webLogout?type=1">
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
						<a href="" class="nav-link active"> <i class="am-icon-home"></i> <span>首页</span></a>
					</li>
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
			<div class="tpl-content-page-title">用户管理</div>
			<ol class="am-breadcrumb">
				<li><a href="#" class="am-icon-home">首页</a></li>
				<li><a href="#">系统管理</a></li>
				<li class="am-active">图书管理</li>
			</ol>
			<div class="tpl-portlet-components">
				<div class="portlet-title">
					<div class="caption font-green bold">
						<span class="am-icon-code"></span> 图书列表
					</div>
				</div>
				<div class="tpl-block">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select id="bookClassificationType" style="display: none;">
									<option value="8">所有</option>
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
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-form-group">
								<select id="bookStateSelected"  style="display: none;">
									<option value="3">所有</option>
									<option value="1">未审核</option>
									<option value="2">已审核</option>
								</select>
							</div>
						</div>
						<div class="am-u-sm-12 am-u-md-3">
							<div class="am-input-group am-input-group-sm">
								<input type="text" id="search-name" class="am-form-field" placeholder="请输入书名或作者名"> 
								<span class="am-input-group-btn">
									<button class="am-btn  am-btn-default am-btn-success tpl-am-btn-success am-icon-search" type="button" onClick="searchBook()"></button>
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
											<th class="table-id">序号</th>
											<th class="table-title">书名</th>
											<th class="table-type">作者</th>
											<th class="table-type">分类</th>
											<th class="table-author am-hide-sm-only">上传者</th>
											<th class="table-date am-hide-sm-only">上传日期</th>
											<th class="table-type">下载量</th>
											<th class="table-type">审核状态</th>
											<th class="table-set">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${!empty page.list}">
											<c:forEach items="${page.list}" var="book" varStatus="index">
												<tr>
													<td>${index.count}</td>
													<td><a href="${pageContext.request.contextPath}/bookDetail?index=${index.count}">${book.bookName}</a></td>
													<td>${book.bookAuthor}</td>
													<c:choose>
														<c:when test="${book.bookClassificationId==1}">
															<td class="am-hide-sm-only">小说</td>
														</c:when>
														<c:when test="${book.bookClassificationId==2}">
															<td class="am-hide-sm-only">文学</td>
														</c:when>
														<c:when test="${book.bookClassificationId==3}">
															<td class="am-hide-sm-only">传记</td>
														</c:when>
														<c:when test="${book.bookClassificationId==4}">
															<td class="am-hide-sm-only">历史</td>
														</c:when>
														<c:when test="${book.bookClassificationId==5}">
															<td class="am-hide-sm-only">经济</td>
														</c:when>
														<c:when test="${book.bookClassificationId==6}">
															<td class="am-hide-sm-only">管理</td>
														</c:when>
														<c:when test="${book.bookClassificationId==7}">
															<td class="am-hide-sm-only">励志</td>
														</c:when>
													</c:choose>
													<td>${book.uploadPerson}</td>
													<td class="am-hide-sm-only"><fmt:formatDate value='${book.uploadTime}' pattern='yyyy-MM-dd hh:mm:ss' /></td>
													<td>${book.bookDownloads}</td>
													<c:choose>
														<c:when test="${book.bookState==1}">
															<td>未审核</td>
														</c:when>
														<c:when test="${book.bookState==2}">
															<td>已审核</td>
														</c:when>
													</c:choose>
													<td>
														<div class="am-btn-toolbar">
															<div class="am-btn-group am-btn-group-xs">
																<button class="am-btn am-btn-default am-btn-xs am-text-secondary" onClick="turnToBookDetail(${book.bookId})">
																	<span class="am-icon-pencil-square-o"></span> 查看审核
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
											<li class="<c:if test='${!page.hasPreviousPage}'> am-disabled </c:if>"><a href="${pageContext.request.contextPath}/adminBookManager?pageNum=${page.pageNum-1}">«</a></li>
											<c:forEach items="${page.navigatepageNums}" var="p" >
												<li class="<c:if test='${page.pageNum == p}'> am-active </c:if>"><a href="
													${pageContext.request.contextPath}/adminBookManager?pageNum=${p}">${p}</a></li>
											</c:forEach>
											<li class="<c:if test='${!page.hasNextPage}'> am-disabled </c:if>"><a href="${pageContext.request.contextPath}/adminBookManager?pageNum=${page.pageNum+1}">»</a></li>
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
		function turnToBookDetail(bookId) {
			window.location.href = "/YuDongReader/bookDetail?bookId="+bookId;
			window.event.returnValue = false;
		}
		function searchBook(){
			var searchName = $("#search-name").val().trim();
			if(searchName!=""){
				window.location.href = "/YuDongReader/adminSearchBook?searchName="+$.trim(searchName);
				window.event.returnValue = false;
			}else{
				window.location.href = "/YuDongReader/adminSearchBook?searchName="+"all";
				window.event.returnValue = false;
			}
		}
	</script>
</body>
</html>