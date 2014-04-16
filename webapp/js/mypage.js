var img_name;

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#img_prev').attr('src', e.target.result).height(50).css('display', 'inline');
			
		};
		img_name = input.files[0].name;
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
	// 일단 이미지가 없을때만 ajax로..
	if(img_name == undefined){
		
		var contentsVal = $('#contentInput').val();
//		$("body form").submit(function(e){
			
			if (contentsVal == "") {
				alert("내용이 없습니다");
			}
			else {
				$.ajax({
					type : "POST",
					url : "writearticle",
					data : {
						content : contentsVal,
						img : img_name,
					}
				}).done(function(date) {
					
					$('#contentsContainerDiv').append('<div class="row contentsDiv">'
							+ '<div class="timeDiv" ><p class="date">'
							+ date
							+ '</p></div>'
							+ contentsVal
							+ '</div>').children(':last').hide().fadeIn('slow');
					$('html, body').animate({ scrollTop: $(document).height() }, "fast");
					$('#img_prev').attr('src', '').css('display', 'none');
					$('.form-horizontal')[0].reset();
					load();
				});
				
			}
		
		
	}
	else {	
		$('body form').submit();
	}
}

function load() {
	$('.timeDiv').each(function(i){
		$(this).height($(this).parent().height() + 45);
		
	});
}

window.onload = load;