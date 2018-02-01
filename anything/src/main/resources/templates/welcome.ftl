<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title></title>
<meta name="keywords" content="">
<meta name="description" content="">

</head>
<link href="${ctx!}/assets/css/bootstrap.min.css?v=3.3.6"
	rel="stylesheet">
<link href="${ctx!}/assets/css/font-awesome.min.css?v=4.4.0"
	rel="stylesheet">
<style>
body{
	background-color: #F6F6F6;
}
.carousel{
	min-height:200px;
	max-height:200px;
}
.nav-stacked li a{
	border:1px solid blue;
}
.carousel-img{
	min-width:894px;
	max-width:894px;
	min-height:200px;
	max-height:200px;
}
.navinside｛
	padding:0px;
｝
.nav-right{
	max-height:200px;
}

</style>
<body>
	<div class="container">
	   <nav class="navbar navbar-inverse">
            <div class="navbar-collapse">
                <ul class="nav navbar-nav">
					<#list navigation as nav>
						<li><a href="${nav.url}">${nav.name}</a></li>
					</#list>                    
                </ul>
            </div>
        </nav>
		<div id="myCarousel" class="col-md-9 carousel slide navinside">
			<!-- 轮播（Carousel）指标 -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>   
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<#list carouselIndex as carousel>
					<#assign a='${carousel_index}'>
					<#if a==0>
						<div class="item active">
							<img src="${carousel.image}" class="carousel-img" alt="First slide">
						</div>
						<#elseif a==1>
							<div class="item">
								<img src="${carousel.image}" class="carousel-img" alt="Second slide">
							</div>
						<#else>
							<div class="item">
								<img src="${carousel.image}" class="carousel-img" alt="Third slide">
							</div>
					</#if>
				</#list>
			</div>
			<!-- 轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" 
				data-slide="prev">&lsaquo;
			</a>
			<a class="carousel-control right" href="#myCarousel" 
				data-slide="next">&rsaquo;
			</a>
		</div>
		<div class="col-md-3 nav-right">
            <h2>今日推荐</h2> 
			
    
    <ul class="nav nav-tabs nav-stacked">
        <li><a href='#'>Another Link 1</a></li>
        <li><a href='#'>Another Link 2</a></li>
        <li><a href='#'>Another Link 3</a></li>
    </ul>    

        </div>
	   <div id="content" class="row-fluid">
        <div class="col-md-9">
            <h2>Main Content Section</h2>
        </div>
        
    </div>
	</div>
	
<script type="text/javascript" src="${ctx!}/js/jquery-1.9.1.min.js"></script>
<script src="${ctx!}/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx!}/assets/js/plugins/layer/layer.min.js"></script>
<script type="text/javascript">

	$(function(){
		
	})
</script>
</body>
</html>
