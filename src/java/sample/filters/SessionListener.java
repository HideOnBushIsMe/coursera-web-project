package sample.filters;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import sample.models.Account;
import sample.utils.SessionManager;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Account user = (Account) se.getSession().getAttribute("LOGIN_USER");
        if (user != null) {
            SessionManager.removeUserSession(user.getAccount());
        }
    }
}
