package sample.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.ProductDAO;
import sample.models.Product;

@WebServlet(name = "ProductDetailController", urlPatterns = {"/ProductDetailController"})
public class ProductDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String productId = request.getParameter("productId");
            ProductDAO dao = new ProductDAO();
            Product product = dao.getProductById(productId);
            
            if (product != null) {
                HttpSession session = request.getSession();
                List<Product> viewedProducts = (List<Product>) session.getAttribute("VIEWED_PRODUCTS");
                if (viewedProducts == null) {
                    viewedProducts = new ArrayList<>();
                }
                
                // Add to viewed list if not already there
                boolean exists = false;
                for (Product p : viewedProducts) {
                    if (p.getProductId().equals(productId)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    viewedProducts.add(product);
                }
                session.setAttribute("VIEWED_PRODUCTS", viewedProducts);
                
                // Calculate Segmentation
                double avgPrice = 0;
                if (!viewedProducts.isEmpty()) {
                    for (Product p : viewedProducts) {
                        avgPrice += p.getPrice();
                    }
                    avgPrice /= viewedProducts.size();
                }
                
                String segmentation = "N/A";
                if (avgPrice > 0) {
                    if (avgPrice < 5000000) segmentation = "Low Income";
                    else if (avgPrice <= 15000000) segmentation = "Middle Income";
                    else segmentation = "High Income";
                }
                session.setAttribute("USER_SEGMENTATION", segmentation);
                
                request.setAttribute("PRODUCT", product);
            }
            
        } catch (Exception e) {
            log("Error at ProductDetailController: " + e.toString());
        } finally {
            request.getRequestDispatcher("productDetailPublic.jsp").forward(request, response);
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
