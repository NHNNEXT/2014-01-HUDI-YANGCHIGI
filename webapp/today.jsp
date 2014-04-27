<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/today.css">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<script src="/js/today.js"></script>
</head>
<body>
	<!-- 단상 목록 들어가는 곳 -->
	<div id="contentContainerDiv">
		<c:forEach items="${ideaList}" var="idea">
			<div class="contentsDiv">
				<span class="timeDiv">
					<p class="date">${idea.time}</p>
				</span>
				<span>
					<c:if test="${!empty idea.imgName}">
						<img class="contentsImg" src="img/${idea.imgName}"
							style="margin-right: 5px;">
					</c:if>
					<p class="contentsP">${idea.content}</p>
				</span>
			</div>
		</c:forEach>
	</div>
	<!-- 날짜, 공감 수, 작성자 닉네임, 댓글 들어가는 곳 -->
	<div id="asideDiv">
		<div id="dateDiv">
			<p class="date">2014</p>
			<p class="date">0415</p>
		</div>
		<div id="profileDiv"></div>
		<div id="likeDiv">
			<form>
				<button type="button" id="likeBtn" class="btn btn-default">Like</button>
				<span id="likeSpan" class="badge">${today.like}</span> <input
					type="hidden" id="likeInput" value="${today.like}" />
			</form>
		</div>

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
	<script>
		var like = {
			likeBtn : $('#likeBtn'),
			likeInput : $('#likeInput'),
			likeSpan : $('#likeSpan'),
			isLiked : ${isLiked},
			
			checkLikeTodayOrNot : function() {
				if (this.isLiked) this.likeBtn.addClass('btn-primary');
			},
			addLikeEvent : function() {
				this.likeBtn.click(function() {
					var like = Number(this.likeSpan.text());
					
					if (this.likeBtn.hasClass('btn-primary')) like -= 1;
					else like += 1;
					
					$.ajax({
						processData : true,
						type : "POST",
						url : "",
						data : {
							like : like
						}
					}).done(function(like) {
						like = Number(like);
						this.likeSpan.text(like);
						this.likeBtn.toggleClass('btn-primary');
					}.bind(this));
				}.bind(this));
			},
			init : function() {
				this.checkLikeTodayOrNot();
				this.addLikeEvent();
			}
		}
		like.init();
	</script>
</body>
</html>