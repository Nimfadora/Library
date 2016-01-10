<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="books" value="${books}"/>
<c:set var="userBooks" value="${userBooks}"/>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/libs/bootstrap/css/bootstrap.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/stylesheet/style.css"/>"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/libs/bootstrap/js/bootstrap.js"/>"></script>
</head>
<body>
<script>
  function take() {
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
          if (http.readyState == 4 && http.status == 200) {
              window.location.reload();
          }
      };
    http.open("POST", "/user/takeBook", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("id="+$("input[name='take']:checked").attr('id'));
  }
  function returnBook() {
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
      if (http.readyState == 4 && http.status == 200) {
          window.location.reload();
      }
    };
    http.open("POST", "/user/returnBook", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("id="+$("input[name='return']:checked").attr('id'));
  }
</script>
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
        <td><input id="${book.id}" type="radio" name="take"/></td>
      </tr>
    </c:forEach>
  </table>
  <button class="btn btn-default" onclick="take()">Take</button>
</div>

<div class="tableContainer">
  <table class="table table-bordered">
    <caption>Books taken</caption>
    <tr>
      <th>Author</th>
      <th>Title</th>
      <th>Choose</th>
    </tr>
    <c:forEach var="userBook" items="${userBooks}">
      <tr>
        <td><c:out value="${userBook.author}"/></td>
        <td><c:out value="${userBook.title}"/></td>
        <td><input id="${userBook.id}" type="radio" name="return"/></td>
      </tr>
    </c:forEach>
  </table>
  <button class="btn btn-default" onclick="returnBook()">Return</button>
</div>
</body>
</html>
