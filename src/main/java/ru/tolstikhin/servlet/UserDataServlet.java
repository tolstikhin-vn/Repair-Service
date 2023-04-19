package ru.tolstikhin.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.entity.User;

import java.io.IOException;

@WebServlet("/user-data")
public class UserDataServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO(); // ваш класс DAO для работы с базой данных

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // получаем id пользователя из параметра запроса
        int userId = Integer.parseInt(request.getParameter("id"));

        // получаем данные пользователя из базы данных
        User user = userDAO.getUserById(userId);

        // создаем объект ObjectMapper для сериализации данных пользователя в JSON
        ObjectMapper mapper = new ObjectMapper();

        // преобразуем данные пользователя в JSON
        String json = mapper.writeValueAsString(user);

        // устанавливаем заголовок Content-Type для ответа
        response.setContentType("application/json");

        // отправляем данные пользователя в JSON клиенту
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
