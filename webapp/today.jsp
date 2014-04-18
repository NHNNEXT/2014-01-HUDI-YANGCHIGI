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
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">

<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/today.css">
<script src="js/today.js"></script>
</head>
<body>
	<!-- 단상 목록 들어가는 곳 -->
	<div id="contentContainerDiv"></div>
	<!-- 날짜, 공감 수, 작성자 닉네임, 댓글 들어가는 곳 -->
	<div id="asideDiv">
		<div id="dateDiv"></div>
		<div id="profileDiv"></div>
		<div id="likeDiv"></div>
		
		
		
		
		<div id="commentDiv">
			<c:forEach items="${commList}" var="comm">
				<div class="comment-set">${comm.content}</div>
			</c:forEach>

		</div>

			<div id="writeCommentDiv" class="input-group">
				<input type="text" id="commentInput" class="form-control"> <span
					class="input-group-btn">
					<button id="uploadCommentBtn" class="btn btn-success" type="button">Go!</button>
				</span>
			</div>
		
		
	</div>
</body>
</html>