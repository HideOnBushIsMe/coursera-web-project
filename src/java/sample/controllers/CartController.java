package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.ProductDAO;
import sample.models.Cart;
import sample.models.Product;

@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "cart.jsp";
        try {
            String action = request.getParameter("action");
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
                cart = new Cart();
            }

            if ("AddToCart".equals(action)) {
                String productId = request.getParameter("productId");
                ProductDAO dao = new ProductDAO();
                Product p = dao.getProductById(productId);
                if (p != null) {
                    cart.add(p);
                }
                session.setAttribute("CART", cart);
                url = "HomeController";
            } else if ("RemoveFromCart".equals(action)) {
                String productId = request.getParameter("productId");
                cart.remove(productId);
                session.setAttribute("CART", cart);
            } else if ("UpdateCart".equals(action)) {
                String productId = request.getParameter("productId");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                cart.update(productId, quantity);
                session.setAttribute("CART", cart);
            } else if ("ViewCart".equals(action)) {
                url = "cart.jsp";
            }

        } catch (Exception e) {
            log("Error at CartController: " + e.toString());
        } finally {
            if (url.equals("HomeController")) {
                response.sendRedirect(url);
            } else {
                request.getRequestDispatcher(url).forward(request, response);
            }
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
