// Создаем новый XMLHttpRequest объект
var xhr = new XMLHttpRequest();

// Настраиваем запрос
xhr.open('GET', '/addresses');

// Отправляем запрос
xhr.send();

// Обрабатываем ответ от сервера
xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
        var addresses = JSON.parse(xhr.responseText);
        var container = document.getElementById('addresses-container');

        // Создаем HTML элементы для каждого адреса и добавляем их в контейнер
        addresses.forEach(function (address) {
            var div = document.createElement('div');
            div.classList.add('address');
            div.innerHTML = `
          <h2>${address.name}</h2>
          <p>${address.address}</p>
          <p>${address.phone_number}</p>
        `;
            container.appendChild(div);
        });
    }
};