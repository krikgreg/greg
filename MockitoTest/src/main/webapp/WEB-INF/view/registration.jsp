<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<form:form modelAttribute="user"
		action="/laboratoryseventeen/registration" method="post">
		<h3>Registration page</h3>
		<table class="table">
			<tr>
				<td>Login</td>
				<td><form:input path="login" required="required" /><span
					class="required">*</span></td>
				<td><form:errors path="login" class="text-danger" /></td>
			</tr>
			<%@ include file="/WEB-INF/view/addUpdateUserForm.jsp"%>
		</table>
		<script src="https://www.google.com/recaptcha/api.js" async defer></script>
		<div class="g-recaptcha"
			data-sitekey="<c:out value="${recaptchaSiteKey}" />"
			data-theme="light"></div>
			<div class="text-danger" >
            <c:out value="${errorMessage}" escapeXml="true"></c:out>
            </div>
		<table>
			<tr>
				<td><button class="btn btn-primary" type="submit"
						value="<spring:message code="save"/>">Submit</button></td>
				<td><a class="btn btn-primary"
					href="/laboratoryseventeen/login" role="button">Cancel</a></td>
			</tr>
		</table>
	</form:form>
</div>