package sample.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.models.Account;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/MainController"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String action = req.getParameter("action");

        // List of actions that require Admin role
        boolean isAdminAction = action != null && (
                action.startsWith("Manage") || 
                action.contains("Account") || 
                action.contains("Category") || 
                action.contains("Product")
        ) && !action.equals("ViewProductDetailPublic"); // Example exclusion

        // Refined admin actions check based on MainController
        String[] adminActions = {
            "ManageAccounts", "AddAccount", "DeleteAccount", "UpdateAccountPage", "UpdateAccount",
            "ManageProducts", "AddProductPage", "AddProduct", "DeleteProduct", "UpdateProductPage", "UpdateProduct",
            "ManageCategories", "AddCategoryPage", "AddCategory", "DeleteCategory", "UpdateCategoryPage", "UpdateCategory"
        };

        boolean needsAdmin = false;
        if (action != null) {
            for (String s : adminActions) {
                if (s.equals(action)) {
                    needsAdmin = true;
                    break;
                }
            }
        }

        if (needsAdmin) {
            HttpSession session = req.getSession(false);
            Account loginUser = (session != null) ? (Account) session.getAttribute("LOGIN_USER") : null;
            if (loginUser == null || loginUser.getRoleInSystem() != 1) {
                res.sendRedirect("login.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
