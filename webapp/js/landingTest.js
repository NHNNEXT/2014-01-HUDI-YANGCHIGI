test("hello test", function() {
	ok(1 == "1", "Passed!");
});

test("strcit", function() {
	deepEqual({
		'a' : 1
	}, {
		'a' : 1
	});
});

var user = 'jehyeok';

asyncTest('test loginAjax', function() {
	$.mockjax({
		url : 'user/login',
		type : 'POST',
		responseText : 'success'
	});

	window.auth.loginForm.hide();
	equal(window.auth.loginForm.is(':hidden'), true);
	window.auth.loginBtn.click();
	// window.auth.loginForm.show();
	equal(window.auth.loginForm.css('display'), 'block');
	start();
	equal($.mockjax.mockedAjaxCalls().length, 1,
			'Initially there are no saved ajax calls')
	$.mockjaxClear();
});

asyncTest('login fail & show popover', function() {
	$.mockjax({
		url : 'user/login',
		type : 'POST',
		responseText : 'fail'
	});

	window.auth.loginBtn.click();
	// window.auth.loginBtn.popover('show');
	tick(1000);
	equal(window.auth.loginBtn.next().is('div'), true);
	tick(2000);
	equal(window.auth.loginBtn.next().is('div'), false);
	
	start();
	$.mockjaxClear();
});

asyncTest('test settTimeout', function() {
	setTimeout(function() {
		ok(true);
	}, 400)
	
	tick(500);
	start();
});