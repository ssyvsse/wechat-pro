<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>用户列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
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
            display: block;
            margin: 0 auto !important;
        }
    } 
    .dateCls{
    	width:200px;
    	display:inline;
    	margin:10px;
    }
</style>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h4>系统日志&nbsp;&nbsp;&nbsp;在线用户人数: <span>${onlineCount}</span>  </h4>
                    </div>
                    <div class="operation-box">
                    
                    </div>
                    <div class="ibox-content">                       
                        <hr>
                        <div class="row row-lg">
		                    <div class="col-sm-12">
		                        <!-- Example Card View -->
		                        <div class="example-wrap">
		                            <div class="example">
		                            	<table id="table_list"></table>
		                            </div>
		                        </div>
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

	<link href="${ctx!}/assets/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">

    <!-- Peity -->
    <script src="${ctx!}/assets/js/plugins/peity/jquery.peity.min.js"></script>

    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=${version}"></script>

    <!-- Page-Level Scripts -->
    <script>
    	window.onload = destroy();
    	function destroy(){
    		var searchText=$("#searchText").val();
			var begin=$("#beginTime").val();
			var end=$("#endTime").val();
    		
    		$("#table_list").bootstrapTable('destroy');
    		//初始化表格,动态从服务器加载数据  
			$("#table_list").bootstrapTable({
			    //使用get请求到服务器获取数据  
			    method: "POST",
			    //必须设置，不然request.getParameter获取不到请求参数
			    contentType: "application/x-www-form-urlencoded",
			    //获取数据的Servlet地址  
			    url: "${ctx!}/admin/log/list",
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
			    search: false,
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
			    queryParams:function(params){ 
					var temp = {
							pageSize:params.pageSize,
							pageNumber:params.pageNumber,
							sortOrder:params.sortOrder,	
							sortName:params.sortName,	
							searchText:searchText,
							begin:begin,
							end:end
					};
					return temp;
				}, 
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
			        title: "操作",
			        field: "operationalContext"
			    },{
			        title: "操作时间",
			        field: "operationTime",
			        sortable: true
			    },{
			        title: "操作人",
			        field: "operator",
			        sortable: true
			    },{
			        title: "ip",
			        field: "ip",
			        sortable: true
			    }]
			});
    		console.log(searchText)
    	}
        $(document).ready(function () {
        	$(".operation-box").prepend("<input type='date' id='beginTime' placeholder='开始时间' onchange='destroy();' class='form-control dateCls'  > ")
        	.append("-&nbsp;&nbsp; <input type='date' id='endTime' placeholder='结束时间' class='form-control dateCls' onchange='destroy();' >")
        	.append("&nbsp;&nbsp; <input type='text' id='searchText' placeholder='请输入查询关键字' class='form-control dateCls'>")
        	.append("&nbsp;&nbsp; <input type='button' id='shBtn' value='搜索' onclick='destroy();' class='btn btn-default'>");
        });
                       
    </script>

    
    

</body>

</html>
