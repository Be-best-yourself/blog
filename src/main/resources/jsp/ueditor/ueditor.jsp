<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/basePath.jsp"%>
<%@include file="/ueditor/ueditor.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=utf-8" />
<meta charset="UTF-8">
<%@ include file="../../common/css/bootstrap_css.jsp"%>
<%@ include file="../../common/js/jquery.jsp"%>
<%@ include file="../../common/js/bootstrap_js.jsp"%>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<title>创作</title>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
<%@ include file="../../common/js/jquery.jsp"%>
<%@ include file="../../common/select2/select2.jsp"%>
</head>
<body>
	<div class="body">
		<c:set var="submenu" value="writeMenu"></c:set>
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/left.jsp"%>
			<%@include file="./ueditor-content.jsp"%>
		</div>
	</div>
	<div id="editornone" style="display: none;">${blogText }</div>
	<%@include file="../../common/loading.jsp"%>
</body>

</html>