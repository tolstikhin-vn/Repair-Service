<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="ru.tolstikhin.entity.RepairOrder"%>
<%@ page import="ru.tolstikhin.entity.OrderHistory"%>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Статус ремонта</title>
    <link rel="stylesheet" href="/css/main-style.css">
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="repair-status">Узнать статус ремонта</a></li>
            <li><a href="about">О компании</a></li>
            <li><a href="addresses">Адреса и контакты</a></li>
        </ul>
    </nav>
    <div id="login">
        <a href="login">Профиль</a>
    </div>
</header>
<main>
    <form action="repair-status" method="get">
        <label for="repair-order">Номер заказа:</label>
        <input type="text" id="repair-order" name="repair-order" required>
        <input type="submit" value="Найти">
    </form>
    <%
        RepairOrder order = (RepairOrder) request.getAttribute("order");
        LinkedList<OrderHistory> history = (LinkedList<OrderHistory>) request.getAttribute("history");
    %>
    <% if (order != null) { %>
    <h2>Информация о заказе</h2>
    <p>Номер заказа: <%= order.getOrderNumber() %></p>
    <%--    <p>Описание проблемы: <%= order.getDescriptionProblem() %></p>--%>
    <p>Текущий статус: <%= history.getLast().getRepairStatus().getName() %></p>
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
            <td><%= item.getRepairStatus().getName() %></td>
            <td><%
                LocalDateTime startDatetime = item.getStartDatetime();
                String formattedStartDatetime = startDatetime.format(formatter);
                out.print(formattedStartDatetime);
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
</body>
</html>
