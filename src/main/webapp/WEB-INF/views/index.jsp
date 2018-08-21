<%--
  Created by IntelliJ IDEA.
  User: 15801
  Date: 2018/8/14
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>SSO-Spring Security</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="common/header.jsp"></jsp:include>
<h1>Welcome to Cas Secured Spring Boot App</h1>
<h2>This is a Secured Page</h2>
<h3>Welcome home <span style="color:cadetblue;">${username}</span></h3>
<br>
${authStr}
<a href="/logout/cas">Logout</a>
<security:authorize access="hasRole('ROLE_ADMIN')">
    auth: ROLE_ADMIN
</security:authorize>
<security:authorize access="hasRole('ROLE_USER')">
    auth: ROLE_USER
</security:authorize>
</body>
</html>
