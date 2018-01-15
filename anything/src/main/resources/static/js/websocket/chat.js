var websocket;
var websocketStatus = false;
var path = 'localhost:9020';
var target = "TO_ALL";
var target_username = "所有人";
var uid = $("#userId").val();
var from = uid;
var fromName = '${user.userName}';
var toUser = '';
var count = 1;
websocket = new WebSocket("ws://" + path + "/ws");
setInterval(function() {
	if ('WebSocket' in window) {
		// console.log("WebSocket path:"+"ws://" + path + "/ws");
		if (!websocketStatus) {
			websocket = new WebSocket("ws://" + path + "/ws");
		}
	}
}, 30000);

websocket.onopen = function(event) {
	websocketStatus = true;
	console.log("WebSocket:已连接,uid=" + uid);
	console.log(event);
	sendUsers();
};

websocket.onmessage = function(event) {
	// var data=JSON.parse(event.data);
	// console.log("WebSocket:收到一条消息",event.data);
	var data = event.data;
	if (data.startWith("[") && data.endWith("]")) {
		data = data.substring(1, data.length - 1);
		var arr = data.split(",");
		$(".chatLeft").empty();
		for (var i = 0; i < arr.length; i++) {
			if (arr[i].trim() == uid) {
				$(".chatLeft").append("<button class='users1 btn-default' disabled>" + arr[i].trim() + "</button></span>");
			} else {
				$(".chatLeft").append("<p><button class='users2 btn-default' onclick='chatTo(\"" + arr[i].trim() + "\",this);'>" + arr[i].trim() + "</button></p>");
			}
		}
	} else if (data.indexOf("fromUid") >= 0 && data.indexOf("fromName") >= 0 && data.indexOf("toName") >= 0 && data.indexOf("date") >= 0) {
		console.log("chatting");
		var obj = JSON.parse(data);
		var list;
		for ( var key in obj) {
			list = obj[key];
		}
		if(count==1){
			for ( var i in list) {
				if (list[i].fromUid == uid) {
					$(".textareaClass").append("<div class='line-1'><span>" + list[i].date + "</span></br><span>" + list[i].text + "</span></div>");
				} else {
					$(".textareaClass").append("<div class='line-2'><span>" + list[i].date + "</span></br><span>" + list[i].text + "</span></div>");
				}
			}
			count ++ ;
		}else{
			for ( var i in list) {
				if (list[i].fromUid == uid) {
					$(".textareaClass").append("<div class='line-1'><span>" + list[i].date + "</span></br><span>" + list[i].text + "</span></div>");
				} else {
					$(".textareaClass").append("<div class='line-2'><span>" + list[i].date + "</span></br><span>" + list[i].text + "</span></div>");
				}
			}
			count ++ ;
		}
		
	}

};

String.prototype.startWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(0, str.length) == str)
		return true;
	else
		return false;
	return true;
}

String.prototype.endWith = function(str) {
	if (str == null || str == "" || this.length == 0
			|| str.length > this.length)
		return false;
	if (this.substr(this.length - str.length, this.length - 1) == str)
		return true;
	else
		return false;
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
var data = {};
function sendMsg() {
	var v = $("#message").val();
	// console.log("sendMsg():"+v);
	if (v == "") {
		return;
	} else {
		data["fromUid"] = from;
		data["fromName"] = fromName;
		data["toName"] = toUser == '' ? target_username : toUser;
		data["text"] = v;
		data["date"] = new Date();
		// console.log("data:"+data);
		websocket.send(JSON.stringify(data));
		$("#msg").val("");
	}
}

Date.prototype.Format = function(fmt) { // author: meizz
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds()
	// 秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	console.log("fmt:" + fmt);
	return fmt;
}

function send(event) {
	var code;
	if (window.event) {
		code = window.event.keyCode; // IE
	} else {
		code = event.which; // Firefox
	}
	if (code == 13) {
		sendMsg();
	}
}

setInterval(sendUsers, 1000 * 60);

function sendUsers() {
	websocket.send("users");
}

function chatTo(toUid,obj) {
	if(toUid==uid){
		console.log("chatTo myself");
	}else{
		console.log("chatTo:"+toUid);
	}
	$(obj).addClass("users3");
	toUser = toUid;
}