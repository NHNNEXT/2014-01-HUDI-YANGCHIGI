<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Modify</title>
</head>
<body>
	<h1>User Modify Page</h1>
	<form method="post" action="usermodify" enctype="multipart/form-data">
	<!-- <form method="post" action="upload.jsp" enctype="multipart/form-data"> -->
		<input type="file" name="filename1" size=40>
		<input type="submit" value="업로드">
		
		<p>메시지: <input type="text" name="msg"> </p>
		<p>패스워드: <input type="password" name="pwd"> </p>
		<p> <input type="submit" value="전송"> </p>
	</form>
</body>
</html>