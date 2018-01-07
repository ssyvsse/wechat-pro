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
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/user/edit">
                        	<input type="hidden" id="id" name="id" value="${_user.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">账户名：</label>
                                <div class="col-sm-8" id="u">
                                    <input id="userName" name="userName" class="form-control" type="text" value="${_user.userName}" onblur="checkUserName()" <#if _user?exists> readonly="readonly"</#if> >
                              
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-8">
                                    <input id="nickName" name="nickName" class="form-control" type="text" value="${_user.nickName}">
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password" >
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">性别：</label>
                                <div class="col-sm-8">
                                	<select name="sex" class="form-control">
                                		<option value="0" <#if _user.sex == 0>selected="selected"</#if>>女</option>
                                		<option value="1" <#if _user.sex == 1>selected="selected"</#if>>男</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">出生日期：</label>
                                <div class="col-sm-8">
                                    <input id="bir" name="bir" readonly="readonly" class="laydate-icon form-control layer-date" value="${_user.birthday}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电话：</label>
                                <div class="col-sm-8" id="t">
                                    <input id="telephone" name="telephone" class="form-control" value="${_user.telephone}" onblur="checkTelephone()">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="email" name="email" class="form-control" value="${_user.email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">地址：</label>
                                <div class="col-sm-8">
                                    <input id="address" name="address" class="form-control" value="${_user.address}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
                                	<select name="locked" class="form-control">
                                		<option value="0" <#if _user.locked == 0>selected="selected"</#if>>未锁定</option>
                                		<option value="1" <#if _user.locked == 1>selected="selected"</#if>>锁定</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-8">
                                    <input id="description" name="description" class="form-control" value="${_user.description}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button id="sub" class="btn btn-primary" type="submit">提交</button>
                               		<button  class="btn" type="button" onclick="qx()">取消</button>
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
	  	//外部js调用
	    laydate({
	        elem: '#bir', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
	    });
	  	
	    $("#frm").validate({
    	    rules: {
    	    	userName: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 10
    	    	
    	      },
    	      	nickName: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 10,
    	    	
    	      },password: {
    	        required: false,
    	        minlength: 6,
    	    	maxlength: 20
    	      },
    	      	sex: {
    	        required: true
    	      },
    	      	bir: {
    	      	date:true,
    	        required: true
    	      },
    	      	telephone: {
    	        required: true 
    	      },
    	      	email: {
    	      	email:true,
    	        required: true
    	      },
    	      	address: {
    	        required: true,
    	        maxlength: 40
    	      },
    	      	locked: {
    	        required: true
    	      },
    	      	description: {
    	        required: true,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx!}/admin/user/edit",
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
	       $("#fa").remove();
	        $("#t").append('<i id="fa" class="fa fa-times-circle"></i><font id="f" size="3" color="red">不是完整的11位手机号或者正确的手机号前七位</font>');
	        document.mobileform.mobile.focus(); 
	       
	    } if((/^1\d{10}$/.test(value))){
			
			$("#sub").removeAttr("disabled");
			$("#f").remove();
		}
	
	}
    
    function qx(){
    	parent.layer.closeAll();
    }
    </script>

</body>

</html>
