<%@ page import="ru.tolstikhin.entity.ServiceCenter" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ru.tolstikhin.entity.RepairOrder" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="ru.tolstikhin.entity.RepairStatus" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Личный кабинет сотрудника</title>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/employee-profile-style.css">
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
    <div id="container">
        <form method="GET" action="/employee">
            <label for="service-center-select">Выберите сервисный центр: </label>
            <select id="service-center-select" name="service-center">
                <option value=""
                        <% if (request.getParameter("service-center") == null || request.getParameter("service-center").equals("")) { %>selected<% } %>>
                    Сервисный центр не выбран
                </option>
                <% for (ServiceCenter serviceCenter : (ArrayList<ServiceCenter>) request.getAttribute("serviceCenters")) { %>
                <option value="<%= serviceCenter.getId() %>"
                        <% if (request.getAttribute("selectedServiceCenter") != null && request.getAttribute("selectedServiceCenter").equals(Integer.toString(serviceCenter.getId()))){ %>selected<% } %>>
                    <%= serviceCenter.getAddress() %>
                </option>
                <% } %>
            </select>
            <button type="submit">Выбрать</button>
        </form>

        <form method="GET" action="/employee">
            <label for="repair-order-select">Заказ: </label>
            <input type="hidden" name="service-center" value="<%= request.getParameter("service-center") %>">
            <select id="repair-order-select" name="repair-order">
                <option value="" selected>Заказ не выбран</option>
                <% if (request.getAttribute("repairOrders") != null) { %>
                <% for (RepairOrder repairOrder : (LinkedList<RepairOrder>) request.getAttribute("repairOrders")) { %>
                <option id="order-number-value" value="<%= repairOrder.getOrderNumber() %>"
                        <% if (request.getAttribute("selectedOrder") != null && request.getAttribute("selectedOrder").equals(repairOrder.getOrderNumber())){ %>selected<% } %>>
                    <%= "№" + repairOrder.getOrderNumber() + " от " + repairOrder.getStartDatetime().format(formatter)%>
                </option>
                <% } %>
                <% } %>
            </select>
            <button type="submit">Выбрать</button>
        </form>

        <% if (request.getAttribute("order") != null) { %>
        <table id="order-table" class="order-table">
            <thead>
            <tr>
                <th>№ заказа</th>
                <th>Устройство</th>
                <th>Модель</th>
                <th>Номер телефона</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="repair-number"><%= ((RepairOrder) request.getAttribute("order")).getOrderNumber() %>
                </td>
                <td class="device"><%= ((RepairOrder) request.getAttribute("order")).getDeviceType() %>
                </td>
                <td class="model"><%= ((RepairOrder) request.getAttribute("order")).getDeviceName() %>
                </td>
                <td class="phone-number"><%= ((RepairOrder) request.getAttribute("order")).getClientPhoneNumber() %>
                </td>
            </tr>
            </tbody>
        </table>
        <p>Описание проблемы:</p>
        <p3>
            <%= ((RepairOrder) request.getAttribute("order")).getDescriptionProblem() %>
        </p3>

        <form method="GET" action="/repair-status" id="order-search-form">
            <input type="hidden" id="repair-order" name="repair-order"
                   value="<%= request.getAttribute("selectedOrder")%>" required>
            <input type="submit" value="История заказа">
        </form>

        <p>Управление заказом</p>

        <form method="POST" action="/employee">
            <input type="hidden" name="repair-status-id" id="repair-status-id">
            <label for="update-status-select">Обновить статус: </label>
            <select id="update-status-select" name="status">
                <option value="" selected>Статус не выбран</option>
                <% for (RepairStatus repairStatus : (ArrayList<RepairStatus>) request.getAttribute("repairStatuses")) { %>
                <option value="<%= repairStatus.getId() %>">
                    <%= repairStatus.getName() %>
                </option>
                <% } %>
            </select>
            <button type="submit" onclick="submitForm()">Обновить</button>
        </form>

        <% } %>

        <div id="log_out">
            <a href="logout">Выйти</a>
        </div>
    </div>
</main>

<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>

<script>
    function submitForm() {
        let selectElement = document.getElementById("update-status-select");
        let selectedOption = selectElement.options[selectElement.selectedIndex];
        document.getElementById("repair-status-id").value = selectedOption.value;
    }
</script>

</body>
</html>