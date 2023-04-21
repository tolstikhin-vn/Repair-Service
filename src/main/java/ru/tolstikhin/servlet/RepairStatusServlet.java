package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.tolstikhin.DAO.OrderHistoryDAO;
import ru.tolstikhin.DAO.RepairOrderDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.OrderHistory;
import ru.tolstikhin.entity.RepairOrder;

import java.io.IOException;
import java.sql.Connection;
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
        // получаем информацию о заказе из базы данных
        RepairOrder order = orderDao.getByOrderNumber(orderNumber);
        if (order != null) {
            // получаем историю заказа из базы данных
            List<OrderHistory> history = historyDao.getByOrderId(order.getId());

            // передаем информацию в JSP-страницу
            request.setAttribute("order", order);
            request.setAttribute("history", history);

        }

        // перенаправляем пользователя на JSP-страницу
        request.getRequestDispatcher("/views/repair-status-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
