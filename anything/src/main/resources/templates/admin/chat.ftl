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
	width:500px;
	margin:0 auto;
}
.textareaClass{
	float:left;
	resize:none;
	width:498px;
	margin:5px;
}
.inputClass{
	float:left;
	width:498px;
	margin:5px;
}
</style>
</head>
	<div class="chatDiv">
		<textarea rows="20" cols="50" class="form-control textareaClass"></textarea>
		<input type="text" value="" id="message" class="form-control inputClass" onclick="sendMsg();" onkeydown="send(event);">
		<button>发送</button>
	</div>
<body>
</body>
<script type="text/javascript">
var websocket;
var websocketStatus = false;
var path = 'localhost:9020';	
var target = "TO_ALL";   
var target_username="所有人";
var uid='${user.id}';	
var from=uid;
var fromName='${user.userName}';
websocket = new WebSocket("ws://" + path + "/ws");
setInterval(function(){
	if ('WebSocket' in window){
		console.log("WebSocket path:"+"ws://" + path + "/ws");
		if(websocketStatus){
			websocket = new WebSocket("ws://" + path + "/ws");
		}else{
			websocket.send("000");
		}
	}
},20000);

websocket.onopen = function(event) {
	websocketStatus = true;
	console.log("WebSocket:已连接");
	console.log(event);
};

websocket.onmessage = function(event) {
	var data=JSON.parse(event.data);
	console.log("WebSocket:收到一条消息",data);		
};
websocket.onerror = function(event) {
	console.log("WebSocket:发生错误 ");
	console.log(event);
};
websocket.onclose = function(event) {
	websocketStatus = false;
	console.log("WebSocket:已关闭");
	console.log(event);
}
function sendMsg(){
	var v=$("#message").val();
	console.log("sendMsg():"+v);
	if(v==""){
		return;
	}else{
		var data={};
		data["from"]=from;
		data["fromName"]=fromName;
		if (target == "TO_ALL"){
		  data["to"]=-1;
		  data["toName"]="所有人";
		}else{
		  data["to"]=target;
		  data["toName"]=target_username;
		}				
		data["text"]=v;
		console.log("data:"+data);
		websocket.send(JSON.stringify(data));							
		$("#msg").val("");
	}
}	

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds() //秒 			    
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    console.log("fmt:"+fmt);
    return fmt;
}
	
function send(event){
	var code;
	 if(window.event){
		 code = window.event.keyCode; // IE
	 }else{
		 code = event.which; // Firefox
	 }
	 console.log("code:"+code);
	if(code==13){ 
		sendMsg();            
	}
}		
</script>
</html>
