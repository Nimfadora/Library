<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="books" value="${books}"/>
<!DOCTYPE HTML>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/stylesheet/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<script>
    function deleteBook() {
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/admin/book/delete",
            data: JSON.stringify({"id": $("input[name='delete']:checked").attr('id')}),
            dataType: 'json',
            timeout: 600000,
            success: function () {
                window.location.reload();
            },
            error: function (error) {
                $(".error").append("<p>"+error.responseJSON.message+"</p>");
            }
        });
    }
</script>
<div class="menu">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value="/admin" />">Home</a></li>
                    <li class="active"><a href="<c:url value="/admin/book" />" id="theatreTable">Books</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<div class="tableContainer">
    <table class="table table-bordered">
        <caption>Books</caption>
        <tr>
            <th>Author</th>
            <th>Title</th>
            <th>Count</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.count}"/></td>
                <td><input id="${book.id}" type="radio" name="delete"/></td>
            </tr>
        </c:forEach>
    </table>
    <button class="btn btn-default" onclick="deleteBook()">Delete</button>
    <div class="error"></div>
</div>

<div class="formContainer">
    <form class="form" method="POST" action="/admin/book/create">
        <input type="hidden" name="submitted" value="true" />
        <div class="form-group">
            <p>
                <label for="author">Author:</label>
                <input type="text" name="author" id="author" required/>
                <br/>
            </p>
            <p>
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" required/>
                <br/>
            </p>

            <p>
                <label for="count">Count:</label>
                <input type="number" name="count" id="count" size="3" min="1" required/>
                <br/>
            </p>

            <input class="btn btn-default" type="submit" value="Create"/>
        </div>
    </form>
</div>
</body>
</html>
