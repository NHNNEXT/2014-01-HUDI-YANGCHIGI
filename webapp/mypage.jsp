<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">

<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/mypage.css">
<script src="js/mypage.js"></script>

</head>
<body>
	<div class="container">

		<div id="myPageAllDiv" class="row">
			<div id="calendarDiv" class="col-xs-4">calendar</div>

			<div id="rightSectionDiv" class="col-xs-7">
				<div id="writeFormDiv" class="row">					
					      <textarea id="contentInput" class="form-control" rows="3" placeholder="생각을 기록하세요" style="resize:none;"></textarea>
					<form class="form-horizontal" role="form">
					
					<input type='file' id="fileInput" accept="image" onchange="readURL(this);" style="display: none;" />
					<div id="imgFormDiv">
						<img src="icon/addimage.png" onclick="chooseFile()"
							style="height: 30px;" /> <span id="previewPane"> <img
							id="img_prev" src="#" alt="your image" style="display: none;" />
						</span>
					</div>
					<div id="writeMenuDiv">
						<div class="form-group">
						      <button type="button" class="btn btn-success" id="submitBtn" onclick="submitArticle()">Submit</button>
					  </div>
					</div>					
					</form>
				</div>
				<div id="contentsDiv" class="row">contents</div>
				<div id="contentsDiv" class="row">contents</div>
				<div id="contentsDiv" class="row">contents</div>
				<div id="contentsDiv" class="row">contents</div>
				<div id="contentsDiv" class="row">contents</div>
			</div>
		</div>
	</div>
</body>
</html>