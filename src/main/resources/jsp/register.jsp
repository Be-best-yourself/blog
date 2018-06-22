<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/css/bootstrap_css.jsp"%>
<%@include file="../common/js/jquery.jsp"%>
<%@include file="../common/js/bootstrap_js.jsp"%>
<title>用户注册</title>
</head>
<body>
<form action="user/register" method="post">
	userName:<input type="text" name="userName">
	<br><br>
	password:<input type="password" name="userPassword">
	<br><br>
	<input type="submit" value="submit">

</form>
<style>
.pop {
	background-image: -webkit-radial-gradient(center center, ellipse cover, rgba(0, 0, 0, 0.4)
		0px, rgba(0, 0, 0, 0.5) 100%);
	z-index: 999999;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: fixed;
	opacity: 1;
	/* 	display: none;
 */
}

.pop .dialog {
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: 6px;
	z-index: 990;
	width: 450px;
	height: 80%;
	margin-left: calc(100%/ 2 - 225px);
	background: #fff;
	position: absolute;
	top: 10%;
}

.pop .dialogTitle {
	font-size: 18px;
	margin-left: 430px;
}

.pop .dialogBody {
	border-top: 1px solid #eee;
	padding-top: 15px;
	padding-left: 15px;
}

.dialogBody>form>input {
	padding: 0 16px;
	border: none;
	background-color: #efefef;
	color: #4f4f4f;
	width: 300px;
	line-height: 40px;
	margin-top: 10px;
}
</style>

<script type="text/javascript">
	function popClose() {
		$(".pop").hide();
	}
	function popShow() {
		$(".pop").show();
		/* $(".dialogBody").empty();
		$(".dialogBody").html(html); */
	}
</script>
<div class="pop">
	<div class="dialog">
		<div class="dialogTitle">
			<span><a href="javascript:void(0)" onclick="popClose()">×</a></span>
		</div>
		<div class="dialogBody">
			<form>
			</form>
		</div>
	</div>
</div>

</body>
</html>