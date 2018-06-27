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
<title>注册</title>
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
	var validateUserName=false;
	var validatePassword=false;
	var validatePhoneNum=false;

	function testUserName(){
		$('#userName').blur(function() {
			var userName=$(this).val();
			//不能为纯数字
			var patrn = /^.*[^\d].*$/;
			//字母或数字
			var userNamePattern = /^[a-zA-Z0-9]{4,16}$/;
			if(userNamePattern.test(userName)&&patrn.test(userName)){
			}else{
				$('#userNametip').text("用户名由4-16位字母或数字组合，但不能为纯数字");
				$('#userNametip').css('visibility','visible');
				$('#userName').focus();
				return;
			}
				
			$.get('${basePath}getUserByUserName.json?userName='+userName,function(data){
				if(data.result){
					$('#userNametip').text("用户名已被注册");
					$('#userNametip').css('visibility','visible');
					$('#userName').focus();
				}else{
					$('#userNametip').css('visibility','hidden');
					validateUserName=true;
				}			
			});
		});
		$('#userName').on('input',function(){
			$('#userNametip').css('visibility','hidden');
		});
	}
	
	function testPassword(){
		$('#password').on('input',function(){
			$('#passwordtip').css('visibility','hidden');
		});
		$('#password').blur(function() {
			//字母或数字
			var passwordPattern = /^[a-zA-Z0-9]{6,12}$/;
			if (!passwordPattern.test($(this).val())) {
				$('#passwordtip').text("密码由6-12位字母或数字组成");
				$('#passwordtip').css('visibility','visible');
				return;
			}else{
				validatePassword=true;
			}
		});
	}
	$('document').ready(function() {
		testUserName();
		testPassword();
		$('#registerBtn').click(function(){
			var md5Password=$.md5($("#password").val().trim());
			if (validatePassword&&validateUserName) {
				$.ajax({
					url:'${basePath}user/register.json',
					data:{'userName':$('#userName').val(),'password':md5Password},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result){
							setTimeout(function(){
								window.location.replace("${basePath}user/toLogin");
							},3000);
							alert("注册成功，三秒后将跳到登录界面");
						}
					}
				});
			}			
		});
		
	});
</script>
</head>

<body>
	<div>
		<div class="login-body">
			<div class="login-title">
				<h1>注册</h1>
			</div>
			<span class="tip" id="userNametip">用户名已存在</span>
			<div class="input-div">
				<img
					src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530013540674&di=178685c0cbee27f65f850ce2d96d5300&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01b0645a112c1ea80121985c0ea3d0.jpg%401280w_1l_2o_100sh.jpg" />
				<input id="userName" type="text" placeholder="用户名" name="userName">
			</div>
			<span class="tip">a</span>
			<div class="input-div">
				<img src="${basePath }/res/img/12.png" /> <input id="phoneNum"
					type="text" placeholder="手机号" name="phoneNum">
			</div>
			<span class="tip" id="passwordtip">密码由6-12位字母或数字组成</span>
			<div class="input-div">
				<img src="${basePath }/res/img/12.png" /> <input id="password"
					type="password" placeholder="密码" name="passWord">
			</div>
			<div class="loginBtn-div">
				<button id="registerBtn">注 册</button>
				已有帐号？<a href="${basePath }user/toLogin">去登录</a>
			</div>
		</div>
	</div>
</body>
</html>