<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>注册</title>
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
								<input name="logname" id="username" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
							</div>
							<div class="input_outer">
								<span class="us_uer"></span>
								<input name="logpass" id="password" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
							</div>
							<div class="mb2"><a class="act-but submit" href="javascript:void(0);" style="color: #FFFFFF" onclick="register();">注册</a></div>
						</form>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<div style="text-align:center;">
		</div>
		<!-- javascript -->
		<script src="/js/TweenLite.min.js"></script>
		<script src="/js/EasePack.min.js"></script>
		<script src="/js/rAF.js"></script>
		<script src="/js/demo-1.js"></script>
		<script src="/js/md5.js"></script>
		<script>
		function register(){
			var username = $("#username").val();
			var password = $("#password").val();
			$.post("/regist",
			{
				userName:username,
				password:md5(password),
				loginType:'background',
			},function(data){
				if(data.code==0){
					alert("注册成功");
				}else{
					alert(data.message);
				}
			},'json');
		}
		
		
		</script>
	</body>
</html>