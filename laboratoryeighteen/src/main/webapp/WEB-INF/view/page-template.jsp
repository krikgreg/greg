<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.css"
	type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/asterisk.css">
</head>
<body>
	<header> </header>
	<section class="main container">
		<sitemesh:write property='body' />
	</section>
	<footer> </footer>
</body>
</html>