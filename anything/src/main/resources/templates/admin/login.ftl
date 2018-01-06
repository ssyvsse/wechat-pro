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
</head>
<body>
		<div class="container demo-1">
			<div class="content">
				<div id="large-header" class="large-header">
					<canvas id="demo-canvas"></canvas>
					<div class="logo_box">
						<h3>欢迎你</h3>
						<form action="#" name="f" method="post">
							<div class="input_outer">
								<span class="u_user"></span>
								<input name="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input id="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
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
		<script src="/js/md5.js"></script>
		<div style="text-align:center;">
		</div>
		<script>
			$(function(){
				$("#logpass").keydown(function(event){
					if(event.keyCode==13){
						login();
					}
				});
			})
			function login(){
				var username = $("input[name='logname']");
				var password = $("#logpass").val();
				$.ajax({
					url:'/login',
					type:'post',
					data:{
						userName:username[0].value,
						password:md5(password),
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