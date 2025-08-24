package com.bankapp.controller;


import com.bankapp.service.TransactionService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/customer/passbook")
public class PassbookServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TransactionService transactionService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        if (start == null) start = "2020-01-01";
        if (end == null) end = "2030-12-31";

        req.setAttribute("transactions", transactionService.getPassbook(userId, start, end));
        req.getRequestDispatcher("/WEB-INF/views/customer/passbook.jsp").forward(req, resp);
    }
}