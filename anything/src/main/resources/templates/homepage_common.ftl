<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<meta name="keywords" content="">
<meta name="description" content="">

</head>
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">
<style type="text/css">
    body #table_list tr th div,
    body #table_list tbody tr td:last-child{
        text-align: center;
    }
    body hr {
        margin-bottom: 0px;
    }
    @media screen and (max-width: 1630px) { 
        body #table_list tbody tr td:last-child button{
            display: inline;
            margin: 0 auto !important;
        }
    } 
</style>
<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>用户管理</h5>
                    </div>
                    <div class="ibox-content">
                        <p>
                        	<@shiro.hasPermission name="system:user:add">
                        	<button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                        	</@shiro.hasPermission>
                        </p>
                        <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list"></table>
		                            </div>
		                        </div>
		                        <!-- End Example Card View -->
		                    </div>
	                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
    
	<!-- Bootstrap table -->
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- Page-Level Scripts -->
    <script>
        $(document).ready(function () {
        	//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/homepage_manager/list",
			    //表格显示条纹  
			    striped: true,
			    //启动分页  
			    pagination: true,
			    //每页显示的记录数  
			    pageSize: 10,
			    //当前第几页  
			    pageNumber: 1,
			    //记录数可选列表  
			    pageList: [5, 10, 15, 20, 25],
			    //是否启用查询  
			    search: true,
			    //是否启用详细信息视图
			    detailView:true,
			    detailFormatter:function(index, row) {
					var html = [];
					html.push('<p><b>描述:</b> ' + row.description + '</p>');
					return html.join('');
				},
			    //表示服务端请求  
			    sidePagination: "server",
			    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
			    //设置为limit可以获取limit, offset, search, sort, order  
			    queryParamsType: "undefined",
			    //json数据解析
			    responseHandler: function(res) {
			    	return {
			            "rows": res.content,
			            "total": res.totalElements
			        }; 
			    },
			    //数据列
			    columns: [{
			        title: "ID",
			        field: "id",
			        sortable: true
			    },{
			        title: "名称",
			        field: "name"
			    },{
			        title: "所属角色",
			        field: "roles",
			        formatter: function(value, row, index) {
                    	var r = "";
                    	$(value).each(function (index,role){
                    		r = r + "【" + role.name + "】";
                    	});
                    	return r;
                    }
			    },{
			        title: "昵称",
			        field: "nickName"
			    },{
			        title: "性别",
			        field: "sex",
			        formatter: function(value, row, index) {
                        if (value == '0') {
                        	return '<span class="label label-warning">女</span>';
                        } else if (value == '1'){
                        	return '<span class="label label-primary">男</span>';
                        } else{
                        	return '<span class="label label-primary">未知</span>';
                        }
                    }
			    },{
			        title: "出生日期",
			        field: "birthday"
			    },{
			        title: "父id",
			        field: "parentId"
			    },{
			        title: "类型",
			        field: "type"
			    },{
			        title: "锁定",
			        field: "locked",
			        formatter: function (value, row, index) {
                        if (value == '0') 
                        	return '<span class="label label-info">未锁定</span>';
                        return '<span class="label label-danger">已锁定</span>';
                    }
			    },{
			        title: "创建时间",
			        field: "createTime",
			        sortable: true
			    },{
			        title: "更新时间",
			        field: "updateTime",
			        sortable: true
			    },{
			        title: "操作",
			        field: "empty",
                    formatter: function (value, row, index) {
                    	var operateHtml = '';
                        return operateHtml;
                    }
			    }]
			});
        });
        
        
        
        
    </script>

    
    

</body>
</html>
