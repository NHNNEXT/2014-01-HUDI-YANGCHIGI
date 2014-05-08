<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Modify</title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<link rel="stylesheet" href="css/usermodify.css">
<link rel="stylesheet" href="css/header.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script src="js/userModify.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script> 
</head>
<body>
	<div id="header">
		<img id="logo" src="image/logo_grass.png" />
		<div id="headerBarDiv">
			<div id="btnsDiv">
				<ul id="btnsUl">
					<li id="showMyPageBtn" class="menu-btn"><button class="btn btn-default btn-lg">MyPage</button></li>
					<li id="showTodaysBtn" class="menu-btn"><button class="btn btn-default btn-lg">Todays</button></li>
					<li id="showSettingBtn" class="menu-btn"><button class="btn btn-default btn-lg">Settings</button></li>
					<li id="logoutBtn" class="menu-btn"><button class="btn btn-default btn-lg">Logout</button></li>
				</ul>
				<button id="addTodayBtn" class="btn btn-warning" style="float: right">Create Today</button>
			</div>
		</div>
	</div>	
	
	
	<div id="baloon">
		<p id="triangle"></p>
		<h1>Setting</h1>
		<form id="userModifyForm" method="post" action="usermodify/upload" enctype="multipart/form-data">
			<input name="imgName" type='file' id="fileInput" accept="image/*" style="display: none;" />
	 		<%-- <h2>nickname: ${nickname}</h2>
			<input type="text" name="nickname" id="nicknameInput">
			
			
			<h2>thumbnailName: ${thumbnailName}</h2>
			<img src="img/${thumbnailName}" width=100 height=100></img> 
			<h1>바꾸고싶은 사진</h1>
			<div id="imgFormDiv">
				<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" />
				<img id="prevImg" src="#" alt="your image" style="display: none;" />
			</div>  --%>
			
			<img src="image/${thumbnailName}" id="thumbnail" width=150 height=150></img> 
			<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" />
			<img id="prevImg" src="#" alt="your image" style="display: none;" />
	
			<h3><div>nickname: ${nickname} -> <input type="text" name="nickname" id="nicknameInput"></div></h3>
			<!-- <input type="submit" value="업로드"> -->
			<!-- <input id="userModifyBtn" type="button" value="업로드"> -->
			
			
			<!-- 제출 -->
			<div id="writeMenuDiv">
				<div class="form-group">
					<button type="button" class="btn btn-success" id="submitBtn">변경</button>
				</div>
			</div>
	
			<!-- <p>메시지: <input type="text" name="msg"> </p>
			<p>패스워드: <input type="password" name="pwd"> </p>
			<p> <input type="submit" value="전송"> </p> -->
		</form>
	</div>
	<div id="footer">
		<ul class="footer_links">
			<li>©Seize, Inc</li>
			<li>Team Yangchigi</li>
			<li>NHN NEXT</li>
			<li>2014 human design project</li>
			<li>help</li>
		</ul>
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
</script>
</html>