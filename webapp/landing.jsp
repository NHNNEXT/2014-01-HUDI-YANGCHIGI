<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/signup.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Button trigger modal -->
	<button class="btn btn-primary btn-lg" data-toggle="modal"
		data-target="#myModal">SignUp</button>
	<div id="signinDiv">
		<!-- Login form -->
		<jsp:include page="login.jspf" />
		<!-- Logout -->
		<jsp:include page="logout.jspf" />
	</div>
	<!-- Modal -->
	<jsp:include page="signup.jspf" />
</body>

<script>
	// 로그인, 로그아웃 관리 객체
	function Auth() {
		this.loginForm = $('#loginForm');
		this.loginBtn = $('#loginBtn');
		this.logoutBtn = $('#logoutBtn');
		this.init();
	}
	// 로그인 버튼 이벤트
	Auth.prototype.addLoginEvent = function() {
		var self = this;
		var callback = this.callback.bind(this, function() {
		});
		$('#loginBtn').click(function() {
			var data = $('#loginForm :input');
			$.ajax({
				type : "POST",
				url : "user/login",
				data : data
			}).done(callback);
		})
	};
	// 로그아웃 버튼 이벤트
	Auth.prototype.addLogoutEvent = function() {
		var callback = this.callback.bind(this, function() {
			this.loginForm[0].reset()
		}, $('#logoutBtn'));
		$('#logoutBtn').click(function() {
			$.ajax({
				type : "POST",
				url : "user/logout"
			}).done(callback)
		});
	};

	Auth.prototype.callback = function(fp, ele, msg) {
		if ("success" === msg) {
			if (this.loginForm.css('display') == 'none') {
				this.loginForm.css('display', 'block');
				this.logoutBtn.css('display', 'none');
			} else {
				this.logoutBtn.css('display', 'block');
				this.loginForm.css('display', 'none');
			}
			fp && fp();
		}
	}
	Auth.prototype.init = function() {
		this.logoutBtn.css('display', 'none');
		this.addLoginEvent();
		this.addLogoutEvent();
	};
	var auth = new Auth();

	function SignUp() {
		this.addSignUpEvent();
		this.formInputs = {
			email : {
				input : $('#emailInput'),
				inputReg : /^([\w-\.]+@([\w]+\.)+[\w]{2,4})?$/,
				warnMsg : '이메일 형식에 맞게 입력하세요.',
				valid : false
			},
			nickname : {
				input : $('#nicknameInput'),
				warnMsg : '닉네임은 4자 이상 입니다.',
				valid : false
			},
			password : {
				input : $('#passwordInput'),
				warnMsg : '비밀번호는 4자 이상 입니다.',
				valid : false
			}
		};
		this.addValidateEvent();
	}
	SignUp.prototype.isValid = function() {
		var valid = true;
		$.each(this.formInputs, function(key, value) {
			valid = valid && value['valid'];
		})
		return valid;
	}
	SignUp.prototype.addSignUpEvent = function() {
		var signUpBtn = $('#signUpBtn');

		signUpBtn.click(function() {
			if (this.isValid()) {
				var data = $('#signUpForm :input');
				$.ajax({
					type : "POST",
					url : "user/signup",
					data : data
				}).done(function(msg) {
					console.log(msg);
				});
			}
		}.bind(this))
	}

	SignUp.prototype.addValidateEvent = function() {
		$.each(this.formInputs, function(key, value) {
			var callback = this.callback.bind(value['input'], value);
			value['input'].keyup(callback);
		}.bind(this));
	}

	SignUp.prototype.callback = function(value, e) {
		var input = $(this).val();

		var showValidityDiv = $(this).next();
		if (!input || !input.match(value['inputReg'])) {
			showValidityDiv.html(value['warnMsg']);
			showValidityDiv.css('color', 'red');
			value['valid'] = false;
		} else {
			showValidityDiv.html('입력 완료');
			showValidityDiv.css('color', 'green');
			value['valid'] = true;
		}
	};

	var signUp = new SignUp();
</script>
</html>