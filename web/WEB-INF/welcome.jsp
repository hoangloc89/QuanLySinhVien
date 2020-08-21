<%-- 
    Document   : welcome
    Created on : Jul 27, 2020, 4:18:07 PM
    Author     : DIENMAYXANH
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
    </head>
    <body>
        <h3>Welcome ${user.firstName} ${user.lastName} (${user.email}) - ${user.role}. <a href="login?action=logout">Logout</a></h3>
        <hr>
        <c:if test="${user.role == 'ADMIN'}">
            <a href="user">User Management</a><br>
        </c:if>
        <a href="student">Student Management</a>
    </body>
</html>
