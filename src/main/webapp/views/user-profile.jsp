<%@ page import="ru.tolstikhin.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile Page</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/user-profile-style.css">
    <script>
        $(document).ready(function(){
            $('form').submit(function(event) {
                // Отменяем стандартное поведение формы
                event.preventDefault();

                // Отправляем данные формы на сервер
                $.ajax({
                    type: 'POST',
                    url: '/save-data',
                    data: $(this).serialize(),
                    success: function(response) {
                        // Обновляем данные на странице
                        $('#user-info').text("Hello, " + response.name + " " + response.surname);
                        // Очищаем
                        $('input[name="first-name"]').val('');
                        $('input[name="last-name"]').val('');
                    }
                });
            });
        });
    </script>
</head>
<body>
<header>
    <a href="/">
        <svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="64px" height="64px"
             style="shape-rendering:geometricPrecision; text-rendering:geometricPrecision; image-rendering:optimizeQuality; fill-rule:evenodd; clip-rule:evenodd"
        >
            <g>
                <path style="opacity:0.733" fill="#000000"
                      d="M 8.5,-0.5 C 19.8333,-0.5 31.1667,-0.5 42.5,-0.5C 44.6534,3.15149 45.9867,7.15149 46.5,11.5C 47.7362,12.2458 49.0695,12.5792 50.5,12.5C 49.736,15.787 47.9027,18.4537 45,20.5C 44.51,21.7932 44.3433,23.1266 44.5,24.5C 45.552,24.6495 46.552,24.4828 47.5,24C 55.7003,15.8187 58.5336,17.6521 56,29.5C 54.0326,33.4059 50.8659,35.7393 46.5,36.5C 45.6687,43.8108 45.1687,51.1441 45,58.5C 44.6339,60.4517 43.8005,62.1184 42.5,63.5C 31.1667,63.5 19.8333,63.5 8.5,63.5C 8.21719,62.7109 7.71719,62.0442 7,61.5C 6.33333,41.5 6.33333,21.5 7,1.5C 7.71719,0.955796 8.21719,0.28913 8.5,-0.5 Z M 11.5,2.5 C 13.5901,2.45014 14.9234,3.45014 15.5,5.5C 22.1667,6.83333 28.8333,6.83333 35.5,5.5C 36.384,3.05796 38.0507,2.22462 40.5,3C 41.4505,6.05675 41.7839,9.22341 41.5,12.5C 39.0372,12.7811 36.8705,13.7811 35,15.5C 33.0306,19.2318 32.0306,23.2318 32,27.5C 27.9076,30.5658 25.0742,34.3991 23.5,39C 24.0621,44.5529 27.0621,46.5529 32.5,45C 35.3333,42.1667 38.1667,39.3333 41,36.5C 41.6667,44.1667 41.6667,51.8333 41,59.5C 31.2441,60.6515 21.4107,60.8181 11.5,60C 11,59.5 10.5,59 10,58.5C 9.33333,40.5 9.33333,22.5 10,4.5C 10.7172,3.9558 11.2172,3.28913 11.5,2.5 Z M 41.5,15.5 C 42.552,15.3505 43.552,15.5172 44.5,16C 40.3561,18.7204 39.5228,22.2204 42,26.5C 46.3642,29.6589 50.0308,28.9922 53,24.5C 51.514,31.5958 47.014,34.4291 39.5,33C 36.8333,35.6667 34.1667,38.3333 31.5,41C 30.1667,41.6667 28.8333,41.6667 27.5,41C 26.5494,40.2825 26.3828,39.4491 27,38.5C 30.7274,35.2759 33.8941,31.6092 36.5,27.5C 35.0475,22.189 36.7142,18.189 41.5,15.5 Z"/>
            </g>
            <g>
                <path style="opacity:0.708" fill="#000000"
                      d="M 23.5,50.5 C 28.4943,50.329 30.3276,52.6624 29,57.5C 26.6667,60.1667 24.3333,60.1667 22,57.5C 21.1668,54.829 21.6668,52.4957 23.5,50.5 Z"/>
            </g>
        </svg>
    </a>
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
    <h1>Welcome to your Profile Page</h1>
    <div class="personal-data">
        <h2>Personal Information</h2>
        <p id="user-info">Hello, <%= ((User) session.getAttribute("user")).getName() + " " + ((User) session.getAttribute("user")).getFam() %>
        <p>Your login is: <%= ((User) session.getAttribute("user")).getLogin() %>
        <form method="post" action="save-data">
            <label for="first-name">First Name:</label>
            <input type="text" id="first-name" name="first-name" required>
            <label for="last-name">Last Name:</label>
            <input type="text" id="last-name" name="last-name">
            <input type="submit" value="Save">
        </form>
    </div>
    <div class="log_out_class">
        <a href="logout">Выйти</a>
    </div>
</main>
</body>
</html>
