<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="ru.tolstikhin.entity.ServiceCenter" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.tolstikhin.entity.CenterMetroLink" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Адреса</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/addresses-page-style.css">
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

    <h1>Сервисные центры</h1>
    <%
        List<ServiceCenter> serviceCenters = (List<ServiceCenter>) request.getAttribute("serviceCenters");
        for (ServiceCenter serviceCenter : serviceCenters) {
    %>
    <div class="service-center">
        <h3><%=serviceCenter.getAddress()%>
        </h3>
        <ul>
            <% Set<String> addedMetroNames = new HashSet<>(); %>
            <% for (CenterMetroLink centerMetroLink : serviceCenter.getCenterMetroLinks()) { %>
            <% if (!addedMetroNames.contains(centerMetroLink.getMetro().getName())) { %>
            <li><%=centerMetroLink.getMetro().getName()%> (<%=centerMetroLink.getDistance()%> м)</li>
            <% addedMetroNames.add(centerMetroLink.getMetro().getName()); %>
            <% } %>
            <% } %>
        </ul>
        <% Set<String> addedPhoneNumbers = new HashSet<>(); %>
        <% if (serviceCenter.getPhoneNumbers().size() > 0) { %>
        <ul>
            <li><%=serviceCenter.getPhoneNumbers().get(0)%>
            </li>
            <% addedPhoneNumbers.add(serviceCenter.getPhoneNumbers().get(0)); %>
            <% for (int i = 1; i < serviceCenter.getPhoneNumbers().size(); i++) { %>
            <% if (!addedPhoneNumbers.contains(serviceCenter.getPhoneNumbers().get(i))) { %>
            <li>+<%=serviceCenter.getPhoneNumbers().get(i)%>
            </li>
            <% addedPhoneNumbers.add(serviceCenter.getPhoneNumbers().get(i)); %>
            <% } %>
            <% } %>
        </ul>
        <% } %>
    </div> <!-- Закрытие блока сервисного центра -->
    <% } %>
</main>

<%--<footer>--%>
<!-- Футер страницы -->
<%--</footer>--%>

</body>
</html>
