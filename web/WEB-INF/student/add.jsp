<%-- 
    Document   : add
    Created on : Aug 14, 2020, 4:51:52 PM
    Author     : DIENMAYXANH
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <title>Add-Student Page</title>
    </head>
    <body>
        <div class="container">
            <h3><a href="login?action=logout">Logout</a></h3>
            <h1>Hello <c:out value="${user.lastName}"/>!</h1>
            <hr>
            <c:if test="${action eq 'add'}">
                <h1 style="color: blue">Thêm thông tin Sinh viên mới</h1>  
            </c:if>
            <c:if test="${action eq 'edit'}">
                <h1 style="color: blue">Sửa thông tin Sinh viên : ${student.lastName} &nbsp; ${student.firstName}</h1>  
            </c:if>
            <c:if test="${action eq 'updatemarks'}">
                <h1 style="color: blue">Cập nhật điểm Sinh viên : ${student.lastName} &nbsp; ${student.firstName}</h1>  
            </c:if>

            <form class="form-horizontal" action="student" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="code">Mã Sinh Viên :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="code" placeholder="Nhập Mã SV" name="code"
                               value="${student.code}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="firstName">Tên :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="firstName" placeholder="Nhập Tên" name="firstName"
                               value="${student.firstName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="lastName">Họ Đệm :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="lastName" placeholder="Nhập Họ đệm" name="lastName"
                               value="${student.lastName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="birthDate">Ngày Sinh :</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="birthDate" name="birthDate" value="${student.birthDate}">

                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="gender">Giới tính : ${student.gender}</label>
                    <label class="radio-inline">
                        <input type="radio" name="gender" id="gender" >Nam
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender" id="gender" >Nữ
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="gender" id="gender" >Khác
                    </label>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="address">Địa Chỉ :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ"
                               value="${student.address}">   
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="class">Lớp Học : ${student.studentClass.className}</label>
                    <select class="col-sm-10" id="class" name="classId">
                        <c:forEach items="${studentClasses}" var="studentClass">
                            <option value="${studentClass.id}" 
                                    style="color: #6633ff">${studentClass.className}</option>
                        </c:forEach>   
                    </select>
                </div>
                <c:if test="${action ne 'add'}">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="theory">Điểm Lý Thuyết :</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="theory" name="theory" placeholder="Nhập điểm lý thuyết"
                                   value="${student.theory}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="practice">Điểm Thực Hành :</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="practice" name="practice" placeholder="Nhập điểm thực hành"
                                   value="${student.practice}">
                        </div>
                    </div>   
                </c:if>
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <c:if test="${action eq 'add'}">
                            <button type="submit" class="btn btn-primary" id="addsv">Thêm Sinh Viên</button>
                            <input type="hidden" name="action" value="addsv" id="addsv">
                        </c:if>
                        <c:if test="${action eq 'edit'}">
                            <button type="submit" class="btn btn-success" id="editsv">Cập Nhật</button>
                            <input type="hidden" name="action" value="editsv" id="editsv">
                        </c:if>
                        <c:if test="${action eq 'updatemarks'}">
                            <button type="submit" class="btn btn-success" id="marked">Cập Nhật Điểm</button>
                            <input type="hidden" name="action" value="marked" id="marked">
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
