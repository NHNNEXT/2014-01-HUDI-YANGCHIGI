<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<title>Seize</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<!-- 파비콘 -->
	<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon">
	<link rel="icon" href="image/favicon.ico" type="image/x-icon">	
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
<script src="/js/todays.js"></script>
<script
	src="http://10.73.43.107:8001/target/target-script-min.js#anonymous"></script>
</html>
