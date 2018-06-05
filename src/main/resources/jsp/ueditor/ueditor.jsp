<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/basePath.jsp"%>
<!DOCTYPE HTML>
<html>

<head>
<meta charset="UTF-8">
<title>write</title>
<style type="text/css">
body, .body {
	background: #eee;
	margin: 0px;
}

.container-fluid {
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
	border-top: 1px solid #eee;
	background: #fff;
}
</style>
</head>
<body>
	<div class="body">
		<%@include file="./header.jsp" %>
		<div class="container-fluid">
			<%@include file="./container-left.jsp"%>
			<%@include file="./container-right.jsp"%>
		</div>
	</div>
</body>

</html>