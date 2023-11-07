<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login Here</title>
</head>
<body>
<form action="HelloServlet" method="post">
    <h1>LOGIN HERE</h1>
    <div class="container">
        <p><b>Email</b></p>
        <input type="email" name="email" placeholder="Enter email" required >


        <p><b>Password</b></p>
        <input type="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>
    </div>
</form>
<div>Don't have an account? <a href="register.jsp" method="post">Register here </a></div>

</body>
</html>