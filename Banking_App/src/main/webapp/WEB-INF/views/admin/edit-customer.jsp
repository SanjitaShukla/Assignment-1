<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bankapp.model.User" %>
<%
    User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
    <link rel="stylesheet" href="../../../../css/style.css">
</head>
<body>
<header>
    <h1>🏦 Edit Customer</h1>
    <a href="dashboard">Back</a>
</header>

<main>
    <h2>Edit <%= user.getName() %></h2>
    <form action="edit-customer" method="post">
        <input type="hidden" name="userId" value="<%= user.getUserId() %>">
        <label>Name: <input type="text" name="name" value="<%= user.getName() %>" required></label><br><br>
        <label>Email: <input type="email" name="email" value="<%= user.getEmail() %>"></label><br><br>
        <button type="submit" class="btn btn-success">Update</button>
    </form>
</main>
</body>
</html>