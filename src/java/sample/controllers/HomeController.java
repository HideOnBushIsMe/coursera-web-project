package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.CategoryDAO;
import sample.dao.ProductDAO;
import sample.models.Category;
import sample.models.Product;

@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            ProductDAO pDao = new ProductDAO();
            CategoryDAO cDao = new CategoryDAO();
            
            String categoryIdStr = request.getParameter("categoryId");
            String minPriceStr = request.getParameter("minPrice");
            String maxPriceStr = request.getParameter("maxPrice");
            String isDiscountStr = request.getParameter("isDiscount");
            String sortOrder = request.getParameter("sortOrder");
            String searchTerm = request.getParameter("searchTerm");

            List<Product> listProduct;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                listProduct = pDao.searchProductsByName(searchTerm);
            } else if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                listProduct = pDao.getProductsByCategory(Integer.parseInt(categoryIdStr));
            } else if (minPriceStr != null || maxPriceStr != null || isDiscountStr != null || sortOrder != null) {
                Integer minPrice = (minPriceStr != null && !minPriceStr.isEmpty()) ? Integer.parseInt(minPriceStr) : null;
                Integer maxPrice = (maxPriceStr != null && !maxPriceStr.isEmpty()) ? Integer.parseInt(maxPriceStr) : null;
                Boolean isDiscount = (isDiscountStr != null && !isDiscountStr.isEmpty()) ? Boolean.parseBoolean(isDiscountStr) : null;
                listProduct = pDao.getFilteredProducts(minPrice, maxPrice, isDiscount, sortOrder);
            } else {
                listProduct = pDao.getAllProducts();
            }

            List<Category> listCategory = cDao.getAllCategories();
            
            request.setAttribute("LIST_PRODUCT", listProduct);
            request.setAttribute("LIST_CATEGORY", listCategory);
            
        } catch (Exception e) {
            e.printStackTrace();
            log("Error at HomeController: " + e.toString());
        } finally {
            request.getRequestDispatcher("home.jsp").forward(request, response);
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
