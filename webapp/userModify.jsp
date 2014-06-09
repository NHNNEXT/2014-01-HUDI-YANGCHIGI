<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>User Modify</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0" > 
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/header.css">
	<link rel="stylesheet" href="css/usermodify.css">
	<link href="https://fontastic.s3.amazonaws.com/atUbsU72QhekCwuoLXgtCC/icons.css" rel="stylesheet">	
	<!-- 파비콘 -->
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon">
	<link rel="icon" href="image/favicon.ico" type="image/x-icon">	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
	<script src="js/userModify.js"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script> 						
</head>
<body>
	<%@include file="header.jspf" %> 
	<div id="baloon">
		
		<div id="userInfo">
			<form id="userModifyForm" method="post" action="usermodify/upload" enctype="multipart/form-data">
				<input name="imgName" type='file' id="fileInput" accept="image/*" style="display: none;" /> 
				<%-- <input src="image/${thumbnailName}" id="uploadImg" name="imgName" type='file'/> --%>
				<%-- <img src="image/${thumbnailName}" id="thumbnail" width=150 height=150> --%> 
 				<!--<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" /> -->
				
				<div id="uploadBlock">
					<%-- <img src="image/pencil.png" id="overlay" style="1display:none; position: absolute; top: 0px; left: 0px; z-index: 2">
					<img src="image/${thumbnailName}" id="uploadImg" style="display: block; position: absolute; top: 0px; left: 0px; z-index: 1"> --%> 

					<div id="box">
						<div id="overlay">
							<span id="plus"><span class="icon-pencil"></span></span>
						</div>
					</div>

					<%-- <img id="overlay" src="image/pencil.png">
					<img id="uploadImg" src="image/${thumbnailName}"/>  --%>
				</div>
				<!-- <img id="prevImg" src="#" alt="your image" style="display: none;" /> -->
				<h1 class="nickname"><p id="oldNickname">${nickname}</p></h1> 
				<input type="text" name="nickname" id="nicknameInput">
				
				<!-- 제출 -->
				<div id="writeMenuDiv">
					<div class="form-group">
						<button type="button" class="btn btn-success" id="submitBtn">변경</button>
					</div>
				</div>
			</form>
		</div>
		<div id="userStats">
			user stats
		</div>
	</div>	
	<%@include file="footer.jspf" %> 
</body>
<script>
	$(document).ready(function() {
		//$('#box').hover(function() {
			$("#box").css('background-image', 'url("image/${thumbnailName}")');	
		//});

	});
</script>
<script src="/js/header.js"></script>
</html>
