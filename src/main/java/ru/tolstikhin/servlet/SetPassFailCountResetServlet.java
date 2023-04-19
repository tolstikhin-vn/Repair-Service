package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.tolstikhin.DAO.UserDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SetPassFailCountResetServlet", value = "/set-pass-fail-count-reset")
public class SetPassFailCountResetServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserBanServlet.class);

    private UserDAO userDAO; // экземпляр класса для работы с БД

    public void init() {
        userDAO = new UserDAO(); // создаем экземпляр при запуске сервлета
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id-user-for-changing"));
        try {
            if (userDAO.setPassFailCountReset(userId)) {
                response.sendRedirect("/admin");
            } else {
                response.sendRedirect("/admin");
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
