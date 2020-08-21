<%-- 
    Document   : add
    Created on : Aug 14, 2020, 4:51:52 PM
    Author     : DIENMAYXANH
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Add User Page</title>
    </head>
    <body>
        <div class="container">
            <h3><a href="login?action=logout">Logout</a></h3>
            <h1>Hello <c:out value="${user.lastName}"/>!</h1>
            <hr>
            <c:if test="${action eq 'add'}">
                <h1 style="color: blue">Thêm một tài khoản mới</h1>  
            </c:if>
            <c:if test="${action eq 'edit'}">
                <h1 style="color: blue">Sửa thông tin Người dùng : ${u.lastName} &nbsp; ${u.firstName}</h1>  
            </c:if>
            <c:if test="${action eq 'delete'}">
                <h1 style="color: blue">Xóa người dùng này : ${u.lastName} &nbsp; ${u.firstName}</h1>  
            </c:if>

            <form class="form-horizontal" action="user" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="email">Email :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="email" placeholder="Nhập Email" name="email"
                               required value="${u.email}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="firstName">Tên :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="firstName" placeholder="Nhập Tên" name="firstName"
                              required value="${u.firstName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="lastName">Họ Đệm :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="lastName" placeholder="Nhập Họ đệm" name="lastName"
                              required value="${u.lastName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">Mật Khẩu :</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" name="password" value="${u.password}"
                        required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="role" >Nhiệm Vụ : ${u.role}</label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="role" value="admin">ADMIN
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="role" value="giaovu">GIÁO VỤ
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="role" id="role" value="giaovien">GIÁO VIÊN
                    </label>
                </div>
                
                <div class="form-group">
                    <div class="control-label col-sm-2">
                        <button type="button" class="btn btn-primary" onclick="location.href='user?action=list'">BACK</button> 
                    </div>
                    <div class="control-label col-sm-2">
                        <c:if test="${action eq 'add'}">
                            <button type="submit" class="btn btn-primary" id="save">SAVE</button>
                            <input type="hidden" name="action" value="save" id="save">
                        </c:if>
                        <c:if test="${action eq 'edit'}">
                            <button type="submit" class="btn btn-success" id="update">UPDATE</button>
                            <input type="hidden" name="action" value="update" id="update">
                        </c:if>
                        <c:if test="${action eq 'delete'}">
                            <button type="submit" class="btn btn-danger" id="remove">REMOVE</button>
                            <input type="hidden" name="action" value="remove" id="remove">
                        </c:if>
                    </div>
                    
                </div>
            </form>
        </div>
    </body>
</html>
