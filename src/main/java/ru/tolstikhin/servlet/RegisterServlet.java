package ru.tolstikhin.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.User;

import java.io.IOException;
@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO; // экземпляр класса для работы с БД

    public void init() {
        userDAO = new UserDAO(); // создаем экземпляр при запуске сервлета
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // После успешной регистрации перенаправляем на страницу входа
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/registration.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        // получаем параметры формы из запроса
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // проверяем, удалось ли создать пользователя
        if (addUser(username, password)) {
            // если пользователь создан, перенаправляем на страницу входа
            response.sendRedirect("/login");
        } else {
            // если пользователь не создан, перенаправляем на страницу регистрации с сообщением об ошибке
            request.setAttribute("errorMessage", "Failed to create user.");
            request.getRequestDispatcher("/views/registration.jsp").forward(request, response);
        }
    }

    // Метод для создания пользователя в базе данных
    private boolean addUser(String username, String password) {
        return userDAO.executeRegQuery(username, password) != null;
    }
}

