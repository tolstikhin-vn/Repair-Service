<%@ page import="ru.tolstikhin.entity.PriceList" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ремонт смартфонов</title>
    <link rel="stylesheet" href="/css/main-style.css">
    <link rel="stylesheet" href="/css/services-style.css">
    <script src="/script/main-script.js"></script>
</head>
<body>
<header>
    <nav>
        <ul>
            <li><a href="track-repairs">Узнать статус ремонта</a></li>
            <li><a href="about">О нас</a></li>
            <li><a href="addresses">Адреса и контакты</a></li>
        </ul>
        <span class="dropdown">
            <%
                String address = (String) session.getAttribute("selectedAddress");
                if (address == null) {
                    address = "Выберите сервисный центр";
                }
            %>
            <p id="address" onclick="showDropdown()">
                <%= address %>
            </p>
            <ul id="dropdown-content">
            </ul>
        </span>
    </nav>
    <div id="login">
        <a href="login">Профиль</a>
    </div>
</header>
<main>
    <div class="entry-content">
        <div class="wp-block-columns mg-b-0">
            <div class="wp-block-column image-top"><img class="img-page lazyloaded"
                                                        src="/images/services/remont-tablets.png"
                                                        alt="Ремонт планшетов" data-ll-status="loaded">
            </div>
            <div class="wp-block-column text-top">
                <div class="wp-block-columns mg-b-0">
                    <div class="wp-block-column top-2-futers"><p class="blocks-top"><img
                            class="img-futures-top lazyloaded"
                            src="/images/services/watch.png"
                            alt="Время ремонта"
                            data-ll-status="loaded">
                        <span class="txt-futures-top">Время ремонта<br><strong>от <span
                                class="txt-19px">20</span> <i>мин</i></strong></span></p> </div>
                    <div class="wp-block-column top-2-futers"><p class="blocks-top"><img
                            class="img-futures-top lazyloaded"
                            src="/images/services/wallet.png"
                            alt="Стоимость ремонта"
                            data-ll-status="loaded">
                        <span class="txt-futures-top">Стоимость ремонта<br><strong>от <span class="txt-19px;">690</span> <i>руб</i></strong><span
                                class="zirka">*</span></span></p> </div>
                </div>
                <p>Бесплатно проведем диагностику планшета, точно определим причину поломки, подберем фирменную деталь,
                    сообщим стоимость восстановительных работ и срочно выполним качественный ремонт планшета в Москве, и
                    предоставим фирменную гарантию!</p>
            </div>
        </div>
        <div class="wp-block-columns mg-b-0 mg-t--15"><!-- wp:column -->
            <div class="wp-block-column"><!-- wp:html --> <p class="blocks-top"><img class="img-futures-top lazyloaded"
                                                                                     src="/images/services/garantiya.png"
                                                                                     alt="Гарантия на ремонт"
                                                                                     data-ll-status="loaded">
                <span class="txt-futures-top">Фирменная<br>гарантия</span></p> <!-- /wp:html --></div>
            <!-- /wp:column --> <!-- wp:column -->
            <div class="wp-block-column"><!-- wp:html --> <p class="blocks-top"><img class="img-futures-top lazyloaded"
                                                                                     src="/images/services/original-zapchasty.png"
                                                                                     alt="Оригинальные запчасти"
                                                                                     data-ll-status="loaded">
                <span class="txt-futures-top">Брендовые<br> запчасти</span></p> <!-- /wp:html --></div>
            <!-- /wp:column --> <!-- wp:column -->
            <div class="wp-block-column"><!-- wp:html --> <p class="blocks-top"><img class="img-futures-top lazyloaded"
                                                                                     src="/images/services/diagnostick.png"
                                                                                     alt="Бесплатная диагностика"
                                                                                     data-ll-status="loaded">
                <span class="txt-futures-top">Бесплатная<br> диагностика</span></p> <!-- /wp:html --></div>
            <!-- /wp:column --> <!-- wp:column -->
            <div class="wp-block-column"><!-- wp:html --> <p class="blocks-top"><img class="img-futures-top lazyloaded"
                                                                                     src="/images/services/team.png"
                                                                                     alt="Опытные мастера"
                                                                                     data-ll-status="loaded">
                <span class="txt-futures-top">Опытные<br> инженеры</span>
            </p>
            </div>
        </div>
        <p class="mg-b-40">Наш сервисный центр осуществляет ремонт планшетов всех производителей, а также любых других
            девайсов и гаджетов. Мастера нашей компании имеют не только высокую квалификацию, но и многолетний опыт
            ремонта самой разной электроники и компьютерной техники, поэтому способны выполнить качественный ремонт
            планшета или другого современного устройства любого типа за очень короткий промежуток времени. Огромный
            ассортимент оригинальных запасных комплектующих – от плат и матриц до корпусов – позволяет им быстро
            подобрать любую нужную деталь и выполнить замену поврежденного элемента.</p>
        <h3 class="text-uppercase table-width">Цена ремонта планшетов</h3>
        <div class="price-list">
            <div class="row-top">
                <div class="service-name">Наименование услуги</div>
                <div class="service-price">Стоимость</div>
                <div class="service-terms">Сроки</div>
            </div>
            <% LinkedList<PriceList> priceLists = (LinkedList<PriceList>) request.getAttribute("priceLists");
                if (priceLists != null) {
                    for (PriceList priceList : priceLists) { %>
            <div class="price-item">
                <div class="service-name"><%= priceList.getName() %>
                </div>
                <div class="service-price">
                    <% if (priceList.getPrice() == 0) { %>
                    Бесплатно
                    <% } else { %>
                    от <%= priceList.getPrice() %> руб.
                    <% } %>
                </div>
                <div class="service-terms">от <%= priceList.getTerms() %>
                </div>
            </div>
            <% }
            }%>
        </div>

    </div>

</main>
</body>
</html>
