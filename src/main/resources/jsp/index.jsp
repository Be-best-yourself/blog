<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@include file="../common/basePath.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/css/bootstrap_css.jsp"%>
<%@include file="../common/js/jquery.jsp"%>
<%@include file="../common/js/bootstrap_js.jsp"%>
<title>模板</title>
<style type="text/css">
header {
	height: 50px;
	border-bottom: 1px solid #eee;
}

.row .row [class*=col-] {
	padding-bottom: 15px;
	padding-top: 15px;
	text-decoration: none;
	text-align: center;
}

.row .row a:hover {
	background: #eee;
}

.row>col-md-1 {
	font-size: 18px;
	color: #563d7c;
}

.pop {
	background-image: -webkit-radial-gradient(center center, ellipse cover, rgba(0, 0, 0, 0.4)
		0px, rgba(0, 0, 0, 0.5) 100%);
	z-index: 99;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: fixed;
	opacity: 1;
	display: none;  
}

.pop .dialog {
	z-index: 990;
	width: 450px;
	height: 80%;
	margin-left: calc(100%/ 2 - 225px);
	background: #fff;
	position: absolute;
	top: 10%;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginBtn").click(function() {
			alert($("input[name=userName]").val());
			$.ajax({
				url : 'login.json',
				data : {
					'userName' : $("input[name=userName]").val(),
					'userPassword' : $("input[name=password]").val()
				},
				type:'post',
				datatype:'json',
				success:function(data){
					alert(data)
				}
			});
		});
		$("#btn").on("click",function(){
			alert($("#file").val());
		});
		

	})
</script>
</head>
<body>
	<header>
		<div class="container-fluid">
			<div class="row">
				<div
					class="col-lg-1 col-md-1 col-sm-1 col-xs-1 col-lg-offset-1 col-md-offset-1">
					<img
						src="data:image/jpeg;base64,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"
						width="40px" height="40px" alt="logo" class="img-circle">
				</div>
				<div
					class="col-lg-9 col-md-9 col-sm-10 col-xs-10 col-lg-offset-1 col-md-offset-1 col-sm-offset-1 col-xs-offset-1 ">
					<div class="row">
						<a href="#" class="col-lg-2 col-md-2 col-sm-3 col-xs-3">电脑</a> <a
							href="#" class="col-lg-2 col-md-2 col-sm-3 col-xs-3">情感</a> <a
							href="#" class="col-lg-2 col-md-2 col-sm-3 col-xs-3">技术</a> <a
							href="#" class="col-lg-2 col-md-2 col-sm-3 col-xs-3">学习</a>
					</div>
				</div>
			</div>
		</div>
	</header>
	<article class="container">
		<shiro:hasRole name="admin">admin</shiro:hasRole>
		
		<form action="${basePath }upload/file" method="post" enctype="multipart/form-data">
		<input id="file" type="file" name="uploadFile" multiple="multiple"/>
		<input id="btn" type="text" value="上传">
		</form>
		
		
	</article>
	<footer>脚</footer>
</body>
</html>