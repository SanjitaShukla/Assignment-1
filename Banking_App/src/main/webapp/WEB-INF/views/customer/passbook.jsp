<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bankapp.model.Transaction" %>
<%
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Passbook</title>
    <link rel="stylesheet" href="../../../../css/style.css">
</head>
<body>
<header>
    <h1>🏦 Passbook</h1>
    <a href="dashboard">Back</a>
</header>

<main>
    <h2>Transaction History</h2>
    <form method="get" style="margin-bottom: 20px;">
        <label>From: <input type="date" name="start" required></label>
        <label>To: <input type="date" name="end" required></label>
        <button type="submit" class="btn btn-primary">Filter</button>
    </form>

    <table>
        <tr>
            <th>Date</th>
            <th>Type</th>
            <th>From</th>
            <th>To</th>
            <th>Amount</th>
        </tr>
        <% for (Transaction tx : transactions) { %>
        <tr>
            <td><%= tx.getTimestamp() %></td>
            <td class="<%= "CREDIT".equals(tx.getType()) ? "credit" : "debit" %>">
                <%= tx.getType() %>
            </td>
            <td><%= tx.getFromAccountNum() %></td>
            <td><%= tx.getToAccountNum() %></td>
            <td>₹<%= String.format("%.2f", tx.getAmount()) %></td>
        </tr>
        <% } %>
    </table>
</main>
</body>
</html>