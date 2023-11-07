<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25/11/2022
  Time: 10:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Here!</title>
    <meta charset="ISO-8859-1">
</head>
<body>
    <form action="RegisterServlet" method="post">
      <p><b>Name: </b></p>
      <input type="text" name="name" placeholder="Enter name" required>

      <p><b>Email</b></p>
      <input type="email" name="email" placeholder="Enter email" required>

      <p><b>Address</b></p>
      <input type="text" name="address" placeholder="Enter address" required>

      <p><b>Password</b></p>
      <input type="password" placeholder="Enter Password" name="password" required>

      <button type="submit">Register Now!</button>
    </form>
</body>
</html>
