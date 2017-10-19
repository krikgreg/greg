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
</head>
<div>
	<form:form modelAttribute="user"
		action="/laboratorysixteen/admin/updateUser" method="post">
		<h3>Update User</h3>
		<%@ include file="/WEB-INF/view/validationErrors.jsp"%>
		<div>
			<%@ include file="/WEB-INF/view/loggedUser.jsp"%>
		</div>
		<form:hidden path="id" />
		<table class="table">
			<tr>
				<td>Login</td>
				<td><form:input path="login" readonly="true"/><span class="required">*</span></td>
			</tr>
			<%@ include file="/WEB-INF/view/addUpdateUserForm.jsp"%>
		</table>
		<%@ include file="/WEB-INF/view/buttonsTable.jsp"%>
	</form:form>
</div>