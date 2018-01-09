<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> </title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/assets/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/assets/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/assets/css/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>完整验证表单</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/user/changePassWord">
                        	<input type="hidden" id="id" name="id" value="${USER.id}">
                            <div class="form-group">
                               
                             <div class="form-group">
                                <label class="col-sm-3 control-label">旧密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password" >
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">新密码：</label>
                                <div class="col-sm-8">
                                    <input id="password2" name="password2" class="form-control" type="password" >
                                </div>
                            </div>
                         
                             <div class="form-group">
                                <label class="col-sm-3 control-label">再次输入新密码：</label>
                                <div class="col-sm-8">
                                    <input id="password3" name="password3" class="form-control" type="password" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button id="sub" class="btn btn-primary" type="submit">提交</button> <a href="/admin/login.html"><button id="bt" class="btn btn-primary" type="button">返回首页</button></a>
                                
                                </div>
                                 
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>


    <!-- 全局js -->
    <script src="${ctx!}/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/assets/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/assets/js/plugins/layer/laydate/laydate.js"></script>
    <script type="text/javascript">
    $(document).ready(function () {
	  	
	    $("#frm").validate({
    	    rules: {
    	    	password: {
    	        required: true,
    	        minlength: 6,
    	    	maxlength: 20
    	      },
    	      password2: {
    	        required: true,
    	        minlength: 6,
    	    	maxlength: 20
    	      },
    	      password3: {
      	        required: true,
      	        minlength: 6,
      	    	maxlength: 20,
      	    	equalTo:password2
      	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/user/changePassWord",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.message, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index); 
	   					});
   	    		   }
   	    		});
            } 
    	});
    });
    
    
    function checkUserName() {
		var value = $('#userName').val();
	
		$.ajax({
			url:'/admin/user/checkUserName',
			type:'post',
			data:{userName:value},
			success:function(result){
				if(result=="no"){
					$("#sub").attr("disabled",true);
					$('#u').append('<font id="f" size="3" color="red">该用户名已存在</font>');
					
				}
				if(result=="yes"){
				
					$("#sub").removeAttr("disabled");
					$("#f").remove();
				}
			}
		});
		
	}
    
    function checkTelephone() {
		var value = $('#telephone').val();
	
	    if(!(/^1\d{10}$/.test(value))){ 
	    	$("#f").remove();
	        $("#sub").attr("disabled",true);
	        $("#t").append('<i class="fa fa-times-circle"></i><font id="f" size="3" color="red">不是完整的11位手机号或者正确的手机号前七位</font>');
	        document.mobileform.mobile.focus(); 
	        return false; 
	    } if((/^1\d{10}$/.test(value))){
			
			$("#sub").removeAttr("disabled");
			$("#f").remove();
		}
	
	}
    </script>

</body>

</html>
