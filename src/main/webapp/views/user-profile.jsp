<%@ page import="java.util.LinkedList" %>
<%@ page import="ru.tolstikhin.entity.RepairOrder" %>
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
    <h1>Личный кабинет</h1>
    <div class="personal-data">
        <h2>Персональная информация</h2>
        <form method="post" action="save-data">
            <label for="first-name">Имя:</label>
            <input type="text" id="first-name" name="first-name" required>
            <label for="last-name">Фамилия:</label>
            <input type="text" id="last-name" name="last-name">
            <input type="submit" value="Сохранить">
        </form>
    </div>

    <div class="orders-list">
        <h2>Список отслеживаемых заказов:</h2>
        <ul>
            <%
                // получаем из базы данных список номеров заказов пользователя
                LinkedList<RepairOrder> orders = (LinkedList<RepairOrder>) request.getAttribute("ordersNumbers");
                // выводим номера заказов на страницу
                for (RepairOrder order : orders) {
            %>
            <li>
                <a href="repair-status?repair-order=<%= order.getOrderNumber() %>" class="order-number" data-order-number="<%= order.getOrderNumber() %>">
                    <%= order.getOrderNumber() %>
                </a>
                <% if (order.isCompleted() && !order.isRated()) { %>
                <a class="leave-feedback" data-order-id="<%= order.getId() %>">Оставить отзыв</a>
                <% } %>
            </li>
            <% } %>
        </ul>
    </div>

    <div id="feedback-modal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Оставить отзыв о заказе></h2>
            <form id="feedback-form" method="POST" action="/feedback">
                <input type="hidden" name="orderId" value="order">
                <label for="feedback-text">Отзыв:</label>
                <textarea id="feedback-text" name="feedbackText" required></textarea>
                <label for="rating">Оценка:</label>
                <select id="rating" name="rating" required>
                    <option value="">-- Выберите оценку --</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
                <input type="hidden" id="order-id" name="order-id" value="">
                <input type="submit" value="Отправить">
            </form>
        </div>
    </div>

    <br>
    <div id="log_out_class">
        <a href="logout">Выйти</a>
    </div>
</main>
<script>
    $(document).ready(function() {
        // показываем диалоговое окно при клике на ссылку-кнопку
        $('.leave-feedback').click(function(e) {
            e.preventDefault();
            var orderId = $(this).data('order-id');
            $('#feedback-form input[name=orderId]').val(orderId);
            $('#order-id').val(orderId);
            $('#feedback-modal').show();
        });

        // закрываем диалоговое окно при клике на крестик или вне его
        $('.close, #feedback-modal').click(function(e) {
            if (e.target == $('#feedback-modal')[0] || e.target == $('.close')[0]) {
                $('#feedback-modal').hide();
            }
        });
    });
</script>
<footer>
    <div id="footer">
        <p>&copy; 2023 "ЭЛЕКТРОНИКУС"</p>
    </div>
</footer>
</body>
</html>
