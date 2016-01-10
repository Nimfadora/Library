<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="reports" value="${reports}"/>
<c:set var="books" value="${books}"/>
<c:set var="users" value="${users}"/>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/stylesheet/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<div class="menu">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="<c:url value="/admin" />">Home</a></li>
                    <li><a href="<c:url value="/admin/book" />" id="theatreTable">Books</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="tableContainer" style="float: left;">
    <table class="table table-bordered">
        <caption>Reports</caption>
        <tr>
            <th>Id</th>
            <th>User id</th>
            <th>User name</th>
            <th>User login</th>
            <th>Book id</th>
            <th>Author</th>
            <th>Title</th>
            <th>Date rent</th>
            <th>Date return</th>
        </tr>
        <c:forEach var="report" items="${reports}">
            <tr>
                <td><c:out value="${report.id}"/></td>
                <td><c:out value="${report.user.id}"/></td>
                <td><c:out value="${report.user.name}"/></td>
                <td><c:out value="${report.user.login}"/></td>
                <td><c:out value="${report.book.id}"/></td>
                <td><c:out value="${report.book.author}"/></td>
                <td><c:out value="${report.book.title}"/></td>
                <td><c:out value="${report.rent}"/></td>
                <td><c:out value="${report.returnDate}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="tableContainer" style="float: right;">
    <table class="table table-bordered">
        <caption>Books</caption>
        <tr>
            <th>Id</th>
            <th>Author</th>
            <th>Title</th>
            <th>Count</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.count}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="tableContainer" style="float: left; padding-top: 0px;">
    <table class="table table-bordered">
        <caption>Users</caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Login</th>
            <th>Birthday</th>
            <th>Role</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.birthday}"/></td>
                <td><c:out value="${user.role}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
