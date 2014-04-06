<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/js/bootstrap.min.js"></script>
	<script src="js/holder.js"></script>
	
	
<title>Insert title here</title>


<script type="text/javascript">
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img_prev').attr('src', e.target.result).height(200);
			};

			reader.readAsDataURL(input.files[0]);
		} else {
			var img = input.value;
			$('#img_prev').attr('src', img).height(200);
		}
	}
	
</script>
</head>
<body>
	<input type='file' onchange="readURL(this);" />
	<br />
	<span id="previewPane">
		<img id="img_prev" data-src="holder.js/100%x200" src="#" alt="your image" />
	</span>


</body>
</html>