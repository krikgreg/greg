<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
	<center>
	<h3>
	Welcome, ${CURRENT_USERMODEL.firstName} !
	</h3>
	<br />
	<br /> Click
	<a href="<c:url value="/logout"/>">here</a> to logout.
	</center>