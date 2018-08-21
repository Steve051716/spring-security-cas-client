<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">菜鸟教程</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
            	<security:authorize access="hasRole('ROLE_ADMIN')">
                	<li class="active"><a href="/menu/ios">iOS</a></li>
                	<li><a href="/menu/svn">SVN</a></li>
                </security:authorize>
                <li><a href="/menu/logout">登出</a></li>
            </ul>
        </div>
    </div>
</nav>