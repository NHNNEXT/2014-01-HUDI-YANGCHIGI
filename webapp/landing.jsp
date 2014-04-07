<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Button trigger modal -->
	<button class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">SignUp</button>
	<!-- Login form -->
	<form id="loginForm" class="form-inline" role="form">
		<div class="form-group">
			<label class="sr-only" for="exampleInputEmail2">Email address</label>
			<input type="email" name="email" class="form-control" id="exampleInputEmail2"
				placeholder="Enter email">
		</div>
		<div class="form-group">
			<label class="sr-only" for="exampleInputPassword2">Password</label> <input
				type="password" name="password" class="form-control" id="exampleInputPassword2"
				placeholder="Password">
		</div>
		<div class="checkbox">
			<label> <input type="checkbox"> Remember me
			</label>
		</div>
		<button id="loginBtn" type="submit" class="btn btn-default">Sign in</button>
	</form>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Sign Up!</h4>
				</div>
				<div class="modal-body">
					<form id="signUpForm" class="form-horizontal" role="form">
						<div class="form-group">
							<label for="inputEmail" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="email" name="email" class="form-control"
									id="inputEmail" placeholder="Email">
							</div>
						</div>
						<div class="form-group">
							<label for="inputNickname" class="col-sm-2 control-label">Nickname</label>
							<div class="col-sm-10">
								<input type="text" name="nickname" class="form-control"
									id="inputNickname" placeholder="Nickname">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-sm-2 control-label">Password</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="inputPassword" placeholder="Password">
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" id="signUpBtn" class="btn btn-primary">SignUp</button>
				</div>
			</div>
		</div>
	</div>
</body>

<script>
	var signUpBtn = $('#signUpBtn');

	signUpBtn.click(function() {
		var data = $('#signUpForm :input');
		/* jQuery.each(data, function(i, field) {
			console.log(i + ': ' + field.value);
		});*/
		$.ajax({
			type : "POST",
			url : "signup",
			data : data
		}).done(function(msg) {
			console.log(msg);
		});
	})
	
	var loginBtn = $('#loginBtn');
	loginBtn.click(function() {
		var data = $('loginForm :ipnut');
		$.ajax({
			type : "POST",
			url : "login",
			data : data
		}).done(function(msg) {
			console.log(msg);
		});
	})
</script>
</html>