<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>login</title>
<link rel="stylesheet" type="text/css" href="/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="/css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="/css/component.css" />
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
		<srcipt type="text/javascript" src="/js/jquery-3.2.1.min.js"></srcipt>
		<script src="/js/TweenLite.min.js"></script>
		<script src="/js/EasePack.min.js"></script>
		<script src="/js/rAF.js"></script>
		<script src="/js/demo-1.js"></script>
		<script>
		function register(){
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			Ajax.post("/login",ad());
		}
		
		function ad(){
			alert(1);
		}
		var Ajax={
			    get: function(url, fn) {
			        var obj = new XMLHttpRequest();  // XMLHttpRequest对象用于在后台与服务器交换数据          
			        obj.open('GET', url, true);
			        obj.onreadystatechange = function() {
			            if (obj.readyState == 4 && obj.status == 200 || obj.status == 304) { // readyState == 4说明请求已完成
			                fn.call(this, obj.responseText);  //从服务器获得数据
			            }
			        };
			        obj.send();
			    },
			    post: function (url, data, fn) {         // datat应为'a=a1&b=b1'这种字符串格式，在jq里如果data为对象会自动将对象转成这种字符串格式
			        var obj = new XMLHttpRequest();
			        obj.open("POST", url, true);
			        obj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");  // 添加http头，发送信息至服务器时内容编码类型
			        obj.onreadystatechange = function() {
			            if (obj.readyState == 4 && (obj.status == 200 || obj.status == 304)) {  // 304未修改
			                fn.call(this, obj.responseText);
			            }
			        };
			        obj.send(data);
			    }
			}
		</script>
	</body>
</html>