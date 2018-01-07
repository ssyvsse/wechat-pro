<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="renderer" content="webkit">

<title>主页</title>

<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
<link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow: hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
			<div class="nav-close">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="sidebar-collapse">
				<ul class="nav" id="side-menu">
					<li class="nav-header">
						<div class="dropdown profile-element">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#"> <span
								class="clear"> <span class="block m-t-xs"
									style="font-size: 20px;"> <i class="fa fa-area-chart"></i>
										<strong class="font-bold">后台</strong>
								</span>
							</span>
							</a>
						</div>
						<div class="logo-element">后台~~~</div>
					</li>
					<li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
						<span class="ng-scope">分类</span>
					</li>
					<li><a class="J_menuItem" href="${ctx!}/admin/welcome.html">
							<i class="fa fa-home"></i> <span class="nav-label">主页</span>
					</a></li>
					<li><a href="#"> <i class="fa fa fa-cog"></i> <span
							class="nav-label">系统配置</span> <span class="fa arrow"></span>
					</a>
						<ul class="nav nav-second-level">


							<li><a class="J_menuItem" href="/admin/enum/enum">枚举值管理</a></li>
							<li><a class="J_menuItem" href="/admin/log/index">日志管理</a></li>
							<li><a class="J_menuItem" href="/admin/version">版本号管理</a></li>
						</ul></li>
					<!-- <li><a href="#"> <i class="glyphicon glyphicon-cloud"></i>
							<span class="nav-label">运营配置</span> <span class="fa arrow"></span>
					</a>
						<ul class="nav nav-second-level">

							<li><a class="J_menuItem"
								href="/admin/navigation/getNavigation">导航栏管理</a></li>

							<li><a class="J_menuItem"
								href="${ctx!}/admin/customer/index">客户管理</a></li>

							<li><a class="J_menuItem"
								href="/admin/homepage_common/index">导航栏列表管理</a></li>

							<li><a class="J_menuItem" href="/admin/homepage_common/tdk">全局TDK设置</a></li>

							<li><a class="J_menuItem" href="/admin/sensitivewords/index">敏感词设置</a></li>
							<li><a class="J_menuItem" href="/admin/SEOManage/index">链接提交</a></li>

						</ul></li> -->
					<@shiro.hasPermission name="system:qxgl:index">
					<li><a href="#"> <i class="glyphicon glyphicon-cloud"></i>
							<span class="nav-label">权限管理</span> <span class="fa arrow"></span>
					</a>
						<ul class="nav nav-second-level">
							<@shiro.hasPermission name="system:user:index">
							<li><a class="J_menuItem" href="${ctx!}/admin/user/index">用户管理</a>
							</li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="system:role:index">
							<li><a class="J_menuItem" href="${ctx!}/admin/role/index">角色管理</a>
							</li>
							</@shiro.hasPermission>

							<li><a class="J_menuItem"
								href="${ctx!}/admin/resource/index">资源管理</a></li>
						</ul>
					</li>
					</@shiro.hasPermission>
				</ul>


			</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">
			<div class="row border-bottom">
				<nav class="navbar navbar-static-top" role="navigation"
					style="margin-bottom: 0">
					<div class="navbar-header">
						<a class="navbar-minimalize minimalize-styl-2 btn btn-info "
							href="#"><i class="fa fa-bars"></i> </a>
					</div>
					<ul class="nav navbar-top-links navbar-right">
						<li class="dropdown"><a class="dropdown-toggle count-info"
							data-toggle="dropdown" href="#"> <i class="fa fa-user"></i> <span
								class="label label-primary"></span>
						</a>
							<ul class="dropdown-menu dropdown-alerts">
								<li><a href="${ctx!}/admin/logout">
										<div>
											<i class="fa fa-remove"></i> 注销 <span
												class="pull-right text-muted small"></span>
										</div>
								</a></li>
								<li><a href="${ctx!}/admin/user/changePwd">
										<div>
											<i class="fa fa-remove"></i> 修改密码 <span
												class="pull-right text-muted small"></span>
										</div>
								</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe id="J_iframe" width="100%" height="100%" src="/admin/welcome.html"
					frameborder="0" data-id="index_v1.html" seamless></iframe>
			</div>
		</div>
		<!--右侧部分结束-->
	</div>

	<!-- 全局js -->
	<script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ctx!}/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script
		src="${ctx!}/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

	<!-- 自定义js -->
	<script src="${ctx!}/assets/js/hAdmin.js?v=4.1.0"></script>
	<script type="text/javascript" src="${ctx!}/assets/js/index.js"></script>
</body>

</html>
