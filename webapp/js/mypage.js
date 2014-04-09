var img_name;

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#img_prev').attr('src', e.target.result).height(50).css(
					'display', 'inline');
		};
		img_name = input.files[0].name;
		console.log(img_name);
		reader.readAsDataURL(input.files[0]);
	} else {
		// if cancel selection
		$('#img_prev').attr('src', '').css('display', 'none');
	}
}

function chooseFile() {
	$('#fileInput').click();
}

function submitArticle() {
	var val = $('#contentInput').val();
	
	if (val == "") {
		alert("내용이 없습니다");
	}
	else {
		$.ajax({
			type : "POST",
			url : "writearticle",
			data : {
				contents : $('#contentInput').val(),
				img : img_name
			}
		}).done(function(msg) {
			$('#contentsContainerDiv').prepend('<div class="row contentsDiv newContents">'
					+ $('#contentInput').val()
					+ '</div>')
					.hide().fadeIn('slow');

			
			$('#img_prev').attr('src', '').css('display', 'none');
			$('.form-horizontal')[0].reset();
		});
	}
}