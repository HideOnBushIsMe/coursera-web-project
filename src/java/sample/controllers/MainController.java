package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    //Login&Logout
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String LOGOUT = "Logout";
    private static final String LOGOUT_CONTROLLER = "LogoutController";

    // Account Actions
    private static final String MANAGE_ACCOUNTS = "ManageAccounts";
    private static final String ACCOUNT_CONTROLLER = "AccountController";
    private static final String ADD_ACCOUNT = "AddAccount";
    private static final String ADD_ACCOUNT_CONTROLLER = "AddAccountController";
    private static final String DELETE_ACCOUNT = "DeleteAccount";
    private static final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountController";
    private static final String UPDATE_ACCOUNT_PAGE = "UpdateAccountPage";
    private static final String UPDATE_ACCOUNT_PAGE_CONTROLLER = "UpdateAccountPageController";
    private static final String UPDATE_ACCOUNT = "UpdateAccount";
    private static final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountController";

    // Product Actions
    private static final String MANAGE_PRODUCTS = "ManageProducts";
    private static final String PRODUCT_CONTROLLER = "ProductController";
    private static final String ADD_PRODUCT_PAGE = "AddProductPage";
    private static final String ADD_PRODUCT_PAGE_CONTROLLER = "AddProductPageController";
    private static final String ADD_PRODUCT = "AddProduct";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    private static final String DELETE_PRODUCT = "DeleteProduct";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    private static final String UPDATE_PRODUCT_PAGE = "UpdateProductPage";
    private static final String UPDATE_PRODUCT_PAGE_CONTROLLER = "UpdateProductPageController";
    private static final String UPDATE_PRODUCT = "UpdateProduct";
    private static final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";

    // Category Actions
    private static final String MANAGE_CATEGORIES = "ManageCategories";
    private static final String CATEGORY_CONTROLLER = "CategoryController";
    private static final String ADD_CATEGORY_PAGE = "AddCategoryPage";
    private static final String ADD_CATEGORY_PAGE_CONTROLLER = "AddCategoryPageController";
    private static final String ADD_CATEGORY = "AddCategory";
    private static final String ADD_CATEGORY_CONTROLLER = "AddCategoryController";
    private static final String DELETE_CATEGORY = "DeleteCategory";
    private static final String DELETE_CATEGORY_CONTROLLER = "DeleteCategoryController";
    private static final String UPDATE_CATEGORY_PAGE = "UpdateCategoryPage";
    private static final String UPDATE_CATEGORY_PAGE_CONTROLLER = "UpdateCategoryPageController";
    private static final String UPDATE_CATEGORY = "UpdateCategory";
    private static final String UPDATE_CATEGORY_CONTROLLER = "UpdateCategoryController";

    // Public Actions
    private static final String HOME = "Home";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String PRODUCT_DETAIL_PUBLIC = "ProductDetailPublic";
    private static final String PRODUCT_DETAIL_PUBLIC_CONTROLLER = "ProductDetailController";
    private static final String ADD_TO_CART = "AddToCart";
    private static final String VIEW_CART = "ViewCart";
    private static final String REMOVE_FROM_CART = "RemoveFromCart";
    private static final String UPDATE_CART = "UpdateCart";
    private static final String CART_CONTROLLER = "CartController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_CONTROLLER;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = HOME_CONTROLLER;
            } else if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(action)) {
                url = LOGOUT_CONTROLLER;
            } // Public Logic
            else if (HOME.equals(action)) {
                url = HOME_CONTROLLER;
            } else if (PRODUCT_DETAIL_PUBLIC.equals(action)) {
                url = PRODUCT_DETAIL_PUBLIC_CONTROLLER;
            } else if (ADD_TO_CART.equals(action) || VIEW_CART.equals(action) || REMOVE_FROM_CART.equals(action) || UPDATE_CART.equals(action)) {
                url = CART_CONTROLLER;
            }
            // Account Logic
            else if (MANAGE_ACCOUNTS.equals(action)) {
                url = ACCOUNT_CONTROLLER;
            } else if (ADD_ACCOUNT.equals(action)) {
                url = ADD_ACCOUNT_CONTROLLER;
            } else if (DELETE_ACCOUNT.equals(action)) {
                url = DELETE_ACCOUNT_CONTROLLER;
            } else if (UPDATE_ACCOUNT_PAGE.equals(action)) {
                url = UPDATE_ACCOUNT_PAGE_CONTROLLER;
            } else if (UPDATE_ACCOUNT.equals(action)) {
                url = UPDATE_ACCOUNT_CONTROLLER;
            } // Product Logic
            else if (MANAGE_PRODUCTS.equals(action)) {
                url = PRODUCT_CONTROLLER;
            } else if (ADD_PRODUCT_PAGE.equals(action)) {
                url = ADD_PRODUCT_PAGE_CONTROLLER;
            } else if (ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT_PAGE.equals(action)) {
                url = UPDATE_PRODUCT_PAGE_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(action)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } // Category Logic
            else if (MANAGE_CATEGORIES.equals(action)) {
                url = CATEGORY_CONTROLLER;
            } else if (ADD_CATEGORY_PAGE.equals(action)) {
                url = ADD_CATEGORY_PAGE_CONTROLLER;
            } else if (ADD_CATEGORY.equals(action)) {
                url = ADD_CATEGORY_CONTROLLER;
            } else if (DELETE_CATEGORY.equals(action)) {
                url = DELETE_CATEGORY_CONTROLLER;
            } else if (UPDATE_CATEGORY_PAGE.equals(action)) {
                url = UPDATE_CATEGORY_PAGE_CONTROLLER;
            } else if (UPDATE_CATEGORY.equals(action)) {
                url = UPDATE_CATEGORY_CONTROLLER;
            }

        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            if ("LogoutController".equals(url)) {
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
