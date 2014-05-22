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
			$('#contentsContainerDiv')
					.append(
							'<div class="row contents">'
									+ '<div class="time" ><p class="date">'
									+ this.replace(time)
									+ '</p></div>'
									+ '<p class="contentsP">'
									+ this.replace(content)
									+ '</p>'
									+ '<img class="trash" src="image/trash_orange.png" >'
									+ '</div>').children(':last').hide()
					.fadeIn('slow');
			// if (imgName === undefined) {
			// $('#contentsContainerDiv').append(
			// '<div class="row contents">'
			// + '<div class="time" ><p class="date">' + time
			// + '</p></div>' + '<p class="contentsP">' + content
			// + '</p></div>').children(':last').hide().fadeIn(
			// 'slow');
		} else {
			$('#contentsContainerDiv')
					.append(
							'<div class="row contents">'
									+ '<div class="time"><p class="date">'
									+ replace(time)
									+ '</p></div>'
									+ '<img class="contentsImg" src="image/'
									+ imgName
									+ '" style="margin-right:5px;">'
									+ '<p class="contentsP">'
									+ replace(content)
									+ '</p>'
									+ '<img class="trash" src="image/trash_orange.png" >'
									+ '</div>').children(':last').hide()
					.fadeIn('slow');
		}
		// } else {
		// $('#contentsContainerDiv').append(
		// '<div class="row contents">'
		// + '<div class="time"><p class="date">' + time
		// + '</p></div>'
		// + '<img class="contentsImg" src="image/' + imgName
		// + '" style="margin-right:5px;">'
		// + '<p class="contentsP">' + content + '</p></div>')
		// .children(':last').hide().fadeIn('slow');
		// }

		$('html, body').animate({
			scrollTop : $(document).height()
		}, "fast", this.setHeightForTimeDiv);
		$('#prevImg').hide();
		$('body form')[0].reset();
		$('#textlength').text("0/200");
		// $('.row.contents').append('<img class="trash"
		// src="image/trash_orange.png" >'); //으앙
		// $('#contentsContainerDiv').append('<img class="trash"
		// src="image/trash_orange.png" >'); //으앙
		// $('#contentsContainerDiv').append('kakakakakakakakako');
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

	// addTrash : function() {
	// $('.row contents').each(
	// function() {
	// $(this).append('<img class="trash" src="image/trash_orange.png" >');
	// });
	// },

	// addTrash : function() {
	// $('.row contents').append('<img class="trash"
	// src="image/trash_orange.png" >');
	//			
	// },

	replace : function(val) {
		val = val.replace('<', '&lt;');
		val = val.replace('>', '&gt;');
		
		return val;
	},

	init : function() {
		$('#submitBtn').click(this.submitArticle.bind(this));
		this.setHeightForTimeDiv();
		this.addTrash();
		$('body').hide().delay(200).fadeIn('slow'); // check
	}
}

var myCalendar = {
	checkHasToday : function() {
		$
				.ajax({
					url : "/today/getList",
					dataType : "json",
					success : function(data) {
						$
								.each(
										$('td.fc-day'),
										function(i, val) {
											$
													.each(
															data,
															function(j, today) {
																if ($(this)
																		.attr(
																				'data-date') == today.date
																		.toString()) {
																	$(this)
																			.css(
																					'background-color',
																					'#fdf6ec')
																			.css('border-radius', '50%');
																	$(this)
																			.click(
																					function() {
																						window.location.href = "/today/"
																								+ today.id;
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
