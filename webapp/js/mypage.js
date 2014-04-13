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
	var postData = $(this).serializeArray();
	
	
	var val = $('#contentInput').val();
	$("body form").submit(function(e){
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
//					imgdata : postData
				}
			}).done(function(date) {
				
				$('#contentsContainerDiv').append('<div class="row contentsDiv">'
						+ '<div class="timeDiv" ><p class="date">'
						+ date
						+ '</p></div>'
						+ $('#contentInput').val()
						+ '</div>').children(':last').hide().fadeIn('slow');
				$("html, body").animate({ scrollTop: $(document).height() }, "fast");
				
				$('#img_prev').attr('src', '').css('display', 'none');
				$('.form-horizontal')[0].reset();
			});
			
		}
	});
	
	$('body form').submit();
}