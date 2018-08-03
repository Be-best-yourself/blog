<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="header">
	<div class="logo">
		<a href="${basePath }"><img alt="logo" src="${basePath }/res/img/logo.png"></a>
	</div>
	<div class="user">
		<div class="userLogo">
			<img src="${userLogo }"
				onerror="javascript:this.src=
						'${basePath }/res/img/default-userlogo.png'">
		</div>
		<div class="userinfo">${userName }</div>
		<div class="logout">
			<a href="${basePath }/logout">退出</a>
		</div>
	</div>
	<div class="clear"></div>
</div>