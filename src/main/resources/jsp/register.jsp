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
<script src="${basePath }res/dragvalidate/drag.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath }res/dragvalidate/drag.css" />
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
	height: 100%;
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

.sendValidateCode{
	cursor: pointer;
    width: 100px;
    border-radius: 10px;
    margin: auto 2px;
    line-height: 2.5;
    display:none; 
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
.drag-pop {
	background-image: -webkit-radial-gradient(center center, ellipse cover, rgba(0, 0, 0, 0.4)
		0px, rgba(0, 0, 0, 0.5) 100%);
	z-index: 999;
	top: 0;
	bottom: 0;
	left: 0;
	right: 0;
	position: fixed;
	display:none;
	opacity: 1;
}

.drag-pop>.drag {
	border: 1px solid rgba(0, 0, 0, .2);
	z-index: 999;
	width: 312px;
	height: 250px;
	margin-left: calc(100%/ 2 - 156px);
	background: #fff;
	position: absolute;
	top: 20%;
}

#msg {
    width: 200px;
    text-align: center;
    font-size: 14px;
    z-index: 999;
    margin-left: calc(50% - 100px);
    background: #fff;
    position: absolute;
    top: calc(20% + 220px);
}

</style>
<script type="text/javascript">
	var validateUserName=false;
	var validatePassword=false;
	var validatePhoneNumCode=false;

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
	
	function testPhoneNum(){
		var phoneReg = /(^1[3|4|5|7|8]\d{9}$)|(^09\d{8}$)/;
		$('#phoneNum').on('input',function() {
			//字母或数字
			if (!phoneReg.test($(this).val())) {
				$('#sendValidateCode').hide();
				$('#validateCode-div').hide();
				$('#phoneNumtip').text("手机格式不正确");
				$('#phoneNumtip').css('visibility','visible');
				$('#phoneNum').focus();
				return;
			}else{
				$('#phoneNumtip').css('visibility','hidden');
				var phoneNum=$(this).val();
				$.get('${basePath }getUserByPhoneNum.json?phoneNum='+phoneNum,function(data){
					if(data.result){
						$('#phoneNumtip').text("此手机号已被注册");
						$('#phoneNumtip').css('visibility','visible');
						$('#phoneNum').focus();
					}else{
						 $('#sendValidateCode').show();
						 $('#validateCode-div').show();
					}			
				});
				
			}
		});
	}
	function testValidateCode(){
		$('#validateCode').on('input',function(){
			$('#validateCodetip').css('visibility','hidden');
		});
		$('#validateCode').blur(function() {
			$.ajax({
				url:'${basePath}compareValidateCode.json',
				async : false,
				data:{'validateCode':$(this).val()},
				type:'post',
				dataType:'json',
				success:function(data){
					if (!data.result) {
						$('#validateCodetip').css('visibility','visible');
						return;
					}else{
						validatePhoneNumCode=true;
					}
				}
			});
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
	
	
	//展示拖动验证码
	function showdragValidate(token) {
		jigsaw.init(document.getElementById('drag'), function() {
			token.split("-");
			$.ajax({
				url : '${basePath}sendSMS.json',
				data : {
					'token' : token.split("-")[0],
					'phoneNum' : token.split("-")[1]
				},
				type:'post',
				dataType:'json',
				success:function(data){
					if (data.result) {
						document.getElementById('msg').innerHTML = '发送成功！';
						setTimeout(function() {
							$('#drag-pop').hide();
							$('#drag').empty();
						}, 1000);
						timing(10, $('#sendValidateCode'));
					}else{
						document.getElementById('msg').innerHTML = '您反应时间有点慢，请重新验证！';
						setTimeout(function() {
							$('#drag-pop').hide();
							$('#drag').empty();
						}, 2000);
					}
				}
			});
			
		
		}, function() {
			alert("验证失败");
		});
		$('#msg').html("发送验证码");
		$('#drag-pop').show(500);
	}

	//定时器
	function timing(time, obj) {
		obj.val(time + "秒后重新发送");
		obj.attr('disabled', 'disabled');
		setTimeout(function() {
			if (time <= 0) {
				obj.val("重新发送");
				obj.removeAttr('disabled');
				return;
			} else {
				time--;
				timing(time, obj);
			}

		}, 1000);
	}

	//从服务器得到令牌
	function getToken() {
		var token = '';
		$.ajax({
			url : '${basePath}getToken.json',
			async : false,
			data : {
				'tokenid' : $('#phoneNum').val()
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				token = data.token;
			}
		});
		return token;
	}
	
$('document').ready(function() {
	testUserName();
	testPhoneNum();
	testValidateCode();
	testPassword();
	$('#registerBtn').click(function() {
		var md5Password = $.md5($("#password").val().trim());
		if (!validateUserName) {
			$('#userNametip').text("用户名由4-16位字母或数字组合，但不能为纯数字");
			$('#userNametip').css('visibility','visible');
			$('#userName').focus();
			return;
		}
		if (!validatePhoneNumCode) {
			$('#passwordtip').css('visibility','visible');
			return;
		}
		if (!validatePassword) {
			$('#passwordtip').text("密码由6-12位字母或数字组成");
			$('#passwordtip').css('visibility','visible');
			$('#password').focus();
			return;
		}
		
	if (validatePassword&& validateUserName) {
		
		$.ajax({
			url : '${basePath}user/register.json',
			data : {
				'userName' : $('#userName').val(),
				'password' : md5Password,
				'phoneNum':$('#phoneNum').val(),
				'validateCode':$('#validateCode').val()
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					setTimeout(function() {
						window.location.replace("${basePath}user/toLogin");
					},3000);
					alert("注册成功，三秒后将跳到登录界面");
				}else{
					alert("");
				}
			}
		});
	}
});

	$("#sendValidateCode").click(function() {
		var token= getToken();
		showdragValidate(token);
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
			
			<span class="tip" id="phoneNumtip">手机格式不正确</span>
			<div class="input-div">
				<img src="${basePath }/res/img/12.png" /> <input id="phoneNum"
					type="text" placeholder="手机号" name="phoneNum">
					<input id="sendValidateCode" class="sendValidateCode" type="submit" value="发送验证码" />
			</div>
			
			
			<span class="tip" id="validateCodetip">验证码错误或已过期</span>
			<div class="input-div" style="display:none" id="validateCode-div">
				<img src="${basePath }/res/img/12.png" /> <input id="validateCode"
					type="text" placeholder="手机验证码" name="validateCode">
			</div>
			
			<span class="tip" id="passwordtip">密码由6-12位字母或数字组成</span>
			<div class="input-div">
				<img src="${basePath }/res/img/12.png" /> <input id="password"
					type="password" placeholder="密码" name="passWord">
			</div>
			
			<div class="loginBtn-div">
				<button id="registerBtn">注 册</button>已有帐号？<a href="${basePath }user/toLogin">去登录</a>
			</div>
		</div>
		
		<div class="drag-pop" id="drag-pop">
			<div id="drag" class="drag"></div>
  			<div id="msg">提示</div>
		</div>
	</div>
</body>
</html>