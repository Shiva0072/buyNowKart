<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="com.buyNowKart.entities.Product"%>
<%@page import="com.buyNowKart.entities.Category"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.buyNowKart.helper.FactoryProvider"%>
<%@page import="com.mysql.cj.xdevapi.SessionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="components/Common.jsp"%>
<html>
<body>
	<%@include file="components/navbar.jsp"%>
	<h2>Hello World!</h2>
	<h2>Creating session factory</h2>
	<%
	out.println(FactoryProvider.getSessionFactory());

	/* Session mysession = FactoryProvider.getSessionFactory().openSession();
	Category catg = new Category("Mobiles","top sellling phones");
	List<Product> ls = new ArrayList<>();

	Product pdt1 = new Product("Moto G60",10,15000);
	ls.add(pdt1);
	catg.setPdtsList(ls);
	Transaction transaction = mysession.beginTransaction();
	mysession.save(pdt1);
	mysession.save(catg);
	transaction.commit();
	mysession.close(); */

	/* Product pdt2 = new Product("Iphone",9,60000); */
	%>
</body>
</html>
