<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Modify</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script src="js/userModify.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
</head>
<body>
	<h1>User Modify Page</h1>
	<form id="userModifyForm" method="post" action="usermodify/upload" enctype="multipart/form-data">
		<h1>내 기존의 닉네임</h1>
		<h2>nickname: ${nickname}</h2>
		<h1>바꾸고싶은 닉네임</h1>
		<input type="text" name="nickname" id="nicknameInput">
		<input name="imgName" type='file' id="fileInput" accept="image/*" style="display: none;" />
		
		
		<h1>내 기존의 사진</h1>
		<h2>thumbnailName: ${thumbnailName}</h2>
		<img src="img/${thumbnailName}" width=100 height=100></img> 
		<h1>바꾸고싶은 사진</h1>
		<div id="imgFormDiv">
			<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" />
			<img id="prevImg" src="#" alt="your image" style="display: none;" />
		</div>
		

		<!-- <input type="submit" value="업로드"> -->
		<!-- <input id="userModifyBtn" type="button" value="업로드"> -->
		
		
		<!-- 제출 -->
		<div id="writeMenuDiv">
			<div class="form-group">
				<button type="button" class="btn btn-success" id="submitBtn">변경</button>
			</div>
		</div>

		<!-- <p>메시지: <input type="text" name="msg"> </p>
		<p>패스워드: <input type="password" name="pwd"> </p>
		<p> <input type="submit" value="전송"> </p> -->
	</form>
</body>
<script>

</script>
</html>