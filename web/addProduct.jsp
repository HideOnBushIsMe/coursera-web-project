<%@page import="sample.models.Category"%><%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head><title>Add Product</title><link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"></head>
    <body class="bg-light"><div class="container mt-5"><div class="card shadow-sm mx-auto" style="max-width: 700px;">
                <div class="card-header bg-success text-white"><h4>Add New Product</h4></div>
                <div class="card-body">
                    <form action="MainController" method="POST">
                        <input type="hidden" name="action" value="AddProduct">
                        <div class="row mb-3"><div class="col-md-4"><label class="form-label">Product ID *</label><input type="text" name="productId" class="form-control" required></div>
                            <div class="col-md-8"><label class="form-label">Product Name *</label><input type="text" name="productName" class="form-control" required></div></div>
                        <div class="mb-3"><label class="form-label">Brief Description</label><textarea name="brief" class="form-control" rows="3"></textarea></div>
                        <div class="row mb-3">
                            <div class="col"><label class="form-label">Category</label><select name="typeId" class="form-select">
                                    <% List<Category> cList = (List<Category>) request.getAttribute("LIST_CATEGORY");
                            if (cList != null)
                                for (Category c : cList) {%><option value="<%= c.getTypeId()%>"><%= c.getCategoryName()%></option><% }%>
                                </select></div>
                            <div class="col"><label class="form-label">Unit</label><input type="text" name="unit" class="form-control" value="pcs"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col"><label class="form-label">Price (VNĐ)</label><input type="number" name="price" class="form-control" value="0"></div>
                            <div class="col"><label class="form-label">Discount (%)</label><input type="number" name="discount" class="form-control" min="0" max="100" value="0"></div>
                        </div>
                        <div class="d-flex justify-content-between"><a href="MainController?action=ManageProducts" class="btn btn-secondary">Cancel</a><button type="submit" class="btn btn-success">Save Product</button></div>
                    </form>
                </div>
            </div></div></body>
</html>
