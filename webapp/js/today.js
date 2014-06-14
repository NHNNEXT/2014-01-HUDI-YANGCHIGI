
function setHeightForTimeDiv() {
	$('.time').each(
			function(i) {
				var newHeight = parseInt($(this).next().height(), 10)
						+ parseInt($(this).next().css('margin-top'), 10) * 2;

				//$(this).height(newHeight);
				$(this).height(10);
				$(this).css('margin-top', $(this).next().css('margin-top'));
			});
}

function load() {
	$('#uploadCommentBtn').click(submitComment);

	setHeightForTimeDiv();
}

window.onload = load;