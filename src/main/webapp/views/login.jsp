<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" href="/css/main-style.css">
    <script src="/script/user-page-script.js"></script>
</head>
<body>
<header>
    <%--  <a href="/">--%>
    <%--  </a>--%>
    <nav>
        <ul>
            <li><a href="track-repairs">Узнать статус ремонта</a></li>
            <li><a href="about">О компании</a></li>
            <li><a href="addresses">Адреса и контакты</a></li>
        </ul>
    </nav>
    <div id="login">
        <a href="login">Профиль</a>
    </div>
</header>
<h1>Login</h1>
<form action="login" method="post">
    <label for="username">Логин:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="Login">
</form>
<p>Не зарегистрированы? <a href="registration">Зарегистрироваться</a></p>
<p>Забыли пароль? <a href="restore-password">Восстановить пароль</a></p>
</body>
</html>
