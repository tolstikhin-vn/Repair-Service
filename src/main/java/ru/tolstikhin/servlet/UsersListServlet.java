package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.util.LinkedList;

@WebServlet("/users-list")
public class UsersListServlet extends HttpServlet {
    private UserDAO userDAO; // экземпляр класса для работы с БД

    public void init() {
        userDAO = new UserDAO(); // создаем экземпляр при запуске сервлета
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        LinkedList<User> users = userDAO.getUsers(); // получаем список пользователей из БД

        request.setAttribute("users", users); // передаем список пользователей на JSP страницу
        request.getRequestDispatcher("/views/admin-profile.jsp").forward(request, response); // перенаправляем на JSP страницу
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
