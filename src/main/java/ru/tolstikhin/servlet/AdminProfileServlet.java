package ru.tolstikhin.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AdminProfileServlet", value = "/admin")
public class AdminProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Если пользователь не аутентифицирован, перенаправляем его на страницу входа
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            System.out.println(session.getAttribute("user"));
            // Если пользователь аутентифицирован, показываем страницу профиля
            request.getRequestDispatcher("/views/admin-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
