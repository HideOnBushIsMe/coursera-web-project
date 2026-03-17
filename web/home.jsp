<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <!-- Bootstrap 3 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <style>
            .product-card { margin-bottom: 20px; padding: 15px; border: 1px solid #ddd; border-radius: 4px; background: #fff; }
            .product-img { height: 150px; width: 100%; object-fit: contain; margin-bottom: 10px; }
            .price { color: #d9534f; font-weight: bold; font-size: 1.2em; }
            .old-price { text-decoration: line-through; color: #777; font-size: 0.9em; margin-right: 5px; }
            .unit-label { color: #555; font-size: 0.9em; margin-left: 3px; }
        </style>
    </head>
    <body class="bg-light text-center">
        <!-- Header -->
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="HomeController">Cửa Hàng Của Tôi</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${not empty sessionScope.LOGIN_USER}">
                                <li><a>Chào, <b>${sessionScope.LOGIN_USER.firstName}</b></a></li>
                                <li><a href="MainController?action=Logout">Thoát</a></li>
                                <c:if test="${sessionScope.LOGIN_USER.roleInSystem == 1}">
                                    <li><a href="dashboard.jsp" style="color: #d9534f;">Quản trị</a></li>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <li><a href="login.jsp">Đăng nhập</a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a href="CartController?action=ViewCart">Giỏ hàng (${sessionScope.CART.items.size() > 0 ? sessionScope.CART.items.size() : 0})</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="row">
                <!-- Sidebar -->
                <div class="col-md-3 text-left">
                    <div class="panel panel-primary">
                        <div class="panel-heading">Danh mục</div>
                        <div class="list-group">
                            <a href="HomeController" class="list-group-item ${empty param.categoryId ? 'active' : ''}">Tất cả</a>
                            <c:forEach var="cat" items="${requestScope.LIST_CATEGORY}">
                                <a href="HomeController?categoryId=${cat.typeId}" class="list-group-item ${param.categoryId == cat.typeId ? 'active' : ''}">
                                    ${cat.categoryName}
                                </a>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="panel panel-default">
                        <div class="panel-heading">Bộ lọc giá</div>
                        <div class="panel-body text-left">
                            <form action="HomeController" method="GET">
                                <div class="form-group">
                                    <input type="number" name="minPrice" class="form-control input-sm" placeholder="Từ..." value="${param.minPrice}">
                                </div>
                                <div class="form-group">
                                    <input type="number" name="maxPrice" class="form-control input-sm" placeholder="Đến..." value="${param.maxPrice}">
                                </div>
                                <button type="submit" class="btn btn-primary btn-sm btn-block text-center">Lọc ngay</button>
                            </form>
                        </div>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="col-md-9">
                    <div class="row">
                        <c:forEach var="p" items="${requestScope.LIST_PRODUCT}">
                            <div class="col-sm-4">
                                <div class="product-card">
                                    <c:choose>
                                        <c:when test="${empty p.productImage}">
                                            <img src="https://placehold.co/300x300?text=No+Image" class="product-img" alt="No image">
                                        </c:when>
                                        <c:otherwise>
                                            <img src="${p.productImage}" class="product-img" alt="${p.productName}">
                                        </c:otherwise>
                                    </c:choose>
                                    <h5 style="height: 35px; overflow: hidden; margin-bottom: 10px;"><b>${p.productName}</b></h5>
                                    <p>
                                        <c:choose>
                                            <c:when test="${p.discount > 0}">
                                                <span class="old-price"><fmt:formatNumber value="${p.price}" pattern="#,###" /></span>
                                                <span class="price"><fmt:formatNumber value="${p.price * (100 - p.discount) / 100}" pattern="#,###" /></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="price"><fmt:formatNumber value="${p.price}" pattern="#,###" /></span>
                                            </c:otherwise>
                                        </c:choose>
                                        <span class="unit-label"> / ${p.unit}</span>
                                    </p>
                                    <div class="btn-group btn-group-justified" style="margin-top: 10px;">
                                        <a href="ProductDetailController?productId=${p.productId}" class="btn btn-default btn-sm">Chi tiết</a>
                                        <a href="CartController?action=AddToCart&productId=${p.productId}" class="btn btn-primary btn-sm">Mua ngay</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <c:if test="${empty requestScope.LIST_PRODUCT}">
                        <div class="alert alert-info">Không tìm thấy sản phẩm nào phù hợp.</div>
                    </c:if>
                </div>
            </div>
        </div>

        <footer class="container" style="margin-top: 50px; border-top: 1px solid #eee; padding: 20px; color: #999;">
            <p>&copy; 2026 Product System</p>
        </footer>

        <!-- Scripts -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
