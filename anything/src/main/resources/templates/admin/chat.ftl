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
	width:460px;
	margin:2px;
	border:1px solid black;
	border-radius:5px;
	float:left;
}
.textareaClass{
	float:left;
	resize:none;
	width:445px;
	margin:5px;
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
.users{
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
</style>
</head>
	<div class="chatDiv">
		<div class="chatLeft">
		</div>
		<div class="chatDivInside">
			<textarea rows="22" cols="50" class="form-control textareaClass"></textarea>
			<input type="text" value="" id="message" class="form-control inputClass" onkeydown="send(event);">
			<button class="btn btn-default btnSend" onclick="sendMsg()">发送</button>
		</div>
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
var toUser = '';
websocket = new WebSocket("ws://" + path + "/ws");
setInterval(function(){
	if ('WebSocket' in window){
		//console.log("WebSocket path:"+"ws://" + path + "/ws");
		if(!websocketStatus){
			websocket = new WebSocket("ws://" + path + "/ws");
		}
	}
},30000);

websocket.onopen = function(event) {
	websocketStatus = true;
	console.log("WebSocket:已连接,uid="+uid);
	console.log(event);
	sendUsers();
};


websocket.onmessage = function(event) {
	//var data=JSON.parse(event.data);
	//console.log("WebSocket:收到一条消息",event.data);	
	var data = event.data;
	if(data.startWith("[")&&data.endWith("]")){
		data = data.substring(1,data.length-1);
		var arr = data.split(",");
		$(".chatLeft").empty();
		for(var i=0;i<arr.length;i++){
			if(arr[i]==uid){
				$(".chatLeft").append("<button class='users btn-default' disabled>"+arr[i]+"</button></p>");
			}else{
				$(".chatLeft").append("<p><button class='users btn-default' onclick='chatTo(\""+arr[i]+"\");'>"+arr[i]+"</button></p>");
			}
		}
	}else if(data.indexOf("fromUid")>=0&&data.indexOf("fromName")>=0&&data.indexOf("toName")>=0&&data.indexOf("date")>=0){
		console.log("chatting");
		var obj = JSON.parse(data);
		var list;
		for(var key in obj){
			list = obj[key];
		}		
		$(".textareaClass").empty();
		for(var i in list){
			if(list[i].fromUid==uid){
				$(".textareaClass").append(list[i].date+" :"+list[i].text+"\r\n");
			}else{
				$(".textareaClass").append(list[i].date+" :"+list[i].text+"\r\n");
			}
		}
	}
	
};

String.prototype.startWith=function(str){    
	  if(str==null||str==""||this.length==0||str.length>this.length)    
	   return false;    
	  if(this.substr(0,str.length)==str)    
	     return true;    
	  else    
	     return false;    
	  return true;    
}  

String.prototype.endWith=function(str){    
	  if(str==null||str==""||this.length==0||str.length>this.length)    
	   return false;    
	  if(this.substr(this.length-str.length,this.length-1)==str)    
	     return true;    
	  else    
	     return false;    
	  return true;    
}  

websocket.onerror = function(event) {
	console.log("WebSocket:发生错误 ");
	console.log(event);
};
websocket.onclose = function(event) {
	websocketStatus = false;
	console.log("WebSocket:已关闭");
	console.log(event);
}
var data={};
function sendMsg(){
	var v=$("#message").val();
	//console.log("sendMsg():"+v);
	if(v==""){
		return;
	}else{
		data["fromUid"]=from;
		data["fromName"]=fromName;
		data["toName"]=toUser==''?target_username:toUser;
		data["text"]=v;
		data["date"]=new Date();
		//console.log("data:"+data);
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
	if(code==13){ 
		sendMsg();            
	}
}		

setInterval(sendUsers,1000*10);
	
function sendUsers(){
	websocket.send("users");
}	

function chatTo(toUid){
	console.log(toUid);
	toUser = toUid;
}
	
</script>
</html>
