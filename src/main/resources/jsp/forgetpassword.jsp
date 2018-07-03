<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/basePath.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../common/js/jquery.jsp"%>
<script src="${basePath }res/dragvalidate/drag.js"></script>
<link rel="stylesheet" type="text/css" href="${basePath }res/dragvalidate/drag.css" />
<script src="${basePath}common/js/jquery.md5.js"></script>
<link rel="stylesheet" href="${basePath }common/css/font-awesome.css">
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
.input-div>input {
	border: none;
	color: #4f4f4f;
	width: calc(100% - 50px);
	line-height: 40px;
	outline: none;
}

.forgetBtn-div {
	text-align: center;
}
.forgetBtn-div>a {
	line-height: 3;
	/* text-decoration: none; */
	margin-top: 3px;
}


.forgetBtn-div>button {
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
	var validatePhoneNumCode=false;
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
						 $('#sendValidateCode').show();
						 $('#validateCode-div').show();
					}else{
						 $('#phoneNumtip').text("此手机号未注册");
						 $('#phoneNumtip').css('visibility','visible');
						 $('#phoneNum').focus();
					}			
				});
			}
		});
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
	
	//展示拖动验证码
	function showdragValidate(token) {
		$('#msg').html("发送验证码");
		$('#drag-pop').show(500);
		jigsaw.init(document.getElementById('drag'), function() {
			token.split("-");
			$.ajax({
				url : '${basePath}sendForgetPasswordsms.json',
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
						timing(60, $('#sendValidateCode'));
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
			console.log("验证失败");
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

	
	$(document).ready(function(){
		testPhoneNum();
		testValidateCode();
		$("#sendValidateCode").click(function() {
			var token= getToken();
			showdragValidate(token);
		});
		
		$('.drag-pop').click(function(){
			$('#drag-pop').hide();
			$('#drag').empty();
		});
		$('#forgetBtn').click(function(){
			if (!validatePhoneNumCode) {
				$('#validateCodetip').css('visibility','visible');
				return;
			}
			var md5Password = $.md5($("#password").val().trim());
			$.ajax({
				url : '${basePath}user/resetpwd.json',
				data : {
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
						alert("密码重置成功，三秒后将跳到登录界面");
					}else{
						alert("重置失败,验证码可能过期，请重新操作");
					}
				}
			});
			
		});
	});
</script>
<title>重设密码？</title>
<body>
<div class="login-body">
			<div class="login-title">
				<h1>重设密码？</h1>
			</div>
			
			<span class="tip" id="phoneNumtip">手机格式不正确</span>
			<div class="input-div">
				<i class="fa fa-mobile-phone fa-2x" aria-hidden="true" style="margin:12px"></i>
				<input id="phoneNum" type="text" placeholder="手机号" name="phoneNum">
			</div>
			
			
			<span class="tip" id="validateCodetip">验证码错误或已过期</span>
			<div class="input-div" style="display:none" id="validateCode-div">
				<i class="fa fa-sort-numeric-asc fa-1x" aria-hidden="true" style="margin:12px"></i>
				<input id="validateCode" type="text" placeholder="手机验证码" name="validateCode">
				<input id="sendValidateCode" class="sendValidateCode" type="submit" value="发送验证码" />
			</div>
			
			<span class="tip" id="passwordtip">密码由6-12位字母或数字组成</span>
			<div class="input-div">
				<i class="fa fa-unlock-alt fa-2x" aria-hidden="true" style="margin:12px"></i>
				<input id="password" type="password" placeholder="请重设密码" name="passWord">
			</div>
			
			<div class="forgetBtn-div">
				<button id="forgetBtn">确认修改</button><a href="${basePath }">返回首页</a>
			</div>
		</div>
		<div class="drag-pop" id="drag-pop">
			<div id="drag" class="drag"></div>
  			<div id="msg">提示</div>
		</div>
</body>
</html>