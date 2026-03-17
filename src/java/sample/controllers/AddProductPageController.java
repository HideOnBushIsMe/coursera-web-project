package sample.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import sample.dao.CategoryDAO;
import sample.models.Category;

@WebServlet(name = "AddProductPageController", urlPatterns = {"/AddProductPageController"})
public class AddProductPageController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CategoryDAO dao = new CategoryDAO();
            List<Category> list = dao.getAllCategories();
            request.setAttribute("LIST_CATEGORY", list);
        } catch (Exception e) {
            log("Error at AddProductPageController: " + e.toString());
        } finally {
            request.getRequestDispatcher("productDetail.jsp").forward(request, response);
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
