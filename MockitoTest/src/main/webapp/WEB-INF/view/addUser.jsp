<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div>
	<form:form modelAttribute="user" action="/laboratoryseventeen/admin/addUser" method="post">
		<h3>Add New User</h3>
		<div>
			<%@ include file="/WEB-INF/view/loggedUser.jsp"%>
		</div>
		<table class="table">
			<tr>
				<td>Login</td>
				<td><form:input path="login" required="required"/><span class="required">*</span></td>
				<td><form:errors path="login" class="text-danger"/></td>
			</tr>
			<%@ include file="/WEB-INF/view/addUpdateUserForm.jsp"%>
		</table>
		<%@ include file="/WEB-INF/view/buttonsTable.jsp"%>
	</form:form>
</div>