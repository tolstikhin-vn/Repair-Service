<%@ page import="ru.tolstikhin.entity.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет администратора</title>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/admin-profile-style.css">
    <script src="/script/admin-script.js"></script>
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
        <h1>Управление пользователями</h1>
        <form method="get" action="users-list">
            <label for="search-input">Поиск по логину:</label>
            <input type="text" id="search-input" name="search" placeholder="Введите логин">
            <button type="submit">Найти</button>
        </form>
        <br>
        <label for="user-select">Выберите пользователя</label>
        <br>
        <select id="user-select" name="user" onchange="getUserData(this.value)">
            <option value="" selected>Пользователь не выбран</option>
            <% for (User user : (LinkedList<User>) request.getAttribute("users")) { %>
            <option id="id-value" value="<%= user.getId() %>"><%= user.getLogin() %>
                (<%= user.getName() %> <%= user.getSurname() %>)
            </option>
            <% } %>
        </select>

        <table class="user-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>логин</th>
                <th>имя</th>
                <th>фамилия</th>
                <th>активный</th>
                <th>архивный</th>
                <th>текущее кол-во ост. попыток<br>ввода пароля</th>
                <th>кол-во попыток ввода<br>пароля всего</th>
                <th>последный вход</th>
                <th>последний выход</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="user-id"></td>
                <td class="user-login"></td>
                <td class="user-name"></td>
                <td class="user-surname"></td>
                <td class="user-active"></td>
                <td class="user-deleted"></td>
                <td class="user-pass-fail-count"></td>
                <td class="user-def-pass-fail-count"></td>
                <td class="user-last-login"></td>
                <td class="user-last-logout"></td>
            </tr>
            </tbody>
        </table>
    </div>

    <h2>Изменить пароль</h2>
    <form method="post" action="change-password">
        <%--        <label for="new-password-input">Изменить пароль</label>--%>
        <input type="hidden" name="id-user-for-changing">
        <input type="text" id="new-password-input" name="new-password" placeholder="Новый пароль">
        <button type="submit" id="change-password-btn">Изменить</button>
    </form>
    <br>

    <h2>Заблокировать пользователя</h2>
    <form method="post" action="user-ban">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Заблокировать</button>
    </form>

    <h2>Разблокировать пользователя</h2>
    <form method="post" action="user-unban">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Разблокировать</button>
    </form>

    <h2>Поместить пользователя в архив</h2>
    <form method="post" action="user-soft-del">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Поместить в архив</button>
    </form>

    <h2>Убрать пользователя из архива</h2>
    <form method="post" action="user-restore-soft-del">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Убрать их архива</button>
    </form>

    <h2>Удалить пользователя из системы</h2>
    <form method="post" action="user-hard-del">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Удалить пользователя</button>
    </form>

    <h2>Сбросить текущее количество попыток входа в аккаунт</h2>
    <form method="post" action="set-pass-fail-count-reset">
        <input type="hidden" name="id-user-for-changing">
        <button type="submit">Сбросить текущие попытки</button>
    </form>
    <div id="log_out_class">
        <a href="logout">Выйти</a>
    </div>
</main>
<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>
</body>
</html>
