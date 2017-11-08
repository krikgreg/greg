<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<%@ taglib prefix="t" uri="/WEB-INF/table.tld"%>
<div>
	<%@ include file="/WEB-INF/view/loggedUser.jsp" %>
</div>
<div class="topLeft">
	<a href="<c:url value="/admin/addUserForm"/>">Add new user</a>
</div>
<div class="center">
	<t:table users="${allUsers}"/>
</div>