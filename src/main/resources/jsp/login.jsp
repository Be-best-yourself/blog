<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/css/bootstrap_css.jsp"%>
<%@include file="../common/js/jquery.jsp"%>
<%@include file="../common/js/bootstrap_js.jsp"%>
<title>用户登录</title>
</head>

<body>
	<form action="login" method="post">
		userName:<input type="text" name="userName" value="${user.userName }">
		<br>
		<br> password:<input type="password" name="userPassword"
			value="${user.userPassword }"> <br>
		<br> <input type="submit" value="登录">
	</form>

</body>
</html>