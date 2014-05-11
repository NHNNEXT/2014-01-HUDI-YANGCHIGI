<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Page</title>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script src="http://malsup.github.com/jquery.form.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.min.js"></script>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.css">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/1.6.4/fullcalendar.print.css">

<link rel="stylesheet" href="css/mypage.css">
<link rel="stylesheet" href="css/header.css">
<script src="js/mypage.js"></script>

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
			<button id="addTodayBtn" class="btn btn-warning" style="float: right">Create
				Today</button>
		</div>
	</div>
	<div class="container">
		<div id="myPageAllDiv" class="row">
			<div id="calendarDiv" class="col-md-4"></div>

			<div id="rightSectionDiv" class="col-md-7">
				<div id="writeFormDiv" class="row">
					<form action="/mypage/write" method="post"
						enctype="multipart/form-data">
						<textarea maxlength="200" name="content" id="contentInput"
							class="form-control" rows="3" placeholder="생각을 기록하세요"
							style="resize: none;"></textarea>
						<input name="imgName" type='file' id="fileInput" accept="image/*"
							style="display: none;" /> <input name="isPrivate"
							id="isPrivateIpnut" type="checkbox" />비공개
						<p id="textlength">0/200</p>
						<div id="imgFormDiv">
							<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" />
							<img id="prevImg" src="#" alt="your image" style="display: none;" />
						</div>

						<div id="writeMenuDiv">
							<div class="form-group">
								<button type="button" class="btn btn-success" id="submitBtn">Submit</button>
							</div>
						</div>
					</form>
				</div>

				<div id="contentsContainerDiv">
					<c:forEach items="${ideaList}" var="idea">
						<div class="row contents">
							<div class="time">
								<p class="date">${idea.time}</p>
							</div>
							<c:if test="${!empty idea.imgName}">
								<img class="contentsImg" src="image/${idea.imgName}"
									style="margin-right: 5px;">
							</c:if>
							<p class="contentsP">${idea.content}</p>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</div>
</body>
<script>
	// header events
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

	// 각 내용 받아오기	
	/* var contents = $('.contents');
	$.each(contents, function(key, value) {
		console.log('time: ' + $(value).find('.time').text());
		console.log('imgName: ' + $(value).find('img').attr('src'));
		console.log('content: ' + $(value).find('.contentsP').text());
	}); */

	var addTodayBtn = $('#addTodayBtn');
	addTodayBtn.click(function() {
		$.ajax({
			type : "POST",
			url : "today",
		}).done(function(msg) {
			if ('success' === msg) {
				alert('투데이 생성');
			}
		});
	});

	//test calendar
	$(document).ready(
			function() {
				$('#calendarDiv').fullCalendar(
						{
							header: {
								left: '',
								center: 'title',
								right: 'prev,next'
							},
							
							dayClick : function(date) {
								// 하루를 추가해 한국 날짜로 변환
								date.setDate(date.getDate() + 1);
								// 오늘 날짜
								var curDate = new Date().toISOString().substring(0,
										10);
								// 선택한 날짜
								var clickedDate = date.toISOString().substring(
										0, 10);

								// 클릭한 날짜가 오늘보다 작아야 투데이 페이지로 이동가능하도록 한다
								// 오늘 날짜꺼는 이미 오늘 입력되고 있는 중이므로 이하가 아닌 미만이다.
								if (clickedDate < curDate){
									$.ajax({
										type:'GET',
										url:'/today/get',
										data:{
											'date' : clickedDate
										},
										success : function(todayid){
											window.location.href = "/today/" + todayid;
										}
									});
									
								}
							}
						});
				
				$.each($('td.fc-day'), function(i,val){
					$.ajax({
						type:'GET',
						url:'/today/get',
						data:{
							'date' : $(this).attr('data-date')
						},
						success : function(todayid){
							$(this).css('background-color' , 'rgb(88, 194, 147)')
						}.bind(this)
					});
				});
			})
</script>
</html>
