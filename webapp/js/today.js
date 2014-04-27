function submitComment() {
		
		var contentsVal = $('#commentInput').val();
		
		if (contentsVal == "") {
			alert("내용이 없습니다");
		}
		else {
			$.ajax({
				type : "POST",
				url : "/today/writecomment",
				data : {
					content : contentsVal
				}
			}).done(function(time) {
				
				$('#commentDiv').append('<div class="comment-set">'
						+ contentsVal
						+ '</div>').children(':last').hide().fadeIn('slow');
				$('html, body').animate({ scrollTop: $(document).height() }, "fast");
				$('#commentInput').val("");
			});
		}
}

function setHeightForTimeDiv() {
	$('.timeDiv').each(
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