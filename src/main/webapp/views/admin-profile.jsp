<%@ page import="ru.tolstikhin.entity.User" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет</title>
    <link rel="stylesheet" href="/css/main-style.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="services">Услуги</a></li>
            <li><a href="about">О компании</a></li>
            <li><a href="contacts">Контакты</a></li>
            <li><a href="addresses">Адреса</a></li>
        </ul>
    </nav>
    <div class="login">
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
            <label for="user-select">Выберите пользователя:</label>
            <select id="user-select" name="user">
                <option value="">Выберите пользователя</option>
<%--                <jsp:useBean id="users" scope="request" type="java.util.List"/>--%>
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}">${user.id} - ${user.login} - ${user.name} ${user.surname}</option>
                </c:forEach>
            </select>
        </form>
        <%--        <form method="get" action="users-list">--%>
        <%--            <label for="search-input">Поиск по логину:</label>--%>
        <%--            <input type="text" id="search-input" name="search" placeholder="Введите логин">--%>
        <%--            <button type="submit">Найти</button>--%>
        <%--        </form>--%>
        <%--        <table>--%>
        <%--            <thead>--%>
        <%--            <tr>--%>
        <%--                <th>ID</th>--%>
        <%--                <th>Логин</th>--%>
        <%--                <th>Имя</th>--%>
        <%--                <th>Фамилия</th>--%>
        <%--            </tr>--%>
        <%--            </thead>--%>
        <%--            <tbody>--%>
        <%--            &lt;%&ndash;            <c:forEach var="user" items="${users}">&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                <tr>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                    <td class="user-id">${user.id}</td>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                    <td class="user-login">${user.login}</td>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                    <td class="user-name">${user.name}</td>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                    <td class="user-surname">${user.surname}</td>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;                </tr>&ndash;%&gt;--%>
        <%--            &lt;%&ndash;            </c:forEach>&ndash;%&gt;--%>
        <%--            <%--%>
        <%--                LinkedList<User> users = (LinkedList<User>) request.getAttribute("users");--%>
        <%--                if (users != null) {--%>
        <%--                    for (User user : users) {--%>
        <%--            %>--%>
        <%--            <tr>--%>
        <%--                <td class="user-id"><%= user.getId() %>--%>
        <%--                </td>--%>
        <%--                <td class="user-login"><%= user.getLogin() %>--%>
        <%--                </td>--%>
        <%--                <td class="user-name"><%= user.getName() %>--%>
        <%--                </td>--%>
        <%--                <td class="user-surname"><%= user.getSurname() %>--%>
        <%--                </td>--%>
        <%--            </tr>--%>
        <%--            <%--%>
        <%--                    }--%>
        <%--                }--%>
        <%--            %>--%>
        <%--            </tbody>--%>
        <%--        </table>--%>
    </div>
</main>

<footer>
    <p>&copy; 2023 My Company</p>
</footer>

</body>
</html>
