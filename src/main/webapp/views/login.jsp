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
    <nav>
        <a href="/">
            <div class="site-logotype">
                <svg xmlns="http://www.w3.org/2000/svg" width="30px" height="48px">
                    <rect x="2" y="2" width="26" height="44" rx="2" fill="none" stroke="black" stroke-width="4"/>
                    <rect x="3" y="3" width="24" height="42" rx="2" fill="#ccc" stroke="none"/>
                    <circle cx="14.5" cy="41" r="2" fill="black"/>
                </svg>
            </div>
        </a>
        <ul>
            <li><a href="repair-status">Узнать статус ремонта</a></li>
            <li><a href="about">О нас</a></li>
            <li><a href="addresses">Адреса и контакты</a></li>
        </ul>
    </nav>
    <div id="login">
        <a href="login">Профиль</a>
    </div>
</header>
<main>
    <div class="input-user-data">
        <h1>Вход</h1>
        <form class="input-user-data-form" action="login" method="post" >
            <label for="username">Логин:</label>
            <input type="text" id="username" name="username" required><br>
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required><br>
            <input type="submit" value="Войти">
        </form>
        <p>Не зарегистрированы? <a href="registration">Регистрация</a></p>
    </div>
</main>
<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>
</body>
</html>
