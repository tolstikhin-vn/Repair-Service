package ru.tolstikhin.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO; // экземпляр класса для работы с БД

    public void init() {
        userDAO = new UserDAO(); // создаем экземпляр при запуске сервлета
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session == null || session.getAttribute("user") == null) {
            // Если пользователь не авторизован, перенаправляем на страницу входа
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/login.jsp");
            dispatcher.forward(request, response);
        } else {
            // Иначе открываем страницу в соответствии с его ролью
            switchRole(((User) session.getAttribute("user")).getLogin(), response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        // Проверяем, что логин и пароль верные
        if (isUserLogIn(username, password)) {
            UserDAO userDAO = new UserDAO();
            ResultSet resultSet = userDAO.getUserData(username);
            if (resultSet != null) {
                try {
                    String surname = null;
                    String name = null;
                    while (resultSet.next()) {
                        surname = resultSet.getString("surname");
                        name = resultSet.getString("name");
                    }
                    // Создаем объект типа User
                    user = new User(username, password, surname, name);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }

            // Если пользователь аутентифицирован, сохраняем его имя в атрибутах сеанса
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Перенаправляем пользователя на страницу профиля в соответствии с его ролью
            switchRole(username, response);

        } else {
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    private boolean isUserLogIn(String username, String password) {
        return userDAO.logIn(username, password);
    }

    private void switchRole(String login, HttpServletResponse response) throws IOException {
        System.out.println((userDAO.getMainUserRole(login)));
        switch (userDAO.getMainUserRole(login)) {
            case "Администратор":
                response.sendRedirect("/admin");
                break;
            case "Пользователь":
                response.sendRedirect("/user");
                break;
            case "Сотрудник":
                response.sendRedirect("/employee");
                break;
            default:
                response.sendRedirect("/");
                break;
        }
    }
}
