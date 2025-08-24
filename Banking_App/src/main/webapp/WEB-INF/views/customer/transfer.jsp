<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Transfer Money</title>
    <link rel="stylesheet" href="../../../../css/style.css">
</head>
<body>
<header>
    <h1>🏦 Transfer Money</h1>
    <a href="dashboard">Back</a>
</header>

<main>
    <h2>Transfer Funds</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p class="error"><%= request.getAttribute("error") %></p>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
        <p class="success"><%= request.getAttribute("success") %></p>
    <% } %>

    <form action="transfer" method="post">
        <label>To Account Number: 
            <input type="text" name="toAccount" required></label><br><br>
        <label>Amount: 
            <input type="number" step="0.01" name="amount" required></label><br><br>
        <button type="submit" class="btn btn-primary">Transfer</button>
    </form>
</main>
</body>
</html>