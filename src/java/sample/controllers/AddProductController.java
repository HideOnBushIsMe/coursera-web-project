package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sample.dao.ProductDAO;
import sample.models.Account;
import sample.models.Product;

@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = "AddProductPageController";

        try {
            String productId = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String brief = request.getParameter("brief");
            String unit = request.getParameter("unit");
            int price = Integer.parseInt(request.getParameter("price"));
            int discount = Integer.parseInt(request.getParameter("discount"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));

            // Mặc định ảnh sản phẩm (có thể nâng cấp sau)
            String productImage = "/images/sanPham/default.jpg";

            // Lấy account đang đăng nhập
            HttpSession session = request.getSession();
            Account loginUser = (Account) session.getAttribute("LOGIN_USER");
            String account = loginUser.getAccount();

            Product p = new Product(productId, productName, productImage, brief,
                    null, typeId, account, unit, price, discount);

            ProductDAO dao = new ProductDAO();
            if (dao.insertProduct(p)) {
                url = "MainController?action=ManageProducts";
            }

        } catch (Exception e) {
            log("Error at AddProductController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
