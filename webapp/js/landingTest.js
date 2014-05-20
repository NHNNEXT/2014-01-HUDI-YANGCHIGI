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
	
	$.ajax({
		type : "POST",
		url : "user/login",
	}).done(function(msg) {
		equal(msg, 'success')
		start();
	});
	
//	window.auth.loginBtn.click();
	ok(true, 'success');
});