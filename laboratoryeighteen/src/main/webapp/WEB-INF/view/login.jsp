<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<div class="panel panel-info small-center-block">
		<form action="<c:url value='j_spring_security_check' />" method="post">
			<c:if test="${sessionScope.SPRING_SECURITY_LAST_EXCEPTION != null}">
				<div class="text-danger">
					${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }
					<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
				</div>
			</c:if>
			<div class="form-group">
				<label for="Login">Login</label>
				<input name="j_username" type="text" class="form-control"
					aria-describedby="emailHelp" placeholder="Login" required="required"/>
				<small id="loginHelp" class="form-text text-muted">We'll
					never share your login with anyone else.</small>
			</div>
			<div class="form-group">
				<label for="Passowrd">Password</label>
				<input name="j_password" type="password" class="form-control"
					placeholder="Password" required="required"/>
			</div>
			<button type="submit" class="btn btn-primary">Sign in</button>
			<a class="btn btn-primary" href="/laboratoryeighteen/registrationForm"
			role="button">Registration</a>
		</form >
	</div>