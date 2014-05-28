<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>Today page</title>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/css/today.css">

<!-- today.css 와 mypage.css 통합하며 중복되는 것들은 todayl_modal.css로 옮기는 중 -->
<link rel="stylesheet" href="/css/mypage.css">
<script src="/js/today.js"></script>
</head>
<body>
	<div class="container">
		<!-- 단상 목록 들어가는 곳 -->
		<div id="contentContainerDiv">
			<c:forEach items="${ideaList}" var="idea">
				<div class="row">
					<span class="time">
						<p class="timeWrapper">${idea.time}</p>
					</span>
					<div class="contents">
						<c:if test="${!empty idea.imgName}">
							<img class="contentsImg" src="/image/${idea.imgName}"
								style="margin-right: 5px;">
						</c:if>
						<p class="contentsP">${idea.content}</p>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- 날짜, 공감 수, 작성자 닉네임, 댓글 들어가는 곳 -->
		<div id="asideDiv">
			<div id="dateDiv">
				<p class="dateoftoday">${year}</p>
				<p class="dateoftoday">${month}${day}</p>
			</div>
			<div id="likeDiv">
				<form>
					<button type="button" id="likeBtn" class="btn btn-default">Like</button>
					<span id="likeSpan" class="badge">${today.like}</span> <input
						type="hidden" id="likeInput" value="${today.like}" />
				</form>
			</div>
			<div id="profileDiv">
				<img src="/image/${user.thumbnail}" /> Nickname: ${user.nickname}
			</div>

			<div id="commentDiv">
				<c:forEach items="${commList}" var="comm">

					<div class="comment-set">
						<input type="hidden" value="${comm.userId}">
						<%-- <img id="commentProfile" src="/image/${user.thumbnail}" />
                                    <p id="nickname">nickname</p> --%>
						<p id="content">${comm.content}</p>
					</div>
				</c:forEach>
			</div>

			<div id="writeCommentDiv">
				<input type="text" id="commentInput" class="form-control"
					maxlength="100">
				<button id="uploadCommentBtn" class="btn btn-default" type="button">입력</button>
			</div>
		</div>
	</div>
	<script>
                    function submitComment() {

                        var contentsVal = $('#commentInput').val();

                        if (contentsVal == "") {
                            alert("내용이 없습니다");
                        }
                        else {
                            $.ajax({
                                type : "POST",
                                url : "/today/" + ${today.id} + "/writecomment",

                                   data : {
                                  	 content : contentsVal
                                   }
                                   }).done(function(time) {

                                $('#commentDiv').append('<div class="comment-set">'
                                		+'<img id="commentProfile" src="/image/${me.thumbnail}" /><p id="nickname">${me.nickname}</p>'
                                                        + replace(contentsVal)
                                                        + '</div>').children(':last').hide().fadeIn('slow');
                                $('#commentInput').val("");
                                $('#commentDiv').animate({
                                    scrollTop : $('#commentDiv')[0].scrollHeight
                                }, "fast");
                            });
                        }
                    }

                    var replace = function(val) {
                        val = val.replace('<', '&lt;');
                        val = val.replace('>', '&gt;');

                        return val;
                    }
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
                                    var like = parseInt(this.likeSpan.text(), 10);

                                    if (this.likeBtn.hasClass('btn-primary')) like -= 1;
                                    else like += 1;

                                    $.ajax({
                                        processData : true,
                                        type : "POST",
                                        url : "/today/" + ${today.id},
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
                    function setHeightForTimeDiv() {
                        $('.time').each(
                            function(i) {
                                var newHeight = parseInt($(this).next().height(), 10)
                                + parseInt($(this).next().css('margin-top'), 10) * 2;
                                $(this).height(newHeight);
                                $(this).css('margin-top', $(this).next().css('margin-top'));
                            });
                    }
                    function loadUserInfo(){
                        $('.comment-set').each(
                            function(){
                                $.ajax({
                                    type:'GET',
                                    url:'/today/getuser/' + $(this).children('input').val()
                                }).done(function(data){
                                	var html = '<img id="commentProfile" src="/image/'+ data.split('&')[1]+'" /><p id="nickname">'+ data.split('&')[0]+ '</p>';
                                    $(this).prepend(html);
                                }.bind(this))
                            }
                        );
                    }

                    function todayLoad() {
                        $('#uploadCommentBtn').click(submitComment);
                        $('#commentInput').keydown(function(e){
                            if(e.keyCode == 13){
                                submitComment();
                            }
                        });
                        setHeightForTimeDiv();
                        like.init();
                        loadUserInfo();
                    }

                </script>
</body>
</html>
