package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.tolstikhin.DAO.OrderHistoryDAO;
import ru.tolstikhin.DAO.RepairOrderDAO;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.OrderHistory;
import ru.tolstikhin.entity.RepairOrder;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RepairStatusServlet", value = "/repair-status")
public class RepairStatusServlet extends HttpServlet {

    private RepairOrderDAO orderDao;
    private OrderHistoryDAO historyDao;

    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        orderDao = new RepairOrderDAO(connection);
        historyDao = new OrderHistoryDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String orderNumber = request.getParameter("repair-order");
        if (orderNumber != null) {
            // получаем информацию о заказе из базы данных
            RepairOrder order = orderDao.getByOrderNumber(orderNumber);
            // получаем историю заказа из базы данных
            List<OrderHistory> history = historyDao.getByOrderId(order.getId());

            // передаем информацию в JSP-страницу
            request.setAttribute("order", order);
            request.setAttribute("history", history);

            HttpSession session = request.getSession();

            if (session != null && session.getAttribute("user") != null && ((User) session.getAttribute("user")).getUserRoleId() == 2) {
                try {
                    linkUserAndOrder(((User) session.getAttribute("user")).getId(), order.getId());
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // перенаправляем пользователя на JSP-страницу
        request.getRequestDispatcher("/views/repair-status-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void linkUserAndOrder(int userId, int orderId) throws SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.updateUserOrderLink(userId, orderId);
    }
}
