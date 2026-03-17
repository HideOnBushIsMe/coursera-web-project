package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.CategoryDAO;

@WebServlet(name = "DeleteCategoryController", urlPatterns = {"/DeleteCategoryController"})
public class DeleteCategoryController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=ManageCategories";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = SUCCESS;
        try {
            int typeId = Integer.parseInt(request.getParameter("typeId"));
            CategoryDAO dao = new CategoryDAO();
            dao.deleteCategory(typeId);
        } catch (Exception e) {
            log("Error at DeleteCategoryController: " + e.toString());
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
