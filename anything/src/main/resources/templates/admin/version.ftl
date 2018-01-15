<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>版本号管理</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<script src="${ctx!}/assets/js/jquery.min.js?v=${version }"></script>
<script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
</head>
<style>
.versionDiv{
	width:50%;
	height:300px;
	margin:0 auto;
}
</style>
<body>
	<div class='versionDiv'>
		<h3>
			<form class='form-inline'>
				<div class="form-group">
				    <label>当前版本号为：</label>
				    <input id='version' class='form-control input-lg' type="text" value="${version }">
				    <button class='btn btn-default input-lg' onclick="updateVersion()">确定修改</button>
				</div>
			</form>
		 </h3>
		
	</div>
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
