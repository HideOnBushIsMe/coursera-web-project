package sample.controllers;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.dao.AccountDAO;
import sample.models.Account;

@WebServlet(name = "UpdateAccountController", urlPatterns = {"/UpdateAccountController"})
public class UpdateAccountController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "MainController?action=ManageAccounts";

        try {
            String accountId = request.getParameter("account");
            String pass = request.getParameter("pass");
            String lastName = request.getParameter("lastName");
            String firstName = request.getParameter("firstName");
            String phone = request.getParameter("phone");

            String birthdayStr = request.getParameter("birthday");
            Date birthday = null;
            if (birthdayStr != null && !birthdayStr.isEmpty()) {
                birthday = Date.valueOf(birthdayStr);
            }

            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
            int roleInSystem = Integer.parseInt(request.getParameter("roleInSystem"));
            boolean isUse = request.getParameter("isUse") != null;

            Account acc = new Account(accountId, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem);
            AccountDAO dao = new AccountDAO();

            boolean checkUpdate = dao.updateAccount(acc);
            if (checkUpdate) {
                url = "MainController?action=ManageAccounts";
            }
        } catch (Exception e) {
            log("Error at UpdateAccountController: " + e.toString());
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
