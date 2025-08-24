<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login — Lavender Bank</title>
  <link rel="stylesheet" href="css/style.css"/>
</head>
<body class="auth">
  <div class="auth-card">
    <h1>Login</h1>
    <form method="post" action="login">
      <label>Role</label>
      <select name="role" required>
        <option value="CUSTOMER">Customer</option>
        <option value="ADMIN">Admin</option>
      </select>
      <label>Username</label>
      <input name="username" required/>
      <label>Password</label>
      <input type="password" name="password" required/>
      <button class="btn full">Sign In</button>
      <p class="muted">No account? <a href="register.jsp">Register</a></p>
    </form>
    <div class="msg">
      <%= request.getParameter("e") != null ? "Invalid credentials / role" : "" %>
      <%= request.getParameter("msg") != null ? request.getParameter("msg") : "" %>
    </div>
  </div>
</body>
</html>
