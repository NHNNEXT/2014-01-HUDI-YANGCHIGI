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

function load() {
	$('#uploadCommentBtn').click(submitComment);
}

window.onload = load;