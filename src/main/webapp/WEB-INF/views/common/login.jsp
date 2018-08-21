<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>登录</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" />
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<security:authorize access="hasRole('ROLE_ADMIN')">
    Hello admin!
</security:authorize>
    <div style="padding: 100px 100px 10px;">
        <form id="loginForm" action="/user/login" method="post">
        	<%-- <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/> --%>
            用户名：<input type="text" id="username" name="username" class="form-control"/>
            密码：<input type="password" id="password" name="password" class="form-control" />
            <button id="loginBtn" onclick="doLogin()" class="btn btn-default">Login</button>
        </form>
    </div>
<script type="text/javascript">
    function doLogin() {
        $("#loginForm").submit();
        /* $.post("/user/login",{username: $("#username").val(), password:$("#password").val()},function(result){
            if(result == "success") {
                window.location.href = "/index";
            }
        }); */
    }
</script>
</body>
</html>