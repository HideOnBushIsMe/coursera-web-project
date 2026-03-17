<%@page import="java.util.List"%>
<%@page import="sample.models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý Tài khoản</title>
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
                    <li class="active"><a href="MainController?action=ManageAccounts">Tài khoản</a></li>
                    <li><a href="MainController?action=ManageCategories">Danh mục</a></li>
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
                    <h3>Danh sách tài khoản</h3>
                </div>
                <div class="col-md-6 text-right" style="margin-top: 20px;">
                    <a href="accountDetail.jsp" class="btn btn-success btn-sm">
                        <span class="glyphicon glyphicon-plus"></span> Thêm tài khoản
                    </a>
                </div>
            </div>

            <%
                String errorMsg = (String) request.getAttribute("ERROR_MSG");
                if (errorMsg != null) {
            %>
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Lỗi!</strong> <%= errorMsg%>
            </div>
            <% } %>

            <div class="panel panel-default">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" style="margin-bottom: 0;">
                        <thead>
                            <tr class="active">
                                <th>Tên đăng nhập</th>
                                <th>Họ tên</th>
                                <th class="text-center">Giới tính</th>
                                <th class="text-center">Ngày sinh</th>
                                <th>Điện thoại</th>
                                <th class="text-center">Quyền</th>
                                <th class="text-center">Trạng thái</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Account> list = (List<Account>) request.getAttribute("LIST_ACCOUNT");
                                if (list != null && !list.isEmpty()) {
                                    for (Account acc : list) {
                            %>
                            <tr>
                                <td><%= acc.getAccount()%></td>
                                <td><%= acc.getLastName() + " " + acc.getFirstName()%></td>
                                <td class="text-center"><%= acc.isGender() ? "Nam" : "Nữ"%></td>
                                <td class="text-center"><%= acc.getBirthday()%></td>
                                <td><%= acc.getPhone()%></td>
                                <td class="text-center">
                                    <span class="label <%= acc.getRoleInSystem() == 1 ? "label-danger" : "label-info"%>">
                                        <%= acc.getRoleInSystem() == 1 ? "Quản trị" : "Nhân viên"%>
                                    </span>
                                </td>
                                <td class="text-center">
                                    <span class="label <%= acc.isIsUse() ? "label-success" : "label-default"%>">
                                        <%= acc.isIsUse() ? "Kích hoạt" : "Đã khóa"%>
                                    </span>
                                </td>
                                <td class="text-center">
                                    <form action="MainController" method="POST" class="form-inline">
                                        <input type="hidden" name="accountId" value="<%= acc.getAccount()%>">
                                        <button type="submit" name="action" value="UpdateAccountPage" class="btn btn-warning btn-xs">Sửa</button>
                                        <button type="submit" name="action" value="DeleteAccount" 
                                                class="btn btn-danger btn-xs"
                                                onclick="return confirm('Xóa tài khoản <%= acc.getAccount()%>?');">
                                            Xóa
                                        </button>
                                    </form>
                                </td>
                            </tr>
                            <%      }
                            } else {
                            %>
                            <tr><td colspan="8" class="text-center">Không tìm thấy tài khoản nào.</td></tr>
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
