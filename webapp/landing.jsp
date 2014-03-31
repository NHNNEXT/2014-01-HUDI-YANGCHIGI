<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	${message} Landing Page!!
	<button id="btn">hi</button>
	${mes2}
</body>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script>
	var btn = $('#btn');
	btn.click(function() {
		$.ajax({
			type : "POST",
			url : "",
		}).done(function(msg) {
			console.log("sucess");
		});
	})
</script>
</html>