<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bankapp.model.User" %>
<%
    List<User> customers = (List<User>) request.getAttribute("customers");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="../../../../css/style.css">
</head>
<body>
<header>
    <h1>🏦 Admin Dashboard</h1>
    <nav>
        <a href="../logout">Logout</a>
    </nav>
</header>

<main>
    <h2>Customer Management</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Username</th>
            <th>Email</th>
            <th>Status</th>
            <th>Action</th>
            <th>Edit</th>
        </tr>
        <% for (User user : customers) { %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.isActive() ? "Active" : "Blocked" %></td>
            <td>
                <form action="toggle-status" method="post" style="display:inline;">
                    <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                    <input type="hidden" name="active" value="<%= user.isActive() %>">
                    <button type="submit" class="btn btn-danger">
                        <%= user.isActive() ? "Block" : "Unblock" %>
                    </button>
                </form>
            </td>
            <td>
                <a href="edit-customer?userId=<%= user.getUserId() %>" class="btn btn-primary">Edit</a>
            </td>
        </tr>
        <% } %>
    </table>
</main>
</body>
</html>