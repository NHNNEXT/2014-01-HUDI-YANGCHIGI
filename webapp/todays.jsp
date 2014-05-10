<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/todays.css">
</head>
<body>
	<div id="headerBarDiv">
		<div id="btnsDiv">
			<ul id="btnsUl">
				<li id="showMyPageBtn" class="menu-btn"><button
						class="btn btn-default btn-lg">MyPage</button></li>
				<li id="showTodaysBtn" class="menu-btn"><button
						class="btn btn-default btn-lg">Todays</button></li>
				<li id="showSettingBtn" class="menu-btn"><button
						class="btn btn-default btn-lg">Settings</button></li>
				<li id="logoutBtn" class="menu-btn"><button
						class="btn btn-default btn-lg">Logout</button></li>
			</ul>
		</div>
	</div>
	<div id="contentContainer">
		<div id="todayContainer">
			<c:forEach items="${todayAndIdeasMap}" var="today">
				<div class="today">
					<input type="hidden" value="${today.key.id}">
					<div class="date">Date: ${today.key.date}</div>
					<div class="like">Like: ${today.key.like}</div>
					<div class="contents">
						<c:forEach items="${today.value}" var="idea">
							<div class="content">${idea.content}</div>
						</c:forEach>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
<script>
	//header events
	function addLogoutEvent() {
		$('#logoutBtn').click(function() {
			$.ajax({
				type : "POST",
				url : "user/logout"
			}).done(function(msg) {
				console.log(msg);
				if ('success' === msg) {
					window.location = '/';
				}
			});
		});
	}

	function addShowTodaysEvent() {
		$('#showTodaysBtn').click(function() {
			window.location = '/today';
		});
	}

	function addShowMyPageEvent() {
		$('#showMyPageBtn').click(function() {
			window.location = '/';
		});
	}
	function addShowSettingEvent() {
		$('#showSettingBtn').click(function() {
			window.location = '/usermodify';
		});
	}
	addLogoutEvent();
	addShowTodaysEvent();
	addShowMyPageEvent();
	addShowSettingEvent();

	var todays = $('.today');
	$.each(todays, function(key, value) {
		var todayId = $(value).find(':input').val();
		$(value).click(function() {
			window.location = 'today/' + todayId;
		});
	});
</script>
</html>