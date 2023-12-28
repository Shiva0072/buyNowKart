<%@page import="com.buyNowKart.resources.UrlConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	User userForLogin = (User) session.getAttribute(Constants.current_user);
	if(userForLogin != null){
		String redirectUrl = (String) session.getAttribute(UrlConstants.REDIRECT_URL);
		response.sendRedirect(redirectUrl);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login User</title>
<%@include file="components/Common.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-md-4 offset-md-4">
				<div class="card mt-3">
					<div class="card-body">
						<div class="heading-font text-center">
							<h2>Login</h2>
						</div>
						<%@include file="components/Message.jsp" %>
						<form action="LoginServlet">
							<div class="form-group">
								<label for="user_email">Email address</label> <input
									type="email" class="form-control" id="user_email"
									aria-describedby="emailHelp" placeholder="Enter email" name="user_email">
							</div>
							<div class="form-group">
								<label for="user_password">Password</label> <input
									type="password" class="form-control" id="user_password"
									placeholder="Password" name="user_password">
							</div>
							<div class="text-center mb-2">
								<small><a href="register.jsp">Click here to create new account</a></small>
							</div>
							<div class="text-center ">
								<button type="submit" class="btn btn-outline-success">Login</button>
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>