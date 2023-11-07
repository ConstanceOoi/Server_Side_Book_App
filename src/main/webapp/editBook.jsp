<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26/11/2022
  Time: 10:56 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
    <form action="EditBookServlet" method="post">
        <p> Book ISBN: <input type="text" name="isbn" readonly value="<c:out value="${books.isbn}"/>" required></p>
        <p> Book Title: <input type="text" name="title" value="<c:out value="${books.title}"/>" required></p>
        <p> Book Synopsis: <input name="synopsis" value="<c:out value="${books.synopsis}"/>" required></p>
        <p> Book Author: <input type="text" name="author"value="<c:out value="${books.author}"/>" required></p>
        <p> Book Category: <input type="text" name="category" value="<c:out value="${books.category}"/>" required></p>
        <p> Book Publication Year: <input type="year" name="publicationYear" value="<c:out value="${books.publicationYear}"/>" required></p>
        <input type="submit" value="Submit !">
    </form>
</body>
</html>
