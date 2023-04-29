// $(document).ready(function(){
//     $('form').submit(function(event) {
//         // Отменяем стандартное поведение формы
//         event.preventDefault();
//
//         // Отправляем данные формы на сервер
//         $.ajax({
//             type: 'POST',
//             url: '/save-data',
//             data: $(this).serialize(),
//             success: function(response) {
//                 // Обновляем данные на странице
//                 $('#user-info').text("Hello, " + response.name + " " + response.surname);
//                 // Очищаем
//                 $('input[name="first-name"]').val('');
//                 $('input[name="last-name"]').val('');
//             }
//         });
//     });
// });