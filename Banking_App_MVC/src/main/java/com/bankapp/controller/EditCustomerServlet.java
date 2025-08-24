package com.bankapp.controller;

import com.bankapp.model.User;
import com.bankapp.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
// Remove the duplicate HttpSession import!
import java.io.IOException;

@WebServlet("/admin/edit-customer")
public class EditCustomerServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        req.setAttribute("user", userService.getUserById(userId));
        req.getRequestDispatcher("/WEB-INF/views/admin/edit-customer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUserId(Integer.parseInt(req.getParameter("userId")));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        userService.updateUser(user);
        resp.sendRedirect("dashboard");
    }
}