package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.tolstikhin.DAO.OrderHistoryDAO;
import ru.tolstikhin.DAO.OrderStatusDAO;
import ru.tolstikhin.DAO.RepairOrderDAO;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.ServiceCenter;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

@WebServlet(name = "EmployeeProfileServlet", value = "/employee")
public class EmployeeProfileServlet extends HttpServlet {

    private ServiceCenterDAO serviceCenterDAO;
    private RepairOrderDAO repairOrderDAO;
    private OrderStatusDAO orderStatusDAO;
    private OrderHistoryDAO orderHistoryDAO;
    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        serviceCenterDAO = new ServiceCenterDAO();
        repairOrderDAO = new RepairOrderDAO();
        orderStatusDAO = new OrderStatusDAO();
        orderHistoryDAO = new OrderHistoryDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {

            // Если пользователь не аутентифицирован, перенаправляем его на страницу входа
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // Если пользователь аутентифицирован, показываем страницу профиля
            ArrayList<ServiceCenter> serviceCenters = serviceCenterDAO.getCentersByEmployee(((User) session.getAttribute("user")).getId());
            request.setAttribute("serviceCenters", serviceCenters);

            if (request.getParameter("service-center") != null) {
                String selectedServiceCenter = request.getParameter("service-center");
                setAttributesForServiceCenter(request, session, selectedServiceCenter);
            }

            if (request.getParameter("repair-order") != null) {
                String selectedOrderNumber = request.getParameter("repair-order");
                setAttributesForRepairOrder(request, session, selectedOrderNumber);
            }
            request.getRequestDispatcher("/views/employee-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        ArrayList<ServiceCenter> serviceCenters = serviceCenterDAO.getCentersByEmployee(((User) session.getAttribute("user")).getId());
        request.setAttribute("serviceCenters", serviceCenters);

        String selectedServiceCenter = (String) session.getAttribute("selectedServiceCenter");
        String selectedOrderNumber = (String) session.getAttribute("selectedOrder");

        orderHistoryDAO.addNewOrderStatus(selectedOrderNumber, Integer.parseInt(request.getParameter("repair-status-id")), ((User) session.getAttribute("user")).getId());

        setAttributesForServiceCenter(request, session, selectedServiceCenter);
        setAttributesForRepairOrder(request, session, selectedOrderNumber);

        request.getRequestDispatcher("/views/employee-profile.jsp").forward(request, response);
    }

    private void setAttributesForServiceCenter(HttpServletRequest request, HttpSession session, String selectedServiceCenter) {
        session.setAttribute("selectedServiceCenter", selectedServiceCenter);
        request.setAttribute("selectedServiceCenter", selectedServiceCenter);
        request.setAttribute("repairOrders", repairOrderDAO.getOrdersForCenter(Integer.parseInt(selectedServiceCenter)));
    }

    private void setAttributesForRepairOrder(HttpServletRequest request, HttpSession session, String selectedOrderNumber) {
        session.setAttribute("selectedOrder", selectedOrderNumber);
        request.setAttribute("selectedOrder", selectedOrderNumber);

        request.setAttribute("order", repairOrderDAO.getByOrderNumber(selectedOrderNumber));

        request.setAttribute("repairStatuses", orderStatusDAO.getAllStatuses());
    }
}