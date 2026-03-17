package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sample.dao.ProductDAO;
import sample.models.Product;

@WebServlet(name = "UpdateProductController", urlPatterns = {"/UpdateProductController"})
public class UpdateProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String url = "MainController?action=ManageProducts";

        try {
            String productId = request.getParameter("productId");
            String productName = request.getParameter("productName");
            String brief = request.getParameter("brief");
            String unit = request.getParameter("unit");
            int price = Integer.parseInt(request.getParameter("price"));
            int discount = Integer.parseInt(request.getParameter("discount"));
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            String productImage = request.getParameter("productImage"); // Lấy lại ảnh cũ

            Product p = new Product(productId, productName, productImage,
                    brief, null, typeId, "", unit, price, discount);

            ProductDAO dao = new ProductDAO();
            dao.updateProduct(p);

        } catch (Exception e) {
            log("Error at UpdateProductController: " + e.toString());
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
