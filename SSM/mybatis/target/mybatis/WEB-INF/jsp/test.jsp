<%--
  Created by IntelliJ IDEA.
  User: walmt
  Date: 2018/1/17
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="getUserById" method="get">
        <input type="text" name="id">
        <input type="submit" value="submit">
    </form>

    <form action="insertUser" method="post">
        username:<input type="text" name="username">
        sex:<input type="text" name="sex">
        birthday:<input type="text" name="birthday">
        address:<input type="text" name="address">
        <input type="submit" value="submit">
    </form>
</body>
</html>
