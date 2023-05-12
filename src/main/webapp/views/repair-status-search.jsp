<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="ru.tolstikhin.entity.RepairOrder" %>
<%@ page import="ru.tolstikhin.entity.OrderHistory" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Статус ремонта</title>
    <link rel="stylesheet" href="/css/main-style.css">
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
    <div id="order-search-container">
        <form action="repair-status" method="get">
            <label for="repair-order">Номер заказа:</label>
            <input type="text" id="repair-order" name="repair-order" required>
            <input type="submit" value="Найти">
        </form>
    </div>
    <%
        RepairOrder order = (RepairOrder) request.getAttribute("order");
        LinkedList<OrderHistory> history = (LinkedList<OrderHistory>) request.getAttribute("history");
    %>
    <h2>Информация о заказе</h2>
    <% if (order != null) { %>
    <p>Номер заказа: <%= order.getOrderNumber() %>
    </p>
    <%--    <p>Описание проблемы: <%= order.getDescriptionProblem() %></p>--%>
    <p>Текущий статус: <%= history.getLast().getRepairStatus().getName() %>
    </p>
    <h2>История заказа</h2>
    <table>
        <thead>
        <tr>
            <th>Статус</th>
            <th>Дата начала</th>
            <th>Дата окончания</th>
        </tr>
        </thead>
        <tbody>
        <% for (OrderHistory item : history) { %>
        <tr>
            <td><%= item.getRepairStatus().getName() %>
            </td>
            <td><%
                if (item.getStartDatetime() != null) {
                    LocalDateTime startDatetime = item.getStartDatetime();
                    String formattedStartDatetime = startDatetime.format(formatter);
                    out.print(formattedStartDatetime);
                }
            %></td>
            <td><%
                if (item.getEndDatetime() != null) {
                    LocalDateTime endDatetime = item.getEndDatetime();
                    String formattedEndDatetime = endDatetime.format(formatter);
                    out.print(formattedEndDatetime);
                }
            %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>
</main>
<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>
</body>
</html>
