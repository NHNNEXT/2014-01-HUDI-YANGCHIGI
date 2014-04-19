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
<link rel="stylesheet" href="css/landing.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- Button trigger modal -->
	<div id="signinDiv">
		<button id="signupBtn" class="btn btn-primary" data-toggle="modal"
			data-target="#myModal">SignUp</button>
		<!-- Login form -->
		<jsp:include page="login.jspf" />
		<!-- Logout -->
		<jsp:include page="logout.jspf" />
	</div>
	<!-- Modal -->
	<jsp:include page="signup.jspf" />
</body>

<script>
	//로그인, 로그아웃 관리 객체
	var auth = {
		loginForm : $('#loginForm'),
		loginBtn : $('#loginBtn'),
		logoutBtn : $('#logoutBtn'),
		popoverTimer : '',

		// 로그인 되어있으면 true 리턴
		isLogged : function() {
			// ${user}정보는 세션에 저장되어 있음
			return '' !== '${user}';
		},
		// 로그인 버튼에 로그인 이벤트 add
		addLoginEvent : function() {
			var self = this;
			var callback = this.callback.bind(this, function() {
			});
			this.loginBtn.click(function() {
				var data = $('#loginForm :input');
				$.ajax({
					type : "POST",
					url : "user/login",
					data : data
				}).done(callback);
			})
		},
		// 로그아웃 버튼에 로그아웃 이벤트 add
		addLogoutEvent : function() {
			var callback = this.callback.bind(this, function() {
				this.loginForm[0].reset();
			}.bind(this));
			$('#logoutBtn').click(function() {
				$.ajax({
					type : "POST",
					url : "user/logout"
				}).done(callback);
			});
		},
		callback : function(fp, msg) {
			if ("success" === msg) {
				if (this.loginForm.is(':hidden')) {
					this.loginForm.show();
					this.logoutBtn.hide();
				} else {
					window.location = '/';
				}
				fp && fp();
				// 로그인 실패
			} else {
				clearTimeout(this.popoverTimer);
				// 로그인 실패 popover
				this.loginBtn.popover('destroy');
				this.loginBtn.popover('show');
				// 1초 후 popover 제거 
				this.popoverTimer = setTimeout(function() {
					this.loginBtn.popover('destroy');
				}.bind(this), 1000);
			}
		},
		// 위에서 정의한 이벤트 add
		init : function() {
			if (this.isLogged())
				this.loginForm.hide();
			else
				this.logoutBtn.hide();

			this.addLoginEvent();
			this.addLogoutEvent();
		}
	}

	var signUp = {
		formInputs : {
			email : {
				input : $('#emailInput'),
				inputReg : /^([\w-\.]+@([\w]+\.)+[\w]{2,4})?$/,
				warnMsg : '이메일 형식에 맞게 입력하세요.',
				valid : false
			},
			nickname : {
				input : $('#nicknameInput'),
				warnMsg : '닉네임을 입력하세요.',
				valid : false
			},
			password : {
				input : $('#passwordInput'),
				warnMsg : '비밀번호를 입력하세요.',
				valid : false
			}
		},
		
		// form 입력이 유효한가?
		isValid : function() {
			var valid = true;
			$.each(this.formInputs, function(key, value) {
				valid = valid && value['valid'];
			});
			return valid;
		},
		// 회원가입 이벤트 add 
		addSignUpEvent : function() {
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
			}.bind(this));
		},
		// form validate 이벤트 add
		addValidateEvent : function() {
			$.each(this.formInputs, function(key, value) {
				var callback = this.callback.bind(value['input'], value);
				value['input'].keyup(callback);
			}.bind(this));
		},
		callback : function(value, e) {
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
		},
		init : function() {
			this.addSignUpEvent();
			this.addValidateEvent();
		}
	}
	
	auth.init();
	signUp.init();
</script>
</html>
