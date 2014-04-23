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
					<!-- <form class="form-horizontal" role="form" method="post"	action="/mypage/write" enctype="multipart/form-data"> -->
					<form action="/mypage/write" method="post" enctype="multipart/form-data"> 
						<textarea name="content" id="contentInput" class="form-control"
							rows="3" placeholder="생각을 기록하세요" style="resize: none;"></textarea>
						<input name="imgName" type='file' id="fileInput" accept="image" style="display: none;" />
						<input name="isPrivate" id="isPrivateIpnut" type="checkbox"/>비공개

						<div id="imgFormDiv">
							<img id="uploadImg" src="icon/addimage.png" style="height: 30px;" />
							<img id="prevImg" src="#" alt="your image" style="display: none;" />
						</div>

						<div id="writeMenuDiv">
							<div class="form-group">
								<button type="submit" class="btn btn-success" id="submitBtn">Submit</button>
							</div>
						</div>
					</form>
				</div>

				<div id="contentsContainerDiv">
					<c:forEach items="${ideaList}" var="idea">
						<div class="row contentsDiv">
							<div class="timeDiv">
								<p class="date">${idea.time}</p>
							</div>
							<c:if test="${!empty idea.imgName}">
								<img class="contentsImg" src="img/${idea.imgName}"
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
</html>
