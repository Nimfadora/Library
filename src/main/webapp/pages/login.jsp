<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/stylesheet/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>

<div class="center-block">
    <div class="formContainer">
        <form class="form" method="post">
            <div class="form-group">
                <p>
                    <label for="login">Login:</label>
                    <input type="text" name="login" id="login" value="<c:out value="${param.login}"/>" required/>
                    <br/>
                </p>
                <p>
                    <label for="password">Password:</label>
                    <input type="text" name="password" id="password" value="<c:out value="${param.password}"/>"
                           required/>
                </p>
                <input class="btn-default" type="submit" value="Log in"/>
            </div>
        </form>
    </div>
</div>

</body>
</html>
