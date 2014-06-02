var Header = {
	logoutBtn : $('#logoutBtn'),
	headerBtns : {
		showTodays : {
			btn : $('#showTodaysBtn'),
			path : '/today'
		},
		showMyPage : {
			btn : $('#showMyPageBtn'),
			path : '/'
		},
		showSetting : {
			btn : $('#showSettingBtn'),
			path : '/usermodify'
		},
	},
	
	addLogoutEvent : function() {
		this.logoutBtn.click(function() {
			$.ajax({
				type : "POST",
				url : "user/logout"
			}).done(function(msg) {
				console.log(msg);
				if ('success' === msg) {
					window.location = '/';
				}
			});
		});
	},
	addHeaderEvents : function() {
		$.each(this.headerBtns, function(key, value) {
			value.btn.click(this.changeTab.bind(this, value.path));
		}.bind(this));
	},
	changeTab : function(path) {
		window.location = path;
	},
	init: function() {
		this.addLogoutEvent();
		this.addHeaderEvents();
	}
}
Header.init();