<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register — Lavender Bank</title>
  <link rel="stylesheet" href="css/style.css"/>
</head>
<body class="auth">
<div class="auth-card">
  <h1>Create Customer</h1>
  <form method="post" action="register">
    <label>Username</label>
    <input name="username" required/>
    <label>Password</label>
    <input type="password" name="password" required/>
    <button class="btn full">Register</button>
    <p class="muted">Back to <a href="login.jsp">Login</a></p>
  </form>
</div>
</body>
</html>
