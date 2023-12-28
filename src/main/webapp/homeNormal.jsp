<%@page import="com.buyNowKart.resources.UrlConstants"%>
<%@page import="com.buyNowKart.entities.User"%>
<%@page import="com.buyNowKart.resources.Constants"%>
<%
User userForHome = (User) session.getAttribute(Constants.current_user);
if (userForHome == null) {
	session.setAttribute(Constants.MESSAGE, "You are not logged in. Please login.");
	response.sendRedirect(UrlConstants.LOGIN_PAGE);
} else if (userForHome.getUserType().equals(Constants.admin_user)) {
	response.sendRedirect(UrlConstants.HOME_ADMIN);
} else {
	session.setAttribute(UrlConstants.REDIRECT_URL, UrlConstants.HOME_NORMAL);
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User home</title>
<%@include file="components/Common.jsp"%>
</head>
<body>
	<%@include file="components/navbar.jsp"%>
	<h2>normal users</h2>
</body>
</html>