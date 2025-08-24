package com.bankapp.filter;

import com.bankapp.util.SessionUtil;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/customer/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        if (session == null || !SessionUtil.isLoggedIn(session)) {
            response.sendRedirect("../login");
            return;
        }

        if ("CUSTOMER".equals(session.getAttribute("role"))) {
            chain.doFilter(req, resp);
        } else {
            response.sendRedirect("../login");
        }
    }
}