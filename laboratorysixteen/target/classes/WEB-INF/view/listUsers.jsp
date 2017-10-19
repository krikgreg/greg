<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="t" uri="/WEB-INF/table.tld"%>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/bootstrap.css"
	type="text/css" media="screen, projection">
</head>
<div>
	<%@ include file="/WEB-INF/view/loggedUser.jsp" %>
</div>
<div class="topLeft">
	<a href="<c:url value="/admin/addUserForm"/>">Add new user</a>
</div>
<div class="center">
	<t:table users="${allUsers}"/>
</div>