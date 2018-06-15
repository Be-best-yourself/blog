<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../../common/basePath.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回收站</title>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
<%@ include file="../../common/js/jquery.jsp"%>
<%@ include file="../../common/css/bootstrap_css.jsp"%>
<%@ include file="../../common/js/bootstrap_js.jsp"%>
<style type="text/css">
</style>
<script type="text/javascript">
	$(document).ready(function() {

	});
</script>
</head>
<body>
	<div class="body">
		<c:set var="submenu" value="recycleMenu"></c:set>
		<%@include file="../include/header.jsp"%>
		<div class="content">
			<%@include file="../include/left.jsp"%>
			<div class="container-right">
			
			</div>
		</div>
	</div>
</body>
</html>