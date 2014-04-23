<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Modify</title>
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
	<h1>User Modify Page</h1>
	<!-- <form id="userModifyForm" method="post" action="usermodify/uploadThumbnail" enctype="multipart/form-data"> -->
	<form id="userModifyForm">
		<h1>내 기존의 닉네임</h1>
		<h2>nickname: ${nickname}</h2>
		<h1>바꾸고싶은 닉네임</h1>
		<input type="text" name="nickname">
		<!-- <input type="submit" value="변경"> -->
		<input id="modifyBtn" type="button" value="변경">
		
		<h1>내 기존의 사진</h1>
		<h2>thumbnailName: ${thumbnailName}</h2>
		<img src="img/${thumbnailName}" width=100 height=100></img> 
		<h1>바꾸고싶은 사진</h1>1
		<input type="file" name="thumbnailName" size=20>
		<img id="prevImg" src="#" alt="your image" style="display: none;" />
		<!-- <input type="submit" value="업로드"> -->
		<input id="userModifyBtn" type="button" value="업로드">
		
		<!-- <p>메시지: <input type="text" name="msg"> </p>
		<p>패스워드: <input type="password" name="pwd"> </p>
		<p> <input type="submit" value="전송"> </p> -->
	</form>
</body>
<script>
	var userModifyForm = $('#userModifyForm');
	var userModifyBtn = $('#userModifyBtn');
	var modifyBtn = $('#modifyBtn');
	
	modifyBtn.click(function() {
		var nickname = $('#userModifyForm :input');
		
		$.ajax({
			type : "POST",
			url : "usermodify/uploadThumbnail",
			//mimeType:"multipart/form-data",
			data : nickname,
		    contentType : "multipart/form-data",
		   	//processData : false
		}).done(function(msg) {
			console.log('success');
		})
	});
</script>
</html>