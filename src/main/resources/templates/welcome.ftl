<!DOCTYPE html>
<html>
<head>
<!-- <meta name="viewport"  
      content="  
        height = [pixel_value | device-height] ,  
        width = [pixel_value | device-width ] ,  
        initial-scale = 0.8 ,  
        minimum-scale = float_value ,  
        maximum-scale = float_value ,  
        user-scalable = [yes | no] ,  
      "  
    />    -->
<meta name="viewport"
	content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

</head>
<body>
	<h2>主页</h2>

	<link rel="stylesheet"
		href="/js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<style>
</style>
	<script src="/js/jquery-1.9.1.min.js" type="text/javascript"></script>
	<srcipt src="/js/bootstrap-3.3.7-dist/js/bootstrap.min.js"
		type="text/javascript">
	</script> <script>
		function sendMsg() {
			var phone = $("#phone").val();
			$.ajax({
				url : '/customer/getRetrieveCode',
				type : 'post',
				data : {
					phone : phone
				},
				dataType : 'json',
				success : function(data) {
					if (data.code == 0) {
						alert("发送成功!");
					} else {
						alert("发送失败!");
					}
				},
				error : function(data) {
					alert("网络链接失败");
				},
			});
		}
	</script>

	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#">首页</a></li>
					<li><a href="#">资料</a></li>
					<li class="disabled"><a href="#">信息</a></li>
					<li class="dropdown pull-right"><a href="#"
						data-toggle="dropdown" class="dropdown-toggle">下拉<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="#">操作</a></li>
							<li><a href="#">设置栏目</a></li>
							<li><a href="#">更多设置</a></li>
							<li class="divider"></li>
							<li><a href="#">分割线</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<#list list as li>
				<div>
					<h2 align="center">${li.chapter}</h2>
					<h3 align="right">${li.author}</h3>
					<h4>&nbsp;&nbsp;${li.content}</h4>
				</div>
				</#list>
				<h3 align="center">
					&nbsp;&nbsp;&nbsp;<a href="${ctx}?page=${prevPage}">上一页</a>&nbsp;&nbsp;&nbsp;<a
						href="${ctx}?page=${prevPage}">${prevPage}</a>&nbsp;&nbsp;&nbsp;<a
						href="${ctx}?page=${pageNo}">${pageNo}</a>&nbsp;&nbsp;&nbsp;<a
						href="${ctx}?page=${nextPage}">${nextPage}</a>&nbsp;&nbsp;&nbsp;<a
						href="${ctx}?page=${nextPage}">下一页</a>&nbsp;&nbsp;&nbsp;<a
						href="${ctx}?page=${lastPage}">最后一页</a>
						<select onchange="location.href='${ctx}?page='+this.value">
							<#list 1..501 as t>
								<option value="${t}" "<#if t==pageNo>\'selected\'<#else> </#if>"  >${t}</option>
							</#list>
						</select>
				</h3>
			</div>
		</div>
	</div>
</body>
</html>