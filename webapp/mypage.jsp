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
					<form class="form-horizontal" role="form" method=post
						action="writearticle" enctype="multipart/form-data">
						<textarea name="content" id="contentInput" class="form-control"
							rows="3" placeholder="생각을 기록하세요" style="resize: none;"></textarea>
						<input name="imgName" type='file' id="fileInput" accept="image"
							onchange="readURL(this);" style="display: none;" />
						<input name="isPrivate" id="isPrivateIpnut" type="checkbox">비공개</input>

						<div id="imgFormDiv">
							<img src="icon/addimage.png" onclick="chooseFile()" /> <img
								id="prevImg" src="#" alt="your image" style="display: none;" />
						</div>

						<button type="button" class="btn btn-success" id="submitBtn"
							onclick="submitArticle()">Submit</button>
					</form>
				</div>
				<div id="contentsContainerDiv">
					<c:forEach items="${todaySet}" var="today">
						<div class="row contentsDiv">
							<div class="timeDiv">
								<p class="date">${today.date}</p>
							</div>
							<c:if test="${empty today.imgName}">${today.content}</c:if>
							<c:if test="${!empty today.imgName}">
								<img class="contentsImg" src="img/${today.imgName}">
							${today.content}</c:if>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
</body>
</html>