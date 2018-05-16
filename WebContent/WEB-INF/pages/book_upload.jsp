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
		选择文件:
		<input type="file" name="book" width="120px"> 
		<br>
		选择文件:
		<input type="file" name="bookimg" width="120px"> 
		<br>
		<input type="submit" value="上传">
			
	</form>
	<hr>
	<form action="${pageContext.request.contextPath }/bookDownController"
		method="get">
		<input type="submit" value="下载">
	</form>
	<input type="file" id="UpFile_Photo3" runat="server" name="UpFile_Photo3"   
	   size="35" onpropertychange="CheckFile(this.value,this)">大图<br />  
</body>
<script type="text/javascript">
	//判断图片类型  
	var f=document.getElementById("UpFile_Photo3").value;  
		if(f==""){ 
			alert("请上传图片");
			return false;
		}  
		else{  
			if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f)){  
		  		alert("图片类型必须是.gif,jpeg,jpg,png中的一种")  
		  	return false;  
		}  
	}  
		
	function CheckFile(f,p)  
	{  
		//判断图片尺寸  
		var img=null;  
		img=document.createElement("img");  
		document.body.insertAdjacentElement("beforeend",img);  
		img.style.visibility="hidden";   
		img.src=f;  
		var imgwidth=img.offsetWidth;  
		var imgheight=img.offsetHeight;  
		if(p.name=="UpFile_Photo1")  
		{  
			if(imgwidth!=68||imgheight!=68)  
			{  
			alert("小图的尺寸应该是68x68");  
			}  
		}  
		if(p.name=="UpFile_Photo2")  
		{  
			if(imgwidth!=257||imgheight!=351)  
			{  
			alert("中图的尺寸应该是257x351");  
			}  
		}  
		if(p.name=="UpFile_Photo3")  
		{  
			if(imgwidth!=800||imgheight!=800)  
			{  
				alert("大图的尺寸应该是800x800");  
			}  
		}  
		//判断图片类型  
		if(!/\.(gif|jpg|jpeg|bmp)$/.test(f))  
		{  
			alert("图片类型必须是.gif,jpeg,jpg,bmp中的一种")  
			return false;  
		}  
		return true;  
	} 
</script>
</html>