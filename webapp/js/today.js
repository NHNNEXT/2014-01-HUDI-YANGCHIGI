

function setHeightForTimeDiv() {
	$('.time').each(
			function(i) {
				var newHeight = parseInt($(this).parent().height())
						+ parseInt($(this).parent().css('margin-top')) * 2;
				$(this).height(newHeight);
			});
}


function load() {
	$('#uploadCommentBtn').click(submitComment);
	
	setHeightForTimeDiv();
}

window.onload = load;