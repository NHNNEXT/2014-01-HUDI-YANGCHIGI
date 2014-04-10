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
		<jsp:include page="login.jsp" />
		<!-- Logout -->
		<jsp:include page="logout.jsp" />
	</div>
	<!-- Modal -->
	<jsp:include page="signup.jsp" />
</body>

<script>
	function addSignUpEvent() {
		var signUpBtn = $('#signUpBtn');

		signUpBtn.click(function() {
			var data = $('#signUpForm :input');
			$.ajax({
				type : "POST",
				url : "signup",
				data : data
			}).done(function(msg) {
				console.log(msg);
			});
		})
	}
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
				url : "login",
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
			debugger;
			$.ajax({
				type : "POST",
				url : "logout"
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

	/* function addClearFormEvent() {
		var data = $('#signUpForm :input');
		var closeBtn = $('#closeBtn');

		closeBtn.click(function() {
			jQuery.each(data, function(i, field) {
				$(field).val('');
			});
		})
	}
	 */
	//addSignUpEvent();
	//addClearFormEvent();
	function SignUp() {
		this.formInputs = {
			email : {
				input : $('#emailInput'),
				inputReg : /^([\w-\.]+@([\w]+\.)+[\w]{2,4})?$/
			},
			nickname : {
				input : $('#nicknameInput')
			},
			password : {
				input : $('#passwordInput')
			}
		};
		this.addValidateEvent();
	}

	SignUp.prototype.addValidateEvent = function() {
		$.each(this.formInputs, function(key, value) {
			var callback = function(value, e) {
				console.log(value);
				debugger;
				var inputReg = /^([\w-\.]+@([\w]+\.)+[\w]{2,4})?$/;
				var input = $(this).val();

				var showValidityDiv = $(this).next();
				if (!input || !input.match(inputReg)) {
					showValidityDiv.html('not match!');
					showValidityDiv.css('color', 'red');
				} else {
					showValidityDiv.html('match');
					showValidityDiv.css('color', 'green');
				}
			};

			value['input'].keyup(callback);
		});
	}

	var signUp = new SignUp();
	/* function addValidateEvent() {
		var emailInput = $('#emailInput');
		var nicknameInput = $('#nicknameInput');
		var passwordInput = $('#passwordInput');

		emailInput.keyup(function() {
			var inputReg = /^([\w-\.]+@([\w]+\.)+[\w]{2,4})?$/;
			var input = $(this).val();

			var showValidityDiv = $(this).next();
			if (!input || !input.match(inputReg)) {
				showValidityDiv.html('not match!');
				showValidityDiv.css('color', 'red');
			} else {
				showValidityDiv.html('match');
				showValidityDiv.css('color', 'green');
			}
		});

		nicknameInput.keyup(function() {
			var input = $(this).val();
			var showValidityDiv = $(this).next();

			if (!input) {
				showValidityDiv.html('닉네임을 입력하세요.');
				showValidityDiv.css('color', 'red');
			} else {
				showValidityDiv.html('입력 완료');
				showValidityDiv.css('color', 'green');
			}
		});

		passwordInput.keyup(function() {
			var input = $(this).val();
			var showValidityDiv = $(this).next();

			if (!input) {
				showValidityDiv.html('비밀번호 입력하세요.');
				showValidityDiv.css('color', 'red');
			} else {
				showValidityDiv.html('입력 완료');
				showValidityDiv.css('color', 'green');
			}
		}); 
	}*/

	//addValidateEvent();
</script>
</html>