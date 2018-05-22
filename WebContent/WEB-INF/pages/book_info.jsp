<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow: hidden;">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>图书信息</title>
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
		<link href="static/css/jquery.Jcrop.min.css" rel="stylesheet" type="text/css">
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
								<li class="active">图书信息</li>
							</ol>
						</div>
						<div class="forms-main">
							<h2 class="inner-tittle">图书信息展示页</h2>
							<div class="graph-form">
								<div class="form-body">
									<form>
										<div class="form-group">
											<label for="bookName">图书封面</label><br>
											<a href="#" data-toggle="modal" data-target="#uploadImgModal">
												<img id="pic-show"
												src="static/images/1.jpg"
												alt="点击修改" />
												<label>点击修改</label>
											</a>
										</div>
										<div class="form-group">
											<label for="bookName">图书名称</label>
											<input type="text" class="form-control" id="bookName" placeholder="图书名称">
										</div>
										<div class="form-group">
											<label for="bookAuthor">图书作者</label>
											<input type="email" class="form-control" id="bookAuthor" placeholder="图书作者">
										</div>
										<div class="form-group">
											<label for="bookAuthor">图书简介</label>
											<input type="email" class="form-control" id="bookAuthor" placeholder="图书简介">
										</div>
										<button type="submit" class="btn btn-default">修改</button> 
									</form>
								</div>	
							</div>
						</div>
						<div class="forms-inner">
							<div class="set-1">
								<div class="clearfix"> </div>
							</div>
						</div>
					</div>
				</div>
				<footer>
					<p>Copyright © 2016.Company name All rights reserved.More Templates.</p>
				</footer>
			</div>
		</div>
		<div class="sidebar-menu">
			<header class="logo">
				<a href="#"> <span id="logo"> <h1>鱼洞阅读</h1></span>
				</a>
			</header>
			<div style="border-top:1px solid rgba(69, 74, 84, 0.7)"></div>
			<div class="down">
				<a href="${pageContext.request.contextPath}/myProfile"><img src="static/images/admin.jpg"></a>
				<a href="${pageContext.request.contextPath}/myProfile"><span class=" name-caret">这是你的用户名</span></a>
				<p>昵称：这是你的昵称</p>
				<ul>
					<li>
						<a class="tooltips" href="${pageContext.request.contextPath}/myProfile"><span>个人信息</span><i class="lnr lnr-user"></i></a>
					</li>
					<!-- <li>
						<a class="tooltips" href="#"><span>设置</span><i class="lnr lnr-cog"></i></a>
					</li> -->
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
		
		<!-- 模态框 -->
		<div class="modal fade" id="uploadImgModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">上传头像</h4>
					</div>
					<div class="modal-body">
						<label for="src-input">选择图片：</label><input id="src-input"
							type="file" />
						<div class="img-wrapper">
							<img id="img-show" src=" " />
						</div>
						<div class="pre-wrapper">
							<canvas class="pre-show" width="200px" height="200px"></canvas>
						</div>
						<div class="btn-wrapper"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button id="upload-btn" type="button" class="btn btn-primary ">确定上传</button>
					</div>
				</div>
			</div>
		</div>
		
		<script src="static/js/jquery.min.js"></script>
		<script src="static/js/jquery.Jcrop.min.js"></script>
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
			
			var jcropApi;
			var srcImg = $("#img-show")[0];
			var srcInput = $("#src-input");
			$("#img-show").Jcrop({
				allowSelect : true,
				allowMove : true,
				allowResize : true,
				baseClass : 'jcrop',
				bgColor : 'black',
				bgOpacity : 0.6,
				bgFade : true,
				aspectRatio : 1,
				borderOpacity : 0.4,
				drawBorders : true,
				dragEdges : true,
				boxWidth : 300,
				fadeTime : 400,
				animationDelay : 20,
				swingSpeed : 3,
				onChange : getPosition
			}, function() {
				jcropApi = this;
			});
			srcInput.change(function() {
				if (!this.files[0].name.match(/.jpg|.gif|.png|.bmp/i)) {
					alert("你选择的文件类型不被支持！");
					return;
				}
				var reader = new FileReader();
				reader.readAsDataURL(this.files[0]);
				reader.onload = function() {
					srcImg.src = this.result;
					jcropApi.setImage(this.result);
					reader = null;
				};
			});
	
			function getPosition(p) {
				preShow(p.x, p.y, p.w, p.h);
			}
	
			var canvas = $(".pre-show")[0];
			var cxt = canvas.getContext("2d");
			function preShow(x, y, w, h) {
				$(".pre-wrapper").show();
				$("#upload-btn").show();
	
				var img = new Image();
				img.onload = function() {
					cxt.drawImage(img, x, y, w, h, 0, 0, 200, 200);
				};
				img.src = srcImg.src;
			}
	
			$("#upload-btn").click(function() {
				alert("upload image");
				var src = canvas.toDataURL("image/jpeg");
				$.ajax({
					url : "uploadImage",
					type : "POST",
					data : {
						img : src
					},
					dataType : "json",
					success : function(data) {
						/* alert("上传成功！文件名："+data); */
						$("#pic-show")[0].src = src;//显示裁剪后图片
	
						resetImgInput();
					}
				});
			});
			function resetImgInput() {
				$("#src-input").prop("value", "");//清除已选input文件
				canvas.height = canvas.height;//清除画布
				$(".pre-wrapper").hide();//隐藏预览框
				$("#uploadImgModal").modal("hide");//隐藏模态框
			}
		</script>
		<!--js -->
		<script src="static/js/jquery.nicescroll.js"></script>
		<script src="static/js/scripts.js"></script>
		<script src="static/js/bootstrap.min.js"></script>
	</body>
</html>