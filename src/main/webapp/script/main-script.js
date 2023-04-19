function showDropdown() {
    const dropdown = document.getElementById("dropdown-content");
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            // Обновляем список адресов после получения ответа от сервлета
            dropdown.innerHTML = this.responseText;

            // Получаем список всех элементов списка адресов
            const addressItems = dropdown.getElementsByTagName("li");
            for (let i = 0; i < addressItems.length; i++) {
                const addressItem = addressItems[i];
                // Добавляем обработчик событий на каждый элемент списка адресов
                addressItem.addEventListener("click", function () {
                    const selectedAddress = addressItem.innerText;
                    // const selectedAddress = addressItem.innerText;
                    // Меняем текст надписи на странице на выбранный адрес
                    const addressText = document.getElementById("address");
                    addressText.innerText = selectedAddress;
                    dropdown.style.display = "none";

                    // Отправляем POST-запрос с выбранным адресом
                    const xhttp = new XMLHttpRequest();
                    xhttp.open("POST", "/addresses-list", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("address=" + selectedAddress);

                    // Перенаправляем на текущую страницу
                    window.location.href = location.href;
                });
            }

            // Показываем выпадающий список
            if (dropdown.style.display === "" || dropdown.style.display === "none") {
                dropdown.style.display = "block";
            } else {
                dropdown.style.display = "none";
            }
        }
    };
    xhttp.open("GET", "/addresses-list", true);
    xhttp.send();
}