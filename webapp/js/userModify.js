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
		data : $('body form').serialize(),	
		success : function() {
			alert("변경되었습니다.새로고침 하세여...(미안)") //하면 될랑가 
			//변경시 함수 추가(addDiv같은거) 
			$()
		}	
	};
	$('body form').ajaxSubmit(option);
}



//function readImg() {
//	var input = $('#uploadImg')[0];  
//	if(input.files&&input.files[0]) {
//		var reader = new FileReader();
//		
//		reader.onload = function(e) {
//			$("#uploadImg").attr('src', e.target.result).width(150).height(150).show("slow");
//		};
//		cur_img_name = input.files[0].name;
//		reader.readAsDataURL(input.files[0]);
//	} else {
//		$("#uploadImg").hide("slow")
//	}
//
//}

function readImg() {
	var input = $('#fileInput')[0];  
	if(input.files&&input.files[0]) {
		var reader = new FileReader();
		
		reader.onload = function(e) {
			$("#box").css('background-image','url("'+e.target.result+'")');
		};
		cur_img_name = input.files[0].name;
		reader.readAsDataURL(input.files[0]);
	} else {
		$("#box").hide("slow")
	}
}
//function readImg() {
//	var input = $('#fileInput')[0];  
//	if(input.files&&input.files[0]) {
//		var reader = new FileReader();
//		
//		reader.onload = function(e) {
//			$("#prevImg").attr('src', e.target.result).width(150).height(150).show("slow");
//		};
//		cur_img_name = input.files[0].name;
//		reader.readAsDataURL(input.files[0]);
//	} else {
//		$("#prevImg").hide("slow")
//	}
//	
//}

function hoverImg() {
	$('#uploadImg').hover(function(){
		$('#overlay').css("visibility","visible");
	}, function(){
		//$('#overlay').css("visibility","hidden");
		$('#overlay').fadeOut('fast',1);
		//$('#overlay').unbind('mouseenter mouseleave');
	});
}


function load() {
	$('#submitBtn').click(submitArticle); //제출 누르기
	//$('#uploadImg').click();
	hoverImg();
	$('#overlay').click(function() { //이미지 업로드 누르는거
//	$('#uploadImg').click(function() { //이미지 업로드 누르는거
		$('#fileInput').click(); 
	});
//	$('#uploadImg').change(readImg);
	$('#fileInput').change(readImg);

}

window.onload = load;