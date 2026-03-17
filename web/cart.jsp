<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
        <!-- Bootstrap 3 CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <style>
            .cart-img { width: 60px; height: 60px; object-fit: contain; }
            .total-section { font-size: 1.5em; font-weight: bold; color: #d9534f; margin-top: 20px; }
        </style>
    </head>
    <body class="bg-light">
        <nav class="navbar navbar-default">
            <div class="container">
                <div class="navbar-header">
                    <a class="navbar-brand" href="HomeController">My Store</a>
                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="HomeController">Tiếp tục mua sắm</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="page-header">
                <h2>Giỏ hàng của bạn</h2>
            </div>

            <c:choose>
                <c:when test="${not empty sessionScope.CART.items}">
                    <div class="panel panel-default">
                        <div class="table-responsive">
                            <table class="table table-hover table-striped mb-0">
                                <thead>
                                    <tr class="active">
                                        <th>Sản phẩm</th>
                                        <th class="text-center">Hình ảnh</th>
                                        <th class="text-right">Đơn giá</th>
                                        <th class="text-center" style="width: 150px;">Số lượng</th>
                                        <th class="text-right">Thành tiền</th>
                                        <th class="text-center">Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${sessionScope.CART.items.values()}">
                                        <tr>
                                            <td style="vertical-align: middle;"><b>${item.product.productName}</b></td>
                                            <td class="text-center">
                                                <img src="${item.product.productImage}" class="cart-img" alt="Sản phẩm">
                                            </td>
                                            <td class="text-right" style="vertical-align: middle;">
                                                <c:set var="currentPrice" value="${item.product.discount > 0 ? (item.product.price * (100 - item.product.discount) / 100) : item.product.price}" />
                                                <fmt:formatNumber value="${currentPrice}" pattern="#,###" /> / ${item.product.unit}
                                            </td>
                                            <td class="text-center" style="vertical-align: middle;">
                                                <form action="CartController" method="POST" class="form-inline">
                                                    <input type="hidden" name="action" value="UpdateCart">
                                                    <input type="hidden" name="productId" value="${item.product.productId}">
                                                    <div class="input-group input-group-sm">
                                                        <input type="number" name="quantity" value="${item.quantity}" min="1" class="form-control text-center" style="width: 60px;">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="submit">Cập nhật</button>
                                                        </span>
                                                    </div>
                                                </form>
                                            </td>
                                            <td class="text-right" style="vertical-align: middle; color: #d9534f; font-weight: bold;">
                                                <fmt:formatNumber value="${currentPrice * item.quantity}" pattern="#,###" />
                                            </td>
                                            <td class="text-center" style="vertical-align: middle;">
                                                <a href="CartController?action=RemoveFromCart&productId=${item.product.productId}" class="btn btn-danger btn-xs" onclick="return confirm('Xóa sản phẩm này?')">
                                                    <span class="glyphicon glyphicon-trash"></span> Xóa
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 col-md-offset-6 text-right">
                            <div class="total-section">
                                Tổng tiền: <fmt:formatNumber value="${sessionScope.CART.getTotal()}" pattern="#,###" /> VNĐ
                            </div>
                            <hr>
                            <a href="#" class="btn btn-success btn-lg">Tiến hành thanh toán</a>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="jumbotron text-center">
                        <span class="glyphicon glyphicon-shopping-cart" style="font-size: 5em; color: #ccc;"></span>
                        <h3>Giỏ hàng đang trống!</h3>
                        <p>Hãy quay lại cửa hàng để chọn sản phẩm bạn yêu thích.</p>
                        <p><a class="btn btn-primary btn-lg" href="HomeController" role="button">Đi mua sắm ngay</a></p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </body>
</html>
