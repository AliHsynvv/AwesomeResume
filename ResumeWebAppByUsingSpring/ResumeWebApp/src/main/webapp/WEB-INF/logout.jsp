<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Logout</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body class="login_background">

<form action="logout" method="POST">
    <div class="col-4 container login_fix_">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

        <button type="submit" class="btn btn-primary" name="login">Logout</button>
    </div>
</form>

</body>
</html>