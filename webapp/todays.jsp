<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/todays.css">
<link rel="stylesheet" href="css/today_modal.css">
<link
	href="https://fontastic.s3.amazonaws.com/atUbsU72QhekCwuoLXgtCC/icons.css"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="today_modal.jspf" />
	<%@include file="header.jspf"%>
	<div id="contentContainerDiv">
		<div id="baloon">
			<div id="todayContainerDiv">
				<c:forEach items="${todayAndIdeasMap}" var="today">
					<div class="today">
						<input type="hidden" value="${today.key.id}">
						<%-- <div class="date">Date: ${today.key.date}</div> --%>
						<div class="profile">
							<img src="/image/${today.value['user'].thumbnail}" />
							${today.value['user'].nickname}
							<div class="like">Like: ${today.key.like}</div>
						</div>
						<div class="viewport">
							<div class="contents">
								<c:forEach items="${today.value['ideas']}" var="idea">
									<div class="content">${idea.content}</div>
								</c:forEach>
							</div>
						</div>
						<button class="btn btn-default next-idea change-idea"></button>
						<button class="btn btn-default pre-idea change-idea"></button>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@include file="footer.jspf"%>

</body>
<script src="/js/header.js"></script>
<script>
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
			/* window.location = 'today/' + todayId; */
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
			}, 'slow');
		}
	});

	$('.next-idea').click(
			function(e) {
				e.stopPropagation();
				var contents = $(e.target.parentNode).find('.contents');
				var contentsLeft = parseInt(contents.css('left'));
				var contentLastChildLeft = parseInt(contents.find(
						'.content:last-child').css('left'));
				if (contentsLeft !== (-1) * contentLastChildLeft) {
					contents.filter(':not(:animated)').animate({
						'left' : '-=100%'
					}, 'slow');
				}
			});

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
</script>
<script
	src="http://localhost:8001/target/target-script-min.js#anonymous"></script>
</html>
