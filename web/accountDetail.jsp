<%@page import="sample.models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Account Details</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <%
            Account acc = (Account) request.getAttribute("ACCOUNT_INFO");
            boolean isUpdate = (acc != null);
            String title = isUpdate ? "Update Account" : "Add New Account";
            String action = isUpdate ? "UpdateAccount" : "AddAccount";
        %>
        <div class="container mt-5">
            <div class="card shadow" style="max-width: 600px; margin: 0 auto;">
                <div class="card-header <%= isUpdate ? "bg-warning text-dark" : "bg-success text-white" %>">
                    <h4 class="mb-0"><%= title %></h4>
                </div>
                <div class="card-body">
                    <%
                        String error = (String) request.getAttribute("ERROR");
                        if (error != null) {
                    %>
                    <div class="alert alert-danger"><%= error%></div>
                    <% }%>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="<%= action %>">

                        <div class="mb-3">
                            <label class="form-label">Account ID (Tên đăng nhập) *</label>
                            <input type="text" name="account" class="form-control" 
                                   value="<%= isUpdate ? acc.getAccount() : "" %>" 
                                   <%= isUpdate ? "readonly bg-light" : "required" %>>
                        </div>
                        
                        <% if (!isUpdate) { %>
                        <div class="mb-3">
                            <label class="form-label">Password *</label>
                            <input type="password" name="pass" class="form-control" required>
                        </div>
                        <% } %>

                        <div class="row mb-3">
                            <div class="col">
                                <label class="form-label">Họ (Last Name)</label>
                                <input type="text" name="lastName" class="form-control" 
                                       value="<%= isUpdate ? (acc.getLastName() != null ? acc.getLastName() : "") : "" %>">
                            </div>
                            <div class="col">
                                <label class="form-label">Tên (First Name) *</label>
                                <input type="text" name="firstName" class="form-control" 
                                       value="<%= isUpdate ? acc.getFirstName() : "" %>" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="text" name="phone" class="form-control" pattern="[0-9]{10,11}" 
                                   title="Chỉ nhập số, 10-11 ký tự"
                                   value="<%= isUpdate ? (acc.getPhone() != null ? acc.getPhone() : "") : "" %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Birth Day</label>
                            <input type="date" name="birthday" class="form-control" 
                                   value="<%= isUpdate ? (acc.getBirthday() != null ? acc.getBirthday().toString() : "") : "" %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label d-block">Gender</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="true" 
                                       <%= isUpdate && acc.isGender() ? "checked" : (!isUpdate ? "checked" : "") %>>
                                <label class="form-check-label">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" value="false"
                                       <%= isUpdate && !acc.isGender() ? "checked" : "" %>>
                                <label class="form-check-label">Female</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Role in System</label>
                            <select name="roleInSystem" class="form-select">
                                <option value="0" <%= isUpdate && acc.getRoleInSystem() == 0 ? "selected" : "" %>>Staff (Nhân viên)</option>
                                <option value="1" <%= isUpdate && acc.getRoleInSystem() == 1 ? "selected" : "" %>>Administrator (Quản trị viên)</option>
                            </select>
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" name="isUse" class="form-check-input" value="true" 
                                   <%= isUpdate && acc.isIsUse() ? "checked" : (!isUpdate ? "checked" : "") %>>
                            <label class="form-check-label">Is active (Hoạt động)</label>
                        </div>
                        <hr>
                        <div class="d-flex justify-content-between">
                            <a href="MainController?action=ManageAccounts" class="btn btn-secondary">Cancel</a>
                            <button type="submit" class="btn <%= isUpdate ? "btn-warning" : "btn-success" %>">
                                <%= isUpdate ? "Update" : "Submit" %>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
