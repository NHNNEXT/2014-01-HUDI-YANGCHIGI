<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link href='http://fonts.googleapis.com/css?family=Raleway:400,300'
	rel='stylesheet' type='text/css'>
<!-- 웹폰트 -->
<link href="http://fonts.googleapis.com/css?family=Maven+Pro"
	rel="stylesheet" type="text/css">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,900|Francois+One|Indie+Flower' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=League+Script|Codystar|Indie+Flower|Damion|Seaweed+Script' rel='stylesheet' type='text/css'>	

<link rel="stylesheet" href="/css/landing.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script>
	var user = '${user}';
</script>
<script src="/js/landing.js"></script>
<!-- <script>auth.init('${user}'})</script> -->
</head>
<body>
	<img id="logo" src="image/logo_grass.png">
	<p id="slogan">Seize the moment</p>
	<!-- Button trigger modal -->
	<div id="signinDiv">
		<!-- Login form -->
		<jsp:include page="login.jspf" />
		<!-- Modal -->
	</div>
	<jsp:include page="signup.jspf" />
</body>
</html>
