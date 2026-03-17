package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.CategoryDAO;
import sample.models.Category;

@WebServlet(name = "AddCategoryController", urlPatterns = {"/AddCategoryController"})
public class AddCategoryController extends HttpServlet {

    private static final String SUCCESS = "MainController?action=ManageCategories";
    private static final String ERROR = "addCategory.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String categoryName = request.getParameter("categoryName");
            String memo = request.getParameter("memo");

            Category category = new Category(0, categoryName, memo);
            CategoryDAO dao = new CategoryDAO();
            if (dao.insertCategory(category)) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Thêm danh mục thất bại.");
            }
        } catch (Exception e) {
            log("Error at AddCategoryController: " + e.toString());
            request.setAttribute("ERROR", "Lỗi hệ thống: " + e.getMessage());
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
