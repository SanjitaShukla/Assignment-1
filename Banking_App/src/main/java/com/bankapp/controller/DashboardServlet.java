package com.bankapp.controller;

import com.bankapp.service.AccountService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/customer/dashboard")
public class DashboardServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        req.setAttribute("account", accountService.getAccountByUserId(userId));
        req.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(req, resp);
    }
}