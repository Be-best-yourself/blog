<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../common/basePath.jsp"%>
<table>
	<thead>
		<tr>
			<td style="width: 50%">名称</td>
			<td style="width: 38%">创建时间</td>
			<td style="width: 12%">操作</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${blogs }" var="blog">
			<tr onclick="javascript:void(0)">
				<td><a href="${basePath }/blog/${blog.id }" target="_blank">${blog.blogName }</a></td>
				<td><fmt:formatDate value="${blog.blogCreateTime }" type="date"
						pattern="yyyy-MM-dd HH:mm" /></td>
				<td>
					<a href="javascript:void(0)" onclick="editerBlog('${blog.id}')">编辑</a> 
					<a href="javascript:void(0)" onclick="recycleBlog('${blog.id}')">删除</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
