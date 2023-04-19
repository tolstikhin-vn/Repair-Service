package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EmployeeProfileServlet", value = "/employee")
public class EmployeeProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Если пользователь не аутентифицирован, перенаправляем его на страницу входа
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            System.out.println(session.getAttribute("user"));
            // Если пользователь аутентифицирован, показываем страницу профиля
            request.getRequestDispatcher("/views/employee-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
