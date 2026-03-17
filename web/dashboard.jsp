<%@page import="sample.models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <!-- Bootstrap 3 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <%
            // Kiểm tra đăng nhập
            Account loginUser = (Account) session.getAttribute("LOGIN_USER");
            if (loginUser == null || loginUser.getRoleInSystem() != 1) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>

        <!-- Navbar Admin (Màu đen) -->
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="dashboard.jsp">QUẢN TRỊ VIÊN</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="MainController?action=ManageAccounts">Tài khoản</a></li>
                        <li><a href="MainController?action=ManageCategories">Danh mục</a></li>
                        <li><a href="MainController?action=ManageProducts">Sản phẩm</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="HomeController">Cửa hàng</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                Chào, <b><%= loginUser.getFirstName()%></b> <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li>
                                    <form action="MainController" method="POST" style="padding: 3px 20px;">
                                        <button type="submit" name="action" value="Logout" class="btn btn-link btn-xs" style="text-decoration: none; color: #333; padding: 0;">
                                            Đăng xuất
                                        </button>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container text-center" style="margin-top: 50px;">
            <div class="jumbotron" style="background: white; border: 1px solid #ddd;">
                <h2>Hệ Thống Quản Trị Nội Bộ</h2>
                <p class="text-muted">Chào mừng quản trị viên quay lại hệ thống.</p>
                <hr>
                <div class="row">
                    <div class="col-md-4">
                        <a href="MainController?action=ManageAccounts" class="btn btn-primary btn-lg btn-block">Quản lý Tài khoản</a>
                    </div>
                    <div class="col-md-4">
                        <a href="MainController?action=ManageCategories" class="btn btn-info btn-lg btn-block">Quản lý Danh mục</a>
                    </div>
                    <div class="col-md-4">
                        <a href="MainController?action=ManageProducts" class="btn btn-success btn-lg btn-block">Quản lý Sản phẩm</a>
                    </div>
                </div>
            </div>
        </div>

        <footer class="container text-center text-muted" style="margin-top: 100px; padding-top: 20px; border-top: 1px solid #eee;">
            <p>&copy; 2026 Admin System</p>
        </footer>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
