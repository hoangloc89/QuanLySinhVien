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
        <title>Student List</title>
    </head>
    <body>
        <h1>Student Management System</h1>
        <h3>Welcome ${user.firstName} ${user.lastName} (${user.email}) - ${user.role}. <a href="login?action=logout">Logout</a></h3>
        <h2>Student List</h2>
        <hr>
        <button class="btn-info" onclick="location.href = 'student?action=add'">Add New Student</button>
        <hr>
        <table style="width: 80%; border: blue solid 2px; table-layout: auto" class="table table-bordered">
            <tr style="text-align: center; background-color: aqua">
                <th>STT</th><th>Họ và Tên</th><th>Ngày Sinh</th><th>Lớp Học</th><th>Quê Quán</th><th>Thao tác</th>
            </tr>
            <c:forEach var="student" items="${students}" varStatus="loop">
                <tr>
                    <td>${loop.index+1}</td>
                    <td>${student.lastName} ${student.firstName}</td>
                    <td>${student.birthDate }</td>
                    <td>${student.studentClass.className }</td>
                    <td>${student.address }</td>
                    <td>
                        <c:if test="${user.role == 'ADMIN' || user.role == 'GIAOVU'}">
                            <button class="btn btn-info" onclick="location.href = 'student?action=edit&code=${student.code}'">Edit</button>
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">Delete</button>
                        </c:if>
                        <c:if test="${user.role == 'ADMIN' || user.role == 'GIAOVIEN'}">
                            <button class="btn btn-default" onclick="location.href = 'student?action=updatemarks&code=${student.code}'">Update Marks</button>

                            <!--    Trigger the modal with a button DELETE
       
                Modal -->
                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog">

                                    <!--                 Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title" style="color: red">Bạn muốn xóa dữ liệu của sinh viên này? ${student.code}</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Hành động xóa này sẽ không thể khôi phục lại được, nếu bạn chắc chắn muốn xóa thì hãy nhấn nút DELETE</p>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-danger" 
                                                    onclick="location.href = 'student?action=delete&code=${student.code}'">Delete</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </c:if>
                </tr>    
            </c:forEach>
        </table>


    </body>
</html>
