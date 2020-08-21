<%-- 
    Document   : index
    Created on : Aug 14, 2020, 4:51:44 PM
    Author     : DIENMAYXANH
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>User List Page</title>
    </head>
    <body>
        <h1>Student Management System</h1>
        <h3>Welcome ${user.firstName} ${user.lastName} (${user.email}) - ${user.role}. <a href="login?action=logout">Logout</a></h3>
        <h2>Users List</h2>
        <hr>
        <button class="btn-info" onclick="location.href = 'user?action=add'">Thêm tài khoản mới</button>
        <hr>
        <table style="width: 80%; border: blue solid 2px; table-layout: auto" class="table table-bordered">
            <tr style="text-align: center; background-color: aqua">
                <th>STT</th><th>Họ và Tên</th><th>Email</th><th>Quyền</th><th>Thao tác</th>
            </tr>
            <c:forEach var="u" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${u.lastName} ${u.firstName}</td>
                    <td>${u.email}</td>
                    <td>${u.role}</td>
                    <td>
                        <button class="btn btn-info" onclick="location.href = 'user?action=edit&email=${u.email}'">Edit</button>
                        <c:if test="${u.role != 'ADMIN'}">
                            <button type="button" class="btn btn-danger" onclick="location.href = 'user?action=delete&email=${u.email}'">Delete</button>   
                        </c:if>

                    </td>
                </tr> 
            </c:forEach>
        </table>
    </body>
</html>
