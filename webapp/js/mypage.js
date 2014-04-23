
var cur_img_name;

function readImg() {
	var input = $('#fileInput')[0];
	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#prevImg').attr('src', e.target.result).height(50).show("slow");
			
		};
		cur_img_name = input.files[0].name;
		reader.readAsDataURL(input.files[0]);
	} else {
		// if cancel selection
		$('#prevImg').hide("slow");
	}
}

function submitArticle() {
	// 일단 이미지가 없을때만 ajax로..
//	if(cur_img_name == undefined){
		
		var contentsVal = $('#contentInput').val();
		var isPrivate = $('#isPrivateIpnut:checked').val();
//		$("body form").submit(function(e){
		var postData = $('body form').serializeArray();
			debugger;
			if (contentsVal == "") {
				alert("내용이 없습니다");
			}
			else {
				$.ajax({
					type : "POST",
					url : "/mypage/write",
					mimeType:"multipart/form-data",
					data : postData
//					{
//						content : contentsVal,
//						isPrivate: isPrivate
//					}
				}).done(function(date) {
					
					$('#contentsContainerDiv').append('<div class="row contentsDiv">'
							+ '<div class="timeDiv" ><p class="date">'
							+ date
							+ '</p></div>'
							+ contentsVal
							+ '</div>').children(':last').hide().fadeIn('slow');
					$('html, body').animate({ scrollTop: $(document).height() }, "fast");
					$('#prevImg').hide();
					$('.form-horizontal')[0].reset();
					setHeightForTimeDiv();
				});
			}
//	}
//	else {	
//		$('body form').submit();
//	}
}

function setHeightForTimeDiv() {
	$('.timeDiv').each(function(i){
		var newHeight = parseInt($(this).parent().height()) + parseInt($(this).parent().css('margin-top')) * 2;
		
		$(this).height(newHeight);		
	});
}






function load() {
//	$('#submitBtn').click(submitArticle);
	
	$('#uploadImg').click(function(){
		$('#fileInput').click();
	});
	$('#fileInput').change(readImg);
	
	setHeightForTimeDiv();
	
}

window.onload = load;
