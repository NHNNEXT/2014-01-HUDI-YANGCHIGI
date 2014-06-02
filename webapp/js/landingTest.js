var user = 'jehyeok';
test('test loginAjax', function() {
	TimerMock.replace();
	TimerMock.reset();
	
	$.mockjax({
		url : 'user/login',
		type : 'POST',
		responseText : 'success'
	});

	window.auth.loginForm.hide();
	equal(window.auth.loginForm.is(':hidden'), true);
	window.auth.loginBtn.click();
	TimerMock.flow(1000);

	equal(window.auth.loginForm.css('display'), 'block');
	equal($.mockjax.mockedAjaxCalls().length, 1,
			'Initially there are no saved ajax calls')
	$.mockjaxClear();
});

asyncTest('login fail & show popover', function() {
	TimerMock.replace();
	TimerMock.reset();
	
	$.mockjax({
		url : 'user/login',
		type : 'POST',
		responseText : 'fail'
	});
	
	console.log('loginBtn.next().is(div): ' + auth.loginBtn.next().is('div'));
	window.auth.loginBtn.click();
	
	TimerMock.flow(800);
	equal(window.auth.loginBtn.next().is('div'), true);
	
	TimerMock.flow(3000);
	equal(window.auth.loginBtn.next().is('div'), false);
	
	start();
	$.mockjaxClear();
});
//
//asyncTest('test setTimeout', function() {
//	setTimeout(function() {
//		ok(true);
//	}, 400)
//	
//	tick(500);
//	start();
//});

