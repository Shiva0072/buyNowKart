<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Account</title>
<%@include file="components/Common.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="row mt-5 mr-2 ml-2">
		<div class="col-md-4 offset-md-4">

			<div class="card">

				<div class="text-center heading-font">

					<img src="images/registration_icon.png" class="img-fluid mt-3"
						style="width: 20%;" />
					<h2>Create new account</h2>
				</div>

				<div class="card-body">

					<%@include file="components/Message.jsp"%>
					<form action="RegisterServlet">
						<div class="form-group">
							<label for="user_name">Name</label> <input class="form-control"
								id="user_name" placeholder="Enter name" name="user_name">
						</div>

						<div class="form-group">
							<label for="user_email">Email</label> <input class="form-control"
								id="user_email" placeholder="Enter email" type="email"
								name="user_emil">
						</div>

						<div class="form-group">
							<label for="user_password">password</label> <input
								class="form-control" id="user_password"
								placeholder="Enter password" type="password"
								name="user_password">
						</div>

						<div class="form-group">
							<label for="user_phone">phone</label> <input class="form-control"
								id="user_phone" placeholder="Enter mobile no" type="number"
								name="user_mobile">
						</div>

						<div class="form-group">
							<label for="user_address">address</label>
							<textarea class="form-control" id="user_address"
								placeholder="Enter address" rows="3" name="user_address"></textarea>
						</div>

						<div class="container text-center">
							<div>
								<button class="btn btn-outline-success border-0" type="submit">Submit</button>
								<button class="btn btn-outline-warning border-0" type="reset">Reset</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>