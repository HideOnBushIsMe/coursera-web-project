package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.models.Category;
import sample.models.Product;

@WebServlet(name = "UpdateProductPageController", urlPatterns = {"/UpdateProductPageController"})
public class UpdateProductPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String productId = request.getParameter("productId");

            ProductDAO pDao = new ProductDAO();
            CategoryDAO cDao = new CategoryDAO();

            Product p = pDao.getProductById(productId);
            List<Category> cList = cDao.getAllCategories();

            request.setAttribute("PRODUCT_INFO", p);
            request.setAttribute("LIST_CATEGORY", cList);

        } catch (Exception e) {
            log("Error at UpdateProductPageController: " + e.toString());
        } finally {
            request.getRequestDispatcher("productDetail.jsp")
                    .forward(request, response);
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
