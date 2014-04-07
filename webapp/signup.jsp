<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Sign Up!</h4>
			</div>
			<div class="modal-body">
				<form id="signUpForm" class="form-horizontal" role="form">
					<div class="form-group">
						<label for="inputEmail" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-10">
							<input type="email" name="email" class="form-control"
								id="inputEmail" placeholder="Email">
						</div>
					</div>
					<div class="form-group">
						<label for="inputNickname" class="col-sm-2 control-label">Nickname</label>
						<div class="col-sm-10">
							<input type="text" name="nickname" class="form-control"
								id="inputNickname" placeholder="Nickname">
						</div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-sm-2 control-label">Password</label>
						<div class="col-sm-10">
							<input type="password" name="password" class="form-control"
								id="inputPassword" placeholder="Password">
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" id="signUpBtn" class="btn btn-primary">SignUp</button>
			</div>
		</div>
	</div>
</div>