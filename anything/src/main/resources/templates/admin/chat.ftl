<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>后台聊天</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<script src="${ctx!}/assets/js/jquery.min.js?v=${version }"></script>
<style>
.chatDiv{
	width:600px;
	height:500px;
	margin:0 auto;
	margin-top:10px;
	padding:2px;
	border:1px solid black;
}
.chatLeft{
	width:130px;
	height:495px;
	border:1px solid black;
	border-radius:5px;
	float:left;
	opacity:1;
}
.chatDivInside{
	width:453px;
	height:98%;
	margin:5px;
	border:1px solid black;
	border-radius:5px;
	float:left;
}
.textareaClass{
	float:left;
	width:430px;
	height:89%;
	margin:5px;
	overflow-y:scroll;
}
.inputClass{
	float:left;
	width:370px;
	margin:5px;
}
.btnSend{
	float:left;
	margin:6px;
}
.users1,.users1:hover{
	max-width:120px;
	min-height:50px;
	max-height:50px;
	overflow:auto;
	margin:5px 1px;
	border:1px solid #ccc;
	margin:3px;
	line-height:30px;
	border-radius:5px;
	background-color:#ccc;
}
.users2{
	max-width:120px;
	min-height:50px;
	max-height:50px;
	overflow:auto;
	margin:5px 1px;
	border:1px solid #ccc;
	margin:3px;
	line-height:30px;
	border-radius:5px;
}
.users3,.users3:hover{
	max-width:120px;
	min-height:50px;
	max-height:50px;
	overflow:auto;
	margin:5px 1px;
	border:1px solid #ccc;
	margin:3px;
	line-height:30px;
	border-radius:5px;
	background-color:yellow;
}
.line-1{
	width:398px;
	text-align:right;
}
.line-2{
	width:398px;
	text-align:left;
}
</style>
</head>
	<div class="chatDiv">
		<div class="chatLeft">
		</div>
		<div class="chatDivInside">
			<div class="form-control textareaClass">
			</div>
			<input type="hidden" value="${user.id}" id="userId">
			<input type="text" value="" id="message" class="form-control inputClass" onkeydown="send(event);">
			<button class="btn btn-default btnSend" onclick="sendMsg()">发送</button>
		</div>
	</div>
<body>
</body>
<script type="text/javascript" src="/js/websocket/chat.js?Math.random()"></script>
<script type="text/javascript">
</script>
</html>
