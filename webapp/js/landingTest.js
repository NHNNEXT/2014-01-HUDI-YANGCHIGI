test("hello test", function() {
	ok(1 == "1", "Passed!");
});

test("strcit", function() {
	deepEqual({'a': 1}, {'a': 1});
});

asyncTest('test loginAjax', function() {
	$.mockjax({
	    url: 'user/login',
	    type: 'POST',
	    responseText: 'success'
	});
	
//	$.ajax({
//		type : "POST",
//		url : "user/login",
//	}).done(function(msg) {
//		equal(msg, 'success')
//		start();
//	});
	window.auth.loginForm.hide();
	equal(window.auth.loginForm.is(':hidden'), true);
	window.auth.loginBtn.click();
	equal(window.auth.loginForm.css('display'), 'block');
	start();
	equal($.mockjax.mockedAjaxCalls().length, 1, 'Initially there are no saved ajax calls')
	$.mockjaxClear();
});

asyncTest('test setTimeout', function() {
	$.mockjax({
		url: 'user/login',
		type: 'POST',
		responseText: 'fail'
	});
	
	window.auth.loginBtn.click();
	
	start();
	$.mockjaxClear();
});