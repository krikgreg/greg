<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:if test="${errors != null}">
	<spring:hasBindErrors name="user">
		<c:forEach var="error" items="${errors.allErrors}">
			<div class="alert alert-danger" role="alert">
				<spring:message message="${error}" />
			</div>
		</c:forEach>
	</spring:hasBindErrors>
</c:if>