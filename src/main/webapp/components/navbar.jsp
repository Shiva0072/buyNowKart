<%@page import="com.buyNowKart.resources.UrlConstants"%>
<%@ page isELIgnored="false" %>
<%@page import="com.buyNowKart.entities.User"%>
<%@page import="com.buyNowKart.resources.Constants"%>
<%
User userForNav = (User) session.getAttribute(Constants.current_user);
String redirectUrlForNav = (String) session.getAttribute(UrlConstants.REDIRECT_URL);
%>
<nav class="navbar navbar-expand-lg navbar-dark custom-bg">
	<div class="container">
		<a class="navbar-brand" href="index.jsp">BuyKart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home </a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-toggle="dropdown" aria-expanded="false"> Categories </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<%
				if (userForNav == null) {
				%>
				<li class="nav-item active"><a class="nav-link"
					href="login.jsp">Login </a></li>
				<li class="nav-item active"><a class="nav-link"
					href="register.jsp">Register </a></li>
				<%
				} else {
				%>
				<li class="nav-item active"><a class="nav-link" href=<%= redirectUrlForNav %>> <%=userForNav.getName()%>
				</a></li>
				<li class="nav-item active"><a class="nav-link"
					href="LogoutServlet">Logout </a></li>
				<%}%>
			</ul>
		</div>

	</div>
</nav>