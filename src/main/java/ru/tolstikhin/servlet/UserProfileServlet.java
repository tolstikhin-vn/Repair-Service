package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.tolstikhin.DAO.OrderHistoryDAO;
import ru.tolstikhin.DAO.RepairOrderDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.RepairOrder;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedList;

@WebServlet("/user")
public class UserProfileServlet extends HttpServlet {

    private RepairOrderDAO orderDao;

    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        orderDao = new RepairOrderDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            // Если пользователь не аутентифицирован, перенаправляем его на страницу входа
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            LinkedList<RepairOrder> orders = orderDao.getOrdersForUser(((User) session.getAttribute("user")).getId());
            if (orders != null) {
                request.setAttribute("ordersNumbers", orders);
            }

            // Если пользователь аутентифицирован, показываем страницу профиля
            request.getRequestDispatcher("/views/user-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
