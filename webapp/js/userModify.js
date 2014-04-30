//var userModifyForm = $('#userModifyForm');
//var userModifyBtn = $('#userModifyBtn');
//var modifyBtn = $('#modifyBtn');

//modifyBtn.click(function() {
//	var nickname = $('#userModifyForm :input');
//
//	$.ajax({
//		type : "POST",
//		url : "usermodify/uploadThumbnail",
//		mimeType : "multipart/form-data",
//		data : nickname,
//		contentType : false,
//		processData : false
//	}).done(function(msg) {
//		console.log('success');
//	})
//});

var cur_img_name;

//제출 누르면 나오는거 
function submitArticle() {
	var nickname = $('#nicknameInput').val();
	var imgName = $('#fileInput').val().split('\\')[2]; //readImg()하면 fileInput에 넣어준다.
	
	//닉네임 중복제거도 해야함
	var option={
		type : "POST",
		url : "/usermodify/upload",
		mimeType : "multipart/form-data",
		data : $('body form').serialize(),	//이건 뭐지
		success : function(time) {
			alert("변경되었습니다.새로고침 하세여...(미안)") //하면 될랑가 
			//addDivAfterAjax(content, time, imgName);
		}	
	};
	$('body form').ajaxSubmit(option);
}

function addDivAfterAjax(content, time, imgName) {
	
}

function readImg() {
	var input = $('#fileInput')[0]; //뭐가 어케되는걸까 
	if(input.files&&input.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#prevImg").attr('src', e.target.result).height(300).show("slow");
		};
		cur_img_name = input.files[0].name;
		reader.readAsDataURL(input.files[0]);
	} else {
		$("#prevImg").hide("slow")
	}

}

function load() {
	$('#submitBtn').click(submitArticle); //제출 누르기
	$('#uploadImg').click(function() { //이미지 업로드 누르는거
		$('#fileInput').click(); 
	});
	$('#fileInput').change(readImg);

}

window.onload = load;