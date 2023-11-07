<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25/11/2022
  Time: 10:19 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#books {
    font-family: Arial, Helvetica, sans-serif;
    border-collapse: collapse ;
    width: 100%;
    table-layout: fixed;
    padding: 8px;
}

#books tr:hover{
    background-color: lightgray;
}

#books th{
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: center;
    background-color: darkgrey;
    color: black;
}

#books td{
    padding: 8px;
    text-align: center;
}

input[type=submit]{
    background-color: darkgray;
    border: none;
    color: black;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
}

form{
    display: inline-block;
}

.createBook{
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    background-color: darkgray;
    display: inline-block;
}

</style>

<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Welcome <c:out value = "${name}"/></title>
</head>
<body>
<div>
    <h1>Hi ${sessionScope.name}. This is your book list. </h1>
</div>
<div>
    <button class="createBook"><a href="createBook.jsp">Add New Book</a></button>
</div>
<div>
    <table id="books">
        <thead>
        <tr>
            <th style="width: 3%">No.</th>
            <th style="width: 10%">ISBN</th>
            <th style="width: 15%">Title</th>
            <th style="width: 25%">Synopsis</th>
            <th style="width: 15%">Author</th>
            <th style="width: 7%">Category</th>
            <th style="width: 10%">Publication Year</th>
            <th style="width: 16%">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${books}" var="books">
            <c:set var="count" value="${count+1}" scope="page"/>
            <tr>
                <td><c:out value="${count}"/></td>
                <td><c:out value="${books.isbn}"/><input type="hidden" name="isbn" value="${books.isbn}"></td>
                <td><c:out value="${books.title}"/><input type="hidden" name="title" value="${books.title}"></td>
                <td style="text-align: justify"><c:out value="${books.synopsis}"/><input type="hidden" name="synopsis" value="${books.synopsis}"/></td>
                <td><c:out value="${books.author}"/><input type="hidden" name="author" value="${books.author}"></td>
                <td><c:out value="${books.category}"/><input type="hidden" name="category" value="${books.category}"></td>
                <td><c:out value="${books.publicationYear}"/><input type="hidden" name="publicationYear" value="${books.publicationYear}"></td>
                <td>
                    <form action="EditFormServlet" method="post"><input type="hidden" name="isbn" value="${books.isbn}"><input type="submit" value="Edit"></form>
                    <form action="DeleteBookServlet" method="post"><input type="hidden" name="isbn" value="${books.isbn}"> <input type="submit" value="Delete"></form>

                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>




</body>
</html>
