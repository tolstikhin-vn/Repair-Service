<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/home-page-style.css">
    <script src="/script/main-script.js"></script>
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
    <div class="container">
        <div class="item">
            <a href="smartphones">
                <img src="/images/smartphone.jpg" alt="Смартфоны">
            </a>
            <p class="caption">Смартфоны</p>
        </div>

        <div class="item">
            <a href="tablets">
                <img src="/images/tablet.jpg" alt="Планшеты">
            </a>
            <p class="caption">Планшеты</p>
        </div>

        <div class="item">
            <a href="laptops">
                <img src="/images/notebook.jpg" alt="Ноутбуки">
            </a>
            <p class="caption">Ноутбуки</p>
        </div>

    </div>
</main>
<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>
</body>
</html>