package sample.utils;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class SessionManager {
    private static Map<String, HttpSession> userSessions = new HashMap<>();

    public static synchronized void addUserSession(String accountId, HttpSession session) {
        if (userSessions.containsKey(accountId)) {
            HttpSession oldSession = userSessions.get(accountId);
            if (oldSession != null && !oldSession.getId().equals(session.getId())) {
                try {
                    oldSession.invalidate();
                } catch (IllegalStateException e) {
                    // Session already invalidated
                }
            }
        }
        userSessions.put(accountId, session);
    }

    public static synchronized void removeUserSession(String accountId) {
        userSessions.remove(accountId);
    }
}
