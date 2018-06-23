<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../common/basePath.jsp"%>
<table>
	<thead>
		<tr>
			<td style="width: 40%">名称</td>
			<td style="width: 30%">标签</td>
			<td style="width: 15%">创建时间</td>
			<td style="width: 15%">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${blogs }" var="blog">
			<tr onclick="javascript:void(0)">
				<td><a href="${basePath }/blog/${blog.id }" target="_blank">${blog.blogName }</a></td>
				<td>${blog.blogKeyword }</td>
				<td><fmt:formatDate value="${blog.blogCreateTime }" type="date"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td><a href="javascript:void(0)" onclick="popShow('${blog.id}')">发布</a>
					<a href="javascript:void(0)" onclick="editerBlog('${blog.id}')">编辑</a>
					<a href="javascript:void(0)" onclick="recycleBlog('${blog.id}')">删除</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>