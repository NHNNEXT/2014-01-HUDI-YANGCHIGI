var cur_img_name;

function readImg() {
	var input = $('#fileInput')[0];
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#prevImg').attr('src', e.target.result).height(50).css('display', 'inline');
			
		};
		cur_img_name = input.files[0].name;
		reader.readAsDataURL(input.files[0]);
	} else {
		// if cancel selection
		$('#prevImg').css('display', 'none');
	}
}

function submitArticle() {
	// 일단 이미지가 없을때만 ajax로..
	if(cur_img_name == undefined){
		
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
						img_name : cur_img_name,
					}
				}).done(function(time) {
					
					$('#contentsContainerDiv').append('<div class="row contentsDiv">'
							+ '<div class="timeDiv" ><p class="date">'
							+ time
							+ '</p></div>'
							+ contentsVal
							+ '</div>').children(':last').hide().fadeIn('slow');
					$('html, body').animate({ scrollTop: $(document).height() }, "fast");
					$('#prevImg').css('display', 'none');
					$('.form-horizontal')[0].reset();
					setHeightForTimeDiv();
				});
				
			}
		
		
	}
	else {	
		$('body form').submit();
	}
}

function setHeightForTimeDiv() {
	$('.timeDiv').each(function(i){
		$(this).height($(this).parent().height() + 45);		
	});
}


function load() {
	$('#submitBtn').click(submitArticle);
	
	$('#uploadImg').click(function(){
		$('#fileInput').click();
	});
	$('#fileInput').change(readImg);
	
	
	setHeightForTimeDiv();
}

window.onload = load;