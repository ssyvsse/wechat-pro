<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>版本号管理</title>
<meta name="keywords" content="">
<meta name="description" content="">
<script src="${ctx!}/assets/js/jquery.min.js?v=${version }"></script>
</head>

<body>
	<h3>当前版本号为：<input id='version' type="text" value="${version }"></h3>
	<button onclick="updateVersion()">确定修改</button>
</body>
<script type="text/javascript">
	function updateVersion(){
		$.ajax({
			url:'${ctx!}/admin/updateVersion',
			type:'POST',
			data:{version:$('#version').val()},
			dataType:'json',
			success:function(msg){
				if(msg.code==0){
					alert(msg.message);
				}else{
					alert(msg.message);
				}
			},
			error:function(msg){
				
			}
		});
	}

</script>
</html>
