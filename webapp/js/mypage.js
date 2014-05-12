var readImg = {
	cur_img_name : null,

	read : function() {
		var input = $('#fileInput')[0];

		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#prevImg').attr('src', e.target.result).height(50).show(
						"slow");
			};
			cur_img_name = input.files[0].name;
			reader.readAsDataURL(input.files[0]);
		} else {
			// if cancel selection
			$('#prevImg').hide("slow");
		}
	},

	init : function() {
		$('#uploadImg').click(function() {
			$('#fileInput').click();
		});
		$('#fileInput').change(this.read);
	}
}

var write = {
	submitArticle : function() {
		var content = $('#contentInput').val();
		var isPrivate = $('#isPrivateIpnut:checked').val();
		var imgName = $('#fileInput').val().split('\\')[2];

		if (hasError(content, imgName))
			return;

		if (content == "") {
			alert("내용이 없습니다");
		} else {
			var option = {
				type : "POST",
				url : "/mypage/write",
				mimeType : "multipart/form-data",
				data : $('body form').serialize(),
				success : function(time) {
					this.addDivAfterAjax(content, time, imgName);
				}.bind(this)
			};
			$('body form').ajaxSubmit(option);
		}
	},

	addDivAfterAjax : function(content, time, imgName) {
		if (imgName === undefined) {
			$('#contentsContainerDiv').append(
					'<div class="row contents">'
							+ '<div class="time" ><p class="date">' + time
							+ '</p></div>' + '<p class="contentsP">' + content
							+ '</p></div>').children(':last').hide().fadeIn(
					'slow');
		} else {
			$('#contentsContainerDiv').append(
					'<div class="row contents">'
							+ '<div class="time"><p class="date">' + time
							+ '</p></div>'
							+ '<img class="contentsImg" src="image/' + imgName
							+ '" style="margin-right:5px;">'
							+ '<p class="contentsP">' + content + '</p></div>')
					.children(':last').hide().fadeIn('slow');
		}

		$('html, body').animate({
			scrollTop : $(document).height()
		}, "fast", this.setHeightForTimeDiv);
		$('#prevImg').hide();
		$('body form')[0].reset();
		$('#textlength').text("0/200");
	},

	setHeightForTimeDiv : function() {
		$('.time').each(
				function(i) {
					var newHeight = parseInt($(this).parent().height(), 10)
							+ parseInt($(this).parent().css('margin-top'), 10)
							* 2;
					$(this).height(newHeight);
				});
	},
	init : function() {
		$('#submitBtn').click(this.submitArticle.bind(this));
		this.setHeightForTimeDiv();
		$('body').hide().delay(200).fadeIn('slow'); // check
	}
}

var myCalendar = {
	checkHasToday : function() {
		$.ajax({
			url : "/today/getList",
			dataType : "json",
			success : function(data) {
				$.each($('td.fc-day'),function(i, val) {
						$.each(data,function(j, today) {
							if ($(this).attr('data-date') == today.date.toString()) {
								$(this).css('background-color','rgb(88, 194, 147)');
								$(this).click(function() {
									window.location.href = "/today/"+ today.id;
									});
								}
				}.bind(this));
				});
			}
		});
	},

	init : function() {
		$('#calendarDiv').fullCalendar({
			header : {
				left : '',
				center : 'title',
				right : 'prev,next'
			},

			dayClick : function(date) {
				// 하루를 추가해 한국 날짜로 변환
				date.setDate(date.getDate() + 1);
				// 오늘 날짜
				var curDate = new Date().toISOString().substring(0, 10);
				// 선택한 날짜
				var clickedDate = date.toISOString().substring(0, 10);

			}

		});

		this.checkHasToday();
		$('.fc-button').click(this.checkHasToday);

	}
}

function hasError(content, imgName) {
	if (content.indexOf("<script>") != -1
			|| (imgName && imgName.indexOf("<script>") != -1)) {
		alert("정확한 문자를 입력하세요");
		return true;
	}
	if (content.length > 200 || (imgName && imgName.length > 200)) {
		alert("200자 미만의 글자만 입력 가능합니다");
		return true;
	}

	return false;
}

function getLength() {
	$('#textlength').text($('#contentInput').val().length + "/200");
}

function load() {
	myCalendar.init();
	readImg.init();
	write.init();
	$('#contentInput').keyup(getLength);

}

window.onload = load;
