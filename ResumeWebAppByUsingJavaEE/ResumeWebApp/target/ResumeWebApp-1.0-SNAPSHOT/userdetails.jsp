<%@ page import="entity.dao.inter.UserDaoInter" %>
<%@ page import="entity.User" %>
<%@ page import="entity.dao.impl.UserDaoImpl" %>
<%@ page import="main.Context" %><%--
  Created by IntelliJ IDEA.
  User: gsynv
  Date: 4/17/2022
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserDetails</title>
</head>
<body>
<%
    User u = (User) request.getAttribute("user");
%>

<div>
    <form action="userdetails" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=u.getId()%>">
        <label  >name:</label>
        <input type="text" name="name" value="<%=u.getName()%>">
        <br/>
        <label  >surname:</label>
        <input type="text" name="surname" value="<%=u.getSurname()%>">
        <input type="submit" name="save" value="save">
    </form>
</div>

</body>
</html>
