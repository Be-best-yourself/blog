<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${classifys }" var="classify">
	<div id="${classify.id }">
		<div class="classify" style="padding-left:${(classify.classifyLevel-1)*10}%">
			<a href="javascript:void(0)"
				onclick="classifyFold('${classify.id}')"><span>+</span> <span>${classify.classifyName }</span>
			</a>
		</div>
	</div>
</c:forEach>
