<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书上传</title>
</head>
<body>
	<form id="bookUploadForm" action="${pageContext.request.contextPath }/bookUploadController"
		method="post" enctype="multipart/form-data">
		图书名称(不能带符号)：
		<input type="text" name="bookName" id="bookName" />
		<br>
		图书分类：
		<select name="bookClassificationId" id="bookClassificationId">
			<option value="1">小说</option>
			<option value="2">文学</option>
			<option value="3">传记</option>
			<option value="4">历史</option>
			<option value="5">经济</option>
			<option value="6">管理</option>
			<option value="7">励志</option>
		</select>
		<br>
		图书作者：
		<input type="text" name="bookAuthor" id="bookAuthor" />
		<br>
		图书简介：
		<textarea rows="5" name="bookIntroduction" id="bookIntroduction"></textarea>
		<br>
		选择图书(仅限不大于20M的txt文件):
		<input type="file" name="bookFile" width="120px"> 
		<br>
		选择图书封面(仅限尺寸为90x110的图片，不大于20k):
		<input type="file" name="bookimgFile" width="120px"> 
		<br>
		<input type="submit" value="上传">
	</form>
	<hr>
</body>
</html>