$(document).ready(function() {
	//로그인, 로그아웃 관리 객체
	var auth = {
		loginForm : $('#loginForm'),
		loginBtn : $('#loginBtn'),
		logoutBtn : $('#logoutBtn'),
		popoverTimer : '',

		// 로그인 되어있으면 true 리턴
		isLogged : function() {
			// ${user}정보는 세션에 저장되어 있음
			//return '' !== '${user}';
			return !!user;
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
			// 로그인 or 로그아웃 성공
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
				this.loginBtn.popover({
					content : '로그인 정보가 틀렸어요.'
				});
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
				showValidityDiv : $('#emailInput').next(),
				warnDuplicateMsg : '이미 사용중인 이메일 입니다.',
				valid : false
			},
			nickname : {
				input : $('#nicknameInput'),
				warnMsg : '닉네임을 입력하세요.',
				showValidityDiv : $('#nicknameInput').next(),
				warnDuplicateMsg : '이미 사용중인 닉네임 입니다.',
				valid : false
			},
			password : {
				input : $('#passwordInput'),
				warnMsg : '비밀번호를 입력하세요.',
				showValidityDiv : $('#passwordInput').next(),
				valid : false
			}
		},
		signUpForm : $('#signUpForm'),
		popoverTimer : '',
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
						data : data,
					}).done(
						function(msg) {
						console.log(msg);
						if ('success' === msg) {
							this.signUpForm[0].reset();
							// display none 
							$('.validate-form').hide();
							$('#myModal').modal('hide');
						} else if ('duplicate email' === msg) {
							var email = this.formInputs['email'];
							email['showValidityDiv']
									.text(email['warnDuplicateMsg']);
							email['showValidityDiv']
									.css('color', 'red');
						} else if ('duplicate nickname' === msg) {
							var nickname = this.formInputs['nickname'];
							nickname['showValidityDiv']
									.text(nickname['warnDuplicateMsg']);
							nickname['showValidityDiv']
									.css('color', 'red');
						}
					}.bind(this));
				} else {
					// 로그인 정보가 올바르지 않을 경우, 제대로 입력하지 않은 input 경고
					$.each(this.formInputs, function(key, value) {
						if (!value['valid']) {
							value['showValidityDiv'].show();
							value['showValidityDiv']
									.html(value['warnMsg']);
							value['showValidityDiv']
									.css('color', 'red');
						}
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
			// display!
			showValidityDiv.show();
			
			if (!input || !input.match(value['inputReg'])) {
				showValidityDiv.text(value['warnMsg']);
				showValidityDiv.css('color', 'red');
				value['valid'] = false;
			} else {
				showValidityDiv.html('입력 완료');
				showValidityDiv.css('color', 'green');
				value['valid'] = true;
			}
		},
		addClearFormEvent : function() {
			var closeBtn = $('#closeBtn');
			closeBtn.click(function() {
				this.signUpForm[0].reset();
				// display none 
				$('.validate-form').hide();
			}.bind(this));
		},
		init : function() {
			this.addSignUpEvent();
			this.addValidateEvent();
			this.addClearFormEvent();
		}
	}
	
	window.auth = auth;
	window.signUp = signUp;
	
	auth.init();
	signUp.init();
});