<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
	<div class="container">
		<form:form modelAttribute="user" action="/laboratorysixteen/login"
			method="post">
			<c:if test="${error != null}">
				<div class="alert alert-danger" role="alert">
					<spring:hasBindErrors name="user">
						<spring:message message="${error}" />
					</spring:hasBindErrors>
				</div>
			</c:if>
			<div class="form-group">
				<label for="Login">Login</label>
				<form:input path="login" name="login" type="text" class="form-control"
					aria-describedby="emailHelp" placeholder="Login" required="required"/>
				<small id="loginHelp" class="form-text text-muted">We'll
					never share your login with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="Passowrd">Password</label>
				<form:input path="password" name="password" type="password" class="form-control"
					placeholder="Password" required="required"/>
			</div>
			<button type="submit" value="submit" class="btn btn-primary">Sign in</button>
			<a class="btn btn-primary" href="/laboratorysixteen/registrationForm"
			role="button">Registration</a>
		</form:form>
	</div>
	<footer> </footer>
</body>
</html>
