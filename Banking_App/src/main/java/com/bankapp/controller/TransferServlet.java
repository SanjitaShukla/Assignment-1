package com.bankapp.controller;



import com.bankapp.service.AccountService;
import com.bankapp.service.TransactionService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/customer/transfer")
public class TransferServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AccountService accountService = new AccountService();
    private TransactionService transactionService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        String toAccNum = req.getParameter("toAccount");
        double amount = Double.parseDouble(req.getParameter("amount"));

        var fromAcc = accountService.getAccountByUserId(userId);
        var toAcc = accountService.getAccountByNumber(toAccNum);

        if (fromAcc == null || toAcc == null || fromAcc.getBalance() < amount) {
            req.setAttribute("error", "Invalid transfer");
            req.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(req, resp);
            return;
        }

        transactionService.transfer(fromAcc.getAccountId(), toAcc.getAccountId(), amount);
        accountService.updateBalance(fromAcc.getAccountNumber(), fromAcc.getBalance() - amount);
        accountService.updateBalance(toAcc.getAccountNumber(), toAcc.getBalance() + amount);

        req.setAttribute("success", "Transfer successful!");
        req.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(req, resp);
    }
}