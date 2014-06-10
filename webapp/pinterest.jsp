<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="author" content="Script Tutorials" />
<title>PinPin</title>
<link href="css/pinterest.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.min.js"></script>
<script src="js/jquery.masonry.min.js"></script>
<script src="js/jquery.colorbox-min.js"></script>
<script src="js/pinterest.js"></script>

</head>
<body>

	<!-- header panel -->
	<div class="header_panel">

		<!-- logo -->
		<a href="#" class="logo"></a>

		<!-- search form -->
		<form action="" method="get" class="search">
			<input autocomplete="off" name="q" size="27" placeholder="Search"
				type="text" /> <input name="search" type="submit" />
		</form>

		<!-- navigation menu -->
		<ul class="nav">
			<li><a href="#add_form" id="login_pop">Add +</a></li>
			<li><a href="#">About<span></span></a>
				<ul>
					<li><a href="#">Help</a></li>
					<li><a href="#">Pin It Button</a></li>
					<li><a href="#" target="_blank">For Businesses</a></li>
					<li class="div"><a href="#">Careers</a></li>
					<li><a href="#">Team</a></li>
					<li><a href="#">Blog</a></li>
					<li class="div"><a href="#">Terms of Service</a></li>
					<li><a href="#">Privacy Policy</a></li>
					<li><a href="#">Copyright</a></li>
					<li><a href="#">Trademark</a></li>
				</ul></li>
			<li><a href="#">Profile<span></span></a>
				<ul>
					<li><a href="#">Invite Friends</a></li>
					<li><a href="#">Find Friends</a></li>
					<li class="div"><a href="#">Boards</a></li>
					<li><a href="#">Pins</a></li>
					<li><a href="#">Likes</a></li>
					<li class="div"><a href="#">Settings</a></li>
					<li><a href="#">Logout</a></li>
				</ul></li>
			<li><a
				href="http://www.script-tutorials.com/pinterest-like-script-step-1/">Back
					to tutorial<span></span>
			</a></li>
		</ul>

	</div>

	<!-- upload form -->
	<a href="#x" class="overlay" id="add_form"></a>
	<div class="popup">
		<div class="header">
			<a class="close" href="#close">x</a>
			<h2>Upload a Pin</h2>
		</div>
		<form>
			<input type="file" name="image_file" id="image_file" onchange="" />
		</form>
	</div>

	<!-- main container -->
	<div class="main_container">

		<!-- pin element 1 -->
		<div class="pin">
			<div class="holder">
				<div class="actions" pin_id="1">
					<a href="#" class="button">Repin</a> <a href="#" class="button">Like</a>
					<a href="#" class="button disabled comment_tr">Comment</a>
				</div>
				<a class="image ajax" href="#" title="Photo number 1" pin_id="1">
					<img alt="Photo number 1"
					src="image/tumblr_n5vniaakio1strk4go1_500.jpg">
				</a>
			</div>
			<p class="desc">Photo number 1 description</p>
			<p class="info">
				<span>1 likes</span> <span>1 repins</span>
			</p>
			<form class="comment" method="post" action="">
				<input type="hidden" name="id" value="1" />
				<textarea placeholder="Add a comment..." maxlength="1000"></textarea>
				<button type="button" class="button">Comment</button>
			</form>
		</div>

		<!-- pin element 2 -->
		<div class="pin">
			<div class="holder">
				<div class="actions" pin_id="2">
					<a href="#" class="button">Repin</a> <a href="#" class="button">Like</a>
					<a href="#" class="button comment_tr">Comment</a>
				</div>
				<a class="image ajax" href="#" title="Photo number 2"> <img
					alt="Photo number 2" src="image/default_thumbnail.jpg">
				</a>
			</div>
			<p class="desc">Photo number 2 description</p>
			<p class="info">
				<span>2 likes</span> <span>2 repins</span>
			</p>
			<form class="comment" method="post" action="" style="display: none">
				<input type="hidden" name="id" value="2" />
				<textarea placeholder="Add a comment..." maxlength="1000"></textarea>
				<button type="button" class="button">Comment</button>
			</form>
		</div>

		<!-- pin element 3 -->
		<div class="pin">
			<div class="holder">
				<div class="actions" pin_id="3">
					<a href="#" class="button">Repin</a> <a href="#" class="button">Like</a>
					<a href="#" class="button comment_tr">Comment</a>
				</div>
				<a class="image ajax" href="#" title="Photo number 3"> <img
					alt="Photo number 3" src="image/logo_grass.png">
				</a>
			</div>
			<p class="desc">Photo number 3 description</p>
			<p class="info">
				<span>3 likes</span> <span>3 repins</span>
			</p>
			<form class="comment" method="post" action="" style="display: none">
				<input type="hidden" name="id" value="3" />
				<textarea placeholder="Add a comment..." maxlength="1000"></textarea>
				<button type="button" class="button">Comment</button>
			</form>
		</div>

		<!-- pin element 4 -->
		<div class="pin">
			<div class="holder">
				<div class="actions" pin_id="4">
					<a href="#" class="button">Repin</a> <a href="#" class="button">Like</a>
					<a href="#" class="button comment_tr">Comment</a>
				</div>
				<a class="image ajax" href="#" title="Photo number 4"> <img
					alt="Photo number 4" src="image/JavaRuby_colored.png">
				</a>
			</div>
			<p class="desc">Photo number 4 description</p>
			<p class="info">
				<span>4 likes</span> <span>4 repins</span>
			</p>
			<form class="comment" method="post" action="" style="display: none">
				<input type="hidden" name="id" value="4" />
				<textarea placeholder="Add a comment..." maxlength="1000"></textarea>
				<button type="button" class="button">Comment</button>
			</form>
		</div>

	</div>
</body>
</html>