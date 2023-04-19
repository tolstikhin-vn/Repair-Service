package ru.tolstikhin.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.tolstikhin.DAO.ServiceCenterDAO;
import ru.tolstikhin.controller.SQLController;
import ru.tolstikhin.entity.ServiceCenter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ShowAddressesServlet", value = "/show-addresses")
public class ShowAddressesServlet extends HttpServlet {
    private ServiceCenterDAO serviceCenterDAO;

    private SQLController sqlController = new SQLController();

    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = sqlController.getConnection();
        serviceCenterDAO = new ServiceCenterDAO(connection);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<ServiceCenter> serviceCenters = serviceCenterDAO.getAll();
            request.setAttribute("serviceCenters", serviceCenters);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error getting service centers", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
