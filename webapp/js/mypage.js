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
	var content = $('#contentInput').val();
	var isPrivate = $('#isPrivateIpnut:checked').val();
	var imgName = $('#fileInput').val().split('\\')[2];

	if(hasError(content, imgName)) return;
	
	if (content == "") {
		alert("내용이 없습니다");
	} else {
		var option={
			type : "POST",
			url : "/mypage/write",
			mimeType : "multipart/form-data",
			data : $('body form').serialize(),
			success : function(time){
				addDivAfterAjax(content,time,imgName);				
			}
		};
		$('body form').ajaxSubmit(option);
	}
}



function addDivAfterAjax(content, time, imgName){
	if(imgName === undefined){
		$('#contentsContainerDiv').append(
				'<div class="row contentsDiv">'
						+ '<div class="timeDiv" ><p class="date">'
						+ time + '</p></div>' 		
						+ '<p class="contentsP">'
						+ content + '</p></div>')
				.children(':last').hide().fadeIn('slow');
	}
	else{
		$('#contentsContainerDiv').append(
				'<div class="row contentsDiv">'
						+ '<div class="timeDiv"><p class="date">'
						+ time + '</p></div>' 		
						+ '<img class="contentsImg" src="img/' + imgName
						+ '" style="margin-right:5px;">'
						+ '<p class="contentsP">'
						+ content + '</p></div>')
				.children(':last').hide().fadeIn('slow');
	}
	
	$('html, body').animate({scrollTop : $(document).height()}, "fast", setHeightForTimeDiv);
	$('#prevImg').hide();
	$('body form')[0].reset();
}

function setHeightForTimeDiv() {
	$('.timeDiv').each(
			function(i) {
				var newHeight = parseInt($(this).parent().height())
						+ parseInt($(this).parent().css('margin-top')) * 2;
				$(this).height(newHeight);
			});
}

function hasError(content, imgName){
	if(content.indexOf("<script>") != -1 || imgName.indexOf("<script>") != -1){
		alert("정확한 문자를 입력하세요");
		return true;
	}
	
	return false;
}

function load() {
	$('#submitBtn').click(submitArticle);

	$('#uploadImg').click(function() {
		$('#fileInput').click();
	});
	$('#fileInput').change(readImg);

	setHeightForTimeDiv();
	$('body').hide().delay(200).fadeIn('slow');

}

window.onload = load;
