<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>

<style>

.container {
	background-color: #FFDDDD;
	margin-top: 50px;
	max-width: none !important;
	width:1000px;
}
#mypagecontainer {
	padding:20px;
}

#calendar {
	height:300px;
	margin-right:20px;
	background-color:#AFAFAF;
}
#contents{
	background-color:#FFFFDD;
	margin-top:20px;
	border:1px solid black;
	height:80px;
	margin-left:30px;
}

#writing{
	height: 150px;
	background-color: #FFFFFF;
	position:relative;
}
#writing_menu{
	height: 30px;
	background-color: #AFAFAF;
	
	position:absolute;
	bottom:0;
	right:0;
}
#withimg{
	height: 30px;
	background-color: #AFAFAF;
	
	position:absolute;
	bottom:0;
	left:0;
}
#img_prev{
	position:absolute;
	left:100%;
	bottom:0;
	margin-left:10px;
}
</style>

<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img_prev').attr('src', e.target.result).height(70).css("display","inline")
			};

			reader.readAsDataURL(input.files[0]);
		} else {
			// if cancel selection
			var img = input.value;
			$('#img_prev').attr('src', img).css("display","none");
		}
	}
	
	function chooseFile(){
		$("#fileInput").click();
	}
	
</script>
</head>
<body>
	<div class="container">
		
		<div id="mypagecontainer" class="row">
			<div id="calendar" class="col-xs-4">calendar</div>
		
			<div id="mypageright" class="col-xs-7">
				<div class="row"></div>
					<div id="writing" class="row">writing
							<input type='file' id="fileInput" accept="image" onchange="readURL(this);" style="display:none;" />
						<div id="withimg">
							
							<img src="icon/addimage.png" onclick="chooseFile()" style="height:30px;"/>
							<span id="previewPane">
								<img id="img_prev" src="#" alt="your image" style="display:none;"/>
							</span>
						</div>
						<div id="writing_menu">writing_menu</div>
					</div>
					<div id="contents" class="row">contents</div>
					<div id="contents" class="row">contents</div>
					<div id="contents" class="row">contents</div>
					<div id="contents" class="row">contents</div>
					<div id="contents" class="row">contents</div>
			</div>
			</div>
		</div>
</body>
</html>