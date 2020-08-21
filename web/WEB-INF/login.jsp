<%-- 
    Document   : login
    Created on : Jul 27, 2020, 4:00:51 PM
    Author     : DIENMAYXANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <title>QLSV - Login page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            /* Remove the navbar's default margin-bottom and rounded borders */ 
            .navbar {
                margin-bottom: 0;
                border-radius: 0;
            }

            /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
            .row.content {height: 450px}

            /* Set gray background color and 100% height */
            .sidenav {
                padding-top: 20px;
                background-color: #f1f1f1;
                height: 100%;
            }

            /* Set black background color, white text and some padding */
            footer {
                background-color: #555;
                color: white;
                padding: 15px;
            }

            /* On small screens, set height to 'auto' for sidenav and grid */
            @media screen and (max-width: 767px) {
                .sidenav {
                    height: auto;
                    padding: 15px;
                }
                .row.content {height:auto;} 
            }
        </style>
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>                        
                        </button>
                    </div>
                    <div class="collapse navbar-collapse" id="myNavbar">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li><a href="#">About</a></li>
                            <li><a href="#">Projects</a></li>
                            <li><a href="#">Contact</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <div class="container-fluid text-center">    
                <div class="row content">
                    <div class="col-sm-2 sidenav">
                        <p><a href="#">Link</a></p>
                        <p><a href="#">Link</a></p>
                        <p><a href="#">Link</a></p>
                    </div>
                    <div class="col-sm-8 text-left"> 
                        <h1>Welcome</h1>
                        <p>Chào mừng bạn đến với ứng dụng quản lý học sinh sinh viên của chúng tôi. Mời bạn đăng nhập để tiếp tục</p>
                        <hr>
                        <h1>Login</h1> 
                        <form class="form-horizontal" action="login" method="post">
                            <input type="hidden" name="action" value="checkLogin" />
                            <p style="color:red;">${message}</p>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="email">Email:</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${email}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="pwd">Password:</label>
                                <div class="col-sm-10">          
                                    <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
                                </div>
                            </div>
                            <div class="form-group">        
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label><input type="checkbox" name="remember"> Remember me</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">        
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input type="submit" value="Login" class="btn btn-default"/>
                                </div>
                            </div>
                        </form> 
                    </div>
                    <div class="col-sm-2 sidenav">
                        <div class="well">
                            <p>ADS</p>
                        </div>
                        <div class="well">
                            <p>ADS</p>
                        </div>
                    </div>
                </div>
            </div>
            <footer class="container-fluid text-center">
                <p>Footer Text</p>
            </footer>
        </div>
    </body>
</html>
