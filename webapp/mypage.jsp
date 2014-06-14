<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Seize</title>
	<!-- 파비콘 -->
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon">
	<link rel="icon" href="image/favicon.ico" type="image/x-icon">	
	<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
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
	<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,900|Francois+One|Indie+Flower' rel='stylesheet' type='text/css'>	
	<link href='http://fonts.googleapis.com/css?family=League+Script|Codystar|Indie+Flower|Damion|Seaweed+Script' rel='stylesheet' type='text/css'>	
	
	<link rel="stylesheet" href="css/mypage.css">
	 <link rel="stylesheet" href="css/today_modal.css">
	<link rel="stylesheet" href="css/header.css">
	<!-- <link rel="stylesheet" href="css/today.css"> -->
<link href="https://fontastic.s3.amazonaws.com/atUbsU72QhekCwuoLXgtCC/icons.css" rel="stylesheet">
	<script src="js/mypage.js"></script>
</head>
<body>
	<%@include file="header.jspf"%>
	<div id="baloon">
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
							style="display: none;" /> 
						<div id="formButtons">
							<p id="textlength">0/200</p>
							<img id="uploadImg" class="formIcon" src="icon/photo.png" />	
							<img id="lockIcon" class="formIcon" src="icon/unlock.png" />	
							<img id="submitBtn" class="formIcon" src="icon/submit.png" />	
						</div>	
						<br><input name="isPrivate"
							id="isPrivateInput" type="hidden" value="false"/>
						<div id="imgFormDiv">
							<!-- <img id="uploadImg" src="icon/addimage.png" style="height: 30px;" /> -->
							<img id="prevImg" src="#" alt="your image" style="display: none;" />
						</div>

						<div id="writeMenuDiv">
							<div class="form-group">
								<!-- <button type="button" class="btn btn-success" id="submitBtn">Submit</button> -->
							</div>
						</div>
					</form>
				</div>

				<div id="contentsContainerDiv">
				<div class="Btime"></div>
					<c:forEach items="${ideaList}" var="idea">
						<div class="row">
							<input type="hidden" id="ideaId" value="${idea.id }">
							<div class="time">
								<c:set var ="_hour" value="${idea.time.substring(0,2)}" />
								<c:set var ="_minute" value="${idea.time.substring(3,5)}" />
								<fmt:parseNumber var="hour" integerOnly="true" value="${_hour}" />
								<fmt:parseNumber var="minute" integerOnly="true" value="${_minute}" />
								<c:set var ="a" value="${minute * 6}" />
								<c:set var ="o" value="${hour % 12 / 12 * 360}" />
								<div class="clock">
									<div  class="hour" style="-webkit-transform: rotate(${o}deg);" ></div>
									<div class="minute" style="-webkit-transform: rotate(${a}deg);"></div>
								</div>
							</div>
							<div class="contents">
								<c:if test="${!empty idea.imgName}">
									<img class="contentsImg" src="image/${idea.imgName}"
										style="margin-right: 5px;">
								</c:if>
								<p class="contentsP">${idea.content}</p>
								<img class="trash" src="image/trash_orange.png"
									data-idea-id="${idea.id }">
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<button id="addTodayBtn" class="btn btn-warning" style="float: right">Create
		Today</button>
	<%@include file="footer.jspf"%>
	<jsp:include page="today_modal.jspf" />
</body>
<script src="/js/header.js"></script>

<script>

/* $(".clock").css("background-color", "blue");
$(".hour").css("transform", "rotate(" + 10 + "deg)");
$(".minute").css("transform", "rotate(" + 40 + "deg)"); */

/* var Shour = ${idea.time}.substring(0,2);
var Sminutes = ${idea.time}.substring(3,5);
var hour = parseInt(Shour);
var minutes = parseInt(Sminutes);
var a = minutes * 6; 
var o = hour % 12 / 12 * 360;
$(".clock").css("background-color", "red !important");
$(".hour").css("transform", "rotate(" + o + "deg)");
$(".minute").css("transform", "rotate(" + a + "deg)"); */

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
	
 	$('#lockIcon').click(function() {
		var lockStatus = $(this).attr("src");
		if(lockStatus === "/icon/unlock.png"){  //image sprite
			$(this).attr("src", "/icon/lock.png");
			$('#isPrivateInput').val(true);
		}else{
			$(this).attr("src", "/icon/unlock.png"); 
			$('#isPrivateInput').val(false);
		}
	}); 
 	
	$('.trash').click(function() {
 		var ideaId = $(this).data("idea-id");
		$(this).parent().parent().slideUp("fast",function(){
			$(this).remove();
		});
		

		$(this).parent().parent().slideUp("fast", function() {
			$(this).remove();
		});
		
		$.ajax({
			type : "POST",
			url : "/mypage/ideaDelete",
			data : {
				ideaId : ideaId
			},
		}).done(function(msg) {
			console.log(msg);
		});
	});

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
	
</script>
</html>
