<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../../common/basePath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../../common/css/bootstrap_css.jsp"%>
<%@include file="../../common/js/jquery.jsp"%>
<%@include file="../../common/js/bootstrap_js.jsp"%>
<title>${blogName }</title>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
</head>
<body>
<%@include file="../include/header.jsp" %>
	<div class="container">
		<div>${blogText }</div>
	</div>
</body>
</html>