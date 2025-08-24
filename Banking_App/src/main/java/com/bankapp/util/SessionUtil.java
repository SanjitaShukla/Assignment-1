package com.bankapp.util;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static boolean isAdmin(HttpSession session) {
        return "ADMIN".equals(session.getAttribute("role"));
    }

    public static boolean isCustomer(HttpSession session) {
        return "CUSTOMER".equals(session.getAttribute("role"));
    }

    public static boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("userId") != null;
    }
}