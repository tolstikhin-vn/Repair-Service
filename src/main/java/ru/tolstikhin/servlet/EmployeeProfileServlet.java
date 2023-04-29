package ru.tolstikhin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import ru.tolstikhin.DAO.RepairOrderDAO;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.DAO.UserDAO;
import ru.tolstikhin.entity.RepairOrder;
import ru.tolstikhin.entity.ServiceCenter;
import ru.tolstikhin.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

@WebServlet(name = "EmployeeProfileServlet", value = "/employee")
public class EmployeeProfileServlet extends HttpServlet {

    private ServiceCenterDAO serviceCenterDAO;
    private RepairOrderDAO repairOrderDAO;

    public void init() {
        serviceCenterDAO = new ServiceCenterDAO(); // создаем экземпляр при запуске сервлета
        repairOrderDAO = new RepairOrderDAO(); // создаем экземпляр при запуске сервлета
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
                String selectedServiceCenterId = request.getParameter("service-center");
                session.setAttribute("selectedServiceCenterId", selectedServiceCenterId);
                request.setAttribute("repairOrders", repairOrderDAO.getOrdersForCenter(Integer.parseInt(request.getParameter("service-center"))));
            }

            if (request.getParameter("repair-order") != null) {
                String selectedOrderNumber = request.getParameter("repair-order");
                session.setAttribute("selectedOrder", selectedOrderNumber);
                request.setAttribute("selectedOrder", selectedOrderNumber);
                repairOrderDAO.getByOrderNumber(selectedOrderNumber);
                request.setAttribute("order", repairOrderDAO.getByOrderNumber(selectedOrderNumber));
            }
            request.getRequestDispatcher("/views/employee-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/employee-profile.jsp").forward(request, response);
    }
}
