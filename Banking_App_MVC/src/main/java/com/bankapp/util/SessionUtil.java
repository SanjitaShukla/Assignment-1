package com.bankapp.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static final String USER_ID = "uid";
    public static final String USER_ROLE = "role";

    public static boolean isLoggedIn(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s != null && s.getAttribute(USER_ID) != null;
    }

    public static Long currentUserId(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return (s == null) ? null : (Long) s.getAttribute(USER_ID);
    }

    public static String currentRole(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return (s == null) ? null : (String) s.getAttribute(USER_ROLE);
    }
}
