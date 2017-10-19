<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tr>
	<td>Password</td>
	<td><form:input path="password" type="password" required="required"/><span class="required">*</span></td>
</tr>
<tr>
	<td>PasswordAgain</td>
	<td><form:input path="passwordAgain" type="password" required="required"/><span class="required">*</span></td>
</tr>
<tr>
	<td>E-mail</td>
	<td><form:input path="email" type="text" required="required"/><span class="required">*</span></td>
</tr>
<tr>
	<td>First name</td>
	<td><form:input path="firstName" type="text" required="required"/><span class="required">*</span></td>
</tr>
<tr>
	<td>Last name</td>
	<td><form:input path="lastName" type="text" required="required"/><span class="required">*</span></td>
</tr>
<tr>
	<td>Birth date</td>
	<td><form:input path="birthday" type="date" placeholder="yyyy-mm-dd" required="required"/><span class="required">*</span></td>
</tr>
<tr>
					<td>Role</td>
				<td><select name="role" style="width: 173px; height: 30px"
					required="required">
						<option></option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.id}" ${role.id == user.role.id ? 'selected' : ''}>${role.name}</option>
						</c:forEach>
				</select><span class="required">*</span></td>
				</tr>