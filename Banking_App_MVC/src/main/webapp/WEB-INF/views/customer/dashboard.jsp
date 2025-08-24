<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.bankapp.model.Account" %>
<%
    Account account = (Account) request.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="../../../../css/style.css">
</head>
<body>
<header>
    <h1>🏦 Welcome, ${name}</h1>
    <nav>
        <a href="passbook">Passbook</a>
        <a href="transfer">Transfer</a>
        <a href="../logout">Logout</a>
    </nav>
</header>

<main>
    <h2>Your Account</h2>
    <p><strong>Account Number:</strong> <%= account.getAccountNumber() %></p>
    <p><strong>Balance:</strong> ₹<%= String.format("%.2f", account.getBalance()) %></p>
</main>
</body>
</html>