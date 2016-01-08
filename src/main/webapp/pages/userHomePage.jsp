<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="books" value="${books}"/>
<c:set var="books" value="${userBooks}"/>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" href="<c:url value="/stylesheet/style.css"/>"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<div class="tableContainer">
  <table class="table table-bordered">
    <caption>Books available</caption>
    <tr>
      <th>Author</th>
      <th>Title</th>
      <th>Count</th>
      <th>Choose</th>
    </tr>
    <c:forEach var="book" items="${books}">
      <tr>
        <td><c:out value="${book.author}"/></td>
        <td><c:out value="${book.title}"/></td>
        <td><c:out value="${book.count}"/></td>
        <td><input id="${book.id}" type="checkbox"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
<button class="btn-default">Take</button>
<div class="tableContainer">
  <table class="table table-bordered">
    <caption>Books taken</caption>
    <tr>
      <th>Author</th>
      <th>Title</th>
      <th>Count</th>
      <th>Choose</th>
    </tr>
    <c:forEach var="userBook" items="${userBooks}">
      <tr>
        <td><c:out value="${userBook.author}"/></td>
        <td><c:out value="${userBook.title}"/></td>
        <td><input id="${userBook.id}" type="checkbox"/></td>
      </tr>
    </c:forEach>
  </table>
</div>
<button class="btn-default">Return</button>
</body>
</html>
