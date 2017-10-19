<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.css"
	type="text/css" media="screen, projection">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/asterisk.css">
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<div>
	<form:form modelAttribute="user"
		action="/laboratorysixteen/registration" method="post">
		<h3>Registration page</h3>
		<%@ include file="/WEB-INF/view/validationErrors.jsp"%>
		<div>
			<%@ include file="/WEB-INF/view/loggedUser.jsp"%>
		</div>
		<table class="table">
			<tr>
				<td>Login</td>
				<td><form:input path="login" required="required" /><span
					class="required">*</span></td>
			</tr>
			<%@ include file="/WEB-INF/view/addUpdateUserForm.jsp"%>
		</table>
    <spring:bind path="recaptchaResponse">
    <div class="form-group ${status.error ? 'has-error' : ''}">
        <div id="g-recaptcha"></div>
        <form:hidden path="recaptchaResponse"/>
        <script type="text/javascript">
            var onloadCallback = function() {
                grecaptcha.render('g-recaptcha', {
                    'sitekey' : '<c:out value="6LcqCzQUAAAAANg3ymnvkiwBXikAOXRL8YwD5ZaS" />',
                    'callback' : function(response) {
                        document.getElementById('recaptchaResponse').value = response;
                    },
                    'theme' : 'light'
                });
            }
        </script>
        <script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&render=explicit" async defer></script>
        <form:errors path="recaptchaResponse" class="help-block"/>
    </div>
    </spring:bind>
		<table>
			<tr>
				<td><button class="btn btn-primary" type="submit" value="<spring:message code="save"/>">Submit</button></td>
				<td><a class="btn btn-primary" href="/laboratorysixteen/login"
					role="button">Cancel</a></td>
			</tr>
		</table>
	</form:form>
</div>