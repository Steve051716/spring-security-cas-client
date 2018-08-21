<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>hello</title>
	</head>
	<body>
	         <h2>title:${title }</h2>
	         <h2>message:${message }</h2>
	         <c:if test="${pageContext.request.userPrincipal.name != null  }">
	               <h2>welcome you ,${pageContext.request.userPrincipal.name }! |
                   <a href="<c:url value='/j_spring_security_logout'/>">Logout</a></h2>
	         </c:if>
	</body>
</html>