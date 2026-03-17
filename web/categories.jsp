<%@page import="java.util.List"%>
<%@page import="sample.models.Category"%>
<%@page import="sample.models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Danh mục</title>
        <!-- Bootstrap 3 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    </head>
    <body class="bg-light">
        <%
            Account loginUser = (Account) session.getAttribute("LOGIN_USER");
            if (loginUser == null || loginUser.getRoleInSystem() != 1) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        
        <nav class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="dashboard.jsp">QUẢN TRỊ VIÊN</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="MainController?action=ManageAccounts">Tài khoản</a></li>
                    <li class="active"><a href="MainController?action=ManageCategories">Danh mục</a></li>
                    <li><a href="MainController?action=ManageProducts">Sản phẩm</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a>Chào, <b><%= loginUser.getFirstName()%></b></a></li>
                    <li><a href="dashboard.jsp">Bảng điều khiển</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="row" style="margin-bottom: 20px;">
                <div class="col-md-6">
                    <h3>Danh mục sản phẩm</h3>
                </div>
                <div class="col-md-6 text-right" style="margin-top: 20px;">
                    <a href="MainController?action=AddCategoryPage" class="btn btn-success btn-sm">
                        <span class="glyphicon glyphicon-plus"></span> Thêm danh mục mới
                    </a>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" style="margin-bottom: 0;">
                        <thead>
                            <tr class="active">
                                <th class="text-center" style="width: 100px;">Mã loại</th>
                                <th>Tên danh mục</th>
                                <th>Mô tả / Ghi chú</th>
                                <th class="text-center" style="width: 150px;">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Category> list = (List<Category>) request.getAttribute("LIST_CATEGORY");
                                if (list != null && !list.isEmpty()) {
                                    for (Category c : list) {
                            %>
                            <tr>
                                <td class="text-center"><b><%= c.getTypeId()%></b></td>
                                <td style="color: #337ab7; font-weight: bold;"><%= c.getCategoryName()%></td>
                                <td class="text-muted small"><%= (c.getMemo() != null ? c.getMemo() : "---")%></td>
                                <td class="text-center">
                                    <form action="MainController" method="POST" class="form-inline">
                                        <input type="hidden" name="typeId" value="<%= c.getTypeId()%>">
                                        <button type="submit" name="action" value="UpdateCategoryPage" class="btn btn-primary btn-xs">Sửa</button>
                                        <button type="submit" name="action" value="DeleteCategory" class="btn btn-danger btn-xs" onclick="return confirm('Xóa danh mục này?')">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                            <%      }
                            } else {
                            %>
                            <tr><td colspan="4" class="text-center">Chưa có danh mục nào.</td></tr>
                            <%  }%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
