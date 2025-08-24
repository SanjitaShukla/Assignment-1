package com.bankapp.controller;

import com.bankapp.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
// Remove the duplicate HttpSession import!
import java.io.IOException;

@WebServlet("/admin/toggle-status")
public class ToggleUserStatusServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("userId"));
        boolean active = Boolean.parseBoolean(req.getParameter("active"));
        userService.updateUserStatus(userId, !active);
        resp.sendRedirect("dashboard");
    }
}