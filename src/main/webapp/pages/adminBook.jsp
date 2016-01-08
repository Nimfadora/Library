<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="books" value="${books}"/>
<!DOCTYPE HTML>
<html>
<head>
    <title>Book</title>
    <link rel="stylesheet" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/stylesheet/style.css"/>"/>
    <%--<c:import url="adress" var=""></c:import>--%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<div class="tableContainer">
    <table class="table table-bordered">
        <tr>
            <th>Author</th>
            <th>Title</th>
            <th>Count</th>
        </tr>
        <c:forEach var="book" items="${books}">
            <tr>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.count}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<c:if test="${param.submitted}">
    <c:if test="${empty param.author}" var="noAuthor"/>
    <c:if test="${empty param.title}" var="noTitle"/>
    <c:if test="${empty param.count}" var="noCount"/>


    <c:if test="${not (noAuthor or noTitle or noCount)}">
        <c:set value="${param.author}" var="author" scope="request"/>
        <c:set value="${param.title}" var="title" scope="request"/>
        <c:set value="${param.count}" var="count" scope="request"/>
        <jsp:forward page="adminBook.jsp"/>
    </c:if>
</c:if>

<div class="formContainer">
    <form class="form" method="post" action="book">
        <input type="hidden" name="submitted" value="true" />
        <div class="form-group">
            <p>
                <label for="author">Author:</label>
                <input type="text" name="author" id="author" value="<c:out value="${param.author}"/>"/>
                <br/>
                <c:if test="${noAuthor}">
                    <small><span style="color: red;">
                        Note: you must enter an author
                    </span></small>
                </c:if>
            </p>
            <p>
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" value="<c:out value="${param.title}"/>"/>
                <br/>
                <c:if test="${noTitle}">
                    <small><span style="color: red;">
                        Note: you must enter a title
                    </span></small>
                </c:if>
            </p>

            <p>
                <label for="count">Count:</label>
                <input type="text" name="count" id="count" size="3" value="<c:out value="${param.count}"/>"/>
                <br/>
                <c:if test="${noCount}">
                    <small><span style="color: red;">
                    Note: you must enter count
                    </span></small>
                </c:if>
            </p>

            <input class="btn-default" type="submit" value="Create"/>
        </div>

    </form>
</div>

<%--<div class="form-group"><label for="troupe">Труппа:</label><select class="form-control" id="troupe">--%>
<%--<option>Выберите труппу</option>--%>
<%--</select></div>--%>
<%--<div class="form-group"><label for="spectacle">Спектакль:</label><select class="form-control" id="spectacle">--%>
<%--<option>Выберите спектакль</option>--%>
<%--</select></div>--%>
<%--<button type="submit" id="update" class="btn btn-info">Update</button>--%>
<%--<button type="button" id="delete" class="btn btn-danger">Delete</button>--%>
<%--<button type="submit" id="create" class="btn btn-success">Create</button>--%>
<%--<button type="button" id="clear" class="btn btn-default">Clear form</button>--%>
</body>
</html>
