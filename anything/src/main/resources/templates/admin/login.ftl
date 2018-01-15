<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>login</title>
<link rel="stylesheet" type="text/css" href="/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/css/component.css" />
<script type="text/javascript" src="/js/jquery-3.2.1.min.js"></script>
<style>
.code{
	float:left;
	width:100px;
}
.code{
	float:left;
	margin-top:0px;
}
.rememberMeClass{
	height: 26px;
	padding: 0 25px;
	margin-bottom: 30px;
	border-radius: 50px;
	position: relative;
	border: rgba(255,255,255,0.2) 2px solid !important;
}
.checkClass{
	margin:15px;
	font: 14px "microsoft yahei",Helvetica,Tahoma,Arial,"Microsoft jhengHei";
    font-family: "microsoft yahei", Helvetica, Tahoma, Arial, "Microsoft jhengHei";
}
</style>
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>欢迎你</h3>
						<form action="#" name="f" method="post" class='form-inline'>
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input id="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<div class="rememberMeClass">
								<input type="checkbox" id="rememberMe">
								<span class="checkClass">自动登录</span>
							</div>
							<div class="input_outer">
								<input type="text" class='text code' id="code" placeholder='验证码'>
								<img class="imgCode" src="/getGifCode" onclick="this.src=this.src+'?'+Math.random()"> 
							</div>
							<div class="mb2"><a class="act-but submit" href="javascript:;" style="color: #FFFFFF" onclick="login();">登录</a></div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="/js/TweenLite.min.js"></script>
		<script src="/js/EasePack.min.js"></script>
		<script src="/js/rAF.js"></script>
		<script src="/js/demo-1.js"></script>
		<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
		<div style="text-align:center;">
		</div>
		<script>
			$(function(){
				$("#logpass").keydown(function(event){
					if(event.keyCode==13){
						login();
					}
				});
				$("#code").keydown(function(event){
					if(event.keyCode==13){
						login();
					}
				});
				/* $.get("/getGifCode",function(data){
					
				}); */
			})
			function login(){
				var username = $("input[name='logname']");
				if(username[0].value==''){
					layer.msg("请输入用户名!",{time:700});
					return;
				}
				var password = $("#logpass").val();
				if(password==''){
					layer.msg("请输入密码!",{time:700});
					return;
				}
				var _code = $("#code").val();
				if(_code==''){
					layer.msg("请输入验证码!",{time:700})
					return;
				}
				var rememberMe = $("#rememberMe").is(":checked");
				$.ajax({
					url:'/login',
					type:'post',
					data:{
						userName:username[0].value,
						password:password,
						imgCode:_code,
						rememberMe:rememberMe,
						loginType:'background'
					},
					dataType:'json',
					success:function(data){
						if(data.code==0){
							location.href="/admin/index.html";
						}else{
							alert(data.message);
						}
					},
				});
			}
		</script>
	</body>
</html>