package com.bankapp.controller;

import com.bankapp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/customers")
public class ViewCustomersServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", userService.getAllCustomers());
        req.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(req, resp);
    }
}
