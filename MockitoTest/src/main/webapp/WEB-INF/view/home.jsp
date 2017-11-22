<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<center>
	<h3>
		Welcome,
		<sec:authentication property="principal.firstName" />
		!
	</h3>
	<br /> <br /> Click <a href="<c:url value="/logout"/>">here</a> to
	logout.
</center>