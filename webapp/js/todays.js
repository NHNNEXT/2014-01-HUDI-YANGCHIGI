var todays = $('.today');

$.each(todays, function(key, value) {
	var todayId = $(value).find(':input').val();
	$(value).click(function() {

		$.ajax({
			url : "/today/" + todayId,
			type : "GET",
			success : function(data) {
				data = data.split('<body>')[1];
				data = data.split('</body>')[0];
				console.log(data);
				$('#todayModal .modal-body').html(data);

				$('#todayModal').on('shown.bs.modal', function(e) {
					todayLoad();
				});
				$('#todayModal').on('hidden.bs.modal', function(e) {
					$('#todayModal').off('shown.bs.modal');
				});

				$('#todayModal').modal();
			}

		});
	});
});

var contents = $('.contents');

$.each(contents, function(key, value) {
	setIdeaContents(value.children);
});

function setIdeaContents(contents) {
	$.each(contents, function(key, value) {
		$(value).css('left', 100 * key + '%');
	});
};

todays.hover(function(e) {
	if ($(e.currentTarget).find('.content').length !== 1) {
		$(e.currentTarget).find('.change-idea').show();
	}
}, function(e) {
	$(e.currentTarget).find('.change-idea').hide();
});

$('.pre-idea').click(function(e) {
	e.stopPropagation();
	var contents = $(e.target.parentNode).find('.contents');
	if (contents.css('left') !== '0px') {
		contents.filter(':not(:animated)').animate({
			'left' : '+=100%'
		}, 300);
	}
});

$('.next-idea').click(function(e) {
	e.stopPropagation();
	var contents = $(e.target.parentNode).find('.contents');
	var contentsLeft = parseInt(contents.css('left'));
	var contentLastChildLeft = parseInt(contents.find(
			'.content:last-child').css('left'));
	if (contentsLeft !== (-1) * contentLastChildLeft) {
		contents.filter(':not(:animated)').animate({
			'left' : '-=100%'
		}, 300);
	}
});

// 터치 이벤트
$.each(todays, function(key, value) {
	var movedLength = 0;
	var today;
	var firstPos;
	var secondPos;
	var touches = {};

	value.addEventListener('touchstart', function(e) {
		console.log('touch start');
		console.log(e.touches);
		$('.change-idea').hide();

		if (e.touches.length === 2) {
			firstPos = e.touches[0].clientX;
			secondPos = e.touches[1] && e.touches[1].clientX;
			touches[e.touches[0].identifier] = [e.touches[0], firstPos];
			touches[e.touches[1].identifier] = [e.touches[1], secondPos];

			movedLength = 0;
			console.log('firstPos: ' + firstPos);
			console.log('secondPos: ' + secondPos);
			today = $(e.currentTarget);
		}
	}, false);
	function touchstart(){
		
	}
	function touchmove(){
		
	}
	
	touchmove({
		changedTouches : [],
		touches : [],
		
	})

	value.addEventListener('touchmove', function(e) {
		if (e.touches.length === 2 && e.changedTouches.length === 2) {
			e.preventDefault();

			// 이동한 손가락 위치
			var firstTouchPos = e.touches[0].clientX;
			var secondTouchPos = e.touches[1].clientX;

			// 이동하기 전 손가락 위치
			firstPos = touches[e.touches[0].identifier][1];
			secondPos = touches[e.touches[1].identifier][1];

			// 이동한 거리
			movedLength += (firstTouchPos - firstPos)
					+ (secondTouchPos - secondPos) / 2;
			// console.log('movedLength: ' + movedLength);

			// 이동한 손가락 위치 업데이트
			touches[e.touches[0].identifier][1] = firstTouchPos;
			touches[e.touches[1].identifier][1] = secondTouchPos;

			if (getAbs(movedLength) > today.width() / 2) {
				if (movedLength < 0) {
					today.find('.next-idea').click();
				} else {
					today.find('.pre-idea').click();
				}
			}
		}
	}, false);
});

function getAbs(num) {
	if (num >= 0)
		return num;
	else
		return (-1) * num;
}

//$(document).keypress(function(e) { 
//    if (e.keyCode == 27) { 
//        $('#todayModal').fadeOut(500);
//        //or
//        window.close();
//    } 
//});

$(document).keyup(function(event){
	if (event.keyCode == 27){
		// Close the modal/menu
//		$('#todayModal').closeElement();
		//$('#todayModal').fadeOut(500);

        	//  Return focus to the element that invoked it 
		//$('a[href=#todayModal]').focus();
		//$('body').removeClass("modal-open");
//		$('.modal-backdrop').removeClass("fade");
//		$('.modal-backdrop').detach();
		$('#todayModal').modal('hide');
	}
});

$(document).ready(function(){
	if($('body').hasClass('modal-open')){
		$('body').css({'overflow': 'hidden'});
	}
	
	
    $('#baloon').masonry({
        itemSelector : '.pin',
        isAnimated: true
//        ,
//        isFitWidth: true
    });
});