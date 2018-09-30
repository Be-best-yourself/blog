<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../../common/basePath.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<%@include file="../../common/css/bootstrap_css.jsp"%>
<%@include file="../../common/js/jquery.jsp"%>
<%@include file="../../common/js/bootstrap_js.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../../common/js/jquery.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${basePath }/res/css/inc.css" />
<title>我要秀</title>
</head>
<body>
	<div class="header">
		<div class="logo">
			<a href="${basePath }"><img alt="logo"
				src="${basePath }/res/img/logo.png"></a>
		</div>
		<div class="user">
			<c:if test="${userName==null }">
				<a style="font-size: 20px; line-height: 4;"
					href="${basePath }/user/toLogin">登录</a>
			</c:if>
			<c:if test="${userName!=null }">
				<div class="userLogo">
					<img src="${userLogo }"
						onerror="javascript:this.src=
						'${basePath }/res/img/default-userlogo.png'">
				</div>
				<div class="userinfo">
					<a href="${basePath }editor/toUEditor"> ${userName } </a>
				</div>
				<div class="logout">
					<a href="${basePath }/logout">退出</a>
				</div>
			</c:if>
		</div>
		<div class="clear"></div>
	</div>
	<div class="container">
		<br>
		<table class="table table-bordered">
			<caption>所有已发布文章</caption>
			<thead>
				<tr>
					<td>文章名</td>
					<td>关键字</td>
					<td>分类</td>
					<td>作者</td>
					<td>创建时间</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${blogs }" var="blog">
					<tr>
						<td><a href="${basePath }article/${blog.id}" target="_blank"
							title="查看文章">${blog.blogName }</a></td>
						<td>${blog.blogKeyword }</td>
						<td>${iClassifyService.getById(blog.blogClassifyId).classifyName }</td>
						<td>${iUserService.getById(blog.blogUserId).userName }</td>
						<td><fmt:formatDate value="${blog.blogCreateTime }"
								pattern="yyyy年MM月dd日" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>