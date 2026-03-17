<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Add New Account</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="card shadow" style="max-width: 600px; margin: 0 auto;">
                <div class="card-header bg-success text-white">
                    <h4 class="mb-0">Add New Account</h4>
                </div>
                <div class="card-body">
                    <%
                        String error = (String) request.getAttribute("ERROR");
                        if (error != null) {
                    %>
                    <div class="alert alert-danger"><%= error%></div>
                    <% }%>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="AddAccount">

                        <div class="mb-3">
                            <label class="form-label">Account ID (Tên đăng nhập) *</label>
                            <input type="text" name="account" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password *</label>
                            <input type="password" name="pass" class="form-control" required>
                        </div>
                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Họ (Last Name)</label>
                                <input type="text" name="lastName" class="form-control">
                            </div>
                            <div class="col">
                                <label class="form-label">Tên (First Name) *</label>
                                <input type="text" name="firstName" class="form-control" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="text" name="phone" class="form-control" pattern="[0-9]{10,11}" title="Chỉ nhập số, 10-11 ký tự">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Birth Day</label>
                            <input type="date" name="birthday" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label class="form-label d-block">Gender</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="true" checked>
                                <label class="form-check-label">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="false">
                                <label class="form-check-label">Female</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Role in System</label>
                            <select name="roleInSystem" class="form-select">
                                <option value="0">Staff (Nhân viên)</option>
                                <option value="1">Administrator (Quản trị viên)</option>
                            </select>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" name="isUse" class="form-check-input" value="true" checked>
                            <label class="form-check-label">Is active (Hoạt động)</label>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <a href="MainController?action=ManageAccounts" class="btn btn-secondary">Cancel</a>
                            <button type="submit" class="btn btn-success">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
