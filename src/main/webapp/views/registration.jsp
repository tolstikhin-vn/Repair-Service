<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Registration Page</title>
</head>
<body>
<h1>Registration</h1>
<form action="registration" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" required><br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required><br>
  <input type="submit" value="Register">
</form>
<p>Already registered? <a href="login">Login now</a></p>
</body>
</html>
