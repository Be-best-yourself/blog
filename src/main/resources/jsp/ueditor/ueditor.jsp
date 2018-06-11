<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/basePath.jsp"%>
<%@ include file="../../common/css/bootstrap_css.jsp"%>
<%@ include file="../../common/js/bootstrap_js.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title>创作</title>
<link rel="stylesheet" type="text/css" href="${basePath }/res/css/inc.css" />
<%@ include file="../../common/js/jquery.jsp"%>
</head>
<body>
	<div class="body">
	<c:set var="submenu" value="writeMenu"></c:set>
		<%@include file="../include/header.jsp" %>
		<div class="content">
			<%@include file="../include/left.jsp"%>
			<%@include file="./ueditor-content.jsp"%>
		</div>
	</div>
</body>

</html>