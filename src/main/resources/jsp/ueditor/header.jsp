<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--为方便查找修改，将css文件碎片化-->
<style>
.header {
	width: 100%;
	height: 72px;
	min-width: 1000px;
	background: #fff;
	text-align: center;
}

.logo {
	float: left;
	margin-left: 50px;
}

.logo>img {
	width: 50px;
	height: 50px;
	margin-top: 10px;
	border-radius: 20px
}

.user {
	float: right;
	width: 25%;
	height: 100%
}

.user>.userLogo>img {
	float: left;
	width: 50px;
	height: 50px;
	margin-top: 10px;
	border-radius: 20px
}

.user>.userinfo {
	font-family: -webkit-pictograph;
	font-size: 16px;
	float: left;
	margin-left: 10px;
	margin-top: 25px;
}

.user>.logout {
	float: right;
	margin-right: 40px;
	margin-top: 20px;
}

.user>.logout>a {
	color: #dbdbdb;
	font-size: 14px;
	padding-left: 8px;
	border-left: 1px solid #dbdbdb;
	text-decoration: none
}

.clear {
	height: 0px;
	font-size: 0;
	clear: both;
}
</style>
<div class="header">
	<div class="logo">
		<img alt="logo" class="img-circle"
			src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528204040354&di=af34011dd797050a230768ce32517a77&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dca3903ed888ba61ecbe3c06c295dfd7f%2Fa9d3fd1f4134970a4b4215089fcad1c8a7865de7.jpg">
	</div>
	<div class="user">
		<div class="userLogo">
			<img src="${userLogo }"
				onerror="javascript:this.src=
						'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1528204040354&di=af34011dd797050a230768ce32517a77&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%253Dshijue1%252C0%252C0%252C294%252C40%2Fsign%3Dca3903ed888ba61ecbe3c06c295dfd7f%2Fa9d3fd1f4134970a4b4215089fcad1c8a7865de7.jpg'">
		</div>
		<div class="userinfo">${userName }</div>
		<div class="logout">
			<a href="${basePath }/logout">退出</a>
		</div>
	</div>
	<div class="clear"></div>
</div>