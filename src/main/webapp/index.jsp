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
        <ul>
            <li><a href="services">Узнать статус ремонта</a></li>
            <li><a href="about">О нас</a></li>
            <li><a href="addresses">Адреса и контакты</a></li>
        </ul>
<%--        <span class="dropdown">--%>
<%--            <%--%>
<%--                String address = (String) session.getAttribute("selectedAddress");--%>
<%--                if (address == null) {--%>
<%--                    address = "Выберите сервисный центр";--%>
<%--                }--%>
<%--            %>--%>
<%--            <p id="address" onclick="showDropdown()">--%>
<%--                <%= address %>--%>
<%--            </p>--%>
<%--            <ul id="dropdown-content">--%>
<%--            </ul>--%>
<%--        </span>--%>
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

<%--<footer>--%>
<!-- Футер страницы -->
<%--</footer>--%>
</body>
</html>