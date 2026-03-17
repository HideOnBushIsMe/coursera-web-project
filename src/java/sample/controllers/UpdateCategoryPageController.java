package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.CategoryDAO;
import sample.models.Category;

@WebServlet(name = "UpdateCategoryPageController", urlPatterns = {"/UpdateCategoryPageController"})
public class UpdateCategoryPageController extends HttpServlet {

    private static final String SUCCESS = "categoryDetail.jsp";
    private static final String ERROR = "MainController?action=ManageCategories";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            CategoryDAO dao = new CategoryDAO();
            Category category = dao.getCategoryById(typeId);
            if (category != null) {
                request.setAttribute("CATEGORY_INFO", category);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateCategoryPageController: " + e.toString());
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
