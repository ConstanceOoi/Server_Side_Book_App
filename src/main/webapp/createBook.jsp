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
    <title>Title</title>
    <meta charset="ISO-8859-1">
</head>
<body>
    <form action="CreateBookServlet" method="post">
        <p> Book ISBN: <input type="text" name="isbn"></p>
        <p> Book Title: <input type="text" name="title"></p>
        <p> Book Synopsis: <input type="text" name="synopsis"></p>
        <p> Book Author: <input type="text" name="author"></p>
        <p> Book Category: <input type="text" name="category"></p>
        <p> Book Publication Year: <input type="year" name="publicationYear"></p>
        <input type="submit" value="Submit !">
    </form>
</body>
</html>
