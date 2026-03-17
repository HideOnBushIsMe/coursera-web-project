package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.dao.AccountDAO;
import sample.models.Account;

@WebServlet(name = "DeleteAccountController", urlPatterns = {"/DeleteAccountController"})
public class DeleteAccountController extends HttpServlet {

    private static final String ERROR = "MainController?action=ManageAccounts";
    private static final String SUCCESS = "MainController?action=ManageAccounts";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String accountId = request.getParameter("accountId");

            // Lấy thông tin user đang đăng nhập (để ngăn không cho tự xóa chính mình)
            HttpSession session = request.getSession();
            Account loginUser = (Account) session.getAttribute("LOGIN_USER");

            if (loginUser != null) {
                if (loginUser.getAccount().equals(accountId)) {
                    // Nếu đang tự xóa chính mình -> Báo lỗi
                    request.setAttribute("ERROR_MSG", "Lỗi: Bạn không thể tự xóa tài khoản đang đăng nhập!");
                } else {
                    AccountDAO dao = new AccountDAO();
                    boolean checkDelete = dao.deleteAccount(accountId);
                    if (checkDelete) {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at DeleteAccountController: " + e.toString());
            // Bắt lỗi khóa ngoại nếu Account này đã tạo Product
            if (e.toString().contains("REFERENCE constraint") || e.toString().contains("FOREIGN KEY")) {
                request.setAttribute("ERROR_MSG", "Không thể xóa tài khoản này vì đã có dữ liệu ràng buộc (sản phẩm, v.v.)");
            }
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
