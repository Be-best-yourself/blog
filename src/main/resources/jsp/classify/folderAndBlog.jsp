<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table>
	<thead>
		<tr>
			<td style="width: 50%">名称</td>
			<td style="width: 30%">类形</td>
			<td style="width: 20%">创建时间</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${classifys }" var="classify">
			<tr onclick="classifyFold('${classify.id}')">
				<td style="width: 50%">${classify.classifyName }</td>
				<td style="width: 30%">文件夹</td>
				<td style="width: 20%"><fmt:formatDate
						value="${classify.classifyCreateTime }" type="date"
						pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>
		<c:forEach items="${blogs }" var="blog">
			<tr onclick="javascript:void(0)">
				<td>${blog.blogName }</td>
				<td>文章</td>
				<td><fmt:formatDate value="${blog.blogCreateTime }" type="date"
						pattern="yyyy-MM-dd HH:mm" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
