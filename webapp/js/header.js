// header events
function addLogoutEvent() {
	$('#logoutBtn').click(function() {
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
}

function addShowTodaysEvent() {
	$('#showTodaysBtn').click(function() {
		window.location = '/today';
	});
}

function addShowMyPageEvent() {
	$('#showMyPageBtn').click(function() {
		window.location = '/';
	});
}
function addShowSettingEvent() {
	$('#showSettingBtn').click(function() {
		window.location = '/usermodify';
	});
}

addLogoutEvent();
addShowTodaysEvent();
addShowMyPageEvent();
addShowSettingEvent();

// 각 내용 받아오기
/*
 * var contents = $('.contents'); $.each(contents, function(key, value) {
 * console.log('time: ' + $(value).find('.time').text()); console.log('imgName: ' +
 * $(value).find('img').attr('src')); console.log('content: ' +
 * $(value).find('.contentsP').text()); });
 */
