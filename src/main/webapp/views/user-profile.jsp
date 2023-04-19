<%@ page import="ru.tolstikhin.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/user-profile-style.css">
</head>
<body>
<header>
<%--    <a href="/">--%>
<%--    </a>--%>
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
<main>
    <h1>Welcome to your Profile Page</h1>
    <div class="personal-data">
        <h2>Personal Information</h2>
        <p id="user-info">Hello, <%= ((User) session.getAttribute("user")).getName() + " " + ((User) session.getAttribute("user")).getSurname() %>
        <p>Your login is: <%= ((User) session.getAttribute("user")).getLogin() %>
        <form method="post" action="save-data">
            <label for="first-name">First Name:</label>
            <input type="text" id="first-name" name="first-name" required>
            <label for="last-name">Last Name:</label>
            <input type="text" id="last-name" name="last-name">
            <input type="submit" value="Save">
        </form>
    </div>
    <div id="log_out_class">
        <a href="logout">Выйти</a>
    </div>
</main>
</body>
</html>
