<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
<title></title>
<style>

</style>
<!-- The main CSS file -->
<link href="/css/clockstyle.css" rel="stylesheet" />

</head>
<body>
	<div id="clock" class="light">
			<div class="display">
				<div class="weekdays"></div>
				<div class="ampm"></div>
				<div class="alarm"></div>
				<div class="digits"></div>
			</div>
		</div>

		<div class="button-holder">
			<a id="switch-theme" class="button">切换风格</a>
			<a class="alarm-button"></a>
		</div>

		<!-- The dialog is hidden with css -->
		<div class="overlay">

			<div id="alarm-dialog">

				<h2>设置闹铃</h2>

				<label class="hours">
					时
					<input type="number" value="0" min="0" />
				</label>

				<label class="minutes">
					分
					<input type="number" value="0" min="0" />
				</label>

				<label class="seconds">
					秒
					<input type="number" value="0" min="0" />
				</label>

				<div class="button-holder">
					<a id="alarm-set" class="button blue">确定</a>
					<a id="alarm-clear" class="button red">取消</a>
				</div>

				<a class="close"></a>

			</div>

		</div>

		<div class="overlay">

			<div id="time-is-up">

				<h2>Time&#39;s up!</h2>

				<div class="button-holder">
					<a class="button blue">Close</a>
				</div>

			</div>

		</div>

		<audio id="alarm-ring" preload>
			<source src="/audio/ticktac.mp3" type="audio/mpeg" />
			<source src="/audio/ticktac.ogg" type="audio/ogg" />
		</audio>

        
		<!-- JavaScript Includes -->
		<script src="/js/jquery.min.js"></script>
		<script src="/js/moment.min.js"></script>
		<script src="/js/script.js"></script>
<div style="text-align:center;clear:both">

</div>
</body>
</html>