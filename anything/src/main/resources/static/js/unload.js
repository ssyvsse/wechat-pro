$(window).bind('beforeunload',function(){ 
	$.ajax({ 
		url:"${ctx}/admin/logout", 
		type:"GET", 
		success:function(){ 
		} 
	}); 
});