var Header = {
	logoutBtn : $('#logoutBtn'),
	showTodaysBtn : $('#showTodaysBtn'),
	showMyPageBtn : $('#showMyPageBtn'),
	showSettingBtn : $('#showSettingBtn'),
	
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
	addShowTodaysEvent : function() {
		this.showTodaysBtn.click(function() {
			window.location = '/today';
		});
	},
	addShowMyPageEvent : function() {
		this.showMyPageBtn.click(function() {
			window.location = '/';
		});
	},
	addShowSettingEvent : function() {
		this.showSettingBtn.click(function() {
			window.location = '/usermodify';
		});
	},
	init: function() {
		this.addLogoutEvent();
		this.addShowTodaysEvent();
		this.addShowMyPageEvent();
		this.addShowSettingEvent();
	}
}
Header.init();