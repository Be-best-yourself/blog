<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/basePath.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/js/jquery.jsp"%>
<script src="${basePath}common/js/jquery.md5.js"></script>
<title>用户登录</title>
<style type="text/css">
html body {
	font-size: 14px;
	line-height: 1.42857143;
	color: #333;
	background-color: #eee;
	width: 99%;
	height: 100%;
}

.body {
	width: 99%;
	height: 100%;
}

.login-body {
	width: 340px;
	height: 380px;
	padding: 0 20px 20px;
	border-radius: 8px;
	background: #fff;
	box-shadow: 1px 1px 2px #999;
	margin: 70px auto;
}

.login-body>.login-title {
	text-align: center;
	padding-top: 10px;
	color: #286c8f;
}

.tip {
	color: red;
	height: 20px;
	visibility: hidden;
}

.input-div {
	width: 98%;
	border: 1px solid #eee;
	display: inline-flex;
}

.input-div>img {
	width: 50px;
	height: 50px;
}

.input-div>input {
	border: none;
	color: #4f4f4f;
	width: calc(100% - 50px);
	line-height: 40px;
	outline: none;
}

.remeberMe-div {
	margin-top: 3px;
}

.remeberMe-div>a {
	float: right;
	text-decoration: blink;
	margin-right: 5px;
}

.loginBtn-div {
	text-align: center;
}

.loginBtn-div>a {
	line-height: 3;
	/* text-decoration: none; */
	margin-top: 3px;
}

.loginBtn-div>button {
	width: 98%;
	background: #286c8f;
	line-height: 40px;
	border: none;
	margin-top: 30px;
	font-size: 20px;
	color: #fff;
	border-radius: 4px;
}
</style>
<script type="text/javascript">
	$('document').ready(function() {
		$('#loginBtn').click(function() {
			var $userName = $('#userName');
			var $password = $('#password');
			if ($userName.val() == null || $userName.val() == '') {
				$userName.focus();
				$('#usernameTip').css('visibility', 'visible');
				return;
			}
			if ($password.val() == null || $password.val() == '') {
				$password.focus();
				$('#passwordTip').css('visibility', 'visible');
				return;
			}
			$.ajax({
				url : '${basePath}user/login.json',
				data : {
					'userName' : $userName.val(),
					'userPassword' : $.md5($password.val().trim()),
					'rememberMe' : $("#rememberMe").is(':checked')
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if(data.result=="true"){
						window.location.replace("${basePath}editor/toUEditor");
					}else{
						$('#usernameTip').css('visibility', 'visible');
						$('#usernameTip').text(data.result);
					}
				}

			});
		});

		$('#userName').on('input', function() {
			$('#usernameTip').css('visibility', 'hidden');
		});
		$('#password').on('input', function() {
			$('#passwordTip').css('visibility', 'hidden');
		});
	});
</script>
</head>

<body>
	<div>
		<div class="login-body">
			<div class="login-title">
				<h1>登录</h1>
			</div>
			<span class="tip" id="usernameTip">*用户名不能为空哦</span>
			<div class="input-div">
				<img
					src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530013540674&di=178685c0cbee27f65f850ce2d96d5300&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b0645a112c1ea80121985c0ea3d0.jpg%401280w_1l_2o_100sh.jpg" />
				<input id="userName" type="text" placeholder="用户名" name="userName">
			</div>
			<span class="tip" id="passwordTip">密码不能为空哦</span>
			<div class="input-div">
				<img src="${basePath }/res/img/12.png" /> <input id="password"
					type="password" placeholder="密码" name="passWord">
			</div>
			<div class="remeberMe-div">
				<input type="checkbox" id="rememberMe" name="rememberMe" />记住我 <a href="#">忘记密码？</a>
			</div>
			<div class="loginBtn-div">
				<button id="loginBtn">登 录</button>
				还没有帐号？<a href="${basePath }user/toRegister">去注册</a>
			</div>
		</div>
	</div>
</body>
</html>