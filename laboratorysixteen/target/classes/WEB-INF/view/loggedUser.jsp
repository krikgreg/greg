<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div style="text-align: right;">
Admin <c:out value = "${CURRENT_USERMODEL.firstName}"/> !
(
<a href="<c:url value="/logout"/>">Logout</a>
)
</div>