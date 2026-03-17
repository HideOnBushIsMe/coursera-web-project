<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>New Category</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light">
        <div class="container mt-5">
            <div class="card shadow-sm mx-auto" style="max-width: 600px;">
                <div class="card-header bg-white border-bottom-0 pt-4">
                    <h4 class="mb-0 text-dark" style="font-weight: 400; font-size: 1.5rem;">New category</h4>
                </div>
                <div class="card-body px-4 pb-4">
                    <%
                        String error = (String) request.getAttribute("ERROR");
                        if (error != null) {
                    %>
                    <div class="alert alert-danger"><%= error %></div>
                    <% } %>
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="AddCategory">
                        <div class="mb-4 row align-items-center">
                            <label class="col-sm-3 col-form-label fw-bold text-dark text-sm-end">Category name:</label>
                            <div class="col-sm-9">
                                <input type="text" name="categoryName" class="form-control" required>
                            </div>
                        </div>
                        <div class="mb-4 row">
                            <label class="col-sm-3 col-form-label fw-bold text-dark text-sm-end">Memo :</label>
                            <div class="col-sm-9">
                                <textarea name="memo" class="form-control" rows="3"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-9 offset-sm-3">
                                <button type="submit" class="btn btn-outline-secondary px-4">Save</button>
                                <a href="MainController?action=ManageCategories" class="btn btn-link text-decoration-none text-muted ms-2">Cancel</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>